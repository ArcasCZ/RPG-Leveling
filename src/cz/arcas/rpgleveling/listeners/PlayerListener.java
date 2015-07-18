/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.arcas.rpgleveling.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

/**
 *
 * @author Arcas
 */
public class PlayerListener implements Listener {
    
    public void onPlayerExpChange(PlayerExpChangeEvent event){
        int getExp = event.getAmount();
        Player player = event.getPlayer();
        
        int playerLevel = player.getLevel() + 1; // 10
        int expToLevel = 2500 * playerLevel; // 25 000
        float currentProgressPercent = player.getExp(); // 0,5
        float currentProgress = expToLevel * currentProgressPercent; // 12 500
        float newProgress = expToLevel / (currentProgress + getExp);
        
        player.setExp(newProgress);
    }
    
}
