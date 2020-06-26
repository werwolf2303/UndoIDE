package com.ide.window;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Open extends JDialog {


    private JPanel contentPane;
    private DefaultMutableTreeNode root;
    private JTree tree;
    private DefaultTreeModel treeModel;


    public static void main(String[] args) {
        new Open().run();
    }

    public void run() {
        File fileRoot = new File("C:/");
        root = new DefaultMutableTreeNode(new FileManager.FileBrowser.FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        tree.setShowsRootHandles(true);
        FileManager.FileBrowser.CreateChildNodes ccn =
                new FileManager.FileBrowser.CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();
        Open dialog = new Open();
        JButton open = new JButton("Öffnen");
        JPanel nav = new JPanel();
        JTextField field = new JTextField(35);
        nav.add(field);
        nav.add(open);
        dialog.setTitle("Projekt öffnen/Datei öffnen");
        dialog.add(nav, BorderLayout.SOUTH);
        dialog.setMinimumSize(new Dimension(640, 480));
        dialog.pack();
        dialog.setVisible(true);
        dialog.add(tree, BorderLayout.CENTER);
        setContentPane(contentPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pathout = tree.getSelectionPath().toString();
                String wandler = pathout.replace(",", "/");
                String wandler2 = wandler.replace("[", "");
                String wandler3 = wandler2.replace("]", "/");
                String wandler4 = wandler3.replace(" ", "");
                String wandler5 = wandler4.replace("\\", "/");
                field.setText(wandler5);
                FileWriter myWriter = null;
                try {
                    myWriter = new FileWriter("C:/UndoIDE/undoide.uic");

                    myWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
