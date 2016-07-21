package ch.reaamz.jumpcreator.event;

import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JumpDoneEvent extends Event
{
	public static final HandlerList handlers = new HandlerList();
	private UUID who;
	private int jumpID;
	
	public JumpDoneEvent(int jumpID, UUID who)
	{
		this.jumpID = jumpID;
		this.who = who;
	}
	
	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
	public int getJumpID()
	{
		return this.jumpID;
	}
	
	public UUID getPlayer()
	{
		return this.who;
	}
}
