package bukkit.moderatorplus.playerinfo;

import bukkit.moderatorplus.ModeratorPlus;
import bukkit.moderatorplus.api.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerInfoCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        if(!ModeratorPlus.getInstance().getPermissionManager().mayUse(p, PermissionManager.Feature.PLAYERINFO)){
            ModeratorPlus.getInstance().sendMessage(p, ChatColor.RED + "No permission!");
            return false;
        }
        if (args.length > 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().equalsIgnoreCase(args[0])) {
                    p.sendMessage(ChatColor.GRAY + "Grabbing player info...");
                    p.sendMessage(ChatColor.GRAY + player.getName() + "'s Info" + ChatColor.DARK_GRAY + ":");
                    p.sendMessage(ChatColor.GRAY + "IP" + ChatColor.DARK_GRAY + ", " + ChatColor.WHITE + getIp());
                    p.sendMessage(ChatColor.GRAY + "GeoLocation" + ChatColor.DARK_GRAY + ", "  + ChatColor.WHITE + getGeoLocation());
                }
            }
        } else {
            ModeratorPlus.getInstance().sendMessage(p, "Invalid arguments! /playerinfo <player>");
        }
        return false;
    }

    public String getIp(){
        return "Unavailable";
    }

    public String getGeoLocation(){
        return "Unavailable";
    }
}
