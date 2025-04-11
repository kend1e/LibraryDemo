package com.kenddie.librarydemo.library;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kenddie.librarydemo.entities.Book;
import com.kenddie.librarydemo.entities.Poster;
import com.kenddie.librarydemo.entities.SignedBook;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LibraryManager {
    private static final String ENTITIES_PATH = "library/entities/";
    private static final String CATALOG_PATH = "library/library_catalog.json";

    private LibraryManager() {}

    public static Map<LibraryEntity, Integer> loadLibrary() {
        List<CatalogEntry> catalog = loadCatalog();
        Map<LibraryEntity, Integer> finalMap = new HashMap<>();

        for (CatalogEntry entry : catalog) {
            switch (entry.getType()) {
                case "book":
                    loadEntity(finalMap, "book", Book.class, entry);
                    break;
                case "signed_book":
                    loadEntity(finalMap, "signed_book", SignedBook.class, entry);
                    break;
                case "poster":
                    loadEntity(finalMap, "poster", Poster.class, entry);
                    break;
            }
        }

        return finalMap;
    }

    private static <T extends LibraryEntity> void loadEntity(
            Map<LibraryEntity, Integer> map, String folder, Class<T> clazz, CatalogEntry entry) {
        Gson gson = new Gson();
        String filePath = ENTITIES_PATH + folder + "/" + entry.getId() + ".json";

        try (InputStream inputStream = LibraryManager.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Entity file not found: " + filePath);
            }

            T entity = gson.fromJson(new InputStreamReader(inputStream), clazz);
            map.put(entity, entry.getCount());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load: " + filePath, e);
        }
    }

    private static List<CatalogEntry> loadCatalog() {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CatalogEntry>>() {}.getType();

        try (InputStream inputStream = LibraryManager.class.getClassLoader().getResourceAsStream(CATALOG_PATH)) {
            if (inputStream == null) {
                throw new RuntimeException("Catalog file not found: " + CATALOG_PATH);
            }

            return gson.fromJson(new InputStreamReader(inputStream), listType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
