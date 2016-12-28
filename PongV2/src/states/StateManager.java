/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author Bruno
 */
public class StateManager {
    Deque<GameState> stateStack;

    public StateManager() {
        stateStack = new ArrayDeque<>();
    }
    
    public void push(GameState s) {
        stateStack.push(s);
    }
    
    public GameState peek() {
        return stateStack.peek();
    }
    
    public void pop() {
        stateStack.pop();
    }
}
