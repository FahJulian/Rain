package com.github.fahjulian.rain.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[120];
    public boolean up, down, left, right;
    public boolean w, a, s, d;
    public boolean autoCenterCamera = true;
    public boolean space = false;

    public void update() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        right = keys[KeyEvent.VK_RIGHT];
        left = keys[KeyEvent.VK_LEFT];
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        s = keys[KeyEvent.VK_S];
        d = keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {   
        if (e.getKeyCode() == KeyEvent.VK_X) autoCenterCamera = !autoCenterCamera;
        keys[e.getKeyCode()] = false; 
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
