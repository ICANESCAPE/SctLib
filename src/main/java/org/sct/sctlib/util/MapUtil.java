package org.sct.sctlib.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author SCT_Alchemy
 * @date 2019-03-17 20:14
 */

public class MapUtil {
    /**
     * 取HashMap的Key
     * @param map
     * @param value
     * @return 传入map的Key
     */
    public static Object getKey(Map map, Object value){
        List<Object> keyList = new ArrayList<>();
        for(Object key: map.keySet()){
            if (map.get(key).equals(value)){
                keyList.add(key);
            }
        }
        return keyList;
    }


}
