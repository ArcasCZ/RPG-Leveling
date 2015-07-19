/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.arcas.rpgleveling.listeners;

import cz.arcas.rpgleveling.RPGLeveling;
import org.bukkit.Bukkit;
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
    public PlayerListener() {
        this.plugin = RPGLeveling.getPlugin();
    }
    
    
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerExpChange(PlayerExpChangeEvent event) {       
        if(!plugin.getConfig().getBoolean("enableXpBoost")){
            return;
        }
        
        int exp = event.getAmount();
        event.setAmount(0);

        Player player = event.getPlayer();

        int playerLevel = player.getLevel();

        Boolean startAtLvlOne = plugin.getConfig().getBoolean("startAtLvlOne");

        if (!startAtLvlOne)
            playerLevel++;

        if (playerLevel == 0) {
            player.setExp(1);
            return;
        }

        int xpBoost = plugin.getConfig().getInt("xpBoost");
        int expToLevel = xpBoost * playerLevel;

        float currentProgressPercent = player.getExp();
        float currentProgress = currentProgressPercent * expToLevel;

        float newProgress = (currentProgress + exp) / expToLevel;

        player.setExp(newProgress);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
        Player player = event.getPlayer();

        if (player.getLevel() == 0) {
            return;
        }
        
        if(plugin.getConfig().getBoolean("useRankupCommand")){
            String command = plugin.getConfig().getString("rankupCommand");
            command = command.replaceAll("\\{user\\}", player.getName());
            command = command.replaceAll("\\{level\\}", Integer.toString(player.getLevel()));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        }
        if(event.getOldLevel() > event.getNewLevel() && plugin.getConfig().getBoolean("playSoundOnLevelUp")){
            String soundString = plugin.getConfig().getString("levelUpSound");
            Sound sound = Sound.valueOf(soundString);

            player.playSound(player.getLocation(), sound, 1F, 1F);
        }
        
        if(event.getOldLevel() < event.getNewLevel() && plugin.getConfig().getBoolean("playSoundOnLevelDowb")){
            String soundString = plugin.getConfig().getString("levelDownSound");
            Sound sound = Sound.valueOf(soundString);

            player.playSound(player.getLocation(), sound, 1F, 1F);
        }
    }
}
