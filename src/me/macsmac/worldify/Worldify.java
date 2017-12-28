package me.macsmac.worldify;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Worldify extends JavaPlugin {
	private Logger log = getLogger();

	public void onEnable(){

	}

	public void onDisable(){
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		
		String name = cmd.getName().toLowerCase();
		String world_name = "world_" + player.getName();
		
		if (name.equals("myworld")) {
			World pworld = player.getWorld();
			
			if (pworld.getName().equals(world_name)) {
				player.sendMessage("You are already in your world");
				return true;
			}
			
			player.sendMessage("Your world is: " + world_name);
			
			World world = Bukkit.getWorld(world_name);
			
			if (world == null) {
				player.sendMessage("Generating world");
				
				WorldCreator creator = new WorldCreator(world_name);
				
				creator.environment(World.Environment.NORMAL);
				creator.generateStructures(true);
				
				world = creator.createWorld();
			}
			
			player.sendMessage("Teleporting");
			
			player.teleport(world.getSpawnLocation());
		}
		
		return true;
	}
}