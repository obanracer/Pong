/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;

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
        while (running) {            
            System.out.println("running :D");
        }
    }
    
    public void processInput() {
        
    }
    
    public void update(double delta) {
        
    }
    
    public void render() {
        
    }
    
}
