package com.ymg.locadorafilmes.repository;

import com.ymg.locadorafilmes.model.Profile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileRepository extends JpaRepository<Profile, Long> {
    
}
