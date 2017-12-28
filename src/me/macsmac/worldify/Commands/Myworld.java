package me.macsmac.worldify.Commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.macsmac.worldify.Helpers.CastPlayer;

public class Myworld implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = CastPlayer.Cast(sender);
		String world_name = "world_" + player.getName();
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
		
		return true;
	}
}
