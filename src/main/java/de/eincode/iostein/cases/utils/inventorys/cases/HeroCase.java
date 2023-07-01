package de.eincode.iostein.cases.utils.inventorys.cases;

import de.eincode.iostein.cases.CaseSystem;
import de.eincode.iostein.cases.api.paginated.PaginatedMenu;
import de.eincode.iostein.cases.api.paginated.PlayerMenuUtility;
import de.eincode.iostein.cases.utils.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class HeroCase
        extends PaginatedMenu {

    private PlayerMenuUtility playerMenuUtility;
    public static ArrayList<UUID> casePlayer = new ArrayList<>();

    @Getter
    public static String caseType = null;
    private List<ItemStack> loot = new ArrayList<>();

    private static int speed = 1;
    private static int delay = 1;
    private static int counter = 0;
    private int ticks = 0;
    public static BukkitRunnable task;

    public HeroCase(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.playerMenuUtility = playerMenuUtility;
    }

    @Override
    public String getMenuName() {
        return "§cKisten";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;

        if (event.getView().getTitle().equalsIgnoreCase(getMenuName()))
            event.setCancelled(true);
    }

    @Override
    public void setMenuItems() {
        final Player player = playerMenuUtility.getPlayer();
        caseType = "hero";

        File file = new File(CaseSystem.getInstance().getDataFolder(), "hero.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        for (int i = 0; i < 54; i++) {
            ItemStack itemStack = (ItemStack) yamlConfiguration.get("hero." + i + ".item");
            loot.add(itemStack);
        }


        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setDisplayName("§a").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());

        inventory.setItem(4, new ItemBuilder(Material.HOPPER, 1).setDisplayName("§aGEWINN").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        Collections.shuffle(loot);
        for (int i = 9; i <= 17 && i - 9 < loot.size(); i++) {
            inventory.setItem(i, loot.get(i - 9));
        }
        task = new BukkitRunnable() {


            @Override
            public void run() {
                if (ticks % delay == 0) {
                    for (int i = 9; i <= 17; i++) {
                        if (i == 17) {
                            inventory.setItem(18, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setDisplayName("§a").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
                        } else {
                            ItemStack currentItem = inventory.getItem(i);
                            ItemStack nextItem = inventory.getItem(i + 1);
                            inventory.setItem(i, nextItem);
                            inventory.setItem(i + 1, currentItem);
                        }
                    }

                    counter++;
                    player.playSound(player.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 1, 1);

                    if (counter >= 10) {
                        counter = 0;
                        if (speed < 5) {
                            speed++;
                            changeAnimationSpeed(player, speed);
                        } else {
                            this.cancel();
                            counter = 0;
                            speed = 1;
                            casePlayer.remove(player.getUniqueId());

                            ItemStack itemStack = inventory.getItem(13);
                            runWin(player, itemStack);
                        }
                    }

                    player.updateInventory();
                }

                ticks++;
            }
        };

        task.runTaskTimer(CaseSystem.getInstance(), 0, speed);
        player.openInventory(inventory);
        casePlayer.add(player.getUniqueId());
    }

    public void changeAnimationSpeed(Player player, int newSpeed) {
        if (task != null) {
            task.cancel();
        }

        speed = newSpeed;
        Inventory inventory = player.getOpenInventory().getTopInventory();

        task = new BukkitRunnable() {

            @Override
            public void run() {
                if (ticks % delay == 0) {
                    for (int i = 9; i <= 17; i++) {
                        if (i == 17) {
                            inventory.setItem(18, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setDisplayName("§a").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
                        } else {
                            ItemStack currentItem = inventory.getItem(i);
                            ItemStack nextItem = inventory.getItem(i + 1);
                            inventory.setItem(i, nextItem);
                            inventory.setItem(i + 1, currentItem);
                        }
                    }

                    counter++;

                    if (counter >= 10) {
                        counter = 0;
                        if (speed < 5) {
                            speed++;
                            changeAnimationSpeed(player, speed);
                        } else {
                            this.cancel();
                            counter = 0;
                            speed = 1;
                            casePlayer.remove(player.getUniqueId());

                            ItemStack itemStack = inventory.getItem(13);
                            runWin(player, itemStack);
                        }
                    }

                    player.updateInventory();
                }

                ticks++;
            }

        };

        task.runTaskTimer(CaseSystem.getInstance(), 0, speed);
    }

    public static void cancelTask() {
        if (task != null) {
            task.cancel();

            counter = 0;
            speed = 1;
            delay = 1;
        }
    }

    private void runWin(Player player, ItemStack itemStack) {
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 5);
        player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast §e" +
                itemStack.getItemMeta().getDisplayName() + " §7gewonnen!");

        CaseSystem.getInstance().getWinAPI().addWin(player,
                itemStack.getItemMeta().getDisplayName());

        CaseSystem.getInstance().getCaseManager().executeCommandOrGiveItem(player, itemStack, "hero");
    }
}
