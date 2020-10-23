package cisapp;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableContentsPanel extends JPanel {
	private DbServerJTableModelAdaptor jtma;
	private SqlServerDbAccessor sqda;
	
    public TableContentsPanel(SqlServerDbAccessor sqda) {
        super(new GridLayout(1,0));
        this.sqda = sqda;
        sqda.connectToDb();
 
        jtma = new DbServerJTableModelAdaptor(sqda);
        String tableName = "Books";
        JTable table = new JTable(new DbTableModel(jtma, tableName));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableSortDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        SqlServerDbAccessor sqda = new SqlServerDbAccessor(
        		"csdata.cd4sevot432y.us-east-1.rds.amazonaws.com",
        		"csc312cloud", "c3s!c2Cld", "JLBookstore");
        System.out.println(sqda.getUrl());
        sqda.connectToDb();
        
        //Create and set up the content pane.
        TableContentsPanel newContentPane = 
        		new TableContentsPanel(sqda);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
    	System.out.println("After addition:" + frame.getComponents().length);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
