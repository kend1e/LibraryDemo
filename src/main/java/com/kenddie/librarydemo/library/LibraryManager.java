package com.kenddie.librarydemo.library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kenddie.librarydemo.entities.Book;
import com.kenddie.librarydemo.entities.Poster;
import com.kenddie.librarydemo.entities.SignedBook;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public final class LibraryManager {
    private static final String ENTITIES_PATH = "library/entities/";
    private static final String CATALOG_PATH = "data/library_catalog.json";

    private LibraryManager() {}

    public static Map<LibraryEntity, Integer> loadLibrary() {
        HashSet<CatalogEntry> catalog = loadCatalog();
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

    public static HashSet<CatalogEntry> loadCatalog() {
        Gson gson = new Gson();
        Type listType = new TypeToken<HashSet<CatalogEntry>>() {}.getType();

        try (Reader reader = new FileReader(CATALOG_PATH)) {
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load catalog", e);
        }
    }

    public static void saveCatalog(HashSet<CatalogEntry> catalog) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(CATALOG_PATH)) {
            gson.toJson(catalog, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save catalog", e);
        }
    }
}
