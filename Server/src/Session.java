import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Session implements Runnable
{
    private Socket connectionSocket;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private Thread t;
    private LinkedList<Message> allMessages;
    private boolean isDead;

    public Session(Socket connectionSocket, LinkedList<Message> allMessages)
		    throws Exception
    {
        this.connectionSocket = connectionSocket;
        inFromClient =
            new ObjectInputStream(connectionSocket.getInputStream());
        outToClient =
            new ObjectOutputStream(connectionSocket.getOutputStream());
        this.allMessages = allMessages;
        isDead = false;
        t = new Thread(this);
        t.start();
    }

    public void run()
    {
        Message fromClient;
        try
        {
            /*BufferedReader inFromClient =
                new BufferedReader(new InputStreamReader(
                            connectionSocket.getInputStream()));*/
            while(!(fromClient = (Message)inFromClient.readObject())
                    .isClosingMessage())
                allMessages.add(fromClient);
            connectionSocket.close();
            isDead = true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            isDead = true;
        }
    }

    public void sendMessage(Message message)
    {
        if (!isDead)
        {
            try
            {
                /*DataOutputStream outToClient =
                    new DataOutputStream(
                            connectionSocket.getOutputStream());*/
                outToClient.writeObject(message);
            }
            catch (Exception e)
            {
                System.out.println(e);
                isDead = true;
            }
        }
    }

    public boolean isDead()
    {
        return isDead;
    }
}

