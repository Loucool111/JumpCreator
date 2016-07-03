package ch.reaamz.jumpcreator;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ch.reaamz.jumpcreator.command.JumpCommand;

public class JumpCreator extends JavaPlugin
{
	public static Plugin instance;

	@Override
	public void onEnable()
	{
		instance = this;
		
		JumpCommand jump = new JumpCommand();
		getCommand("jump").setExecutor(jump);
	}

	@Override
	public void onDisable()
	{
		saveConfig();
	}
}
