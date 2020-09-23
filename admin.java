
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class admin {
	public void menu() {
		JFrame adminMenu = new JFrame("Admin Menu");
		
		JButton view_books = new JButton("View Books");
		view_books.setBounds(50,20,150,25);
		view_books.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	controller library = new controller();
	        	library.viewBooks();
	        }	
	    });
		
		JButton add_book = new JButton("Add Book");
		add_book.setBounds(250,20,150,25);
		add_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	controller library = new controller();
	        	library.addBook();
	        }	
	    });
		
		JButton view_issue = new JButton("View Issued Books");
		view_issue.setBounds(450,20,150,25);
		view_issue.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	controller library = new controller();
	        	library.viewIssued();
	        }	
	    });
		
		JButton issue_book = new JButton("Issue Book");
		issue_book.setBounds(650,20,150,25);
		issue_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	controller library = new controller();
	        	library.issueBook();
	        }	
	    });
		
		JButton add_user=new JButton("Add User");
	    add_user.setBounds(50,60,150,25);
	    add_user.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	controller library = new controller();
	        	library.addUser();
	        }	
	    });
	    
	    JButton view_user =new JButton("View Users");
	    view_user.setBounds(250,60,150,25);
	    view_user.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	controller library = new controller();
	        	library.viewUser();
	        }	
	    });
	    
	    JButton return_book=new JButton("Return Books");
	    return_book.setBounds(450,60,150,25);
	    return_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	controller library = new controller();
	        	library.returnBook();
	        }	
	    });
		
		JButton create_data=new JButton("Create/Reset Data");
	    create_data.setBounds(650,60,150,25);
	    create_data.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	controller library = new controller();
	        	library.createData();
	        }	
	    });
	    
	    adminMenu.add(view_books);
	    adminMenu.add(create_data);
	    adminMenu.add(add_book);
	    adminMenu.add(view_issue);
	    adminMenu.add(issue_book);
	    adminMenu.add(add_user);
	    adminMenu.add(view_user);
	    adminMenu.add(return_book);
	    
	    adminMenu.setSize(850,180);//400 width and 500 height  
	    adminMenu.setLayout(null);//using no layout managers  
	    adminMenu.setVisible(true);//making the frame visible 
	    adminMenu.setLocationRelativeTo(null);
	}
}
