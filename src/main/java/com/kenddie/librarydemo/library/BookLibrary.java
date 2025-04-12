package com.kenddie.librarydemo.library;

import com.kenddie.librarydemo.entities.lib.Borrowable;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.util.HashSet;
import java.util.Map;

public class BookLibrary {
    private static BookLibrary bookLibrary;
    private final Map<LibraryEntity, Integer> library;
    private final HashSet<CatalogEntry> catalog;

    private BookLibrary() {
        catalog = LibraryManager.loadCatalog();
        library = LibraryManager.loadLibrary();
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

        catalogEntry.setCount(catalogEntry.getCount() - 1);
        if (!catalogEntry.addUser(userName)) {
            return false;
        }
        library.merge(entity, -1, Integer::sum);
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

        catalogEntry.setCount(catalogEntry.getCount() + 1);
        if (!catalogEntry.removeUser(userName)) {
            return false;
        }
        library.merge(entity, 1, Integer::sum);
        LibraryManager.saveCatalog(catalog);
        return true;
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
        for (LibraryEntity entity : library.keySet()) {
            if (entity.getId().toString().equals(id)) {
                return entity;
            }
        }
        return null;
    }

    public LibraryEntity[] getAvailableEntities() {
        LibraryEntity[] entities = library.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)
                .map(Map.Entry::getKey)
                .toArray(LibraryEntity[]::new);

        if (entities.length == 0) {
            return null;
        }

        return entities;
    }
}
