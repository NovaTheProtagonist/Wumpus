package view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.board.Board;

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
        System.out.println("Please type in the name of the file:\n");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        System.out.println("Loading File: " + fileName);
        return fileName;
    }

    @Override
    public void printBoard(Optional<Board> board) {
        if (board.isPresent()) {
            System.out.println(board.get());
        } else {
            System.out.println("Unable to read file");
        }
    }

    @Override
    public void printCommandNotFound() {
        System.out.println("Invalid Command");
    }
}
