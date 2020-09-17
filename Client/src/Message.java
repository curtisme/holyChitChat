class Message implements java.io.Serializable
{
    public String sender;
    public String content;
    private boolean isClosing;

    public Message(String sender, String content)
    {
        this.sender = sender;
        this.content= content;
        isClosing = false;
    }

    public Message()
    {
        sender = content = null;
        isClosing = true;
    }

    public boolean isClosingMessage()
    {
        return isClosing;
    }

    public String toString()
    {
        return String.format("%s: %s", sender, content);
    }
}
