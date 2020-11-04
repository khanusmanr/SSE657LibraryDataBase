package library;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class User extends JFrame {
	
	public void menu(String username) {
		JFrame userMenu = new JFrame("User Menu");
		
		JButton my_books = new JButton("My Books");
		my_books.setBounds(50,20,120,25);
		my_books.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	Controller library = new Controller();
	        	library.myBooks(username);
	        }	
	    });
		
		JButton view_books = new JButton("View Books");
		view_books.setBounds(180,20,120,25);
		view_books.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	Controller library = new Controller();
	        	library.viewBooks();
	        }	
	    });
		
		LibraryMain lm = new LibraryMain();
	    
	    lm.setIconOnTitleBar(new ImageIcon("images/libraryIcon.png").getImage());
		userMenu.setIconImage(lm.getIconOnTitleBar()); 
		
		userMenu.add(my_books);
		userMenu.add(view_books);
		
		userMenu.setSize(360,100);//360 width and 100 height  
	    userMenu.setLayout(null);  
	    userMenu.setVisible(true);//making the frame visible 
	    userMenu.setLocationRelativeTo(null);
	}
}
