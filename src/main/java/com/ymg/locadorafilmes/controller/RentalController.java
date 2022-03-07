package com.ymg.locadorafilmes.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ymg.locadorafilmes.model.Rental;
import com.ymg.locadorafilmes.service.IMovieService;
import com.ymg.locadorafilmes.service.IRentalService;
import com.ymg.locadorafilmes.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {
    @Autowired
    public IRentalService rentalService;

    @Autowired
    public IUserService userService;

    @Autowired
    public IMovieService movieService;

    @PostMapping(value = "/rentals/create", consumes = "application/json", produces = "application/json")
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.saveRental(rental);
    }

    @PostMapping(value = "/rentals/renewed/{id}", consumes = "application/json", produces = "application/json")
    public Rental renewedRental(@PathVariable String id, @RequestBody Map<String, Object> payload)
            throws ParseException {
        String returnDate = payload.get("returnDate").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(returnDate);

        return rentalService.renewedRental(Long.parseLong(id), date);
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public @ResponseBody List<Rental> findAll() {
        return rentalService.findAll();
    }

}
