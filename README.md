#  Tic Tac Toe Interactivo - Backend

Este es el backend de una aplicación web interactiva del juego **Tic Tac Toe**, que permite:
- Crear y unirse a salas de juego.
- Jugar en tiempo real entre dos jugadores usando **WebSockets (STOMP)**.
- Persistir el estado del juego y su historial en una base de datos **MongoDB**.

##  Tecnologías usadas

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring WebSocket
- Spring Messaging (STOMP)
- Spring Data MongoDB
- Maven
- MongoDB

---

##  Estructura del Proyecto

```
src/
├── main/
│ ├── java/com/edu/eci/ARSW/ticTacToeInteractivo/
│ │ ├── controller/ # Controladores REST y WebSocket
│ │ ├── model/ # Entidades del dominio (GameRoom, MoveMessage)
│ │ ├── repository/ # Interfaces de acceso a datos (MongoDB)
│ │ └── TicTacToeApp.java # Clase principal
│ └── resources/
│ └── application.properties
```

## Endpoints REST

### Crear una nueva sala

```
POST /room
```
Response: GameRoom con código único.

### Obtener información de una sala

```
GET /room/{roomCode}
```

### Unirse a una sala

```
POST /room/{roomCode}/join?playerId={uuid}
```

- Asigna al jugador como X u O según disponibilidad.
- Devuelve un mensaje indicando el rol asignado.

```
GET /room/{roomCode}
```

## Comunicación WebSocket (STOMP)

### Endpoint WebSocket
```bash
ws://localhost:8080/ws
```


### Cliente envía movimientos:

```bash
/app/game/{roomCode}/move
```

### Suscripción para recibir actualizaciones de la sala:

```bash
/topic/game/{roomCode}
```

