package com.kenddie.librarydemo.library;

import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.util.Map;

public class BookLibrary {
    private static BookLibrary bookLibrary;
    private final Map<LibraryEntity, Integer> library;

    private BookLibrary() {
        library = LibraryManager.loadLibrary();
    }

    public BookLibrary getInstance() {
        if (bookLibrary == null) {
            bookLibrary = new BookLibrary();
        }
        return bookLibrary;
    }

    public Map<LibraryEntity, Integer> getLibrary() {
        return library;
    }
}
