package org.sct.sctlib.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2019-03-17 21:00
 */

public class PlayerUtil {
    /**
     * 判断玩家是否拥有物品
     *
     * @param player 玩家
     * @param target 目标物品
     * @return 有/无
     */
    public static boolean hasItem(Player player, ItemStack target) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item.isSimilar(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将物品通过掉落方式发送给玩家
     *
     * @param player 玩家
     * @param item 奖励物品
     */
    public static void dropItem(Player player, ItemStack item) {
        if (item == null) {
            return;
        }
        player.getWorld().dropItem(player.getLocation(), item);
    }

    /**
     * 给玩家发送物品
     *
     * @param player 玩家
     * @param item 物品
     */
    public static void addItem(Player player, ItemStack item) {
        if (isInvFull(player)) {
            dropItem(player, item);
        } else {
            player.getInventory().addItem(new ItemStack[] { item });
        }
    }

    /**
     * 给玩家增加指定数量的物品
     *
     * @param player 玩家
     * @param item 物品
     * @param amount 数量
     */
    public static void addItem(Player player, ItemStack item, int amount) {
        item.setAmount(item.getAmount() + amount);
        if (isInvFull(player)) {
            dropItem(player, item);
        } else {
            player.getInventory().addItem(new ItemStack[] { item });
        }
    }

    /**
     * 删除玩家手里的物品
     *
     * @param player 玩家
     */
    public static void delItemInMainHand(Player player) {
        player.getInventory().setItemInMainHand(null);
    }

    /**
     * 删除玩家手里的物品
     *
     * @param player 玩家
     * @param amount 指定数量
     */
    public static void delItemInMainHand(Player player, int amount) {
        ItemStack item = player.getInventory().getItemInMainHand();
        item.setAmount(item.getAmount() - amount);
        player.getInventory().setItemInMainHand(item);
    }


    /**
     * 判断玩家背包是否满了
     *
     * @param player 玩家
     * @return 背包是否满了
     */
    public static boolean isInvFull(Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item.isSimilar(new ItemStack(Material.AIR))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 以玩家身份执行指令
     *
     * @param player 玩家
     * @param command 命令
     * @param var  替换的变量
     * @param target 替换var的变量
     */
    public static void performCmd(Player player, List<String> command, String var, String target) {
        player.setOp(true);
        for (String cmd : command) {
            player.performCommand(cmd.replace(var, target));
        }
        player.setOp(false);
    }
}
