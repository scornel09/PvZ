package com.epf;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ZombieDAOImpl implements ZombieDAO {

    private final JdbcTemplate jdbcTemplate;

    public ZombieDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Zombie> zombieRowMapper = (rs, rowNum)
            -> new Zombie(rs.getInt("id"), rs.getString("name"), rs.getInt("health"), rs.getFloat("atkSpeed"), rs.getFloat("mvtSpeed"), rs.getInt("map_id"));

    @Override
    public void create(Zombie zombie) {
        String sql = "INSERT INTO zombies (name, health, map_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, zombie.getName(), zombie.getHealth(), zombie.getMapId());
    }

    @Override
    public Zombie read(int id) {
        String sql = "SELECT * FROM zombies WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, zombieRowMapper, id);
    }

    @Override
    public void update(Zombie zombie) {
        String sql = "UPDATE zombies SET name = ?, health = ?, atkSpeed = ?, mvtSpeed = ?, map_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, zombie.getName(), zombie.getHealth(), zombie.getAtkSpeed(), zombie.getMvtSpeed(), zombie.getMapId(), zombie.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM zombies WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Zombie> findAll() {
        String sql = "SELECT * FROM zombies";
        return jdbcTemplate.query(sql, zombieRowMapper);
    }

    @Override
    public List<Zombie> findByMapId(int mapId) {
        String sql = "SELECT * FROM zombies WHERE map_id = ?";
        return jdbcTemplate.query(sql, zombieRowMapper, mapId);
    }
}
