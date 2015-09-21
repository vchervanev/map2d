import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: che
 * Date: 02.02.13
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame extends JFrame implements WindowStateListener {
    public static final PaintPanel canvas = new PaintPanel();
    private Dimension oldSize;

    public MainFrame() {
        setTitle("map2d");
        setSize(800, 600);
        oldSize = getSize();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowStateListener(this);
        add(canvas);
        canvas.resizeImage(oldSize.width, oldSize.height);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame ex = new MainFrame();
                ex.setVisible(true);

            }
        });
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        if (!getSize().equals(oldSize)) {
            oldSize.setSize(getSize());
            canvas.resizeImage(oldSize.width, oldSize.height);
        }
    }
}
