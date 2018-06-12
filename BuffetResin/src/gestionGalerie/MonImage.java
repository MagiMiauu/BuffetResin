package gestionGalerie;


import java.awt.Image;
import javax.swing.ImageIcon;


//Cette classe permet de r�cup�rer une photo
public class MonImage extends ImageIcon 
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected MesImages mesImages = new MesImages();
	
	protected ImageIcon photoEntree;
	protected ImageIcon photoSortie;

	
		/**
		 * @author julien
		 * @param numImage
		 *  R�cup�ration d'une image par son num�ro dans un tableau et de lui donner une taille 
		 */
		public MonImage(int numImage) 
		{
				photoEntree=mesImages.recupererImage(numImage);
				
				//Transforme l'ImageIcon  en Image
				Image image = photoEntree.getImage();
				//la nouvelle image et mise � l'�chelle
				Image newimg = image.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);  
				
				//la newimg est transform�e en  ImageIcon
				photoSortie = new ImageIcon(newimg);
				photoSortie.setDescription(photoEntree.getDescription());
		}
	
		/**
		 * @author julien
		 * R�cup�ration d'une image par son lien et de lui donne une taille 
		 * @param chemin
		 */
		public MonImage(String chemin) 
		{
				
				ImageIcon photoEntree = new ImageIcon(chemin);
				
				Image image = photoEntree.getImage(); // transform it 
				Image newimg = image.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH); // Donne une taille � l'image � l'�chelle donn�e 
				
				photoSortie = new ImageIcon(newimg);
				photoSortie.setDescription(photoEntree.getDescription());
		}
	
		/**
		 * Constructeur de base
		 */
		public MonImage() 
		{
				
		}

	/**
	 * @param path
	 * @param width
	 * @return
	 */
	public  ImageIcon transformationImage(String path, int width) 
	{
		ImageIcon ii = new ImageIcon(path);
		double ratio = (double)width/ii.getIconWidth();
		return new ImageIcon(ii.getImage().getScaledInstance((int)(ii.getIconWidth()*ratio), (int)(ii.getIconHeight()*ratio), Image.SCALE_DEFAULT));
	}

	/**
	 * @param description
	 * @param i
	 * @param j
	 * @return
	 */
	public  ImageIcon transformationImage(String description, int i, int j) 
	{
		ImageIcon ii = new ImageIcon(description);
		System.out.println("MonImage je retourne votre description " +ii.getDescription());
		return new ImageIcon(ii.getImage().getScaledInstance(i, j, Image.SCALE_DEFAULT));
	}
	

}
