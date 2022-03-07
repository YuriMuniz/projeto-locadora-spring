package com.ymg.locadorafilmes.service;

import com.ymg.locadorafilmes.model.History;

import java.util.List;
public interface IHistoryService {
    public History saveHistory(History history);
	public History updateHistory(History history);
	public List<History> findAll();
	public void deleteHistory(History history);
}
