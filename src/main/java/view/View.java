package view;

import java.util.List;


public interface View {
    void printStartMessage();

    String requestPlayerName();

    String requestMenuCommand(List<String> commandList);

    String requestFileName();
}
