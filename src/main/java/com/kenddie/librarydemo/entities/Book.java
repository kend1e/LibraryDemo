package com.kenddie.librarydemo.entities;

import com.kenddie.librarydemo.entities.lib.Languages;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.time.LocalDate;
import java.util.UUID;

public class Book extends LibraryEntity {
    private final int pages;
    private final BookType type;


    public Book(UUID id, String name,
                String publisher, String author,
                Languages language, LocalDate publishDate,
                int value, int pages, BookType type, String content) {
        super(id, name, publisher, author, language, publishDate, value, content);
        this.pages = pages;
        this.type = type;
    }

    @Override
    public boolean isBorrowable() {
        return true;
    }

    @Override
    public String getShortDescription() {
        return type.toString() + " | " + getName() + " | Author: " + getAuthor() + " | Pages: " + pages;
    }

    public BookType getType() {
        return type;
    }

    public int getPages() {
        return pages;
    }
}
