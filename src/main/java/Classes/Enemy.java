package Classes;

/**
 * Classe représentant un ennemi dans le jeu.
 *
 * <p>
 * Les ennemis sont des personnages qui attaquent les autres joueurs ou personnages.
 * Ils héritent des caractéristiques de la classe abstraite {@link Character}.
 * </p>
 */
public class Enemy extends Character {

    /**
     * Constructeur de la classe Enemy.
     *
     * @param name    le nom de l'ennemi
     * @param hp      les points de vie de l'ennemi
     * @param attack  la force d'attaque de l'ennemi
     * @param defense la défense de l'ennemi
     * @param speed   la vitesse de l'ennemi
     */
    public Enemy(String name, int hp, int attack, int defense, int speed) {
        super(name, hp, attack, defense, speed);
    }

    /**
     * Renvoie une représentation simplifiée de l'ennemi sous forme d'une chaîne de caractères.
     * <p>
     * Cette méthode retourne uniquement la première lettre du nom de l'ennemi.
     * </p>
     *
     * @return la première lettre du nom de l'ennemi
     */
    @Override
    public String toString() {
        return getName().charAt(0) + ""; // Affiche la première lettre du nom
    }

    /**
     * Attaque un autre personnage et réduit ses points de vie en fonction de la force d'attaque
     * de l'ennemi et de la défense de la cible.
     *
     * <p>
     * Les dégâts infligés sont calculés comme suit :
     * <code>Math.max(0, this.getAttack() - target.getDefense())</code>.
     * Si les dégâts sont négatifs, ils sont ramenés à zéro.
     * </p>
     *
     * @param target le personnage cible de l'attaque
     */
    @Override
    public void attack(Character target) {

        // Calcul des dégâts infligés
        int damage = Math.max(0, this.getAttack() - target.getDefense());
        target.setHp(target.getHp() - damage);

        // Affichage du résultat
        System.out.printf("%s attaque %s pour %d de dégâts.\n", this.getName(), target.getName(), damage);
        System.out.printf("Il reste %d PV à %s.\n", target.getHp(), target.getName());
    }
}
