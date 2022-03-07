package com.ymg.locadorafilmes.repository;

import com.ymg.locadorafilmes.model.History;
import com.ymg.locadorafilmes.model.User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUser(User user);
}
