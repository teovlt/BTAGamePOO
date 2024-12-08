package Classes;

public class Enemy extends Character {
    public Enemy(String name, int hp, int attack, int defense, int speed) {
        super(name, hp, attack, defense, speed);
    }

    @Override
    public String toString() {
        return getName().charAt(0) + ""; // Affiche la première lettre du nom
    }

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
