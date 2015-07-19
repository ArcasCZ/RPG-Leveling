/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.arcas.rpgleveling;

import cz.arcas.rpgleveling.commands.Level;
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
        saveDefaultConfig();
  
        String version = getDescription().getVersion();
        String configVersion = getConfig().getString("version");
        
        if(!version.equalsIgnoreCase(configVersion)){
            getLogger().warning("Config version mismatch!");
            getLogger().warning("Please create backup of old config, delete old config, and set up it again.");
            getLogger().warning("Config version: " + configVersion);
            getLogger().warning("Plugin version: " + version);
            setEnabled(false);
            return;
        }
        
        plugin = this;
        
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);

        
        loadCommands();

        
        
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
    
    private void loadCommands(){
        getCommand("level").setExecutor(new Level());
    }

    
    
}
