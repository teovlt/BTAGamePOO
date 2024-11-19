package Classes;

/**
 * Classe abstraite représentant un personnage dans le jeu.
 */
public abstract class Character {
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int speed;

    /**
     * Constructeur de la classe Character.
     *
     * @param name    le nom du personnage
     * @param hp      les points de vie du personnage
     * @param attack  la force d'attaque du personnage
     * @param defense la défense du personnage
     */
    public Character(String name, int hp, int attack, int defense, int speed) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {return speed;}

    public void setSpeed(int speed) {this.speed = speed;}

    /**
     * Méthode abstraite pour attaquer un autre personnage.
     *
     * @param target le personnage cible de l'attaque
     */
    public abstract void attack(Character target);

    /**
     * Vérifie si le personnage est encore en vie.
     *
     * @return true si hp > 0, sinon false
     */
    public boolean isDead() {
        return this.hp <= 0;
    }


    @Override
    public String toString() {
        return "============================\n" +
                "| Nom      : " + String.format("%-12s", name) + " |\n" +
                "| HP       : " + String.format("%-12d", hp) + " |\n" +
                "| Attaque  : " + String.format("%-12d", attack) + " |\n" +
                "| Défense  : " + String.format("%-12d", defense) + " |\n" +
                "| Vitesse  : " + String.format("%-12d", speed) + " |\n" +
                "============================ \n";
    }

}
