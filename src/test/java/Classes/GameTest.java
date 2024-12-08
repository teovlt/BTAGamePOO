package Classes;

import Utils.CombatManager;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testHeroDiesBeforeGoal() {
        // Arrange
        Hero hero = new Hero("TestHero", 10, 24, 8, 10, 0, 0);
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
        Character hero = new Hero("Hero", 40, 10, 5,6,9,3);    // PV 40, Défense 5

        // Act
        enemy.attack(hero);

        // Assert
        int expectedHp = 40 - (15 - 5); // Dégâts infligés = attaque - défense
        assertEquals(expectedHp, hero.getHp(), "Les points de vie du héros après l'attaque sont incorrects.");
    }

    @Test
    void testHeroStartsOnMap() {
        Hero hero = new Hero("TestHero", 50, 24, 8, 10, 0, 0);
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
        Hero hero = new Hero("TestHero", 25, 20, 5, 10, 0, 0); // Attaque = 20
        Enemy enemy = new Enemy("TestEnemy", 20, 15, 5, 5);    // Ennemi avec 20 HP

        //TODO
    }

    @Test
    void testHeroSpecialAttack() {
        // Arrange
        Hero hero = new Hero("TestHero", 25, 20, 5, 10, 0, 0); // Attaque = 20
        Enemy enemy = new Enemy("TestEnemy", 20, 10, 5, 5);    // Ennemi avec 20 HP

        //TODO
    }

}
