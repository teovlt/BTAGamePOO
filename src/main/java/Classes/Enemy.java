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
        System.out.printf("\n%s attaque %s !\n", this.getName(), target.getName());

        // Calcul des dégâts infligés
        int damage = Math.max(0, this.getAttack() - target.getDefense());
        target.setHp(target.getHp() - damage);

        // Affichage du résultat
        System.out.printf("%s inflige %d dégâts à %s.\n", this.getName(), damage, target.getName());

        // Vérification si la cible est morte
        if (target.isDead()) {
            System.out.printf("%s a été vaincu par %s !\n", target.getName(), this.getName());
        } else {
            System.out.printf("Il reste %d PV à %s.\n", target.getHp(), target.getName());
        }
    }



}
