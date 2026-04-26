package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;

import java.util.List;
public class RestFloor extends TowerFloor {

    @Override
    protected String getFloorName() {
        return "Rest Floor";
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("A peaceful room. You can rest here.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        for (Hero hero : party) {
            if (hero.isAlive()) {
                hero.heal(20);
            }
        }
        return new FloorResult(true, 0, "Party rested");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false;
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
    }
}
