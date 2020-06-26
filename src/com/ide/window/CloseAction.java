package com.ide.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseAction extends JDialog {

    public void run() {
        CloseAction dialog = new CloseAction();
        JPanel p = new JPanel();
        dialog.setTitle("UndoIDE v1.0");
        JButton b = new JButton("Schließen");
        JButton b1 = new JButton("Abbrechen");
        JLabel l = new JLabel("    Wirklich schließen? Nicht gespeicherter Stuff geht verloren!");
        dialog.setVisible(true);
        dialog.setResizable(false);
        dialog.setMinimumSize(new Dimension(370, 120));
        dialog.add(p, BorderLayout.SOUTH);
        dialog.add(l, BorderLayout.CENTER);
        p.add(b);
        p.add(b1);
        p.setVisible(true);
        dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new CloseAction().run();
    }
}
