package bukkit.moderatorplus.report.cmds;

import bukkit.moderatorplus.ModeratorPlus;
import bukkit.moderatorplus.report.guis.ReportTypeGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        if (args.length > 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().equalsIgnoreCase(args[0])) {
                    p.openInventory(ReportTypeGUI.INSTANCE.getInv(p, player));
                    return false;
                }
            }
            ModeratorPlus.getInstance().sendMessage(p, ChatColor.RED + args[0] + ChatColor.GRAY + " is not online!");
        } else {
            ModeratorPlus.getInstance().sendMessage(p, "Invalid arguments! /playerinfo <player>");
        }
        return false;
    }
}
