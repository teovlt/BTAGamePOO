package Characters;

import Abilities.Rage;
import Classes.Hero;

public class Warrior extends Hero {
    public Warrior() {
        super("Guerrier", 100, 8, 12, 5,0,0, new Rage()); // Le Guerrier a une attaque forte
    }
}
