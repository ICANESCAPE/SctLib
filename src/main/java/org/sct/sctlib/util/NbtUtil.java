package org.sct.sctlib.util;

import net.minecraft.server.v1_10_R1.NBTTagByte;
import net.minecraft.server.v1_10_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftItem;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

/**
 * @author SCT_Alchemy
 * @date 2019-03-20 19:52
 */

public class NbtUtil {

    /**
     * 获取物品nbt属性
     *
     * @param is 物品
     * @return NBT属性
     */
    public static Object getItemNBT(ItemStack is) {
        Object nmsItem = NmsUtil.getNMSItem(is);
        if (nmsItem == null) {
            return null;
        }
        try {
            return nmsItem.getClass().getMethod("getTag").invoke(nmsItem) != null ? nmsItem.getClass().getMethod("getTag").invoke(nmsItem) : NmsUtil.getNMSClass("NBTTagCompound").newInstance();
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return null;
    }

    /**
     * 设置物品不可破坏
     *
     * @param item 物品
     * @param unbreak 是否不可破坏
     * @return 物品
     */
    public static ItemStack setUnbreakable(ItemStack item, boolean unbreak) {
        try {
            net.minecraft.server.v1_10_R1.ItemStack nms = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nms.hasTag() ? nms.getTag() : new NBTTagCompound();
            nbt.set("Unbreakable", new NBTTagByte((byte) 1));
            nms.setTag(nbt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

}
