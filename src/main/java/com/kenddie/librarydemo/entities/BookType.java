package com.kenddie.librarydemo.entities;

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
