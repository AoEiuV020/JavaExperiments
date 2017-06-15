package njupt;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * Created by AoEiuV020 on 2017.06.15-14:33:00.
 */
public class ClockApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new ClockFrame();
            frame.setVisible(true);
        });
    }
}

class ClockFrame extends JFrame {
    private ClockThread clockThread;

    ClockFrame() {
        super("时钟");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ClockUI clockUI = new ClockUI();
        getContentPane().add(clockUI);
        pack();
        // 这个线程负责刷新，
        clockThread = new ClockThread(clockUI);
        clockThread.start();
    }

    @Override
    public void dispose() {
        super.dispose();
        clockThread.cancel();
    }
}

class ClockUI extends JComponent {

    ClockUI() {
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // 几个参数确保窗口大小改变时，时钟等比例放大，
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int d = Math.min(width, height);
        int outR = d / 2;
        int inR = outR - d / 33;
        int tail = d / 100;
        // 画表盘，
        g.setColor(Color.GRAY);
        g.fillOval(centerX - outR, centerY - outR, 2 * outR, 2 * outR);
        g.setColor(Color.ORANGE);
        g.fillOval(centerX - inR, centerY - inR, 2 * inR, 2 * inR);
        g.setColor(Color.WHITE);
        // 画刻度，
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        for (int i = 0; i < 12; i++) {
            drawLineRotate(g, centerX, centerY, inR, outR, i / 12.0);
        }
        // 画指针，
        g.setColor(Color.BLACK);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);
        double doubleHour = hour + minute / 60.0;
        double doubleMinute = minute + second / 60.0;
        double doubleSecond = second + millisecond / 1000.0;
        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        drawLineRotate(g, centerX, centerY, -tail, (int) (0.4 * inR), doubleHour / 12.0);
        g2d.setStroke(new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        drawLineRotate(g, centerX, centerY, -tail * 2, (int) (0.6 * inR), doubleMinute / 60.0);
        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        drawLineRotate(g, centerX, centerY, -tail * 3, (int) (0.8 * inR), doubleSecond / 60.0);
    }

    /**
     * 画一条旋转后的线，
     *
     * @param angle 旋转角度，0 ~ 1,
     */
    private void drawLineRotate(Graphics g, int centerX, int centerY, int fromR, int toR, double angle) {
        double s = Math.sin(Math.PI * 2 * angle);
        double c = Math.cos(Math.PI * 2 * angle);
        g.drawLine((int) (centerX + fromR * s), (int) (centerY - fromR * c), (int) (centerX + toR * s), (int) (centerY - toR * c));
    }
}

class ClockThread extends Thread {
    private boolean running = true;
    private ClockUI clockUI;

    ClockThread(ClockUI clockUI) {
        this.clockUI = clockUI;
    }

    void cancel() {
        running = false;
        interrupt();
    }

    @Override
    public void run() {
        try {
            while (running) {
                Thread.sleep(100);
                clockUI.repaint();
            }
        } catch (InterruptedException ignore) {
        }
    }
}