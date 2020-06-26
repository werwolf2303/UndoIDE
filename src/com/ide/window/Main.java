package com.ide.window;

import com.ide.window.setup.Setup;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    public void run() throws IOException {
        File f = new File("C:/UndoIDE");
        if(f.exists()) {
            new IDEWindow().run();
        }else{
            new Setup().setup();
        }
    }
}
