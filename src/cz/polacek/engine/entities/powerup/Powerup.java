package cz.polacek.engine.entities.powerup;

import cz.polacek.config.Config;
import cz.polacek.engine.Panel;
import cz.polacek.engine.entities.Entity;
import cz.polacek.engine.entities.player.Player;
import cz.polacek.engine.thread.Interval;
import cz.polacek.engine.utils.Utils;

import java.util.Random;

public class Powerup extends Entity {

    PowerupType type;

    public Powerup() {
        super(0,0);
        randomPosition();
        setXGrid(5);
        setYGrid(4);
        this.type = randomPowerType();
    }

    public Powerup(PowerupType powerupType) {
        super(0,0);
        randomPosition();
        setXGrid(5);
        setYGrid(4);
        this.type = powerupType;
    }

    public void update(Player player, Panel panel) {
        if (player.getRect().intersects(this.getRect())) {
            powerUp(player, panel);
            type = randomPowerType();
            randomPosition();
        }
    }

    void powerUp(Player player, Panel panel) {
        switch (type) {
            case ADD_LIFE -> player.addPLAYER_HEALTH(1);
            case ADD_SHIELD -> player.addPLAYER_SHIELD(1);
            case ADD_MONEY -> panel.setSCORE(panel.getSCORE() + Config.POWERUP_POINTS);
            case RANDOM -> {
                type = randomPowerType();
                powerUp(player, panel);
            }
        }
    }

    PowerupType randomPowerType() {
        PowerupType[] types = PowerupType.values();
        Random random = new Random();
        PowerupType randomType = types[random.nextInt(types.length)];
        setXGrid(randomType.xGrid);
        return randomType;
    }

    void randomPosition() {
        setX(Utils.randomInteger(2, Config.TILES_HORIZONTAL - 3) * Config.TILE_COMPUTED);
        setY(Utils.randomInteger(2, Config.TILES_VERTICAL - 3) * Config.TILE_COMPUTED);
    }
}
