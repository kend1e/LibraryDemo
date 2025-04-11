package com.kenddie.librarydemo.library;

@SuppressWarnings("ClassCanBeRecord")
public class CatalogEntry {
    private final String id;
    private final String type;
    private final int count;

    public CatalogEntry(String id, String type, int count) {
        this.id = id;
        this.type = type;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return count;
    }
}
