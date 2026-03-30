QuickChat

QuickChat is a fast, secure Java-based messaging application built on a client-server architecture. It enables real-time communication between multiple users with a simple login system and a responsive interface, demonstrating core principles of software development including multithreading, object-oriented design, and networking.

Features
Real-time messaging between multiple clients
Username-based login authentication
Responsive and user-friendly interface
Built using Java with a client-server architecture
Easily extensible for features like encryption, persistent chat history, and GUI enhancements
Requirements
Java Development Kit (JDK) – Version 24.0.1
Verify installation with:
java -version
javac -version

Expected output:

java version "24.0.1" ...
javac 24.0.1
Project Structure

Place all files in the same folder (example path: C:\Projects\QuickChat\).

Source Files:

Server.java
Client.java

Compiled Files:

Server.class
Client.class
Server$ClientHandler.class
Predefined Login Credentials
Username	Password
user1	pass1
user2	pass2
user3	pass3

Credentials are predefined in the code for testing purposes.

Running the Application
Step 1: Open Terminal / Command Prompt

Navigate to the project folder:

cd "C:\Projects\QuickChat\"
Step 2: Compile the Java Files
javac Server.java Client.java
Step 3: Start the Server
java Server

Expected output:

Server started...
Waiting for clients to connect...

Leave this terminal window open while running clients.

Step 4: Start a Client
Open a new terminal window
Navigate to the project folder
cd "C:\Projects\QuickChat\"
Run the client:
java Client
Enter valid credentials:
Username: user1
Password: pass1

Expected output:

Welcome user1
user1 has joined.
Step 5: Add Additional Clients (Optional)
Repeat Step 4 in new terminal windows using other usernames (user2, user3, etc.)
Multiple clients can now communicate in real-time.
Troubleshooting
Issue	Solution
java not recognized	Ensure JDK bin directory is included in system PATH
Class not found	Verify all files were compiled in the correct folder
Login fails	Use only the predefined username/password combinations
Summary of Commands
Action	Command / Note
Compile	javac Server.java Client.java
Run Server	java Server
Run Client	java Client
Required JDK	24.0.1
Test Users	user1/pass1, user2/pass2, user3/pass3
