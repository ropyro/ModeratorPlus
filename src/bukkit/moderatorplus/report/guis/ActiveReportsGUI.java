package bukkit.moderatorplus.report.guis;

import bukkit.moderatorplus.report.Report;
import com.sun.org.apache.regexp.internal.RE;
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

import java.util.ArrayList;
import java.util.List;

public class ActiveReportsGUI implements Listener {

    public static final ActiveReportsGUI INSTANCE;

    private Inventory inv;

    public ArrayList<Report> reports = new ArrayList<>();

    private ActiveReportsGUI() {
        inv = Bukkit.createInventory(null, 54, "Handle Active Reports,");
    }

    static {
        INSTANCE = new ActiveReportsGUI();
    }


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getClickedInventory().getTitle() == inv.getTitle())) return;

        Player p = (Player) event.getWhoClicked();

        ItemStack item = event.getCurrentItem();
        ItemMeta meta = item.getItemMeta();

        if (meta.getDisplayName().equalsIgnoreCase(ChatColor.RED + "Exit") && item.getType() == Material.BARRIER) {
            p.closeInventory();
        }

        if(item.getType() == Material.SKULL_ITEM){
            for(Report report : reports){
                if(report.getID().equals(getID(item.getItemMeta().getDisplayName()))){
                    p.openInventory(ReportHandleGUI.INSTANCE.getInv(report));
                }
            }
        }
        event.setCancelled(true);
    }

    public String getID(String name){
        return ChatColor.stripColor(name.replaceAll("-", "").replaceAll("\\+", "").replaceAll(" ", "").split("ID")[1]);
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void addReport(Report report){
        reports.add(report);
    }
    public void removeReport(Report report){
        reports.remove(report);
    }

    public Inventory getInv() {
        /**
         * General Items
         */
        ItemStack boarder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta boarderMeta = boarder.getItemMeta();
        boarderMeta.setDisplayName(ChatColor.RESET + "");
        boarder.setItemMeta(boarderMeta);

        ItemStack exit = new ItemStack(Material.BARRIER, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit");
        exit.setItemMeta(exitMeta);

        ItemStack infoBook = new ItemStack(Material.BOOK, 1);
        ItemMeta infoBookMeta = infoBook.getItemMeta();
        infoBookMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "+------" + ChatColor.RESET + ChatColor.WHITE + " Info " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----+");
        List<String> infoBookLore = new ArrayList<>();
        infoBookLore.add(ChatColor.GRAY + " Active Reports,");
        infoBookLore.add(ChatColor.WHITE + "  These are all the");
        infoBookLore.add(ChatColor.WHITE + "  Active reports.");
        infoBookLore.add(ChatColor.WHITE + "  Click them to deny");
        infoBookLore.add(ChatColor.WHITE + "  or accept the report.");
        infoBookLore.add(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "+----------------+");
        infoBookMeta.setLore(infoBookLore);
        infoBook.setItemMeta(infoBookMeta);


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
        inv.setItem(13, boarder);
        inv.setItem(14, boarder);
        inv.setItem(15, boarder);
        inv.setItem(16, boarder);
        inv.setItem(17, boarder);

        for(int i = 18; i < 54; i++){
            Report report = null;
            if(reports.size() - 1 >= i - 18){
                report = reports.get(i - 18);
            }

            if(report == null){
                inv.setItem(i, new ItemStack(Material.AIR));
                continue;
            }

            if(report.isHandled()){
                inv.setItem(i, new ItemStack(Material.AIR));
                reports.remove(report);
                continue;
            }

            ItemStack reportItem = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
            SkullMeta reportItemMeta = (SkullMeta) reportItem.getItemMeta();
            reportItemMeta.setOwner(report.getTarget().getName());
            reportItemMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH +  "+------" + ChatColor.RESET + ChatColor.WHITE + " ID " + report.getID() + " " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH +  "-----+");
            List<String> reportItemLore = new ArrayList<>();
            reportItemLore.add(ChatColor.GRAY + " Reported Player,");
            reportItemLore.add(ChatColor.WHITE + "  " + report.getTarget().getName());
            reportItemLore.add(ChatColor.GRAY + " Reporter,");
            reportItemLore.add(ChatColor.WHITE + "  " + report.getReporter().getName());
            reportItemLore.add(ChatColor.GRAY + " Reason,");
            reportItemLore.add(ChatColor.WHITE + "  " + report.getReason());
            reportItemLore.add(ChatColor.GRAY + " Timestamp,");
            reportItemLore.add(ChatColor.WHITE + "  " + report.getTime());
            reportItemLore.add(ChatColor.GRAY + " (" + ChatColor.WHITE + "Click to manage!" + ChatColor.GRAY +")");
            reportItemLore.add(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "+-----------------+");
            reportItemMeta.setLore(reportItemLore);
            reportItem.setItemMeta(reportItemMeta);

            inv.setItem(i, reportItem);
        }
        return inv;
    }
}
