package cz.polacek.engine.entities.bullet;

import cz.polacek.config.Config;
import cz.polacek.engine.entities.Entity;
import cz.polacek.engine.entities.player.Face;

public class Bullet extends Entity {

    double xVel, yVel;
    Face face;
    public Bullet() {
        super(-Config.TILE_COMPUTED * 4, -Config.TILE_COMPUTED * 4);
        setXGrid(0);
        setYGrid(3);
        this.face = Face.NULL;
    }

    public void update() {
        setX(getX() + xVel);
        setY(getY() + yVel);
        setXGrid(getXGrid() + 1);
        if (getXGrid() > 2) {
            setXGrid(0);
        }
        if (getX() > (Config.WINDOW_WIDTH + Config.TILE_COMPUTED)) {
            setxVel(0);
            setX(2 * -Config.TILE_COMPUTED);
        } else if (getX() < -Config.TILE_COMPUTED) {
            setxVel(0);
            setX(2 * -Config.TILE_COMPUTED);
        }
        if (getY() > (Config.WINDOW_HEIGHT + Config.TILE_COMPUTED)) {
            setyVel(0);
            setY(2 * -Config.TILE_COMPUTED);
        } else if (getY() < -Config.TILE_COMPUTED) {
            setyVel(0);
            setY(2 * -Config.TILE_COMPUTED);
        }
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        double speed = Config.BULLET_SPEED;
        this.face = face;
        if (face == Face.UP) {
            this.xVel = 0;
            this.yVel = -speed;
        } else if (face == Face.DOWN) {
            this.xVel = 0;
            this.yVel = speed;
        } else if (face == Face.LEFT) {
            this.xVel = -speed;
            this.yVel = 0;
        } else if (face == Face.RIGHT) {
            this.xVel = speed;
            this.yVel = 0;
        } else if (face == Face.UP_LEFT) {
            this.xVel = -speed;
            this.yVel = -speed;
        } else if (face == Face.UP_RIGHT) {
            this.xVel = speed;
            this.yVel = -speed;
        } else if (face == Face.DOWN_LEFT) {
            this.xVel = -speed;
            this.yVel = speed;
        } else if (face == Face.DOWN_RIGHT) {
            this.xVel = speed;
            this.yVel = speed;
        } else {
            this.xVel = 0;
            this.yVel = 0;
        }
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public void isHit() {
        setX(-Config.TILE_COMPUTED*4);
        setY(-Config.TILE_COMPUTED*4);
    }
}
