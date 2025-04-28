package com.epf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.dao.MapDAO;
import com.epf.model.Map;

@Service
public class MapServiceImpl implements MapService {

    private final MapDAO mapDAO;

    @Autowired
    public MapServiceImpl(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    @Override
    public Map createMap(Map map) {
        return mapDAO.create(map);
    }

    @Override
    public Map getMapById(int id) {
        return mapDAO.read(id);
    }

    @Override
    public Map updateMap(Map map) {
        return mapDAO.update(map);
    }

    @Override
    public void deleteMap(int id) {
        mapDAO.delete(id);
    }

    @Override
    public List<Map> getAllMaps() {
        return mapDAO.findAll();
    }
}
