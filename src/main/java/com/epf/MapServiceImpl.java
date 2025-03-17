package com.epf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    private final MapDAO mapDAO;

    @Autowired
    public MapServiceImpl(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    @Override
    public void createMap(Map map) {
        mapDAO.create(map);
    }

    @Override
    public Map getMapById(int id) {
        return mapDAO.read(id);
    }

    @Override
    public void updateMap(Map map) {
        mapDAO.update(map);
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

