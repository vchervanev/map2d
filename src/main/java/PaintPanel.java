import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Math.min;
import static java.lang.Math.round;

public class PaintPanel extends JPanel implements IScreen, ComponentListener,
        MouseListener, MouseWheelListener, MouseMotionListener {

    private BufferedImage image = null;
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Graphics2D graphics = null;
    private double zoom = 1;
    private Point origin = new Point(0, 0);

    private boolean isMouseDown = false;
    private Point lastMousePoint = new Point();
    public Graphics2D graphics() { return graphics; }
    public double zoom() { return zoom; }
    public Point origin() {return origin; }

    public void resizeImage(int width, int height) {
        if (width == 0 || height == 0)
            return;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
        onScreenUpdated();
        doPaint();
    }


    public PaintPanel() {
        shapes.add(new PolyLine(this, Color.gray, new double[]{ 5, 10, 30, 50, 80, 5 }, new double[]{ 1, 40, 80, 30, 10, 1}));
        shapes.add(new StandardShape(this, StandardShape.Type.Square,
                new Point(20, 30), new Dimension(20, 20), Color.red, Color.black, true, true));
        shapes.add(new StandardShape(this, StandardShape.Type.Ellipse,
                new Point(120, 30), new Dimension(20, 20), Color.blue, Color.blue, false, true));
        shapes.add(new StandardShape(this, StandardShape.Type.TriangleUp,
                new Point(20, 130), new Dimension(20, 20), Color.green, Color.red, true, true));
        shapes.add(new StandardShape(this, StandardShape.Type.TriangleDown,
                new Point(120, 130), new Dimension(20, 20), Color.yellow, Color.green, false, false));
        shapes.add(0, new Connector(this, Color.black, (Shape2D)shapes.get(3), (Shape2D)shapes.get(4)));
        resizeImage(getWidth(), getHeight());
        addComponentListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    public void onScreenUpdated() {
        for (Shape shape : shapes) {
            shape.onScreenUpdated();
        }
    }

    public void doPaint() {
        graphics().setColor(Color.white);
        graphics().setBackground(Color.white);
        graphics().fillRect(0, 0, image.getWidth(), image.getHeight());
        for (Shape shape : shapes) {
            shape.doPaint();
        }
        graphics().setBackground(Color.white);
        graphics().setColor(Color.white);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(),  this);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        resizeImage(this.getWidth(), this.getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isMouseDown = isMouseDown || (e.getButton() == MouseEvent.BUTTON1);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMouseDown = isMouseDown && (e.getButton() != MouseEvent.BUTTON1);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        lastMousePoint = e.getPoint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isMouseDown) {
            origin().translate(-lastMousePoint.x + e.getX(), -lastMousePoint.y + e.getY());
            fullRepaint();
            lastMousePoint = e.getPoint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        if (isMouseDown) {
//            origin().translate(-lastMousePoint.x + e.getX(), -lastMousePoint.y + e.getY());
//            fullRepaint();
//        }
        lastMousePoint = e.getPoint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoom *= 1 + Math.signum(e.getPreciseWheelRotation()) * 0.05;
        fullRepaint();
    }

    private void fullRepaint() {
        onScreenUpdated();
        doPaint();
        repaint();
    }
}

