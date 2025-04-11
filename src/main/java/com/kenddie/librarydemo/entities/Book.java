package com.kenddie.librarydemo.entities;

import com.kenddie.librarydemo.entities.lib.Borrowable;
import com.kenddie.librarydemo.entities.lib.Languages;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;
import com.kenddie.librarydemo.entities.lib.Readable;
import com.kenddie.librarydemo.ui.PageReader;

import java.util.UUID;

public class Book extends LibraryEntity implements Borrowable, Readable {
    private final BookType bookType;

    public Book(UUID id, String name,
                String publisher, String author,
                Languages language, int publishDate,
                int value, BookType bookType, String content) {
        super(id, name, publisher, author, language, publishDate, value, content);
        this.bookType = bookType;
    }

    @Override
    public String getShortDescription() {
        return bookType.toString() + " | " + getName() + " | Author: " + getAuthor() + " | Pages: " + getPages();
    }

    public BookType getType() {
        return bookType;
    }

    @Override
    public int getPages() {
        if (getContent() == null || getContent().isBlank()) {
            return 0;
        }
        return getContent().length() / PageReader.PAGE_SIZE;
    }
}
