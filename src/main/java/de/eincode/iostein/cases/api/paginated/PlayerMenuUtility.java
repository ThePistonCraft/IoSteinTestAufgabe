package de.eincode.iostein.cases.api.paginated;

import lombok.Getter;
import org.bukkit.entity.Player;

public class PlayerMenuUtility {
    @Getter
    private Player player;
    public PlayerMenuUtility(Player player) {
        this.player = player;
    }
}
