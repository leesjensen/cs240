package dataAccess;

import model.AuthData;
import model.GameData;
import model.UserData;

import java.util.Collection;

/**
 * Represents all operations that may be performed on the database.
 */
public class MemoryDataAccess implements DataAccess {


    public MemoryDataAccess() throws DataAccessException {
    }

    /**
     * Clears out all the data in the database.
     *
     * @throws DataAccessException for database or sql query violations (e.g. no error for not found).
     */
    public void clear() throws DataAccessException {
    }

    /**
     * Persist a user
     *
     * @param user with both username and ID provided.
     * @throws DataAccessException for database or sql query violations.
     */
    public UserData writeUser(UserData user) throws DataAccessException {
        return null;
    }

    /**
     * Read a previously persisted user.
     *
     * @param user with either ID or username provided.
     * @return The requested @User
     * @throws DataAccessException for database or sql query violations (e.g. no error for not found).
     */
    public UserData readUser(UserData user) throws DataAccessException {
        return null;
    }

    /**
     * Persist the authorization token. If a token already exists in the database it is overwritten.
     *
     * @param user to persist.
     * @return The @AuthData for the user.
     * @throws DataAccessException for database or sql query violations.
     */
    public AuthData writeAuth(UserData user) throws DataAccessException {
        return null;
    }

    /**
     * Read a previously persisted authorization token.
     *
     * @param authToken for the @AuthData to retrieve.
     * @return The @AuthToken for the user or Null if it doesn't exist.
     * @throws DataAccessException for database or sql query violations (e.g. no error for not found).
     */
    public AuthData readAuth(String authToken) throws DataAccessException {
        return null;
    }

    /**
     * Clears out an authorization token. This call is idempotent.
     *
     * @param authData containing the authToken to delete.
     * @throws DataAccessException for database or sql query violations.
     */
    public void deleteAuth(AuthData authData) throws DataAccessException {
    }

    /**
     * Creates a new game. A new gameID is assigned to the returned object.
     *
     * @param game to create
     * @throws DataAccessException for database or sql query violations.
     */
    public GameData newGame(GameData game) throws DataAccessException {
        return null;
    }

    /**
     * Update an existing game.
     *
     * @param gameData to update
     * @return the @Game if it was updated, and null if there was no game with that ID.
     * @throws DataAccessException for database or sql query violations.
     */
    public GameData updateGame(GameData gameData) throws DataAccessException {
        return null;
    }

    /**
     * Read a previously persisted Game.
     *
     * @param gameID for the game to read
     * @return The requested Game or null if not found.
     * @throws DataAccessException for database or sql query violations (e.g. no error for not found).
     */
    public GameData readGame(int gameID) throws DataAccessException {
        return null;
    }

    /**
     * The complete list of games. Since we don't delete games this will be the full list unless the clear operation is called.
     *
     * @return the list of @Game objects
     * @throws DataAccessException for database or sql query violations.
     */
    public Collection<GameData> listGames() throws DataAccessException {
        return null;
    }
}
