package Classes;

import Abilities.Rage;
import Characters.Warrior;
import Utils.CombatManager;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testHeroDiesBeforeGoal() {
        // Arrange
        Hero hero = new Hero("TestHero", 10, 24, 8, 10, 0, 0, null);
        Enemy enemy = new Enemy("StrongEnemy", 50, 30, 8, 15);

        // Act
        while (!hero.isDead() && !enemy.isDead()) {
            CombatManager.handleDuel(hero, enemy);
        }

        // Assert
        assertTrue(hero.isDead(), "Hero should be dead");
    }

    @Test
    void testEnemyAttackHero() {
        // Arrange
        Character enemy = new Enemy("Enemy", 50, 15, 0,7); // Attaque 15, Défense 0
        Character hero = new Hero("Hero", 40, 10, 5,6,9,3, null);    // PV 40, Défense 5

        // Act
        enemy.attack(hero);

        // Assert
        int expectedHp = 40 - (15 - 5); // Dégâts infligés = attaque - défense
        assertEquals(expectedHp, hero.getHp(), "Les points de vie du héros après l'attaque sont incorrects.");
    }

    @Test
    void testHeroStartsOnMap() {
        Hero hero = new Hero("TestHero", 50, 24, 8, 10, 0, 0, null);
        Map map = new Map(5, 5);
        map.setHero(hero, 0, 0);

        assertEquals(hero, map.getMap()[0][0], "Hero should be at position (0,0)");
    }

    @Test
    void testEnemyPlacementOnMap() {
        Map map = new Map(5, 5);
        Enemy enemy = new Enemy("Enemy", 10, 20, 8, 5);
        map.setEnemy(enemy, 1, 1);

        assertEquals(enemy, map.getMap()[1][1], "Enemy should be at position (1,1)");
    }

    @Test
    void testHeroNormalAttack() {
        // Arrange
        Hero hero = new Hero("TestHero", 25, 20, 5, 10, 0, 0, null); // Attaque = 20
        Enemy enemy = new Enemy("TestEnemy", 20, 15, 5, 5);    // Ennemi avec 20 HP

        // Simuler l'entrée de l'utilisateur pour choisir l'attaque normale
        String simulatedInput = "1\n"; // Choix de l'attaque normale
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        // Act
        hero.attack(enemy);  // L'attaque normale doit être effectuée

        // Assert
        int expectedHp = 20 - (20 - 5); // Dégâts infligés = attaque - défense
        assertEquals(expectedHp, enemy.getHp(), "Les points de vie de l'ennemi après l'attaque sont incorrects.");
    }


    @Test
    void testHeroSpecialAttack() {
        // Arrange
        Warrior hero = new Warrior();
        Enemy enemy = new Enemy("TestEnemy", 20, 10, 5, 5);    // Ennemi avec 20 HP

        // Simuler l'entrée de l'utilisateur pour choisir l'attaque spéciale
        String simulatedInput = "2\n"; // Choix de la capacité spéciale
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        // Act
        hero.attack(enemy);  // La capacité spéciale doit être activée


        // Assert
        // Vérifier que l'ennemi a été affecté par l'attaque (puisque la logique d'activation spéciale n'est pas implémentée, on pourrait juste vérifier l'état de l'ennemi)
        assertEquals(12, enemy.getHp(), "Les points de vie de l'ennemi doivent etre descendu de 8 (5 de capacité spéciale qui annule les 5 de défense puis 8 d'attaque)");
    }

}
