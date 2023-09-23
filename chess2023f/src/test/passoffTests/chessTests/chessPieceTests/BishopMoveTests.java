package passoffTests.chessTests.chessPieceTests;

import chess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import passoffTests.TestFactory;

import java.util.HashSet;

public class BishopMoveTests {

    @Test
    public void bishopMoveUntilEdge() {
        var board = TestFactory.loadBoard("""
                | | | | | | | | |
                | | | | | | | | |
                | | | | | | | | |
                | | | |B| | | | |
                | | | | | | | | |
                | | | | | | | | |
                | | | | | | | | |
                | | | | | | | | |
                """);

        var bishop = TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        var position = TestFactory.getNewPosition(5, 4);
        var validMoves = TestFactory.loadMoves(position, new int[][]{
                {6, 5}, {7, 6}, {8, 7},
                {4, 5}, {3, 6}, {2, 7}, {1, 8},
                {4, 3}, {3, 2}, {2, 1},
                {6, 3}, {7, 2}, {8, 1},
        });

        Assertions.assertEquals(validMoves, bishop.pieceMoves(board, position), "Wrong moves");
    }


    @Test
    public void bishopCaptureEnemy() {
        var board = TestFactory.loadBoard("""
                | | | | | | | | |
                | | | |Q| | | | |
                | | | | | | | | |
                | |b| | | | | | |
                |r| | | | | | | |
                | | | | | | | | |
                | | | | |P| | | |
                | | | | | | | | |
                """);

        var bishop = TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        var position = TestFactory.getNewPosition(5, 2);
        var validMoves = TestFactory.loadMoves(position, new int[][]{
                {6, 3}, {7, 4},
                {4, 3}, {3, 4}, {2, 5},
                // none
                {6, 1},
        });

        Assertions.assertEquals(validMoves, bishop.pieceMoves(board, position), "Wrong moves");
    }


    @Test
    public void bishopBlocked() {
        var board = TestFactory.loadBoard("""
                | | | | | | | | |
                | | | | | | | | |
                | | | | | | | | |
                | | | | | | | | |
                | | | | | | | | |
                | | | | | | | | |
                | | | | |R| |P| |
                | | | | | |B| | |
                """);

        var bishop = TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        var position = TestFactory.getNewPosition(1, 6);

        Assertions.assertEquals(new HashSet<>(), bishop.pieceMoves(board, position), "Wrong moves");
    }

}
