import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {

	public void gui() {
		// TODO Auto-generated method stub
		JFrame window=new JFrame("Login");  
	    JLabel l1,l2;  
	    l1=new JLabel("Username");  
	    l1.setBounds(30,15, 100,30); //x axis, y axis, width, height 
	     
	    l2=new JLabel("Password");  
	    l2.setBounds(30,50, 100,30);    
	     
	    JTextField F_user = new JTextField(); 
	    F_user.setBounds(110, 15, 200, 30);
	         
	    JPasswordField F_pass=new JPasswordField(); 
	    F_pass.setBounds(110, 50, 200, 30);
	       
	    JButton login_but=new JButton("Login");
	    login_but.setBounds(130,90,80,25);
	    login_but.addActionListener(new ActionListener() {  //Perform action
	         
	        public void actionPerformed(ActionEvent e){ 
	 
	        String username = F_user.getText(); 
	        String password = F_pass.getText();
	         
	        if(username.equals("")) //If username is null
	        {
	            JOptionPane.showMessageDialog(null,"Please enter username"); 
	        } 
	        else if(password.equals("")) //If password is null
	        {
	            JOptionPane.showMessageDialog(null,"Please enter password"); 
	        }
	        else 
	        {
	        	Connection connection = connect(); 
	        	try {
	        	Statement stmt = connection.createStatement();
	        	stmt.executeUpdate("Use library");
	        	String st = ("Select * From Users where username ='"+username+"'And password="+password+"'");
	        	ResultSet rs = stmt.executeQuery(st);
	        	if(rs.next()==false)
	        	{ 
	        		System.out.print("No user");
	        		JOptionPane.showMessageDialog(null, "Wrong Username/Password!");
	        	}
	        	else { 
	        		window.dispose();
	        		rs.beforeFirst();
	        		while(rs.next())
	        		{ 
	        			String admin = rs.getString("ADMIN");
	        			String UID = rs.getString("UID");
	        			if(admin.equals('1'))
	        			{ 
	        				admin.menu(); 
	        			}
	        			else { 
	        				user.menu(UID); 
	        			}
	        		}
	        	}
	        	}
	        	catch (Exception ex) { 
	        		ex.printStackTrace(); 
	        	}
        }
    }

			
	    });
	    
	    libraryMain lm = new libraryMain();
	    
	    lm.setIconOnTitleBar(new ImageIcon("images/libraryIcon.png").getImage());
		window.setIconImage(lm.getIconOnTitleBar()); 
		
	    window.add(F_pass); //add password
	    window.add(login_but);//adding button in JFrame  
	    window.add(F_user);  //add user
	    window.add(l1);  // add label1 i.e. for username
	    window.add(l2); // add label2 i.e. for password
	     
	    window.setSize(400,180);//400 width and 500 height  
	    window.setLayout(null); 
	    window.setVisible(true);//making the frame visible 
	    window.setLocationRelativeTo(null);
	}
	public static void create() {
	    try {
	    Connection connection=connect();
	    ResultSet resultSet = connection.getMetaData().getCatalogs();
	    //iterate each catalog in the ResultSet
	        while (resultSet.next()) {
	          // Get the database name, which is at position 1
	          String databaseName = resultSet.getString(1);
	          if(databaseName.equals("library")) {
	              //System.out.print("yes");
	              Statement stmt = connection.createStatement();
	              //Drop database if it pre-exists to reset the complete database
	              String sql = "DROP DATABASE library";
	              stmt.executeUpdate(sql);
	          }
	        }
	          Statement stmt = connection.createStatement();
	           
	          String sql = "CREATE DATABASE LIBRARY"; //Create Database
	          stmt.executeUpdate(sql); 
	          stmt.executeUpdate("USE LIBRARY"); //Use Database
	          //Create Users Table
	          String sql1 = "CREATE TABLE USERS(UID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, USERNAME VARCHAR(30), PASSWORD VARCHAR(30), ADMIN BOOLEAN)";
	          stmt.executeUpdate(sql1);
	          //Insert into users table
	          stmt.executeUpdate("INSERT INTO USERS(USERNAME, PASSWORD, ADMIN) VALUES('admin','admin',TRUE)");
	          //Create Books table
	          stmt.executeUpdate("CREATE TABLE BOOKS(BID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, BNAME VARCHAR(50), GENRE VARCHAR(20), PRICE INT)");
	          //Create Issued Table
	          stmt.executeUpdate("CREATE TABLE ISSUED(IID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UID INT, BID INT, ISSUED_DATE VARCHAR(20), RETURN_DATE VARCHAR(20), PERIOD INT, FINE INT)");
	          //Insert into books table
	          stmt.executeUpdate("INSERT INTO BOOKS(BNAME, GENRE, PRICE) VALUES ('War and Peace', 'Mystery', 200),  ('The Guest Book', 'Fiction', 300), ('The Perfect Murder','Mystery', 150), ('Accidental Presidents', 'Biography', 250), ('The Wicked King','Fiction', 350)");
	           
	    resultSet.close();
	    }
	    catch(Exception ex){ 
	    	ex.printStackTrace(); 
	    }
	    }
	private Connection connect() {
		// TODO Auto-generated method stub
		try { 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://csdata.cd4sevot432y.us-east-1.rds.amazonaws.com");
			return con; 
		}
		catch(Exception ex) { 
			ex.printStackTrace();
		}
		
		return null;
	}               

}
