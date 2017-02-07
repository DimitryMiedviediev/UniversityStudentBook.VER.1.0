package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dimitry on 07.02.17.
 */
public class GetTime {
    public String getTime() {
        return (new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS")).format(new Date());
    }
}
