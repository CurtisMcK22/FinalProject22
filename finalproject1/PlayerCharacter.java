package com.example.finalproject1;

public class PlayerCharacter {
    private int hitPoints;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int totalGold;

    public PlayerCharacter() {
        hitPoints = 20;
        strength = 0;
        dexterity = 0;
        intelligence = 0;
        totalGold = 0;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        int roll1;
        int roll2;
        int roll3;

        roll1 = (int)(Math.random()* 6 + 1);
        roll2 = (int)(Math.random()* 6 + 1);
        roll3 = (int)(Math.random()* 6 + 1);

        strength = roll1 + roll2 + roll3;
        return strength;
    }

    public int getDexterity() {
        int roll1;
        int roll2;
        int roll3;

        roll1 = (int)(Math.random()* 6 + 1);
        roll2 = (int)(Math.random()* 6 + 1);
        roll3 = (int)(Math.random()* 6 + 1);

        dexterity = roll1 + roll2 + roll3;
        return dexterity;
    }
    public int getIntelligence() {
        int roll1;
        int roll2;
        int roll3;

        roll1 = (int)(Math.random()* 6 + 1);
        roll2 = (int)(Math.random()* 6 + 1);
        roll3 = (int)(Math.random()* 6 + 1);

        intelligence = roll1 + roll2 + roll3;
        return intelligence;
    }
    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }
}
