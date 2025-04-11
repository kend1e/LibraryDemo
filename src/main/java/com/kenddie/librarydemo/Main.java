package com.kenddie.librarydemo;

import com.kenddie.librarydemo.library.BookLibrary;
import com.kenddie.librarydemo.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        BookLibrary bookLibrary = BookLibrary.getInstance();
        ConsoleUI ui = ConsoleUI.getInstance();
        ui.run();
    }
}