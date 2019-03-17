package org.sct.sctlib.util;

/**
 * @author SCT_Alchemy
 * @date 2019-03-17 20:16
 */

public class RandomUtil {
    /**
     * 生成 1-max 之间的一个随机数
     *
     * @param max 最大的数值
     * @return 生成的随机数
     */
    public static int getRandom(int max) {
        return 1 + (int) (Math.random() * max);
    }

    /**
     * 生成 min-max 之间的一个随机数
     *
     * @param min 最小值
     * @param max 最大的数值
     * @return 生成的随机数
     */
    public static int getRandom(int min, int max) {
        return min + (int) (Math.random() * max);
    }
}
