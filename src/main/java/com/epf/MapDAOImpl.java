package com.epf;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MapDAOImpl implements MapDAO {

    private final JdbcTemplate jdbcTemplate;

    public MapDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Map> mapRowMapper = (rs, rowNum) ->
            new Map(rs.getInt("id"), rs.getInt("ligne"), rs.getInt("colonne"));

    @Override
    public void create(Map map) {
        String sql = "INSERT INTO maps (name, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne());
    }

    @Override
    public Map read(int id) {
        String sql = "SELECT * FROM maps WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, mapRowMapper, id);
    }

    @Override
    public void update(Map map) {
        String sql = "UPDATE maps SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM maps WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Map> findAll() {
        String sql = "SELECT * FROM maps";
        return jdbcTemplate.query(sql, mapRowMapper);
    }
}
