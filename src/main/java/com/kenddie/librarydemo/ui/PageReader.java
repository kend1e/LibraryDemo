package com.kenddie.librarydemo.ui;

import com.kenddie.librarydemo.entities.lib.LibraryEntity;
import com.kenddie.librarydemo.entities.lib.Readable;

import java.util.Scanner;

/**
 * Allows users to read long content in paginated form in the console.
 * Content is divided into fixed-size pages and navigated interactively.
 */
public final class PageReader {
    private enum PageAction {
        NEXT("Next"),
        PREVIOUS("Previous"),
        EXIT("Exit");

        private final String title;

        PageAction(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    private PageReader() {
    }

    /*public static int read(Readable entity, int currentPage, Scanner scanner) {
        String content = entity.getPage(currentPage);

        if (content == null || content.isEmpty()) {
            System.out.println("This item has no readable content.");
            return 0;
        }

        int totalPages = entity.getPageCount();

        while (true) {
            System.out.println("\nPage " + (currentPage + 1) + " / " + totalPages);
            System.out.println(content);

            PageAction action = InputUtils.askForChoice("Choose an action:", PageAction.values(), PageAction::getTitle, scanner);

            switch (action) {
                case NEXT:
                    if (currentPage < totalPages - 1) {
                        return currentPage + 1;
                    }
                    break;
                case PREVIOUS:
                    if (currentPage > 0) {
                        return currentPage - 1;
                    }
                    break;
                case EXIT:
                    return 0;
            }
        }
    }*/

    public static void readContent(Readable entity, Scanner scanner) {
        if (entity.getPageCount() <= 0) {
            System.out.println("This item has no readable content.");
            return;
        }
        int currentPage = 0;

        int totalPages = entity.getPageCount();

        while (true) {
            System.out.println("\nPage " + (currentPage + 1) + " / " + totalPages);
            System.out.println(entity.getPage(currentPage));

            PageAction action = InputUtils.askForChoice("Choose an action:", PageAction.values(), PageAction::getTitle, scanner);

            switch (action) {
                case NEXT:
                    if (currentPage < totalPages - 1) {
                        currentPage++;
                    }
                    break;
                case PREVIOUS:
                    if (currentPage > 0) {
                        currentPage--;
                    }
                    break;
                case EXIT:
                    return;
            }
        }
    }
}