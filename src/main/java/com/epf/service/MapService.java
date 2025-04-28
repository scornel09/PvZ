package com.epf.service;

import java.util.List;

import com.epf.model.Map;

public interface MapService {

    Map createMap(Map map);

    Map getMapById(int id);

    Map updateMap(Map map);

    void deleteMap(int id);

    List<Map> getAllMaps();
}
