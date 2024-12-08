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
     * Demande au joueur d'entrer son nom.
     */
    private void askPlayerName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer votre nom : ");
        // Nom du joueur
        String playerName = sc.nextLine();
        hero = new Hero(playerName, 50, 24, 8, 10, 0, 0);
        System.out.println("Bienvenue " + hero.getName());
    }

    /**
     * Lancement du jeu avec choix de la difficulté.
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

        // Configuration de la carte et des ennemis selon la difficulté choisie
        this.map = new Map(difficulty.getMapSize(), difficulty.getMapSize());
        this.enemies = EnemyFactory.generateEnemyGroup(difficulty.getEnemyCount());

        System.out.println("Vous avez choisi la difficulté : " + difficulty.name());
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


    private void start() {
        Scanner scanner = new Scanner(System.in);
        map.setHero(hero, 0, 0); // Position de départ du héros
        map.printMap();

        while (true) {
            System.out.println("Utilisez les touches (Z/Q/S/D) pour déplacer le personnage :");
            String input = scanner.nextLine().toUpperCase();

            // Stockage temporaire des nouvelles coordonnées
            int[] newCoordinates = {hero.getX(), hero.getY()};

            switch (input) {
                case "Z": // Haut
                    newCoordinates[0]--; // Diminue la ligne
                    break;
                case "S": // Bas
                    newCoordinates[0]++; // Augmente la ligne
                    break;
                case "Q": // Gauche
                    newCoordinates[1]--; // Diminue la colonne
                    break;
                case "D": // Droite
                    newCoordinates[1]++; // Augmente la colonne
                    break;
                default:
                    System.out.println("Commande invalide !");
                    continue; // Relance le tour sans modifier l'état
            }

            // Vérifie si les nouvelles coordonnées sont valides
            if (!isValidPosition(newCoordinates)) {
                System.out.println("Vous ne pouvez pas sortir de la carte !");
                continue; // Ignore le déplacement
            }

            // Met à jour la carte : enlève le héros de l'ancienne position et le place à la nouvelle
            map.clearHero(hero.getX(), hero.getY());
            hero.setX(newCoordinates[0]); // Met à jour les coordonnées du héros
            hero.setY(newCoordinates[1]);
            map.setHero(hero, hero.getX(), hero.getY());

            // Vérifie si le joueur tombe sur un ennemi
            Enemy enemy = map.getEnemy(hero.getX(), hero.getY());
            if (enemy != null) {
                System.out.println("Un ennemi apparaît : " + enemy.getName() + " !");
                CombatManager.handleDuel(hero, enemy);
                if (hero.isDead()) {
                    System.out.println("Vous avez été vaincu. Fin du jeu.");
                    break;
                }

                // Si l'ennemi est vaincu, il est retiré de la carte
                map.clearEnemy(hero.getX(), hero.getY());
                enemies.remove(enemy);
                System.out.println("Ennemi vaincu !");
                map.setHero(hero, hero.getX(), hero.getY());

            }
                map.printMap();

            // Condition de victoire : plus aucun ennemi sur la carte
            if (enemies.isEmpty()) {
                System.out.println("Félicitations ! Vous avez vaincu tous les ennemis !");
                break;
            }
        }
    }

    /**
     * Vérifie si la position donnée est valide (dans les limites de la carte).
     *
     * @param position tableau [row, column]
     * @return true si la position est valide, sinon false
     */
    private boolean isValidPosition(int[] position) {
        return position[0] >= 0 && position[0] < map.getRows() &&
                position[1] >= 0 && position[1] < map.getColumns();
    }

}


