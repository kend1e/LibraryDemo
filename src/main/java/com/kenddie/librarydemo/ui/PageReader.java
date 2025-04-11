package com.kenddie.librarydemo.ui;

import java.util.Scanner;

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

    public static final int PAGE_SIZE = 1000;

    private PageReader() {
    }

    public static void readContent(String content, Scanner scanner) {
        if (content == null || content.isEmpty()) {
            System.out.println("This item has no readable content.");
            return;
        }

        int totalPages = content.length() / PAGE_SIZE;
        int currentPage = 0;

        while (true) {
            showPage(content, currentPage, totalPages);

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

    private static void showPage(String content, int currentPage, int totalPages) {
        int start = currentPage * PAGE_SIZE;
        int end = Math.min(content.length(), start + PAGE_SIZE);
        String page = content.substring(start, end);

        System.out.println("\nPage " + (currentPage + 1) + " / " + totalPages);
        System.out.println(page);
    }
}