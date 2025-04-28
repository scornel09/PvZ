package com.epf.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epf.model.Map;

@Repository
public class MapDAOImpl implements MapDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MapDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Map> MAP_ROW_MAPPER = (rs, rowNum) -> {
        Map map = new Map();
        map.setId(rs.getInt("id_map"));
        map.setLigne(rs.getInt("ligne"));
        map.setColonne(rs.getInt("colonne"));
        map.setCheminImage(rs.getString("chemin_image"));
        return map;
    };

    @Override
    public Map create(Map map) {
        String sql = "INSERT INTO map(id, ligne, colonne, chemin_image) VALUES (NULL, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, map.getLigne());
            ps.setInt(2, map.getColonne());
            ps.setString(3, map.getCheminImage());
            return ps;
        }, keyHolder);
        map.setId(keyHolder.getKey().intValue());
        return map;
    }

    @Override
    public Map read(int id) {
        try {
            String sql = "SELECT * FROM map WHERE id_map = ?";
            return jdbcTemplate.queryForObject(sql, MAP_ROW_MAPPER, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Map update(Map map) {
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getCheminImage(), map.getId());
        return map;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Map> findAll() {
        String sql = "SELECT * FROM map";
        return jdbcTemplate.query(sql, MAP_ROW_MAPPER);
    }
}
