package cz.polacek.engine;

import cz.polacek.config.Config;
import cz.polacek.engine.entities.Background;
import cz.polacek.engine.entities.Enemy;
import cz.polacek.engine.entities.Entity;
import cz.polacek.engine.entities.bullet.Bullets;
import cz.polacek.engine.entities.gui.GUI;
import cz.polacek.engine.entities.gui.GUIType;
import cz.polacek.engine.entities.powerup.Powerup;
import cz.polacek.engine.panels.*;
import cz.polacek.engine.text.Text;
import cz.polacek.engine.text.TextLined;
import cz.polacek.engine.entities.player.Player;
import cz.polacek.engine.handler.KeyHandler;
import cz.polacek.engine.thread.Interval;
import cz.polacek.engine.utils.Sprite;
import cz.polacek.view.WindowError;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Panel extends JPanel implements Runnable {

    int SCORE = 0;
    String NAME = "";

    public int getSCORE() {
        return SCORE;
    }

    public void setSCORE(int SCORE) {
        this.SCORE = SCORE;
    }

    LinkedList<Text> highScores = new LinkedList<>();
    ArrayList<ScoreObject> records = new ArrayList<>();

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    private final int FPS = Config.PREFERRED_FPS;

    JFrame frame;


    Player player = new Player(keyHandler, true);

    PanelState panelState = PanelState.MAIN_MENU;
    PanelController panelController = Config.PANEL_CONTROLLER;

    Interval bulletInterval = new Interval(Config.BULLET_INTERVAL);
    public Panel(JFrame frame) {
        this.setSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Better render
        this.setLayout(null);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.frame = frame;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        bulletInterval.start();

        int framesCount = 0;
        long startTime = System.nanoTime();

        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

                framesCount++;
                long elapsedTime = System.nanoTime() - startTime;
                if (elapsedTime >= 1_000_000_000) {
                    double fps = framesCount / (elapsedTime / 1_000_000_000.0);
                    frame.setTitle(Config.TITLE + " - FPS: " + fps);
                    framesCount = 0;
                    startTime = System.nanoTime();
                }
            } catch (InterruptedException e) {
                new WindowError(e.toString());
            }

        }
    }

    private void sortRecords() {
        records.clear();
        highScores.clear();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Config.RECORD_FILE));
            String line;
            if (SCORE > 0) {
                records.add(new ScoreObject(SCORE, NAME));
            }
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(";");
                records.add(new ScoreObject(Integer.parseInt(split[0]), split[1]));
            }
            records.sort(Comparator.comparingInt(ScoreObject::getScore));
            Collections.reverse(records);

            FileWriter fileWriter = new FileWriter(Config.RECORD_FILE);
            StringBuilder stringBuilder = new StringBuilder();
            for (ScoreObject record : records) {
                stringBuilder.append(record.score).append(";").append(record.name).append(";\n");
            }
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10; i++) {
            highScores.add(new Text("%" + (i + 1) + "%" + records.get(i).getName(), records.get(i).getScore() + "", Config.TILES_HORIZONTAL - 2, 1, 4 + i));
        }
    }

    Enemy[] enemies = setEnemies(Config.ENEMY_MAX_COUNT);

    public Enemy[] setEnemies(int size) {
        Enemy[] enemies1 = new Enemy[size];
        for (int i = 0; i < size; i++) {
            enemies1[i] = new Enemy();
        }
        return enemies1;
    }

    // I gave up, this is the only thing that
    // came to my mind and actually worked
    // - Jan Poláček

    boolean firstRun = true;
    boolean pause = false;
    EndScreen endScreen = new EndScreen(keyHandler);
    Background background = new Background(player);
    Powerup powerup = new Powerup();
    Bullets bullets = new Bullets(keyHandler);

    public void update() {
        if (!pause) {
            for (Enemy enemy : enemies) {
                enemy.update();
                for (int i = 0; i < bullets.bullets.length; i++) {
                    if (enemy.getRect().intersects(bullets.bullets[i].getRect())) {
                        enemy.isHit();
                        bullets.bullets[i].isHit();
                        SCORE = SCORE + 200;
                    }
                }
                if (enemy.getRect().intersects(player.getRect())) {
                    enemy.isHit();
                    player.isHit();
                }
            }


            player.update();
            background.update(player);
            powerup.update(player, this);
            bullets.update(player, bulletInterval);
        }

        switch (panelState) {

            case MAIN_MENU -> {
                if (firstRun) {
                    player = new Player(keyHandler, true);
                    firstRun = false;
                }
                player.setxVel(-Config.PLAYER_SPEED / 2);
                player.setyVel(Config.PLAYER_SPEED / 2);
                if (Config.COIN_PLAY) {
                    if (Objects.equals(keyHandler.typedKey, "c")) {
                        panelState = PanelState.GAME;
                        firstRun = true;
                        keyHandler.typedKey = "";
                    }
                } else {
                    if (Objects.equals(keyHandler.typedKey, "\n")) {
                        panelState = PanelState.GAME;
                        firstRun = true;
                        keyHandler.typedKey = "";
                    }
                }
            }

            case GAME -> {
                if (firstRun) {
                    player = new Player(keyHandler);
                    bullets = new Bullets(keyHandler);
                    endScreen.resetName();
                    SCORE = 0;
                    firstRun = false;
                }
                if (Objects.equals(keyHandler.typedKey, "p")) {
                    pause = !pause;
                    keyHandler.typedKey = "";
                }
                if (!pause) SCORE++;
                if (!player.isAlive()) {
                    panelState = PanelState.END_SCREEN;
                    firstRun = true;
                }
            }

            case END_SCREEN -> {
                endScreen.update();
                if (keyHandler.enterPressed) {
                    NAME = endScreen.name.trim();
                    panelState = PanelState.SCORE_SCREEN;
                    keyHandler.typedKey = "";
                }
            }

            case SCORE_SCREEN -> {
                if (firstRun) {
                    sortRecords();
                    firstRun = false;
                }
                player.setxVel(-Config.PLAYER_SPEED / 2);
                player.setyVel(Config.PLAYER_SPEED / 2);
                if (Objects.equals(keyHandler.typedKey, "\n")) {
                    panelState = PanelState.MAIN_MENU;
                    firstRun = true;
                    keyHandler.typedKey = "";
                }
            }

            default -> {
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        drawSprite(graphics2D, 0, 8, 0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        for (int i = 0; i < background.planets.length; i++) {
            for (int j = 0; j < background.planets[i].length; j++) {
                drawSprite(graphics2D, background.planets[i][j]);
            }
        }
        for (int i = 0; i < background.first.length; i++) {
            for (int j = 0; j < background.first[0].length; j++) {
                drawSprite(graphics2D, background.second[i][j]);
                drawSprite(graphics2D, background.first[i][j]);
            }
        }

//        for (int i = 0; i < background.backgroundFirst.length; i++) {
//            for (int j = 0; j < background.backgroundFirst[0].length; j++) {
//                drawSprite(graphics2D, background.backgroundFirst[i][j]);
//                drawSprite(graphics2D, background.backgroundSecond[i][j]);
//            }
//        }

        switch (panelState) {
            case MAIN_MENU -> {
                if (Config.COIN_PLAY) {
                    drawText(graphics2D, new TextLined("" +
                            "EvadeAsteroid%n" +
                            "2.0%n" +
                            "%n%n%n%n%n%n" +
                            "insert coin%n" +
                            "to play",
                            Text.Alignment.TOP_CENTER, 0, 2));
                } else {
                    drawText(graphics2D, new TextLined("" +
                            "EvadeAsteroid%n" +
                            "2.0%n" +
                            "%n" +
                            "%n" +
                            "shoot by%n" +
                            panelController.shoot + "%n" +
                            "%n" +
                            "move by%n" +
                            panelController.move + "%n" +
                            "%n" +
                            "%n" +
                            "press " + panelController.enter + " to start",
                            Text.Alignment.TOP_CENTER, 0, 2));
                }
            }
            case GAME -> {
                drawSprite(graphics2D, powerup);
                drawSprite(graphics2D, player);
                drawSprite(graphics2D, bullets.bullets);
                drawSprite(graphics2D, enemies);
                for (int i = 0; i < Config.PLAYER_MAX_HEALTH; i++) {
                    drawSprite(graphics2D, new GUI(0, Config.TILES_VERTICAL - 1, i, GUIType.HEALTH, player));
                }
                for (int i = 0; i < Config.PLAYER_MAX_SHIELD; i++) {
                    drawSprite(graphics2D, new GUI(0, Config.TILES_VERTICAL - 2, i, GUIType.SHIELD, player));
                }
                drawText(graphics2D, new Text("score:" + SCORE, Text.Alignment.TOP_RIGHT, 0, 0));
                if (pause) {
                    drawSprite(graphics2D, 1, 8, 0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
                    drawText(graphics2D, new Text("PAUSED", Text.Alignment.MIDDLE_CENTER, 0, 0));
                }
            }
            case END_SCREEN -> {
                drawSprite(graphics2D, powerup);
                drawSprite(graphics2D, player);
                drawSprite(graphics2D, bullets.bullets);
                drawSprite(graphics2D, enemies);
                drawSprite(graphics2D, 1, 8, 0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
                drawText(graphics2D, new TextLined("" +
                        "press " + panelController.enter + " to continue%n" +
                        "%n" +
                        "%n" +
                        "%n" +
                        "your score:" + SCORE + "%n" +
                        "%n" +
                        endScreen.menu + "%n" +
                        "%n" +
                        "Game Over",
                        Text.Alignment.MIDDLE_CENTER, 0, -7
                ));
            }
            case SCORE_SCREEN -> {
                drawText(graphics2D, highScores);
                drawText(graphics2D, new Text("press " + panelController.enter + " to exit", Text.Alignment.TOP_CENTER, 0, 1));
            }
            default -> {
            }
        }

        graphics2D.dispose();
    }

    private void drawSprite(Graphics2D graphics2D, int xGrid, int yGrid, int xPos, int yPos) {
        graphics2D.drawImage(
                Sprite.getSprite(xGrid, yGrid),
                xPos, yPos,
                Config.TILE_COMPUTED,
                Config.TILE_COMPUTED,
                null
        );
    }

    private void drawSprite(Graphics2D graphics2D, Entity entity) {
        graphics2D.drawImage(
                Sprite.getSprite(entity.getXGrid(), entity.getYGrid()),
                (int) entity.getX(), (int) entity.getY(),
                entity.getWidth(),
                entity.getHeight(),
                null
        );
    }

    private void drawSprite(Graphics2D graphics2D, Entity[] entities) {
        for (Entity entity  : entities) {
            drawSprite(graphics2D, entity);
        }
    }

    private void drawSprite(Graphics2D graphics2D, int xGrid, int yGrid, int xPos, int yPos, int width, int height) {
        graphics2D.drawImage(
                Sprite.getSprite(xGrid, yGrid),
                xPos, yPos,
                width,
                height,
                null
        );
    }

    private void drawText(Graphics2D graphics2D, Text text) {
        for (int i = 0; i < text.getGrid().length; i++) {
            graphics2D.drawImage(
                    Sprite.getTextSprite(text.getGrid()[i][0], text.getGrid()[i][1]),
                    text.getX() + (Config.TILE_SIZE * Config.SCALE * (i)),
                    text.getY(),
                    text.getWidth(), text.getHeight(),
                    null
            );
        }
    }

    private void drawText(Graphics2D graphics2D, TextLined textLined) {
        for (int i = 0; i < textLined.getLinesCount(); i++) {
            drawText(graphics2D, textLined.getTexts()[i]);
        }
    }
    private void drawText(Graphics2D graphics2D, ArrayList<Text> texts) {
        for (Text text : texts) {
            drawText(graphics2D, text);
        }
    }

    private void drawText(Graphics2D graphics2D, LinkedList<Text> texts) {
        for (Text text : texts) {
            drawText(graphics2D, text);
        }
    }

    public PanelState getPanelState() {
        return panelState;
    }

    public void setPanelState(PanelState panelState) {
        this.panelState = panelState;
    }
}