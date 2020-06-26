package com.ide.window;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class OpenFile extends JDialog implements Runnable  {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public static FileManager.FileBrowser FileBrowser;

        private DefaultMutableTreeNode root;

        private DefaultTreeModel treeModel;

        private JTree tree;

        public void run() {
            method();
        }

        public void method() {
            OpenFile frame3 = new OpenFile();
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            File fileRoot = new File("C:/");
            root = new DefaultMutableTreeNode(new FileManager.FileBrowser.FileNode(fileRoot));
            treeModel = new DefaultTreeModel(root);
            tree = new JTree(treeModel);
            tree.setShowsRootHandles(true);
            JScrollPane scrollPane = new JScrollPane(tree);
            frame3.add(scrollPane);
            frame3.setLocationByPlatform(true);
            frame3.setSize(640, 480);
            frame3.setVisible(true);

            FileManager.FileBrowser.CreateChildNodes ccn =
                    new FileManager.FileBrowser.CreateChildNodes(fileRoot, root);
            new Thread(ccn).start();
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new FileManager.FileBrowser());
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
                            new DefaultMutableTreeNode(new FileManager.FileBrowser.FileNode(file));
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
