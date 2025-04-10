package com.kenddie.librarydemo.entities.lib;

public enum Languages {
    EN_UK("English (United Kingdom)"),
    SK("Slovak"),
    CZ("Czech"),
    UA("Ukrainian"),
    RU("Russian");

    private final String languageName;

    Languages(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageName() {
        return languageName;
    }
}
