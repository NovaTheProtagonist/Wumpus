package service.menu;

import model.board.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistance.PlayerRepository;
import persistance.PlayerRepositoryImpl;

import java.util.Optional;


class MenuServiceImplTest {

    PlayerRepository playerRepository = new PlayerRepositoryImpl("","","");

    MenuService menuService = new MenuServiceImpl(playerRepository);

    String expectedBoard =
            "WWWWWW\n" +
            "W___PW\n" +
            "WUGP_W\n" +
            "W____W\n" +
            "WS_P_W\n" +
            "WWWWWW\n";

    @Test
    void testReadFileShouldCreateCorrectBoardTilesWhenInputIsValid() {
        String testFileName = "Input1.txt";

        menuService.readFile(testFileName);

        String result = menuService.getBoard().orElseThrow().toString();

        Assertions.assertEquals(expectedBoard, result);
    }

    @Test
    void testReadFileShouldThrowExceptionWhenInputIsNull() {
        String testFileName = null;

        Assertions.assertThrows(NullPointerException.class, () -> menuService.readFile(testFileName));
    }

    @Test
    void testReadFileShouldReturnEmptyWhenInputIsNotValid() {
        String testFileName = "Test.txt";

        Optional<Board> expected = Optional.empty();

        menuService.readFile(testFileName);

        Optional<Board> result = menuService.getBoard();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testCachePlayerNameShouldSaveValidInput() {
        String testInput = "Péter";

        String result = menuService.cachePlayerName(testInput);

        Assertions.assertEquals(testInput, result);
    }

}