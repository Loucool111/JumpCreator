package ch.reaamz.jumpcreator.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.reaamz.jumpcreator.JumpCreator;
import ch.reaamz.jumpcreator.Utils;

public class JumpCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			
			if (cmd.getName().equalsIgnoreCase("jump"))
			{
				if (args.length > 0)
				{
					if (args[0].equalsIgnoreCase("create"))
					{
						if (args.length > 1 && args[1].length() <= 25)
						{
							int resultID = JumpCreator.database.createJump(p.getUniqueId(), args[1]);

							if (resultID > -1)
							{
								Utils.sendCustomMessage(p, ChatColor.GREEN + "Successfully created Jump '" + args[1] + "'. JumpID : " + resultID);
								return true;
							}
							else if (resultID == -1)
							{
								Utils.sendCustomMessage(p, ChatColor.RED + "This jump name already exists !");
								return true;
							}
							else if (resultID == -2)
							{
								Utils.sendCustomMessage(p, ChatColor.RED + "Failed to create jump.");
								return true;
							}
						}
						else
						{
							Utils.sendCustomMessage(p, "Jump must have a valid name ! less than 25 characters");
							return true;
						}
					}
					else if (args[0].equalsIgnoreCase("delete"))
					{
						if (Utils.isInteger(args[1]))
						{
							boolean success = JumpCreator.database.deleteJump(p.getUniqueId(), Integer.parseInt(args[1]));
							
							if (success)
								Utils.sendCustomMessage(p, ChatColor.GREEN + "Successfully deleted JumpId : " + args[1]);
							else
								Utils.sendCustomMessage(p, ChatColor.RED + "Could not delete JumpID : " + args[1] + ". It either doesn't exist or you do not have permission to delete it.");
						}
						else
						{
							sendHelp(p, "ID must be an number !");
							return true;
						}
					}
					else if (Utils.isInteger(args[0]))
					{
						
					}
					else
					{
						sendHelp(p, "First argument must be a number or 'create' or 'delete' !");
						return true;
					}
				}
				else
				{
					sendHelp(p, "Jump command needs arguments. See syntax below:");
					return true;
				}

			}
		}
		return false;
	}

	private void sendHelp(Player p, String message)
	{
		Utils.sendCustomMessage(p, ChatColor.RED + message);
		Utils.sendCustomMessage(p, ChatColor.GOLD + "Jump Command Syntax :");
		Utils.sendCustomMessage(p, ChatColor.AQUA + "/jump list");
		Utils.sendCustomMessage(p, ChatColor.AQUA + "/jump create <Name>");
		Utils.sendCustomMessage(p, ChatColor.AQUA + "/jump delete <ID>");
		Utils.sendCustomMessage(p, ChatColor.AQUA + "/jump <ID> get <Author|Name|TP|Start|End|RefTime>");
		Utils.sendCustomMessage(p, ChatColor.AQUA + "/jump <ID> set <Name|TP|Start|End|RefTime>");
	}

}
