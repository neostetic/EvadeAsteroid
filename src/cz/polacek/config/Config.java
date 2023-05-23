package cz.polacek.config;

import cz.polacek.engine.panels.PanelController;
import cz.polacek.engine.text.TextStringFormatter;
import cz.polacek.engine.utils.ColorPalette;

public  class Config {

    static public String TITLE = "EvadeAsteroid 2.0";
    static public int PREFERRED_FPS = 60;
    static public PanelController PANEL_CONTROLLER = PanelController.PS_COLOR;
    static public ColorPalette COLOR_PALETTE = ColorPalette.PICO8;
    static public boolean WINDOW_RESIZABLE = false;
    static public boolean WINDOW_VISIBLE = true;
    static public int SCALE = 3;
    static public int TILE_SIZE = 16;
    static public int TILES_HORIZONTAL = 20;
    static public int TILES_VERTICAL = 15;
    static public int TILE_COMPUTED = TILE_SIZE * SCALE;
    static public int WINDOW_WIDTH = (TILE_COMPUTED * TILES_HORIZONTAL);            // (tileComputed * 20)
    static public int WINDOW_HEIGHT = (TILE_COMPUTED * TILES_VERTICAL);             // (tileComputed * 15)
    static public double GRAVITY = 1.01;                                            // default 1.01
    static public double PLAYER_SLOWDOWN = 16;
    static public double PLAYER_SPEED = (TILE_SIZE / 10) * SCALE;
    static public int PLAYER_MAX_HEALTH = 3;
    static public int PLAYER_MAX_SHIELD = 5;
    static public int PLAYERNAME_MAX_LENGTH = 6;
    static public int ENEMY_SPAWN_INTERVAL = 30;
    static public int ENEMY_MAX_COUNT = 15;
    static public double ENEMY_MIN_VELOCITY = .1;
    static public double ENEMY_MAX_VELOCITY = 1.25;
    static public int BULLET_INTERVAL = 500;
    static public int BULLET_MAX_COUNT = 20;
    static public double BULLET_SPEED = (TILE_SIZE / 5) * SCALE;
    static public int POWERUP_POINTS = 500;
    static public String RECORD_FILE = "records.csv";
    static public final String[] ALLOWED_CHARACTERS = {
            " ",
            "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
            "0","1","2","3","4","5","6","7","8","9",
            "!","\"","#","%","&","'","(",")","*","+",",","-",".","/",":",";","<","=",">","?","@","[","\\","]","^","_","`",
            ":)","(:",":D",":/",":o",":,(",":(",":z:".trim()
    };
    static public TextStringFormatter[] STRING_FORMATTERS = new TextStringFormatter[]{
            new TextStringFormatter("%key_enter_old", new char[] {129}),
            new TextStringFormatter("%key_enter", new char[] {132}),
            new TextStringFormatter("%key_spacebar", new char[] {133, 134, 135, 136}),
            new TextStringFormatter("%key_backspace", new char[] {130, 131}),
            new TextStringFormatter("%key_w", new char[] {137}),
            new TextStringFormatter("%key_s", new char[] {138}),
            new TextStringFormatter("%key_a", new char[] {139}),
            new TextStringFormatter("%key_d", new char[] {140}),
            new TextStringFormatter("%key", new char[] {141}),
            new TextStringFormatter("%xbox_a_col", new char[] {152}),
            new TextStringFormatter("%xbox_b_col", new char[] {153}),
            new TextStringFormatter("%xbox_x_col", new char[] {154}),
            new TextStringFormatter("%xbox_y_col", new char[] {155}),
            new TextStringFormatter("%xbox_a", new char[] {142}),
            new TextStringFormatter("%xbox_b", new char[] {143}),
            new TextStringFormatter("%xbox_x", new char[] {144}),
            new TextStringFormatter("%xbox_y", new char[] {145}),
            new TextStringFormatter("%dpad_up", new char[] {146}),
            new TextStringFormatter("%dpad_down", new char[] {147}),
            new TextStringFormatter("%dpad_left", new char[] {148}),
            new TextStringFormatter("%dpad_right", new char[] {149}),
            new TextStringFormatter("%dpad_on", new char[] {150}),
            new TextStringFormatter("%dpad_off", new char[] {151}),
            new TextStringFormatter("%dpad", new char[] {151}),
            new TextStringFormatter("%ps_x_col", new char[] {172}),
            new TextStringFormatter("%ps_o_col", new char[] {173}),
            new TextStringFormatter("%ps_sq_col", new char[] {174}),
            new TextStringFormatter("%ps_tr_col", new char[] {175}),
            new TextStringFormatter("%ps_x", new char[] {162}),
            new TextStringFormatter("%ps_o", new char[] {163}),
            new TextStringFormatter("%ps_sq", new char[] {164}),
            new TextStringFormatter("%ps_tr", new char[] {165}),
            new TextStringFormatter("%l_stick_up", new char[] {166}),
            new TextStringFormatter("%l_stick_down", new char[] {167}),
            new TextStringFormatter("%l_stick_left", new char[] {168}),
            new TextStringFormatter("%l_stick_right", new char[] {169}),
            new TextStringFormatter("%l_stick_on", new char[] {171}),
            new TextStringFormatter("%l_stick_off", new char[] {170}),
            new TextStringFormatter("%l_stick", new char[] {170}),
            new TextStringFormatter("%0%", new char[] {182}),
            new TextStringFormatter("%1%", new char[] {183}),
            new TextStringFormatter("%2%", new char[] {184}),
            new TextStringFormatter("%3%", new char[] {185}),
            new TextStringFormatter("%4%", new char[] {186}),
            new TextStringFormatter("%5%", new char[] {187}),
            new TextStringFormatter("%6%", new char[] {188}),
            new TextStringFormatter("%7%", new char[] {189}),
            new TextStringFormatter("%8%", new char[] {190}),
            new TextStringFormatter("%9%", new char[] {191}),
            new TextStringFormatter("%10%", new char[] {192}),
            new TextStringFormatter("%11%", new char[] {193}),
            new TextStringFormatter("%12%", new char[] {194}),
            new TextStringFormatter("%13%", new char[] {195}),
            new TextStringFormatter("%14%", new char[] {196}),
            new TextStringFormatter("%15%", new char[] {197}),
            new TextStringFormatter("%16%", new char[] {198}),
            new TextStringFormatter("%17%", new char[] {199}),
            new TextStringFormatter("%18%", new char[] {200}),
            new TextStringFormatter("%19%", new char[] {201}),
            new TextStringFormatter(":z:", new char[] {209}),
            new TextStringFormatter(":stop:", new char[] {210}),
            new TextStringFormatter(":18:", new char[] {211}),
            new TextStringFormatter(":smoke:", new char[] {212}),
            new TextStringFormatter(":idea:", new char[] {213}),
            new TextStringFormatter(":bulb:", new char[] {213}),
            new TextStringFormatter(":king:", new char[] {214}),
            new TextStringFormatter(":gfx:", new char[] {215}),
            new TextStringFormatter(":win:", new char[] {216}),
            new TextStringFormatter(":lose:", new char[] {217}),
            new TextStringFormatter(":yes:", new char[] {218}),
            new TextStringFormatter(":no:", new char[] {219}),
            new TextStringFormatter(":del:", new char[] {220}),
            new TextStringFormatter(":exit:", new char[] {221}),
            new TextStringFormatter(":)", new char[] {202}),
            new TextStringFormatter("(:", new char[] {203}),
            new TextStringFormatter(":D", new char[] {204}),
            new TextStringFormatter(":/", new char[] {205}),
            new TextStringFormatter(":o", new char[] {206}),
            new TextStringFormatter(":O", new char[] {206}),
            new TextStringFormatter(":,(", new char[] {207}),
            new TextStringFormatter(":(", new char[] {208}),
            new TextStringFormatter("%up", new char[] {127}),
            new TextStringFormatter("%down", new char[] {128}),
            new TextStringFormatter("%dev", new char[] {231}),
    };

}
