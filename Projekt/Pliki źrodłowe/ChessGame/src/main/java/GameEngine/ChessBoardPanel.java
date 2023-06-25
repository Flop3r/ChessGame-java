/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine;

import GameEngine.Board.Board;
import GameEngine.Board.StartingBoard;
import GameEngine.Moves.CastlingMove;
import GameEngine.Moves.EnPassantMove;
import GameEngine.Moves.Move;
import GameEngine.Pieces.King;
import GameEngine.Pieces.Pawn;
import GameEngine.Pieces.Piece;
import GameEngine.Pieces.Rook;
import GameEngine.Utils.ChessColor;
import GameEngine.Utils.GameConst;
import GameEngine.Utils.PaintUtils;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Panel reprezentujący szachownicę i rysujący jej aktualny stan.
 */
public class ChessBoardPanel extends JPanel implements MouseListener {
    @Getter
    @Setter
    private Board board;
    @Getter
    @Setter
    private int selX = -1, selY = -1;
    @Getter
    @Setter
    private Piece selectedPiece = null;

    /**
     * Konstruktor klasy ChessBoardPanel.
     * Tworzy nową szachownicę początkową.
     */
    public ChessBoardPanel() {
        this.board = new StartingBoard();
        addMouseListener(this);

        //this.board = new DrawTestBoard();
    }

    /**
     * Metoda odpowiedzialna za rysowanie szachownicy i figur na panelu.
     * Wywoływana automatycznie przez system.
     *
     * @param g Obiekt klasy Graphics, służący do rysowania.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawBoard(g2d);
        drawHighlightedSquares(g2d);
        drawPieces(g2d);
        drawPossibleMoves(g2d);

    }

    /**
     * Metoda zwracająca preferowany rozmiar panelu.
     * Ustala, że panel powinien mieć rozmiar odpowiadający szachownicy (8 pól x 64 piksele).
     *
     * @return Preferowany rozmiar panelu.
     */
    @Override
    public Dimension getPreferredSize() {
        int boardSize = 8 * 64; // Rozmiar szachownicy (8 pól x 64 piksele)
        return new Dimension(boardSize, boardSize);
    }

