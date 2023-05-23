package cz.polacek.view;

import cz.polacek.config.Config;
import cz.polacek.engine.Panel;

import javax.swing.*;

public class Window {

    protected JFrame frame = new JFrame();
    protected Panel panel = new Panel(frame);

    public Window() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(Config.WINDOW_RESIZABLE);
        frame.setTitle(Config.TITLE);
        frame.setSize(Config.WINDOW_WIDTH + 16, Config.WINDOW_HEIGHT + 39); // idk why the fuck this is needed
        // frame.setSize(config.windowWidth, config.windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(Config.WINDOW_VISIBLE);
        frame.setLayout(null);
        frame.add(panel);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            new WindowError(e.toString());
        }
        panel.startGameThread();
    }

}
