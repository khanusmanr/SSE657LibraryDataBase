import java.awt.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
//import net.proteanit.sql.DbUtils;
public class libraryMain extends JPanel {
	
	private Image iconOnTitleBar;
	//private Image backgroundPhoto;
	
	public  libraryMain() {
		Dimension windowSize = new Dimension(900, 600);
		this.setPreferredSize(windowSize);
		this.setMaximumSize(windowSize);
		this.setMinimumSize(windowSize);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		login login = new login();
		login.gui();
	}
	
	public void setIconOnTitleBar(Image image) {
		// TODO Auto-generated method stub
		iconOnTitleBar = image;
	}
	
	public Image getIconOnTitleBar() {
		// TODO Auto-generated method stub
		return iconOnTitleBar;
	}

}
