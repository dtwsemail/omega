package omega.lang.common.utils;

import java.util.UUID;

/**
 * Created by Administrator on 2017/5/13.
 */
public class SerialUtil {

    public static  String generateSerialNo(){
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
