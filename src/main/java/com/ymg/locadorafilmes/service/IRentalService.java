package com.ymg.locadorafilmes.service;

import com.ymg.locadorafilmes.model.Rental;

import java.util.Date;
import java.util.List;

public interface IRentalService {
    public Rental saveRental(Rental rental);
	public Rental updateRental(Rental rental);
	public List<Rental> findAll();
	public void deleteRental(Rental rental);
	public Rental findById(Long id);
	public Rental renewedRental(Long id, Date returnDate);
}
