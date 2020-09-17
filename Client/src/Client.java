import java.io.*;
import java.net.*;

class Client
{
    public static void main(String args[]) throws Exception
    {
        if (args.length < 1)
        {
            System.out.println("Please provide server hostname");
            return;
        }
        String name;
        BufferedReader inFromUser = 
            new BufferedReader(
                    new InputStreamReader(System.in));
        System.out.print("Enter a name: ");
        name = inFromUser.readLine();
        Socket clientSocket = new Socket(args[0], 6789);
        ObjectOutputStream outToServer = 
            new ObjectOutputStream(clientSocket.getOutputStream());
        GUI g = new GUI(outToServer, clientSocket, name);
        ServerListener listener = new ServerListener(clientSocket, g);
    }
}
