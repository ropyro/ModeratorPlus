package bukkit.moderatorplus.report.guis;

import bukkit.moderatorplus.ModeratorPlus;
import bukkit.moderatorplus.report.Report;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import sun.security.x509.AVA;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReportHandleGUI implements Listener {

    public static final ReportHandleGUI INSTANCE;

    private Inventory inv;

    private Report report;

    private ReportHandleGUI() {
        inv = Bukkit.createInventory(null, 45, "Handle report, ");
    }

    static {
        INSTANCE = new ReportHandleGUI();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getClickedInventory().getTitle() == inv.getTitle())) return;

        Player p = (Player) event.getWhoClicked();

        ItemStack item = event.getCurrentItem();
        ItemMeta meta = item.getItemMeta();

        if (meta.getDisplayName().equalsIgnoreCase(ChatColor.RED + "Exit") && item.getType() == Material.BARRIER) {
            p.closeInventory();
        } else if (meta.getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Accept Report") && item.getType() == Material.EMERALD_BLOCK) {
            ActiveReportsGUI.INSTANCE.removeReport(report);
            ModeratorPlus.getInstance().sendMessage(report.getReporter(), "Report against, " + report.getTarget().getName() + " has been " + ChatColor.GREEN + "accepted" + ChatColor.GRAY + "!");
            p.openInventory(ActiveReportsGUI.INSTANCE.getInv());
        } else if (meta.getDisplayName().equalsIgnoreCase(ChatColor.RED + "Deny Report") && item.getType() == Material.REDSTONE_BLOCK) {
            ActiveReportsGUI.INSTANCE.removeReport(report);
            ModeratorPlus.getInstance().sendMessage(report.getReporter(), "Report against, " + report.getTarget().getName() + " has been " + ChatColor.RED + "denied" + ChatColor.GRAY + "!");
            p.openInventory(ActiveReportsGUI.INSTANCE.getInv());
        }

        event.setCancelled(true);
    }


    public Inventory getInv(Report report) {
        this.report = report;

        /**
         * General Items
         */
        ItemStack boarder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta boarderMeta = boarder.getItemMeta();
        boarderMeta.setDisplayName(ChatColor.RESET + "");
        boarder.setItemMeta(boarderMeta);

        ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta acceptMeta = accept.getItemMeta();
        acceptMeta.setDisplayName(ChatColor.GREEN + "Accept Report");
        accept.setItemMeta(acceptMeta);

        ItemStack deny = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta denyMeta = deny.getItemMeta();
        denyMeta.setDisplayName(ChatColor.RED + "Deny Report");
        deny.setItemMeta(denyMeta);

        ItemStack exit = new ItemStack(Material.BARRIER, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit");
        exit.setItemMeta(exitMeta);

        ItemStack infoBook = new ItemStack(Material.BOOK, 1);
        ItemMeta infoBookMeta = infoBook.getItemMeta();
        infoBookMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "+------" + ChatColor.RESET + ChatColor.WHITE + " Info " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----+");
        List<String> infoBookLore = new ArrayList<>();
        infoBookLore.add(ChatColor.GRAY + " Report Handler,");
        infoBookLore.add(ChatColor.WHITE + "  Either accept");
        infoBookLore.add(ChatColor.WHITE + "  or deny the");
        infoBookLore.add(ChatColor.WHITE + "  the report.");
        infoBookLore.add(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "+----------------+");
        infoBookMeta.setLore(infoBookLore);
        infoBook.setItemMeta(infoBookMeta);

        ItemStack reportItem = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta reportItemMeta = (SkullMeta) reportItem.getItemMeta();
        reportItemMeta.setOwner(report.getTarget().getName());
        reportItemMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "+------" + ChatColor.RESET + ChatColor.WHITE + " ID " + report.getID() + " " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----+");
        List<String> reportItemLore = new ArrayList<>();
        reportItemLore.add(ChatColor.GRAY + " Reported Player,");
        reportItemLore.add(ChatColor.WHITE + "  " + report.getTarget().getName());
        reportItemLore.add(ChatColor.GRAY + " Reporter,");
        reportItemLore.add(ChatColor.WHITE + "  " + report.getReporter().getName());
        reportItemLore.add(ChatColor.GRAY + " Reason,");
        reportItemLore.add(ChatColor.WHITE + "  " + report.getReason());
        reportItemLore.add(ChatColor.GRAY + " Timestamp,");
        reportItemLore.add(ChatColor.WHITE + "  " + report.getTime());
        reportItemLore.add(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "+-----------------+");
        reportItemMeta.setLore(reportItemLore);
        reportItem.setItemMeta(reportItemMeta);

        inv.setItem(0, boarder);
        inv.setItem(1, boarder);
        inv.setItem(2, boarder);
        inv.setItem(3, infoBook);
        inv.setItem(4, boarder);
        inv.setItem(5, exit);
        inv.setItem(6, boarder);
        inv.setItem(7, boarder);
        inv.setItem(8, boarder);

        inv.setItem(9, boarder);
        inv.setItem(10, boarder);
        inv.setItem(11, boarder);
        inv.setItem(12, boarder);
        inv.setItem(13, reportItem);
        inv.setItem(14, boarder);
        inv.setItem(15, boarder);
        inv.setItem(16, boarder);
        inv.setItem(17, boarder);

        inv.setItem(18, accept);
        inv.setItem(19, accept);
        inv.setItem(20, accept);
        inv.setItem(21, accept);
        inv.setItem(22, boarder);
        inv.setItem(23, deny);
        inv.setItem(24, deny);
        inv.setItem(25, deny);
        inv.setItem(26, deny);

        inv.setItem(27, accept);
        inv.setItem(28, accept);
        inv.setItem(29, accept);
        inv.setItem(30, accept);
        inv.setItem(31, boarder);
        inv.setItem(32, deny);
        inv.setItem(33, deny);
        inv.setItem(34, deny);
        inv.setItem(35, deny);

        inv.setItem(36, accept);
        inv.setItem(37, accept);
        inv.setItem(38, accept);
        inv.setItem(39, accept);
        inv.setItem(40, boarder);
        inv.setItem(41, deny);
        inv.setItem(42, deny);
        inv.setItem(43, deny);
        inv.setItem(44, deny);
        return inv;
    }
}
