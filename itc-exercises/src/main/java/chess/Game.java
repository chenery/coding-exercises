package chess;

import chess.Player.Colour;
import chess.api.MoveResponse;
import chess.api.MoveResponse.Status;

/**
 * TODO create a game state class?
 *
 * TODO create a command line interface
 *
 * TODO print the board after each move
 */
public class Game {

    private Board board;
    private Player white;
    private Player black;
    private GameHistory history;

    public Game() {
        // setup all the pieces on the board
        board = new Board();

        // setup the players
        white = new Player(Colour.WHITE);
        black = new Player(Colour.BLACK);

        history = new GameHistory();
    }

    public MoveResponse move(Move move) {
        if (!RuleEngine.moveIsAllowed(move, board, history)) {
            return new MoveResponse(Status.INVALID);
        }

        // update board
        board.applyMove(move);

        // record move in history
        history.recordMove(move);

        // declare winner
        if (RuleEngine.isWinningMove(move, board)) {
            return new MoveResponse(Status.CHECKMATE);
        }

        return new MoveResponse(Status.OK);
    }





    public static void main(String[] args) {
        Game game = new Game();
    }
}
