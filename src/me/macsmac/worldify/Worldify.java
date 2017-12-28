package me.macsmac.worldify;

import java.util.logging.Logger;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.macsmac.worldify.Commands.Myworld;

public class Worldify extends JavaPlugin {
	public void onEnable() {
		try {
			PushCommand(Myworld.class, "myworld");
			
			PluginManager manager = getServer().getPluginManager();
		    manager.registerEvents(new Events(this), this);
		} catch (Exception e) {}
	}

	public void onDisable(){
		
	}
	
	private void PushCommand(Class Cmd, String name) throws Exception {
		CommandExecutor cmdExecutor = (CommandExecutor) Cmd.newInstance();
		
		getCommand(name).setExecutor(cmdExecutor);
	}
}