import chess.Board;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import service.ListGamesResponse;
import ui.ChessClient;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTests {

    private ChessClient client;

    @BeforeEach
    public void setup() throws Exception {
        client = new ChessClient();
        client.clear();
    }

    @Test
    public void badCmdTest() {
        assertThrows(Exception.class, () -> client.eval("garbage"));
    }

    static final String loggedOutHelp = "  \u001B[38;5;12mregister <USERNAME> <PASSWORD> <EMAIL>\u001B[38;5;0m - \u001B[38;5;5mto create an account\u001B[38;5;0m\n" +
            "  \u001B[38;5;12mlogin <USERNAME> <PASSWORD>\u001B[38;5;0m - \u001B[38;5;5mto play chess\u001B[38;5;0m\n" +
            "  \u001B[38;5;12mquit\u001B[38;5;0m - \u001B[38;5;5mplaying chess\u001B[38;5;0m\n" +
            "  \u001B[38;5;12mhelp\u001B[38;5;0m - \u001B[38;5;5mwith possible commands\u001B[38;5;0m\n";

    static final String loggedInHelp = "  \u001B[38;5;12mcreate <NAME>\u001B[38;5;0m - \u001B[38;5;5ma game\u001B[38;5;0m\n" +
            "  \u001B[38;5;12mlist\u001B[38;5;0m - \u001B[38;5;5mgames\u001B[38;5;0m\n" +
            "  \u001B[38;5;12mjoin <ID> [WHITE|BLACK|<empty>]\u001B[38;5;0m - \u001B[38;5;5ma game\u001B[38;5;0m\n" +
            "  \u001B[38;5;12mobserve <ID>\u001B[38;5;0m - \u001B[38;5;5ma game\u001B[38;5;0m\n" +
            "  \u001B[38;5;12mlogout\u001B[38;5;0m - \u001B[38;5;5mwhen you are done\u001B[38;5;0m\n" +
            "  \u001B[38;5;12mquit\u001B[38;5;0m - \u001B[38;5;5mplaying chess\u001B[38;5;0m\n" +
            "  \u001B[38;5;12mhelp\u001B[38;5;0m - \u001B[38;5;5mwith possible commands\u001B[38;5;0m\n";

    @Test
    public void helpTest() throws Exception {
        assertEquals(loggedOutHelp, client.eval("HELP"));
        client.eval("register joe jackson joe@mail.com");
        assertEquals(loggedInHelp, client.eval("HELP"));
        client.eval("logout");
        assertEquals(loggedOutHelp, client.eval("HELP"));
    }

    @Test
    public void loginTest() throws Exception {
        assertEquals("Success", client.eval("register joe password c@mail.com"));
        // Must log out before logging in
        assertEquals("Failure", client.eval("login joe password"));
        assertEquals("Success", client.eval("logout"));
        assertEquals("Success", client.eval("login joe password"));
    }


    @Test
    public void joinTest() throws Exception {
        assertEquals("Success", client.eval("register player1 password c@mail.com"));
        assertEquals("Success", client.eval("create game1"));

        var gameList = listGames();
        if (gameList.games.length == 1) {
            var gameID = gameList.games[0].gameID;

            assertEquals("Success", client.eval(String.format("join %s white", gameID)));
            // Can't join if already joined
            assertEquals("Failure", client.eval(String.format("join %s white", gameID)));

            client.eval("logout");
            assertEquals("Success", client.eval("login player1 password"));
            assertEquals("Success", client.eval(String.format("observe %s", gameID)));
            // Can't join if already observing
            assertEquals("Failure", client.eval(String.format("join %s BLACK", gameID)));

            client.eval("logout");
            assertEquals("Success", client.eval("login player1 password"));
            assertEquals("Success", client.eval(String.format("join %s BLACK", gameID)));

            gameList = listGames();
            assertEquals("player1", gameList.games[0].blackUsername);
            assertEquals("player1", gameList.games[0].whiteUsername);
        }
    }


    @Test
    public void playTest() throws Exception {
        assertEquals("Success", client.eval("register player1 password p1@mail.com"));
        assertEquals("Success", client.eval("create game1"));

        var client2 = new ChessClient();
        assertEquals("Success", client2.eval("register player2 password p2@mail.com"));


        var gameList = listGames();
        if (gameList.games.length == 1) {
            var gameID = gameList.games[0].gameID;

            assertEquals("Success", client.eval(String.format("join %s white", gameID)));
            assertEquals("Success", client2.eval(String.format("join %s BLACK", gameID)));

            while (!client.isPlaying() || !client2.isPlaying()) {
                System.out.println("waiting for game to start");
                Thread.sleep(1000);
            }

            assertEquals("Success", client.eval(String.format("move e2e4", gameID)));
            assertEquals("Success", client2.eval(String.format("move e7e3", gameID)));
        }
    }


    @Test
    public void boardTest() {
        Board board = new Board();
        board.resetBoard();
        System.out.print(board);
    }

    private ListGamesResponse listGames() throws Exception {
        var gameListText = client.eval("list");
        return new Gson().fromJson(gameListText, ListGamesResponse.class);
    }
}

