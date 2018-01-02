package bukkit.moderatorplus;

import bukkit.moderatorplus.api.ModeratorCMD;
import bukkit.moderatorplus.api.PermissionManager;
import bukkit.moderatorplus.playerinfo.PlayerInfoCMD;
import bukkit.moderatorplus.staffchat.StaffChatCMD;
import bukkit.moderatorplus.staffchat.StaffChatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author MrRonin
 * @version 1.0
 */
public class ModeratorPlus extends JavaPlugin {

    private static ModeratorPlus INSTANCE;

    private final PermissionManager permissionManager = new PermissionManager();

    /**
     * Commands:
     */
    public static final ModeratorCMD moderatorCMD = new ModeratorCMD();
    public static final StaffChatCMD staffChatCMD = new StaffChatCMD();
    public static final PlayerInfoCMD playerInfoCMD = new PlayerInfoCMD();

    /**
     * Listeners:
     */
    public static final StaffChatListener staffChatListener = new StaffChatListener();

    @Override
    public void onEnable() {
        super.onEnable();
        INSTANCE = this;
        getCommand("staffchat").setExecutor(staffChatCMD);
        getCommand("playerinfo").setExecutor(playerInfoCMD);
        Bukkit.getPluginManager().registerEvents(staffChatListener, this);
    }
    @Override
    public void onDisable() {
        super.onDisable();

    }


    public PermissionManager getPermissionManager() {
        return permissionManager;
    }

    public void sendMessage(Player target, String msg){
        target.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "Moderator" + ChatColor.RED + "+" + ChatColor.DARK_GRAY +"] " + ChatColor.GRAY + msg);
    }

    public static ModeratorPlus getInstance(){
        return INSTANCE;
    }
}
