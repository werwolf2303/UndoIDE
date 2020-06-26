package com.ide.window;

import javax.swing.*;
import java.awt.event.*;

public class New extends JDialog {
    private JPanel contentPane;

    public void run() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        SwingUtilities.invokeLater(new FileManager.FileBrowser());
    }

    public static void main(String[] args) {
        new New().run();
    }
}
