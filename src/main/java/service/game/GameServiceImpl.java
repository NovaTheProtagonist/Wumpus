package service.game;

import model.board.Board;
import model.board.BoardTile;
import model.board.TileType;
import model.hero.FacingDirection;
import model.hero.Hero;
import model.hero.Position;
import model.hero.Rotation;

public class GameServiceImpl implements GameService {

    private Board board;

    private Hero hero;

    private GameStatus gameStatus;

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Hero getHero() {
        return hero;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void surrender() {
        clearBoard();
        lose();
    }

    @Override
    public void movePlayer() {
        FacingDirection facingDirection = hero.getFacingDirection();
        Position currentPosition = hero.getPosition();
        BoardTile currentTile = board.getBoardTile(currentPosition);
        BoardTile nextTile = board.getBoardTile(currentPosition.nextInDirection(facingDirection));
        switch (nextTile.getType()) {
            case WALL -> {
                return;
            }
            case PIT -> handleStepOnPit();

            case WUMPUS -> handleStepOnWumpus();

            case GOLD -> handleStepOnGold(nextTile);

            case SPAWN -> handleStepOnSpawn();

            default -> {

            }

        }
        stepOnNewTile(currentTile, nextTile);
    }

    private void handleStepOnSpawn() {
        if (hero.hasGold()) {
            gameStatus = GameStatus.WIN;
        }
    }

    private void handleStepOnGold(BoardTile nextTile) {
        hero.grabGold();
        nextTile.setType(TileType.EMPTY);
    }

    private void handleStepOnWumpus() {
        lose();
    }

    private void handleStepOnPit() {
        int arrows = hero.getArrows();
        if (arrows <= 0) {
            lose();
        } else {
            hero.setArrows(arrows - 1);
        }
    }

    private void stepOnNewTile(BoardTile currentTile, BoardTile nextTile) {
        currentTile.setOccupied(false);
        hero.stepForward();
        nextTile.setOccupied(true);
    }

    private void lose() {
        gameStatus = GameStatus.LOSE;
    }

    @Override
    public void rotatePlayer(Rotation rotation) {
        hero.rotate(rotation);
    }

    @Override
    public void shootArrow() {
        int currentArrows = hero.getArrows();
        if (currentArrows <= 0) {
            return;
        }
        hero.setArrows(currentArrows - 1);
        FacingDirection facingDirection = hero.getFacingDirection();
        Position heroPosition = hero.getPosition();
        Position nextPosition = heroPosition.nextInDirection(facingDirection);
        while (true) {
            BoardTile nextTile = board.getBoardTile(nextPosition);
            if (nextTile.getType() == TileType.WALL) {
                break;
            }
            if (nextTile.getType() == TileType.WUMPUS) {
                nextTile.setType(TileType.EMPTY);
                break;
            }
            nextPosition = nextPosition.nextInDirection(facingDirection);
        }
    }


    private void spawnPlayer() {
        board.spawnHero(hero);
        BoardTile spawnTile = board.getBoardTile(hero.getPosition());
        spawnTile.setType(TileType.SPAWN);
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void startGame() {
        gameStatus = GameStatus.ONGOING;
        spawnPlayer();
    }

    @Override
    public void clearBoard() {
        board = null;
        hero = null;
    }
}
