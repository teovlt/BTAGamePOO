package org.example;

import Classes.Difficulty;
import Classes.Enemy;
import Classes.Hero;
import Classes.Map;
import Utils.CombatManager;
import Utils.EnemyFactory;

import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Enemy> enemies; // Liste des ennemis générés
    private Map map; // Carte du jeu
    private Hero hero; // Joueur

    public static void main(String[] args) {
        credits();
        Game game = new Game();
        game.askPlayerName();
        game.chooseDifficulty();
        game.placeEnemiesOnMap();
        game.start();
    }

    /**
     * Affiche les crédits du jeu.
     */
    private static void credits() {
        System.out.println("\n");
        System.out.println("----------------------------------------");
        System.out.println("|                                      |");
        System.out.println("|  Jeu créé par Amanie, Chayma et Teo  |");
        System.out.println("|                                      |");
        System.out.println("----------------------------------------");
        System.out.println("\n");
    }

    /**
     * Demande au joueur d'entrer son nom et initialise le héros.
     */
    private void askPlayerName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer votre nom : ");
        String playerName = sc.nextLine();
        hero = new Hero(playerName, 50, 24, 8, 10, 0, 0);
        System.out.println("Bienvenue " + hero.getName() + "!");
    }

    /**
     * Permet au joueur de choisir un niveau de difficulté, configure la carte
     * et génère les ennemis en fonction de ce choix.
     */
    private void chooseDifficulty() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez le niveau de difficulté : \n" +
                "1. Facile (Carte petite, peu d'ennemis)\n" +
                "2. Moyen (Carte moyenne, ennemis modérés)\n" +
                "3. Difficile (Grande carte, beaucoup d'ennemis)");

        int difficultyChoice = -1;
        Difficulty difficulty = null;

        while (difficulty == null) {
            System.out.print("Votre choix (1, 2 ou 3) : ");
            difficultyChoice = sc.nextInt();

            switch (difficultyChoice) {
                case 1:
                    difficulty = Difficulty.EASY;
                    break;
                case 2:
                    difficulty = Difficulty.MEDIUM;
                    break;
                case 3:
                    difficulty = Difficulty.HARD;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }

        this.map = new Map(difficulty.getMapSize(), difficulty.getMapSize());
        this.enemies = EnemyFactory.generateEnemyGroup(difficulty.getEnemyCount());

        System.out.println("Vous avez choisi la difficulté : " + difficulty.name());
        System.out.println("Votre but est maintenant de tuer tous les ennemis pour gagner ! \n");
        System.out.println("Bonne chance ;)");
    }

    /**
     * Place les ennemis sur des positions aléatoires sur la carte.
     */
    private void placeEnemiesOnMap() {
        int heroStartRow = 0; // Ligne de départ du héros
        int heroStartColumn = 0; // Colonne de départ du héros

        for (Enemy enemy : enemies) {
            int row, column;
            do {
                row = (int) (Math.random() * map.getRows());
                column = (int) (Math.random() * map.getColumns());
            } while (
                    map.getMap()[row][column] != null || // Vérifier que la case est vide
                            (row == heroStartRow && column == heroStartColumn) // Vérifier qu'on n'est pas sur la case de départ
            );

            map.setEnemy(enemy, row, column);
        }
    }

    /**
     * Démarre la boucle principale du jeu. Gère les déplacements du joueur
     * et les interactions avec les ennemis.
     */
    private void start() {
        Scanner scanner = new Scanner(System.in);
        map.setHero(hero, 0, 0); // Position de départ du héros
        map.printMap();

        while (true) {
            System.out.println("Utilisez les touches (Z/Q/S/D) pour déplacer le personnage (" + hero.getName().charAt(0) + ") :");
            String input = scanner.nextLine().toUpperCase();

            int[] newCoordinates = {hero.getX(), hero.getY()};

            switch (input) {
                case "Z": // Haut
                    newCoordinates[0]--;
                    break;
                case "S": // Bas
                    newCoordinates[0]++;
                    break;
                case "Q": // Gauche
                    newCoordinates[1]--;
                    break;
                case "D": // Droite
                    newCoordinates[1]++;
                    break;
                default:
                    System.out.println("Commande invalide !");
                    continue;
            }

            if (!isValidPosition(newCoordinates)) {
                System.out.println("Vous ne pouvez pas sortir de la carte !");
                continue;
            }

            map.clearHero(hero.getX(), hero.getY());
            hero.setX(newCoordinates[0]);
            hero.setY(newCoordinates[1]);
            map.setHero(hero, hero.getX(), hero.getY());

            Enemy enemy = map.getEnemy(hero.getX(), hero.getY());
            if (enemy != null) {
                System.out.println("Un ennemi apparaît : " + enemy.getName() + " !");
                CombatManager.handleDuel(hero, enemy);
                if (hero.isDead()) {
                    System.out.println("GAME OVER !!");
                    break;
                }

                map.clearEnemy(hero.getX(), hero.getY());
                enemies.remove(enemy);
                System.out.println("Ennemi vaincu !");
                map.setHero(hero, hero.getX(), hero.getY());
            }
            map.printMap();

            if (enemies.isEmpty()) {
                System.out.println("Félicitations ! Vous avez vaincu tous les ennemis !");
                break;
            }
        }
    }

    /**
     * Vérifie si une position donnée est valide sur la carte.
     *
     * @param position Tableau contenant les coordonnées [ligne, colonne]
     * @return true si la position est dans les limites, sinon false
     */
    private boolean isValidPosition(int[] position) {
        return position[0] >= 0 && position[0] < map.getRows() &&
                position[1] >= 0 && position[1] < map.getColumns();
    }
}
