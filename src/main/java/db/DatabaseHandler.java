package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

    private static final String url = "jdbc:mysql:" +    // Database type
            "//localhost" +         // Host
            ":3306/" +              // Port
            "tallinnbusdb" +        // Database name
            "?serverTimezone=UTC";  // Timezone
    private static final String user = "root";
    private static final String pass = "Rs15021988)";

    Connection dbConnection;

    public DatabaseHandler() {
        try {
            dbConnection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return dbConnection;
    }


    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
