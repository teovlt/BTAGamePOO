package org.example;

import Characters.Healer;
import Characters.Tank;
import Characters.Warrior;
import Classes.Difficulty;
import Classes.Enemy;
import Classes.Hero;
import Classes.Map;
import Utils.CombatManager;
import Utils.EnemyFactory;

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe principale représentant le jeu.
 * Gère la configuration initiale, le déroulement du jeu, et l'interaction entre le héros et les ennemis.
 */
public class Game {
    private List<Enemy> enemies; // Liste des ennemis générés
    private Map map; // Carte du jeu
    private Hero hero; // Joueur

    private static final Logger logger = Logger.getLogger(Game.class.getName());

    /**
     * Point d'entrée principal du jeu.
     *
     * @param args Arguments en ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        catchLog();
        logger.info("Démarrage du jeu");
        Game game = new Game();
        game.credits();
        game.askPlayerName();
        game.chooseDifficulty();
        game.placeEnemiesOnMap();
        game.start();
        logger.info("Fin du jeu");
    }

    /**
     * Initialise le gestionnaire de logs pour écrire dans un fichier.
     */
    private static void catchLog() {
        try {
            FileHandler fileHandler = new FileHandler("./log.txt", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new java.util.logging.SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
            logger.info("Logger initialisé avec succès");
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
            logger.severe("Erreur lors de l'initialisation du logger : " + e.getMessage());
        }
    }

    /**
     * Affiche les crédits du jeu.
     */
    private void credits() {
        logger.info("Affichage des crédits du jeu");
        System.out.println("\n");
        System.out.println("----------------------------------------");
        System.out.println("|                                      |");
        System.out.println("|  Jeu créé par Amanie, Chayma et Teo  |");
        System.out.println("|                                      |");
        System.out.println("----------------------------------------");
        System.out.println("\n");
    }

    /**
     * Demande le nom du joueur et configure son personnage.
     */
    private void askPlayerName() {
        logger.info("Demande du nom du joueur");
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer votre nom : ");
        String playerName = sc.nextLine();
        logger.info("Nom du joueur saisi : " + playerName);

        System.out.println("Bienvenue " + playerName + "!");
        this.hero = chooseCharacter();
        hero.setName(playerName);
        logger.info("Personnage choisi : " + hero.getClass().getSimpleName());
    }

    /**
     * Permet au joueur de choisir un personnage parmi les options disponibles.
     *
     * @return Le personnage choisi par le joueur.
     */
    public static Hero chooseCharacter() {
        Scanner scanner = new Scanner(System.in);
        Hero chosenCharacter = null;
        logger.info("Début du choix du personnage");

        while (chosenCharacter == null) {
            System.out.println("Choisissez votre personnage:");
            System.out.println("1. Guerrier");
            System.out.println("2. Soigneur");
            System.out.println("3. Tank");
            System.out.print("Entrez le numéro de votre choix: ");

            int choice = -1;

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un numéro valide.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    chosenCharacter = new Warrior();
                    logger.info("Personnage Guerrier choisi");
                    break;
                case 2:
                    chosenCharacter = new Healer();
                    logger.info("Personnage Soigneur choisi");
                    break;
                case 3:
                    chosenCharacter = new Tank();
                    logger.info("Personnage Tank choisi");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez essayer à nouveau.");
                    logger.warning("Choix invalide du joueur : " + choice);
            }
        }

        return chosenCharacter;
    }

    /**
     * Permet au joueur de choisir un niveau de difficulté, qui détermine la taille de la carte et le nombre d'ennemis.
     */
    private void chooseDifficulty() {
        logger.info("Demande du choix de la difficulté");
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez le niveau de difficulté : \n" +
                "1. Facile (Carte petite, peu d'ennemis)\n" +
                "2. Moyen (Carte moyenne, ennemis modérés)\n" +
                "3. Difficile (Grande carte, beaucoup d'ennemis)");

        Difficulty difficulty = null;

        while (difficulty == null) {
            System.out.print("Votre choix (1, 2 ou 3) : ");
            int difficultyChoice = sc.nextInt();

            switch (difficultyChoice) {
                case 1:
                    difficulty = Difficulty.EASY;
                    logger.info("Difficulté choisie : Facile");
                    break;
                case 2:
                    difficulty = Difficulty.MEDIUM;
                    logger.info("Difficulté choisie : Moyen");
                    break;
                case 3:
                    difficulty = Difficulty.HARD;
                    logger.info("Difficulté choisie : Difficile");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    logger.warning("Choix de difficulté invalide : " + difficultyChoice);
            }
        }

