package ch.reaamz.jumpcreator.event.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener
{
	private List<Location> jumpStarts = new ArrayList<>();
	
	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		
		for (Location l : jumpStarts)
		{
			if (p.getLocation().getBlockX() == l.getBlockX() && p.getLocation().getBlockZ() == l.getBlockZ())
			{
				
			}
		}
	}
}
