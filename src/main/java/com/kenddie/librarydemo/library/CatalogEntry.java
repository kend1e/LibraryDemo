package com.kenddie.librarydemo.library;

import java.util.HashSet;

/**
 * Catalog entry that maps a library item to its count and the users who have borrowed it.
 */
public class CatalogEntry {
    private final String id;
    private final String type;
    private int count;
    private final HashSet<String> users;

    public CatalogEntry(String id, String type, int count, HashSet<String> users) {
        this.id = id;
        this.type = type;
        this.count = count;
        this.users = users;
    }

    public void setCount(int count) {
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

    public HashSet<String> getUsers() {
        return users;
    }

    public boolean addUser(String user) {
        return users.add(user);
    }

    public boolean removeUser(String user) {
        return users.remove(user);
    }
}
