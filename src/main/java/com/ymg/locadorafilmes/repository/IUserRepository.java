package com.ymg.locadorafilmes.repository;


//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//import java.util.List;

import com.ymg.locadorafilmes.model.User;
import com.ymg.locadorafilmes.model.UserSituation;

public interface IUserRepository extends JpaRepository<User, Long>{
    //List<User> findByName(String name);
    User findByName(String name);
    User findByEmail(String email);
    List<User> findBySituation(UserSituation situation);
    
	
}
