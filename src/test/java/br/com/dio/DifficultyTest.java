package br.com.dio;

import br.com.dio.model.Difficulty;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DifficultyTest {

    @Test
    void testDifficultyValues() {
        assertNotNull(Difficulty.EASY);
        assertNotNull(Difficulty.MEDIUM);
        assertNotNull(Difficulty.HARD);
    }
}