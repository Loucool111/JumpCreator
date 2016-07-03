package ch.reaamz.jumpcreator.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

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
	 * @param author the player that created the jump
	 * @param name the name of the jump
	 * @return the ID or -1 for duplicate name and -2 for another error
	 */
	public int createJump(UUID author, String name)
	{
		int returnValue = -2;

		try
		{
			Statement state = this.db.getConnection().createStatement();

			state.executeUpdate("INSERT INTO tJump (JUM_Author,JUM_Name) VALUES ('" + author.toString() + "','" + name + "');");

			ResultSet res = state.executeQuery("SELECT IDJump FROM tJump WHERE JUM_Author='" + author.toString() + "' AND JUM_Name='" + name + "';");

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
}
