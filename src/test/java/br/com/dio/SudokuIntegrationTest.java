package br.com.dio;

import br.com.dio.model.Board;
import br.com.dio.util.BoardGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuIntegrationTest {

    @Test
    void testBoardGeneratorCreatesValidBoard() {
        Board board = BoardGenerator.createBoard();
        assertNotNull(board, "O gerador deve retornar um tabuleiro válido");
    }
}