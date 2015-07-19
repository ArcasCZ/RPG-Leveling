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
    
    private static RPGLeveling plugin;
    @Override
    public void onEnable(){
        plugin = this;
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);

        saveDefaultConfig();
        
        getLogger().info("Loaded!");
        getLogger().info("By ArcasCZ | http://uranus-portal.com");
    }
    
    @Override
    public void onDisable(){
        getLogger().info("Unloaded!");
        getLogger().info("By ArcasCZ | http://uranus-portal.com");
    }
    
    
    public static RPGLeveling getPlugin(){
        return plugin;
    }

    
    
}
