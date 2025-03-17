package com.epf;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseTestService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseTestService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void testConnection() {
        try {
            String sql = "SELECT 1";
            Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
            System.out.println("Test réussi, résultat : " + result);
        } catch (Exception e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }
}
