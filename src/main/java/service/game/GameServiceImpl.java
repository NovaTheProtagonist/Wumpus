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
        board = null;
        hero = null;
    }

    @Override
    public void movePlayer() {
        Position currentPosition = hero.getPosition();
        BoardTile currentTile = board.getBoardTile(currentPosition);
        currentTile.setOccupied(false);
        hero.stepForward();
        Position nextPosition = hero.getPosition();
        BoardTile nextTile = board.getBoardTile(nextPosition);
        nextTile.setOccupied(true);
    }

    @Override
    public void rotatePlayer(Rotation rotation) {
        hero.rotate(rotation);
    }

    @Override
    public void shootArrow() {
        int currentArrows = hero.getArrows();
        hero.setArrows(currentArrows - 1);
        FacingDirection facingDirection = hero.getFacingDirection();
        Position heroPosition = hero.getPosition();
        while (true) {
            BoardTile nextTile = board.getBoardTile(heroPosition.nextInDirection(facingDirection));
            if (nextTile.getType() == TileType.WALL)
                break;
            if (nextTile.getType() == TileType.WUMPUS) {
                nextTile.setType(TileType.EMPTY);
                break;
            }
        }
    }

    @Override
    public TileType getPlayerTileType() {
        Position heroPosition = hero.getPosition();
        BoardTile playerTile = board.getBoardTile(heroPosition.getColumn(), heroPosition.getRow());
        return playerTile.getType();
    }

    @Override
    public void spawnPlayer() {
        board.spawnHero(hero);
    }
}
