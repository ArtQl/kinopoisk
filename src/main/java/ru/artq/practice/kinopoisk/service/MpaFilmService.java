package ru.artq.practice.kinopoisk.service;

import ru.artq.practice.kinopoisk.model.Film;

import java.util.Set;

public interface MpaFilmService {

    void addMpaToFilm(Film film);

    Set<Integer> getMpaFilm(Integer filmId);
}