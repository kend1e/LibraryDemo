package com.kenddie.librarydemo.entities.lib;

import java.util.UUID;

public abstract class LibraryEntity {
    private final UUID id;
    private final String name;
    private final String publisher;
    private final String author;
    private final Languages language;
    private final int publishDate;
    private final int value;
    private final String content;

    public LibraryEntity(UUID id, String name, String publisher,
                         String author, Languages language,
                         int publishDate, int value, String content) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.language = language;
        this.publishDate = publishDate;
        this.value = value;
        this.content = content;
    }

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

    public int getPublishDate() {
        return publishDate;
    }

    public int getValue() {
        return value;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LibraryEntity other)) {
            return false;
        }
        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
