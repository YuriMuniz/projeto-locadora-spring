package com.ymg.locadorafilmes.service;
import com.ymg.locadorafilmes.model.Movie;
import com.ymg.locadorafilmes.model.Situation;
import com.ymg.locadorafilmes.repository.IMovieRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService implements IMovieService {
    @Autowired
	public IMovieRepository movieRepository;


    @Override
	public Movie saveMovie(Movie movie) {
		movie.setSituation(Situation.ACTIVE);
		movie.setAmountRented(0);
		return movieRepository.save(movie);
	}

    @Override
	public Movie updateMovie(Movie movie) {
		return movieRepository.saveAndFlush(movie);
	}

    @Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

    @Override
	public void deleteMovie(Movie movie) {
		movie.setSituation(Situation.INATIVE);
		movieRepository.saveAndFlush(movie);
	}

	@Override
	public Movie findById(Long id) {
		return movieRepository.getById(id);
	}

	@Override
	public List<Movie> findAllBySituation(Situation situation) {
		return movieRepository.findBySituation(situation);
	}

}
