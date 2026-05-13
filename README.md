# QuickChat

QuickChat is a multi-user real-time messaging application built in Java using socket programming, multithreading, and Maven.

## Author

Godson JEAN

## Features

- Real-time messaging
- Multi-client support
- Client-server architecture
- Username/password authentication
- Thread-safe server handling
- Object serialization messaging
- Maven build system

## Technologies

- Java
- Maven
- TCP Sockets
- Multithreading

## How To Run

```bash
# Start Server
mvn exec:java -Dexec.mainClass="server.Server"

# Start Client (in new terminals)
mvn exec:java -Dexec.mainClass="client.Client"
```

## Demo Users

Username    Password

user1   pass1

user2   pass2

user3   pass3

----

## Future Improvements

- Spring Boot migration
- PostgreSQL database
- JWT authentication
- WebSocket support
- File transfers
- Encryption
- React frontend

## Demo (__screenshots__)

- Server Started

![Server Started](screenshots\qc_server_started.png)

- Users Joint

![User Joint](screenshots\qc_sever_started_users_Joint.png)

- User1

![User1](screenshots\qc_user1_to_2.png)

- Users2

![User2](screenshots\qc_user2_to_1.png)
