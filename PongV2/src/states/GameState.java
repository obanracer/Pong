/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Graphics2D;

/**
 *
 * @author Bruno
 */
public abstract class GameState {
    public static final int MAIN_MENU = 0;
    
    public GameState() {
        
    }
    
    public abstract void processInput();
    public abstract void update(double delta);
    public abstract void render(Graphics2D g);
    
}
