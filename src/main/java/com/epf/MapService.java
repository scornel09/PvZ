package com.epf;

import java.util.List;

public interface MapService {
    void createMap(Map map);
    Map getMapById(int id);
    void updateMap(Map map);
    void deleteMap(int id);
    List<Map> getAllMaps();
}
