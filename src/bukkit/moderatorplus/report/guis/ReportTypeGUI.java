package bukkit.moderatorplus.report.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

    @EventHandler
    public void onClick(InventoryClickEvent event){

        if(!(event.getClickedInventory() == this.inv)) return;

        ItemStack item = event.getCurrentItem();
        ItemMeta meta = item.getItemMeta();

        if(meta.getDisplayName().equalsIgnoreCase(ChatColor.RESET + "") && item.getType() == Material.STAINED_GLASS_PANE) event.setCancelled(true);
    }


    public Inventory getInv(Player reporter, Player target) {
        this.reporter = reporter;
        this.target = target;

        ItemStack boarder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta boarderMeta = boarder.getItemMeta();
        boarderMeta.setDisplayName(ChatColor.RESET + "");
        boarder.setItemMeta(boarderMeta);

        inv.setItem(0, boarder);
        inv.setItem(1, boarder);
        inv.setItem(2, boarder);
        inv.setItem(3, boarder);
        inv.setItem(4, boarder);
        inv.setItem(5, boarder);
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

        inv.setItem(18, boarder);
        inv.setItem(19, boarder);
        inv.setItem(20, boarder);
        inv.setItem(21, boarder);
        inv.setItem(22, boarder);
        inv.setItem(23, boarder);
        inv.setItem(24, boarder);
        inv.setItem(25, boarder);
        inv.setItem(26, boarder);
        return inv;
    }
}
