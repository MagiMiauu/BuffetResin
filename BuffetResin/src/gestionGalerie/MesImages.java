package gestionGalerie;


import java.io.File;

import javax.swing.ImageIcon;











public class MesImages extends ImageIcon {
	private static final long serialVersionUID = 1L;
	ImageIcon images[];
	File recherche;
	//File ListePhoto[];
	//ImageIcon photoChoisie;
	
	
	public MesImages() {
		// TODO Auto-generated constructor stub
		
		File recherche = new File("C:/Users/julie/git/BuffetResin/BuffetResin/src/photoGallerie") ;
		
				 File [] listePhoto = recherche.listFiles(); 
				
				images = new ImageIcon[listePhoto.length] ;
				
				for(int i=0;i<images.length;i++) 
				{
					
					String url = listePhoto[i].getAbsolutePath();
					
				images[i]= new ImageIcon(url);
				}
			
				

				
}

	


}
