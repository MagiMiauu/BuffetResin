package apparence;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PnlCentre extends JPanel 
{
	
	// Panel d'application (centre)
	// Modele pour les appli
	String nom;
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	  protected void paintComponent(Graphics g) 
	{
	    super.paintComponent(g);
	        g.drawImage(new ImageIcon("./src/photoDefault/wallpaper1.png").getImage(), 0, 0, null);
	}
	
    	
	/**
	 * @param nom
	 */
	public PnlCentre(String nom) 
	{
		setSize(480,200);
		this.nom = nom;
		setOpaque(true);
	}
}
