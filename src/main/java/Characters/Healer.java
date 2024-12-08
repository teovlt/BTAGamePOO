package Characters;

import Abilities.Healing;
import Classes.Hero;

public class Healer extends Hero {
    public Healer() {
        super("Soigneur", 80, 7, 6, 4, 0,0,new Healing()); // Le Soigneur a moins d'attaque, mais peut se soigner
    }
}
