package de.eincode.iostein.cases.api.paginated;

import de.eincode.iostein.cases.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

public abstract class PaginatedMenu
        extends Menu {
    protected int page = 0;
    protected int maxItemsPerPage = 27;
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    public void addMenuBorder() {
        for (int i = 27; i < 35; i++)
            inventory.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setDisplayName("§a").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        inventory.setItem(27, new ItemBuilder(Material.DARK_OAK_BUTTON, 1).setDisplayName("§8« §aLinks").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        inventory.setItem(31, new ItemBuilder(Material.ARROW, 1).setDisplayName("§cZurück").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        inventory.setItem(35, new ItemBuilder(Material.DARK_OAK_BUTTON, 1).setDisplayName("§8» §aRechts").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }

}
