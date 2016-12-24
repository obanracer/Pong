/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Bruno
 */
public class Window extends JFrame {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    
    private static final String TITLE = "Pong";
    private static final String VERSION = "2.0";
    
    private Game game;
    
    public Window() {
        init();
    }
    
    private void init() {
        setTitle(TITLE + " " + VERSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setIgnoreRepaint(true);
        setVisible(true);
        
        game = new Game();
        add(game, BorderLayout.CENTER);
        
        pack();
        
        setLocationRelativeTo(null);
    }
    
    public void startGame() {
        game.start();
    }
}
