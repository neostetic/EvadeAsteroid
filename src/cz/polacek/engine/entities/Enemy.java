package cz.polacek.engine.entities;

import cz.polacek.config.Config;
import cz.polacek.engine.utils.Utils;

public class Enemy extends Entity {

    double xVel, yVel;
    private boolean isLiving = true;
    public Enemy(int xPos, int yPos) {
        super(xPos, yPos);
        setXGrid(Utils.randomInteger(0,5));
        setYGrid(5);
        this.xVel = Utils.randomDouble(0, 5);
        this.yVel = Utils.randomDouble(0, 5);
    }

    public Enemy() {
        super(0, 0);
        int[] spawns = Utils.randomSpawnLocation();
        setX(spawns[0]);
        setY(spawns[1]);
        setXGrid(Utils.randomInteger(0,5));
        setYGrid(5);
        if (Utils.randomBoolean()) {
            this.xVel = Utils.randomDouble(Config.ENEMY_MIN_VELOCITY, Config.ENEMY_MAX_VELOCITY) * Config.SCALE;
        } else {
            this.xVel = -Utils.randomDouble(Config.ENEMY_MIN_VELOCITY, Config.ENEMY_MAX_VELOCITY) * Config.SCALE;
        }
        if (Utils.randomBoolean()) {
            this.yVel = Utils.randomDouble(Config.ENEMY_MIN_VELOCITY, Config.ENEMY_MAX_VELOCITY) * Config.SCALE;
        } else {
            this.yVel = -Utils.randomDouble(Config.ENEMY_MIN_VELOCITY, Config.ENEMY_MAX_VELOCITY) * Config.SCALE;
        }
    }

    public void change() {
        int[] spawns = Utils.randomSpawnLocation();
        setX(spawns[0]);
        setY(spawns[1]);
        setXGrid(Utils.randomInteger(0,5));
        setYGrid(5);
        if (Utils.randomBoolean()) {
            this.xVel = Utils.randomDouble(Config.ENEMY_MIN_VELOCITY, Config.ENEMY_MAX_VELOCITY) * Config.SCALE;
        } else {
            this.xVel = -Utils.randomDouble(Config.ENEMY_MIN_VELOCITY, Config.ENEMY_MAX_VELOCITY) * Config.SCALE;
        }
        if (Utils.randomBoolean()) {
            this.yVel = Utils.randomDouble(Config.ENEMY_MIN_VELOCITY, Config.ENEMY_MAX_VELOCITY) * Config.SCALE;
        } else {
            this.yVel = -Utils.randomDouble(Config.ENEMY_MIN_VELOCITY, Config.ENEMY_MAX_VELOCITY) * Config.SCALE;
        }
    }

    public void update() {
        x = x + xVel;
        y = y + yVel;
        if (x > Config.WINDOW_WIDTH) {
            x = -Config.TILE_COMPUTED;
            change();
        }
        if (x < -Config.TILE_COMPUTED) {
            x = Config.WINDOW_WIDTH;
            change();
        }
        if (y > Config.WINDOW_HEIGHT) {
            y = -Config.TILE_COMPUTED;
            change();
        }
        if (y < -Config.TILE_COMPUTED) {
            y = Config.WINDOW_HEIGHT;
            change();
        }
    }

    public void isHit() {
        x = -1000;
        y = -1000;
        isLiving = false;
    }

    public boolean isAlive() {
        return isLiving;
    }
}
