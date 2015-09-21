import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: che
 * Date: 02.02.13
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public interface IScreen {
    public Graphics2D graphics();
    public double zoom();
    public Point origin();
}
