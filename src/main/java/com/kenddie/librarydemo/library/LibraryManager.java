package com.kenddie.librarydemo.library;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kenddie.librarydemo.entities.Book;
import com.kenddie.librarydemo.entities.Poster;
import com.kenddie.librarydemo.entities.SignedBook;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public final class LibraryManager {
    private static final String ENTITIES_PATH = "library/entities/";
    private static final String CATALOG_PATH = "library/library_catalog.json";

    private LibraryManager() {}

    public static Map<LibraryEntity, Integer> loadLibrary() {
        Map<String, Integer> catalog = loadCatalog();
        Map<LibraryEntity, Integer> finalMap = new HashMap<>();

        finalMap.putAll(loadEntities(ENTITIES_PATH + "books", Book.class, catalog));
        finalMap.putAll(loadEntities(ENTITIES_PATH + "signed_books", SignedBook.class, catalog));
        finalMap.putAll(loadEntities(ENTITIES_PATH + "posters", Poster.class, catalog));

        return finalMap;
    }

    public static void addToCatalog(LibraryEntity entity) {
        loadCatalog().merge(entity.getId().toString(), 1, Integer::sum);
    }

    public static void removeFromCatalog(LibraryEntity entity) {
        loadCatalog().merge(entity.getId().toString(), -1, Integer::sum);
    }

    private static <T extends LibraryEntity> Map<LibraryEntity, Integer>
    loadEntities(String path, Class<T> clazz, Map<String, Integer> catalog) {
        Map<LibraryEntity, Integer> libraryEntities = new HashMap<>();

        File directory = new File(path);
        File[] files = directory.listFiles((dir, name) -> name.endsWith("json"));

        Gson gson = new Gson();
        if (files == null) {
            return libraryEntities;
        }

        for (File file : files) {
            try (FileReader fileReader = new FileReader(file)) {
                T entity = gson.fromJson(fileReader, clazz);
                String id = entity.getId().toString();

                if (catalog.containsKey(id)) {
                    libraryEntities.put(entity, catalog.get(id));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return libraryEntities;
    }

    private static Map<String, Integer> loadCatalog() {
        Gson libraryCatalog = new Gson();
        Type type = new TypeToken<Map<String, Integer>>() {}.getType();

        try (FileReader fileReader = new FileReader(CATALOG_PATH)) {
            return libraryCatalog.fromJson(fileReader, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
