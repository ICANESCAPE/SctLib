package org.sct.sctlib.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author SCT_Alchemy
 * @date 2019-03-17 20:32
 */

public class TabUtil {
    /**
     * 更改一个玩家的Tab列表
     *
     * @param player 玩家
     * @param head Tab顶部
     * @param foot Tab底部
     */
    public static void setTab(Player player, String head, String foot) {
        if (head == null) {
            head = "";
        }
        head = ChatColor.translateAlternateColorCodes('&', head);
        if (foot == null) {
            foot = "";
        }
        foot = ChatColor.translateAlternateColorCodes('&', foot);
        try {
            Object tabHeader = NmsUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + head + "\"}" });
            Object tabFooter = NmsUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + foot + "\"}" });
            Constructor<?> titleConstructor = NmsUtil.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[] { NmsUtil.getNMSClass("IChatBaseComponent") });
            Object packet = titleConstructor.newInstance(new Object[] { tabHeader });
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, tabFooter);
            NmsUtil.sendPacket(player, packet);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
