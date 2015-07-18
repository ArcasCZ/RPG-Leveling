/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.arcas.rpgleveling.listeners;

import cz.arcas.rpgleveling.RPGLeveling;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

/**
 *
 * @author Arcas
 */
public class PlayerListener implements Listener {

    RPGLeveling plugin;
    public PlayerListener(RPGLeveling RPGLeveling) {
        this.plugin = RPGLeveling;
    }
    
    
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerExpChange(PlayerExpChangeEvent event){
        int getExp = event.getAmount(); // 3
        event.setAmount(0);
        
        Player player = event.getPlayer();
        
        int playerLevel = player.getLevel();
        
        Boolean startAtLvlOne = plugin.getConfig().getBoolean("startAtLvlOne");
        
        if(!startAtLvlOne){
            playerLevel++;
        }
        
        if(playerLevel == 0){
            player.setExp(1);
            return;
        }
        
        int xpBoost = plugin.getConfig().getInt("xpBoost");      
        int expToLevel = xpBoost * playerLevel;
        
        float currentProgressPercent = player.getExp();
        float currentProgress = currentProgressPercent * expToLevel;
        
        float newProgress = (currentProgress + getExp) / expToLevel;

        player.setExp(newProgress);
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLevelChange(PlayerLevelChangeEvent event){
        Player player = event.getPlayer();
        
        if(player.getLevel() == 0){
            return;
        }
        
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
    }
}
