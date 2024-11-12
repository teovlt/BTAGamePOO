package src.Interfaces;

import src.Classes.Character;
public interface SpecialAbility {
    /**
     * Active la capacité spéciale.
     *
     * @param user   le personnage qui utilise la capacité
     * @param target la cible de la capacité (peut être null selon la capacité)
     */
    void activate(Character user, Character target);
}

