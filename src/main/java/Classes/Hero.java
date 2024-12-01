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

        System.out.println("\n--- Attaque ---");
        System.out.println("1. Attaque normale");
        if (!this.specialAbilityUsed) {
            System.out.println("2. Capacité spéciale");
        }

        int choice = sc.nextInt();

        if (choice == 2) {
            activate(this, target);
        } else {
            int damage = Math.max(0, this.getAttack() - target.getDefense());
            target.setHp(target.getHp() - damage);
            System.out.printf("%s attaque %s pour %d dégâts.\n", this.getName(), target.getName(), damage);
        }

        if (target.isDead()) {
            System.out.println("L'ennemi " + target.getName() + " est mort sous vos coups !");
        }
    }

    @Override
    public void activate(Character user, Character target) {
        if (specialAbilityUsed) {
            System.out.println("Capacité spéciale déjà utilisée.");
            return;
        }

        // Utilisation de la capacité spéciale
        this.specialAbilityUsed = true;
    }

    public int[] posPrec(){
        return new int []{x,y};
    }

    public int[] moveHero(int rowVariation, int columnVariation, int rowMap, int colMap) {
        int newX = this.x + rowVariation;
        int newY = this.y + columnVariation;

        // Vérification des limites de la carte
        if (newX >= 0 && newX < rowMap && newY >= 0 && newY < colMap) {
            x = newX;  // Mettre à jour la position du héros
            y = newY;
        }
        return new int[]{x, y};  // Retourner la nouvelle position du héros
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
