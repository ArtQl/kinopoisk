package ru.artq.practice.kinopoisk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse  {
    private final String error;
    private final String description;
}
