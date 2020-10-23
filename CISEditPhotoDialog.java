package cisapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CISEditPhotoDialog extends JDialog {
	private ResultSet rows;
	private Object[][] tableContents;
	private String[] columnNames;
	private JFrame parent;
	private String table;
	private Map<String, JTextField> fieldsByName;
	private SqlServerDbAccessor sqda;
	
	private JPanel contentPanel;
	private JPanel controlPanel;
	private JPanel textFieldPanel;
	private JPanel photoPanel;
	private JTextArea jtfPhotoDesc;
	private JLabel jlblPhoto;
	
	private int currRow;
	
	public CISEditPhotoDialog(JFrame parent, SqlServerDbAccessor sqda, 
    		String table) {
	    super(parent, "CIS Photo Edit", true);
	    this.sqda = sqda;
	    sqda.connectToDb();
	    this.table = table;
	    fieldsByName = new HashMap<String, JTextField>();
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		jtfPhotoDesc = new JTextArea(3, 40);
		contentPanel.add(jtfPhotoDesc, BorderLayout.SOUTH);
		
		textFieldPanel = prepareFieldPanel();
		contentPanel.add(textFieldPanel, BorderLayout.NORTH);
		
		photoPanel = new JPanel();
		Dimension photoDim = new Dimension(320, 350);
		photoPanel.setMinimumSize(photoDim);
		photoPanel.setMaximumSize(photoDim);
		photoPanel.setPreferredSize(photoDim);
		jlblPhoto = new JLabel();
		//ImageIcon photoIcon = new ImageIcon("image/user_cu_04.png");
		//try {
			Image photo = (Image)tableContents[currRow][5];
			ImageIcon photoIcon = new ImageIcon(photo);
			jlblPhoto.setIcon(photoIcon);
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		photoPanel.add(jlblPhoto);
		contentPanel.add(photoPanel);
		
		controlPanel = new JPanel();
		String[] btnNames = {"Previous", "Change Password", "Load Photo", 
				"Edit Description", "Next"}; 
		JButton[] btns = new JButton[btnNames.length];
		for (int i=0; i<btnNames.length; i++) {
			btns[i] = new JButton(btnNames[i]);
			controlPanel.add(btns[i]);
		}
		
		// hard code it for now to get the Load Photo nutton as btns[2]
		btns[2].addActionListener(
    	        new ActionListener(){
    	            public void actionPerformed(ActionEvent e) {
    	            	SqlServerDbAccessor sqda = new SqlServerDbAccessor(
    	                		"csdata.cd4sevot432y.us-east-1.rds.amazonaws.com",
    	                		"csc312cloud", "c3s!c2Cld", "StudTest");
    	            	JFileChooser fc = new JFileChooser();
    	            	int returnVal = fc.showOpenDialog(CISEditPhotoDialog.this);

    	                if (returnVal == JFileChooser.APPROVE_OPTION) {
    	                    File file = fc.getSelectedFile();
    	                    ImageIcon oldPhoto = (ImageIcon)jlblPhoto.getIcon();
    	                    ImageIcon newPhoto = new ImageIcon("image/"+file.getName());
    	                    jlblPhoto.setIcon(newPhoto);
    	                    //This is where a real application would open the file.
    	                    System.out.println("Opening: " + file.getName() + ".");

    	                    int dialogButton = JOptionPane.showConfirmDialog (null, 
    	                    		"Click YES to commit, NO to cancel...",
    	                    		"Set a Photo",JOptionPane.YES_NO_OPTION);
    	                    if(dialogButton == JOptionPane.YES_OPTION) {
    	                    	UpdatePhotoTest upt = new UpdatePhotoTest();
    	                    	upt.updateImageInColumn("MZ01Login", "Photo",
    	                    			 "image/"+file.getName(), "WHERE ID = 6");
    	                    }
    	                    else {
    	                    	jlblPhoto.setIcon(oldPhoto);
    	                    }
    	                }
    	            }
    	        }
    	    );

		if (currRow == 0) 
			btns[0].setEnabled(false);
		else if (currRow == btns.length - 1)
			btns[btns.length - 1].setEnabled(false);
		
		btns[btns.length - 1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currRow++;
				setValuesForRow(currRow);
			}
			
		}); 
			
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(controlPanel, BorderLayout.SOUTH);
		
		pack();
	}

	protected void setValuesForRow(int currRow2) {
		// TODO Auto-generated method stub
		
	}

	private JPanel prepareFieldPanel() {
	    JPanel panel = new JPanel(new GridBagLayout());
	    GridBagConstraints cs = new GridBagConstraints();
	
	    cs.fill = GridBagConstraints.HORIZONTAL;
	    
	    DbServerJTableModelAdaptor dao = new DbServerJTableModelAdaptor(sqda);
	    columnNames = dao.getColNamesForTable(table);
	    
	    tableContents = dao.getData(table);
	    currRow = 0;
	    
	    JLabel jLabel;
	    JTextField jTextField;
	    
	    Object data;
	    
	    for (int fieldSeq = 0; fieldSeq < 5; fieldSeq++) {
	    	jLabel = new JLabel(columnNames[fieldSeq] + ": ");
	    	cs.gridx = 0;
	    	cs.gridy = fieldSeq;
	    	cs.gridwidth = 1;
	    	panel.add(jLabel, cs);
	
	    	jTextField = new JTextField(30);
	    	
	    	data = tableContents[currRow][fieldSeq];
	    	
	    	jTextField.setText((data==null)?"":data.toString());
	    	jTextField.setEnabled(false);
	        cs.gridx = 1;
	        cs.gridy = fieldSeq;
	        cs.gridwidth = 2;
	        panel.add(jTextField, cs);
	        fieldsByName.put(columnNames[fieldSeq], jTextField);
	    }
	    
	    data = tableContents[currRow][6];
	    jtfPhotoDesc.setEnabled(false);
	    jtfPhotoDesc.setText((data==null)?"":data.toString());
		
		return panel;
	}

	public static void main(String[] args) {
		SqlServerDbAccessor sqda = new SqlServerDbAccessor(
        		"csdata.cd4sevot432y.us-east-1.rds.amazonaws.com",
        		"csc312cloud", "c3s!c2Cld", "StudTest");
		CISEditPhotoDialog d = new CISEditPhotoDialog(null,
    			sqda, "MZ01Login");
		d.setVisible(true);
	}
}
