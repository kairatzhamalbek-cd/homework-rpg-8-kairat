package com.narxoz.rpg.combatant;
import com.narxoz.rpg.state.HeroState;
import com.narxoz.rpg.state.NormalState;


public class Hero {

    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;
    private HeroState state;


    public Hero(String name, int hp, int attackPower, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.state = new NormalState();

    }

    public String getName()        { return name; }
    public int getHp()             { return hp; }
    public int getMaxHp()          { return maxHp; }
    public int getAttackPower()    { return attackPower; }
    public int getDefense()        { return defense; }
    public HeroState getState()    { return state; }
    public boolean isAlive()       { return hp > 0; }

    public void setState(HeroState state) {
        this.state = state;
        System.out.println(name + " is now " + state.getName());
    }


    public void takeDamage(int amount) {
        int finalDamage = state.modifyIncomingDamage(Math.max(0, amount));
        hp = Math.max(0, hp - finalDamage);
        System.out.println(name + " takes " + finalDamage + " damage. HP: " + hp + "/" + maxHp);

    }


    public void heal(int amount) {
        int healing = Math.max(0, amount);
        hp = Math.min(maxHp, hp + healing);
        System.out.println(name + " heals " + healing + " HP. HP: " + hp + "/" + maxHp);
    }
    public int calculateDamage() {
        return state.modifyOutgoingDamage(attackPower);
    }

    public void onTurnStart() {
        state.onTurnStart(this);
    }

    public void onTurnEnd() {
        state.onTurnEnd(this);
    }

    public boolean canAct() {
        return isAlive() && state.canAct();
    }

}
