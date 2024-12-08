package Interfaces;

import Classes.Character;

public interface SpecialAbility {
    /**
     * Active la capacité spéciale.
     *
     * @param target la cible de la capacité (peut être null selon la capacité)
     */
    void activate(Character target);
}

