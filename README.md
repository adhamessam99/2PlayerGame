# Player Game

## Project Overview

The Player Game project demonstrates a simple communication protocol between two `Player` instances. It includes the following functionalities:

1. **Creating Two Players**: One player acts as the initiator, sending messages to the other player.
2. **Message Exchange**: Each player sends a response message that includes the received message concatenated with a counter.
3. **Graceful Termination**: The program ends after the initiator has sent and received 10 messages.
4. **Same Process Communication**: Both players can run in the same Java process.
5. **Separate Process Communication**: Players can also run in separate Java processes.

## Project Structure


- **`pom.xml`**: Maven configuration file.
- **`src/main/java/org/example/Main.java`**: Contains the entry point for running both `Player` instances in the same process.
- **`src/main/java/org/example/Player.java`**: Defines the `Player` class with communication logic.
- **`src/main/java/org/example/PlayerClient.java`**: Defines the client-side code for communicating with the `PlayerServer`.
- **`src/main/java/org/example/PlayerServer.java`**: Defines the server-side code for receiving messages from the `PlayerClient`.

## Running the Project

### Running Both Players in the Same Process

To run both `Player` instances in the same Java process:

1. **Ensure the project is compiled**:
   ```bash
   mvn clean compile
2. **Run the Main class**
   ```bash
   ./start.sh
3. **Run the Player Server class**
   ```bash
   java -cp target/classes org.example.PlayerServer
4. **Run the Player Client class**
   ```bash
   java -cp target/classes org.example.PlayerClient

## Documentation
**Player Class
Responsibility: Handles message sending and receiving. Manages message counters and synchronizes communication between players.**

**PlayerClient Class
Responsibility: Connects to a PlayerServer, sends messages, and receives responses.**

**PlayerServer Class
Responsibility: Listens for incoming connections from PlayerClient, processes received messages, and sends responses.**

**Main Class
Responsibility: Initializes two Player instances and manages communication between them within the same process.**

## Notes
**Ensure that the PlayerServer is started before the PlayerClient when running in separate processes.**
