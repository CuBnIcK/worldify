package me.macsmac.worldify;

import java.util.logging.Logger;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import Commands.Myworld;

public class Worldify extends JavaPlugin {
	private Logger log = getLogger();

	public void onEnable() {
		try {
			PushCommand(Myworld.class, "myworld");
		} catch (Exception e) {}
	}

	public void onDisable(){
		
	}
	
	private void PushCommand(Class Cmd, String name) throws Exception {
		CommandExecutor cmdExecutor = (CommandExecutor) Cmd.newInstance();
		
		getCommand(name).setExecutor(cmdExecutor);
	}
}