package chess;

/**
 *
 */
public class Square {

    private Piece piece;

    public Square() {
    }

    public Square withPiece(Piece piece) {
        this.piece = piece;
        return this;
    }

    public Piece getPiece() {
        return piece;
    }
}
