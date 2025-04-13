package com.kenddie.librarydemo.entities;

/**
 * Enum of the type of the book, such as standard book, magazine, or newspaper.
 */
public enum BookType {
    BOOK,
    MAGAZINE,
    NEWSPAPER;

    @Override
    public String toString() {
        String name = this.name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
