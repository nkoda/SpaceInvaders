package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.Map;

public class BulletTest {
    Bullet b;

    @BeforeEach
    void beforeEach() {
        b = new Bullet(2,3);
    }

    @Test
    void bulletTest() {
        assertEquals(2, b.getPosX());
        assertEquals(3, b.getPosY());
        b.move();
        assertEquals(1, b.getPosY());
    }


    @Test
    void bulletVelocityTest() {
        b.setDy(1);
        b.setDx(2);

        assertEquals(2,b.getDx());
        assertEquals(1,b.getDy());
    }
}
