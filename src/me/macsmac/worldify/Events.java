package me.macsmac.worldify;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Events implements Listener {
	private Logger log = Bukkit.getLogger();
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
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		String wname = player.getWorld().getName();
		
		if (wname.equals("world") || wname.equals("world_neter") || wname.equals("world_end")) return;
		if (!wname.equals("world_" + player.getName())) {
			event.setCancelled(true);
			player.sendMessage("You can't interact with stranger's world");
		}
	}
}
