package com.kenddie.librarydemo.library;

import com.kenddie.librarydemo.entities.lib.Borrowable;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.util.List;
import java.util.Map;

public class BookLibrary {
    private static BookLibrary bookLibrary;
    private final Map<LibraryEntity, Integer> library;
    private final List<CatalogEntry> catalog;

    private BookLibrary() {
        catalog = LibraryManager.loadCatalog();
        library = LibraryManager.loadLibrary();
    }

    public boolean borrowEntity(LibraryEntity entity) {
        if (!(entity instanceof Borrowable)) {
            return false;
        }
        String id = entity.getId().toString();
        CatalogEntry catalogEntry = findEntryById(id);

        if (catalogEntry == null || catalogEntry.getCount() <= 0) {
            return false;
        }

        catalogEntry.setCount(catalogEntry.getCount() - 1);
        library.merge(entity, -1, Integer::sum);
        LibraryManager.saveCatalog(catalog);
        return true;
    }

    public void returnEntity(LibraryEntity entity) {
        if (!(entity instanceof Borrowable)) {
            return;
        }
        String id = entity.getId().toString();
        CatalogEntry entry = findEntryById(id);

        if (entry == null) {
            return;
        }

        entry.setCount(entry.getCount() + 1);
        library.merge(entity, 1, Integer::sum);
        LibraryManager.saveCatalog(catalog);
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

    private CatalogEntry findEntryById(String id) {
        for (CatalogEntry entry : catalog) {
            if (entry.getId().equals(id)) {
                return entry;
            }
        }
        return null;
    }
}
