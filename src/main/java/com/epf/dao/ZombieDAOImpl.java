package com.epf.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epf.model.Zombie;

@Repository
public class ZombieDAOImpl implements ZombieDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ZombieDAOImpl.class);

    @Autowired
    public ZombieDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Zombie> getRowMapper() {
        return (rs, rowNum) -> {
            Zombie zombie = new Zombie();
            zombie.setId(rs.getInt("id_zombie"));
            zombie.setNom(rs.getString("nom"));
            zombie.setPointDeVie(rs.getInt("point_de_vie"));
            zombie.setVitesseDeDeplacement(rs.getFloat("vitesse_de_deplacement"));
            zombie.setDegatAttaque(rs.getInt("degat_attaque"));
            zombie.setCheminImage(rs.getString("chemin_image"));
            zombie.setAttaqueParSeconde(rs.getBigDecimal("attaque_par_seconde"));
            zombie.setIdMap(rs.getInt("id_map"));
            return zombie;
        };
    }

    @Override
    public Zombie create(Zombie zombie) {
        try {
            String sql = "INSERT INTO zombie (nom, point_de_vie, vitesse_de_deplacement, degat_attaque, chemin_image, attaque_par_seconde, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, zombie.getNom());
                ps.setInt(2, zombie.getPointDeVie());
                ps.setFloat(3, zombie.getVitesseDeDeplacement());
                ps.setInt(4, zombie.getDegatAttaque());
                ps.setString(5, zombie.getCheminImage());
                ps.setBigDecimal(6, zombie.getAttaqueParSeconde());
                if (zombie.getIdMap() != 0) {
                    ps.setInt(7, zombie.getIdMap());
                } else {
                    ps.setNull(7, java.sql.Types.INTEGER);
                }
                return ps;
            }, keyHolder);

            if (keyHolder.getKey() != null) {
                zombie.setId(keyHolder.getKey().intValue());
            }
            return zombie;
        } catch (Exception e) {
            logger.error("Erreur lors de la création du zombie", e);
            throw new RuntimeException("Erreur lors de la création du zombie", e);
        }
    }

    @Override
    public Zombie read(int id) {
        try {
            String sql = "SELECT * FROM zombie WHERE id_zombie = ?";
            return jdbcTemplate.queryForObject(sql, getRowMapper(), id);
        } catch (Exception e) {
            logger.error("Erreur lors de la lecture du zombie", e);
            throw new RuntimeException("Erreur lors de la lecture du zombie", e);
        }
    }

    @Override
    public Zombie update(Zombie zombie) {
        try {
            String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, vitesse_de_deplacement = ?, degat_attaque = ?, chemin_image = ?, attaque_par_seconde = ?, id_map = ? WHERE id_zombie = ?";
            jdbcTemplate.update(sql,
                    zombie.getNom(),
                    zombie.getPointDeVie(),
                    zombie.getVitesseDeDeplacement(),
                    zombie.getDegatAttaque(),
                    zombie.getCheminImage(),
                    zombie.getAttaqueParSeconde(),
                    zombie.getIdMap(),
                    zombie.getId()
            );
            return zombie;
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour du zombie", e);
            throw new RuntimeException("Erreur lors de la mise à jour du zombie", e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM zombie WHERE id_zombie = ?";
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression du zombie", e);
            throw new RuntimeException("Erreur lors de la suppression du zombie", e);
        }
    }

    @Override
    public List<Zombie> findAll() {
        String sql = "SELECT * FROM zombie";
        return jdbcTemplate.query(sql, getRowMapper());
    }

    @Override
    public List<Zombie> findByMapId(int mapId) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        return jdbcTemplate.query(sql, getRowMapper(), mapId);
    }
}
