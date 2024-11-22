package org.example;

import Classes.Difficulty;
import Classes.Enemy;
import Classes.Map;
import Utils.EnemyFactory;

import java.util.List;
import java.util.Scanner;

public class Game {
    private String playerName; // Nom du joueur
    private List<Enemy> enemies; // Liste des ennemis générés
    private Map map; // Carte du jeu

    public static void main(String[] args) {
        credits();
        Game game = new Game();
        game.askPlayerName();
        game.launchGame();
        game.placeEnemiesOnMap();
        game.printMap();
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
        this.playerName = sc.nextLine();
        System.out.println("Bienvenue, " + playerName + " !");
    }

    /**
     * Lancement du jeu avec choix de la difficulté.
     */
    private void launchGame() {
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
        for (Enemy enemy : enemies) {
            int row, column;
            do {
                row = (int) (Math.random() * map.getRows());
                column = (int) (Math.random() * map.getColumns());
            } while (map.getMap()[row][column] != null); // S'assurer que la case est libre

            map.setEnemy(enemy, row, column);
        }
    }

    /**
     * Affiche la carte du jeu.
     */
    private void printMap() {
        System.out.println("\nVoici la carte du jeu :");
        map.printMap();
    }
}
