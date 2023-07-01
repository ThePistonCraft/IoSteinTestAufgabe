package de.eincode.iostein.cases.utils.inventorys;

import de.eincode.iostein.cases.CaseSystem;
import de.eincode.iostein.cases.api.paginated.PaginatedMenu;
import de.eincode.iostein.cases.api.paginated.PlayerMenuUtility;
import de.eincode.iostein.cases.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class CaseInventory
        extends PaginatedMenu {

    private PlayerMenuUtility playerMenuUtility;

    public CaseInventory(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.playerMenuUtility = playerMenuUtility;
    }

    @Override
    public String getMenuName() {
        return "§cDeine Kisten §e#" + page;
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;

        if (event.getView().getTitle().equalsIgnoreCase(getMenuName())) {
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8« §aLinks")) {
                if (page == 0) {
                    player.closeInventory();
                    player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du bist bereits auf der ersten Seite.");
                    return;
                }
                page = page - 1;
                super.open();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück"))
                new MainInventory(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aRechts")) {
                if (!((index + 1) >= CaseSystem.getInstance().getCaseAPI().getCase(player, "apollo"))
                        || !((index + 1) >= CaseSystem.getInstance().getCaseAPI().getCase(player, "poseidon"))
                        || !((index + 1) >= CaseSystem.getInstance().getCaseAPI().getCase(player, "hero"))) {
                    page = page + 1;
                    super.open();
                    return;
                }
                player.closeInventory();
                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du bist bereits auf der letzten Seite.");
            }
        }
    }

    @Override
    public void setMenuItems() {
        addMenuBorder();
        final Player player = this.playerMenuUtility.getPlayer();
        if (CaseSystem.getInstance().getCaseAPI().getCase(player, "apollo") >= 1) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= CaseSystem.getInstance().getCaseAPI().getCase(player, "apollo")) break;
                if (CaseSystem.getInstance().getCaseAPI().getCase(player, "apollo") != 0) {
                    ItemStack itemStack = new ItemBuilder(Material.NETHER_STAR, 1).setDisplayName("§aApollo§f-§aKiste").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
                    inventory.setItem(i, itemStack);
                }
            }
        }
        if (CaseSystem.getInstance().getCaseAPI().getCase(player, "poseidon") >= 0) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= CaseSystem.getInstance().getCaseAPI().getCase(player, "poseidon")) break;
                if (CaseSystem.getInstance().getCaseAPI().getCase(player, "poseidon") != 0) {
                    ItemStack itemStack = new ItemBuilder(Material.NETHER_STAR, 1).setDisplayName("§5Poseidon§f-§5Kiste").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
                    inventory.addItem(itemStack);
                }
            }
        }
        if (CaseSystem.getInstance().getCaseAPI().getCase(player, "hero") >= 0) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= CaseSystem.getInstance().getCaseAPI().getCase(player, "hero")) break;
                if (CaseSystem.getInstance().getCaseAPI().getCase(player, "hero") != 0) {
                    ItemStack itemStack = new ItemBuilder(Material.NETHER_STAR, 1).setDisplayName("§6Hero§f-§6Kiste").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
                    inventory.addItem(itemStack);
                }
            }
        }
    }

}
