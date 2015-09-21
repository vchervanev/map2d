import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: che
 * Date: 03.02.13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
public class Connector extends Shape {
    protected Shape2D shapeA;
    protected Shape2D shapeB;

    public Connector(IScreen screen, Color color, Shape2D a, Shape2D b) {
        super(screen);
        setColor(color);
        shapeA = a;
        shapeB = b;
    }

    @Override
    public void onScreenUpdated() {
        // nothing
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.drawLine(shapeA.getX(), shapeA.getY(), shapeB.getX(), shapeB.getY());
    }
}