    /**
     * Obsługa zdarzenia kliknięcia myszą.
     *
     * @param e Obiekt klasy MouseEvent przechowujący informacje o zdarzeniu.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (ChessGame.isGameOver()) {
            return; // Jeśli gra jest zakończona, zignoruj kliknięcie
        }

        int xp = e.getX() / 64;
        int yp = e.getY() / 64;

        if (selX == -1) {
            handleEmptySquareOrOpponentPiece(xp, yp);
        } else if (selX == xp && selY == yp) {
            unselectPiece();
        } else if (selectedPiece != null) {
            handleMove(xp, yp);
            handleCheckmate(board.getRound());
            unselectPiece();
        }

        this.repaint();
    }

    // Pozostałe metody MouseListener są nieużywane i mogą pozostać puste

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Metoda rysująca szachownicę na panelu.
     *
     * @param g2d Obiekt klasy Graphics2D służący do rysowania.
     */
    private void drawBoard(Graphics2D g2d) {
        boolean colorMain = true;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Color color = colorMain ? GameConst.FST_COLOR : GameConst.SND_COLOR;
                PaintUtils.drawSquare(g2d, x, y, color, 1);
                colorMain = !colorMain;
            }
            colorMain = !colorMain;
        }
    }

    /**
     * Metoda rysująca podświetlone pola na szachownicy.
     *
     * @param g2d Obiekt klasy Graphics2D służący do rysowania.
     */
    private void drawHighlightedSquares(Graphics2D g2d) {
        highlightCheck(g2d);
        if (selectedPiece != null) {
            PaintUtils.drawSquare(g2d, selX, selY, GameConst.HIGHLIGHT_COLOR, 0.5f);
        }
    }

    /**
     * Metoda podświetlająca pole, na którym znajduje się szachowany król.
     *
     * @param g2d Obiekt klasy Graphics2D służący do rysowania.
     */
    private void highlightCheck(Graphics2D g2d) {
        ChessColor round = board.getRound();
        King king = board.getKing(round);
        if (king != null && board.isCheck(round)) {
            int x = king.getX();
            int y = king.getY();
            PaintUtils.drawSquare(g2d, x, y, GameConst.CHECK_COLOR, GameConst.HIGHLIGHT_ALPHA);

            if (board.isCheckMate(round)) {
                PaintUtils.drawSquare(g2d, x, y, GameConst.CHECKMATE_COLOR, GameConst.HIGHLIGHT_ALPHA);
            }
        }
    }

    /**
     * Metoda rysująca możliwe ruchy dla zaznaczonej figury.
     *
     * @param g2d Obiekt klasy Graphics2D służący do rysowania.
     */
    private void drawPossibleMoves(Graphics2D g2d) {
        if (selectedPiece != null) {
            LinkedList<Move> potentialMoves = board.filterPotentialMoves(selectedPiece);
            for (Move move : potentialMoves) {
                int x = move.getX();
                int y = move.getY();
                if (board.getPiece(x, y) == null) {
                    PaintUtils.fillOval(g2d, x, y, 24, GameConst.MOVES_COLOR, GameConst.MOVES_ALPHA);
                } else {
                    PaintUtils.drawOval(g2d, x, y, 60, GameConst.MOVES_COLOR, GameConst.MOVES_ALPHA);
                }
            }
        }
    }

    /**
     * Metoda rysująca figury na szachownicy.
     *
     * @param g2d Obiekt klasy Graphics2D służący do rysowania.
     */
    private void drawPieces(Graphics2D g2d) {
        for (Piece piece : board.getPieces()) {
            BufferedImage image = piece.getImage();
            if (image != null) {
                Image scaledImage = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                g2d.drawImage(scaledImage, piece.getX() * 64, piece.getY() * 64, this);
            }
        }
    }

    /**
     * Metoda obsługująca zdarzenie kliknięcia na puste pole lub pole z figurą przeciwnika.
     *
     * @param xp Pozycja x kliknięcia.
     * @param yp Pozycja y kliknięcia.
     */
    private void handleEmptySquareOrOpponentPiece(int xp, int yp) {
        selectedPiece = board.getPiece(xp, yp);
        if (selectedPiece != null && selectedPiece.getColor() == board.getRound()) {
            selX = xp;
            selY = yp;
        } else {
            selectedPiece = null;
        }
    }

    /**
     * Metoda odznaczająca zaznaczoną figurę.
     */
    private void unselectPiece() {
        selX = -1;
        selY = -1;
        selectedPiece = null;
    }

    /**
     * Metoda obsługująca wykonanie ruchu na szachownicy.
     *
     * @param xp Pozycja x docelowego pola.
     * @param yp Pozycja y docelowego pola.
     */
    private void handleMove(int xp, int yp) {
        Move move;
        if (selectedPiece instanceof King && xp == selX + 2) {
            move = createCastlingMove(xp, yp, selX + 3, selY, selX + 1, selY);
        } else if (selectedPiece instanceof King && xp == selX - 3) {
            move = createCastlingMove(xp, yp, selX - 4, selY, selX - 1, selY);
        } else if (selectedPiece instanceof Pawn && board.getPiece(xp, yp) == null && xp - selX == -1 && Math.abs(yp - selY) == 1) {
            move = createEnPassantMove(xp, yp, selX - 1, selY);
        } else if (selectedPiece instanceof Pawn && board.getPiece(xp, yp) == null && xp - selX == 1 && Math.abs(yp - selY) == 1) {
            move = createEnPassantMove(xp, yp, selX + 1, selY);
        } else {
            move = new Move(xp, yp);
        }

        if (board.isMoveLegal(selectedPiece, move) && selectedPiece.getColor() == board.getRound()) {
            board.executeMove(selectedPiece, move);
        }
    }

    /**
     * Metoda tworząca ruch roszady.
     *
     * @param kingX    Pozycja x króla po roszadzie.
     * @param kingY    Pozycja y króla po roszadzie.
     * @param rookX    Pozycja x wieży po roszadzie.
     * @param rookY    Pozycja y wieży po roszadzie.
     * @param newRookX Nowa pozycja x wieży.
     * @param newRookY Nowa pozycja y wieży.
     * @return Obiekt klasy CastlingMove reprezentujący ruch roszady.
     */
    private Move createCastlingMove(int kingX, int kingY, int rookX, int rookY, int newRookX, int newRookY) {
        Rook rook = (Rook) board.getPiece(rookX, rookY);
        return new CastlingMove(kingX, kingY, rook, newRookX, newRookY);
    }

    /**
     * Metoda tworząca ruch bicia w przelocie.
     *
     * @param newX          Pozycja x docelowego pola.
     * @param newY          Pozycja y docelowego pola.
     * @param capturedPawnX Pozycja x bierki przeciwnika bijącej w przelocie.
     * @param capturedPawnY Pozycja y bierki przeciwnika bijącej w przelocie.
     * @return Obiekt klasy EnPassantMove reprezentujący ruch bicia w przelocie.
     */
    private Move createEnPassantMove(int newX, int newY, int capturedPawnX, int capturedPawnY) {
        Pawn capturedPawn = (Pawn) board.getPiece(capturedPawnX, capturedPawnY);
        return new EnPassantMove(newX, newY, capturedPawn);
    }

    /**
     * Metoda obsługująca zakończenie partii w przypadku mata lub remisu.
     *
     * @param round Kolor aktualnej tury.
     */
    private void handleCheckmate(ChessColor round) {
        if (board.isCheckMate(round)) {
            String winner = (round == ChessColor.WHITE) ? "Czarny" : "Biały";
            ChessGame.endGame(winner);
        } else if (board.isDraw()) {
            ChessGame.endGame(null);
        }
    }
}
