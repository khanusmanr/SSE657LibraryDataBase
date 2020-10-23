package cisapp;

import java.util.Map;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CISInsertRecordDialog extends JDialog {
	private JFrame parent;
	private Map<String, JTextField> fieldsByName;
	private SqlServerDbAccessor sqda;
	private String table;
	private String[] columnNames;

    public CISInsertRecordDialog(JFrame parent, SqlServerDbAccessor sqda, 
    		String table) {
        super(parent, "CIS Data Entry", true);
        this.sqda = sqda;
        sqda.connectToDb();
        this.table = table;
        fieldsByName = new HashMap<String, JTextField>();
        
        JPanel fieldsPanel = prepareFieldsForColumns();
        
        prepareControlPanel(fieldsPanel);
    }

	private void prepareControlPanel(JPanel fieldsPanel) {
        JButton btnSubmit = new JButton("Submit");
        
        btnSubmit.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	String sql = "INSERT INTO " + table + " VALUES (";
            	String colVal;
            	for (String colName : columnNames) {
            		colVal = fieldsByName.get(colName).getText();
            		if (colVal.trim().length() == 0)
            			sql += "NULL, ";
            		else
            			sql += "'" + colVal + "', ";
            	}
            	
            	sql = sql.substring(0, sql.length()-2) + ")";
            	System.out.println("In Submit handler: sql=" + sql);
            	try {
					int r = sqda.getConnection().
							createStatement().executeUpdate(sql);
					if (r == 1) {
						JOptionPane.showConfirmDialog(CISInsertRecordDialog.this,
								"1 row effected...", "Insert - Success", 
								JOptionPane.OK_OPTION);
						dispose();
					}
					else
						JOptionPane.showConfirmDialog(CISInsertRecordDialog.this,
								"Insertion failed. Please try again...");
				} catch (SQLException e1) {
					JOptionPane.showConfirmDialog(CISInsertRecordDialog.this,
							"Insertion failed. Please try again...");
					
					e1.printStackTrace();
				}
            }

        });
		
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	for (JTextField jtf : fieldsByName.values())
            		jtf.setText("");
                //dispose();
            }
        });
        
        JButton btnCancel = new JButton("Cancel");
        btnClear.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        
        JPanel bp = new JPanel();
        bp.add(btnSubmit);
        bp.add(btnClear);
        bp.add(btnCancel);
        
        getContentPane().add(fieldsPanel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
	}

	private JPanel prepareFieldsForColumns() {
		// TODO Auto-generated method stub
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        columnNames = 
        		new DbServerJTableModelAdaptor(sqda).getColNamesForTable(table);
        
        JLabel jLabel;
        JTextField jTextField;
        
        for (int fieldSeq = 0; fieldSeq < columnNames.length; fieldSeq++) {
        	jLabel = new JLabel(columnNames[fieldSeq] + ": ");
        	cs.gridx = 0;
        	cs.gridy = fieldSeq;
        	cs.gridwidth = 1;
        	panel.add(jLabel, cs);
 
        	jTextField = new JTextField(30);
	        cs.gridx = 1;
	        cs.gridy = fieldSeq;
	        cs.gridwidth = 2;
	        panel.add(jTextField, cs);
	        fieldsByName.put(columnNames[fieldSeq], jTextField);
        }
		
		return panel;
	}
	
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Insert Record Test");
        final JButton btnShow = new JButton("Click to show");
 
        btnShow.addActionListener(
	        new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	            	SqlServerDbAccessor sqda = new SqlServerDbAccessor(
	                		"csdata.cd4sevot432y.us-east-1.rds.amazonaws.com",
	                		"csc312cloud", "c3s!c2Cld", "StudTest");
	            	CISInsertRecordDialog insertDlg = new CISInsertRecordDialog(frame,
	            			sqda, "MZ01Login");
	                insertDlg.setVisible(true);
	                // if logon successfully
	                //if(insertDlg.isSucceeded()){
	                //    btnShow.setText("Hi " + insertDlg.getUsername() + "!");
	                //}
	            }
	        }
	    );
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnShow);
        frame.setVisible(true);
    }

	public boolean isSucceeded() {
		// TODO Auto-generated method stub
		return false;
	}
}
