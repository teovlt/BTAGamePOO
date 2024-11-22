package org.example;

import Classes.Enemy;
import Classes.Map;
import Utils.EnemyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private ArrayList<Enemy> badGuys;
    private HashMap<Integer, Map> maps = new HashMap<>();

    int numCarte;
    Map map;


    public Game() {
        this.badGuys = new ArrayList<>(EnemyFactory.generateEnemyGroup(4));
        initializeMaps(3);
    }

    private void initializeMaps(int numMaps) {
        for (int i = 1; i <= numMaps; i++) {
            maps.put(i, new Map(i+5, i+5));
        }
    }

    public static void main(String[] args) {
        credits();
        Game game = new Game();
        game.launchGame();
        game.printMap();
    }

    private static void credits() {
        System.out.println("------------------------------------");
        System.out.println("|                                  |");
        System.out.println("|  Jeu créé par Amanie, Chayma et   |");
        System.out.println("|              Teo                 |");
        System.out.println("|                                  |");
        System.out.println("------------------------------------");
    }

    private void launchGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Je chois la carte " +
                "Carte 1 : Facile, " +
                "Carte 2 : Difficulté Moyenne, " +
                "Carte 3 : Difficile. " +
                "Veuillez saisir 1, 2 ou 3 ");
        this.numCarte = sc.nextInt();
        System.out.println("Vous avez choisi la carte : " + numCarte);
        this.map = maps.get(numCarte);
        System.out.println("Je lance le jeu");
        for (Enemy badGuy : badGuys) {
            System.out.println(badGuy);
        }
    }


    private void printMap() {

        for (Enemy badGuy : badGuys) {
            int column = (int) (Math.random() * map.getColumns());
            int row = (int) (Math.random() * map.getRows());
            map.setEnemy(badGuy, row, column);
        }
        map.printMap();
    }
}

