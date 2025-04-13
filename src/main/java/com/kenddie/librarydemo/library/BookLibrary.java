package com.kenddie.librarydemo.library;

import com.kenddie.librarydemo.entities.lib.Borrowable;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.util.HashSet;

/**
 * Singleton class that manages the state of the library, including available items and catalog entries.
 * Supports borrowing and returning of borrowable items.
 */

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

    /**
     * Attempts to borrow the specified entity for a given user.
     *
     * @param entity the item to borrow
     * @param userName the name of the user
     * @return {@code true} if the borrow was successful, otherwise {@code false}
     */
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

    /**
     * Attempts to return the specified entity for a given user.
     *
     * @param entity the item to return
     * @param userName the name of the user
     * @return {@code true} if the return was successful, otherwise {@code false}
     */
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

    /**
     * Finds a catalog entry by its ID.
     *
     * @param id the ID of the item
     * @return the catalog entry, or {@code null} if not found
     */
    public CatalogEntry findEntryById(String id) {
        for (CatalogEntry entry : catalog) {
            if (entry.getId().equals(id)) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Finds a library entity by its ID.
     *
     * @param id the ID of the entity
     * @return the library entity instance, or {@code null} if not found
     */
    public LibraryEntity findEntityById(String id) {
        for (LibraryEntity entity : library) {
            if (entity.getId().toString().equals(id)) {
                return entity;
            }
        }
        return null;
    }

    /**
     * Returns an array of entities that are available (have non-zero count).
     *
     * @return an array of available entities, or {@code null} if none are available
     */
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

    /**
     * Returns the number of available copies of the specified entity.
     *
     * @param entity the entity to check
     * @return the count, or -1 if the entity is not in the catalog
     */
    public int getCountOfEntity(LibraryEntity entity) {
        for (CatalogEntry entry : catalog) {
            if (entry.getId().equals(entity.getId().toString())) {
                return entry.getCount();
            }
        }
        return -1;
    }
}
