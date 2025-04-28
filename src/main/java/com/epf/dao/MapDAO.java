package com.epf.dao;

import java.util.List;

import com.epf.model.Map;

public interface MapDAO {

    Map create(Map map);

    Map read(int id);

    Map update(Map map);

    void delete(int id);

    List<Map> findAll();
}
