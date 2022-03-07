package com.ymg.locadorafilmes.service;
import com.ymg.locadorafilmes.model.History;

import com.ymg.locadorafilmes.repository.IHistoryRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HistoryService implements IHistoryService {
    @Autowired
	public IHistoryRepository historyRepository;


    @Override
	public History saveHistory(History history) {
		return historyRepository.save(history);
	}

    @Override
	public History updateHistory(History history) {
		return historyRepository.saveAndFlush(history);
	}

    @Override
	public List<History> findAll() {
		return historyRepository.findAll();
	}

    @Override
	public void deleteHistory(History history) {
		historyRepository.delete(history);
	}

}
