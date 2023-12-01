package service.game;

import model.board.Board;
import model.board.TileType;
import model.hero.Hero;
import model.hero.Rotation;

public interface GameService {
    Board getBoard();

    Hero getHero();

    void setBoard(Board board);

    void setHero(Hero hero);

    void surrender();

    void movePlayer();

    void rotatePlayer(Rotation rotation);

    void shootArrow();

    TileType getPlayerTileType();

    void spawnPlayer();

}
