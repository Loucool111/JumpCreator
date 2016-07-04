package ch.reaamz.jumpcreator.completer;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import ch.reaamz.jumpcreator.Utils;

public class JumpCompleter implements TabCompleter
{
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
	{
		if (command.getName().equalsIgnoreCase("jump"))
		{
			if (!args[0].equalsIgnoreCase(""))
			{
				if (args[0].equalsIgnoreCase("create")) return Arrays.asList("");
				if (args[0].equalsIgnoreCase("delete")) return Arrays.asList("0");
				if (args[0].equalsIgnoreCase("list")) return Arrays.asList("");
				if (args[0].equalsIgnoreCase("info")) return Arrays.asList("");
				
				if (Utils.isInteger(args[0]) && args.length > 1)
				{
					if (args.length > 2)
					{
						if (args[1].equalsIgnoreCase("get")) return Arrays.asList("Author","Name","TP","Start","End","RefTime");
						else if (args[1].equalsIgnoreCase("set")) return Arrays.asList("Name","TP","Start","End","RefTime");
					}
					else
						return Arrays.asList("get","set");
				}
				else
					return Arrays.asList("");
			}
			else
				return Arrays.asList("create", "delete", "list", "info", "0");
		}
		return null;
	}
}
