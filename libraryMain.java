import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
 
import javax.swing.*;
//import net.proteanit.sql.DbUtils;
public class libraryMain extends JPanel {
	
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
