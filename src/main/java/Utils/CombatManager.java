package Utils;

import Classes.Enemy;
import Classes.Hero;

import java.util.List;
import java.util.Comparator;

public class CombatManager {

    /**
     * Gère un combat entre un héros et un groupe d'ennemis.
     *
     * Cette méthode exécute une boucle principale de combat jusqu'à ce que
     * soit le héros soit mort, soit tous les ennemis aient été vaincus.
     * Les entités (héros et ennemis) attaquent en fonction de leur vitesse.
     *
     * @param hero    le héros qui combat
     * @param enemies la liste des ennemis
     */
    public static void handleCombat(Hero hero, List<Enemy> enemies) {
        System.out.println("Un combat commence !");

        while (!hero.isDead() && !enemies.isEmpty()) {
            // Tri des ennemis en fonction de leur vitesse décroissante
            enemies.sort(Comparator.comparingInt(Enemy::getSpeed).reversed());

            // Boucle à travers les ennemis
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);

                if (hero.getSpeed() >= enemy.getSpeed()) {
                    // Le héros attaque en premier
                    System.out.println(hero.getName() + " attaque " + enemy.getName() + " !");
                    hero.attack(enemy);

                    if (enemy.isDead()) {
                        System.out.println(enemy.getName() + " est vaincu !");
                        enemies.remove(i);
                        i--; // Ajuste l'index pour éviter de sauter un ennemi
                        continue;
                    }

                    // L'ennemi riposte
                    System.out.println(enemy.getName() + " riposte !");
                    enemy.attack(hero);
                } else {
                    // L'ennemi attaque en premier
                    System.out.println(enemy.getName() + " attaque " + hero.getName() + " !");
                    enemy.attack(hero);

                    if (hero.isDead()) {
                        System.out.println("Le héros a été vaincu. Partie terminée.");
                        return;
                    }

                    // Le héros riposte
                    System.out.println(hero.getName() + " riposte !");
                    hero.attack(enemy);

                    if (enemy.isDead()) {
                        System.out.println(enemy.getName() + " est vaincu !");
                        enemies.remove(i);
                        i--; // Ajuste l'index pour continuer correctement la boucle
                    }
                }
            }
        }

        // Résultat final du combat
        if (hero.isDead()) {
            System.out.println("Le héros a été vaincu. Partie terminée.");
        } else {
            System.out.println("Tous les ennemis ont été vaincus !");
        }
    }

    /**
     * Gère un duel entre un héros et un ennemi.
     *
     * Le duel se termine lorsque soit le héros, soit l'ennemi est vaincu.
     * Les attaques sont basées sur la vitesse des participants, celui ayant
     * la vitesse la plus élevée attaquant en premier.
     *
     * @param hero  le héros
     * @param enemy l'ennemi
     */
    public static void handleDuel(Hero hero, Enemy enemy) {
        System.out.println("Le duel commence entre " + hero.getName() + " (" + hero.getHp() + ") et " + enemy.getName() + " (" + enemy.getHp() + ") !");

        while (!hero.isDead() && !enemy.isDead()) {
            if (hero.getSpeed() >= enemy.getSpeed()) {
                // Le héros attaque en premier
                System.out.println(hero.getName() + " attaque " + enemy.getName() + " !");
                hero.attack(enemy);

                if (enemy.isDead()) {
                    System.out.println(enemy.getName() + " est vaincu !");
                    break;
                }

                // L'ennemi riposte
                System.out.println(enemy.getName() + " riposte !");
                enemy.attack(hero);

                if (hero.isDead()) {
                    System.out.println(hero.getName() + " est vaincu, vous battez en retraite...");
                    break;
                }
            } else {
                // L'ennemi attaque en premier
                System.out.println(enemy.getName() + " attaque " + hero.getName() + " !");
                enemy.attack(hero);

                if (hero.isDead()) {
                    System.out.println(hero.getName() + " a été vaincu...");
                    break;
                }

                // Le héros riposte
                System.out.println(hero.getName() + " riposte !");
                hero.attack(enemy);

                if (enemy.isDead()) {
                    System.out.println(enemy.getName() + " est vaincu !");
                }
            }
        }
    }
}
