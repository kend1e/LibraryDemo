package com.kenddie.librarydemo.ui;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    private static ConsoleUI instance;
    private String userName;

    private ConsoleUI() {
    }

    public void run() {
        askUserName();


    }

    private void askUserName() {
        String name;
        do {
            System.out.println("Welcome to the library! Please enter your name: ");
            name = scanner.nextLine();

            if (!name.matches("^(?=.*[A-Za-z0-9]).{3,30}$")) {
                System.out.println("\nPlease enter a valid name.");
            } else {
                break;
            }
        } while (true);
        userName = name;
    }

    public static ConsoleUI getInstance() {
        if (instance == null) {
            instance = new ConsoleUI();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }
}
