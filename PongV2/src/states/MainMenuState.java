/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Color;
import java.awt.Graphics2D;
import main.Window;

/**
 *
 * @author Bruno
 */
public class MainMenuState extends GameState {

    @Override
    public void processInput() {
        
    }

    @Override
    public void update(double delta) {
        
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(50, 50, 100, 100);
    }
    
}
