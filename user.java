import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class user extends JFrame {
	
	public static void main(String[] args){
		JFrame userMenu = new JFrame("User Menu");
		
		JButton my_books = new JButton("My Books");
		my_books.setBounds(50,20,120,25);
		my_books.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	controller library = new controller();
	        	library.viewIssued();
	        }	
	    });
		
		JButton view_books = new JButton("View Books");
		view_books.setBounds(180,20,120,25);
		view_books.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	controller library = new controller();
	        	library.viewBooks();
	        }	
	    });
		
		userMenu.add(my_books);
		userMenu.add(view_books);
		
		userMenu.setSize(360,100);//400 width and 500 height  
	    userMenu.setLayout(null);//using no layout managers  
	    userMenu.setVisible(true);//making the frame visible 
	    userMenu.setLocationRelativeTo(null);
	}
}
