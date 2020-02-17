package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
	String url = 	"jdbc:mysql:" +         // Database type
					"//remotemysql.com" +   // Host
					":3306/" +              // Port
					"DCdmjMhpyO" +          // Database name
					"?serverTimezone=UTC";  // Timezone
	String user = 	"DCdmjMhpyO";
	String pass = 	"9GcyPpFVKz";

	Connection dbConnection;
	Statement statement;

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
