package com.kenddie.librarydemo.library;

import com.kenddie.librarydemo.entities.lib.Borrowable;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.util.HashSet;

public class BookLibrary {
    private static BookLibrary bookLibrary;
    private final HashSet<LibraryEntity> library;
    private final HashSet<CatalogEntry> catalog;

    private BookLibrary() {
        catalog = LibraryManager.loadCatalog();
        library = LibraryManager.loadLibrary();
    }

    public static BookLibrary getInstance() {
        if (bookLibrary == null) {
            bookLibrary = new BookLibrary();
        }
        return bookLibrary;
    }

    public boolean borrowEntity(LibraryEntity entity, String userName) {
        if (!(entity instanceof Borrowable)) {
            return false;
        }
        String id = entity.getId().toString();
        CatalogEntry catalogEntry = findEntryById(id);

        if (catalogEntry == null || catalogEntry.getCount() <= 0) {
            return false;
        }

        if (!catalogEntry.addUser(userName)) {
            return false;
        }
        catalogEntry.setCount(catalogEntry.getCount() - 1);
        LibraryManager.saveCatalog(catalog);
        return true;
    }

    public boolean returnEntity(LibraryEntity entity, String userName) {
        if (!(entity instanceof Borrowable)) {
            return false;
        }
        String id = entity.getId().toString();
        CatalogEntry catalogEntry = findEntryById(id);

        if (catalogEntry == null) {
            return false;
        }

        if (!catalogEntry.removeUser(userName)) {
            return false;
        }
        catalogEntry.setCount(catalogEntry.getCount() + 1);
        LibraryManager.saveCatalog(catalog);
        return true;
    }

    public HashSet<LibraryEntity> getLibrary() {
        return library;
    }

    public HashSet<CatalogEntry> getCatalog() {
        return catalog;
    }

    public CatalogEntry findEntryById(String id) {
        for (CatalogEntry entry : catalog) {
            if (entry.getId().equals(id)) {
                return entry;
            }
        }
        return null;
    }

    public LibraryEntity findEntityById(String id) {
        for (LibraryEntity entity : library) {
            if (entity.getId().toString().equals(id)) {
                return entity;
            }
        }
        return null;
    }

    public LibraryEntity[] getAvailableEntities() {
        LibraryEntity[] entities = library
                .stream()
                .filter(entity -> getCountOfEntity(entity) > 0)
                .toArray(LibraryEntity[]::new);

        if (entities.length == 0) {
            return null;
        }

        return entities;
    }

    public int getCountOfEntity(LibraryEntity entity) {
        for (CatalogEntry entry : catalog) {
            if (entry.getId().equals(entity.getId().toString())) {
                return entry.getCount();
            }
        }
        return -1;
    }
}
