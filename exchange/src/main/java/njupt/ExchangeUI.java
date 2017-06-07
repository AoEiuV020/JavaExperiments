package njupt;

import javax.swing.*;

/**
 * Created by aoeiuv on 17-6-7.
 */
class ExchangeUI extends JPanel {
    JTextField from = new JTextField();
    JButton rmbToUsd = new JButton("￥ -> $");
    JButton usdToRmb = new JButton("$ -> ￥");
    JLabel to = new JLabel(" ");
    private JFrame frame;

    ExchangeUI(JFrame frame) {
        this.frame = frame;
        Box box = Box.createVerticalBox();
        box.add(from);
        box.add(rmbToUsd);
        box.add(usdToRmb);
        box.add(to);
        add(box);
        rmbToUsd.addActionListener(e -> to.setText(String.format("%.2f", parse(from.getText()) / 6.88)));
        usdToRmb.addActionListener(e -> to.setText(String.format("%.2f", parse(from.getText()) * 6.88)));
    }

    double parse(String text) {
        try {
            return Double.parseDouble(text);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "数字格式不对",
                    "error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
}
