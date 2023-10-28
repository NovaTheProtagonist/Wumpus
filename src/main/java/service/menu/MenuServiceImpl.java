package service.menu;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import model.board.Board;
import model.coordinate.Coordinate;
import model.hero.FacingDirection;



public class MenuServiceImpl implements MenuService {

    private String playerName;


    private boolean shouldRun = true;

    @Override
    public void startGame() {

    }

    @Override
    public void exit() {
        shouldRun = false;
    }

    @Override
    public Optional<Board> readFile(String fileName) {
        Optional<Board> newBoard = Optional.empty();
        InputStream boardFile = this.getClass().getClassLoader().getResourceAsStream(fileName);

        Scanner reader = null;
        if (boardFile != null) {
            reader = new Scanner(boardFile);
            String firstLine = reader.nextLine();
            String[] gameConfig = firstLine.split(" ");
            Coordinate playerStartCoordinate = new Coordinate(gameConfig[1].charAt(0), Integer.parseInt(gameConfig[2]));
            int boardSize = Integer.parseInt(gameConfig[0]);
            newBoard = Optional.of(new Board(Integer.parseInt(gameConfig[0]), playerStartCoordinate));

            for (int i = 0; i < boardSize; i++) {
                String line = reader.nextLine();
                for (int j = 0; j < boardSize; j++) {
                    newBoard.get().setBoardTile(i, j, line.charAt(j));

                }
            }

            FacingDirection facingDirection = Arrays.stream(FacingDirection.values())
                    .filter((FacingDirection f) -> f.name().charAt(0) == gameConfig[3].charAt(0))
                    .findAny()
                    .orElseThrow();
        }

        return newBoard;
    }

    @Override
    public void loadFromDatabase(String playerName) {

    }

    @Override
    public void saveToDatabase(String playerName, Integer winCount) {
        
    }

    @Override
    public String cachePlayerName(String playerName) {
        this.playerName = playerName;
        return this.playerName;
    }

    @Override
    public boolean shouldRun() {
        return shouldRun;
    }
}
