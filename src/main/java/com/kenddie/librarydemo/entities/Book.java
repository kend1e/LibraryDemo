package com.kenddie.librarydemo.entities;

import com.kenddie.librarydemo.entities.lib.Borrowable;
import com.kenddie.librarydemo.entities.lib.Languages;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.util.UUID;

public class Book extends LibraryEntity implements Borrowable {
    private final int pages;
    private final BookType bookType;

    public Book(UUID id, String name,
                String publisher, String author,
                Languages language, int publishDate,
                int value, int pages, BookType bookType, String content) {
        super(id, name, publisher, author, language, publishDate, value, content);
        this.pages = pages;
        this.bookType = bookType;
    }

    @Override
    public String getShortDescription() {
        return bookType.toString() + " | " + getName() + " | Author: " + getAuthor() + " | Pages: " + pages;
    }

    public BookType getType() {
        return bookType;
    }

    public int getPages() {
        return pages;
    }
}
