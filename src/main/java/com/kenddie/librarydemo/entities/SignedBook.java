package com.kenddie.librarydemo.entities;

import com.kenddie.librarydemo.entities.lib.Languages;

import java.time.LocalDate;
import java.util.UUID;

public class SignedBook extends Book {
    public SignedBook(UUID id, String name,
                      String publisher, String author,
                      Languages language, LocalDate publishDate,
                      int value, int pages, BookType type, String content) {
        super(id, name, publisher, author, language, publishDate, value, pages, type, content);
    }

    @Override
    public int getValue() {
        return super.getValue() * 2;
    }
}
