package com.example.finalproject1;

public class DungeonRoom {
    private int npcHitPoints;
    private int npcStrength;
    private int npcDexterity;
    private int npcIntelligence;
    private Boolean blockedRoom;
    private Boolean npcInRoom;
    private int goldInRoom;

    public DungeonRoom() {
        npcHitPoints = 0;
        npcStrength = 0;
        npcDexterity = 0;
        npcIntelligence = 0;
        this.blockedRoom = true;
        this.npcInRoom = true;
        this.goldInRoom = goldInRoom;


    }

    public void setNpcHitPoints(int npcHitPoints) {
        this.npcHitPoints = npcHitPoints;
    }

    public int getNpcHitPoints() {
        npcHitPoints = (int)(Math.random()* 6 + 1);
        return npcHitPoints;
    }

    public int getNpcStrength() {
        npcStrength = npcHitPoints * 2;
        return npcStrength;
    }

    public int getNpcDexterity() {
        npcDexterity = npcHitPoints * 2;
        return npcDexterity;
    }

    public int getNpcIntelligence() {
        npcIntelligence = npcHitPoints * 2;
        return npcIntelligence;
    }

    public Boolean getBlockedRoom() {
        int randomChanceOfBlockedRoom = (int) (Math.random() * 2 + 1);
        if (randomChanceOfBlockedRoom == 1) {
            blockedRoom = true;
        } else {
            blockedRoom = false;
        }
        return blockedRoom;
    }

    public Boolean getNpcInRoom() {
        int npcChanceOfSpawning = (int) (Math.random() * 2 + 1);
        if (npcChanceOfSpawning == 1){
            npcInRoom = true;
        } else {
            npcInRoom = false;
        }
        return npcInRoom;
    }

    public int getGoldInRoom() {
        goldInRoom = (int)(Math.random()* 20 + 1);
        return goldInRoom;
    }
}