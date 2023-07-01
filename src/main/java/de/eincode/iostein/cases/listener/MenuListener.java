package de.eincode.iostein.cases.listener;

import de.eincode.iostein.cases.CaseSystem;
import de.eincode.iostein.cases.api.paginated.Menu;
import de.eincode.iostein.cases.utils.inventorys.cases.ApolloCase;
import de.eincode.iostein.cases.utils.inventorys.cases.HeroCase;
import de.eincode.iostein.cases.utils.inventorys.cases.PoseidonCase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener
        implements Listener {

    @EventHandler
    public void menuClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Menu) {
            if (event.getCurrentItem() == null) {
                return;
            }
            Menu menu = (Menu) holder;
            menu.handleMenu(event);
        }
    }

    @EventHandler
    public void playerCloseInventory(InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();

        if (event.getView().getTitle().equalsIgnoreCase("§aApollo§f-§aKiste §8- §eLoot Setup")) {
            CaseSystem.getInstance().getCaseManager().saveCaseInventory(event.getInventory(), "apollo");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast den LOOT von der §aApollo§f-§aKiste §7geupdatet!");
        }
        if (event.getView().getTitle().equalsIgnoreCase("§5Poseidon§f-§5Kiste §8- §eLoot Setup")) {
            CaseSystem.getInstance().getCaseManager().saveCaseInventory(event.getInventory(), "poseidon");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast den LOOT von der §5Poseidon§f-§5Kiste §7geupdatet!");
        }
        if (event.getView().getTitle().equalsIgnoreCase("§6Hero§f-§a6iste §8- §eLoot Setup")) {
            CaseSystem.getInstance().getCaseManager().saveCaseInventory(event.getInventory(), "hero");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast den LOOT von der §6Hero§f-§6Hero §7geupdatet!");
        }
    }
}
