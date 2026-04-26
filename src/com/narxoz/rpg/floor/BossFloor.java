package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;

import java.util.List;

public class BossFloor extends TowerFloor {
    private Monster boss;

    @Override
    protected String getFloorName() {
        return "Boss Floor";
    }

    @Override
    protected void announce() {
        System.out.println("\n=== FINAL BOSS APPEARS ===");
    }

    @Override
    protected void setup(List<Hero> party) {
        boss = new Monster("Dragon", 120, 20);
        System.out.println("The mighty " + boss.getName() + " stands before you!");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        int totalDamage = 0;

        while (boss.isAlive() && party.stream().anyMatch(Hero::isAlive)) {

            for (Hero hero : party) {
                if (!hero.isAlive()) continue;

                hero.onTurnStart();

                if (hero.canAct()) {
                    int dmg = hero.calculateDamage();
                    boss.takeDamage(dmg);
                    System.out.println(hero.getName() + " hits " + boss.getName() + " for " + dmg);
                }

                hero.onTurnEnd();
            }

            for (Hero hero : party) {
                if (!hero.isAlive()) continue;

                if (boss.isAlive()) {
                    int before = hero.getHp();
                    boss.attack(hero);
                    totalDamage += (before - hero.getHp());
                }
            }
        }

        boolean cleared = boss.getHp() <= 0;
        return new FloorResult(cleared, totalDamage, cleared ? "Boss defeated" : "Party wiped");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        if (result.isCleared()) {
            for (Hero hero : party) {
                if (hero.isAlive()) {
                    hero.heal(30);
                }
            }
            System.out.println("Legendary reward granted!");
        }
    }
}
