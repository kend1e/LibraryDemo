package com.kenddie.librarydemo.ui;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

public final class InputUtils {
    private InputUtils() {}

    public static <T extends Enum<T>> T askForChoice(String text, T[] choices, Function<T, String> displayFunction, Scanner scanner) {
        do {
            System.out.println(text);

            for (T choice : choices) {
                System.out.println((choice.ordinal() + 1) + ". " + displayFunction.apply(choice));
            }

            String input = scanner.nextLine().trim();
            int index;
            try {
                index = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            Optional<T> selected = Arrays.stream(choices)
                    .filter(choice -> (choice.ordinal() + 1) == index)
                    .findAny();

            if (selected.isPresent()) {
                return selected.get();
            } else {
                System.out.println("Invalid selection, try again.");
            }
        } while (true);
    }

    public static <T> T askForEntityChoice(String text, T[] choices, Function<T, String> displayFunction, Scanner scanner) {
        do {
            System.out.println(text);

            for (int i = 0; i < choices.length; i++) {
                System.out.println((i + 1) + ". " + displayFunction.apply(choices[i]));
            }

            String input = scanner.nextLine().trim();
            int index;
            try {
                index = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            if (index < 1 || index > choices.length) {
                System.out.println("Invalid selection, try again.");
                continue;
            }

            return choices[index - 1];
        } while (true);
    }
}
