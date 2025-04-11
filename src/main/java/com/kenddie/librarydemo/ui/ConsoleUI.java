package com.kenddie.librarydemo.ui;

import com.kenddie.librarydemo.entities.lib.LibraryEntity;
import com.kenddie.librarydemo.entities.lib.Readable;
import com.kenddie.librarydemo.library.BookLibrary;

import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {
    private enum MenuOption {
        READ("Read a book"),
        BORROW("Borrow a book"),
        RETURN("Return a book"),
        EXIT("Exit");

        private final String title;

        MenuOption(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    private final Scanner scanner = new Scanner(System.in);

    private static ConsoleUI instance;
    private String userName;

    private ConsoleUI() {
    }

    public void run() {
        askUserName();

        do {
            System.out.println("Welcome to the library!");
        } while (showMenu());
    }

    private boolean showMenu() {
        MenuOption menuOption = InputUtils.askForChoice(
                "What would you like to do?", MenuOption.values(), MenuOption::getTitle, scanner);

        switch (menuOption) {
            case READ:
                showCurrentLibrary();
                readEntity();
                return true;
            case BORROW:
                return true;
            case RETURN:
                return true;
            default:
                return false;
        }
    }

    private void readEntity() {
        Map<LibraryEntity, Integer> library = BookLibrary.getInstance().getLibrary();
        LibraryEntity[] entities = library.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)
                .map(Map.Entry::getKey)
                .toArray(LibraryEntity[]::new);

        if (entities.length == 0) {
            System.out.println("No items are available in the library.");
            return;
        }

        LibraryEntity entity = InputUtils.askForEntityChoice(
                "\nChoose an item to read:", entities, LibraryEntity::getShortDescription, scanner);

        if (entity instanceof Readable) {
            PageReader.readContent(entity.getContent(), scanner);
        } else {
            System.out.println("\n" + entity.getContent() + "\n");
        }
    }

    private void showCurrentLibrary() {
        Map<LibraryEntity, Integer> libraryEntities = BookLibrary.getInstance().getLibrary();
        for (LibraryEntity entity : libraryEntities.keySet()) {
            System.out.println(entity.getShortDescription() + " | Count: " + libraryEntities.get(entity));
        }
    }

    private void askUserName() {
        String name;
        do {
            System.out.println("Hello! Please enter your name: ");
            name = scanner.nextLine().trim();

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
}
