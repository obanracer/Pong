/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class Game extends Canvas implements Runnable {
    
    private static final int NUMBER_OF_BUFFERS = 2;
    private static final double TARGET_FPS = 60.0;
    private static final double NS_PER_UPDATE = 1000000000 / TARGET_FPS;
    
    private BufferStrategy bufferStrategy;
    private boolean running = false;
    
    public Game() {
        init();
    }

    private void init() {
        setMinimumSize(new Dimension(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT));
        setMaximumSize(new Dimension(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT));
        setPreferredSize(new Dimension(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT));
        
        setIgnoreRepaint(true);
        setFocusable(running);
        
        requestFocus();
    }
    
    public synchronized void start() {
        createBufferStrategy(NUMBER_OF_BUFFERS);
        bufferStrategy = getBufferStrategy();
        
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop() {
        running = false;
    }
    
    @Override
    public void run() {
        double steps = 0.0;
        long lastTime = System.nanoTime();
        while (running) {
            long currentTime = System.nanoTime();
            double delta = currentTime - lastTime;
            lastTime = currentTime;
            steps += delta;
            
            processInput();
            
            while (steps >= NS_PER_UPDATE) {
                update(delta);
                steps -= NS_PER_UPDATE;
            }
            
            render();
            
            sleep(currentTime);
        }
    }
    
    public void processInput() {
        
    }
    
    public void update(double delta) {
        
    }
    
    public void render() {
        do {
            do {
                Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
                
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
                
                g.dispose();
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }
    
    public void sleep(long currentTime) {
        double endTime = currentTime + NS_PER_UPDATE;
        while (System.nanoTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
