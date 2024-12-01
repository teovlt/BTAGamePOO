package Classes;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testHeroAttackEnemy () {
        // Arrange
        Hero attacker = new Hero("Attacker", 50, 10, 5,4,5,6);
        Enemy target = new Enemy("Target", 30, 8, 4,7);

        // Act
        attacker.attack(target);

        // Assert
        int expectedHp = 30 - 10 ; // dégâts infligés = attaque - défense
        assertEquals(expectedHp, target.getHp(), "HP de la cible après une attaque normale incorrect.");
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

//    @Test
//    void testAttackSpecialAbility() {
//        // Arrange
//        Character attacker = new Hero("Attacker", 50, 10, 5,4,5,6);
//        Character target = new Enemy("Target", 30, 8, 4,3);
//
//        // Simulez un effet spécial en modifiant votre méthode activate() si nécessaire
//        attacker.attack(target);
//
//        // Assert
//        // Ici, ajoutez des assertions spécifiques à ce que "activate" fait.
//        assertTrue(target.getHp() < 30, "HP de la cible n'a pas diminué après capacité spéciale.");
//    }

    @Test
    void testTargetDeath() {
        // Arrange
        Character target = new Hero("Attacker", 50, 20, 5,3,4,7);
        Character attacker = new Enemy("Target", 10, 55, 4,7);

        // Act
        attacker.attack(target);

        // Assert
        assertTrue(target.isDead(), "La cible aurait dû être morte après l'attaque.");
    }

}
