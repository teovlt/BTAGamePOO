package Abilities;

import Classes.Character;
import Interfaces.SpecialAbility;

public class Healing implements SpecialAbility {
    @Override
    public void activate(Character user, Character target) {
        int healingAmount = 30; // Par exemple, guérit 30 points de vie
        user.setHp(user.getHp() + healingAmount); // Méthode pour augmenter les PV
        System.out.println(user.getName() + " utilise Guérison et se soigne de " + healingAmount + " points de vie !");
    }
}
