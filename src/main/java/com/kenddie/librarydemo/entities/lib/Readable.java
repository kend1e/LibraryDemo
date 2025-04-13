package com.kenddie.librarydemo.entities.lib;

/**
 * Interface for items that will be read page by page. Otherwise, the item content will be displayed at once.
 */
public interface Readable {
    int getPages();
}
