package com.narxoz.rpg.tower;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.TowerFloor;
import com.narxoz.rpg.floor.FloorResult;

import java.util.List;
public class TowerRunner {
    private final List<TowerFloor> floors;

    public TowerRunner(List<TowerFloor> floors) {
        this.floors = floors;
    }

    public TowerRunResult run(List<Hero> party) {
        int cleared = 0;

        for (TowerFloor floor : floors) {

            if (party.stream().noneMatch(Hero::isAlive)) {
                break;
            }

            FloorResult result = floor.explore(party);
            System.out.println(result.getSummary());

            if (result.isCleared()) {
                cleared++;
            } else {
                break;
            }
        }

        int alive = (int) party.stream().filter(Hero::isAlive).count();
        boolean reachedTop = cleared == floors.size();

        return new TowerRunResult(cleared, alive, reachedTop);
    }
}
