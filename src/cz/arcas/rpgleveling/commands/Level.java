/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.arcas.rpgleveling.commands;

import cz.arcas.rpgleveling.RPGLeveling;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Arcas
 */
public class Level implements CommandExecutor{
    
    RPGLeveling plugin;

    public Level(){
        plugin = RPGLeveling.getPlugin();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player;
        
        if(args.length > 0){
            String name = args[0];
            player = Bukkit.getPlayer(name);
            if(player == null || !player.isOnline()){
                sender.sendMessage(ChatColor.RED + "Player is offline!");
                return false;
            }
        }else if(sender instanceof Player){
            player = (Player) sender;
        }else{
            sender.sendMessage("This command is only for players!");
            return false;
        }
        
        if(!sender.hasPermission("rpgleveling.level")){
            sender.sendMessage(ChatColor.RED + "You don't have permissions for this!");
            return false;
        }
        
        int xpBoost = 0;
        float progressPercent = player.getExp();
        int playersXp = player.getTotalExperience();
        int xpForLevel = player.getExpToLevel();
        int playerRealLevel = player.getLevel();
        int playerLevel = player.getLevel();
        
        if(plugin.getConfig().getBoolean("enableXpBoost")){
            xpBoost = plugin.getConfig().getInt("xpBoost");
            if(!plugin.getConfig().getBoolean("startAtLvlOne")){
                playerLevel++;
            }
            xpForLevel = xpBoost * playerLevel;
            playersXp = Math.round(xpForLevel * progressPercent);            
        }
        
        sender.sendMessage(ChatColor.BLUE + "------- Level Information -------");
        sender.sendMessage(ChatColor.WHITE + "Level: " + playerRealLevel);
        sender.sendMessage(ChatColor.WHITE + "Progress: " + round(progressPercent, 2) + "%");
        sender.sendMessage(ChatColor.WHITE + "XP: " + playersXp + " / " + xpForLevel);

        return true;
    }

    private float round(float number, int decimals) {
        if (decimals < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, decimals);
        number = number * factor;
        long tmp = Math.round(number);
        return (float) tmp / factor;
    }
    
}
