package cz.polacek.engine.entities;

import cz.polacek.config.Config;

import java.awt.*;

public class Entity {
    public int width, height;
    public double x, y;
    int xGrid, yGrid;

    public Entity(int xPos, int yPos) {
        this.x = xPos * Config.TILE_COMPUTED;
        this.y = yPos * Config.TILE_COMPUTED;
        this.width = Config.TILE_COMPUTED;
        this.height = Config.TILE_COMPUTED;
        this.xGrid = 0;
        this.yGrid = 5;
    }

    public Entity(int xPos, int yPos, int xGrid, int yGrid) {
        this.x = xPos * Config.TILE_COMPUTED;
        this.y = yPos * Config.TILE_COMPUTED;
        this.width = Config.TILE_COMPUTED;
        this.height = Config.TILE_COMPUTED;
        this.xGrid = xGrid;
        this.yGrid = yGrid;
    }

    public Entity(int xPos, int yPos, int xGrid, int yGrid, boolean isTiled) {
        if (isTiled) {
            this.x = xPos * Config.TILE_COMPUTED;
            this.y = yPos * Config.TILE_COMPUTED;
        } else {
            this.x = xPos;
            this.y = yPos;
        }
        this.width = Config.TILE_COMPUTED;
        this.height = Config.TILE_COMPUTED;
        this.xGrid = xGrid;
        this.yGrid = yGrid;
    }

    public Rectangle getRect() {
        return new Rectangle((int) x, (int) y, Config.TILE_COMPUTED, Config.TILE_COMPUTED);
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getXGrid() {
        return xGrid;
    }

    public void setXGrid(int xGrid) {
        this.xGrid = xGrid;
    }

    public int getYGrid() {
        return yGrid;
    }

    public void setYGrid(int yGrid) {
        this.yGrid = yGrid;
    }
}
