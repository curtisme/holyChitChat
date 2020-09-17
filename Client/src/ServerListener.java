import java.io.*;
import java.net.*;

class ServerListener implements Runnable
{
    private Socket clientSocket;
    private Thread t;
    private GUI g;

    public ServerListener(Socket socket, GUI g)
    {
        clientSocket = socket;
        this.g = g;
        t = new Thread(this);
        t.start();
    }

    public void run()
    {
        try
        {
            Message fromServer;
            ObjectInputStream inFromServer =
                new ObjectInputStream(clientSocket.getInputStream());
            while(true)
            {
                fromServer = (Message)inFromServer.readObject();
                g.displayMessage(fromServer);
            }
        }

        catch (SocketException se)
        {
            if (clientSocket.isClosed())
            {
                System.out.println("Disconnected");
            }
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
