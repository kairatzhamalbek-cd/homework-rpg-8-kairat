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

            hero1.setState(new StunnedState(1));
            hero2.setState(new PoisonedState(3));

            List<Hero> party = Arrays.asList(hero1, hero2);

            List<TowerFloor> firstFloors = Arrays.asList(
                    new CombatFloor(),
                    new TrapFloor(),
                    new RestFloor()
            );

            TowerRunner firstRunner = new TowerRunner(firstFloors);
            TowerRunResult firstResult = firstRunner.run(party);

            if (firstResult.getHeroesSurviving() > 0) {
                hero1.setState(new BerserkState());

                List<TowerFloor> finalFloor = Arrays.asList(
                        new BossFloor()
                );

                TowerRunner bossRunner = new TowerRunner(finalFloor);
                TowerRunResult bossResult = bossRunner.run(party);

                int totalFloorsCleared = firstResult.getFloorsCleared() + bossResult.getFloorsCleared();
                int heroesAlive = bossResult.getHeroesSurviving();
                boolean reachedTop = totalFloorsCleared == 4;

                System.out.println("\n=== FINAL RESULT ===");
                System.out.println("Floors cleared: " + totalFloorsCleared);
                System.out.println("Heroes alive: " + heroesAlive);
                System.out.println("Reached top: " + reachedTop);
            }
         }
}
