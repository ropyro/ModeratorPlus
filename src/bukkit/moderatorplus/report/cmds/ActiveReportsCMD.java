package bukkit.moderatorplus.report.cmds;

import bukkit.moderatorplus.ModeratorPlus;
import bukkit.moderatorplus.api.PermissionManager;
import bukkit.moderatorplus.report.guis.ActiveReportsGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ActiveReportsCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (ModeratorPlus.getInstance().getPermissionManager().mayUse(p, PermissionManager.Feature.ACTIVEREPORTS)) {
                p.openInventory(ActiveReportsGUI.INSTANCE.getInv());
            } else {
                ModeratorPlus.getInstance().sendMessage(p, ChatColor.RED + "No permission!");
            }

        }
        return false;
    }
}
