package com.ymg.locadorafilmes.service;

import com.ymg.locadorafilmes.model.Movie;
import com.ymg.locadorafilmes.model.Situation;

import java.util.List;
public interface IMovieService {
    public Movie saveMovie(Movie movie);
	public Movie updateMovie(Movie movie);
	public List<Movie> findAll();
	public void deleteMovie(Movie movie);
	public Movie findById(Long id);
	public List<Movie> findAllBySituation(Situation situation);
	
}
