package com.epf;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PlanteDAOImpl implements PlanteDAO {

    private final JdbcTemplate jdbcTemplate;

    public PlanteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Plante> planteRowMapper = (rs, rowNum) ->
            new Plante(rs.getInt("id"), rs.getString("name"), rs.getInt("damage"), rs.getInt("health"), rs.getFloat("atkSpeed"), rs.getInt("cost"), rs.getFloat("sunPerSecond"), SlowType.valueOf(rs.getString("slowType")));

    @Override
    public void create(Plante plante) {
        String sql = "INSERT INTO plantes (name, damage) VALUES (?, ?)";
        jdbcTemplate.update(sql, plante.getName(), plante.getDamage(), plante.getHealth(), plante.getAtkSpeed(), plante.getCost(), plante.getSunPerSecond());
    }

    @Override
    public Plante read(int id) {
        String sql = "SELECT * FROM plantes WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, planteRowMapper, id);
    }

    @Override
    public void update(Plante plante) {
        String sql = "UPDATE plantes SET name = ?, damage = ?, health = ? WHERE id = ?";
        jdbcTemplate.update(sql, plante.getName(), plante.getDamage(), plante.getHealth(), plante.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM plantes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Plante> findAll() {
        String sql = "SELECT * FROM plantes";
        return jdbcTemplate.query(sql, planteRowMapper);
    }
}

