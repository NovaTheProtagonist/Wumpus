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
import persistence.PlayerRepository;
import persistence.PlayerRepositoryImpl;

class GameServiceImplTest {

    PlayerRepository playerRepository = new PlayerRepositoryImpl("");

    GameService gameService = new GameServiceImpl(playerRepository);

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
        Assertions.assertEquals(GameStatus.WIN, gameService.getGameStatus());
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
    void testMovePlayerShouldMoveToNextTileAndLoseWhenNextTileIsAWumpus() {
        Position expectedPosition = new Position(2,2);
        BoardTile expectedTile = gameService.getBoard().getBoardTile(expectedPosition);
        expectedTile.setType(TileType.WUMPUS);

        gameService.movePlayer();
        Position actualPosition = gameService.getHero().getPosition();
        BoardTile actualTile = gameService.getBoard().getBoardTile(actualPosition);

        Assertions.assertEquals(expectedPosition, actualPosition);
        Assertions.assertEquals(expectedTile, actualTile);
        Assertions.assertEquals(TileType.WUMPUS, actualTile.getType());
        Assertions.assertEquals(GameStatus.LOSE, gameService.getGameStatus());
    }

    @Test
    void testRotatePlayerShouldRotateCorrectly() {
        FacingDirection expected1 = FacingDirection.EAST;
        FacingDirection expected2 = FacingDirection.WEST;

        gameService.rotatePlayer(Rotation.RIGHT);

        Assertions.assertEquals(expected1, hero.getFacingDirection());

        hero.setFacingDirection(FacingDirection.NORTH);
        gameService.rotatePlayer(Rotation.LEFT);

        Assertions.assertEquals(expected2, hero.getFacingDirection());
    }

    @Test
    void testShootArrowShouldNotLoseAnArrowAndShouldNotHitWumpusWhenArrowCountIsZero() {
        BoardTile boardTile = board.getBoardTile(new Position(2,2));
        boardTile.setType(TileType.WUMPUS);
        hero.setArrows(0);
        gameService.shootArrow();

        Assertions.assertEquals(0, hero.getArrows());
        Assertions.assertEquals(TileType.WUMPUS, boardTile.getType());
    }

    @Test
    void testShootArrowShouldKillWumpusAndLoseAnArrowWhenPlayerHasArrows() {
        BoardTile boardTile = board.getBoardTile(new Position(2,2));
        boardTile.setType(TileType.WUMPUS);
        gameService.shootArrow();

        Assertions.assertEquals(startingArrows - 1, hero.getArrows());
        Assertions.assertEquals(TileType.EMPTY, boardTile.getType());
    }

    @Test
    void testShootArrowShouldHitWallAndLoseAnArrowWhenPlayerHasArrowsAndThereIsNoWumpusInTheWay() {
        BoardTile boardTile = board.getBoardTile(new Position(2,0));
        boardTile.setType(TileType.WALL);
        gameService.shootArrow();

        Assertions.assertEquals(startingArrows - 1, hero.getArrows());
        Assertions.assertEquals(TileType.WALL, boardTile.getType());
    }

    @Test
    void testSurrenderShouldClearDataAndResultInALose() {
        gameService.surrender();

        Assertions.assertNull(gameService.getBoard());
        Assertions.assertNull(gameService.getHero());
        Assertions.assertEquals(GameStatus.LOSE, gameService.getGameStatus());
    }
}