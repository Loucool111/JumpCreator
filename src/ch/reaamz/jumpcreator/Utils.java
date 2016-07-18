package ch.reaamz.jumpcreator;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Utils
{
	public static final String PLUGIN_NAME = "JumpCreator";

	public static void broadcastCustomMessage(String message)
	{
		for (Player p : Bukkit.getServer().getOnlinePlayers())
		{
			p.sendMessage(ChatColor.GOLD + "[" + PLUGIN_NAME + "] " + ChatColor.RESET + message);
		}
	}

	public static void broadcastMessage(String message)
	{
		for (Player p : Bukkit.getServer().getOnlinePlayers())
		{
			p.sendMessage(message);
		}
	}

	public static void sendCustomMessage(Player player, String message)
	{
		player.sendMessage(ChatColor.GOLD + "[" + PLUGIN_NAME + "] " + ChatColor.RESET + message);
	}

	public static void logInfo(String info)
	{
		Bukkit.getServer().getLogger().info(info);
	}

	public static boolean isInteger(String s)
	{
		boolean isValidInteger = false;
		
		try
		{
			Integer.parseInt(s);
			isValidInteger = true;
		}
		catch (NumberFormatException ex) {}

		return isValidInteger;
	}
	
	public static Location getLocation(String rawLoc)
	{
		if (rawLoc == null) return null;
		
		rawLoc = rawLoc.substring(1, rawLoc.length() - 1);
		
		String[] items = rawLoc.split(", ");
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		Map<String, String> str = new HashMap<String, String>();
		
		for (String dItem : items)
		{
			String[] stra = dItem.split("=");
			str.put(stra[0], stra[1]);
		}
		
		map.put("world", "world");
		
		map.put("x", Double.valueOf(str.get("x")));
		map.put("y", Double.valueOf(str.get("y")));
		map.put("z", Double.valueOf(str.get("z")));
		
		map.put("pitch", Float.valueOf(str.get("pitch")));
		map.put("yaw", Float.valueOf(str.get("yaw")));
		
		return Location.deserialize(map);
	}
}
