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

import java.util.ArrayList;
import java.util.List;

public class ReportTypeGUI implements Listener {

    public static final ReportTypeGUI INSTANCE;

    private Inventory inv;

    private Player reporter;
    private Player target;

    private ReportTypeGUI(){
        inv = Bukkit.createInventory(null, 27, "Select reason for the report,");
    }

    static {
        INSTANCE = new ReportTypeGUI();
    }

    public String returntrue(){
        return null;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(!(event.getClickedInventory().getTitle() == inv.getTitle())) return;

        Player p = (Player)event.getWhoClicked();

        ItemStack item = event.getCurrentItem();
        ItemMeta meta = item.getItemMeta();

        if(meta.getDisplayName().equalsIgnoreCase(ChatColor.RED + "Cancel Report") && item.getType() == Material.BARRIER){
            ModeratorPlus.getInstance().sendMessage(p, "Cancelled report on " + ChatColor.RED + target.getName() + ChatColor.GRAY + "!");
            p.closeInventory();
        }else
        if(meta.getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "> " + ChatColor.DARK_RED + "Kill Aura" + ChatColor.DARK_GRAY + " <") && item.getType() == Material.DIAMOND_SWORD){
            ActiveReportsGUI.INSTANCE.addReport(new Report(reporter, target, "Kill Aura"));
            p.closeInventory();
        }else
        if(meta.getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "> " + ChatColor.GRAY + "Flight" + ChatColor.DARK_GRAY + " <") && item.getType() == Material.FEATHER){
            ActiveReportsGUI.INSTANCE.addReport(new Report(reporter, target, "Flight"));
            p.closeInventory();
        }else
        if(meta.getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "> " + ChatColor.AQUA + "Speed" + ChatColor.DARK_GRAY + " <") && item.getType() == Material.POTION){
            ActiveReportsGUI.INSTANCE.addReport(new Report(reporter, target, "Speed"));
            p.closeInventory();
        }else
        if(meta.getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "> " + ChatColor.GOLD + "Other" + ChatColor.DARK_GRAY + " <") && item.getType() == Material.PAPER){
            //TODO: Add way to change this ;-;
            ActiveReportsGUI.INSTANCE.addReport(new Report(reporter, target, "Other"));
            p.closeInventory();
        }else
        if(meta.getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "> " + ChatColor.BLUE + "Jesus" + ChatColor.DARK_GRAY + " <") && item.getType() == Material.WATER_BUCKET){
            ActiveReportsGUI.INSTANCE.addReport(new Report(reporter, target, "Jesus"));
            p.closeInventory();
        }else
        if(meta.getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "> " + ChatColor.RED + "X-Ray" + ChatColor.DARK_GRAY + " <") && item.getType() == Material.DIAMOND_ORE){
            ActiveReportsGUI.INSTANCE.addReport(new Report(reporter, target, "X-Ray"));
            p.closeInventory();
        }else
        if(meta.getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "> " + ChatColor.YELLOW + "Velocity" + ChatColor.DARK_GRAY + " <") && item.getType() == Material.TNT){
            ActiveReportsGUI.INSTANCE.addReport(new Report(reporter, target, "Velocity"));
            p.closeInventory();
        }
        event.setCancelled(true);
    }

    public Inventory getInv(Player reporter, Player target) {
        this.reporter = reporter;
        this.target = target;

        /**
         * General Items
         */
        ItemStack boarder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta boarderMeta = boarder.getItemMeta();
        boarderMeta.setDisplayName(ChatColor.RESET + "");
        boarder.setItemMeta(boarderMeta);

        ItemStack cancel = new ItemStack(Material.BARRIER, 1);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName(ChatColor.RED + "Cancel Report");
        cancel.setItemMeta(cancelMeta);

        ItemStack reporterSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta reporterSkullItemMeta = (SkullMeta) reporterSkull.getItemMeta();
        reporterSkullItemMeta.setOwner(reporter.getName());
        reporterSkullItemMeta.setDisplayName(ChatColor.GREEN + "Reporter");
        reporterSkull.setItemMeta(reporterSkullItemMeta);

        ItemStack targetSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta targetSkullItemMeta = (SkullMeta) targetSkull.getItemMeta();
        targetSkullItemMeta.setOwner(target.getName());
        targetSkullItemMeta.setDisplayName(ChatColor.RED + "Target");
        targetSkull.setItemMeta(targetSkullItemMeta);

        ItemStack infoBook = new ItemStack(Material.BOOK, 1);
        ItemMeta infoBookMeta = infoBook.getItemMeta();
        infoBookMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH +  "+------" + ChatColor.RESET + ChatColor.WHITE + " Info " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH +  "-----+");
        List<String> infoBookLore = new ArrayList<>();
        infoBookLore.add(ChatColor.GRAY + " Reports,");
        infoBookLore.add(ChatColor.WHITE + "  After reporting a");
        infoBookLore.add(ChatColor.WHITE + "  player, staff will");
        infoBookLore.add(ChatColor.WHITE + "  be notified. Once");
        infoBookLore.add(ChatColor.WHITE + "  reviewed, you will");
        infoBookLore.add(ChatColor.WHITE + "  be notified with");
        infoBookLore.add(ChatColor.WHITE + "  the verdict of");
        infoBookLore.add(ChatColor.WHITE + "  your report!");
        infoBookLore.add(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "+----------------+");
        infoBookMeta.setLore(infoBookLore);
        infoBook.setItemMeta(infoBookMeta);

        /**
         * Reasons
         */

        ItemStack killaura = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta killauraMeta = killaura.getItemMeta();
        killauraMeta.setDisplayName(ChatColor.DARK_GRAY + "> " + ChatColor.DARK_RED + "Kill Aura" + ChatColor.DARK_GRAY + " <");
        killaura.setItemMeta(killauraMeta);

        ItemStack flight = new ItemStack(Material.FEATHER, 1);
        ItemMeta flightMeta = flight.getItemMeta();
        flightMeta.setDisplayName(ChatColor.DARK_GRAY + "> " + ChatColor.GRAY + "Flight" + ChatColor.DARK_GRAY + " <");
        flight.setItemMeta(flightMeta);

        ItemStack speed = new ItemStack(Material.POTION, 1, (short)8194);
        ItemMeta speedMeta = speed.getItemMeta();
        speedMeta.setDisplayName(ChatColor.DARK_GRAY + "> " + ChatColor.AQUA + "Speed" + ChatColor.DARK_GRAY + " <");
        speed.setItemMeta(speedMeta);

        ItemStack other = new ItemStack(Material.PAPER, 1);
        ItemMeta otherMeta = other.getItemMeta();
        otherMeta.setDisplayName(ChatColor.DARK_GRAY + "> " + ChatColor.GOLD + "Other" + ChatColor.DARK_GRAY + " <");
        other.setItemMeta(otherMeta);

        ItemStack jesus = new ItemStack(Material.WATER_BUCKET, 1);
        ItemMeta jesusMeta = jesus.getItemMeta();
        jesusMeta.setDisplayName(ChatColor.DARK_GRAY + "> " + ChatColor.BLUE + "Jesus" + ChatColor.DARK_GRAY + " <");
        jesus.setItemMeta(jesusMeta);

        ItemStack xray = new ItemStack(Material.DIAMOND_ORE, 1);
        ItemMeta xrayMeta = xray.getItemMeta();
        xrayMeta.setDisplayName(ChatColor.DARK_GRAY + "> " + ChatColor.RED + "X-Ray" + ChatColor.DARK_GRAY + " <");
        xray.setItemMeta(xrayMeta);

        ItemStack velocity = new ItemStack(Material.TNT, 1);
        ItemMeta velocityMeta = velocity.getItemMeta();
        velocityMeta.setDisplayName(ChatColor.DARK_GRAY + "> " + ChatColor.YELLOW + "Velocity" + ChatColor.DARK_GRAY + " <");
        velocity.setItemMeta(velocityMeta);

        inv.setItem(0, boarder);
        inv.setItem(1, boarder);
        inv.setItem(2, reporterSkull);
        inv.setItem(3, boarder);
        inv.setItem(4, infoBook);
        inv.setItem(5, boarder);
        inv.setItem(6, targetSkull);
        inv.setItem(7, boarder);
        inv.setItem(8, boarder);

        inv.setItem(9, boarder);
        inv.setItem(10, killaura);
        inv.setItem(11, flight);
        inv.setItem(12, speed);
        inv.setItem(13, other);
        inv.setItem(14, jesus);
        inv.setItem(15, xray);
        inv.setItem(16, velocity);
        inv.setItem(17, boarder);

        inv.setItem(18, boarder);
        inv.setItem(19, boarder);
        inv.setItem(20, boarder);
        inv.setItem(21, boarder);
        inv.setItem(22, cancel);
        inv.setItem(23, boarder);
        inv.setItem(24, boarder);
        inv.setItem(25, boarder);
        inv.setItem(26, boarder);
        return inv;
    }
}
