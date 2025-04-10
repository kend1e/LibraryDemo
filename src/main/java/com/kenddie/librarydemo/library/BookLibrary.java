package com.kenddie.librarydemo.library;

import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.util.Map;

public class BookLibrary {
    private static BookLibrary bookLibrary;
    private Map<LibraryEntity, Integer> library;

    private BookLibrary() {
        library = LibraryManager.loadLibrary();
    }

    public void addEntity(LibraryEntity entity) {
        library.merge(entity, 1, Integer::sum);
        LibraryManager.addToCatalog(entity);
    }

    public void removeEntity(LibraryEntity entity) {
        library.merge(entity, -1, Integer::sum);
        LibraryManager.removeFromCatalog(entity);
    }

    public static BookLibrary getInstance() {
        if (bookLibrary == null) {
            bookLibrary = new BookLibrary();
        }
        return bookLibrary;
    }

    public Map<LibraryEntity, Integer> getLibrary() {
        return library;
    }
}
