package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;

import java.util.List;

public class CombatFloor extends TowerFloor {
    private Monster monster;

    @Override
    protected String getFloorName() {
        return "Combat Floor";
    }

    @Override
    protected void setup(List<Hero> party) {
        monster = new Monster("Goblin", 50, 10);
        System.out.println("A wild " + monster.getName() + " appears!");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        int totalDamage = 0;

        while (monster.isAlive() && party.stream().anyMatch(Hero::isAlive)) {

            for (Hero hero : party) {
                if (!hero.isAlive()) continue;

                hero.onTurnStart();

                if (hero.canAct()) {
                    int dmg = hero.calculateDamage();
                    monster.takeDamage(dmg);
                    System.out.println(hero.getName() + " deals " + dmg + " damage to " + monster.getName());
                }

                hero.onTurnEnd();
            }

            for (Hero hero : party) {
                if (!hero.isAlive()) continue;

                if (monster.isAlive()) {
                    int before = hero.getHp();
                    monster.attack(hero);
                    totalDamage += (before - hero.getHp());
                }
            }
        }

        boolean cleared = monster.getHp() <= 0;
        return new FloorResult(cleared, totalDamage, cleared ? "Monster defeated" : "Heroes lost");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        if (result.isCleared()) {
            for (Hero hero : party) {
                if (hero.isAlive()) {
                    hero.heal(10);
                }
            }
            System.out.println("Heroes received healing as loot");
        }
    }
}
