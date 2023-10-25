import java.util.Scanner;

public class WumpusGame {
    private String playerName;

    public void start() {
        printStartMessage();
        requestPlayerName();

    }

    private void printStartMessage() {
        System.out.println("Welcome to WumpusLabyrinth\n");
    }

    private void requestPlayerName() {
        System.out.println("Please Enter Your Name:\n");
        Scanner scanner = new Scanner(System.in);
        playerName = scanner.nextLine();
        System.out.println("Welcome " + playerName + "!");
    }

}
