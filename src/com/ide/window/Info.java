package com.ide.window;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Info extends JDialog {
    private JPanel contentPane;

    public void run() {
        Info dialog = new Info();
        dialog.pack();
        dialog.setVisible(true);
        JLabel name = new JLabel("UndoIDE v1.0");
        JTextArea beta = new JTextArea("Warnung programmierter Stuff geht in" + "\n" + "dieser Alpha verloren!!");
        beta.setEditable(false);
        beta.setBackground(Color.LIGHT_GRAY);
        dialog.setTitle("Info");
        JLabel author = new JLabel("Author : Gianluca.B");
        dialog.add(name, BorderLayout.NORTH);
        dialog.add(beta, BorderLayout.CENTER);
        dialog.add(author, BorderLayout.SOUTH);
        dialog.setResizable(false);
        dialog.setMinimumSize(new Dimension(320, 220));

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
       new Info().run();
    }
}
