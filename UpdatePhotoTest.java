package cisapp;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class UpdatePhotoTest {
	private PreparedStatement stmt;
	private SqlServerDbAccessor dba;
	
	public UpdatePhotoTest() {
		dba = new SqlServerDbAccessor();
		dba.setDbName("StudTest");
		dba.connectToDb();
		//stmt = dba.getPrepStmt();
	}

	public void updateImageInColumn(String table, String column, 
			String imageFile, String condition) {
		FileInputStream imageInputStream = null;
		String updateSql = "UPDATE " + table + " SET " + column + " = ? " +
							condition;
		try {
			stmt = dba.getConnection().prepareStatement(updateSql);
			imageInputStream = new FileInputStream(new File(imageFile));
			stmt.setBinaryStream(1, imageInputStream);
			int success = stmt.executeUpdate();
			
			if (success == 1)
				System.out.println("One photo loaded successfully!");
			else
				System.out.println("Somehow it didn't go through...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// condition in format "WHERE xxx = yyy"
	public Image getImageInColumn(String table, String column, String condition) { 
		Image photo = null;
		InputStream imageInputStream = null;
		String selectSql = "SELECT " + column + 
				" FROM " + table + " " + condition;
		System.out.println(selectSql);
		
		ResultSet rs = null;
		try {
			// prepare statement with a SELECT statement, which may be a 
			// regular statement here
			stmt = dba.getConnection().prepareStatement(selectSql);
			rs = stmt.executeQuery();
			// move to the first and only row in the result set
			rs.next();
			imageInputStream = rs.getBinaryStream(1);
			photo = ImageIO.read(imageInputStream);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return photo;
	}
	
	// condition in format "WHERE xxx = yyy"
	public Image getImageInRow(String table, String condition) { 
		Image photo = null;
		InputStream imageInputStream = null;
		String selectSql = "SELECT * FROM " + table + " " + condition;
		System.out.println(selectSql);
		
		ResultSet rs = null;
		try {
			// prepare statement with a SELECT statement, which may be a 
			// regular statement here
			stmt = dba.getConnection().prepareStatement(selectSql);
			rs = stmt.executeQuery();
			// move to the first and only row in the result set
			rs.next();
			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();
			for (int i=1; i<=cols; i++) {
				System.out.println(i + "::" + meta.getColumnType(i));
				if (meta.getColumnType(i) == Types.VARBINARY) {					
					imageInputStream = rs.getBinaryStream(i);
					if (imageInputStream == null)
						photo = null;
					else
						photo = ImageIO.read(imageInputStream);
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return photo;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UpdatePhotoTest test = new UpdatePhotoTest();
		
		// Test update image
		test.updateImageInColumn("MZ01Login", "Photo", "image/user_cu_04.png",
				"WHERE ID = 4");
		
		// Test retrieve image
		Image photo;
		ImageIcon icon;
		for (int i=1; i<=5; i++) {
			photo = test.getImageInRow("MZ01Login", "WHERE ID = " + i);
			if (photo == null)
				icon = null;
			else 
				icon = new ImageIcon(photo);
			JOptionPane.showConfirmDialog(null, "Description goes here...", 
					"Photo Retrieval Test", 0, 0, icon);
		}

	}

}
