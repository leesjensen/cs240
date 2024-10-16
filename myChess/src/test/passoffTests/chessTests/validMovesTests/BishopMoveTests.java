package passoffTests.chessTests.validMovesTests;

import chess.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static chess.ChessGame.TeamColor.BLACK;
import static chess.ChessGame.TeamColor.WHITE;
import static chess.ChessPiece.PieceType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static passoffTests.TestFactory.*;

public class BishopMoveTests {
    private ChessBoard board;
    private ChessGame game;
    private Set<ChessMove> validMoves;

    @BeforeEach
    public void setup() {
        board = getNewBoard();
        game = getNewGame();
        validMoves = new HashSet<>();
    }

    @Test
    public void emptyBoard() {

        /*
        | | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | |B| | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
         */

        ChessPosition position = getNewPosition(5, 4);
        ChessPiece bishop = getNewPiece(WHITE, BISHOP);
        board.addPiece(position, bishop);

        //- -
        validMoves.add(getNewMove(position, getNewPosition(4, 3), null));
        validMoves.add(getNewMove(position, getNewPosition(3, 2), null));
        validMoves.add(getNewMove(position, getNewPosition(2, 1), null));

        // + -
        validMoves.add(getNewMove(position, getNewPosition(6, 3), null));
        validMoves.add(getNewMove(position, getNewPosition(7, 2), null));
        validMoves.add(getNewMove(position, getNewPosition(8, 1), null));

        // - +
        validMoves.add(getNewMove(position, getNewPosition(4, 5), null));
        validMoves.add(getNewMove(position, getNewPosition(3, 6), null));
        validMoves.add(getNewMove(position, getNewPosition(2, 7), null));
        validMoves.add(getNewMove(position, getNewPosition(1, 8), null));

        // + +
        validMoves.add(getNewMove(position, getNewPosition(6, 5), null));
        validMoves.add(getNewMove(position, getNewPosition(7, 6), null));
        validMoves.add(getNewMove(position, getNewPosition(8, 7), null));


        //check
        game.setBoard(board);
        var actualMoves = game.validMoves(position);
        assertEquals(validMoves, actualMoves);
    }

    @Test
    public void piecesInWay() {

        /*
        | | | | | | | | |
		| | | |Q| | | | |
		| | | | | | | | |
		|P|b|n| | | | | |
		|r| | | | | | | |
		| | | | | | |K| |
		| | | | |k| | | |
		| | | | | | | | |
         */

        ChessPosition position = getNewPosition(5, 2);
        ChessPiece bishop = getNewPiece(BLACK, BISHOP);
        board.addPiece(position, bishop);

        //pieces in way
        board.addPiece(getNewPosition(4, 1), getNewPiece(BLACK, ROOK));
        board.addPiece(getNewPosition(7, 4), getNewPiece(WHITE, QUEEN));
        board.addPiece(getNewPosition(2, 5), getNewPiece(BLACK, KING));

        //extra decoy pieces
        board.addPiece(getNewPosition(3, 7), getNewPiece(WHITE, KING));
        board.addPiece(getNewPosition(5, 1), getNewPiece(WHITE, PAWN));
        board.addPiece(getNewPosition(5, 3), getNewPiece(BLACK, KNIGHT));

        // +
        validMoves.add(getNewMove(position, getNewPosition(6, 1), null));

        // - +
        validMoves.add(getNewMove(position, getNewPosition(4, 3), null));
        validMoves.add(getNewMove(position, getNewPosition(3, 4), null));

        // + +
        validMoves.add(getNewMove(position, getNewPosition(6, 3), null));
        validMoves.add(getNewMove(position, getNewPosition(7, 4), null));


        //check
        game.setBoard(board);
        var actualMoves = game.validMoves(position);
        assertEquals(validMoves, actualMoves);
    }


    @Test
    public void pinCheck() {

        /*
        | | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| |r| | | |B| |K|
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
         */

        var bishop = getNewPiece(WHITE, BISHOP);
        var bishopPosition = getNewPosition(5, 6);
        board.addPiece(bishopPosition, bishop);

        //Enemy Rook causing pin (white bishop can't move out of line)
        board.addPiece(getNewPosition(5, 2), getNewPiece(BLACK, ROOK));
        board.addPiece(getNewPosition(5, 8), getNewPiece(WHITE, KING));

        //check
        game.setBoard(board);
        assertEquals(validMoves, game.validMoves(bishopPosition));
    }
}
