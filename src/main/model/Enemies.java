package model;


import persistence.Saveable;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;


//a way to store and ID all enemies present in game
public class Enemies implements Saveable {
    private Integer initEnemiesSpawned;
    private Integer enemiesSize = 0;
    private Integer mainPlayerPosX;
    private Integer mainPlayerPosY;
    private LinkedHashMap<Integer, Enemy> enemies;

    public Enemies() {
        enemies = new LinkedHashMap<>();
    }

    @Override
    public void save(PrintWriter printWriter) {
        for (Map.Entry<Integer, Enemy> entry :enemies.entrySet()) {
            entry.getValue().save(printWriter);
        }
    }

    //MODIFIES: this
    //EFFECTS: spawn new enemy in the game
    public void spawn(Integer spawnSize) {
        for (Integer i = 0; i < spawnSize; i++) {
            Enemy enemy = new Enemy(mainPlayerPosX, mainPlayerPosY);
            enemies.put(i, enemy);
        }
        initEnemiesSpawned = spawnSize;
        enemiesSize = spawnSize;
    }


    //EFFECT: updates the ememies location
    public void updateEnemies() {
        for (Map.Entry<Integer, Enemy> entry :enemies.entrySet()) {
            entry.getValue().update(mainPlayerPosX, mainPlayerPosY);
            if (entry.getValue().isDead()) {
                enemiesSize--;
            }
        }
    }

    //EFFECTS: get number of dead enemies
    public int getNumDead() {
        int count = 0;
        for (Map.Entry<Integer, Enemy> entry :enemies.entrySet()) {
            if (entry.getValue().isDead()) {
                count++;
            }
        }
        return count;
    }

    //EFFECTS: returns the number of enemies in initially spawned
    public int initSize() {
        return initEnemiesSpawned;
    }

    //EFFECTS: returns the size of the enemy hashmap
    public int size() {
        return enemies.size();
    }

    //EFFECTS: kills all enemies currently spawned
    public void killAll() {
        for (Map.Entry<Integer, Enemy> entry :enemies.entrySet()) {
            if (!!!entry.getValue().isDead()) {
                entry.getValue().setHealth(0);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: add enemy externally.
    public void forceAddEnemy(Enemy enemy) {
        enemies.put(enemiesSize, enemy);
        enemiesSize++;
    }

    public void setMainPlayerPosX(Integer mainPlayerPosX) {
        this.mainPlayerPosX = mainPlayerPosX;
    }

    public void setMainPlayerPosY(Integer mainPlayerPosY) {
        this.mainPlayerPosY = mainPlayerPosY;
    }

    public LinkedHashMap<Integer, Enemy> getEnemies() {
        return enemies;
    }
}
