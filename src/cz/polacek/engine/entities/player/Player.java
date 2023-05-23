package cz.polacek.engine.entities.player;

import cz.polacek.config.Config;
import cz.polacek.engine.entities.Entity;
import cz.polacek.engine.handler.KeyHandler;

public class Player extends Entity {

    private KeyHandler keyHandler;
    private Face playerFace;

    private int PLAYER_HEALTH;
    private int PLAYER_SHIELD;
    private boolean isLiving = true;

    private double xVel, yVel;
    private boolean invincible;

    public Player(KeyHandler keyHandler, boolean invincible) {
        super(Config.WINDOW_WIDTH / 2 - Config.TILE_COMPUTED / 2, Config.WINDOW_HEIGHT / 2 - Config.TILE_COMPUTED / 2);
        setX(Config.WINDOW_WIDTH / 2 - Config.TILE_COMPUTED / 2);
        setY(Config.WINDOW_HEIGHT / 2 - Config.TILE_COMPUTED / 2);
        this.PLAYER_HEALTH = Config.PLAYER_MAX_HEALTH;
        this.PLAYER_SHIELD = Config.PLAYER_MAX_SHIELD;
        this.keyHandler = keyHandler;
        this.playerFace = Face.UP;
        this.invincible = invincible;
    }

    public Player(KeyHandler keyHandler) {
        super(Config.WINDOW_WIDTH / 2 - Config.TILE_COMPUTED / 2, Config.WINDOW_HEIGHT / 2 - Config.TILE_COMPUTED / 2);
        setX(Config.WINDOW_WIDTH / 2 - Config.TILE_COMPUTED / 2);
        setY(Config.WINDOW_HEIGHT / 2 - Config.TILE_COMPUTED / 2);
        this.PLAYER_HEALTH = Config.PLAYER_MAX_HEALTH;
        this.PLAYER_SHIELD = Config.PLAYER_MAX_SHIELD;
        this.keyHandler = keyHandler;
        this.playerFace = Face.UP;
        this.invincible = false;
    }

    public Player(int xPos, int yPos, KeyHandler keyHandler) {
        super(xPos, yPos);
        setX(xPos);
        setY(yPos);
        this.PLAYER_HEALTH = Config.PLAYER_MAX_HEALTH;
        this.PLAYER_SHIELD = Config.PLAYER_MAX_SHIELD;
        this.keyHandler = keyHandler;
        this.playerFace = Face.UP;
        this.invincible = false;
    }

    private int deathAnimCounter = 0;
    private int deathAnimBreak = 0;

    public void update() {
        int hold;

        if (isAlive()) {
            if (keyHandler.upPressed && keyHandler.rightPressed) {
                playerFace = Face.UP_RIGHT;
            } else if (keyHandler.upPressed && keyHandler.leftPressed) {
                playerFace = Face.UP_LEFT;
            } else if (keyHandler.downPressed && keyHandler.leftPressed) {
                playerFace = Face.DOWN_LEFT;
            } else if (keyHandler.downPressed && keyHandler.rightPressed) {
                playerFace = Face.DOWN_RIGHT;
            } else if (keyHandler.upPressed) {
                playerFace = Face.UP;
            } else if (keyHandler.downPressed) {
                playerFace = Face.DOWN;
            } else if (keyHandler.rightPressed) {
                playerFace = Face.RIGHT;
            } else if (keyHandler.leftPressed) {
                playerFace = Face.LEFT;
            }

            if (keyHandler.upPressed) {
                if (yVel <= -Config.PLAYER_SPEED) {
                    yVel = -Config.PLAYER_SPEED;
                } else {
                    yVel = yVel - Config.PLAYER_SPEED / Config.PLAYER_SLOWDOWN;
                }
            }
            if (keyHandler.downPressed) {
                if (yVel >= Config.PLAYER_SPEED) {
                    yVel = Config.PLAYER_SPEED;
                } else {
                    yVel = yVel + Config.PLAYER_SPEED / Config.PLAYER_SLOWDOWN;
                }
            }
            if (keyHandler.leftPressed) {
                if (xVel <= -Config.PLAYER_SPEED) {
                    xVel = -Config.PLAYER_SPEED;
                } else {
                    xVel = xVel - Config.PLAYER_SPEED / Config.PLAYER_SLOWDOWN;
                }
            }
            if (keyHandler.rightPressed) {
                if (xVel >= Config.PLAYER_SPEED) {
                    xVel = Config.PLAYER_SPEED;
                } else {
                    xVel = xVel + Config.PLAYER_SPEED / Config.PLAYER_SLOWDOWN;
                }
            }
            if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
                hold = 0;
            } else {
                hold = 1;
            }

            if (x > Config.WINDOW_WIDTH - Config.TILE_COMPUTED) {
                x = Config.WINDOW_WIDTH - Config.TILE_COMPUTED;
            } else if (x < 0) {
                x = 0;
            }
            if (y > Config.WINDOW_HEIGHT - Config.TILE_COMPUTED) {
                y = Config.WINDOW_HEIGHT - Config.TILE_COMPUTED;
            } else if (y < 0) {
                y = 0;
            }

            setXGrid(playerFace.getX());

        } else {
            deathAnimBreak++;
            if (deathAnimBreak > 4) {
                deathAnimBreak = 0;
                deathAnimCounter++;
                if (deathAnimCounter > 7) {
                    deathAnimCounter = 0;
                }
            }
            hold = 2;
            setXGrid(deathAnimCounter);
        }

        setYGrid(hold);

        yVel = yVel / Config.GRAVITY;
        xVel = xVel / Config.GRAVITY;
        y = y + yVel;
        x = x + xVel;
    }

    public void isHit() {
        if (!invincible) {
            if (PLAYER_SHIELD > 0) {
                PLAYER_SHIELD--;
            } else {
                PLAYER_HEALTH--;
            }
            if (PLAYER_HEALTH < 1) {
                isLiving = false;
            }
        }
    }

    public boolean isAlive() {
        return isLiving;
    }

    public int getPLAYER_HEALTH() {
        return PLAYER_HEALTH;
    }

    public void setPLAYER_HEALTH(int PLAYER_HEALTH) {
        this.PLAYER_HEALTH = PLAYER_HEALTH;
    }

    public void addPLAYER_HEALTH(int count_life) {
        for (int i = 0; i < count_life; i++) {
            if (getPLAYER_HEALTH() < Config.PLAYER_MAX_HEALTH) {
                this.PLAYER_HEALTH = getPLAYER_HEALTH() + 1;
            }
        }
    }

    public int getPLAYER_SHIELD() {
        return PLAYER_SHIELD;
    }

    public void setPLAYER_SHIELD(int PLAYER_SHIELD) {
        this.PLAYER_SHIELD = PLAYER_SHIELD;
    }

    public void addPLAYER_SHIELD(int count_shield) {
        for (int i = 0; i < count_shield; i++) {
            if (getPLAYER_SHIELD() < Config.PLAYER_MAX_SHIELD) {
                this.PLAYER_SHIELD = getPLAYER_SHIELD() + 1;
            }
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

    public Face getPlayerFace() {
        return playerFace;
    }
}
