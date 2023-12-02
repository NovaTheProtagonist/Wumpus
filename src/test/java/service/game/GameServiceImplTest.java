package service.game;

import model.board.Board;
import model.board.BoardTile;
import model.board.TileType;
import model.hero.FacingDirection;
import model.hero.Hero;
import model.hero.Position;
import model.hero.Rotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {

    GameService gameService = new GameServiceImpl();

    Board board;

    Hero hero;

    int startingArrows = 3;

    @BeforeEach
    void beforeEach(){
        board = new Board(5);
        Position spawnPosition = new Position(2,3);
        hero = new Hero();
        hero.setPosition(spawnPosition);
        hero.setFacingDirection(FacingDirection.NORTH);
        hero.setArrows(startingArrows);
        gameService.setBoard(board);
        gameService.setHero(hero);
        gameService.startGame();
    }

    @Test
    void testMovePlayerShouldMoveToNextTileWhenNextTileIsEmpty() {
        Position expectedPosition = new Position(2,2);
        BoardTile expectedTile = gameService.getBoard().getBoardTile(expectedPosition);

        gameService.movePlayer();
        Position actualPosition = gameService.getHero().getPosition();
        BoardTile actualTile = gameService.getBoard().getBoardTile(actualPosition);

        Assertions.assertEquals(expectedPosition, actualPosition);
        Assertions.assertEquals(expectedTile, actualTile);
    }

    @Test
    void testMovePlayerShouldMoveToNextTileWhenNextTileIsSpawn() {
        Position expectedPosition = new Position(2,3);
        BoardTile expectedTile = gameService.getBoard().getBoardTile(expectedPosition);

        hero.setPosition(new Position(2,4));
        gameService.movePlayer();
        Position actualPosition = gameService.getHero().getPosition();
        BoardTile actualTile = gameService.getBoard().getBoardTile(actualPosition);

        Assertions.assertEquals(expectedPosition, actualPosition);
        Assertions.assertEquals(expectedTile, actualTile);
        Assertions.assertEquals(TileType.SPAWN, actualTile.getType());
    }

    @Test
    void testMovePlayerShouldMoveToNextTileAndWinWhenNextTileIsSpawnAndPlayerHasGold() {
        Position expectedPosition = new Position(2,3);
        BoardTile expectedTile = gameService.getBoard().getBoardTile(expectedPosition);

        hero.setPosition(new Position(2,4));
        hero.grabGold();
        gameService.movePlayer();
        Position actualPosition = gameService.getHero().getPosition();
        BoardTile actualTile = gameService.getBoard().getBoardTile(actualPosition);

        Assertions.assertEquals(expectedPosition, actualPosition);
        Assertions.assertEquals(expectedTile, actualTile);
        Assertions.assertEquals(TileType.SPAWN, actualTile.getType());
    }



    @Test
    void testMovePlayerShouldMoveToNextTileAndPickUpGoldWhenNextTileIsGold() {
        Position expectedPosition = new Position(2,2);
        BoardTile expectedTile = gameService.getBoard().getBoardTile(expectedPosition);
        expectedTile.setType(TileType.GOLD);

        gameService.movePlayer();
        Position actualPosition = gameService.getHero().getPosition();
        BoardTile actualTile = gameService.getBoard().getBoardTile(actualPosition);

        Assertions.assertEquals(expectedPosition, actualPosition);
        Assertions.assertEquals(expectedTile, actualTile);
        Assertions.assertEquals(TileType.EMPTY, actualTile.getType());
        Assertions.assertTrue(hero.hasGold());
    }
    @Test
    void testMovePlayerShouldMoveToNextTileAndLoseAnArrowWhenNextTileIsAPit() {
        Position expectedPosition = new Position(2,2);
        BoardTile expectedTile = gameService.getBoard().getBoardTile(expectedPosition);
        expectedTile.setType(TileType.PIT);

        gameService.movePlayer();
        Position actualPosition = gameService.getHero().getPosition();
        BoardTile actualTile = gameService.getBoard().getBoardTile(actualPosition);

        Assertions.assertEquals(expectedPosition, actualPosition);
        Assertions.assertEquals(expectedTile, actualTile);
        Assertions.assertEquals(TileType.PIT, actualTile.getType());
        Assertions.assertEquals(startingArrows - 1, hero.getArrows());
    }

    @Test
    void testMovePlayerShouldMoveToNextTileAndLoseWhenNextTileIsAPitAndArrowCountEqualsZero() {
        Position expectedPosition = new Position(2,2);
        BoardTile expectedTile = gameService.getBoard().getBoardTile(expectedPosition);
        expectedTile.setType(TileType.PIT);

        hero.setArrows(0);
        gameService.movePlayer();
        Position actualPosition = gameService.getHero().getPosition();
        BoardTile actualTile = gameService.getBoard().getBoardTile(actualPosition);

        Assertions.assertEquals(expectedPosition, actualPosition);
        Assertions.assertEquals(expectedTile, actualTile);
        Assertions.assertEquals(TileType.PIT, actualTile.getType());
        Assertions.assertEquals(GameStatus.LOSE, gameService.getGameStatus());
        Assertions.assertEquals(0, hero.getArrows());
    }

    @Test
    void testMovePlayerShouldMoveToNextTile() {
        Position expectedPosition = new Position(2,2);
        BoardTile expectedTile = gameService.getBoard().getBoardTile(expectedPosition);
        expectedTile.setType(TileType.PIT);

        hero.setArrows(0);
        gameService.movePlayer();
        Position actualPosition = gameService.getHero().getPosition();
        BoardTile actualTile = gameService.getBoard().getBoardTile(actualPosition);

        Assertions.assertEquals(expectedPosition, actualPosition);
        Assertions.assertEquals(expectedTile, actualTile);
        Assertions.assertEquals(TileType.PIT, actualTile.getType());
        Assertions.assertEquals(GameStatus.LOSE, gameService.getGameStatus());
        Assertions.assertEquals(0, hero.getArrows());
    }



}