package service.menu;

import model.board.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class MenuServiceImplTest {

    MenuService menuService = new MenuServiceImpl();

    String expectedBoard =
            "WWWWWW\n" +
            "W___PW\n" +
            "WUGP_W\n" +
            "W____W\n" +
            "W__P_W\n" +
            "WWWWWW\n";

    @Test
    void testReadFileShouldCreateCorrectBoardTilesWhenInputIsValid() {
        String testFileName = "Input1.txt";

        String result = menuService.readFile(testFileName).toString();

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

        Optional<Board> result = menuService.readFile(testFileName);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void cachePlayerName() {
    }
}