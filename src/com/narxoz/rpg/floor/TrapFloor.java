package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;

import java.util.List;
public class TrapFloor extends TowerFloor{

    @Override
    protected String getFloorName() {
        return "Trap Floor";
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("The floor is filled with hidden traps...");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        int totalDamage = 0;

        for (Hero hero : party) {
            if (!hero.isAlive()) continue;

            hero.onTurnStart();

            int before = hero.getHp();
            hero.takeDamage(15);
            totalDamage += (before - hero.getHp());

            hero.onTurnEnd();
        }

        boolean anyAlive = party.stream().anyMatch(Hero::isAlive);
        return new FloorResult(anyAlive, totalDamage, "Traps triggered");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        if (result.isCleared()) {
            System.out.println("You survived the traps, but found no loot");
        }
    }
}
