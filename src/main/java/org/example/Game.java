package org.example;

import Classes.Enemy;
import Utils.EnemyFactory;

import java.util.ArrayList;

public class Game {

    public static void main(String[] args) {
        credits();
        launchGame();
    }

    private static void credits() {
        System.out.println("------------------------------------");
        System.out.println("|                                  |");
        System.out.println("|  Jeu créé par Amani, Chayma et   |");
        System.out.println("|              Teo                 |");
        System.out.println("|                                  |");
        System.out.println("------------------------------------");
    }

    private static void launchGame() {
        //lancer la boucle du jeu
        System.out.println("je lance le jeu");
        ArrayList<Enemy> badGuys = new ArrayList<>(EnemyFactory.generateEnemyGroup(4));
        for (Enemy badGuy: badGuys
             ) {
            System.out.println(badGuy);
        }
    }


}
