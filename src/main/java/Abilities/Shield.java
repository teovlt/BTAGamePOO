package Abilities;

import Classes.Character;
import Interfaces.SpecialAbility;

public class Shield implements SpecialAbility {
    @Override
    public void activate(Character user, Character target) {
        int defenseAmount = 5;
        user.setDefense(user.getDefense() + defenseAmount); // Augmente la défense de 5 par exemple
        System.out.println(user.getName() + " utilise Bouclier pour augmenter sa défense de "+ defenseAmount+" !");
    }
}
