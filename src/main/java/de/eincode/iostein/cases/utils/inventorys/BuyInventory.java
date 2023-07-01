package de.eincode.iostein.cases.utils.inventorys;

import de.eincode.iostein.cases.CaseSystem;
import de.eincode.iostein.cases.api.paginated.PaginatedMenu;
import de.eincode.iostein.cases.api.paginated.PlayerMenuUtility;
import de.eincode.iostein.cases.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;

import java.util.List;

public class BuyInventory
        extends PaginatedMenu {

    private PlayerMenuUtility playerMenuUtility;

    public BuyInventory(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.playerMenuUtility = playerMenuUtility;
    }

    @Override
    public String getMenuName() {
        return "§6§lKisten kaufen";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();


        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;

        if (event.getView().getTitle().equalsIgnoreCase(getMenuName())) {
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8« §aZurück")) {
                new MainInventory(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
            }

            final int playerCoins = CaseSystem.getInstance().getCoinAPI().getPlayerCoins(player);

            // APOLLO
            if (event.getCurrentItem().getType() == Material.CHEST) {
                if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore()) {
                    List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                    for (String line : lore) {
                        if (line.contains("1.000")) {
                            if (!(playerCoins >= 1000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 1000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "apollo", 1);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e1 §eApollo §7Case gekauft!");
                        }
                        if (line.contains("4.500")) {
                            if (!(playerCoins >= 4500)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 4500);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "apollo", 5);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e5 §eApollo §7Cases gekauft!");
                            ;
                        }
                        if (line.contains("25.000")) {
                            if (!(playerCoins >= 25000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 25000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "apollo", 36);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e36 §eApollo §7Cases gekauft!");
                        }
                        if (line.contains("50.000")) {
                            if (!(playerCoins >= 50000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 50000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "apollo", 80);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e80 §eApollo §7Cases gekauft!");
                        }
                    }
                }
            }

            // POSEIDON
            if (event.getCurrentItem().getType() == Material.ENDER_CHEST) {
                if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore()) {
                    List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                    for (String line : lore) {
                        if (line.contains("5.000")) {
                            if (!(playerCoins >= 5000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 5000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "poseidon", 1);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e1 §ePoseidon §7Case gekauft!");
                        }
                        if (line.contains("25.000")) {
                            if (!(playerCoins >= 25000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 25000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "poseidon", 6);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e6 §ePoseidon §7Cases gekauft!");
                            ;
                        }
                        if (line.contains("50.000")) {
                            if (!(playerCoins >= 50000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 50000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "poseidon", 15);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e15 §ePoseidon §7Cases gekauft!");
                        }
                        if (line.contains("100.000")) {
                            if (!(playerCoins >= 100000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 100000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "poseidon", 32);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e32 §ePoseidon §7Cases gekauft!");
                        }
                    }
                }
            }

            // HERO
            if (event.getCurrentItem().getType() == Material.YELLOW_SHULKER_BOX) {
                if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore()) {
                    List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                    for (String line : lore) {
                        if (line.contains("10.000")) {
                            if (!(playerCoins >= 10000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 10000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "hero", 1);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e1 §eHero §7Case gekauft!");
                        }
                        if (line.contains("30.000")) {
                            if (!(playerCoins >= 30000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 30000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "hero", 3);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e3 §eHero §7Cases gekauft!");
                            ;
                        }
                        if (line.contains("50.000")) {
                            if (!(playerCoins >= 50000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 50000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "hero", 6);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e6 §eHero §7Cases gekauft!");
                        }
                        if (line.contains("100.000")) {
                            if (!(playerCoins >= 100000)) {
                                player.closeInventory();
                                player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast nicht genug Credits!");
                                return;
                            }
                            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(player, 100000);
                            CaseSystem.getInstance().getCaseAPI().addCase(player, "hero", 15);
                            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast dir §e15 §eHero §7Cases gekauft!");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setMenuItems() {
        final Player player = this.playerMenuUtility.getPlayer();
        final int playerCoins = CaseSystem.getInstance().getCoinAPI().getPlayerCoins(player);

        for (int i = 36; i < 45; i++)
            inventory.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setDisplayName("§a").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());

        inventory.setItem(36, new ItemBuilder(Material.DARK_OAK_BUTTON, 1).setDisplayName("§8« §aZurück").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());

        // APOLLO
        if (!(playerCoins >= 1000)) {
            inventory.setItem(1, new ItemBuilder(Material.CHEST, 1)
                    .setDisplayName("§aApollo§f-§aKiste")
                    .setLore("§7Klicke, um §e1 §7Kiste für §61.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(1, new ItemBuilder(Material.CHEST, 1)
                    .setDisplayName("§aApollo§f-§aKiste")
                    .setLore("§7Klicke, um §e1 §7Kiste für §61.000 Credits ", "§7zu kaufen")
                    .build());
        }
        if (!(playerCoins >= 4500)) {
            inventory.setItem(3, new ItemBuilder(Material.CHEST, 1)
                    .setDisplayName("§aApollo§f-§aKiste")
                    .setLore("§7Klicke, um §e5 §7Kisten für §64.500 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(3, new ItemBuilder(Material.CHEST, 1)
                    .setDisplayName("§aApollo§f-§aKiste")
                    .setLore("§7Klicke, um §e5 §7Kisten für §64.500 Credits ", "§7zu kaufen")
                    .build());
        }
        if (!(playerCoins >= 25000)) {
            inventory.setItem(5, new ItemBuilder(Material.CHEST, 1)
                    .setDisplayName("§aApollo§f-§aKiste")
                    .setLore("§7Klicke, um §e36 §7Kisten für §625.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(5, new ItemBuilder(Material.CHEST, 1)
                    .setDisplayName("§aApollo§f-§aKiste")
                    .setLore("§7Klicke, um §e36 §7Kisten für §625.000 Credits ", "§7zu kaufen")
                    .build());
        }
        if (!(playerCoins >= 50000)) {
            inventory.setItem(7, new ItemBuilder(Material.CHEST, 1)
                    .setDisplayName("§aApollo§f-§aKiste")
                    .setLore("§7Klicke, um §e80 §7Kisten für §650.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(7, new ItemBuilder(Material.CHEST, 1)
                    .setDisplayName("§aApollo§f-§aKiste")
                    .setLore("§7Klicke, um §e80 §7Kisten für §650.000 Credits ", "§7zu kaufen")
                    .build());
        }

        // POSEIDON
        if (!(playerCoins >= 5000)) {
            inventory.setItem(10, new ItemBuilder(Material.ENDER_CHEST, 1)
                    .setDisplayName("§5Poseidon§f-§5Kiste")
                    .setLore("§7Klicke, um §e1 §7Kiste für §65.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(10, new ItemBuilder(Material.ENDER_CHEST, 1)
                    .setDisplayName("§5Poseidon§f-§5Kiste")
                    .setLore("§7Klicke, um §e1 §7Kiste für §65.000 Credits ", "§7zu kaufen")
                    .build());
        }
        if (!(playerCoins >= 25000)) {
            inventory.setItem(12, new ItemBuilder(Material.ENDER_CHEST, 1)
                    .setDisplayName("§5Poseidon§f-§5Kiste")
                    .setLore("§7Klicke, um §e6 §7Kisten für §625.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(12, new ItemBuilder(Material.ENDER_CHEST, 1)
                    .setDisplayName("§5Poseidon§f-§5Kiste")
                    .setLore("§7Klicke, um §e6 §7Kisten für §625.000 Credits ", "§7zu kaufen")
                    .build());
        }
        if (!(playerCoins >= 25000)) {
            inventory.setItem(14, new ItemBuilder(Material.ENDER_CHEST, 1)
                    .setDisplayName("§5Poseidon§f-§5Kiste")
                    .setLore("§7Klicke, um §e15 §7Kisten für §650.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(14, new ItemBuilder(Material.ENDER_CHEST, 1)
                    .setDisplayName("§5Poseidon§f-§5Kiste")
                    .setLore("§7Klicke, um §e15 §7Kisten für §650.000 Credits ", "§7zu kaufen")
                    .build());
        }
        if (!(playerCoins >= 100000)) {
            inventory.setItem(16, new ItemBuilder(Material.ENDER_CHEST, 1)
                    .setDisplayName("§5Poseidon§f-§5Kiste")
                    .setLore("§7Klicke, um §e32 §7Kisten für §6100.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(16, new ItemBuilder(Material.ENDER_CHEST, 1)
                    .setDisplayName("§5Poseidon§f-§5Kiste")
                    .setLore("§7Klicke, um §e32 §7Kisten für §6100.000 Credits ", "§7zu kaufen")
                    .build());
        }

        // HERO
        if (!(playerCoins >= 10000)) {
            inventory.setItem(19, new ItemBuilder(Material.YELLOW_SHULKER_BOX, 1)
                    .setDisplayName("§6Hero§f-§6Kiste")
                    .setLore("§7Klicke, um §e1 §7Kiste für §610.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(19, new ItemBuilder(Material.YELLOW_SHULKER_BOX, 1)
                    .setDisplayName("§6Hero§f-§6Kiste")
                    .setLore("§7Klicke, um §e1 §7Kiste für §610.000 Credits ", "§7zu kaufen")
                    .build());
        }
        if (!(playerCoins >= 30000)) {
            inventory.setItem(21, new ItemBuilder(Material.YELLOW_SHULKER_BOX, 1)
                    .setDisplayName("§6Hero§f-§6Kiste")
                    .setLore("§7Klicke, um §e3 §7Kisten für §630.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(21, new ItemBuilder(Material.YELLOW_SHULKER_BOX, 1)
                    .setDisplayName("§6Hero§f-§6Kiste")
                    .setLore("§7Klicke, um §e3 §7Kisten für §630.000 Credits ", "§7zu kaufen")
                    .build());
        }
        if (!(playerCoins >= 25000)) {
            inventory.setItem(23, new ItemBuilder(Material.YELLOW_SHULKER_BOX, 1)
                    .setDisplayName("§6Hero§f-§6Kiste")
                    .setLore("§7Klicke, um §e6 §7Kisten für §650.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(23, new ItemBuilder(Material.YELLOW_SHULKER_BOX, 1)
                    .setDisplayName("§6Hero§f-§6Kiste")
                    .setLore("§7Klicke, um §e6 §7Kisten für §650.000 Credits ", "§7zu kaufen")
                    .build());
        }
        if (!(playerCoins >= 100000)) {
            inventory.setItem(25, new ItemBuilder(Material.YELLOW_SHULKER_BOX, 1)
                    .setDisplayName("§6Hero§f-§6Kiste")
                    .setLore("§7Klicke, um §e15 §7Kisten für §6100.000 Credits ", "§7zu kaufen", "§a",
                            "§cDu hast nicht genug §6Credits")
                    .build());
        } else {
            inventory.setItem(25, new ItemBuilder(Material.YELLOW_SHULKER_BOX, 1)
                    .setDisplayName("§6Hero§f-§6Kiste")
                    .setLore("§7Klicke, um §e15 §7Kisten für §6100.000 Credits ", "§7zu kaufen")
                    .build());
        }
    }
}
