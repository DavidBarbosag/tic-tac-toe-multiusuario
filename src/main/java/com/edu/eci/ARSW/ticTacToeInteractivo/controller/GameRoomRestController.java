package com.edu.eci.ARSW.ticTacToeInteractivo.controller;

import com.edu.eci.ARSW.ticTacToeInteractivo.model.GameRoom;
import com.edu.eci.ARSW.ticTacToeInteractivo.repository.GameRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "*")
public class GameRoomRestController {

    @Autowired
    private GameRoomRepository repository;

    @PostMapping
    public GameRoom createRoom() {
        String code = generateCode();
        GameRoom room = new GameRoom(code);
        return repository.save(room);
    }

    @GetMapping("/{roomCode}")
    public GameRoom getRoom(@PathVariable String roomCode) {
        Optional<GameRoom> room = repository.findByRoomCode(roomCode);
        return room.orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @PostMapping("/{roomCode}/join")
    public ResponseEntity<String> joinRoom(@PathVariable String roomCode, @RequestParam String playerId) {
        GameRoom room = repository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (room.getPlayerX() == null) {
            room.setPlayerX(playerId);
        } else if (room.getPlayerO() == null && !playerId.equals(room.getPlayerX())) {
            room.setPlayerO(playerId);
        } else if (!playerId.equals(room.getPlayerX()) && !playerId.equals(room.getPlayerO())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Room full or player ID conflict");
        }

        repository.save(room);
        return ResponseEntity.ok("Joined as " + (playerId.equals(room.getPlayerX()) ? "X" : "O"));
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
