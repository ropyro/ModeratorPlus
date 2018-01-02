package bukkit.moderatorplus.staffchat;

import bukkit.moderatorplus.ModeratorPlus;
import bukkit.moderatorplus.api.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class StaffChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        if(!ModeratorPlus.getInstance().getPermissionManager().mayUse(p, PermissionManager.Feature.STAFFCHAT)) return;
        if (event.getMessage().charAt(0) == '@' || ModeratorPlus.getInstance().staffChatCMD.isInStaffChat(p)) {
            Bukkit.getOnlinePlayers().stream().filter(player -> ModeratorPlus.getInstance().getPermissionManager().mayUse(player, PermissionManager.Feature.STAFFCHAT)).forEach(player -> player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "STAFF" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET + p.getDisplayName() + ChatColor.DARK_GRAY + " > " + ChatColor.GRAY + event.getMessage().replace("@", "")));
            event.setCancelled(true);
        }
    }
}
