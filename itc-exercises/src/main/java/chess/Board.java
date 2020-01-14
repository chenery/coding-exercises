package chess;

import chess.Player.Colour;
import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 *  8 rows and 8 columns
 *
 *                      BLACK
 *
 *          col 0, col 1, col 2, ... col 7
 *  row: 7
 *  row: 6
 *  ...
 *  row 1
 *
 *                      WHITE
 */
public class Board {

    private static final int NUM_ROWS = 8;
    private static final int NUM_COLS = 8;
    private static final int NUM_PAWNS = 8;

    // rows by columns
    List<List<Square>> rows = new ArrayList<List<Square>>();


    public Board() {

        // add the rows to the board
        for (int i = 0; i < NUM_ROWS; i++) {
            // add 8 rows
            List<Square> rowSquares = new ArrayList<>();
            rows.add(rowSquares);

            // add the squares to the rows
            for (int j = 0; j < NUM_COLS; j++) {
                // add 8 squares to the row
                rowSquares.add(new Square());
            }
        }

        setupColour(Colour.WHITE);
        setupColour(Colour.BLACK);
    }

    public void applyMove(Move move) {

    }

    private void setupColour(Colour colour) {

        int rowIndex = colour == Colour.WHITE ? 1 : 6;

        // 8 pawns
        List<Square> row = rows.get(rowIndex);
        for (int i = 0; i < NUM_PAWNS; i++) {
            row.get(i).withPiece(new Pawn());
        }

        // 2 rooks

        // TODO complete
        System.out.println("Completed setup of " + colour);
    }
}
