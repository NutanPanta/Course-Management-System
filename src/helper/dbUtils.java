package helper;

import java.sql.*;

public class dbUtils {

    public static Connection getDBConnection() throws SQLException {

        String connectionString = "jdbc:mysql://" + config.dbHost + ":" + config.dbPort + "/" + config.dbName;

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        return DriverManager.getConnection(connectionString, config.dbuser, config.dbpass);

    }
}
