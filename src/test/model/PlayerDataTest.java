package model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerDataTest {
    PlayerData p;
    @BeforeEach
    void beforeEach() {
        p = new PlayerData(20,5,0,1);
    }

    @Test
    void retrievePlayerDataTest() {
        assertEquals(20, p.getHealth());
        assertEquals(5, p.getAttack());
        assertEquals(0, p.getPositionX());
        assertEquals(1, p.getPositionY());
    }
}
