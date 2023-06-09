package cz.polacek.engine.utils;

public enum ColorPalette {
    CLASSIC(""),
    ONE_BIT("1BIT"),
    BITTERSWEET("BITTERSWEET"),
    BLUEM0LD("BLUEM0LD"),
    CASIO("CASIO"),
    MISSING_TEXTURE("MISSING-TEXTURE"),
    NOSTALGIA("NOSTALGIA"),
    KIROKAZE("KIROKAZE"),
    PAPERBACK_TWO("PAPERBACK-2"),
    PICO8("PICO8");

    String file;

    ColorPalette(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
