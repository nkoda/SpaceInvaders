package model;


// this class is a structure of the saved data for the  game
public class PlayerData {
    private Integer health;
    private Integer attack;
    private Integer positionX;
    private Integer positionY;


    public PlayerData(int health, int attack, int positionX, int positionY) {
        this.health = health;
        this.attack = attack;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }
}
