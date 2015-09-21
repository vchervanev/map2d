
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: che
 * Date: 02.02.13
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */
public class StandardShape extends Shape2D {

    public enum Type {Square,  Ellipse, TriangleUp, TriangleDown}
    protected final Type type;
    private int[] xs = null;
    private int[] ys = null;

    public StandardShape(IScreen screen, Type type, Point point, Dimension size, Color color, Color bgColor, boolean supportsZoom, boolean supportsMove) {
        super(screen, supportsZoom, supportsMove);
        this.type = type;
        this.setPoint(point.x, point.y);
        this.setSize(size);
        this.setColor(color, bgColor);
        if (type == Type.TriangleUp || type == Type.TriangleDown) {
            xs = new int[3];
            ys = new int[3];
        }
    }

    @Override
    public void paint(Graphics2D graphics) {
        if(xs != null) {

            int dx = size.width / 2;
            int dy = (type == Type.TriangleUp ? -1 : +1) * size.height / 2;
            xs[0] = point.x - dx;
            xs[1] = point.x + dx;
            xs[2] = point.x;
            ys[0] = point.y + dy;
            ys[1] = point.y + dy;
            ys[2] = point.y - dy;
        }
        switch (type) {
            case Square:
                graphics.fillRect(point.x, point.y, size.width, size.height);
                graphics.setColor(graphics.getBackground());
                graphics.drawRect(point.x, point.y, size.width, size.height);
                break;
            case Ellipse:
                graphics.fillArc(point.x, point.y, size.width, size.height, 0, 360);
                graphics.setColor(graphics.getBackground());
                graphics.drawArc(point.x, point.y, size.width, size.height, 0, 360);
                break;
            case TriangleUp:
            case TriangleDown:
                graphics.fillPolygon(xs, ys, 3);
                graphics.setColor(graphics.getBackground());
                graphics.drawPolygon(xs, ys, 3);
                break;
        }
    }
}
