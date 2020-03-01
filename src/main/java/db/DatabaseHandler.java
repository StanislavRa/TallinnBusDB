package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
	String url = 	"jdbc:mysql:" +         // Database type
					"//remotemysql.com" +   // Host
					":3306/" +              // Port
					"rWRRgkd7lg" +          // Database name
					"?serverTimezone=UTC";  // Timezone
	String user = 	"rWRRgkd7lg";
	String pass = 	"U0UirVF1pu";

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
