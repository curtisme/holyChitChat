import java.io.*;
import java.net.*;

class Client
{
    public static void main(String args[]) throws Exception
    {
        String sentence;
        BufferedReader inFromUser = 
            new BufferedReader(
                    new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = 
            new DataOutputStream(
                    clientSocket.getOutputStream());
        ServerListener listener = new ServerListener(clientSocket);
        while(!((sentence = inFromUser.readLine()).equals("q")))
            outToServer.writeBytes(sentence + '\n');
        outToServer.writeBytes("close\n");
        clientSocket.close();
    }
}
