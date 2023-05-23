package cz.polacek.engine.text;

import cz.polacek.config.Config;
import cz.polacek.engine.utils.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {

    String text;
    int[][] grid;
    int x, y;
    int width, height;
    public enum Alignment {
        TOP_LEFT,
        TOP_RIGHT,
        TOP_CENTER,
        MIDDLE_LEFT,
        MIDDLE_RIGHT,
        MIDDLE_CENTER,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        BOTTOM_CENTER
    };
    public Text(String text) {
        text = textFormatter(text);
        this.text = text;
        this.grid = calculateGrid(text);
        this.x = 0;
        this.y = 0;
        this.width = Config.TILE_COMPUTED;
        this.height = Config.TILE_COMPUTED;
    }

    public Text(String text, int tile_x, int tile_y) {
        text = textFormatter(text);
        this.text = text;
        this.grid = calculateGrid(text);
        this.x = tile_x * Config.TILE_COMPUTED;
        this.y = tile_y * Config.TILE_COMPUTED;
        this.width = Config.TILE_COMPUTED;
        this.height = Config.TILE_COMPUTED;
    }

    public Text(String text, int x, int y, boolean isTiled) {
        text = textFormatter(text);
        this.text = text;
        this.grid = calculateGrid(text);
        if (isTiled) {
            this.x = x * Config.TILE_COMPUTED;
            this.y = y * Config.TILE_COMPUTED;
        } else {
            this.x = x;
            this.y = y;
        }
        this.width = Config.TILE_COMPUTED;
        this.height = Config.TILE_COMPUTED;
    }

    public Text(String text_right, String text_left, int maxLength, int x, int y) {
        String tr = textFormatter(text_right);
        String tl = textFormatter(text_left);
        int dotsLength = maxLength - (tl.length() + tr.length());
        this.text = tr + ".".repeat(Math.max(0, dotsLength)) + tl;
        this.grid = calculateGrid(text);
        this.x = x * Config.TILE_COMPUTED;
        this.y = y * Config.TILE_COMPUTED;
        this.width = Config.TILE_COMPUTED;
        this.height = Config.TILE_COMPUTED;
    }

    public Text(String text_right, String text_left, String replacement, int maxLength, int x, int y) {
        text_right = textFormatter(text);
        text_left = textFormatter(text);
        int dotsLength = maxLength - (text_left.length() + text_right.length());
        this.text = text_right + replacement.repeat(Math.max(0, dotsLength)) + text_left;
        this.grid = calculateGrid(text);
        this.x = x * Config.TILE_COMPUTED;
        this.y = y * Config.TILE_COMPUTED;
        this.width = Config.TILE_COMPUTED;
        this.height = Config.TILE_COMPUTED;
    }

    public Text(String text, Alignment alignment, int x, int y) {
        text = textFormatter(text);
        this.text = text;
        this.grid = calculateGrid(text);
        int textLength = text.length() * Config.TILE_COMPUTED;
        int xComputed = (x * Config.TILE_COMPUTED);
        int yComputed = (y * Config.TILE_COMPUTED);
        int paddingHorizontal = ((Config.WINDOW_WIDTH - xComputed - (text.length()) * Config.TILE_COMPUTED) / 2);
        int paddingVertical = (((Config.WINDOW_HEIGHT / 2) - yComputed - (Config.TILE_COMPUTED/2)));
        if (alignment == Alignment.TOP_LEFT) {
            this.x = xComputed;
            this.y = yComputed;
        } else if (alignment == Alignment.TOP_RIGHT) {
            this.x = Config.WINDOW_WIDTH - xComputed - textLength;
            this.y = yComputed;
        } else if (alignment == Alignment.TOP_CENTER) {
            this.x = paddingHorizontal + xComputed;
            this.y = yComputed;
        } else if (alignment == Alignment.BOTTOM_LEFT) {
            this.x = xComputed;
            this.y = Config.WINDOW_HEIGHT - Config.TILE_COMPUTED;
        } else if (alignment == Alignment.BOTTOM_RIGHT) {
            this.x = Config.WINDOW_WIDTH - xComputed - textLength;
            this.y = Config.WINDOW_HEIGHT - Config.TILE_COMPUTED;
        } else if (alignment == Alignment.BOTTOM_CENTER) {
            this.x = paddingHorizontal + xComputed;
            this.y = Config.WINDOW_HEIGHT - Config.TILE_COMPUTED;
        } else if (alignment == Alignment.MIDDLE_LEFT) {
            this.x = xComputed;
            this.y = paddingVertical - (Config.TILE_COMPUTED / 2);
        } else if (alignment == Alignment.MIDDLE_RIGHT) {
            this.x = Config.WINDOW_WIDTH - xComputed - textLength;
            this.y = paddingVertical - (Config.TILE_COMPUTED / 2);
        } else if (alignment == Alignment.MIDDLE_CENTER) {
            this.x = paddingHorizontal + xComputed;
            this.y = paddingVertical - (Config.TILE_COMPUTED / 2);
        }
        this.width = Config.TILE_COMPUTED;
        this.height = Config.TILE_COMPUTED;
    }

    private int[][] calculateGrid(String string) {
        int[] gridSingle = Utils.textSprites(string);
        int[][] gridLayered = new int[gridSingle.length][2];
        for (int i = 0; i < gridSingle.length; i++) {
            gridLayered[i][0] = gridSingle[i]%10;
            gridLayered[i][1] = (gridSingle[i] - gridSingle[i]%10) / 10;
        }
        return gridLayered;
    }

    private String textFormatter(String string) {
        String result = string;
        for (int i = 0; i < Config.STRING_FORMATTERS.length; i++) {
            TextStringFormatter sf = Config.STRING_FORMATTERS[i];
            if (result.contains(sf.getText())) {
                result = result.replace(sf.getText(), sf.getReplacement());
            }
        }
        Pattern pattern = Pattern.compile("\\\\(\\d+)");
        String input = result;
        Matcher matcher = pattern.matcher(input);
        StringBuffer output = new StringBuffer();

        while (matcher.find()) {
            String match = matcher.group();
            String number = matcher.group(1);
            char replacement = (char) Integer.parseInt(number);
            matcher.appendReplacement(output, String.valueOf(replacement));
        }
        matcher.appendTail(output);
        result = String.valueOf(output);
        return result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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
}
