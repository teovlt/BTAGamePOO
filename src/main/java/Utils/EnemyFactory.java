package Utils;

import Classes.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactory {

    private static final Random random = new Random();

    /**
     * Génère un ennemi aléatoire avec des caractéristiques variées.
     *
     * Cette méthode choisit aléatoirement un type d'ennemi parmi plusieurs
     * possibilités et initialise ses caractéristiques (points de vie, attaque, défense, etc.).
     *
     * @return une instance d'ennemi générée aléatoirement
     */
    public static Enemy generateRandomEnemy() {
        // Types d'ennemis et leurs caractéristiques
        String[] enemyTypes = {"Brigand", "Catcheur", "Gangster"};

        // Génération aléatoire du type d'ennemi
        String type = enemyTypes[random.nextInt(enemyTypes.length)];

        switch (type) {
            case "Brigand":
                return new Enemy("Brigand", 50, 10, 5, 5);
            case "Catcheur":
                return new Enemy("Catcheur", 150, 8, 10, 3);
            case "Gangster":
                return new Enemy("Gangster", 40, 12, 4, 7);
            default:
                return new Enemy("Ennemi Inconnu", 30, 10, 2, 4);
        }
    }

    /**
     * Génère un groupe d'ennemis pour un segment de la carte.
     *
     * Cette méthode crée une liste d'ennemis en appelant la méthode
     * {@link #generateRandomEnemy()} un certain nombre de fois,
     * en fonction du paramètre count.
     *
     * @param count le nombre d'ennemis à générer
     * @return une liste d'ennemis générés aléatoirement
     */
    public static List<Enemy> generateEnemyGroup(int count) {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            enemies.add(generateRandomEnemy());
        }
        return enemies;
    }
}
