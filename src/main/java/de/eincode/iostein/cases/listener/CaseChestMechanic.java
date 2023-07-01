package de.eincode.iostein.cases.listener;

import de.eincode.iostein.cases.CaseSystem;
import de.eincode.iostein.cases.utils.inventorys.MainInventory;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CaseChestMechanic
        implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();

            if (clickedBlock.getType() == Material.CHEST) {
                Block beaconBlock = clickedBlock.getRelative(BlockFace.DOWN);

                if (beaconBlock.getType() == Material.BEACON) {
                    event.setCancelled(true);
                    new MainInventory(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
                }
            }
        }
    }
}
