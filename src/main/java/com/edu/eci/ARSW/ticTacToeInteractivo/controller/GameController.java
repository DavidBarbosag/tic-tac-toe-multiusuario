package com.edu.eci.ARSW.ticTacToeInteractivo.controller;

import com.edu.eci.ARSW.ticTacToeInteractivo.model.GameRoom;
import com.edu.eci.ARSW.ticTacToeInteractivo.model.MoveMessage;
import com.edu.eci.ARSW.ticTacToeInteractivo.repository.GameRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameRoomRepository gameRoomRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/game/{roomCode}/move")
    public void handleMove(@DestinationVariable String roomCode, @Payload MoveMessage move) {
        GameRoom room = gameRoomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Room not found: " + roomCode));

        List<String[]> history = room.getHistory();
        int currentMove = room.getCurrentMove();
        String[] currentBoard = history.get(currentMove);

        boolean isXTurn = (currentMove % 2 == 0);
        String expectedPlayerId = isXTurn ? room.getPlayerX() : room.getPlayerO();

        if (!move.getPlayer().equals(expectedPlayerId)) {
            System.out.println("Invalid player tried to move");
            return;
        }

        if (currentBoard[move.getIndex()] != null) return;

        String symbol = isXTurn ? "X" : "O";
        String[] newBoard = currentBoard.clone();
        newBoard[move.getIndex()] = symbol;

        history = history.subList(0, currentMove + 1);
        history.add(newBoard);

        room.setHistory(history);
        room.setCurrentMove(history.size() - 1);

        gameRoomRepository.save(room);
        messagingTemplate.convertAndSend("/topic/game/" + roomCode, room);
    }
}
