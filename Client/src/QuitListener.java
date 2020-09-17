import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class QuitListener implements ActionListener
{
    private GUI g;

    public QuitListener(GUI g)
    {
        this.g = g;
    }

    public void actionPerformed(ActionEvent e)
    {
        g.quit();
    }
}
