package org.example.nazarov.library;

import org.example.nazarov.main.Book;

public class LibraryBook extends Book {
    private int libraryId;
    public void displayInfo() {
        displayTitle();
        displayLibraryId();
    }

    protected void displayLibraryId() {
        System.out.println(libraryId);
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }
}
