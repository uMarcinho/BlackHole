package br.com.dio;

import br.com.dio.model.Board;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testBoardInitialization() {
        Board board = new Board();
        assertNotNull(board, "O tabuleiro não deveria ser nulo");
    }

    @Test
    void testValidMove() {
        Board board = new Board();
        boolean result = board.setNumber(0, 0, 5);
        assertTrue(result, "Deveria permitir jogada válida");
    }

    @Test
    void testInvalidMoveOutOfBounds() {
        Board board = new Board();
        boolean result = board.setNumber(10, 10, 5);
        assertFalse(result, "Não deveria permitir jogada fora do tabuleiro");
    }
}