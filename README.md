# QuickChat
QuickChat is a fast, secure Java messaging app by Godson JEAN (UNCC ID: 801213219). Built on a client-server model, it enables real-time chat with multiple users, simple login, and a responsive interface—ideal for quick, seamless communication.


About the Application

I am Godson JEAN, UNCC student, ID: 801213219, and I have created this messaging application named "QuickChat". The goal of QuickChat is to provide a simple, fast, and secure messaging platform that allows users to communicate seamlessly in real-time. Whether for casual chats or important discussions, QuickChat makes it easy for users to stay connected and engage in meaningful conversations.

The application is designed with an intuitive user interface, ensuring that users can send and receive messages with minimal delay. Additionally, it incorporates essential features like username-based logins and support for multiple users within a single session.

QuickChat is built using Java and follows core principles of real-time messaging, including client-server communication. It offers a reliable and efficient experience for anyone looking for a straightforward and responsive messaging tool.

Thank you for exploring QuickChat!

---

**How to Compile and Run the Java Chat Program (Client-Server Model)**

---

## ✅ REQUIREMENTS

### Java Development Kit (JDK)

* **Required Version**: JDK **24.0.1**
* Confirm installation by running:

  ```bash
  java -version
  javac -version
  ```

  Expected output:

  ```bash
  java version "24.0.1" ...
  javac 24.0.1
  ```

---

## 📁 PROJECT STRUCTURE

Place all the following files into the **same folder**, for example:

```plaintext
C:\Users\Admin\JavaChatApp\
```

### Files:

* Server.java
* Client.java

After compilation, these files will be generated:

* Server.class
* Client.class
* Server\$ClientHandler.class

---

## 🧩 LOGIN CREDENTIALS (Predefined in the code)

| Username | Password |
| -------- | -------- |
| user1    | pass1    |
| user2    | pass2    |
---

## ⚙️ INSTRUCTIONS TO RUN

### 🔧 Step 1: Open Command Prompt

* Press `Windows + R`
* Type `cmd`, hit Enter

### 📂 Step 2: Navigate to Project Folder

```bash
cd "C:\Users\Admin\JavaChatApp\"
```

### 📦 Step 3: Compile the Java Files

```bash
javac Server.java Client.java
```

If successful, `.class` files will be created.

### 🚀 Step 4: Start the Server

```bash
java Server
```

You should see:

```
Server started...
Waiting for clients to connect...
```

Leave this terminal window open.

### 💬 Step 5: Start a Client (New Terminal Window)

1. Open a **new** Command Prompt window
2. Navigate to project folder:

   ```bash
   cd "C:\Users\Admin\JavaChatApp\"
   ```
3. Run the client:

   ```bash
   java Client
   ```
4. Enter valid credentials when prompted:

   ```
   Username: user1
   Password: pass1
   ```

   Output:

   ```
   Welcome user1
   user1 has joined.
   ```

### ➕ Step 6: Add More Clients (Optional)

* Repeat Step 5 using different usernames (user2, user3) in new windows.
* Clients can now chat with each other.

---

## 🚧 TROUBLESHOOTING

| Problem               | Solution                                               |
| --------------------- | ------------------------------------------------------ |
| `java` not recognized | Ensure PATH includes JDK 24.0.1\bin                    |
| Class not found       | Make sure you compiled all files in the correct folder |
| Authentication failed | Use only the allowed username/password combinations    |

---

## ✅ SUMMARY

| Action      | Command/Note                          |
| ----------- | ------------------------------------- |
| Compile     | `javac Server.java Client.java`       |
| Run Server  | `java Server`                         |
| Run Client  | `java Client`                         |
| JDK Version | Use JDK 24.0.1                        |
| Valid Users | user1/pass1, user2/pass2, user3/pass3 |

---

**End of Instructions**
