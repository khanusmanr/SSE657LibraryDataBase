package library;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import csdbdao.SqlServerDbAccessor;

public class Controller {
	
	public Controller() {
		//establish connection to database
	}
	
	public void viewBooks() {
		//both user and admin
		JFrame f = new JFrame("Books Available");
		SqlServerDbAccessor sqda = new SqlServerDbAccessor();
		sqda.setDbName("SSE657-Library");
		sqda.connectToDb();
		String sql = "SELECT * FROM Books_Instance";
		try {
			
			Statement stmt = sqda.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
            
            JTable book_list= new JTable(); 
            book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
            
            JScrollPane scrollPane = new JScrollPane(book_list); 
            
            f.add(scrollPane); 
            f.setSize(800, 400); 
            f.setVisible(true);
            f.setLocationRelativeTo(null);
		}
		catch (SQLException e1) {
            // TODO Auto-generated catch block
             JOptionPane.showMessageDialog(null, e1);
        } 
	}
	
	public void viewIssued() {
		//both user and admin
	}
	
	public void addUser() {
		//only admin
	}
	
	public void viewUser() {
		//only admin
		JFrame f = new JFrame("Books Available");
		SqlServerDbAccessor sqda = new SqlServerDbAccessor();
		sqda.setDbName("SSE657-Library");
		sqda.connectToDb();
		String sql = "SELECT * FROM Members";
		try {
			
			Statement stmt = sqda.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
            
            JTable book_list= new JTable(); 
            book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
            
            JScrollPane scrollPane = new JScrollPane(book_list); 
            
            f.add(scrollPane); 
            f.setSize(800, 400); 
            f.setVisible(true);
            f.setLocationRelativeTo(null);
		}
		catch (SQLException e1) {
            // TODO Auto-generated catch block
             JOptionPane.showMessageDialog(null, e1);
	}
	}
	public void issueBook() {
		//only admin
	}
	
	public void addBook() {
		//only admin
	}
	
	public void returnBook() {
		//only admin
	}
	
	public void createData() {
		//only admin
	}
}
