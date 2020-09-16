import java.io.*;
import java.net.*;

class ServerListener implements Runnable
{
    private Socket clientSocket;
    private Thread t;

    public ServerListener(Socket socket)
    {
        clientSocket = socket;
        t = new Thread(this);
        t.start();
    }

    public void run()
    {
        try
        {
            BufferedReader inFromServer =
                new BufferedReader(
                        new InputStreamReader(
                            clientSocket.getInputStream()));
            while(!clientSocket.isClosed())
            {
                System.out.println("FROM SERVER: " +
                        inFromServer.readLine());
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
