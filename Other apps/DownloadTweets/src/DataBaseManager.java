

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/pokemongotweets?useUnicode=yes&characterEncoding=UTF-8";

   //  Database credentials
   private static final String USER = "pavankon_app";
   private static final String PASS = "ppa_noknavap";
   
   
   private Connection conn = null;
	
	public Connection getNewDataBaseConnection(){
		Connection con = null;
		   try{
		      Class.forName(JDBC_DRIVER);
		      System.out.println("Connecting to database...");
		      if(this.conn == null || this.conn.isClosed()){
			      this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      }
	    	  con = this.conn;
		   }
		   catch(Exception ex){
			   System.out.print(ex);
			   ex.printStackTrace();
		   }
		   
		   return con;
		}
	
	public PreparedStatement startDatabaseOperation(String QUERY) throws SQLException{
		PreparedStatement pst = getNewDataBaseConnection().prepareStatement(QUERY);
		return pst;
	}
	
	public ResultSet getDatabaseOprationResult(PreparedStatement pst, String qry) throws SQLException{
		return pst.executeQuery();
	}
	
	public int getDatabaseOprationResult(PreparedStatement pst) throws SQLException{
		return pst.executeUpdate();
	}
	
	public void stopDatabaseOperation() throws SQLException{
		this.conn.close();
	}

	public Connection getConn() {
		return this.conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public static DataBaseManager getRegDBConnection() {
		// TODO Auto-generated method stub
		return null;
	}
}
