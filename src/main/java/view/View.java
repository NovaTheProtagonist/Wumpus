package view;

import java.util.List;
import java.util.Optional;

import model.board.Board;
import model.hero.Hero;
import model.hero.Rotation;
import persistence.PlayerResult;


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

    Optional<Rotation> requestRotation();

    void printTopList(List<PlayerResult> topList);
}
