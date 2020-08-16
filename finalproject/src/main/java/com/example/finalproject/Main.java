package com.example.finalproject;

public class Main {
    public static void main(String[] args) {
        BattleComponent component = DaggerBattleComponent.create();
        War war = component.getWar();
        war.prepare();
        war.report();
    }
}
