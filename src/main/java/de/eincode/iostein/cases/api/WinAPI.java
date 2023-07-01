package de.eincode.iostein.cases.api;

import de.eincode.iostein.cases.CaseSystem;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;

public class WinAPI {

    public void addWin(Player player, String win) {
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement ps = CaseSystem.getInstance().getMySQL().connection.prepareStatement("INSERT INTO playerWin (UUID, win) VALUES (?, ?)");
            ps.setString(1, uuid);
            ps.setString(2, win);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
