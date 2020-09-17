import java.awt.*;
import java.io.*;
import java.net.*;

public class GUI extends Frame
{
    private TextField userInput;
    private TextArea chat;
    private Button quitButton;

    //Definitely move this into its own class one day
    ObjectOutputStream outToServer;
    Socket clientSocket;
    String userName;

    public GUI(ObjectOutputStream o, Socket s, String userName)
    {
        outToServer = o;
        clientSocket = s;
	this.userName = userName;

        setLayout(null);
        
        userInput = new TextField();
        userInput.setBounds(new Rectangle(5,265,360,30));
        userInput.addKeyListener(new EnterKeyListener(this));
        add(userInput);

        chat = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        chat.setBounds(new Rectangle(5,5,390,255));
        chat.setEditable(false);
        add(chat);
        
        quitButton = new Button("quit");
        quitButton.setBounds(new Rectangle(365, 265, 30, 30));
        quitButton.addActionListener(new QuitListener(this));
	quitButton.setBackground(Color.white);
        add(quitButton);

        setBackground(Color.blue);
        setSize(400, 300);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
    }

    public void displayMessage(Message m)
    {
        chat.append(String.format("%s: %s\n", m.sender, m.content));
    }

    public void sendMessage()
    {
        try
        {
            outToServer.writeObject(new Message(userName, userInput.getText()));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        userInput.setText("");
    }

    public void quit()
    {
        try
        {
            outToServer.writeObject(new Message());
            clientSocket.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.exit(0);
    }
}

