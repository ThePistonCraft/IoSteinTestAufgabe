package de.eincode.iostein.cases.command;

import de.eincode.iostein.cases.CaseSystem;
import de.eincode.iostein.cases.utils.inventorys.cases.win.apollo.EditApolloWins;
import de.eincode.iostein.cases.utils.inventorys.cases.win.poseidon.EditPoseidonWins;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class CaseCommand
        implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        final Player player = (Player) sender;

        if (!player.hasPermission("iostein.case")) {
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Dazu hast du keine Rechte.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <add> <command:true/false> {true:<command>}");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <inv>");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Placeholder: §e%player% wird ersetzt durch playerName");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "§cBeim Inventar, nur ITEMS entfernen oder ITEMS ohne CMD hinzufügen, " +
                    "dass hinzufügen per Command ohne CMD ist möglich.");
            return true;
        }
        if (args.length > 2) {
            if (player.getItemInHand().getType() == Material.AIR) {
                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du musst ein ITEM in der Hand halten!");
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("apollo")) {
            Inventory inventory = Bukkit.createInventory(null, 54, "§cLOOT");
            File file = new File(CaseSystem.getInstance().getDataFolder(), "apollo.yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

            if (args[1].equalsIgnoreCase("add")) {
                if (args[2].equalsIgnoreCase("true")) {
                    if (args.length >= 4) {
                        String cmd = "";

                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 3; i < args.length; i++) {
                            stringBuilder.append(args[i]).append(' ');
                        }
                        cmd = stringBuilder.toString().trim();
                        for (int number = 0; number < 54; number++) {
                            ItemStack itemStack = yamlConfiguration.getItemStack("apollo." + number + ".item");
                            inventory.setItem(number, itemStack);
                        }

                        if (inventory.firstEmpty() != -1) {
                            inventory.addItem(player.getItemInHand());
                            CaseSystem.getInstance().getCaseManager().saveCaseInventoryCommand(inventory, "apollo", true, cmd);

                            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Erfolgreich Item zu §eAPOLLO §7hinzugefügt!");
                            return true;
                        }
                        player.sendMessage(CaseSystem.getInstance().getPrefix() + "Es sind die max. plätze des ApolloCases voll!");
                        return true;
                    }
                }
                if (args[2].equalsIgnoreCase("false")) {
                    for (int number = 0; number < 54; number++) {
                        ItemStack itemStack = yamlConfiguration.getItemStack("apollo." + number + ".item");
                        inventory.setItem(number, itemStack);
                    }

                    if (inventory.firstEmpty() != -1) {
                        inventory.addItem(player.getItemInHand());
                        CaseSystem.getInstance().getCaseManager().saveCaseInventoryCommand(inventory, "apollo", false, "NONE");

                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        player.sendMessage(CaseSystem.getInstance().getPrefix() + "Erfolgreich Item zu §eAPOLLO §7hinzugefügt!");
                        return true;
                    }
                    player.sendMessage(CaseSystem.getInstance().getPrefix() + "Es sind die max. plätze des ApolloCases voll!");
                    return true;
                }
                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <add> <command:true/false> {true:<command>}");
                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <inv>");
                player.sendMessage(CaseSystem.getInstance().getPrefix() + "§cBeim Inventar, nur ITEMS entfernen oder ITEMS ohne CMD hinzufügen, " +
                        "dass hinzufügen per Command ohne CMD ist möglich.");
                return true;
            }
            if (args[1].equalsIgnoreCase("inv")) {
                new EditApolloWins(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
                return true;
            }
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <add> <command:true/false> {true:<command>}");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <inv>");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "§cBeim Inventar, nur ITEMS entfernen oder ITEMS ohne CMD hinzufügen, " +
                    "dass hinzufügen per Command ohne CMD ist möglich.");
            return true;
        }
        if (args[0].equalsIgnoreCase("poseidon")) {
            Inventory inventory = Bukkit.createInventory(null, 54, "§cLOOT");
            File file = new File(CaseSystem.getInstance().getDataFolder(), "poseidon.yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

            if (args[1].equalsIgnoreCase("true")) {
                if (args.length >= 4) {
                    String cmd = "";

                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 3; i < args.length; i++) {
                        stringBuilder.append(args[i]).append(' ');
                    }
                    cmd = stringBuilder.toString().trim();
                    for (int number = 0; number < 54; number++) {
                        ItemStack itemStack = yamlConfiguration.getItemStack("poseidon." + number + ".item");
                        inventory.setItem(number, itemStack);
                    }

                    if (inventory.firstEmpty() != -1) {
                        inventory.addItem(player.getItemInHand());
                        CaseSystem.getInstance().getCaseManager().saveCaseInventoryCommand(inventory, "poseidon", true, cmd);

                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        player.sendMessage(CaseSystem.getInstance().getPrefix() + "Erfolgreich Item zu §eAPOSEIDON §7hinzugefügt!");
                        return true;
                    }
                    player.sendMessage(CaseSystem.getInstance().getPrefix() + "Es sind die max. plätze des PoseidonCases voll!");
                    return true;
                }
            }
            if (args[1].equalsIgnoreCase("false")) {
                for (int number = 0; number < 54; number++) {
                    ItemStack itemStack = yamlConfiguration.getItemStack("poseidon." + number + ".item");
                    inventory.setItem(number, itemStack);
                }

                if (inventory.firstEmpty() != -1) {
                    inventory.addItem(player.getItemInHand());
                    CaseSystem.getInstance().getCaseManager().saveCaseInventoryCommand(inventory, "poseidon", false, "NONE");

                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                    player.sendMessage(CaseSystem.getInstance().getPrefix() + "Erfolgreich Item zu §ePOSEIDON §7hinzugefügt!");
                    return true;
                }
                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Es sind die max. plätze des ApolloCases voll!");
                return true;
            }
            if (args[1].equalsIgnoreCase("inv")) {
                new EditPoseidonWins(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
                return true;
            }
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <add> <command:true/false> {true:<command>}");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <inv>");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "§cBeim Inventar, nur ITEMS entfernen oder ITEMS ohne CMD hinzufügen, " +
                    "dass hinzufügen per Command ohne CMD ist möglich.");
            return true;
        }
        if (args[0].equalsIgnoreCase("hero")) {
            Inventory inventory = Bukkit.createInventory(null, 54, "§cLOOT");
            File file = new File(CaseSystem.getInstance().getDataFolder(), "hero.yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

            if (args[1].equalsIgnoreCase("true")) {
                if (args.length >= 4) {
                    String cmd = "";

                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 3; i < args.length; i++) {
                        stringBuilder.append(args[i]).append(' ');
                    }
                    cmd = stringBuilder.toString().trim();
                    for (int number = 0; number < 54; number++) {
                        ItemStack itemStack = yamlConfiguration.getItemStack("hero." + number + ".item");
                        inventory.setItem(number, itemStack);
                    }

                    if (inventory.firstEmpty() != -1) {
                        inventory.addItem(player.getItemInHand());
                        CaseSystem.getInstance().getCaseManager().saveCaseInventoryCommand(inventory, "hero", true, cmd);

                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        player.sendMessage(CaseSystem.getInstance().getPrefix() + "Erfolgreich Item zu §eHero §7hinzugefügt!");
                        return true;
                    }
                    player.sendMessage(CaseSystem.getInstance().getPrefix() + "Es sind die max. plätze des HeroCases voll!");
                    return true;
                }
            }
            if (args[1].equalsIgnoreCase("false")) {
                for (int number = 0; number < 54; number++) {
                    ItemStack itemStack = yamlConfiguration.getItemStack("hero." + number + ".item");
                    inventory.setItem(number, itemStack);
                }

                if (inventory.firstEmpty() != -1) {
                    inventory.addItem(player.getItemInHand());
                    CaseSystem.getInstance().getCaseManager().saveCaseInventoryCommand(inventory, "hero", false, "NONE");

                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                    player.sendMessage(CaseSystem.getInstance().getPrefix() + "Erfolgreich Item zu §eHERO §7hinzugefügt!");
                    return true;
                }
                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Es sind die max. plätze des HeroCases voll!");
                return true;
            }
            if (args[1].equalsIgnoreCase("inv")) {
                new EditPoseidonWins(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
                return true;
            }
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <add> <command:true/false> {true:<command>}");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <inv>");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "§cBeim Inventar, nur ITEMS entfernen oder ITEMS ohne CMD hinzufügen, " +
                    "dass hinzufügen per Command ohne CMD ist möglich.");
            return true;
        }

        player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <add> <command:true/false> {true:<command>}");
        player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/case <apollo, poseidon, hero> <inv>");
        player.sendMessage(CaseSystem.getInstance().getPrefix() + "§cBeim Inventar, nur ITEMS entfernen oder ITEMS ohne CMD hinzufügen, " +
                "dass hinzufügen per Command ohne CMD ist möglich.");
        return false;
    }
}
