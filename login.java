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
	        	if(password.equals("password") && username.equals("admin"))//hardcoded for now
	        	{
	        		admin admin = new admin();
	        		admin.menu();
	        	}
	        	
	        	else if(username.equals("user") && password.equals("password")) //hardcoded for now
	        	{
	        		user user = new user();
	        		user.menu();
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

}
