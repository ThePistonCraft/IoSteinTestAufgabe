package de.eincode.iostein.cases.api;

import de.eincode.iostein.cases.CaseSystem;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CaseAPI {
    public void addCase(Player player, String type, int amount) {
        try {
            PreparedStatement statement = CaseSystem.getInstance().getMySQL().connection.prepareStatement("UPDATE playercases SET " + type + "=? WHERE UUID=?");
            statement.setString(2, player.getUniqueId().toString());
            statement.setInt(1, getCase(player, type) + amount);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setCase(Player player, String type, int amount) {
        try {
            PreparedStatement statement = CaseSystem.getInstance().getMySQL().connection.prepareStatement("UPDATE playercases SET " + type + "=? WHERE UUID=?");
            statement.setString(2, player.getUniqueId().toString());
            statement.setInt(1, amount);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void removeCase(Player player, String type, int amount) {
        try {
            PreparedStatement statement = CaseSystem.getInstance().getMySQL().connection.prepareStatement("UPDATE playercases SET " + type + "=? WHERE UUID=?");
            statement.setString(2, player.getUniqueId().toString());
            if (getCase(player, type) <= 0) {
                return;
            }
            statement.setInt(1, getCase(player, type) - amount);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public int getCase(Player player, String type) {
        try {
            PreparedStatement statement = CaseSystem.getInstance().getMySQL().connection.prepareStatement("SELECT " + type + " FROM playercases WHERE UUID= ?");
            statement.setString(1, player.getUniqueId().toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(type);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public boolean isPlayerInDatabase(Player player) {
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement statement = CaseSystem.getInstance().getMySQL().connection.prepareStatement("SELECT * FROM playercases WHERE UUID= ?");
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            boolean user = resultSet.next();
            resultSet.close();
            statement.close();
            return user;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void createPlayer(Player player) {
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement ps = CaseSystem.getInstance().getMySQL().connection.prepareStatement("INSERT INTO playercases (UUID, apollo, poseidon, hero) VALUES (?, ?, ?, ?)");
            ps.setString(1, uuid);
            ps.setInt(2, 0);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
