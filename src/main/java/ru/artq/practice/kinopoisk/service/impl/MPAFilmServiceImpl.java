package ru.artq.practice.kinopoisk.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.artq.practice.kinopoisk.model.MPA;
import ru.artq.practice.kinopoisk.service.MPAFilmService;
import ru.artq.practice.kinopoisk.storage.FilmStorage;
import ru.artq.practice.kinopoisk.storage.MPAFilmStorage;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MPAFilmServiceImpl implements MPAFilmService {
    private final MPAFilmStorage mpaFilmStorage;
    private final FilmStorage filmStorage;

    @Override
    public Collection<String> getAllMPAFilm(Integer filmId) {
        filmStorage.getFilm(filmId);
        return mpaFilmStorage.getAllMPAFilm(filmId).stream().map(MPA::name).toList();
    }

    @Override
    public Boolean addMPAToFilm(Integer filmId, String mpa) {
        filmStorage.getFilm(filmId);
        return mpaFilmStorage.addMPAToFilm(filmId, validateMPA(mpa));
    }

    @Override
    public Boolean removeMPAFromFilm(Integer filmId, String mpa) {
        filmStorage.getFilm(filmId);
        return mpaFilmStorage.removeMPAFromFilm(filmId, validateMPA(mpa));
    }

    private MPA validateMPA(String mpa) {
        MPA mpaEnum;
        if (mpa == null)
            throw new IllegalArgumentException("MPA cannot be null");
        try {
            mpaEnum = MPA.valueOf(mpa.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("MPA is not correct", e);
        }
        return mpaEnum;
    }
}