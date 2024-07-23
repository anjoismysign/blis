package me.anjoismysign.utilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ItemStackUtil {

    /**
     * Will display an ItemStack by either their ItemMeta's displayname or their Material's name
     *
     * @param itemStack The ItemStack to display
     * @return The displayname of the ItemStack
     */
    public static String display(ItemStack itemStack) {
        if (itemStack == null)
            return "null";
        if (!itemStack.hasItemMeta())
            return itemStack.getType().name();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta.hasItemName())
            return itemMeta.getItemName();
        if (itemMeta.hasDisplayName())
            return itemMeta.getDisplayName();
        return itemStack.getType().name();
    }

    /**
     * Will replace all instances of a string in an ItemStack's ItemMeta
     *
     * @param itemStack   The ItemStack to replace in
     * @param target      The string to replace
     * @param replacement The string to replace with
     */
    public static void replace(ItemStack itemStack, String target, String replacement) {
        if (itemStack == null)
            return;
        if (!itemStack.hasItemMeta())
            return;
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta.hasItemName()){
            String itemName = itemMeta.getItemName().replace(target, replacement);
            itemMeta.setItemName(itemName);
        }
        if (itemMeta.hasDisplayName()) {
            String displayname = itemMeta.getDisplayName().replace(target, replacement);
            itemMeta.setDisplayName(displayname);
        }
        if (itemMeta.hasLore()) {
            List<String> lore = new ArrayList<>();
            List<String> current = itemMeta.getLore();
            for (String s : current) {
                lore.add(s.replace(target, replacement));
            }
            itemMeta.setLore(lore);
        }
        itemStack.setItemMeta(itemMeta);
    }
}
