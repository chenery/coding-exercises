package chess;

/**
 *
 */
public class Player {
    public enum Colour { WHITE, BLACK}

    private Colour colour;

    public Player(Colour colour) {
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }
}
