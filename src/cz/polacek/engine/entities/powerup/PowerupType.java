package cz.polacek.engine.entities.powerup;

public enum PowerupType {

    RANDOM(4),
    ADD_LIFE(0),
    ADD_SHIELD(1),
    ADD_MONEY(3),
    NULL(5);

    int xGrid;

    PowerupType(int xGrid) {
        this.xGrid = xGrid;
    }
}
