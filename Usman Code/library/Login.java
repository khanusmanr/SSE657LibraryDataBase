package library;
import javax.swing.*;

import csdbdao.SqlServerDbAccessor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;

public class Login {

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
	    login_but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = F_user.getText(); 
				@SuppressWarnings("deprecation")
				String password = F_pass.getText();
				
				if(username.equals(""))
				{ 
					JOptionPane.showMessageDialog(null, "Please enter username");
				}
				else if (password.equals(""))
				{ 
					JOptionPane.showMessageDialog(null, "Please enter password");
				}
				else { 
					SqlServerDbAccessor sqda = new SqlServerDbAccessor();
					
		    		sqda.setDbName("SSE657-Library");
		    		sqda.connectToDb();
		    		String sql = ("SELECT * FROM Members WHERE Username = '"+username+"'AND Password='"+password+"'");
		    		System.out.println(sql);
		    		
		    		try { 
		    			Statement stmt = sqda.getConnection().createStatement();
		    			ResultSet rs = stmt.executeQuery(sql);
		    			System.out.println("pass"); 
		    			//stmt.executeUpdate(sql);
		    			if(rs.next()==false) { 
		    				System.out.print("No User");
		    				JOptionPane.showMessageDialog(null, "Wrong Username/Password");
		    			}
		    			else { 
		    				
		    				System.out.println("pass2"); 
		    					System.out.println("pass3");
		    					String member = rs.getString("Status");
		    					System.out.println(member); 
		    					//String MID = rs.getString("MemberId");
		    					//System.out.println(MID); 
		    					
		    					if(member.equals("Admin"))
		    					{ 
		    		
		    						Admin.menu(username); 
		    						window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		    					}
		    					else { 
		    						//User user = new User();
		    						User.menu(username); 
		    						window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		    					}
		    			}
		    		}
		    		catch(Exception ex){ 
		    			ex.printStackTrace();
		    		}
				}
			} 
	    	
	    });
	    
    LibraryMain lm = new LibraryMain();
    
    lm.setIconOnTitleBar(new ImageIcon("images/libraryIcon.png").getImage());
    //Library Icons - Free Download, PNG and SVG. (n.d.). Retrieved September 23, 2020, from https://icons8.com/icons/set/library
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
}
	    
	    /*
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
	        	if(password.equals("password") && username.equals("admin"))//hardcoded for now
	        	{
	        		Admin admin = new Admin();
	        		admin.menu(username);
	        		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	        	}
	        	
	        	else if(username.equals("user") && password.equals("password")) //hardcoded for now
	        	{
	        		User user = new User();
	        		user.menu(username);
	        		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	        	}
	        	
	        	else
	        	{
	        		JOptionPane.showMessageDialog(null,"Username or password is not correct");
	        	}
	        }
	    }               
	    });*/
	    
	
