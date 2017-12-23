import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {

	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/sp17680";
	// private static final String DB_URL =
	// "jdbc:mysql://localhost/Yelp_Pokemon";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "toor";

	private static Connection conn = null;
	
	static{
		init();
	}
	
	private static void init() {
		try {
			Class.forName(JDBC_DRIVER);
			// System.out.println("Connecting to database..."+DB_URL);
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			}
		} catch (Exception ex) {
			System.out.print(ex);
			ex.printStackTrace();
		}
	}
	
	public static Connection getDBConnection(){
		return conn;
	}
}
