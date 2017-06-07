package njupt;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * Created by aoeiuv on 17-6-7.
 */
public class ExchangeApp {
    public static void main(String[] args) {
        invokeLater(() -> {
            JFrame frame = new JFrame("外汇转换");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setContentPane(new ExchangeUI(frame));
            frame.pack();
            frame.setVisible(true);
            frame.setSize(200, frame.getHeight());
        });
    }
}
