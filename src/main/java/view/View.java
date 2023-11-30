package view;

import java.util.List;

import model.board.Board;
import model.hero.Hero;


public interface View {
    void printStartMessage();

    String requestPlayerName();

    String requestMenuCommand(List<String> commandList);

    String requestGameCommand(List<String> commandList);

    String requestFileName();

    void printCommandNotFound();

    void printBoard(Board board);

    void printError(String errorMessage);

    void printHero(Hero hero);

    void printWin();

    void printLose();

    void printInstructions();
}
