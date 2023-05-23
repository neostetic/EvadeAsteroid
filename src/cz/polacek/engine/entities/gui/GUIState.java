package cz.polacek.engine.entities.gui;

public enum GUIState {
    FULL(0),
    EMPTY(1);

    int number;

    GUIState(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
