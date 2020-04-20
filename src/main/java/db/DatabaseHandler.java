package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

    private static final DatabaseHandler instance = new DatabaseHandler();
    private Connection dbConnection;

    private static final String url =
            "jdbc:mysql:" +         // Database type
            "//localhost" +         // Host
            ":3306/" +              // Port
            "tallinnbusdb" +        // Database name
            "";  // Timezone

    private static final String user = "root";

    private static final String pass = "root";

    private DatabaseHandler() {
        try {
            dbConnection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseHandler getInstance() {
        return instance;
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
