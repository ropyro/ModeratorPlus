package bukkit.moderatorplus;

import bukkit.moderatorplus.api.ModeratorCMD;
import bukkit.moderatorplus.api.PermissionManager;
import bukkit.moderatorplus.playerinfo.PlayerInfoCMD;
import bukkit.moderatorplus.report.cmds.ActiveReportsCMD;
import bukkit.moderatorplus.report.cmds.ReportCMD;
import bukkit.moderatorplus.report.guis.ReportTypeGUI;
import bukkit.moderatorplus.staffchat.StaffChatCMD;
import bukkit.moderatorplus.staffchat.StaffChatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
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
    public static final ReportCMD reportCMD = new ReportCMD();
    public static final ActiveReportsCMD activeReportsCMD = new ActiveReportsCMD();

    /**
     * Listeners:
     */
    public static final StaffChatListener staffChatListener = new StaffChatListener();

    @Override
    public void onEnable() {
        super.onEnable();
        INSTANCE = this;

        //Commands
        registerCommand("staffchat", staffChatCMD);
        registerCommand("playerinfo", playerInfoCMD);
        registerCommand("report", reportCMD);
        registerCommand("activereports", activeReportsCMD);

        //Events
        registerEvents(staffChatListener);
        registerEvents(ReportTypeGUI.INSTANCE);
    }
    @Override
    public void onDisable() {
        super.onDisable();

    }

    public void registerEvents(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
    public void registerCommand(String label, CommandExecutor commandExecutor){
        getCommand(label).setExecutor(commandExecutor);
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
