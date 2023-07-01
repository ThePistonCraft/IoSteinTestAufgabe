package de.eincode.iostein.cases.listener;

import de.eincode.iostein.cases.CaseSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionListener
        implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (!CaseSystem.getInstance().getCaseAPI().isPlayerInDatabase(player)) {
            CaseSystem.getInstance().getCaseAPI().createPlayer(player);
        }
        if (!CaseSystem.getInstance().getCoinAPI().isPlayerInDatabase(player)) {
            CaseSystem.getInstance().getCoinAPI().createPlayer(player);
        }
    }
}
