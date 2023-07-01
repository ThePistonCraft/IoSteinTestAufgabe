package de.eincode.iostein.cases.utils.inventorys;

import de.eincode.iostein.cases.CaseSystem;
import de.eincode.iostein.cases.api.paginated.PaginatedMenu;
import de.eincode.iostein.cases.api.paginated.PlayerMenuUtility;
import de.eincode.iostein.cases.utils.ItemBuilder;
import de.eincode.iostein.cases.utils.inventorys.cases.ApolloCase;
import de.eincode.iostein.cases.utils.inventorys.cases.HeroCase;
import de.eincode.iostein.cases.utils.inventorys.cases.PoseidonCase;
import de.eincode.iostein.cases.utils.inventorys.cases.win.apollo.ApolloWins;
import de.eincode.iostein.cases.utils.inventorys.cases.win.hero.HeroWins;
import de.eincode.iostein.cases.utils.inventorys.cases.win.poseidon.PoseidonWins;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;

public class MainInventory
        extends PaginatedMenu {

    private PlayerMenuUtility playerMenuUtility;

    public MainInventory(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.playerMenuUtility = playerMenuUtility;
    }

    @Override
    public String getMenuName() {
        return "§c§lKisten";
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;

        if (event.getView().getTitle().equalsIgnoreCase(getMenuName())) {
            event.setCancelled(true);

            if (event.isLeftClick()) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aApollo§f-§aKiste")) {
                    if (CaseSystem.getInstance().getCaseAPI().getCase(player, "apollo") >= 1) {
                        CaseSystem.getInstance().getCaseAPI().removeCase(player, "apollo", 1);
                        new ApolloCase(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
                        return;
                    }
                    player.closeInventory();
                    player.sendMessage(CaseSystem.getInstance().getPrefix() + "§cDu hast nicht genug Cases!");
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Poseidon§f-§5Kiste")) {
                    if (CaseSystem.getInstance().getCaseAPI().getCase(player, "poseidon") >= 1) {
                        CaseSystem.getInstance().getCaseAPI().removeCase(player, "poseidon", 1);
                        new PoseidonCase(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
                        return;
                    }
                    player.closeInventory();
                    player.sendMessage(CaseSystem.getInstance().getPrefix() + "§cDu hast nicht genug Cases!");
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Hero§f-§6Kiste")) {
                    if (CaseSystem.getInstance().getCaseAPI().getCase(player, "hero") >= 1) {
                        CaseSystem.getInstance().getCaseAPI().removeCase(player, "hero", 1);
                        new HeroCase(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
                        return;
                    }
                    player.closeInventory();
                    player.sendMessage(CaseSystem.getInstance().getPrefix() + "§cDu hast nicht genug Cases!");
                }
            }

            if (event.isRightClick()) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aApollo§f-§aKiste")) {
                    if (!CaseSystem.getInstance().getCaseManager().existCaseFile("apollo")) {
                        player.sendMessage(CaseSystem.getInstance().getPrefix() + "Die Case §aApollo §7existiert nicht!");
                        return;
                    }
                    new ApolloWins(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Poseidon§f-§5Kiste")) {
                    if (!CaseSystem.getInstance().getCaseManager().existCaseFile("poseidon")) {
                        player.sendMessage(CaseSystem.getInstance().getPrefix() + "Die Case §5Poseidon §7existiert nicht!");
                        return;
                    }
                    new PoseidonWins(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Hero§f-§6Kiste")) {
                    if (!CaseSystem.getInstance().getCaseManager().existCaseFile("hero")) {
                        player.sendMessage(CaseSystem.getInstance().getPrefix() + "Die Case §6Hero §7existiert nicht!");
                        return;
                    }
                    new HeroWins(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAlle Kisten")) {
                new CaseInventory(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lKisten kaufen")) {
                new BuyInventory(CaseSystem.getInstance().getPlayerMenuUtility(player)).open();
            }
        }
    }

    @Override
    public void setMenuItems() {
        final Player player = this.playerMenuUtility.getPlayer();

        int apolloCase = CaseSystem.getInstance().getCaseAPI().getCase(player, "apollo");
        int poseidonCase = CaseSystem.getInstance().getCaseAPI().getCase(player, "poseidon");
        int heroCase = CaseSystem.getInstance().getCaseAPI().getCase(player, "hero");
        int credits = CaseSystem.getInstance().getCoinAPI().getPlayerCoins(player);

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setDisplayName("§a").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        }
        for (int i = 0; i < 9 * 3; i++) {
            inventory.setItem(i, new ItemBuilder(Material.AIR, 1).build());

        }

        inventory.setItem(11, new ItemBuilder(Material.CHEST, 1).setDisplayName("§aApollo§f-§aKiste")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                .setLore("§7» Du hast §b" + apolloCase + "x §aApollo§f-§aKisten", "§a",
                        "§bLinksklick§7, um deine Kisten zu öffnen",
                        "§cRechtsklick§7, um den Kisteninhalt zu betrachten")
                .build());

        inventory.setItem(13, new ItemBuilder(Material.ENDER_CHEST, 1).setDisplayName("§5Poseidon§f-§5Kiste")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                .setLore("§7» Du hast §b" + poseidonCase + "x §5Poseidon§f-§6Kisten", "§a",
                        "§bLinksklick§7, um deine Kisten zu öffnen",
                        "§cRechtsklick§7, um den Kisteninhalt zu betrachten")
                .build());
        inventory.setItem(15, new ItemBuilder(Material.YELLOW_SHULKER_BOX, 1).setDisplayName("§6Hero§f-§6Kiste")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                .setLore("§7» Du hast §b" + heroCase + "x §6Hero§f-§6Kisten", "§a",
                        "§bLinksklick§7, um deine Kisten zu öffnen",
                        "§cRechtsklick§7, um den Kisteninhalt zu betrachten")
                .build());

        inventory.setItem(30, new ItemBuilder(Material.CHEST, 1).setDisplayName("§cAlle Kisten").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        inventory.setItem(31, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("§eDu hast §a§l" + credits + " Credits").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        inventory.setItem(32, new ItemBuilder(Material.NETHER_STAR, 1).setDisplayName("§6§lKisten kaufen").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
    }
}
