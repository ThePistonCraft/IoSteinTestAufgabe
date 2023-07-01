package de.eincode.iostein.cases.utils.inventorys.cases.win.poseidon;

import de.eincode.iostein.cases.CaseSystem;
import de.eincode.iostein.cases.api.paginated.PaginatedMenu;
import de.eincode.iostein.cases.api.paginated.PlayerMenuUtility;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class EditPoseidonWins
        extends PaginatedMenu {

    private PlayerMenuUtility playerMenuUtility;

    public EditPoseidonWins(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.playerMenuUtility = playerMenuUtility;
    }

    @Override
    public String getMenuName() {
        return "§5Poseidon§f-§5Poseidon §8- §eLoot Setup";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

    }

    @Override
    public void setMenuItems() {
        File file = new File(CaseSystem.getInstance().getDataFolder(), "poseidon.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        for (int i = 0; i < 54; i++) {
            ItemStack itemStack = (ItemStack) yamlConfiguration.get("poseidon." + i + ".item");
            inventory.setItem(i, itemStack);
        }
    }
}
