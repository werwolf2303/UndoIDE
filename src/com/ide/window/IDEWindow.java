package com.ide.window;

import com.ide.window.stringcache.Cache;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class IDEWindow implements ActionListener {

    JPanel panel1;
    private DefaultMutableTreeNode root;

    private DefaultTreeModel treeModel;

    private Cache c;

    private JTree tree;
    JScrollPane usercode;
    JScrollPane tree1;
    JTextArea textField1;
    JFrame f1 = new JFrame("UndoIDE v1.0");

    private Timer timer = new Timer(1000, this);
    private double d = 0;

    public void TimerTest()
    {
        timer.start();
        try {

            File myObj = new File("C:/UndoIDE/cache/cache.dat");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data == "true") {
                    File myObj2 = new File("C:/UndoIDE/cache/cache.dat");
                    Scanner myReader2 = new Scanner(myObj2);
                    String data2 = myReader2.nextLine();
                    File myObj3 = new File("C:/UndoIDE/" + data2);
                    Scanner myReader3 = new Scanner(myObj3);
                    textField1.setText(myReader3.nextLine());
                    myReader3.close();
                    myReader2.close();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        d++;
    }

    public void setText(String text) {
        textField1.setText(text);
    }

    public void EXIT() {
        f1.dispose();
        f1.setVisible(false);
    }

    public static void main(String[] args) {
        new IDEWindow().run();
        new IDEWindow().TimerTest();
    }

    public JTextArea getTextField1() {
        return textField1;
    }

    public void run() {
        File fileRoot = new File("C:/UndoIDE/Projekt1");
        root = new DefaultMutableTreeNode(new FileManager.FileBrowser.FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        tree.setShowsRootHandles(true);
        FileManager.FileBrowser.CreateChildNodes ccn =
                new FileManager.FileBrowser.CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();
        JScrollPane scrollPane = new JScrollPane(tree);
        JPopupMenu popup = new JPopupMenu();
        UndoManager undoManager = new UndoManager();
        textField1.getDocument().addUndoableEditListener(undoManager);

        Action undoAction = new AbstractAction("Undo") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
                else {
                    System.out.println("No Undo Buffer.");
                }
            }
        };

        Action copyAction = new AbstractAction("Copy") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textField1.copy();
            }
        };

        Action cutAction = new AbstractAction("Cut") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textField1.cut();
            }
        };

        Action pasteAction = new AbstractAction("Paste") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textField1.paste();
            }
        };

        Action selectAllAction = new AbstractAction("Select All") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textField1.selectAll();
            }
        };

        cutAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
        copyAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
        pasteAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
        selectAllAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control A"));

        popup.add (undoAction);
        popup.addSeparator();
        popup.add (cutAction);
        popup.add (copyAction);
        popup.add (pasteAction);
        popup.addSeparator();
        popup.add (selectAllAction);

        textField1.setComponentPopupMenu(popup);
        JScrollPane pane = new JScrollPane(textField1);
        f1.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        f1.add(pane, BorderLayout.CENTER);
        f1.add(tree, BorderLayout.WEST);
        f1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        tree.setBackground(Color.darkGray);
        tree.setForeground(Color.white);
        f1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new CloseAction().run();
            }
        });
        JMenuBar bar = new JMenuBar();
        JMenu i1 = new JMenu("Datei");
        JMenu l1 = new JMenu("Hilfe");
        JMenu tools = new JMenu("Tools");
        JMenuItem minecraft = new JMenuItem("MCPE Map Viewer");
        JMenuItem e1 = new JMenuItem("Einstellungen");
        JMenuItem e2 = new JMenuItem("Info");
        JMenuItem i2 = new JMenuItem("Neu");
        JMenuItem i3 = new JMenuItem("Öffnen");
        JButton l4 = new JButton("Run");
        f1.add(l4, BorderLayout.SOUTH);
        l4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ea) {
                try {
                    Random r = new Random();
                    String file = "C:/UndoIDE/" + "index" + r.nextInt() + ".html";
                    Runtime rn = Runtime.getRuntime();
                    Process pr = rn.exec("cmd /c start " + file);
                    FileWriter myWriter = new FileWriter(file);
                    myWriter.write(textField1.getText());
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        });
        bar.setVisible(true);
        bar.setEnabled(true);
        bar.add(i1);
        e2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Info().run();
            }
        });

        bar.add(l1);
        tools.add(minecraft);
        l1.add(e1);
        l1.add(e2);
        i1.add(i2);
        i1.add(i3);
        e1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File myObj = new File("C:/UndoIDE/settings.uic");
                    Scanner myReader = new Scanner(myObj);
                    String line = myReader.nextLine();
                    if(line == "enabled") {
                        new Settings().ENABLE();
                    }
                    if(line == "disabled") {
                        new Settings().DISABLE();
                    }
                    myReader.close();
                } catch (FileNotFoundException ea) {
                    System.out.println("An error occurred.");
                    ea.printStackTrace();
                }
                new Settings().run();
            }
        });
        i3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileManager.FileBrowser().run();
            }
        });
        i3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Open().run();
            }
        });
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                if (ae.getClickCount() == 2) {
                    textField1.setText("");
                    String pathout = tree.getSelectionPath().toString();
                    String wandler = pathout.replace(",", "/");
                    String wandler2 = wandler.replace("[", "");
                    String wandler3 = wandler2.replace("]", "/");
                    String wandler4 = wandler3.replace(" ", "");
                    String wandler5 = wandler4.replace("\\", "/");
                    String t = "\n";
                    Scanner s = null;
                }
            }
        });
        f1.setVisible(true);
        f1.setSize(new Dimension(700, 500));
        f1.setResizable(true);
        f1.setJMenuBar(bar);

        //###########################

        JPopupMenu popup2 = new JPopupMenu();
        tree.setComponentPopupMenu(popup2);
        //#############################################################
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3){

                    popup2.show(e.getComponent(), e.getX(), e.getY());//set the position and show the popup menu
                }
            }
        });
        JButton reload = new JButton("Reload");
        JButton news = new JButton("Neu");
        JButton copy = new JButton("Copy");
        JButton cut = new JButton("Cut");
        JButton paste = new JButton("Paste");
        popup2.add(news);
        popup2.add(reload);
        //popup2.add(copy);
        //popup2.add(cut);
        //popup2.add(paste);
        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                treeModel.reload();
                popup2.setVisible(false);
            }
        });
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        news.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog newfile = new JDialog();
                newfile.setVisible(true);
                JTextField field = new JTextField();
                JButton button = new JButton("Erstellen");
                newfile.setTitle("Neue Datei");
                newfile.setMinimumSize(new Dimension(250, 120));
                newfile.add(field, BorderLayout.CENTER);
                newfile.add(button, BorderLayout.SOUTH);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newfile.dispose();
                        if(field.getText() == "") {
                            System.err.print("FATAL: NO FILE NAME");
                        }else{
                            File news = new File("C:/UndoIDE/Projekt1/" + field.getText());
                            try {
                                news.createNewFile();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    public static void get(JFrame frame) {
    }
}