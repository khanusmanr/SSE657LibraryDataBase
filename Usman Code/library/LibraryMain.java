package library;
import java.awt.Dimension;
import java.awt.Image;
 
import javax.swing.*;
public class LibraryMain extends JPanel {
	
	private Image iconOnTitleBar;
	//private Image backgroundPhoto;
	
	public void libraryMain() {
		Dimension windowSize = new Dimension(900, 600);
		this.setPreferredSize(windowSize);
		this.setMaximumSize(windowSize);
		this.setMinimumSize(windowSize);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login login = new Login();
		login.gui();
		//myBooks();
		//addBook();
		//addUser();
		//viewIssued();
	}
	
	public void setIconOnTitleBar(Image image) {
		// TODO Auto-generated method stub
		iconOnTitleBar = image;
	}
	
	public Image getIconOnTitleBar() {
		// TODO Auto-generated method stub
		return iconOnTitleBar;
	}
	
	public static void myBooks() {
		Controller c = new Controller();
		c.myBooks("meatball");
	}
	
	public static void addBook() {
		Controller c = new Controller();
		c.addBook();
	}
	
	public static void addUser() {
		Controller c = new Controller();
		c.addUser();
	}
	
	public static void viewIssued() {
		Controller c = new Controller();
		c.viewIssued();
	}
}
