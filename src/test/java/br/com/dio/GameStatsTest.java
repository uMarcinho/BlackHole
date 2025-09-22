package br.com.dio;

import br.com.dio.model.GameStats;
import br.com.dio.model.GameStatusEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameStatsTest {

    @Test
    void testGameStatusDefault() {
        GameStats stats = new GameStats();
        assertEquals(GameStatusEnum.IN_PROGRESS, stats.getStatus(), "O jogo deve começar em progresso");
    }
}