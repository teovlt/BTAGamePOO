package Characters;

import Abilities.Shield;
import Classes.Hero;

public class Tank extends Hero {
    public Tank() {
        super("Tank", 150, 6, 8, 15, 0,0,new Shield()); // Le Tank a une grande défense
    }
}
