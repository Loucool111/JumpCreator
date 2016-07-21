package ch.reaamz.jumpcreator.event;

import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JumpStartEvent extends Event
{
	public static final HandlerList handlers = new HandlerList();
	private UUID who;
	private int jumpID;
	
	public JumpStartEvent(UUID who, int jumpID)
	{
		this.who = who;
		this.jumpID = jumpID;
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
	
	public UUID getPlayer()
	{
		return this.who;
	}
	
	public int getJumpID()
	{
		return this.jumpID;
	}
}
