package com.imtobis.nickplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NickPlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("nick").setExecutor(this);
        getCommand("unnick").setExecutor(this);
        getLogger().info("NickPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NickPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("nick")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("nick.use")) {
                    if (args.length == 1) {
                        String nickname = ChatColor.translateAlternateColorCodes('&', args[0]);
                        player.setDisplayName(nickname);
                        player.setPlayerListName(nickname);
                        player.setCustomName(nickname);
                        player.setCustomNameVisible(true);
                        player.sendMessage(ChatColor.GREEN + "Your nickname has been set to " + nickname + ".");
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid syntax. Usage: /nick <nickname>");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Only players can use this command.");
                return true;
            }
        } else if (cmd.getName().equalsIgnoreCase("unnick")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("unnick.use")) {
                    player.setDisplayName(player.getName());
                    player.setPlayerListName(player.getName());
                    player.setCustomName(player.getName());
                    player.setCustomNameVisible(false);
                    player.sendMessage(ChatColor.GREEN + "Your nickname has been removed.");
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Only players can use this command.");
                return true;
            }
        }
        return false;
    }
}
