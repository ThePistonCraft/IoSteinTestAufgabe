package de.eincode.iostein.cases.utils;

import de.eincode.iostein.cases.CaseSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class CaseManager {

    public void saveCaseInventory(Inventory inventory, String caseName) {
        File file = new File(CaseSystem.getInstance().getDataFolder(), caseName + ".yml");

        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        for (int i = 0; i < 54; i++) {
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack != null) {
                if (yamlConfiguration.contains(caseName + "." + i)) {
                    yamlConfiguration.set(caseName + "." + i + ".item", itemStack);
                } else {
                    yamlConfiguration.createSection(caseName + "." + i);
                    yamlConfiguration.set(caseName + "." + i + ".item", itemStack);
                    if (!yamlConfiguration.contains(caseName + "." + i + ".settings")) {
                        yamlConfiguration.createSection(caseName + "." + i + ".settings");
                    }
                    yamlConfiguration.set(caseName + "." + i + ".settings.commandEnable", false);
                    yamlConfiguration.set(caseName + "." + i + ".settings.command", "NONE");
                }
            } else {
                if (existCaseFile(caseName) && yamlConfiguration.contains(caseName + "." + i)) {
                    yamlConfiguration.set(caseName + "." + i, null);
                }
            }
        }

        try {
            yamlConfiguration.save(file);
        } catch (IOException ignored) {
        }
    }

    public void saveCaseInventoryCommand(Inventory inventory, String caseName, boolean value, String command) {
        File file = new File(CaseSystem.getInstance().getDataFolder(), caseName + ".yml");

        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        for (int i = 0; i < 54; i++) {
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack != null) {
                if (yamlConfiguration.contains(caseName + "." + i)) {
                    yamlConfiguration.set(caseName + "." + i + ".item", itemStack);
                } else {
                    yamlConfiguration.createSection(caseName + "." + i);
                    yamlConfiguration.set(caseName + "." + i + ".item", itemStack);
                    if (!yamlConfiguration.contains(caseName + "." + i + ".settings")) {
                        yamlConfiguration.createSection(caseName + "." + i + ".settings");
                    }
                    yamlConfiguration.set(caseName + "." + i + ".settings.commandEnable", value);
                    yamlConfiguration.set(caseName + "." + i + ".settings.command", command);
                }
            } else {
                if (existCaseFile(caseName) && yamlConfiguration.contains(caseName + "." + i)) {
                    yamlConfiguration.set(caseName + "." + i, null);
                }
            }
        }

        try {
            yamlConfiguration.save(file);
        } catch (IOException ignored) {
        }
    }

    public void executeCommandOrGiveItem(Player player, ItemStack itemStack, String caseName) {
        File file = new File(CaseSystem.getInstance().getDataFolder(), caseName + ".yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection caseSection = yamlConfiguration.getConfigurationSection(caseName);
        if (caseSection != null) {
            String itemKey = getItemKey(itemStack, caseSection);
            if (itemKey != null) {
                boolean commandEnable = yamlConfiguration.getBoolean(caseName + "." + itemKey + ".settings.commandEnable");
                String command = yamlConfiguration.getString(caseName + "." + itemKey + ".settings.command");

                if (commandEnable && command != null && !command.equals("NONE")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, command.replace("%player%", player.getName()));
                } else {
                    player.getInventory().addItem(itemStack);
                }
            }
        }
    }

    private String getItemKey(ItemStack itemStack, ConfigurationSection caseSection) {
        for (String key : caseSection.getKeys(false)) {
            if (caseSection.isConfigurationSection(key)) {
                ConfigurationSection itemSection = caseSection.getConfigurationSection(key);
                ItemStack storedItemStack = itemSection.getItemStack("item");
                if (storedItemStack != null && storedItemStack.isSimilar(itemStack)) {
                    return key;
                }
            }
        }
        return null;
    }

    public boolean existCaseFile(String caseName) {
        File file = new File(CaseSystem.getInstance().getDataFolder(), caseName + ".yml");
        return file.exists();
    }
}
