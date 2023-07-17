package service;


import com.google.gson.Gson;

public class JoinRequest {
    public String playerColor;
    public int gameID;

    public JoinRequest(int gameID, String playerColor) {
        this.gameID = gameID;
        this.playerColor = playerColor;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}