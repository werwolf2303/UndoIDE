package com.ide.window;

import com.ide.window.filemanager.DirAPI;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Settings extends JDialog {
    private JPanel contentPane;
    private JCheckBox anzeigenCheckBox;
    private JCheckBox versteckenCheckBox;
    private JCheckBox lightCheckBox;
    private JCheckBox darkCheckBox;
    private JTextField undoIDEV10TextField;
    private JPanel closeoptionslabel;
    private JPanel closeoptions;
    private JPanel designlabel;
    private JPanel designoptions;
    private JLabel titel;
    private JLabel design;
    private JLabel close;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox on = new JCheckBox("Enable");
    private JCheckBox off = new JCheckBox("Disable");

    public void run() {
        JLabel one = new JLabel("Tab1");
        JPanel set1 = new JPanel();
        Settings dialog = new Settings();
        dialog.setMinimumSize(new Dimension(600, 400));
        dialog.setTitle(undoIDEV10TextField.getText() + " - Einstellungen");
        dialog.pack();
        dialog.setVisible(true);
        JTextArea area = new JTextArea("Not Implemented Yet");
        dialog.add(area, BorderLayout.CENTER);
        dialog.add(set1, BorderLayout.NORTH);
        JLabel http = new JLabel("HTTP Server");
        set1.add(http);
        set1.add(on);
        set1.add(off);
        on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File f = new File("C:/UndoIDE/httpserver");
                    if (f.exists()) {

                    } else {
                        File f1 = new File("C:/UndoIDE/httpserver");
                        f1.mkdir();
                        File f2 = new File("C:/UndoIDE/httpserver/index.html");
                        f2.createNewFile();
                        FileWriter wr = new FileWriter(new File("C:/UndoIDE/httpserver/index.html"));
                        wr.write("<a>Du kannst diese Index.html in 'C:/UndoIDE/httpserver' §auml;ndern</a>");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                if(on.isSelected() == true) {
                    off.setSelected(false);
                }
                try {
                    HTTPServer.main(new String[] {});
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                FileWriter myWriter = null;
                try {
                    myWriter = new FileWriter("C:/UndoIDE/settings.uic");
                    myWriter.write("enabled");
                    myWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (on.isSelected() == true) {
                    on.setSelected(false);
                }
                HTTPServer.exit();
                FileWriter myWriter2 = null;
                try {
                    myWriter2 = new FileWriter(new File("C:/UndoIDE/settings.uic"));
                    myWriter2.write("disabled");
                    myWriter2.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        area.setEditable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setTitle(undoIDEV10TextField.getText());
            }
        });
    }

    public void ENABLE() {
        on.setSelected(true);
    }

    public void DISABLE() {
        off.setSelected(true);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
       new Settings().run();
    }
}
