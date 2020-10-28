import java.sql.*;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;


public class admin {
	public Connection menu(String admin) {
		JFrame adminMenu = new JFrame("Admin Menu");
		
		JButton view_books = new JButton("View Books");
		view_books.setBounds(50,20,150,25);
		view_books.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	JFrame f = new JFrame("Book Available");
	        	Connection connection = connection(); 
	        	
	        	String sql = "select * from Books";
	        	try { 
	        		Statement stmt = connection.createStatement(); 
	        		stmt.executeUpdate("Use Library");
	        		stmt = connection.createStatement(); 
	        		ResultSet rs = stmt.executeQuery(sql); 
	        		JTable book_list = new JTable(); 
	        		book_list.setModel(DbUtils.resultSetToTableModel(rs));
	        		JScrollPane scrollPane = new JScrollPane(book_list); 
	        		
	        		f.add(scrollPane);
	        		f.setSize(800,400);
	        		f.setVisible(true);
	        		f.setLocationRelativeTo(null);
	        	}
	        	catch(SQLException e1){ 
	        		JOptionPane.showMessageDialog(null, e1);
	        	}
	        	controller library = new controller();
	        	library.viewBooks();
	        }	
	    });
		
		JButton add_book = new JButton("Add Book");
		add_book.setBounds(250,20,150,25);
		add_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	
	        	JFrame g = new JFrame("Enter Book Details");
	        	JLabel l1,l2,l3; 
	        	l1 = new JLabel("Book Name");
	        	l1.setBounds(30,15,100,30);
	        	
	        	l2 = new JLabel("Genre");
	        	l2.setBounds(30,53,100,30);
	        	
	        	l3 = new JLabel("Author Name");
	        	l3.setBounds(30,90,100,30);
	        	
	        	JTextField F_bname = new JTextField();
	        	F_bname.setBounds(110,15,200,30);
	        	
	        	JTextField F_genre=new JTextField();
                F_genre.setBounds(110, 53, 200, 30);
                
                JTextField F_aname=new JTextField();
                F_aname.setBounds(110, 90, 200, 30);
	        	
                JButton create_but = new JButton("Submit");
                create_but.setBounds(130,130,80,25);
                create_but.addActionListener(new ActionListener() { 
                	public void actionPerformed(ActionEvent e) { 
                		String bname = F_bname.getText(); 
                		String genre = F_genre.getText(); 
                		String author = F_aname.getText();
                		Connection connection = connect();
                        
                        try {
                        Statement stmt = connection.createStatement();
                         stmt.executeUpdate("USE LIBRARY");
                         stmt.executeUpdate("INSERT INTO BOOKS(BNAME,GENRE,ANAME) VALUES ('"+bname+"','"+genre+"',"+author+")");
                         JOptionPane.showMessageDialog(null,"Book added!");
                         g.dispose();
                          
                        }
                         
                        catch (SQLException e1) {
                            // TODO Auto-generated catch block
                             JOptionPane.showMessageDialog(null, e1);
                        }   
                	}
                });
                g.add(l3);
                g.add(create_but);
                g.add(l1);
                g.add(l2);
                g.add(F_bname);
                g.add(F_genre);
                g.add(F_aname);
                g.setSize(350,200);  
                g.setLayout(null); 
                g.setVisible(true); 
                g.setLocationRelativeTo(null);
                
                }
                controller library = new controller();
	        	//library.addBook();
		});
		
		JButton view_issue = new JButton("View Issued Books");
		view_issue.setBounds(450,20,150,25);
		view_issue.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	JFrame f = new JFrame("User's List");
	        	Connection connection = connect(); 
	        	String sql = "select * from issued"; 
	        try {
	        	Statement stmt = connection.createStatement(); 
        		stmt.executeUpdate("Use Library");
        		stmt = connection.createStatement(); 
        		ResultSet rs = stmt.executeQuery(sql); 
        		JTable book_list = new JTable(); 
        		book_list.setModel(DbUtils.resultSetToTableModel(rs));
        		JScrollPane scrollPane = new JScrollPane(book_list); 
        		
        		f.add(scrollPane);
        		f.setSize(800,400);
        		f.setVisible(true);
        		f.setLocationRelativeTo(null);
	        }
	        catch(SQLException e1)
	        { 
	        	JOptionPane.showMessageDialog(null, e1);
	        }
	        	controller library = new controller();
	        	library.viewIssued();
	        } 
	    });
		
		
		JButton issue_book = new JButton("Issue Book");
		issue_book.setBounds(650,20,150,25);
		issue_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	
	        	JFrame g = new JFrame("Enter Details");
	        	JLabel l1,l2,l3,l4; 
	        	l1=new JLabel("Book ID(BID)");
	        	l1.setBounds(30,15, 100,30);
	        	
	        	l2=new JLabel("User ID(UID)");
	        	l2.setBounds(30,53,100,30);
	        	
	        	l3=new JLabel("Period(days)");
	        	l3.setBounds(30,90, 100,30);
	        	
	        	l4=new JLabel("Issued Date(DD-MM-YYYY)");
	        	l4.setBounds(30,127, 150,30);
	        	
	        	JTextField F_bid = new JTextField(); 
	        	F_bid.setBounds(110,15,200,30);
	        	
	        	JTextField F_uid = new JTextField(); 
	        	F_uid.setBounds(110,15,200,30);
	        	
	        	JTextField F_period = new JTextField(); 
	        	F_period.setBounds(110,15,200,30);
	        	
	        	JTextField F_issue = new JTextField(); 
	        	F_issue.setBounds(110,15,200,30);
	        	
	        	JButton create_but = new JButton("Submit");
	        	create_but.setBounds(130,170,80,25);
	        	create_but.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String uid = F_uid.getText();
	                    String bid = F_bid.getText();
	                    String period = F_period.getText();
	                    String issued_date = F_issue.getText();
	                    
	                    int period_int = Integer.parseInt(period);
	                    Connection connection = connect(); 
	                    try { 
	                    	Statement stmt = connection.createStatement(); 
	                    	stmt.executeUpdate("USE LIBRARY");
	                    	stmt.executeUpdate("Insert into Issued(UID,BID,ISSUED_DATE,PERIOD) Values('"+uid+"','"+bid+"','"+issued_date+"',"+period_int+")");
	                    	JOptionPane.showMessageDialog(null, "Book Issued!");
	                    	g.dispose(); 
	                    }
	                    catch(SQLException e1)
	                    { 
	                    	JOptionPane.showMessageDialog(null, e1);
	                    }
					}
	        		
	        	});
	        	g.add(l3);
                g.add(l4);
                g.add(create_but);
                g.add(l1);
                g.add(l2);
                g.add(F_uid);
                g.add(F_bid);
                g.add(F_period);
                g.add(F_issue);
                g.setSize(350,250);  
                g.setLayout(null);
                g.setVisible(true); 
                g.setLocationRelativeTo(null);
	        	
	        	controller library = new controller();
	        	library.issueBook();
	        }	
	    });
		
		JButton add_user=new JButton("Add User");
	    add_user.setBounds(50,60,150,25);
	    add_user.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	JFrame g = new JFrame("Enter User deatils"); 
	        	JLabel l1,l2;
	        	l1 = new JLabel("Username"); 
	        	l1.setBounds(30,15,100,30);
	        	
	        	l2 = new JLabel("Password"); 
	        	l1.setBounds(30,15,100,30);
	        	
	        	JTextField F_user = new JTextField();
	        	F_user.setBounds(110, 15, 200, 30);
                
                //set text field n  for password
                JPasswordField F_pass=new JPasswordField();
                F_pass.setBounds(110, 50, 200, 30);
                //set radio button for admin
                JRadioButton a1 = new JRadioButton("Admin");
                a1.setBounds(55, 80, 200,30);
                //set radio button for user
                JRadioButton a2 = new JRadioButton("User");
                a2.setBounds(130, 80, 200,30);
                //add radio buttons
                ButtonGroup bg=new ButtonGroup();    
                bg.add(a1);bg.add(a2);  

                JButton create_but = new JButton("Create");
                create_but.setBounds(130,130,80,25);
                create_but.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String username = F_user.getText(); 
						String password = F_pass.getText(); 
						Boolean admin = false; 
						if(a1.isSelected())
						{ 
							admin = true; 
						}
						Connection connection = connect(); 
						try {
		                    Statement stmt = connection.createStatement();
		                     stmt.executeUpdate("USE LIBRARY");
		                     stmt.executeUpdate("INSERT INTO USERS(USERNAME,PASSWORD,ADMIN) VALUES ('"+username+"','"+password+"',"+admin+")");
		                     JOptionPane.showMessageDialog(null,"User added!");
		                     g.dispose();
		                      
		                    }
		                     
		                    catch (SQLException e1) {
		                        // TODO Auto-generated catch block
		                         JOptionPane.showMessageDialog(null, e1);
		                    }   
		                     
					}
                	
                });
                g.add(create_but);
                g.add(a2);
                g.add(a1);
                g.add(l1);
                g.add(l2);
                g.add(F_user);
                g.add(F_pass);
                g.setSize(350,200);//400 width and 500 height  
                g.setLayout(null);//using no layout managers  
                g.setVisible(true);//making the frame visible 
                g.setLocationRelativeTo(null);
	        	
	        	controller library = new controller();
	        	library.addUser();
	        }	
	    });
	    
	    JButton view_user =new JButton("View Users");
	    view_user.setBounds(250,60,150,25);
	    view_user.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	JFrame f = new JFrame("User's List");
	        	Connection connection = connect(); 
	        	String sql = "select * from users"; 
	        try {
	        	Statement stmt = connection.createStatement(); 
        		stmt.executeUpdate("Use Library");
        		stmt = connection.createStatement(); 
        		ResultSet rs = stmt.executeQuery(sql); 
        		JTable book_list = new JTable(); 
        		book_list.setModel(DbUtils.resultSetToTableModel(rs));
        		JScrollPane scrollPane = new JScrollPane(book_list); 
        		
        		f.add(scrollPane);
        		f.setSize(800,400);
        		f.setVisible(true);
        		f.setLocationRelativeTo(null);
	        }
	        catch(SQLException e1)
	        { 
	        	JOptionPane.showMessageDialog(null, e1);
	        }
	        	controller library = new controller();
	        	library.viewUser();
	        }	
	    });
	    
	    JButton return_book=new JButton("Return Books");
	    return_book.setBounds(450,60,150,25);
	    return_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	
	        	JFrame g = new JFrame("Enter Details");
	        	JLabel l1,l2,l3,l4; 
	        	l1=new JLabel("Issue ID(IID)");
	        	l1.setBounds(30,15, 100,30);
	        	
	        	l4=new JLabel("Return Date(DD-MM-YYYY)");
	        	l4.setBounds(30,50, 150,30);
	        	
	        	JTextField F_iid = new JTextField();
                F_iid.setBounds(110, 15, 200, 30);
                
                JTextField F_return=new JTextField();
                F_return.setBounds(180, 50, 130, 30);
                
                JButton create_but = new JButton("Create");
                create_but.setBounds(130,130,80,25);
                create_but.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String iid = F_iid.getText();
	                    String return_date = F_return.getText();
	                     
	                    Connection connection = connect();
	                    try {
	                    	Statement stmt = connection.createStatement(); 
	                    	stmt.executeUpdate("Use Library");
	                    	String date1 =null; 
	                    	String date2 = return_date; 
	                    	ResultSet rs = stmt.executeQuery("SELECT ISSUED_DATE FROM ISSUED WHERE IID="+iid);
	                    	while(rs.next()) { 
	                    		date1 = rs.getString(1);  
	                    	}
	                    	try { 
	                    		 Date date_1=new SimpleDateFormat("dd-MM-yyyy").parse(date1);
	                             Date date_2=new SimpleDateFormat("dd-MM-yyyy").parse(date2);
	                             long diff = date_2.getTime() - date_1.getTime(); 
	                             ex.days= (int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
	                    	}
	                    	catch(ParseException e1) { 
	                    		e1.printStackTrace();
	                    	}
	                    	stmt.executeUpdate("UPDATE ISSUED SET RETURN_DATE='"+return_date+"' WHERE IID="+iid);
	                        g.dispose();
	                         
	    
	                        Connection connection1 = connect();
	                        Statement stmt1 = connection1.createStatement();
	                        stmt1.executeUpdate("USE LIBRARY");                
	                       ResultSet rs1 = stmt1.executeQuery("SELECT PERIOD FROM ISSUED WHERE IID="+iid); //set period
	                       String diff=null; 
	                       while (rs1.next()) {
	                            diff = rs1.getString(1);
	                             
	                          }
	                       int diff_int = Integer.parseInt(diff);
	                       if(ex.days&amp;amp;amp;amp;amp;amp;amp;amp;amp;amp;gt;diff_int) { 
	                    	   int fine = (ex.days-diff_int)*10; 
	                    	   stmt1.executeUpdate("Update Issues Set Fine = "+fine+"Where IID = "-iid);
	                    	   String fine_str = ("Fine :Rs. "+fine);
	                    	   JOptionPane.showMessageDialog(null, fine_str);
	                       }
	                       JOptionPane.showMessageDialog(null, "Book Returned!");
	                    }
	                    catch(SQLException e1){
	                    	JOptionPane.showMessageDialog(null, e1);
	                    }
					}
               
                });
                g.add(l4);
                g.add(create_but);
                g.add(l1);
                g.add(F_iid);
                g.add(F_return);
                g.setSize(350,250);
                g.setLayout(null);  
                g.setVisible(true); 
                g.setLocationRelativeTo(null); 
	        	//controller library = new controller();
	            //library.returnBook();	
	        }
	        });
	    
		
		JButton create_data=new JButton("Create/Reset Data");
	    create_data.setBounds(650,60,150,25);
	    create_data.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	
	        	create(); 
	        	JOptionPane.showMessageDialog(null, "Database Created/Reset!");
	        	controller library = new controller();
	        	library.createData();
	        }	
	    });
	   public Connection connect() {
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
	    
	    libraryMain lm = new libraryMain();
	    
	    lm.setIconOnTitleBar(new ImageIcon("images/libraryIcon.png").getImage());
		adminMenu.setIconImage(lm.getIconOnTitleBar()); 
	    
	    adminMenu.add(view_books);
	    adminMenu.add(create_data);
	    adminMenu.add(add_book);
	    adminMenu.add(view_issue);
	    adminMenu.add(issue_book);
	    adminMenu.add(add_user);
	    adminMenu.add(view_user);
	    adminMenu.add(return_book);
	    
	    adminMenu.setSize(850,180);//850 width and 180 height  
	    adminMenu.setLayout(null);
	    adminMenu.setVisible(true);//making the frame visible 
	    adminMenu.setLocationRelativeTo(null);
	}

	
	
}
