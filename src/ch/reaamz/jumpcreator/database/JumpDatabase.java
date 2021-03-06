package ch.reaamz.jumpcreator.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;

import com.google.common.collect.Maps;

import ch.reaamz.jumpcreator.JumpCreator;
import code.husky.mysql.MySQL;

public class JumpDatabase
{
	private MySQL db;

	public void connectDatabase() throws ClassNotFoundException, SQLException
	{
		this.db = new MySQL(JumpCreator.instance, "localhost", "3306", "JumpCreator", "root", "");
		this.db.openConnection();
	}

	public void closeDatabase() throws SQLException
	{
		this.db.closeConnection();
	}

	/**
	 * 
	 * @param author
	 *            the player that created the jump
	 * @param name
	 *            the name of the jump
	 * @return the ID or -1 for duplicate name and -2 for another error
	 */
	public int createJump(UUID author, String name)
	{
		int returnValue = -2;

		try
		{
			Statement state = this.db.getConnection().createStatement();

			state.executeUpdate(
					"INSERT INTO tJump (JUM_Author,JUM_Name) VALUES ('" + author.toString() + "','" + name + "');");

			ResultSet res = state.executeQuery("SELECT IDJump FROM tJump WHERE JUM_Author='" + author.toString()
					+ "' AND JUM_Name='" + name + "';");

			if (!res.next())
			{
				state.close();
				return -1;
			}

			int id = res.getInt("IDJump");
			state.close();

			return id;
		}
		catch (SQLException e)
		{
			if (e.getMessage().startsWith("Duplicate"))
			{
				returnValue = -1;
			}
			else
				e.printStackTrace();
		}
		return returnValue;
	}

	public boolean deleteJump(UUID who, int JumpID)
	{

		return false;
	}

	public boolean changeJumpName(UUID who, int JumpID, String newName)
	{
		return false;
	}

	public boolean changeJumpTP(UUID who, int JumpID, Location tpLoc)
	{
		try
		{
			Statement state = this.db.getConnection().createStatement();

			state.executeUpdate("INSERT INTO tJump (JUM_TP) VALUES ('" + tpLoc.serialize().toString()
					+ "') WHERE IDJump=" + JumpID + "");

			state.close();

			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{

		}

		return false;
	}

	public boolean changeJumpStart(UUID who, int JumpID, Location startLoc)
	{
		return false;
	}

	public boolean changeJumpEnd(UUID who, int JumpID, Location endLoc)
	{
		return false;
	}

	public boolean changeJumpRefTime(UUID who, int JumpID, String RefTime)
	{
		return false;
	}

	public HashMap<Integer, Location> getJumpsStarts()
	{
		HashMap<Integer, Location> starts = Maps.newHashMap();

		try
		{
			Statement state = this.db.getConnection().createStatement();

			ResultSet res = state.executeQuery("SELECT IDJump, JUM_Start FROM tJump;");
			
			while(res.next())
			{
				String loc = res.getString("JUM_Start");
				int id = res.getInt("IDJump");
				
				if (loc != null)
				{
					String[] parts = loc.split(";");
					
					Location l = new Location(JumpCreator.instance.getServer().getWorld("overworld"), Double.parseDouble(parts[0]), Double.parseDouble(parts[0]), Double.parseDouble(parts[0]));
					
					starts.put(id, l);
				}
			}

			state.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return starts;

	}
	
	public HashMap<Integer, Location> getJumpsEnds()
	{
		HashMap<Integer, Location> starts = Maps.newHashMap();

		try
		{
			Statement state = this.db.getConnection().createStatement();

			ResultSet res = state.executeQuery("SELECT IDJump, JUM_End FROM tJump;");
			
			while(res.next())
			{
				String loc = res.getString("JUM_End");
				int id = res.getInt("IDJump");
				
				if (loc != null)
				{
					String[] parts = loc.split(";");
					
					Location l = new Location(JumpCreator.instance.getServer().getWorld("overworld"), Double.parseDouble(parts[0]), Double.parseDouble(parts[0]), Double.parseDouble(parts[0]));
					
					starts.put(id, l);
				}
			}

			state.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return starts;

	}

	/*
	 * public String getJumpInfo(UUID who, int JumpID, String InfoName) { return
	 * ""; }
	 */
}
