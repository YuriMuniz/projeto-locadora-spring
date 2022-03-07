package com.ymg.locadorafilmes.repository;

import java.util.List;

import com.ymg.locadorafilmes.model.Rental;
import com.ymg.locadorafilmes.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserAndIsReturned(User user, boolean isReturned );
}
