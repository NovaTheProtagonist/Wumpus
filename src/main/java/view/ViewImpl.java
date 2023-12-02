package view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.board.Board;
import model.hero.Hero;
import model.hero.Position;
import model.hero.Rotation;

public class ViewImpl implements View {
    @Override
    public void printStartMessage() {
        System.out.println("Welcome to WumpusLabyrinth\n");
    }

    @Override
    public String requestPlayerName() {
        System.out.println("Please Enter Your Name:\n");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        System.out.println("Welcome " + playerName + "!");
        return playerName;
    }

    @Override
    public String requestMenuCommand(List<String> commandList) {
        System.out.println("MENU\n");
        return requestCommand(commandList);
    }

    @Override
    public String requestGameCommand(List<String> commandList) {
        return requestCommand(commandList);
    }

    private static String requestCommand(List<String> commandList) {
        System.out.println("Please select a command:");
        commandList.forEach((String command) -> System.out.println("- " + command));
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String requestFileName() {
        System.out.println("Please type in the name of the file:\n");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        System.out.println("Loading File: " + fileName);
        return fileName;
    }

    @Override
    public void printCommandNotFound() {
        System.out.println("Invalid Command");
    }

    @Override
    public void printBoard(Board board) {
        System.out.println(board);
    }

    @Override
    public void printError(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

    @Override
    public void printHero(Hero hero) {
        System.out.println(hero.getName());
        Position heroPosition = hero.getPosition();
        System.out.println("Position: " + (heroPosition.getColumn() + 1) + ", " + (heroPosition.getRow() + 1));
        System.out.println("Facing: " + hero.getFacingDirection().name());
        System.out.println("Ammo: " + hero.getArrows());
        System.out.println(hero.hasGold() ? "You are in possession of the Gold" : "You do not possess the Gold");
        System.out.println();
    }

    @Override
    public void printWin() {
        System.out.println("You have secured the gold! Congratulations, you win!");

    }

    @Override
    public void printLose() {
        System.out.println("You have died");
    }

    @Override
    public void printInstructions() {
        System.out.println("The goal of the game is to pick up the gold (G) and bring it back to your starting location (S)!");
        System.out.println("If you step onto a tile with a pit (P) you lose an arrow unless you don't have any arrows left," +
                " in this case you die");
        System.out.println("If you shoot an arrow and it hits a Wumpus (U), the Wumpus (U) will die and you lose an arrow");
        System.out.println("If your arrow doesn't hit a Wumpus (U) it will break upon hitting a wall (W) " +
                "resulting in you losing an arrow");
        System.out.println("If you step onto a tile where a Wumpus (U) resides you shall perish");
        System.out.println("Good luck!\n");
    }

    @Override
    public Optional<Rotation> requestRotation() {
        System.out.println("Please choose which way to rotate L or R: \n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Optional<Rotation> rotation = Arrays.stream(Rotation.values())
                .filter(rotation1 -> rotation1.name().startsWith(input)).findAny();
        return rotation;
    }
}
