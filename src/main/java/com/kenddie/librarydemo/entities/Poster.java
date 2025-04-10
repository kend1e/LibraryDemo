package com.kenddie.librarydemo.entities;

import com.kenddie.librarydemo.entities.lib.Languages;
import com.kenddie.librarydemo.entities.lib.LibraryEntity;

import java.awt.*;
import java.time.LocalDate;
import java.util.UUID;

public class Poster extends LibraryEntity {
    private final Dimension size;

    public Poster(UUID id, String name,
                  String publisher, String author,
                  Languages language, LocalDate publishDate,
                  int value, Dimension size, String content) {
        super(id, name, publisher, author, language, publishDate, value, content);
        this.size = size;
    }

    @Override
    public String getShortDescription() {
        return "Poster | " + getName() + " | Size: " + size.height + " x " + size.width;
    }

    public Dimension getSize() {
        return size;
    }
}
