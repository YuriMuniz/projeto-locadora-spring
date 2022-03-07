package com.ymg.locadorafilmes.controller;

import java.util.List;

import com.ymg.locadorafilmes.model.History;
import com.ymg.locadorafilmes.service.IHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HistoryController {
    @Autowired
    public IHistoryService historyService;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public @ResponseBody List<History> findAll() {
        return historyService.findAll();
    }
}
