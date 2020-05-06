package com.github.fahjulian.rain;

import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.level.Level;
import com.github.fahjulian.rain.input.Keyboard;
import com.github.fahjulian.rain.input.Mouse;
import com.github.fahjulian.rain.entity.mob.Player;
import com.github.fahjulian.rain.math.Position;
import com.github.fahjulian.rain.math.GridPosition;

import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    
    public static Position cameraPos = new Position(0, 0);
    
    public static int width = 1920 / 6;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    public static String title = "Rain";

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;
    private Player player;
    private Level level;
    private Keyboard keyboard;
    private Mouse mouse;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        
        screen = new Screen(width, height);
        // level = new RandomLevel(64, 64);
        level = Level.SPAWN_LEVEL;
        keyboard = new Keyboard();
        mouse = new Mouse();
        Position spawnPosition = new GridPosition(80, 18).toPosition(16);
        player = new Player(spawnPosition, level);

        frame = new JFrame();

        addKeyListener(keyboard);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    /**
     * Start the game in a new Thread
     */
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    /**
     * Stop the game and it's thread
     */
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run the gameloop and stop the game afterwards
     */
    @Override
    public void run() {
        final double fps = 60.0;
        final double nsPerUpdate = 1.0e9 / fps;
        long timer = System.currentTimeMillis();
        long lastCycle = System.nanoTime();
        int updates = 0, frames = 0;
        double excessTicks = 0.0;

        while (running) {
            long now = System.nanoTime();
            excessTicks += (now - lastCycle) / nsPerUpdate;
            lastCycle = now;

            while (excessTicks >= 1) {
                update();
                excessTicks--;
                updates++;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                frame.setTitle(title + "  |  " + frames + " FPS, " + updates + " UPS");
                timer += 1000;
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    /**
     * Render all game members to the Screen
     */
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        level.render(cameraPos, screen);
        player.render(screen);

        for (int i = 0; i < pixels.length; i++)
            pixels[i] = screen.pixels[i];

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 0, 10));
        g.drawString(String.format("Camera: (%d, %d)", cameraPos.x, cameraPos.y), 15, 15);
        g.drawString(String.format("Player: (%d, %d)", player.pos.x, player.pos.y), 15, 25);
        
        g.dispose();
        bs.show();
    }

    /**
     * Update the game and all its members
     */
    public void update() {
        keyboard.update();
        player.update();
        level.update();

        if (Keyboard.up) cameraPos.y -= 2;
        if (Keyboard.down) cameraPos.y += 2;
        if (Keyboard.right) cameraPos.x += 2;
        if (Keyboard.left) cameraPos.x -= 2;

        if (Keyboard.autoCenterCamera || Keyboard.space) {
            cameraPos.x = player.getCenter().x - width / 2;
            cameraPos.y = player.getCenter().y - height / 2;
        }

        screen.setCameraPos(cameraPos.x, cameraPos.y);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.requestFocus();

        game.start();
    }
}
