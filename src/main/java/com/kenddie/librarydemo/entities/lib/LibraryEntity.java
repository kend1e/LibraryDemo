package com.kenddie.librarydemo.entities.lib;

import java.time.LocalDate;
import java.util.UUID;

public abstract class LibraryEntity {
    private final UUID id;
    private final String name;
    private final String publisher;
    private final String author;
    private final Languages language;
    private final LocalDate publishDate;
    private final int value;
    private final String content;

    public LibraryEntity(UUID id, String name, String publisher,
                         String author, Languages language,
                         LocalDate publishDate, int value, String content) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.language = language;
        this.publishDate = publishDate;
        this.value = value;
        this.content = content;
    }

    public abstract boolean isBorrowable();

    public abstract String getShortDescription();

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }

    public Languages getLanguage() {
        return language;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public int getValue() {
        return value;
    }

    public String getContent() {
        return content;
    }
}
