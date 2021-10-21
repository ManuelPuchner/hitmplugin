/*
@Author: MatseCrafter_304
@Date: 18.10.2021
*/
package at.hitm.hitmplugin.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import at.hitm.hitmplugin.Main;

public class Enderchest implements CommandExecutor{
	
	public static ArrayList<UUID> enderchests = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("enderchest.open")) {
				if(args.length == 0) {
					p.openInventory(p.getEnderChest());
				} else if(args.length == 1) {
					if(p.hasPermission("enderchest.edit")) {
						Player target = Bukkit.getPlayer(args[0]);
						if(target == null) {
							Player offlineTarget = (Player) Bukkit.getOfflinePlayer(args[0]);
							if(offlineTarget == null) {
								p.sendMessage("§4Dieser Spieler existiert nicht oder besitzt keine Enderchest auf dem HITM Server");
							} else {
								p.openInventory((offlineTarget).getEnderChest());
								enderchests.contains(p.getUniqueId());
							}
							
						} else {
							p.openInventory(target.getEnderChest());
							enderchests.contains(p.getUniqueId());
						}
					} else {
						p.sendMessage("§4 Du hast keine Berechtigungen für das!");
					}
				} else {
					p.sendMessage("§fVerwende " + label + "§f<Spieler>");
				}
			} else {
				p.sendMessage("§4 Du hast keine Berechtigungen für das!");
			}
		} else {
			sender.sendMessage("§4Du musst ein Spieler sein um diesen command auszuführen");
		}
		return false;
	}
	
}
