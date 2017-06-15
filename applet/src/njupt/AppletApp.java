package njupt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by AoEiuV020 on 2017.06.15-13:20:53.
 */
public class AppletApp extends JApplet {
    private Graphics graphics;
    private AutoMoveThread thread;
    private int mouseX, mouseY;

    @Override
    public void init() {
        graphics = getGraphics();
        MyListener listener = new MyListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    @Override
    public void start() {
        thread = new AutoMoveThread();
        thread.start();
    }

    @Override
    public void stop() {
        thread.cancel();
    }

    private void drawCircle(int r, int x, int y, Color color) {
        graphics.setColor(color);
        graphics.fillOval(x - r, y - r, 2 * r, 2 * r);
    }

    private void savePosition(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
    }

    class AutoMoveThread extends Thread {
        private final int r = 5;
        private final int step = 5;
        private int x;
        private int y;
        private boolean running = true;

        AutoMoveThread() {
            x = getWidth() / 2;
            y = getHeight() / 2;
        }

        @Override
        public void run() {
            try {
                while (running) {
                    drawCircle(r, x, y, Color.GREEN);
                    move();
                    Thread.sleep(100);
                }
            } catch (InterruptedException ignore) {
            }
        }

        private void move() {
            x += mouseX - x > 0 ? step : -step;
            y += mouseY - y > 0 ? step : -step;
        }

        void cancel() {
            running = false;
            interrupt();
        }
    }

    class MyListener extends MouseAdapter {
        private final int r = 15;

        @Override
        public void mouseDragged(MouseEvent e) {
            drawCircle(r, e.getX(), e.getY(), Color.YELLOW);
            savePosition(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            drawCircle(r, e.getX(), e.getY(), Color.RED);
            savePosition(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            drawCircle(r, e.getX(), e.getY(), Color.BLUE);
            savePosition(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            savePosition(e);
        }
    }
}
