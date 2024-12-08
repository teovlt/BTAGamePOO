package Classes;

import Abilities.Rage;
import Classes.Character;
import Interfaces.SpecialAbility;

import java.util.Scanner;

/**
 * Classe représentant le héros du jeu.
 *
 * <p>
 * Le héros est un personnage jouable qui peut attaquer normalement ou utiliser une capacité spéciale.
 * Il a également une position sur une carte représentée par des coordonnées (x, y).
 * </p>
 */
public class Hero extends Character {

    private int x; // Position du joueur sur la carte (ligne)
    private int y; // Position du joueur sur la carte (colonne)
    private boolean specialAbilityUsed; // Indique si la capacité spéciale a été utilisée

    private SpecialAbility specialAbility;

    /**
     * Constructeur de la classe Hero.
     *
     * @param name    le nom du héros
     * @param hp      les points de vie du héros
     * @param attack  la force d'attaque du héros
     * @param defense la défense du héros
     * @param speed   la vitesse du héros
     * @param x       la position initiale en x (ligne)
     * @param y       la position initiale en y (colonne)
     */
    public Hero(String name, int hp, int attack, int defense, int speed, int x, int y, SpecialAbility specialAbility) {
        super(name, hp, attack, defense, speed);
        this.x = 0; // Par défaut, position initiale (0, 0)
        this.y = 0;
        this.specialAbilityUsed = false;
        this.specialAbility = specialAbility;
    }


    /**
     * Retourne une chaîne de caractères représentant le héros.
     *
     * @return une chaîne avec le nom, la santé et la position du héros
     */
    @Override
    public String toString() {
        return "Nom: " + this.getName() + ", Santé: " + this.getHp() + ", Position: (" + x + ", " + y + ")";
    }

    /**
     * Gère l'attaque du héros sur une cible.
     *
     * <p>
     * Le joueur peut choisir entre une attaque normale ou une capacité spéciale.
     * Si la capacité spéciale a déjà été utilisée, seule l'attaque normale est disponible.
     * </p>
     *
     * @param target le personnage cible de l'attaque
     */
    @Override
    public void attack(Character target) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n ---- Choix de l'attaque ----");
        System.out.println(" |  1. Attaque normale      |");
        if (!this.specialAbilityUsed) {
            System.out.println(" |  2. Capacité spéciale    |");
        }
        System.out.println(" ----------------------------\n");

        int choice = -1;

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
            activateSpecialAbility(target); // Utilise la capacité spéciale
        } else {
            // Calcul des dégâts de l'attaque normale
            int damage = Math.max(0, this.getAttack() - target.getDefense());
            target.setHp(target.getHp() - damage);
            System.out.printf("%s attaque %s pour %d dégâts.\n", this.getName(), target.getName(), damage);
        }
        System.out.println("Il reste " + target.getHp() + " à " + target.getName());
    }

    /**
     * Active la capacité spéciale du héros.
     *
     * <p>
     * Si la capacité spéciale a déjà été utilisée, elle ne peut pas être activée à nouveau.
     * </p>
     *
     * @param target la cible de la capacité spéciale
     */
    public void activateSpecialAbility(Character target) {
        if (specialAbilityUsed) {
            System.out.println("Capacité spéciale déjà utilisée.");
            return;
        }
        System.out.println(this.getAttack());
        specialAbility.activate(this,target);
        System.out.println(this.getAttack());

        int damage = Math.max(0, this.getAttack() - target.getDefense());
        target.setHp(target.getHp() - damage);
        System.out.printf("%s attaque %s pour %d dégâts.\n", this.getName(), target.getName(), damage);

        this.specialAbilityUsed = true;
    }

    /**
     * Retourne la position précédente du héros sous forme d'un tableau de deux entiers.
     *
     * @return un tableau contenant les coordonnées x et y de la position précédente
     */
    public int[] posPrec() {
        return new int[]{x, y};
    }

    /**
     * Retourne la position x (ligne) du héros.
     *
     * @return la position x du héros
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la position y (colonne) du héros.
     *
     * @return la position y du héros
     */
    public int getY() {
        return y;
    }

    /**
     * Modifie la position x (ligne) du héros.
     *
     * @param x la nouvelle position x du héros
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Modifie la position y (colonne) du héros.
     *
     * @param y la nouvelle position y du héros
     */
    public void setY(int y) {
        this.y = y;
    }
}
