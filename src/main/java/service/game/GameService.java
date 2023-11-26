package service.game;

import model.board.Board;
import model.board.TileType;
import model.hero.Hero;

public interface GameService {
    Board getBoard();

    Hero getHero();

    void setBoard(Board board);

    void setHero(Hero hero);

    void surrender();

    void movePlayer();

    void rotatePlayer();

    void shootArrow();

    TileType getPlayerTileType();

}
