package com.edu.eci.ARSW.ticTacToeInteractivo.repository;

import com.edu.eci.ARSW.ticTacToeInteractivo.model.GameRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GameRoomRepository extends MongoRepository<GameRoom, String> {
    Optional<GameRoom> findByRoomCode(String roomCode);
}