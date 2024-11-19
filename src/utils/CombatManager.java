package src.utils;

import src.Classes.Character;
import src.Classes.Enemy;
import src.Classes.Hero;

import java.util.List;

public class CombatManager {

    /**
     * Gère le combat entre un héros et un groupe d'ennemis.
     *
     * @param hero    le héros qui combat
     * @param enemies la liste des ennemis
     */
    public static void handleCombat(Character hero, List<Enemy> enemies) {
        System.out.println("Un combat commence !");
        while (!hero.isDead() && !enemies.isEmpty()) {

            // Personnage le plus rapide attaque en premier
            // Si la cible est morte stop
            // Sinon cible attaque ensuie


            if (hero.isDead()) {
                System.out.println("Le héros a été vaincu. Partie terminée.");
            } else {
                System.out.println("Tous les ennemis ont été vaincus !");
            }
        }
    }

    /**
     * Gère un duel entre un héros et un ennemi.
     *
     * @param hero  le héros
     * @param enemy l'ennemi
     */
    public static void handleDuel(Hero hero, Enemy enemy) {
        System.out.println("Le duel commence entre " + hero.getName() + " et " + enemy.getName() + " !");
        System.out.println(hero);
        System.out.println(enemy);

        // Boucle de combat
        while (!hero.isDead() && !enemy.isDead()) {
           //Same



            // Vérifie si le héros est mort
            if (hero.isDead()) {
                System.out.println(hero.getName() + " a été vaincu...");
            }
        }
    }
}
