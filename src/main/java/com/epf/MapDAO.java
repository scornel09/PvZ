package com.epf;

import java.util.List;

public interface MapDAO {
    void create(Map map);
    Map read(int id);
    void update(Map map);
    void delete(int id);
    List<Map> findAll();
}
