package me.macsmac.worldify;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Events implements Listener {
	private Plugin plugin;
	
	public Events(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		final Player player = event.getPlayer();
		String world_name = "world_" + player.getName();
		final World world = Bukkit.getWorld(world_name);
		
		if (world != null) {
			player.sendMessage("Respawning...");
			
			new BukkitRunnable() {
				public void run() {
					player.teleport(world.getSpawnLocation());
				}
			}.runTaskLater(plugin, 20);
		}
	}
}
