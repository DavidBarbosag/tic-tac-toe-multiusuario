package com.edu.eci.ARSW.ticTacToeInteractivo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "games")
public class GameRoom {

    @Id
    private String id;

    private String playerX;
    private String playerO;

    private String roomCode;

    private List<String[]> history = new ArrayList<>();

    private int currentMove = 0;


    public GameRoom() {
        // Estado inicial: tablero vacío
        history.add(new String[9]);
        currentMove = 0;
    }

    public GameRoom(String roomCode) {
        this.roomCode = roomCode;
        history.add(new String[9]);
        currentMove = 0;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public List<String[]> getHistory() {
        return history;
    }

    public void setHistory(List<String[]> history) {
        this.history = history;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(int currentMove) {
        this.currentMove = currentMove;
    }

    public String getPlayerX() {
        return playerX;
    }

    public void setPlayerX(String playerX) {
        this.playerX = playerX;
    }

    public String getPlayerO() {
        return playerO;
    }

    public void setPlayerO(String playerO) {
        this.playerO = playerO;
    }

    // --- Métodos utilitarios ---

    /**
     * Retorna el tablero actual (último estado en la historia)
     */
    public String[] getCurrentBoard() {
        return history.get(currentMove);
    }

    /**
     * Añade un nuevo estado a la historia y avanza el índice
     */
    public void addMove(String[] newBoard) {
        // Elimina futuros si se retrocedió antes
        if (currentMove < history.size() - 1) {
            history = new ArrayList<>(history.subList(0, currentMove + 1));
        }
        history.add(newBoard);
        currentMove = history.size() - 1;
    }
}
