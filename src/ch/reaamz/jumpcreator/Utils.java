package ch.reaamz.jumpcreator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
}
