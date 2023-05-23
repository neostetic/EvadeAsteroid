package cz.polacek.engine.text;

public class TextLined {
    Text[] texts;
    int linesCount;

    public TextLined(String texts) {
        String[] strings = texts.split("%n");
        Text[] texts1 = new Text[strings.length];
        for (int i = 0; i < strings.length; i++) {
            texts1[i] = new Text(strings[i], 0, i);
        }
        this.texts = texts1;
        this.linesCount = strings.length;
    }

    public TextLined(String texts, int tile_x, int tile_y) {
        String[] strings = texts.split("%n");
        Text[] texts1 = new Text[strings.length];
        for (int i = 0; i < strings.length; i++) {
            texts1[i] = new Text(strings[i], tile_x, tile_y + i);
        }
        this.texts = texts1;
        this.linesCount = strings.length;
    }

    public TextLined(String texts, Text.Alignment alignment, int x, int y) {
        String[] strings = texts.split("%n");
        Text[] texts1 = new Text[strings.length];
        for (int i = 0; i < strings.length; i++) {
            texts1[i] = new Text(strings[i], alignment, x, y + i);
        }
        this.texts = texts1;
        this.linesCount = strings.length;
    }

    public Text[] getTexts() {
        return texts;
    }

    public void setTexts(Text[] texts) {
        this.texts = texts;
    }

    public int getLinesCount() {
        return linesCount;
    }

    public void setLinesCount(int linesCount) {
        this.linesCount = linesCount;
    }
}
