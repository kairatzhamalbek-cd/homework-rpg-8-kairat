package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.*;
import com.narxoz.rpg.state.*;
import com.narxoz.rpg.tower.TowerRunner;
import com.narxoz.rpg.tower.TowerRunResult;

import java.util.Arrays;
import java.util.List;



public class Main {

    public static void main(String[] args) {
        Hero hero1 = new Hero("Aragorn", 100, 20, 5);
        Hero hero2 = new Hero("Legolas", 80, 25, 3);

        hero2.setState(new PoisonedState(3));

        List<Hero> party = Arrays.asList(hero1, hero2);

        List<TowerFloor> floors = Arrays.asList(
                new CombatFloor(),
                new TrapFloor(),
                new RestFloor(),
                new BossFloor()
        );

        TowerRunner runner = new TowerRunner(floors);

        TowerRunResult result = runner.run(party);

        System.out.println("\n=== FINAL RESULT ===");
        System.out.println("Floors cleared: " + result.getFloorsCleared());
        System.out.println("Heroes alive: " + result.getHeroesSurviving());
        System.out.println("Reached top: " + result.isReachedTop());

         }
}
