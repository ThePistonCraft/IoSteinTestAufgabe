package de.eincode.iostein.cases.utils;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    private ItemStack item = null;
    public ItemMeta meta = null;

    public ItemBuilder(Material material, int amount, byte subid) {
        this.item = new ItemStack(material, amount, (short) subid);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder setDisplayName(String name) {
        this.meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.meta.setLore(List.of(lore));
        return this;
    }

    public ItemBuilder setSkullOwner(String name) {
        SkullMeta skullMeta = (SkullMeta) this.meta;
        skullMeta.setOwner(name);
        this.meta = skullMeta;
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        this.meta.addItemFlags(new ItemFlag[]{itemFlag});
        return this;
    }

    public ItemStack build() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}
