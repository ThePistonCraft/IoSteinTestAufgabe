package de.eincode.iostein.cases.api;

import de.eincode.iostein.cases.CaseSystem;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinAPI {
    public void addPlayerCoins(Player player, int amount) {
        try {
            PreparedStatement statement = CaseSystem.getInstance().getMySQL().connection.prepareStatement("UPDATE playercoins SET coins=? WHERE UUID=?");
            statement.setString(2, player.getUniqueId().toString());
            statement.setInt(1, getPlayerCoins(player) + amount);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setPlayerCoins(Player player, int amount) {
        try {
            PreparedStatement statement = CaseSystem.getInstance().getMySQL().connection.prepareStatement("UPDATE playercoins SET coins=? WHERE UUID=?");
            statement.setString(2, player.getUniqueId().toString());
            statement.setInt(1, amount);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void removePlayerCoins(Player player, int amount) {
        try {
            PreparedStatement statement = CaseSystem.getInstance().getMySQL().connection.prepareStatement("UPDATE playercoins SET coins=? WHERE UUID=?");
            statement.setString(2, player.getUniqueId().toString());
            if (getPlayerCoins(player) <= 0) {
                return;
            }
            statement.setInt(1, getPlayerCoins(player) - amount);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public int getPlayerCoins(Player player) {
        try {
            PreparedStatement statement = CaseSystem.getInstance().getMySQL().connection.prepareStatement("SELECT coins FROM playercoins WHERE UUID= ?");
            statement.setString(1, player.getUniqueId().toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("coins");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    public boolean isPlayerInDatabase(Player player) {
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement statement = CaseSystem.getInstance().getMySQL().connection.prepareStatement("SELECT * FROM playercoins WHERE UUID= ?");
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
            PreparedStatement ps = CaseSystem.getInstance().getMySQL().connection.prepareStatement("INSERT INTO playercoins (UUID, coins) VALUES (?, ?)");
            ps.setString(1, uuid);
            ps.setInt(2, 0);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
