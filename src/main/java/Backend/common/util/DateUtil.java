package Backend.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class  DateUtil {

    public static Timestamp getDateNow() {
        //        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        return new Timestamp(System.currentTimeMillis());
    }
}
