package com.kenddie.librarydemo.ui;

import com.kenddie.librarydemo.entities.lib.LibraryEntity;
import com.kenddie.librarydemo.entities.lib.Readable;
import com.kenddie.librarydemo.library.BookLibrary;
import com.kenddie.librarydemo.library.CatalogEntry;

import java.util.HashSet;
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
            System.out.println("\nWelcome to the library!");
        } while (showMenu());
    }

    private boolean showMenu() {
        MenuOption menuOption = InputUtils.askForChoice(
                "What would you like to do?", MenuOption.values(), MenuOption::getTitle, scanner);

        switch (menuOption) {
            case READ:
                libraryRead();
                return true;
            case BORROW:
                libraryBorrow();
                return true;
            case RETURN:
                libraryReturn();
                return true;
            default:
                return false;
        }
    }

    private void libraryBorrow() {
        LibraryEntity[] entities = BookLibrary.getInstance().getAvailableEntities();
        if (entities == null) {
            System.out.println("No items are available in the library.");
            return;
        }

        LibraryEntity entity = InputUtils.askForEntityChoice(
                "\nChoose an item to borrow:", entities, (libraryEntity) ->
                        libraryEntity.getShortDescription()
                        + " | Count: "
                        + BookLibrary.getInstance().getCountOfEntity(libraryEntity),
                scanner);

        if (BookLibrary.getInstance().borrowEntity(entity, userName)) {
            System.out.println("You have successfully borrowed " + entity.getShortDescription());
            System.out.println("There are "
                    + BookLibrary.getInstance().getCountOfEntity(entity)
                    + " of those books in the library.");
        } else {
            System.out.println("You can't borrow this item.");
        }
    }

    private void libraryRead() {
        LibraryEntity[] entities = BookLibrary.getInstance().getAvailableEntities();
        if (entities == null) {
            System.out.println("No items are available in the library.");
            return;
        }

        LibraryEntity entity = InputUtils.askForEntityChoice(
                "\nChoose an item to read:", entities, (libraryEntity) ->
                        libraryEntity.getShortDescription()
                                + " | Count: "
                                + BookLibrary.getInstance().getCountOfEntity(libraryEntity), scanner);

        if (entity instanceof Readable) {
            PageReader.readContent(entity.getContent(), scanner);
        } else {
            System.out.println("\n" + entity.getContent() + "\n");
        }
    }

    private void libraryReturn() {
        HashSet<LibraryEntity> borrowed = new HashSet<>();
        HashSet<CatalogEntry> catalog = BookLibrary.getInstance().getCatalog();

        for (CatalogEntry catalogEntry : catalog) {
            for (String user : catalogEntry.getUsers()) {
                if (user.equals(userName)) {
                    borrowed.add(BookLibrary.getInstance().findEntityById(catalogEntry.getId()));
                }
            }
        }

        if (borrowed.isEmpty()) {
            System.out.println("You haven't borrowed any items.");
            return;
        }

        LibraryEntity entity = InputUtils.askForEntityChoice(
                "\nChoose an item to return:", borrowed.toArray(new LibraryEntity[0]),
                LibraryEntity::getShortDescription, scanner);

        if (BookLibrary.getInstance().returnEntity(entity, userName)) {
            System.out.println("You have successfully returned " + entity.getShortDescription());
            System.out.println("There are "
                    + BookLibrary.getInstance().getCountOfEntity(entity)
                    + " those books in the library.");
        } else {
            System.out.println("You can't return this item.");
        }
    }

    /*private void showCurrentLibrary() {
        Map<LibraryEntity, Integer> libraryEntities = BookLibrary.getInstance().getLibrary();
        for (LibraryEntity entity : libraryEntities.keySet()) {
            System.out.println(entity.getShortDescription() + " | Count: " + libraryEntities.get(entity));
        }
    }*/

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
