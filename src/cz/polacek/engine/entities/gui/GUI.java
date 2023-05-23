package cz.polacek.engine.entities.gui;

import cz.polacek.config.Config;
import cz.polacek.engine.entities.Entity;
import cz.polacek.engine.entities.player.Player;

public class GUI extends Entity {

    GUIState state;
    Player player;
    public GUI(int xPos, int yPos, int index, GUIType type, Player player) {
        super(xPos, yPos);
        setX((int) (getX() + (Config.TILE_COMPUTED * index)));
        this.player = player;
        if (type == GUIType.SHIELD) {
            if (player.getPLAYER_SHIELD() > index) {
                state = GUIState.FULL;
            } else {
                state = GUIState.EMPTY;
            }
        } else if (type == GUIType.HEALTH) {
            if (player.getPLAYER_HEALTH() > index) {
                state = GUIState.FULL;
            } else {
                state = GUIState.EMPTY;
            }
        } else { state = GUIState.EMPTY; }
        setXGrid(state.getNumber());
        setYGrid(type.getNumber());
    }
}
