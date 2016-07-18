package ch.reaamz.jumpcreator;

import java.sql.SQLException;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ch.reaamz.jumpcreator.command.JumpCommand;
import ch.reaamz.jumpcreator.completer.JumpCompleter;
import ch.reaamz.jumpcreator.database.JumpDatabase;

public class JumpCreator extends JavaPlugin
{
	/*
	 * TODOList:
	 * ajout / modif des locs
	 * 
	 * 
	 * 
	 * Future:
	 * JumpScores, tJumpScores, etc
	 */
	
	public static Plugin instance;
	public static JumpDatabase database = new JumpDatabase();

	@Override
	public void onEnable()
	{
		instance = this;

		// setup de la database
		try
		{
			database.connectDatabase();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			Utils.logInfo("====================DATABASE CONNECTION ERROR=====================");
			Utils.logInfo(e.toString());
			Utils.logInfo("==================================================================");
		}

		JumpCommand jump = new JumpCommand();
		JumpCompleter completer = new JumpCompleter();
		getCommand("jump").setExecutor(jump);
		getCommand("jump").setTabCompleter(completer);
		
		Utils.logInfo("JumpCreator version 1 successfully loaded.");
	}

	@Override
	public void onDisable()
	{
		saveConfig();
		
		try
		{
			database.closeDatabase();
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
		}
	}
}
