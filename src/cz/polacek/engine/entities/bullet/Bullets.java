package cz.polacek.engine.entities.bullet;

import cz.polacek.config.Config;
import cz.polacek.engine.entities.player.Player;
import cz.polacek.engine.handler.KeyHandler;
import cz.polacek.engine.thread.Interval;

public class Bullets {

    KeyHandler keyHandler;
    public Bullet[] bullets;
    int bulletIndex;

    public Bullets(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
        this.bullets = new Bullet[Config.BULLET_MAX_COUNT];
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet();
        }
        this.bulletIndex = 0;
    }

    void fire(Player player, Interval interval) {
        if (interval.canDo()) {
            bulletIndex++;
            if (bulletIndex >= bullets.length) {
                bulletIndex = 0;
            }
            bullets[bulletIndex].setX(player.x);
            bullets[bulletIndex].setY(player.y);
            bullets[bulletIndex].setFace(player.getPlayerFace());
            interval.did();
        }
    }

    public void update(Player player, Interval interval) {
        if (player.isAlive()) {
            if (keyHandler.spacePressed || keyHandler.enterPressed) {
                fire(player, interval);
            }
        }
        for (Bullet bullet : bullets) {
            bullet.update();
        }
    }
}
