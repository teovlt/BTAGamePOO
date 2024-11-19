package src.Classes;
import src.Interfaces.SpecialAbility;

/**
 * Classe représentant le héros du jeu.
 */
public class Hero extends Character implements SpecialAbility {
    private boolean specialAbilityUsed;

    /**
     * Constructeur de la classe Hero.
     *
     * @param name    le nom du héros
     * @param hp      les points de vie du héros
     * @param attack  la force d'attaque du héros
     * @param defense la défense du héros
     */
    public Hero(String name, int hp, int attack, int defense, int speed) {
        super(name, hp, attack, defense, speed);
        this.specialAbilityUsed = false;
    }

    @Override
    public void attack(Character target) {
        System.out.println("attack");
        System.out.println("mettre le choix de capacité spéciale ici");
    }

    @Override
    public void activate(Character user, Character target) {
        if (specialAbilityUsed) {
            System.out.println("Capacité spéciale déjà utilisée.");
            return;
        }
        //TODO
    }

    /**
     * Réinitialise l'utilisation de la capacité spéciale (à appeler au début d'un niveau).
     */
    public void resetSpecialAbility() {
        this.specialAbilityUsed = false;
    }


}
