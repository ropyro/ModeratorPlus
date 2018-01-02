package bukkit.moderatorplus.staffchat;

import bukkit.moderatorplus.ModeratorPlus;
import bukkit.moderatorplus.api.PermissionManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class StaffChatCMD implements CommandExecutor {

    private final ArrayList<Player> players;

    public StaffChatCMD(){
        players = new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (ModeratorPlus.getInstance().getPermissionManager().mayUse(p, PermissionManager.Feature.STAFFCHAT)) {
                if (players.contains(p)) {
                    players.remove(p);
                    ModeratorPlus.getInstance().sendMessage(p, ChatColor.RED + "No longer talking in the staff chat.");
                } else {
                    players.add(p);
                    ModeratorPlus.getInstance().sendMessage(p, ChatColor.GREEN + "Now talking in the staff chat.");
                }
            } else {
                ModeratorPlus.getInstance().sendMessage(p, ChatColor.RED + "No permission!");
            }

        }
        return false;
    }

    public boolean isInStaffChat(Player p){
        if(players.contains(p)){
            return true;
        }
        return false;
    }
}
