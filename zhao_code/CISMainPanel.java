package cisapp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CISMainPanel extends JPanel {
	private Image backgroundPhoto;
	private Image iconOnTitleBar;
	
	public CISMainPanel() {
		Dimension windowSize = new Dimension(900, 600);
		this.setPreferredSize(windowSize);
		this.setMaximumSize(windowSize);
		this.setMinimumSize(windowSize);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(backgroundPhoto, 0, 0, this.getPreferredSize().width, 
				this.getPreferredSize().height, null);
	}
	
	public static void main(String[] args) {
		JFrame window = new JFrame("College Information System - Welcome");
		
		CISMainPanel mp = new CISMainPanel();
		mp.iconOnTitleBar = new ImageIcon("image/MUSeal.png").getImage();
		mp.backgroundPhoto = new ImageIcon("image/MercerBears.jpg").getImage();
		window.setIconImage(mp.iconOnTitleBar);
		
		window.add(mp);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setIconOnTitleBar(Image image) {
		// TODO Auto-generated method stub
		this.iconOnTitleBar = image;
	}

	public Image getIconOnTitleBar() {
		// TODO Auto-generated method stub
		return iconOnTitleBar;
	}

	public void setBackground(Image image) {
		this.backgroundPhoto = image;
	}

}
