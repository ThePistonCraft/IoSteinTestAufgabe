package de.eincode.iostein.cases;

import de.eincode.iostein.cases.api.CaseAPI;
import de.eincode.iostein.cases.api.CoinAPI;
import de.eincode.iostein.cases.api.WinAPI;
import de.eincode.iostein.cases.api.paginated.PlayerMenuUtility;
import de.eincode.iostein.cases.command.CaseCommand;
import de.eincode.iostein.cases.command.CoinCommand;
import de.eincode.iostein.cases.listener.CaseChestMechanic;
import de.eincode.iostein.cases.listener.ConnectionListener;
import de.eincode.iostein.cases.listener.MenuListener;
import de.eincode.iostein.cases.utils.CaseManager;
import de.eincode.iostein.cases.utils.MySQL;
import de.eincode.iostein.cases.utils.YamlConfigurationLoader;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class CaseSystem
        extends JavaPlugin {
    @Getter
    public static CaseSystem instance;
    @Getter
    public String prefix = "§8[§dCase§8] §7";

    @Getter
    public MySQL mySQL;

    @Getter
    public CaseAPI caseAPI;
    @Getter
    public CaseManager caseManager;
    @Getter
    public WinAPI winAPI;
    @Getter
    public CoinAPI coinAPI;

    @Getter
    private final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        this.init();
    }

    private void init() {
        instance = this;
        YamlConfigurationLoader.loadConfiguration(this, "config.yml");

        this.mySQL = new MySQL();
        this.caseAPI = new CaseAPI();
        this.winAPI = new WinAPI();
        this.coinAPI = new CoinAPI();
        this.caseManager = new CaseManager();

        mySQL.connect();

        mySQL.createTable("playerCoins", "UUID VARCHAR(255), coins BIGINT(255)");
        mySQL.createTable("playerCases", "UUID VARCHAR(255), apollo BIGINT(255), " +
                "poseidon BIGINT(255), hero BIGINT(255)");
        mySQL.createTable("playerWin", "UUID VARCHAR(255), win VARCHAR(255)");

        getCommand("case").setExecutor(new CaseCommand());
        getCommand("coins").setExecutor(new CoinCommand());

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new CaseChestMechanic(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
    }
    public PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtility;
        if (!(getPlayerMenuUtilityMap().containsKey(player))) {

            playerMenuUtility = new PlayerMenuUtility(player);
            getPlayerMenuUtilityMap().put(player, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return getPlayerMenuUtilityMap().get(player);
        }
    }

}
