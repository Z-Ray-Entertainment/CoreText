/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.script.state;

import java.util.Stack;

/**
 *
 * @author vortex
 */
public class StateStack {
    private Stack<InterfaceState> stack = new Stack<>();
    
    public void pushState(InterfaceState state){
        stack.push(state);
    }
    
    public void popState(){
        stack.pop().closeState();
    }
    
    public String getLastState(){
        return stack.get(stack.lastIndexOf(stack)).getName();
    }
}
