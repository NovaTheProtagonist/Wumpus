package view;

import model.board.Board;

import java.util.List;


public interface View {
    void printStartMessage();

    String requestPlayerName();

    String requestMenuCommand(List<String> commandList);

    String requestFileName();

    void printBoard(Board board);

    void printCommandNotFound();
}
