import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: che
 * Date: 02.02.13
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public abstract class Shape {

    protected final IScreen screen;
    private Color color = Color.BLACK;
    private Color bgColor = Color.WHITE;

    public Shape(IScreen screen) {
        this.screen = screen;
    }

    public void setColor(Color color, Color bgColor) {
        setColor(color);
        setBgColor(bgColor);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public final void doPaint() {
        Graphics2D graphics = screen.graphics();
        graphics.setColor(color);
        graphics.setBackground(bgColor);
        paint(graphics);
    }

    public abstract void onScreenUpdated();

    public abstract void paint(Graphics2D graphics);

    //public abstract boolean hitTest();
}
