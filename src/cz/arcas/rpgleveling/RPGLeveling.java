/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.arcas.rpgleveling;

import cz.arcas.rpgleveling.listeners.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Arcas
 */
public final class RPGLeveling extends JavaPlugin{
    @Override
    public void onEnable(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);
        
        System.out.println("Loaded!");
        System.out.println("By ArcasCZ | http://uranus-portal.com");
    }
    
    @Override
    public void onDisable(){
        System.out.println("Unloaded!");
        System.out.println("By ArcasCZ | http://uranus-portal.com");
    }
    
    
    
}