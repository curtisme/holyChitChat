import java.io.*;
import java.net.*;
import java.util.LinkedList;

class Server
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket welcomeSocket = new ServerSocket(6789);

        LinkedList<Session> sessions = new LinkedList<Session>();
        LinkedList<Message> allMessages = new LinkedList<Message>();

        SessionMessageManager manager = new SessionMessageManager(sessions,
                allMessages); 
        while (true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println(connectionSocket);
            sessions.add(new Session(connectionSocket, allMessages));
        }
    }
}
