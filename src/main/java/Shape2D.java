import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: che
 * Date: 02.02.13
 * Time: 21:49
 * To change this template use File | Settings | File Templates.
 */
public abstract class Shape2D extends Shape {

    private final boolean supportsZoom;
    private final boolean supportsMove;

    private double baseX = 0;
    private double baseY = 0;
    private Dimension baseSize = new Dimension();
    protected Point point = new Point();
    protected Dimension size = new Dimension();

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public Shape2D(IScreen screen, boolean supportsZoom, boolean supportsMove) {
        super(screen);
        this.supportsZoom = supportsZoom;
        this.supportsMove = supportsMove;
    }

    protected void setPoint(double x, double y) {
        baseX = x;
        baseY = y;
        if (!supportsMove) {
            this.point.setLocation(baseX, baseY);
        }
    }

    protected void setSize(Dimension size) {
        baseSize.setSize(size);
        if (!supportsZoom) {
            this.size.setSize(baseSize);
        }
    }

    @Override
    public void onScreenUpdated() {
        Point origin = screen.origin();
        if (supportsMove) {
            point.setLocation(baseX*screen.zoom(), baseY*screen.zoom());
            point.translate(origin.x, origin.y);
        }

        if (supportsZoom) {
            size.setSize(baseSize.width*screen.zoom(), baseSize.height*screen.zoom());
        }
    }

}
