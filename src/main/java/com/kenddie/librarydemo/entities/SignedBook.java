package com.kenddie.librarydemo.entities;

import com.kenddie.librarydemo.entities.lib.Languages;

import java.util.UUID;

/**
 * Signed book item. Extends {@link Book} and overrides its value to increased price.
 */
public class SignedBook extends Book {
    private static final int VALUE_MULTIPLIER = 2;

    public SignedBook(UUID id, String name,
                      String publisher, String author,
                      Languages language, int publishDate,
                      int value, BookType bookType, String content, int pageSize) {
        super(id, name, publisher, author, language, publishDate, value, bookType, content, pageSize);
    }

    @Override
    public int getValue() {
        return super.getValue() * VALUE_MULTIPLIER;
    }
}
