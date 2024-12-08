package Abilities;

import Classes.Character;
import Interfaces.SpecialAbility;

public class Rage implements SpecialAbility {
    @Override
    public void activate(Character user, Character target) {
        int attackAmount = 5;
        user.setAttack(user.getAttack() + attackAmount);
        System.out.println(user.getName() + " entre en rage et augmente son attaque de "+ attackAmount+ " !");
    }
}
