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
	Statement statement;

	public Connection getDbConnection() {
		return dbConnection;
	}

	public Statement createStatement() {
		try {
			dbConnection = DriverManager.getConnection(url, user, pass);
			statement = dbConnection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	public void closeConnection() {
		try {
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
