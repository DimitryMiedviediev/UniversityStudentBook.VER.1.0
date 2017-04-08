package filter;

import org.hibernate.dialect.MySQLInnoDBDialect;

/**
 * Created by Dimitry on 07.04.17.
 */
public class CustomMySQLDialect extends MySQLInnoDBDialect {
    public String getTableTypeString(){
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
