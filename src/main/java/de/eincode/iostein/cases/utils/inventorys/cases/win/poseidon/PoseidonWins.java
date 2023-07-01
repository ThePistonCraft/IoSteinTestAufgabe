package de.eincode.iostein.cases.utils.inventorys.cases.win.poseidon;

import de.eincode.iostein.cases.CaseSystem;
import de.eincode.iostein.cases.api.paginated.PaginatedMenu;
import de.eincode.iostein.cases.api.paginated.PlayerMenuUtility;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class PoseidonWins
        extends PaginatedMenu {

    private PlayerMenuUtility playerMenuUtility;

    public PoseidonWins(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.playerMenuUtility = playerMenuUtility;
    }

    @Override
    public String getMenuName() {
        return "§5Poseidon§7-§5Kiste §8- §8LOOT";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;

        if (event.getView().getTitle().equalsIgnoreCase(getMenuName()))
            event.setCancelled(true);
    }

    @Override
    public void setMenuItems() {
        File file = new File(CaseSystem.getInstance().getDataFolder(), "poseidon.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        for (int i = 0; i < 54; i++) {
            ItemStack itemStack = (ItemStack) yamlConfiguration.get("poseidon." + i);
            inventory.setItem(i, itemStack);
        }
    }
}

