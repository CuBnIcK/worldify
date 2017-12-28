package Helpers;

import org.bukkit.entity.Player;

public class CastPlayer {
	public static <T> Player Cast(T val) {
		return ((Player) val);
	}
}
