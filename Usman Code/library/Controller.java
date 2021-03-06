package library;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
		String sql = "SELECT * FROM Book_Lookup";
		try {
			
			Statement stmt = sqda.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
            
            JTable book_list= new JTable(); 
            book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
            
            JScrollPane scrollPane = new JScrollPane(book_list); 
            
            LibraryMain lm = new LibraryMain();
    	    
    	    lm.setIconOnTitleBar(new ImageIcon("images/libraryIcon.png").getImage());
    	    //Library Icons - Free Download, PNG and SVG. (n.d.). Retrieved September 23, 2020, from https://icons8.com/icons/set/library
    		f.setIconImage(lm.getIconOnTitleBar()); 
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
	
	public void myBooks(String username) {
		JFrame f = new JFrame("My Books");
		SqlServerDbAccessor sqda = new SqlServerDbAccessor();
		sqda.setDbName("SSE657-Library");
		sqda.connectToDb();
		String sql = "SELECT * FROM Book_Lookup "
				+ "WHERE owner IN ( '"+username+"')";
		try {
			
			Statement stmt = sqda.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
            
            JTable book_list= new JTable(); 
            book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
            
            JScrollPane scrollPane = new JScrollPane(book_list); 
            
            LibraryMain lm = new LibraryMain();
    	    
    	    lm.setIconOnTitleBar(new ImageIcon("images/libraryIcon.png").getImage());
    	    //Library Icons - Free Download, PNG and SVG. (n.d.). Retrieved September 23, 2020, from https://icons8.com/icons/set/library
    		f.setIconImage(lm.getIconOnTitleBar()); 
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
		//admin only
		JFrame f = new JFrame("Books Available");
		SqlServerDbAccessor sqda = new SqlServerDbAccessor();
		sqda.setDbName("SSE657-Library");
		sqda.connectToDb();
		String sql = "SELECT * FROM Book_Lookup WHERE NOT owner = 'NULL'";
		try {
			
			Statement stmt = sqda.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
            
            JTable book_list= new JTable(); 
            book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
            
            JScrollPane scrollPane = new JScrollPane(book_list); 
            
            LibraryMain lm = new LibraryMain();
    	    
    	    lm.setIconOnTitleBar(new ImageIcon("images/libraryIcon.png").getImage());
    	    //Library Icons - Free Download, PNG and SVG. (n.d.). Retrieved September 23, 2020, from https://icons8.com/icons/set/library
    		f.setIconImage(lm.getIconOnTitleBar()); 
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
	
	public void addUser() {
		//only admin
		JFrame window=new JFrame("Add User");  
	    JLabel l1,l2, l3, l4, l5;  
	    
	    l2=new JLabel("Member Name");  
	    l2.setBounds(30,15, 100,30); 
	    
	    JTextField F_membername=new JTextField(); 
	    F_membername.setBounds(120, 15, 200, 30);
	    
	    l3=new JLabel("Username");  
	    l3.setBounds(30,50, 100,30); 
	    
	    JTextField F_username=new JTextField(); 
	    F_username.setBounds(120, 50, 200, 30);
	    
	    l4=new JLabel("Password");  
	    l4.setBounds(30,85, 100,30); 
	    
	    JTextField F_password=new JTextField(); 
	    F_password.setBounds(120, 85, 200, 30);
	    
	    l5=new JLabel("Status");  
	    l5.setBounds(30,120, 100,30); 
	    
	    JTextField F_status=new JTextField(); 
	    F_status.setBounds(120, 120, 200, 30);
	       
	    JButton add_but=new JButton("Add User");
	    add_but.setBounds(120,155,200,25);
	    add_but.addActionListener(new ActionListener() {  //Perform action
	         
	    public void actionPerformed(ActionEvent e){  
	    	//String memberid = F_memberID.getText();
        	String membername = F_membername.getText();
        	String username = F_username.getText();
        	String password = F_password.getText();
        	String status = F_status.getText();
        	
        	SqlServerDbAccessor sqda = new SqlServerDbAccessor();
    		sqda.setDbName("SSE657-Library");
    		sqda.connectToDb();
    		String sql = "INSERT INTO Members (MemberName, Username, Password, Status)"
    				+ "VALUES ('" +membername+ "' , '" + username + "' , '" + password + "' ,  '" + status + "')" ;
    		try {
    			
    			Statement stmt = sqda.getConnection().createStatement();
    			//ResultSet rs = stmt.executeQuery(sql);
    			stmt.executeUpdate(sql);
    			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                
    		}
    		catch (SQLException e1) {
                // TODO Auto-generated catch block
                 JOptionPane.showMessageDialog(null, e1);
    	}
    		
	        }
	    });
	    
	    LibraryMain lm = new LibraryMain();
	    
	    lm.setIconOnTitleBar(new ImageIcon("images/libraryIcon.png").getImage());
	    //Library Icons - Free Download, PNG and SVG. (n.d.). Retrieved September 23, 2020, from https://icons8.com/icons/set/library
		window.setIconImage(lm.getIconOnTitleBar()); 
		
	    window.add(F_password); //add password
	    //window.add(F_memberID);//adding button in JFrame  
	    window.add(F_username);  //add user
	    window.add(F_membername);
	    window.add(F_status);
	    window.add(add_but);
	    //window.add(l1);  // add label1 i.e. for username
	    window.add(l2); // add label2 i.e. for password
	    window.add(l3);
	    window.add(l4);
	    window.add(l5);
	     
	    window.setSize(400,300);//400 width and 500 height  
	    window.setLayout(null); 
	    window.setVisible(true);//making the frame visible 
	    window.setLocationRelativeTo(null);
	}
	
	public void viewUser() {
		//only admin
		JFrame f = new JFrame("Current Members");
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
            
            LibraryMain lm = new LibraryMain();
    	    
    	    lm.setIconOnTitleBar(new ImageIcon("images/libraryIcon.png").getImage());
    	    //Library Icons - Free Download, PNG and SVG. (n.d.). Retrieved September 23, 2020, from https://icons8.com/icons/set/library
    		f.setIconImage(lm.getIconOnTitleBar()); 
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
		JFrame window=new JFrame("Add Book");  
	    JLabel l1,l2, l3, l4, l5;  
	    l1=new JLabel("Title");  
	    l1.setBounds(30,15, 100,30); //x axis, y axis, width, height    
	     
	    JTextField F_title = new JTextField(); 
	    F_title.setBounds(110, 15, 200, 30);
	    
	    l2=new JLabel("Author");  
	    l2.setBounds(30,50, 100,30); 
	    
	    JTextField F_author=new JTextField(); 
	    F_author.setBounds(110, 50, 200, 30);
	    
	    l4=new JLabel("Genre");  
	    l4.setBounds(30,85, 100,30); 
	    
	    JTextField F_genre=new JTextField(); 
	    F_genre.setBounds(110, 85, 200, 30);
	    
	    l5=new JLabel("Owner");  
	    l5.setBounds(30,120, 100,30); 
	    
	    JTextField F_owner=new JTextField(); 
	    F_owner.setBounds(110, 120, 200, 30);
	       
	    JButton add_but=new JButton("Add Book");
	    add_but.setBounds(110,155,200,25);
	    add_but.addActionListener(new ActionListener() {  //Perform action
	         
	        public void actionPerformed(ActionEvent e){ 
	        	String author = F_author.getText();
	        	String title = F_title.getText();
	        	String genre = F_genre.getText();
	        	//String bookID = F_bookID.getText();
	        	String owner = F_owner.getText();
	        	
	        	//JFrame f = new JFrame("Current Members");
	    		SqlServerDbAccessor sqda = new SqlServerDbAccessor();
	    		sqda.setDbName("SSE657-Library");
	    		sqda.connectToDb();
	    		String sql = "INSERT INTO Book_Lookup (Title, Name, Genre, owner)"
	    				+ "VALUES ('" + title+ "', '" +author+ "' , '" + genre + "' ,  '" + owner + "')" ;
	    		try {
	    			
	    			Statement stmt = sqda.getConnection().createStatement();
	    			stmt.executeUpdate(sql);
	    			
	    			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	    		}
	    		catch (SQLException e1) {
	                // TODO Auto-generated catch block
	                 JOptionPane.showMessageDialog(null, e1);
	    	}
	        	
	        	//System.out.println("title "+title+" genre "+genre);
	        }
	    });
	    
	    LibraryMain lm = new LibraryMain();
	    
	    lm.setIconOnTitleBar(new ImageIcon("images/libraryIcon.png").getImage());
	    //Library Icons - Free Download, PNG and SVG. (n.d.). Retrieved September 23, 2020, from https://icons8.com/icons/set/library
		window.setIconImage(lm.getIconOnTitleBar()); 
		
	    window.add(F_author); //add password
	    window.add(add_but);//adding button in JFrame  
	    window.add(F_title);  //add user
	    window.add(F_genre);
	    //window.add(F_bookID);
	    window.add(F_owner);
	    window.add(l1);  
	    window.add(l2); 
	    //window.add(l3);
	    window.add(l4);
	    window.add(l5);
	     
	    window.setSize(400,300);  
	    window.setLayout(null); 
	    window.setVisible(true);//making the frame visible 
	    window.setLocationRelativeTo(null);
	}
	
	public void returnBook() {
		//only admin
	}
	
	/*public void createData() {
		//only admin
	}*/
}
