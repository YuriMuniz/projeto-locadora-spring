package com.ymg.locadorafilmes.service;

import com.ymg.locadorafilmes.model.History;
import com.ymg.locadorafilmes.model.Movie;
import com.ymg.locadorafilmes.model.Rental;
import com.ymg.locadorafilmes.model.Situation;
import com.ymg.locadorafilmes.model.User;
import com.ymg.locadorafilmes.repository.IRentalRepository;
import com.ymg.locadorafilmes.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RentalService implements IRentalService {
	@Autowired
	public IRentalRepository rentalRepository;

	@Autowired
	public IUserRepository userRepository;

	@Autowired
	public IMovieService movieService;

	@Autowired
	public IHistoryService historyService;

	@Override
	public Rental saveRental(Rental rental) {
		if (rental.getMovies().size() > 5) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The maximum number of movies per user is 5");
		}

		User user = userRepository.getById(rental.getUser().getId());

		List<Rental> rentalUser = rentalRepository.findByUserAndIsReturned(user, false);

		if (rentalUser.size() > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has an open lease");
		}

		List<Long> unavailable = new ArrayList<Long>();
		for (Movie movie : rental.getMovies()) {
			Movie movieConsulted = movieService.findById(movie.getId());
			if (movieConsulted.getAmount() - movieConsulted.getAmountRented() < 1) {
				unavailable.add(movieConsulted.getId());
			}
		}
		if (unavailable.size() > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"The movie(s) is not available - ID´s : " + unavailable);
		} else {
			for (Movie movie : rental.getMovies()) {
				Movie movieConsulted = movieService.findById(movie.getId());
				if (movieConsulted.getAmount() - movieConsulted.getAmountRented() == 1) {
					movieConsulted.setSituation(Situation.UNAVAILABLE);
				}
				movieConsulted.setAmountRented(movieConsulted.getAmountRented() + 1);
				movieService.updateMovie(movieConsulted);
				History history = new History();
				history.setIsRenewed(false);
				history.setMovie(movieConsulted);
				history.setUser(rental.getUser());
				historyService.saveHistory(history);
			}

			rental.setIsReturned(false);
			rental.setCountRenewed(0);
		}
		return rentalRepository.save(rental);
	}

	@Override
	public Rental updateRental(Rental rental) {
		return rentalRepository.saveAndFlush(rental);
	}

	@Override
	public List<Rental> findAll() {
		return rentalRepository.findAll();
	}

	@Override
	public void deleteRental(Rental rental) {
		rentalRepository.delete(rental);
	}

	@Override
	public Rental findById(Long id) {
		return rentalRepository.getById(id);
	}

	@Override
	public Rental renewedRental(Long id, Date returnDate) {
		Rental rental = rentalRepository.getById(id);
		if (rental.getCountRenewed() == 2) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Renewal limit reached");
		}
		rental.setCountRenewed(rental.getCountRenewed() + 1);
		rental.setIsRenewed(true);
		rental.setReturnDateRenewed(returnDate);

		for (Movie movie : rental.getMovies()) {
			Movie movieConsulted = movieService.findById(movie.getId());
			if (movieConsulted.getAmount() - movieConsulted.getAmountRented() == 1) {
				movieConsulted.setSituation(Situation.UNAVAILABLE);
			}
			movieConsulted.setAmountRented(movieConsulted.getAmountRented() + 1);
			movieService.updateMovie(movieConsulted);
			History history = new History();
			history.setIsRenewed(true);
			history.setMovie(movieConsulted);
			history.setUser(rental.getUser());
			historyService.saveHistory(history);
		}

		return rentalRepository.saveAndFlush(rental);

	}

}
