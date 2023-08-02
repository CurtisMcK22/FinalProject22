package com.example.finalproject1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    private Button startGameButton;
    @FXML
    private TextArea gameDialog;
    @FXML
    private Circle playerModel;
    @FXML
    private Button upMovementButton;
    @FXML
    private Button downMovementButton;
    @FXML
    private Button leftMovementButton;
    @FXML
    private Button rightMovementButton;
    @FXML
    private Button searchMovementButton;
    @FXML
    private Button attackMovementButton;
    @FXML
    private Button runMovementButton;
    @FXML
    private Button sleepMovementButton;
    @FXML
    private Label playerHp;
    @FXML
    private Label playerStrength;
    @FXML
    private Label playerDexterity;
    @FXML
    private Label playerIntelligence;
    @FXML
    private Label playerTotalGold;
    @FXML
    private Label NpcHP;
    @FXML
    private Label NpcStrength;
    @FXML
    private Label NpcDexterity;
    @FXML
    private Label NpcIntelligence;

    boolean startGameButtonHasBeenPressed = false;
    boolean npcInRoomChecker = false;

    DungeonRoom DungeonRoom = new DungeonRoom();
    PlayerCharacter playerCharacter = new PlayerCharacter();

    public void startGame(ActionEvent actionEvent) {
        startGameButtonHasBeenPressed = true;
        upMovementButton.setDisable(true);
        leftMovementButton.setDisable(true);
        startGameButton.setDisable(true);
        attackMovementButton.setDisable(true);
        runMovementButton.setDisable(true);
        gameDialog.clear();
        gameDialog.setEditable(false);

        playerHp.setText(String.valueOf(playerCharacter.getHitPoints()));
        playerStrength.setText(String.valueOf(playerCharacter.getStrength()));
        playerDexterity.setText(String.valueOf(playerCharacter.getDexterity()));
        playerIntelligence.setText(String.valueOf(playerCharacter.getIntelligence()));
        playerTotalGold.setText(String.valueOf(playerCharacter.getTotalGold()));
    }
    public void playerUpMovement(ActionEvent actionEvent) {

        playerModel.getCenterX();
        gameDialog.appendText(String.valueOf(playerModel.getCenterX()));

        gameDialog.appendText("\n You moved up!");

    }
    public void playerDownMovement(ActionEvent actionEvent) {
        gameDialog.appendText("\n You moved down!");
        searchMovementButton.setDisable(false);

    }
    public void playerLeftMovement(ActionEvent actionEvent) {
        gameDialog.appendText("\n You moved left!");

    }
    public void playerRightMovement(ActionEvent actionEvent) {
        gameDialog.appendText("\n You moved right!");
        searchMovementButton.setDisable(false);


    }

    public void playerSearchMovement(ActionEvent actionEvent) {
        if (startGameButtonHasBeenPressed) {
            npcInRoomChecker = DungeonRoom.getNpcInRoom();
            if (!npcInRoomChecker) {
                gameDialog.appendText("\n You found a monster! \n You must fight or run!");
                sleepMovementButton.setDisable(true);
                searchMovementButton.setDisable(true);
                attackMovementButton.setDisable(false);
                runMovementButton.setDisable(false);
                NpcHP.setText(String.valueOf(DungeonRoom.getNpcHitPoints()));
                NpcStrength.setText(String.valueOf(DungeonRoom.getNpcStrength()));
                NpcDexterity.setText(String.valueOf(DungeonRoom.getNpcDexterity()));
                NpcIntelligence.setText(String.valueOf(DungeonRoom.getNpcIntelligence()));
            } else {
                attackMovementButton.setDisable(true);
                searchMovementButton.setDisable(true);
                runMovementButton.setDisable(true);
                gameDialog.appendText("\n Searching for gold...");
                int droll = (int) (Math.random() * 20 + 1);
                if (droll < playerCharacter.getIntelligence()) {
                    int goldInRoom = (int) (Math.random() * 20 + 1);
                    playerCharacter.setTotalGold(playerCharacter.getTotalGold() + goldInRoom);
                    gameDialog.appendText("\n You have found " + goldInRoom + " gold coins!");
                    playerTotalGold.setText(String.valueOf(playerCharacter.getTotalGold()));

                } else {
                    gameDialog.appendText("\n Intelligence too low, no gold was found");
                    gameDialog.appendText("\n Please move to a new dungeon to continue \n searching for gold!");

                }
            }
        }
    }
    public void playerRunMovement(ActionEvent actionEvent) {
        if (startGameButtonHasBeenPressed) {
            if (!npcInRoomChecker) {
                gameDialog.appendText("\n You attempt to run away from the monster");
                int roll = (int) (Math.random() * 20 + 1);
                if (roll < DungeonRoom.getNpcIntelligence()) {
                    gameDialog.appendText("\n The monster sees you running!");
                    int newPlayerHitpoints = playerCharacter.getHitPoints() - (int) (Math.random() * 15 + 1);
                    playerCharacter.setHitPoints(newPlayerHitpoints);
                    playerHp.setText(String.valueOf(playerCharacter.getHitPoints()));
                    gameDialog.appendText("\n The monster lands a hit on you before you escaped!");
                    gameDialog.appendText("\n Please move to a new dungeon to search or heal!");


                } else {
                    gameDialog.appendText("\n You escaped safely because the monster \n was unable to see you!");
                    gameDialog.appendText("\n Please move to a new dungeon to continue \n searching for gold!");

                }
                searchMovementButton.setDisable(true);
                attackMovementButton.setDisable(true);
                runMovementButton.setDisable(true);

            }
        }
    }
    public void playerAttackMovement(ActionEvent actionEvent) {
        if (startGameButtonHasBeenPressed) {
            searchMovementButton.setDisable(true);
            int roll = (int) (Math.random() * 20 + 1);
            if (roll >= DungeonRoom.getNpcDexterity()) {
                int newNpcHitpoints = playerCharacter.getStrength() / 3 - DungeonRoom.getNpcHitPoints();
                DungeonRoom.setNpcHitPoints(newNpcHitpoints);
                NpcHP.setText(String.valueOf(newNpcHitpoints));
                gameDialog.appendText("\n Your attack landed, \n you did damage towards the monster!");
                if (DungeonRoom.getNpcHitPoints() <= 0) {
                    int roll2 = (int) (Math.random() * 20 + 1);
                    if (roll2 >= playerCharacter.getDexterity()) {
                        int newPlayerHitpoints = playerCharacter.getHitPoints() - ((DungeonRoom.getNpcStrength() / 3));
                        playerCharacter.setHitPoints(newPlayerHitpoints);
                        playerHp.setText(String.valueOf(playerCharacter.getHitPoints()));
                        gameDialog.appendText("\n Monster with a big attack");
                    }
                }
                if (newNpcHitpoints <= 0) {
                    runMovementButton.setDisable(true);
                    gameDialog.appendText("\n You have defeated the monster!");
                    gameDialog.appendText("\n Searching for gold...");
                    int roll1 = (int) (Math.random() * 20 + 1);
                    if (roll1 < playerCharacter.getIntelligence()) {
                        int currentGoldInRoom = DungeonRoom.getGoldInRoom();
                        playerCharacter.setTotalGold(playerCharacter.getTotalGold() + currentGoldInRoom);
                        gameDialog.appendText("\n You have found " + currentGoldInRoom + " gold coins!" +
                                " \n \n You must move to another dungeon to continue searching for gold elsewhere!");
                        playerTotalGold.setText(String.valueOf(playerCharacter.getTotalGold()));
                        npcInRoomChecker = false;
                        attackMovementButton.setDisable(true);
                    } else {
                        gameDialog.appendText("\n You are not smart enough to find the gold \n in this dungeon!" +
                                "\n \n You must move to another dungeon to continue searching for gold elsewhere!");
                        attackMovementButton.setDisable(true);
                    }
                }
            } else {
                gameDialog.appendText("\n Your attack Missed!");

                if (DungeonRoom.getNpcHitPoints() > 0) {
                    int roll2 = (int) (Math.random() * 20 + 1);
                    if (roll2 >= playerCharacter.getDexterity()) {
                        int newPlayerHitpoints = playerCharacter.getHitPoints() - ((DungeonRoom.getNpcStrength() / 3));
                        playerCharacter.setHitPoints(newPlayerHitpoints);
                        playerHp.setText(String.valueOf(playerCharacter.getHitPoints()));
                        gameDialog.appendText("\n The monster landed an attack on you!");
                    } else {
                        int newPlayerHitpointsIfMonsterMisses = playerCharacter.getHitPoints() - 1;
                        playerCharacter.setHitPoints(newPlayerHitpointsIfMonsterMisses);
                        playerHp.setText(String.valueOf(newPlayerHitpointsIfMonsterMisses));
                        gameDialog.appendText("The monster attack was weak, only took 1 damage!");
                    }
                }
            }
        }
    }
    public void playerSleepMovement(ActionEvent actionEvent) {
        if (startGameButtonHasBeenPressed) {
            gameDialog.appendText("\n Resting for HP...");
            gameDialog.appendText("\n HP has been restored to full!");
            gameDialog.appendText("\n Now go collect more gold!");
            playerCharacter.setHitPoints(20);
            playerHp.setText(String.valueOf(20));
        }
    }
    public void endGame(ActionEvent actionEvent) {

        System.exit(0);
    }
}