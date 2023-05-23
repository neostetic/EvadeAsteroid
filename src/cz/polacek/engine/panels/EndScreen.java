package cz.polacek.engine.panels;

import cz.polacek.config.Config;
import cz.polacek.engine.handler.KeyHandler;
import cz.polacek.engine.utils.Utils;

import java.util.Objects;

public class EndScreen {

    private final KeyHandler keyHandler;
    private final int nameLength;
    private final String[] nameArray;
    public String menu;
    public String name;
    private int charNamePosition = 0;

    private final String[] allowedCharacters = Config.ALLOWED_CHARACTERS;


    public EndScreen(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
        this.nameLength = Config.PLAYERNAME_MAX_LENGTH;
        this.nameArray = new String[Config.PLAYERNAME_MAX_LENGTH];
        resetName();
        this.menu = " ".repeat(nameLength);
    }

    public void resetName() {
        for (int i = 0; i < nameLength; i++) {
            nameArray[i] = allowedCharacters[1];
        }
        charNamePosition = 0;
    }

    public void update() {
        if (Objects.equals(keyHandler.typedKey, "a")) {
            charNamePosition = charNamePosition - 1;
        }
        if (Objects.equals(keyHandler.typedKey, "d")) {
            charNamePosition = charNamePosition + 1;
        }
        if (charNamePosition < 0) {
            charNamePosition = nameLength - 1;
        } else if (charNamePosition > nameLength - 1) {
            charNamePosition = 0;
        }
        if (Objects.equals(keyHandler.typedKey, "w")) {
            int index = Utils.findInArray(allowedCharacters, nameArray[charNamePosition]);
            if (index - 1 < 0) {
                index = allowedCharacters.length;
            }
            nameArray[charNamePosition] = allowedCharacters[index - 1];
        }
        if (Objects.equals(keyHandler.typedKey, "s")) {
            int index = Utils.findInArray(allowedCharacters, nameArray[charNamePosition]);
            if (index + 1 > allowedCharacters.length - 1) {
                index = -1;
            }
            nameArray[charNamePosition] = allowedCharacters[index + 1];
        }
        keyHandler.typedKey = "";
        StringBuilder sbTop = new StringBuilder();
        StringBuilder sbMiddle = new StringBuilder();
        StringBuilder sbBottom = new StringBuilder();
        for (int i = 0; i < nameLength; i++) {
            if (i < charNamePosition || i > charNamePosition) {
                sbTop.append(" ");
                sbBottom.append(" ");
            } else {
                sbTop.append("%up");
                sbBottom.append("%down");
            }
        }
        for (String s : nameArray) {
            sbMiddle.append(s);
        }
        menu = sbTop + "%n" + sbMiddle + "%n" + sbBottom;
        StringBuilder sbName = new StringBuilder();
        for (String s : nameArray) {
            sbName.append(s);
        }
        name = sbName.toString();
    }
}
