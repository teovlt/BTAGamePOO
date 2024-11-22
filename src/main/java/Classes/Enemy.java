package Classes;

public class Enemy extends Character {

    /**
     * Constructeur de la classe Ennemy.
     *
     * @param name    le nom de l'ennemi
     * @param hp      les points de vie de l'ennemie
     * @param attack  la force d'attaque de l'ennemie
     * @param defense la défense de l'ennemi
     */
    public Enemy(String name, int hp, int attack, int defense, int speed) {
        super(name, hp, attack, defense, speed);
    }

    @Override
    public String toString() {
        return getName().charAt(0) + ""; // Affiche la première lettre du nom
    }
    @Override
    public void attack(Character target) {

    }



}
