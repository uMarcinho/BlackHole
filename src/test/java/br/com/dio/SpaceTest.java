package br.com.dio;

import br.com.dio.model.Space;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpaceTest {

    @Test
    void testEmptySpace() {
        Space space = new Space();
        assertEquals(0, space.getValue(), "Um espaço vazio deve ter valor 0");
    }

    @Test
    void testSetValue() {
        Space space = new Space();
        space.setValue(7);
        assertEquals(7, space.getValue(), "O valor deveria ser atualizado para 7");
    }
}