        this.map = new Map(difficulty.getMapSize(), difficulty.getMapSize());
        this.enemies = EnemyFactory.generateEnemyGroup(difficulty.getEnemyCount());
        logger.info("Carte créée avec une taille de " + map.getRows() + "x" + map.getColumns());
        logger.info("Nombre d'ennemis générés : " + enemies.size());
    }

    /**
     * Place les ennemis générés aléatoirement sur la carte.
     */
    private void placeEnemiesOnMap() {
        logger.info("Placement des ennemis sur la carte");
        int heroStartRow = 0;
        int heroStartColumn = 0;

        for (Enemy enemy : enemies) {
            int row, column;
            do {
                row = (int) (Math.random() * map.getRows());
                column = (int) (Math.random() * map.getColumns());
            } while (
                    map.getMap()[row][column] != null ||
                            (row == heroStartRow && column == heroStartColumn)
            );

            map.setEnemy(enemy, row, column);
            logger.info("Ennemi " + enemy.getName() + " placé en position (" + row + ", " + column + ")");
        }
    }

    /**
     * Lance la boucle principale du jeu. Gère les déplacements du héros, les rencontres avec les ennemis,
     * et les interactions principales.
     */
    private void start() {
        logger.info("Démarrage du jeu principal");
        Scanner scanner = new Scanner(System.in);
        map.setHero(hero, 0, 0);
        map.printMap();

        while (true) {
            System.out.println("Utilisez les touches (Z/Q/S/D) pour déplacer le personnage (" + hero.getName().charAt(0) + ") :");
            String input = scanner.nextLine().toUpperCase();

            int[] newCoordinates = {hero.getX(), hero.getY()};

            switch (input) {
                case "Z": newCoordinates[0]--; break;
                case "S": newCoordinates[0]++; break;
                case "Q": newCoordinates[1]--; break;
                case "D": newCoordinates[1]++; break;
                default:
                    System.out.println("Commande invalide !");
                    logger.warning("Commande invalide : " + input);
                    continue;
            }

            if (!isValidPosition(newCoordinates)) {
                System.out.println("Vous ne pouvez pas sortir de la carte !");
                logger.warning("Tentative de sortie de la carte en position : " +
                        newCoordinates[0] + "," + newCoordinates[1]);
                continue;
            }

            map.clearHero(hero.getX(), hero.getY());
            hero.setX(newCoordinates[0]);
            hero.setY(newCoordinates[1]);
            map.setHero(hero, hero.getX(), hero.getY());
            logger.info("Héros déplacé en position : " + hero.getX() + "," + hero.getY());

            Enemy enemy = map.getEnemy(hero.getX(), hero.getY());
            if (enemy != null) {
                System.out.println("Un ennemi apparaît : " + enemy.getName() + " !");
                logger.info("Combat initié avec l'ennemi : " + enemy.getName());
                CombatManager.handleDuel(hero, enemy);
                if (hero.isDead()) {
                    System.out.println("GAME OVER !!");
                    logger.severe("Héros mort. Fin du jeu.");
                    break;
                }

                map.clearEnemy(hero.getX(), hero.getY());
                enemies.remove(enemy);
                logger.info("Ennemi " + enemy.getName() + " vaincu");
                System.out.println("Ennemi vaincu !");
                map.setHero(hero, hero.getX(), hero.getY());
            }
            map.printMap();

            if (enemies.isEmpty()) {
                System.out.println("Félicitations ! Vous avez vaincu tous les ennemis !");
                logger.info("Tous les ennemis ont été vaincus.");
                break;
            }
        }
    }

    /**
     * Vérifie si les coordonnées données sont valides (dans les limites de la carte).
     *
     * @param coordinates Coordonnées [ligne, colonne] à vérifier.
     * @return `true` si les coordonnées sont valides, `false` sinon.
     */
    private boolean isValidPosition(int[] coordinates) {
        return coordinates[0] >= 0 && coordinates[0] < map.getRows() &&
                coordinates[1] >= 0 && coordinates[1] < map.getColumns();
    }
}
