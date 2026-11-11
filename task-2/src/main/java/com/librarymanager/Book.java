package com.librarymanager;

public record Book(
        Long id,
        String title,
        String author,
        int year,
        boolean available
) {
}