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
import states.MainMenuState;
import states.StateManager;

/**
 *
 * @author Bruno
 */
public class Game extends Canvas implements Runnable {
    
    private static final int NUMBER_OF_BUFFERS = 2;
    private static final double TARGET_FPS = 60.0;
    private static final double NS_PER_UPDATE = 1000000000 / TARGET_FPS;
    
    private BufferStrategy bufferStrategy;
    private Graphics2D g;
    private StateManager stateManager;
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
        
        stateManager = new StateManager();
        stateManager.push(new MainMenuState());
        
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
        
        int UPSCounter = 0;
        int FPSCounter = 0;
        double timer = 0;
        final double NS_PER_SECOND = 1000000000;
        
        while (running) {
            long currentTime = System.nanoTime();
            double delta = currentTime - lastTime;
            lastTime = currentTime;
            steps += delta;
            
            processInput();
            
            while (steps >= NS_PER_UPDATE) {
                update(delta);
                steps -= NS_PER_UPDATE;
                UPSCounter++;
            }
            
            render();
            FPSCounter++;
            
            
            ////FPS Counter stuff. TODO - Implement properly.////
            timer += delta;
            if (timer > NS_PER_SECOND) {
                System.out.println("UPS: " + UPSCounter + ", FPS: " + FPSCounter);
                timer -= NS_PER_SECOND;
                UPSCounter = 0;
                FPSCounter = 0;
            }
            /////////////////////////////////////////////////////
            
            sleep(currentTime);
        }
    }
    
    public void processInput() {
        stateManager.peek().processInput();
    }
    
    public void update(double delta) {
        stateManager.peek().update(delta);
    }
    
    public void render() {
        do {
            do {
                g = (Graphics2D) bufferStrategy.getDrawGraphics();
                
                stateManager.peek().render(g);
                
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
