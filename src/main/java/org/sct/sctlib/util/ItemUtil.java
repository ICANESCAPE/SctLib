package org.sct.sctlib.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2019-03-17 19:45
 */

public class ItemUtil {
    /**
     * 查找指定Lore的行数
     *
     * @param item 物品
     * @param lore 被查找的Lore
     * @return 行数，不存在未-1
     */
    public static int getLoreIndex(ItemStack item, String lore) {
        if (item == null || !item.hasItemMeta() || lore == null || "".equals(lore)) {
            return -1;
        }
        List<String> lores = item.getItemMeta().getLore();
        int count = 0;
        for (String target : lores) {
            if (lore.equalsIgnoreCase(target)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    /**
     * 在指定行数插入Lore
     *
     * @param item 物品
     * @param lore 插入的Lore
     * @return 插入Lore后的物品
     */
    public static ItemStack insert(ItemStack item, String lore) {
        if (item == null || !item.hasItemMeta() || lore == null || "".equals(lore)) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        lores.add(BasicUtil.remove(lore, '&'));
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 在指定行数插入Lore
     *
     * @param item 物品
     * @param lore 插入的Lore
     * @param index 指定行数
     * @return 插入Lore后的物品
     */
    public static ItemStack insert(ItemStack item, String lore, int index) {
        if (item == null || !item.hasItemMeta() || lore == null || "".equals(lore) || index < 0) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        lores.add(index, BasicUtil.remove(lore, '&'));
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 删除指定的Lore(可能存在多个)
     *
     * @param item 物品
     * @param lore 指定Lore
     * @return 完成后的物品
     */
    public static ItemStack delete(ItemStack item, String lore) {
        if (item == null || !item.hasItemMeta() || lore == null) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        for (String target : lores) {
            if (target.equalsIgnoreCase(BasicUtil.remove(lore, '&'))) {
                lores.remove(target);
            }
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 删除指定行数的Lore
     *
     * @param item 物品
     * @param index 指定行数
     * @return 完成后的物品
     */
    public static ItemStack delete(ItemStack item, int index) {
        if (item == null || !item.hasItemMeta() || index < 0) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        lores.remove(index);
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 替换指定的Lore
     *
     * @param item 物品
     * @param old 被替换的lore
     * @param target 替换的Lore
     * @return 替换后的物品
     */
    public static ItemStack replace(ItemStack item, String old, String target) {
        if (item == null || !item.hasItemMeta() || "".equals(old) || "".equals(target)) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        for (String string : lores) {
            if (string.equalsIgnoreCase(BasicUtil.remove(old, '&'))) {
                string.replace(string, BasicUtil.remove(target, '&'));
            }
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 替换指定行的Lore
     *
     * @param item 物品
     * @param  index 指定位置
     * @param target 替换的Lore
     * @return 替换后的物品
     */
    public static ItemStack replace(ItemStack item, int index, String target) {
        if (item == null || !item.hasItemMeta() || index < 0 || "".equals(target)) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        lores.set(index, BasicUtil.remove(target, '&'));
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

}
