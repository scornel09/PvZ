package com.epf;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.epf.config.ZombieDAOTestConfig;
import com.epf.dao.ZombieDAO;
import com.epf.model.Zombie;

@SpringJUnitConfig(ZombieDAOTestConfig.class)
public class ZombieDAOImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ZombieDAO zombieDAO;

    @BeforeEach
    void setUp() throws SQLException {
        // Clear and reinitialize test data
        jdbcTemplate.update("DELETE FROM zombie");
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("data.sql"));
    }

    @Test
    void create_ShouldReturnCreatedZombie() {
        // Arrange
        Zombie zombie = new Zombie();
        zombie.setNom("Test Zombie");
        zombie.setPointDeVie(100);
        zombie.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie.setDegatAttaque(20);
        zombie.setVitesseDeDeplacement(1.5f);
        zombie.setCheminImage("test.png");
        zombie.setIdMap(1);

        // Act
        Zombie createdZombie = zombieDAO.create(zombie);

        // Assert
        assertNotNull(createdZombie);
        assertEquals(zombie.getNom(), createdZombie.getNom());
        assertEquals(zombie.getPointDeVie(), createdZombie.getPointDeVie());
        assertEquals(zombie.getAttaqueParSeconde(), createdZombie.getAttaqueParSeconde());
        assertEquals(zombie.getDegatAttaque(), createdZombie.getDegatAttaque());
        assertEquals(zombie.getVitesseDeDeplacement(), createdZombie.getVitesseDeDeplacement());
        assertEquals(zombie.getCheminImage(), createdZombie.getCheminImage());
        assertEquals(zombie.getIdMap(), createdZombie.getIdMap());
    }

    @Test
    void read_WhenZombieExists_ShouldReturnZombie() {
        // Arrange
        Zombie zombie = new Zombie();
        zombie.setNom("Test Zombie");
        zombie.setPointDeVie(100);
        zombie.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie.setDegatAttaque(20);
        zombie.setVitesseDeDeplacement(1.5f);
        zombie.setCheminImage("test.png");
        zombie.setIdMap(1);

        Zombie createdZombie = zombieDAO.create(zombie);

        // Act
        Zombie foundZombie = zombieDAO.read(createdZombie.getId());

        // Assert
        assertNotNull(foundZombie);
        assertEquals(createdZombie.getId(), foundZombie.getId());
        assertEquals(zombie.getNom(), foundZombie.getNom());
        assertEquals(zombie.getPointDeVie(), foundZombie.getPointDeVie());
        assertEquals(zombie.getAttaqueParSeconde(), foundZombie.getAttaqueParSeconde());
        assertEquals(zombie.getDegatAttaque(), foundZombie.getDegatAttaque());
        assertEquals(zombie.getVitesseDeDeplacement(), foundZombie.getVitesseDeDeplacement());
        assertEquals(zombie.getCheminImage(), foundZombie.getCheminImage());
        assertEquals(zombie.getIdMap(), foundZombie.getIdMap());
    }

    @Test
    void read_WhenZombieDoesNotExist_ShouldReturnNull() {
        // Act
        Zombie foundZombie = zombieDAO.read(999);

        // Assert
        assertNull(foundZombie);
    }

    @Test
    void update_ShouldReturnUpdatedZombie() {
        // Arrange
        Zombie zombie = new Zombie();
        zombie.setNom("Test Zombie");
        zombie.setPointDeVie(100);
        zombie.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie.setDegatAttaque(20);
        zombie.setVitesseDeDeplacement(1.5f);
        zombie.setCheminImage("test.png");
        zombie.setIdMap(1);

        Zombie createdZombie = zombieDAO.create(zombie);

        createdZombie.setNom("Updated Zombie");
        createdZombie.setPointDeVie(150);
        createdZombie.setAttaqueParSeconde(new BigDecimal("1.5"));
        createdZombie.setDegatAttaque(30);
        createdZombie.setVitesseDeDeplacement(2.0f);
        createdZombie.setCheminImage("updated.png");
        createdZombie.setIdMap(2);

        // Act
        Zombie updatedZombie = zombieDAO.update(createdZombie);

        // Assert
        assertNotNull(updatedZombie);
        assertEquals(createdZombie.getId(), updatedZombie.getId());
        assertEquals(createdZombie.getNom(), updatedZombie.getNom());
        assertEquals(createdZombie.getPointDeVie(), updatedZombie.getPointDeVie());
        assertEquals(createdZombie.getAttaqueParSeconde(), updatedZombie.getAttaqueParSeconde());
        assertEquals(createdZombie.getDegatAttaque(), updatedZombie.getDegatAttaque());
        assertEquals(createdZombie.getVitesseDeDeplacement(), updatedZombie.getVitesseDeDeplacement());
        assertEquals(createdZombie.getCheminImage(), updatedZombie.getCheminImage());
        assertEquals(createdZombie.getIdMap(), updatedZombie.getIdMap());
    }

    @Test
    void delete_ShouldRemoveZombie() {
        // Arrange
        Zombie zombie = new Zombie();
        zombie.setNom("Test Zombie");
        zombie.setPointDeVie(100);
        zombie.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie.setDegatAttaque(20);
        zombie.setVitesseDeDeplacement(1.5f);
        zombie.setCheminImage("test.png");
        zombie.setIdMap(1);

        Zombie createdZombie = zombieDAO.create(zombie);

        // Act
        zombieDAO.delete(createdZombie.getId());

        // Assert
        assertNull(zombieDAO.read(createdZombie.getId()));
    }

    @Test
    void findAll_ShouldReturnListOfZombies() {
        // Arrange
        Zombie zombie1 = new Zombie();
        zombie1.setNom("Zombie 1");
        zombie1.setPointDeVie(100);
        zombie1.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie1.setDegatAttaque(20);
        zombie1.setVitesseDeDeplacement(1.5f);
        zombie1.setCheminImage("zombie1.png");
        zombie1.setIdMap(1);
        zombieDAO.create(zombie1);

        Zombie zombie2 = new Zombie();
        zombie2.setNom("Zombie 2");
        zombie2.setPointDeVie(150);
        zombie2.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombie2.setDegatAttaque(30);
        zombie2.setVitesseDeDeplacement(2.0f);
        zombie2.setCheminImage("zombie2.png");
        zombie2.setIdMap(2);
        zombieDAO.create(zombie2);

        // Act
        List<Zombie> foundZombies = zombieDAO.findAll();

        // Assert
        assertNotNull(foundZombies);
        assertEquals(2, foundZombies.size());
    }

    @Test
    void findByMapId_ShouldReturnListOfZombies() {
        // Arrange
        Zombie zombie1 = new Zombie();
        zombie1.setNom("Zombie 1");
        zombie1.setPointDeVie(100);
        zombie1.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie1.setDegatAttaque(20);
        zombie1.setVitesseDeDeplacement(1.5f);
        zombie1.setCheminImage("zombie1.png");
        zombie1.setIdMap(1);
        zombieDAO.create(zombie1);

        Zombie zombie2 = new Zombie();
        zombie2.setNom("Zombie 2");
        zombie2.setPointDeVie(150);
        zombie2.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombie2.setDegatAttaque(30);
        zombie2.setVitesseDeDeplacement(2.0f);
        zombie2.setCheminImage("zombie2.png");
        zombie2.setIdMap(1);
        zombieDAO.create(zombie2);

        // Act
        List<Zombie> foundZombies = zombieDAO.findByMapId(1);

        // Assert
        assertNotNull(foundZombies);
        assertEquals(2, foundZombies.size());
    }
}
