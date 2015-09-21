import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: che
 * Date: 03.02.13
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class PolyLine extends Shape {

    double xs[];
    double ys[];
    Point points[];

    public PolyLine(IScreen screen, Color color, double[] xs, double[] ys) {
        super(screen);
        assert xs != null && ys != null;
        assert xs.length == ys.length;

        setColor(color);
        this.xs = new double[xs.length];
        this.ys = new double[xs.length];
        points = new Point[xs.length];
        for(int i = 0; i < xs.length; i++) {
            this.xs[i] = xs[i];
            this.ys[i] = ys[i];
            points[i] = new Point();
        }
    }

    @Override
    public void onScreenUpdated() {
        Point origin = screen.origin();
        for(int i = 0; i < points.length; i++) {
            Point point = points[i];
            point.setLocation(xs[i]*screen.zoom(), ys[i]*screen.zoom());
            point.translate(origin.x, origin.y);
        }
    }

    @Override
    public void paint(Graphics2D graphics) {
        for(int i = 1; i < points.length; i++) {
            graphics.drawLine(points[i-1].x, points[i-1].y, points[i].x, points[i].y);
        }
    }
}
