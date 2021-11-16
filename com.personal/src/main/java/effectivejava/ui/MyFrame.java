package effectivejava.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-11-05 14:46:03
 * @author: wanglong16@meicai.cn
 */
public class MyFrame extends JFrame {
    private static final long serialVersionUID = 2L;

    public MyFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(300,300);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        String msg = "I rule!!";
        graphics.drawString(msg, 100, 100);
    }

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame("LONG");
    }
}
