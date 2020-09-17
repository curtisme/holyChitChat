import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class EnterKeyListener implements KeyListener
{
    private GUI g;

    public EnterKeyListener(GUI g)
    {
        this.g = g;
    }
    
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            g.sendMessage();
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void keyTyped(KeyEvent e)
    {
    }
}
