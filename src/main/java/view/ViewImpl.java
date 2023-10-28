package view;

import java.util.List;
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

    @Override
    public String requestMenuCommand(List<String> commandList) {
        System.out.println("MENU\n");
        System.out.println("Please select a command:");
        commandList.forEach((String command) -> System.out.println("- " + command));
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String requestFileName() {
        return null;
    }
}
