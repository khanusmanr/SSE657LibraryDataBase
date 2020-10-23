package cisapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class CISMainForm extends JFrame {
		
	public CISMainForm() {
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Security");
		menu.setMnemonic(KeyEvent.VK_S);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Menu for login, logout, change passwrod, etc.");
		// add menu to menuar
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Login",
		                         KeyEvent.VK_I);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "This will popup a Login Dialog...");
		menu.add(menuItem);
		
		// add action listener to activate this menu item
		menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                CISLoginDialog loginDlg = new CISLoginDialog(CISMainForm.this);
                loginDlg.setVisible(true);
                
                // if log on successfully
                if(loginDlg.isSucceeded()){
                	CISMainForm.this.setTitle(CISMainForm.this.getTitle()
                			+ " " + loginDlg.getUsername() + "!");
                }
            }
        });
		
		//additional JMenuItems, not functioning now
		menuItem = new JMenuItem("Change Password",
                KeyEvent.VK_P);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything now");
		menu.add(menuItem);
		
		// add action listener to activate Chg Pwd for Step 6
		menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	SqlServerDbAccessor sqda = new SqlServerDbAccessor(
            			"csdata.cd4sevot432y.us-east-1.rds.amazonaws.com",
                		"csc312cloud", "c3s!c2Cld", "StudTest");
            	CISEditPhotoDialog editDialog = new CISEditPhotoDialog(CISMainForm.this, 
                		sqda, "MZ01Login");
            	editDialog.setVisible(true);
            }
        });
		
		menu.addSeparator();
		menuItem = new JMenuItem("Switch User",
		                         KeyEvent.VK_U);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything now");
		menu.add(menuItem);

		menu.addSeparator();
		menuItem = new JMenuItem("Logout",
		                         KeyEvent.VK_O);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_4, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything now");
		menu.add(menuItem);
		// set menubar to window
		this.setJMenuBar(menuBar);	
		
		// for step 3
		// limited to select * from one table for now
		menu = new JMenu("Search");
		menu.setMnemonic(KeyEvent.VK_R); // R - for retrieval
		menu.getAccessibleContext().setAccessibleDescription(
		        "Menu for Search and display results");
		menuItem = new JMenuItem("From a Table",
                KeyEvent.VK_F);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_5, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This will select contents in the whole table");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	SqlServerDbAccessor sqda = new SqlServerDbAccessor();
            	sqda.setDbName("JLBookstore");
                System.out.println(sqda.getUrl());

            	sqda.connectToDb();
            	TableContentsPanel newContentPane = 
                		new TableContentsPanel(sqda);
            	newContentPane.setOpaque(true);
            	CISMainForm.this.getContentPane().removeAll();
            	CISMainForm.this.getContentPane().add(newContentPane);
            	CISMainForm.this.repaint();
            }
        });
		
		menuItem = new JMenuItem("Use a Query",
		        KeyEvent.VK_Q);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_6, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This will execute a SQL SELECT statement");
		menu.add(menuItem);
		

		menu.addSeparator();
		menuItem = new JMenuItem("Build Your Query",
                KeyEvent.VK_Y);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_7, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything now");
		menu.add(menuItem);

		menuBar.add(menu);

		// add 3 dummy menus for now
		menu = new JMenu("Data Entry");
		menuItem = new JMenuItem("To a Table",
                KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_8, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This will enter a row into a table");
		menu.add(menuItem);
		
		menuItem.addActionListener(
    	        new ActionListener(){
    	            public void actionPerformed(ActionEvent e) {
    	            	SqlServerDbAccessor sqda = new SqlServerDbAccessor(
    	                		"csdata.cd4sevot432y.us-east-1.rds.amazonaws.com",
    	                		"csc312cloud", "c3s!c2Cld", "StudTest");
    	            	CISInsertRecordDialog insertDlg = 
    	            			new CISInsertRecordDialog(CISMainForm.this,
    	            			sqda, "MZ01Login");
    	                insertDlg.setVisible(true);
    	                // if logon successfully
    	            }
    	        }
    	    );

		menu.addSeparator();
		menuItem = new JMenuItem("Load a CSV File",
                KeyEvent.VK_V);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_8, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything now");
		menu.add(menuItem);

		menuBar.add(menu);
		menu = new JMenu("Help");
		menuBar.add(menu);
	}

	public static void main(String[] args) {
		JFrame window = new CISMainForm();
		window.setTitle("College Information System - Welcome");
		
		CISMainPanel mp = new CISMainPanel();
		mp.setIconOnTitleBar(new ImageIcon("image/MUSeal.png").getImage());
		mp.setBackground(new ImageIcon("image/JesseBench.jpg").getImage());
		window.setIconImage(mp.getIconOnTitleBar());
		
		window.add(mp);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


