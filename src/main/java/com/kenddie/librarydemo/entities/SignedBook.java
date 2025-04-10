package com.kenddie.librarydemo.entities;

import com.kenddie.librarydemo.entities.lib.Languages;

import java.time.LocalDate;
import java.util.UUID;

public class SignedBook extends Book {
    private static final int VALUE_MULTIPLIER = 2;

    public SignedBook(UUID id, String name,
                      String publisher, String author,
                      Languages language, LocalDate publishDate,
                      int value, int pages, BookType bookType, String content) {
        super(id, name, publisher, author, language, publishDate, value, pages, bookType, content);
    }

    @Override
    public int getValue() {
        return super.getValue() * VALUE_MULTIPLIER;
    }
}
