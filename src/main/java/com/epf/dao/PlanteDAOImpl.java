package com.epf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epf.model.Plante;
import com.epf.model.Plante.Effet;

@Repository
public class PlanteDAOImpl implements PlanteDAO {

    private final JdbcTemplate jdbcTemplate;

    public PlanteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Plante> planteRowMapper = (ResultSet rs, int rowNum) -> {
        Plante plante = new Plante();
        plante.setId(rs.getInt("id_plante"));
        plante.setNom(rs.getString("nom"));
        plante.setPointDeVie(rs.getInt("point_de_vie"));
        plante.setTempsRecharge(rs.getFloat("temps_recharge"));
        plante.setDegatAttaque(rs.getInt("degat_attaque"));
        plante.setCout(rs.getInt("cout"));
        plante.setSoleilParSeconde(rs.getBigDecimal("soleil_par_seconde").floatValue());

        String effetStr = rs.getString("effet");
        if (effetStr != null) {
            plante.setEffet(Effet.valueOf(effetStr));
        } else {
            plante.setEffet(Effet.NORMAL);
        }

        plante.setCheminImage(rs.getString("chemin_image"));
        plante.setAttaqueParSeconde(rs.getFloat("attaque_par_seconde"));
        return plante;
    };

    @Override
    public Plante create(Plante plante) {
        System.out.println("=== Début de la création d'une plante dans le DAO ===");
        System.out.println("Données reçues : " + plante);

        String sql = "INSERT INTO plante (nom, point_de_vie, temps_recharge, degat_attaque, cout, soleil_par_seconde, effet, chemin_image, attaque_par_seconde) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("SQL : " + sql);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                System.out.println("Préparation des paramètres :");
                System.out.println("1. nom: " + plante.getNom());
                System.out.println("2. point_de_vie: " + plante.getPointDeVie());
                System.out.println("3. temps_recharge: " + plante.getTempsRecharge());
                System.out.println("4. degat_attaque: " + plante.getDegatAttaque());
                System.out.println("5. cout: " + plante.getCout());
                System.out.println("6. soleil_par_seconde: " + plante.getSoleilParSeconde());
                System.out.println("7. effet: " + plante.getEffet().name());
                System.out.println("8. chemin_image: " + plante.getCheminImage());
                System.out.println("9. attaque_par_seconde: " + plante.getAttaqueParSeconde());

                ps.setString(1, plante.getNom());
                ps.setInt(2, plante.getPointDeVie());
                ps.setFloat(3, plante.getTempsRecharge());
                ps.setInt(4, plante.getDegatAttaque());
                ps.setInt(5, plante.getCout());
                ps.setBigDecimal(6, new java.math.BigDecimal(plante.getSoleilParSeconde()));
                ps.setString(7, plante.getEffet().name());
                ps.setString(8, plante.getCheminImage());
                ps.setFloat(9, plante.getAttaqueParSeconde());
                return ps;
            }, keyHolder);

            int generatedId = keyHolder.getKey().intValue();
            System.out.println("ID généré : " + generatedId);
            plante.setId(generatedId);

            System.out.println("=== Fin de la création d'une plante dans le DAO ===");
            return plante;
        } catch (Exception e) {
            System.out.println("ERREUR lors de la création de la plante dans le DAO : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Plante read(int id) {
        String sql = "SELECT * FROM plante WHERE id_plante = ?";
        try {
            return jdbcTemplate.queryForObject(sql, planteRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Plante update(Plante plante) {
        String sql = "UPDATE plante SET nom = ?, point_de_vie = ?, temps_recharge = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ?, attaque_par_seconde = ? WHERE id_plante = ?";
        jdbcTemplate.update(sql,
                plante.getNom(),
                plante.getPointDeVie(),
                plante.getTempsRecharge(),
                plante.getDegatAttaque(),
                plante.getCout(),
                new java.math.BigDecimal(plante.getSoleilParSeconde()),
                plante.getEffet().name(),
                plante.getCheminImage(),
                plante.getAttaqueParSeconde(),
                plante.getId()
        );
        return plante;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM plante WHERE id_plante = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Plante> findAll() {
        String sql = "SELECT * FROM plante";
        return jdbcTemplate.query(sql, planteRowMapper);
    }
}
