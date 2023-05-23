package cz.polacek.engine.entities;

import cz.polacek.config.Config;
import cz.polacek.engine.entities.player.Player;
import cz.polacek.engine.utils.Utils;

public class Background {

    Player player;
    public Entity[][] first;
    public Entity[][] second;
    public Entity[][] planets;
    int yGrid = 9;
    public Background(Player player) {
        this.player = player;
        this.first = generateBackground(0, 2, yGrid, 4, 4);
        this.second = generateBackground(3, 5, yGrid, 4, 4);
        this.planets = generateBackground(6, 9, yGrid, 2, 2);
    }

    public void update(Player player) {
        borderTeleport(first);
        borderTeleport(second);
        borderTeleport(planets);
        for (Entity[] entities : first) {
            for (int y = 0; y < first[0].length; y++) {
                entities[y].setX((entities[y].getX() - player.getxVel() / 3));
                entities[y].setY((entities[y].getY() - player.getyVel() / 3));
            }
        }
        for (Entity[] entities : second) {
            for (int y = 0; y < second[0].length; y++) {
                entities[y].setX((entities[y].getX() - player.getxVel() / 4));
                entities[y].setY((entities[y].getY() - player.getyVel() / 4));
            }
        }
        for (Entity[] entities : planets) {
            for (int y = 0; y < planets[0].length; y++) {
                entities[y].setX((entities[y].getX() - player.getxVel() / 6));
                entities[y].setY((entities[y].getY() - player.getyVel() / 6));
            }
        }
    }

    private Entity[][] generateBackground(int xGridStart, int xGridEnd, int yGrid, int xMax, int yMax) {
        Entity[][] entities = new Entity[xMax][yMax];
        for (int x = 0; x < entities.length; x++) {
            for (int y = 0; y < entities[0].length; y++) {
                entities[x][y] = new Entity(
                    (int) player.x + Config.TILE_COMPUTED * (Utils.randomInteger(0, Config.TILES_HORIZONTAL)),
                    (int) player.y + Config.TILE_COMPUTED * (Utils.randomInteger(0, Config.TILES_VERTICAL)),
                    Utils.randomInteger(xGridStart,xGridEnd),
                    yGrid,
                    false
                );
            }
        }
        return entities;
    }

    private void borderTeleport(Entity[][] entities) {
        for (int x = 0; x < entities.length; x++) {
            for (int y = 0; y < entities[0].length; y++) {
                Entity entity = entities[x][y];
                if (entity.x < -Config.TILE_COMPUTED) {
                    int calc = (int) (entity.x + Config.TILE_COMPUTED);
                    entity.x = Config.WINDOW_WIDTH + Config.TILE_COMPUTED - calc;
                } else if (entity.x > Config.WINDOW_WIDTH + Config.TILE_COMPUTED) {
                    int calc = (int) (entity.x - (Config.WINDOW_WIDTH + Config.TILE_COMPUTED));
                    entity.x = -Config.TILE_COMPUTED + calc;
                }
                if (entity.y < -Config.TILE_COMPUTED) {
                    int calc = (int) (entity.y + Config.TILE_COMPUTED);
                    entity.y = Config.WINDOW_HEIGHT + Config.TILE_COMPUTED - calc;
                } else if (entity.y > Config.WINDOW_HEIGHT + Config.TILE_COMPUTED) {
                    int calc = (int) (entity.y - (Config.WINDOW_HEIGHT + Config.TILE_COMPUTED));
                    entity.y = -Config.TILE_COMPUTED + calc;
                }
            }
        }
    }
}
