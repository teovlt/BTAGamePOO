package Classes;

/**
 * Classe abstraite représentant un personnage dans un jeu.
 *
 * <p>
 * Cette classe sert de base pour différents types de personnages jouables ou
 * non-jouables. Elle contient les attributs de base d'un personnage tels que
 * le nom, les points de vie (HP), l'attaque, la défense et la vitesse.
 * Les sous-classes doivent implémenter la méthode {@link #attack(Character)}
 * pour définir le comportement spécifique d'une attaque.
 * </p>
 */
public abstract class Character {

    /**
     * Le nom du personnage.
     */
    private String name;

    /**
     * Les points de vie (HP) du personnage.
     * Si {@code hp <= 0}, le personnage est considéré comme mort.
     */
    private int hp;

    /**
     * La puissance d'attaque du personnage.
     * Détermine les dégâts infligés à un adversaire lors d'une attaque.
     */
    private int attack;

    /**
     * La défense du personnage.
     * Réduit les dégâts subis lors d'une attaque ennemie.
     */
    private int defense;

    /**
     * La vitesse du personnage.
     * Utilisée pour déterminer l'ordre des actions dans le jeu.
     */
    private int speed;

    /**
     * Constructeur de la classe {@code Character}.
     * Initialise les attributs de base du personnage.
     *
     * @param name    le nom du personnage
     * @param hp      les points de vie du personnage
     * @param attack  la force d'attaque du personnage
     * @param defense la défense du personnage
     * @param speed   la vitesse du personnage
     */
    public Character(String name, int hp, int attack, int defense, int speed) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    /**
     * Obtient le nom du personnage.
     *
     * @return le nom du personnage
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du personnage.
     *
     * @param name le nouveau nom du personnage
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtient les points de vie (HP) du personnage.
     *
     * @return les points de vie du personnage
     */
    public int getHp() {
        return hp;
    }

    /**
     * Définit les points de vie (HP) du personnage.
     *
     * @param hp les nouveaux points de vie du personnage
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Obtient la puissance d'attaque du personnage.
     *
     * @return la puissance d'attaque du personnage
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Définit la puissance d'attaque du personnage.
     *
     * @param attack la nouvelle puissance d'attaque du personnage
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Obtient la défense du personnage.
     *
     * @return la défense du personnage
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Définit la défense du personnage.
     *
     * @param defense la nouvelle défense du personnage
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Obtient la vitesse du personnage.
     *
     * @return la vitesse du personnage
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Définit la vitesse du personnage.
     *
     * @param speed la nouvelle vitesse du personnage
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Méthode abstraite représentant une attaque sur un autre personnage.
     *
     * <p>
     * Cette méthode doit être implémentée dans les sous-classes pour
     * définir le comportement spécifique d'une attaque.
     * </p>
     *
     * @param target le personnage cible de l'attaque
     */
    public abstract void attack(Character target);

    /**
     * Vérifie si le personnage est mort.
     *
     * @return {@code true} si les points de vie (HP) sont inférieurs ou égaux à 0,
     *         sinon {@code false}
     */
    public boolean isDead() {
        return this.hp <= 0;
    }

    /**
     * Retourne une représentation textuelle des attributs du personnage.
     *
     * @return une chaîne formatée décrivant le personnage
     */
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
