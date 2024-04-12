package com.codenamemc.loader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new loadersystem(this), this);
        getServer().getPluginManager().registerEvents(new openshop(this), this);
        getServer().getPluginManager().registerEvents(new inshop(this), this);
        getServer().getPluginManager().registerEvents(new ifclick(this), this);
        getLogger().info("Loader 플러그인 활성화됨!");

    }

    @Override
    public void onDisable() {
        getLogger().info("Loader 플러그인 비활성화됨!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equals("shop")) openshop.openInvShop((Player) sender);
        else if(cmd.getName().equals("gotohome")) loadersystem.gotoHome((Player) sender);
        else if(cmd.getName().equals("loadertutorial")) loadersystem.tutorial((Player) sender);
        return false;
    }
}

