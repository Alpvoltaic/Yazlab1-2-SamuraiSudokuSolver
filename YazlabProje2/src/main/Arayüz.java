package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;

import javax.swing.JFrame;

public class Arayüz extends JFrame {

    JButton btn;

    public Arayüz() {
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        JButton btn = new JButton();
        btn.setText("5Lİ THREAD İLE ÇÖZ");
        btn.setBounds(765, 225, 200, 60);
        btn.setVisible(true);
        btn.setFocusable(false);
        btn.addActionListener((ActionEvent e) -> {
            Main.çöz();
            btn.setVisible(false);
        });
        this.add(btn);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
//        g.setColor(Color.WHITE);
//        g.fillRect(0, 0, 1000, 1000);

        for (int i = 0; i < 21; i++) {

            for (int j = 0; j < 21; j++) {
                if (Main.harita[i][j] != 0) {
                    if (Main.harita[i][j] == -1) {
                        g.setColor(Color.CYAN);
                        g.fill3DRect(100 + j * 30, 120 + i * 30, 30, 30, true);

                        g.setColor(Color.WHITE);

                    } else {

                        g.setColor(Color.CYAN);
                        g.fill3DRect(100 + j * 30, 120 + i * 30, 30, 30, true);
                        g.setColor(Color.BLACK);
                        g.setFont(new Font(TOOL_TIP_TEXT_KEY, Font.PLAIN, 20));
                        g.drawString(String.valueOf(Main.harita[i][j]), 110 + j * 30, 140 + i * 30);
                        g.setColor(Color.WHITE);

                    }
                }

            }

        }

    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

}
