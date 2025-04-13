package com.kenddie.librarydemo.entities.lib;

/**
 * Supported languages for library items.
 */
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

    /**
     * Returns a full name of the language in English.
     *
     * @return language display name
     */
    public String getLanguageName() {
        return languageName;
    }
}
