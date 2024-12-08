package Classes;

import Classes.Character;
import Interfaces.SpecialAbility;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Hero extends Character implements SpecialAbility {
    private int x; // Position du joueur sur la carte (ligne)
    private int y; // Position du joueur sur la carte (colonne)
    private boolean specialAbilityUsed;
    private int choice = -1;

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public Hero(String name, int hp, int attack, int defense, int speed, int x, int y) {
        super(name, hp, attack, defense, speed);
        this.x = 0;
        this.y = 0;
        this.specialAbilityUsed = false;
    }

    @Override
    public String toString() {
        return "Nom: " + this.getName() + ", Santé: " + this.getHp() + ", Position: (" + x + ", " + y + ")";
    }

    @Override
    public void attack(Character target) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n ---- Choix de l'attaque ----");
        System.out.println(" |  1. Attaque normale      |");
        if (!this.specialAbilityUsed) {
        System.out.println(" |  2. Capacité spéciale    |");
        }
        System.out.println(" ----------------------------\n");


        // Boucle pour assurer une entrée valide
        while (choice < 1 || choice > 2 || (choice == 2 && this.specialAbilityUsed)) {
            System.out.print("Votre choix : ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice == 2 && this.specialAbilityUsed) {
                    System.out.println("Capacité spéciale déjà utilisée. Choisissez une autre option.");
                }
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre (1 ou 2).");
                sc.next(); // Consomme l'entrée invalide
            }
        }

        if (choice == 2) {
            activate(this, target); // Utilise la capacité spéciale
        } else {
            // Calcul des dégâts de l'attaque normale
            int damage = Math.max(0, this.getAttack() - target.getDefense());
            target.setHp(target.getHp() - damage);
            System.out.printf("%s attaque %s pour %d dégâts.\n", this.getName(), target.getName(), damage);
        }
        System.out.println("Il reste " + target.getHp() + " à " + target.getName());
    }

    @Override
    public void activate(Character user, Character target) {
        if (specialAbilityUsed) {
            System.out.println("Capacité spéciale déjà utilisée.");
            return;
        }

        // Utilisation de la capacité spéciale
        System.out.println("NON IMPLEMENTE");
        this.specialAbilityUsed = true;
    }

    public int[] posPrec() {
        return new int[]{x, y};
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
