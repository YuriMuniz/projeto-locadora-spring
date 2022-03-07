package com.ymg.locadorafilmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.ymg.locadorafilmes.model.Movie;
import com.ymg.locadorafilmes.model.Situation;

public interface IMovieRepository extends JpaRepository<Movie, Long> {
        List<Movie> findBySituation(Situation situation);
}
