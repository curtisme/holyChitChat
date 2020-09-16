import java.util.LinkedList;

class SessionMessageManager implements Runnable
{
    private LinkedList<Session> sessions;
    private LinkedList<Message> messages;
    private Thread t;

    public SessionMessageManager(LinkedList<Session> sessions,
            LinkedList<Message> messages)
    {
        this.sessions = sessions;
        this.messages = messages;
        t = new Thread(this);
        t.start();
    }

    public void run()
    {
        while(true)
        {
            synchronized(sessions)
            {
                /*Session tmp;
                int numSessions = sessions.size();
                for (int i=0;i<numSessions;i++)
                {
                    tmp = sessions.remove();
                    if (!tmp.isDead())
                        sessions.add(tmp);
                }*/
                synchronized(messages)
                {
                    Message toSend;
                    while(!messages.isEmpty())
                    {
                        toSend = messages.remove();
                        for (Session session : sessions)
                        {
                            session.sendMessage(toSend);
                        }
                    }
                }
            }
        }
    }
}
