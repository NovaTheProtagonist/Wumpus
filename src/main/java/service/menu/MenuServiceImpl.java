package service.menu;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.board.Board;
import model.board.BoardTile;
import model.board.TileType;
import model.coordinate.Coordinate;
import model.hero.FacingDirection;
import model.hero.Hero;
import model.hero.Position;
import persistence.PlayerRepository;
import persistence.PlayerResult;


public class MenuServiceImpl implements MenuService {

    private final PlayerRepository playerRepository;

    private String playerName;

    private Optional<Board> board = Optional.empty();

    private Optional<Hero> hero = Optional.empty();

    public MenuServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public void readFile(String fileName) {
        InputStream boardFile = this.getClass().getClassLoader().getResourceAsStream(fileName);

        if (boardFile != null) {
            Scanner reader = new Scanner(boardFile);
            String firstLine = reader.nextLine();
            String[] gameConfig = firstLine.split(" ");
            Coordinate playerStartCoordinate = new Coordinate(gameConfig[1].charAt(0), Integer.parseInt(gameConfig[2]));
            Position startPosition = new Position(playerStartCoordinate);
            int boardSize = Integer.parseInt(gameConfig[0]);
            Optional<Board> newBoard = Optional.of(new Board(Integer.parseInt(gameConfig[0])));

            int playerArrowCount = 0;

            for (int i = 0; i < boardSize; i++) {
                String line = reader.nextLine();
                for (int j = 0; j < boardSize; j++) {
                    newBoard.get().setBoardTile(i, j, line.charAt(j));
                    if (line.charAt(j) == TileType.WUMPUS.getShortName()) {
                        playerArrowCount++;
                    }
                }
            }

            FacingDirection facingDirection;
            facingDirection = Arrays.stream(FacingDirection.values())
                    .filter((FacingDirection f) -> f.name().charAt(0) == gameConfig[3].charAt(0))
                    .findAny()
                    .orElseThrow();

            BoardTile spawnTile = newBoard.get().getBoardTile(startPosition);
            spawnTile.setType(TileType.SPAWN);
            this.board = newBoard;
            Hero hero = new Hero();
            hero.setName(this.playerName);
            hero.setFacingDirection(facingDirection);
            hero.setArrows(playerArrowCount);
            hero.setPosition(startPosition);
            this.hero = Optional.of(hero);
        }
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
    public Optional<Board> getBoard() {
        return board;
    }

    @Override
    public Optional<Hero> getHero() {
        return hero;
    }

    @Override
    public List<PlayerResult> getTopList() {
        return playerRepository.selectAll().stream()
                .sorted(Comparator.comparingInt(PlayerResult::getWinCount).reversed())
                .limit(5).toList();
    }
}
