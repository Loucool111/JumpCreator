package ch.reaamz.jumpcreator.event.listener;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.google.common.collect.Maps;

import ch.reaamz.jumpcreator.JumpCreator;
import ch.reaamz.jumpcreator.JumpTimer;

public class PlayerMoveListener implements Listener
{
	private HashMap<Integer, Location> jumpStarts = Maps.newHashMap();
	
	private HashMap<Integer, Location> jumpEnds = Maps.newHashMap();
	
	private HashMap<Integer, Runnable> timerIds = Maps.newHashMap();
	
	public PlayerMoveListener()
	{
		jumpStarts = JumpCreator.database.getJumpsStarts();
		
		jumpEnds = JumpCreator.database.getJumpsEnds();
	}
	
	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		
		for (Entry<Integer, Location> entry : jumpStarts.entrySet())
		{
			Location l = entry.getValue();
			int jumpID = entry.getKey();
			
			if (p.getLocation().getBlockX() == l.getBlockX() && p.getLocation().getBlockZ() == l.getBlockZ())
			{
				Runnable task = new JumpTimer(jumpID);
				
				int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(JumpCreator.instance, task, 0, 20);
				
				timerIds.put(taskId, task);
			}
		}
		for (Entry<Integer, Location> entry : jumpEnds.entrySet())
		{
			Location l = entry.getValue();
			int jumpID = entry.getKey();
			
			if (p.getLocation().getBlockX() == l.getBlockX() && p.getLocation().getBlockZ() == l.getBlockZ())
			{
				
			}
		}
	}
}
