package com.ide.window.setup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Setup {

    public static void main(String[] args) {
    }

    public String readfile(String filename) throws FileNotFoundException {
        File myObj = new File("C:/UndoIDE/" + filename);
        Scanner myReader = new Scanner(myObj);
        String data = myReader.nextLine();
        myReader.close();
        return toString();
    }

    public void setup() throws IOException {
        File undoIDE = new File("C:/UndoIDE");
        File cache = new File("C:/UndoIDE/cache");
        File file1 = new File("C:/UndoIDE/undoide.uic");
        undoIDE.mkdir();
        cache.mkdir();
        file1.createNewFile();
    }

    public void createFile(String filename) throws IOException {
        File file = new File("C:/UndoIDE/" + filename);
        file.createNewFile();
    }

    public void createDirs(String dirnames) {
        File dir = new File("C:/UndoIDE/" + dirnames);
        dir.mkdirs();
    }

    public void createDir(String dirname) {
        File dir = new File("C:/UndoIDE/" + dirname);
        dir.mkdir();
    }
}
