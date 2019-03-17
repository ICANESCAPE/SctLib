package org.sct.sctlib.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.sct.sctlib.SctLib;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author SCT_Alchemy
 * @date 2019-03-17 19:33
 */

public class MessageUtil {
    /**
     * 给一个玩家发送Title信息 1.8+
     *
     * @param player   发送的玩家
     * @param fadeIn   淡入时间
     * @param stay     停留时间
     * @param fadeOut  淡出时间
     * @param title    主标题
     * @param subtitle 副标题
     */
    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        String version = BasicUtil.getServerVersion();
        try {
            if (title != null) {
                title = BasicUtil.remove(title, '&');
                title = title.replaceAll("%player%", player.getName());
                Object enumTitle = NmsUtil.getNMSClass(version.equalsIgnoreCase("v1_8_R1") ? "EnumTitleAction" : "PacketPlayOutTitle$EnumTitleAction").getField("TITLE").get(null);
                Object chatTitle = NmsUtil.getNMSClass(version.equalsIgnoreCase("v1_8_R1") ? "ChatSerializer" : "IChatBaseComponent$ChatSerializer").getMethod("a", new Class[]{String.class}).invoke(null, new Object[]{title});
                Constructor<?> titleConstructor = NmsUtil.getNMSClass("PacketPlayOutTitle").getConstructor(new Class[]{NmsUtil.getNMSClass(version.equalsIgnoreCase("v1_8_R1") ? "EnumTitleAction" : "PacketPlayOutTitle$EnumTitleAction"), NmsUtil.getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE});
                Object titlePacket = titleConstructor.newInstance(new Object[]{enumTitle, chatTitle, fadeIn, stay, fadeOut});
                NmsUtil.sendPacket(player, titlePacket);
            }
            if (subtitle != null) {
                subtitle = BasicUtil.remove(subtitle, '&');
                subtitle = subtitle.replaceAll("%player%", player.getName());
                Object enumSubtitle = NmsUtil.getNMSClass(version.equalsIgnoreCase("v1_8_R1") ? "EnumTitleAction" : "PacketPlayOutTitle$EnumTitleAction").getField("SUBTITLE").get(null);
                Object chatSubtitle = NmsUtil.getNMSClass(version.equalsIgnoreCase("v1_8_R1") ? "ChatSerializer" : "IChatBaseComponent$ChatSerializer").getMethod("a", new Class[]{String.class}).invoke(null, new Object[]{subtitle});
                Constructor<?> subtitleConstructor = NmsUtil.getNMSClass("PacketPlayOutTitle").getConstructor(new Class[]{NmsUtil.getNMSClass(version.equalsIgnoreCase("v1_8_R1") ? "EnumTitleAction" : "PacketPlayOutTitle$EnumTitleAction"), NmsUtil.getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE});
                Object subtitlePacket = subtitleConstructor.newInstance(new Object[]{enumSubtitle, chatSubtitle, fadeIn, stay, fadeOut});
                NmsUtil.sendPacket(player, subtitlePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给一名玩家发送actionbar
     *
     * @param player 玩家
     * @param msg    信息
     */
    public static void sendBar(Player player, String msg) {
        msg = BasicUtil.remove(msg, '&');
        try {
            Object bar = NmsUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[]{String.class}).invoke(null, new Object[]{"{\"text\":\"" + msg + "\"}"});
            Constructor<?> chatConstructor = NmsUtil.getNMSClass("PacketPlayOutChat").getConstructor(new Class[]{NmsUtil.getNMSClass("IChatBaseComponent"), Byte.TYPE});
            Object packet = chatConstructor.newInstance(new Object[]{bar, (byte) 2});
            NmsUtil.sendPacket(player, packet);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException | InstantiationException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 给所有玩家发送一个持续存在的ActionBar
     *
     * @param timer timer
     */
    public static void sendState(long timer, String message) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    sendBar(player, BasicUtil.remove(message, '&'));
                }
            }
        }.runTaskTimer(SctLib.getPlugin(SctLib.class), 0L, timer);
    }

}
