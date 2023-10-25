package view;

import java.util.Scanner;

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
}
