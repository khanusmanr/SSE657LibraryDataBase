package csdbdao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.Login;

public class SqlServerDbAccessor {

	private Connection con;
//	private Statement stmt;
	private PreparedStatement prepStmt;
//	private ResultSet rs;
	
	private String connectionUrl;
	
	private String defaultConnUrl = "jdbc:sqlserver://;" +
            "servername=csdata.cd4sevot432y.us-east-1.rds.amazonaws.com;"
			+ "user=csc312cloud;password=c3s!c2Cld;"; 
			// + "databaseName=JLBookstore;";
	/*
	// in WSC
	private String defaultConnUrl = "jdbc:sqlserver://;servername=cssql\\sqldata;"
				+ "user=csc480dev;password=c4s*C0sWe;" +
			"databaseName=JLBookstore;";
	*/

	public SqlServerDbAccessor() {
		connectionUrl = defaultConnUrl;
	}
	
	public SqlServerDbAccessor(String serverName, String user, String pwd, 
			String dbName) {
		connectionUrl = "jdbc:sqlserver://;";
		connectionUrl += "servername=" + serverName + ";"; 
		connectionUrl += "user=" + user + ";"; 
		connectionUrl += "password=" + pwd + ";"; 
		connectionUrl += "databaseName=" + dbName + ";"; 
	}
	
	
	public void setDbName(String dbName) {
		connectionUrl += "databaseName=" + dbName;
	}
	
	public void connectToDb() {
    	try {
    		// Establish the connection.
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	con = DriverManager.getConnection(connectionUrl);
    	} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public PreparedStatement getPrepStmt() {
		// TODO Auto-generated method stub
		return prepStmt;
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return con;
	}

	public String getUrl() {
		// TODO Auto-generated method stub
		return connectionUrl;
	}
	
	public static ResultSet loadEntriesFromDb(String file)
	{
		ArrayList<String> subjects = new ArrayList<String>();
		SqlServerDbAccessor sqda = new SqlServerDbAccessor();
		String SQL = "SELECT * FROM " + file;
		String[] row = new String[4];
		try {
			Statement stmt = sqda.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			/*while(rs.next()) {
				for (int i=1; i<=4; i++) {
					row[i-1] = ((rs.getString(i) == null) ? "" : rs.getString(i));
					subjects.add(row[i-1]);
				}
				
			}*/
			
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SqlServerDbAccessor sqda = new SqlServerDbAccessor();
		sqda.setDbName("SSE657-Library");
		sqda.connectToDb();
		//sqda.loadEntriesFromDb("Books_Instance");
}*/
}