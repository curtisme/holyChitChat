import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Session implements Runnable
{
    private Socket connectionSocket;
    private Thread t;
    private LinkedList<Message> allMessages;
    private boolean isDead;

    public Session(Socket connectionSocket, LinkedList<Message> allMessages)
    {
        this.connectionSocket = connectionSocket;
        this.allMessages = allMessages;
        isDead = false;
        t = new Thread(this);
        t.start();
    }

    public void run()
    {
        String clientSentence;
        String modifiedSentence;
        try
        {
            BufferedReader inFromClient =
                new BufferedReader(new InputStreamReader(
                            connectionSocket.getInputStream()));
            while(!((clientSentence = inFromClient.readLine()).equals("close")))
            {
                modifiedSentence = clientSentence.toUpperCase() + '\n';
                allMessages.add(new Message(
                            Integer.toString(connectionSocket.getPort()),
                            modifiedSentence)
                        );
            }
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
                DataOutputStream outToClient =
                    new DataOutputStream(
                            connectionSocket.getOutputStream());
                outToClient.writeBytes(message.sender + " " + message.content);
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

