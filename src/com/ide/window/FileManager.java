package com.ide.window;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.String;

import com.ide.window.setup.Setup;
import jdk.nashorn.internal.parser.Parser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FileManager {


    public static FileBrowser FileBrowser;

    public static class FileBrowser implements Runnable {

         DefaultMutableTreeNode root;

        DefaultTreeModel treeModel;

         JTree tree;
         IDEWindow f;
        Setup setup;
        private JTextField filename = new JTextField(35);
        IDEWindow m;


        public JTextField getTextField() {
            return filename;
        }

        @Override
        public void run() {
            JFrame frame = new JFrame("Neues Projekt");
            File fileRoot = new File("C:/UndoIDE");
            JButton b1 = new JButton("GetFileName");
            JButton b = new JButton("Speichern");
            JPanel p = new JPanel();
            frame.add(p, BorderLayout.SOUTH);
            p.add(filename);
            p.add(b);
            p.add(b1);
            b.setVisible(false);
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String pathout = tree.getSelectionPath().toString();
                    String wandler = pathout.replace(",", "/");
                    String wandler2 = wandler.replace("[", "");
                    String wandler3 = wandler2.replace("]", "/");
                    String wandler4 = wandler3.replace(" ", "");
                    String wandler5 = wandler4.replace("\\", "/");
                    String out = filename.getText();
                    filename.setText(wandler5 + out);
                    b.setVisible(true);
            }
            });
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String file = filename.getText();
                    File new1 = new File("C:/" + file);
                    try {
                        new1.createNewFile();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("C:/UndoIDE/undoide.uic");
                        myWriter.write(file);
                        myWriter.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    FileWriter myWriter2 = null;
                    try {
                        myWriter2 = new FileWriter("C:/UndoIDE/cache/cache.dat");
                        myWriter2.write("true");
                        myWriter2.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    frame.dispose();
                }
            });
            root = new DefaultMutableTreeNode(new FileNode(fileRoot));
            treeModel = new DefaultTreeModel(root);

            tree = new JTree(treeModel);
            tree.setShowsRootHandles(true);
            JScrollPane scrollPane = new JScrollPane(tree);

            frame.add(scrollPane);
            frame.setLocationByPlatform(true);
            frame.setSize(640, 480);
            frame.setVisible(true);

            CreateChildNodes ccn =
                    new CreateChildNodes(fileRoot, root);
            new Thread(ccn).start();
        }

        public void file() {
            new IDEWindow().getTextField1().setText("t");
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new FileBrowser());
        }

        public static class CreateChildNodes implements Runnable {

            private DefaultMutableTreeNode root;

            private File fileRoot;

            public CreateChildNodes(File fileRoot,
                                    DefaultMutableTreeNode root) {
                this.fileRoot = fileRoot;
                this.root = root;
            }

            @Override
            public void run() {
                createChildren(fileRoot, root);
            }

            private void createChildren(File fileRoot,
                                        DefaultMutableTreeNode node) {
                File[] files = fileRoot.listFiles();
                if (files == null) return;

                for (File file : files) {
                    DefaultMutableTreeNode childNode =
                            new DefaultMutableTreeNode(new FileNode(file));
                    node.add(childNode);
                    if (file.isDirectory()) {
                        createChildren(file, childNode);
                    }
                }
            }

        }

        public static class FileNode {

            private File file;

            public FileNode(File file) {
                this.file = file;
            }

            @Override
            public String toString() {
                String name = file.getName();
                if (name.equals("")) {
                    return file.getAbsolutePath();
                } else {
                    return name;
                }
            }
        }

    }
}
