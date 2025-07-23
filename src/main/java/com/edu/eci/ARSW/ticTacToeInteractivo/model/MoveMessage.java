package com.edu.eci.ARSW.ticTacToeInteractivo.model;

public class MoveMessage {
    private String player; // "X" o "O"
    private int index;     // posición 0-8

    // Getters y Setters
    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
