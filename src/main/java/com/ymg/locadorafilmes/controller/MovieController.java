package com.ymg.locadorafilmes.controller;

import java.util.List;

import javax.validation.Valid;

import com.ymg.locadorafilmes.model.Movie;
import com.ymg.locadorafilmes.model.Situation;
import com.ymg.locadorafilmes.service.IMovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class MovieController {

    @Autowired
    public IMovieService movieService;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public @ResponseBody List<Movie> findAll() {
        return movieService.findAllBySituation(Situation.ACTIVE);

    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public @ResponseBody Movie findById(@PathVariable String id) {
        return movieService.findById(Long.parseLong(id));

    }

    @PostMapping(value = "/movies/create", consumes = "application/json", produces = "application/json")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        if(movie.getAmount() == null){
            movie.setAmount(1);
        }
        return movieService.saveMovie(movie);
    }

    @PutMapping(value = "/movies/update", consumes = "application/json", produces = "application/json")
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }

    @PutMapping(value = "/movies/delete", consumes = "application/json", produces = "application/json")
    public @ResponseBody String deteleMovie(@RequestBody Movie movie) {

        try {
            movieService.deleteMovie(movie);
        } catch (Error err) {
            return err.getMessage();
        }
        return "Movie has been deleted";
    }

}
