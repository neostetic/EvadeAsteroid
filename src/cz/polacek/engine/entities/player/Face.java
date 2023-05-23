package cz.polacek.engine.entities.player;

public enum Face {
    UP(0),
    DOWN(2),
    LEFT(3),
    RIGHT(1),
    UP_LEFT(5),
    DOWN_LEFT(6),
    DOWN_RIGHT(7),
    UP_RIGHT(4);

    private final int xGrid;

    Face(int xGrid) {
        this.xGrid = xGrid;
    }

    public int getX() {
        return xGrid;
    }
}
