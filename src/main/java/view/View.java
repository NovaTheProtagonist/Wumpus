package view;

import model.board.Board;

import java.util.List;
import java.util.Optional;


public interface View {
    void printStartMessage();

    String requestPlayerName();

    String requestMenuCommand(List<String> commandList);

    String requestFileName();

    void printBoard(Optional<Board> board);

    void printCommandNotFound();
}
