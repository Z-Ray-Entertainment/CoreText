/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.script.state;

/**
 *
 * @author vortex
 */
public interface InterfaceState {
    public abstract void setName();
    public abstract String getName();
    public abstract void closeState();
}
