package org.sct.sctlib.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author SCT_Alchemy
 * @date 2019-03-17 19:34
 */

public class NmsUtil {

    private static Class<?> chatSerializer;
    private static Class<?> enumTitleAction;
    private static Class<?> packetPlayOutTitle;
    private static Class<?> packetPlayOutChat;
    private static String version;
    private NmsUtil(){};

    static {
        /*org.bukkit.craftbukkit.vX_XX_RX;*/
        version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        chatSerializer = version.indexOf("v1_8_R1") != -1 ? getNMSClass("ChatSerializer") : getNMSClass("IChatBaseComponent$ChatSerializer");
        enumTitleAction = version.indexOf("v1_8_R1") != -1 ? getNMSClass("EnumTitleAction") : getNMSClass("PacketPlayOutTitle$EnumTitleAction");
        packetPlayOutTitle = getNMSClass("PacketPlayOutTitle");
        packetPlayOutChat = getNMSClass("PacketPlayOutChat");
    }

    /**
     * 取PacketPlayOutTitle类
     * @return {@link Class}
     */
    public static Class<?> getPacketPlayOutTitleClass() {
        return packetPlayOutTitle;
    }

    /**
     * 取PacketPlayOutChat类
     * @return {@link Class}
     */
    public static Class<?> getPacketPlayOutChatClass() {
        return packetPlayOutChat;
    }

    /**
     * 取EnumTitleAction类
     * @return {@link Class}
     */
    public static Class<?> getEnumTitleActionClass() {
        return enumTitleAction;
    }

    /**
     * 取chatSerializer类
     * @return {@link Class}
     */
    public static Class<?> getChatSerializerClass() {
        return chatSerializer;
    }

    /**
     * 取服务器版本  如v1_10_R1
     * @return
     */
    public static String getVersion() {
        return version;
    }

    public static Class<?> getOBCClass(String className) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + getVersion() + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取物品的NMS对象
     * @param is
     * @return {@link Object}
     */
    public static Object getNMSItem(ItemStack is) {
        try {
            return getOBCClass("inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack.class).invoke(is, is);
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return null;
    }

    /**
     * 取对应的NMS下的类
     * @param className 类名
     * @return {@link Class}
     */
    public static Class<?> getNMSClass(String className) {
        try {
            return Class.forName("net.minecraft.server." + version + "." + className);
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return null;
    }

    /**
     * 给一名玩家发送NMS数据包
     * @param player
     * @param packet
     */
    public static void sendPacket(Player player, Object packet) {
        Object entityPlayer = getNMSPlayer(player);
        try {
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, packet);
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
    }

    /**
     * 取玩家NMS对象也就是EntityPlayer类的实例
     * @param player 玩家
     * @return Object
     */
    public static Object getNMSPlayer(Player player) {
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            return entityPlayer;
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return player;
    }
}
