package cz.polacek.engine.entities.gui;

public enum GUIType {
    HEALTH(6),
    SHIELD(7);

    int number;

    GUIType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
