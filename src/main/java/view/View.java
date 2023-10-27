package view;

import command.Command;

import java.util.List;
import java.util.Map;

public interface View {
    void printStartMessage();
    String requestPlayerName();
    String requestMenuCommand(List<String> commandList);
    String requestFileName();
}
