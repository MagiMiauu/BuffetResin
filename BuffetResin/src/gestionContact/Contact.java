package gestionContact;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.StringTokenizer;

import static java.nio.file.StandardCopyOption.*;
import javax.swing.ImageIcon;

public class Contact implements Serializable, Comparable<Object>
{

	private static final long serialVersionUID = 1L;
	private static String chemin = ".\\src\\fichierContact\\"; //chemin => chemin des fichiers
	private String nomFichier;

	private String nom;
	private String prenom;
	private String numeroTel;
	private String numeroMobile;
	private String email;
	private ImageIcon photo;
	private String photoDescription; //cr�e pour garder l'url de mon image
	
	//si l'on pr�cise le chemin de l'image a part. c'est utiliser pour le d�s�zise
	/**
	 * Premier constructeur des contacts si on a l'image
	 * ce constructeur est utilis� lorsque l'on d�serialise les contact d'un fichier
	 * @param nom
	 * @param prenom
	 * @param numeroTel
	 * @param numeroMobile
	 * @param email
	 * @param photoDescription
	 * @param photo
	 * @author loanb
	 */
	public Contact(String nom, String prenom, String numeroTel, String numeroMobile, String email,String photoDescription, ImageIcon photo) 
	{
		this.nom = nom;
		this.prenom = prenom;
		this.numeroTel = numeroTel;
		this.numeroMobile = numeroMobile;
		this.email = email;
		//ajout de l'image
		this.photo = new ImageIcon(photo.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
		this.photo.setDescription(photoDescription);
		this.photoDescription = photoDescription;
		this.nomFichier = nom+prenom;
	}
	
	/**
	 //si l'on pr�cise le chemin de l'image a part. c'est utiliser lors de la cr�ation/modification d'un contact
	 * Premier constructeur des contacts si on a l'image
	 * ce constructeur est utilis� lorsque l'on d�serialise les contact d'un fichier
	 * @param nom
	 * @param prenom
	 * @param numeroTel
	 * @param numeroMobile
	 * @param email
	 * @param photo
	 * @author loanb
	 */
	public Contact(String nom, String prenom, String numeroTel, String numeroMobile, String email,ImageIcon photo) 
	{
		this.nom = nom;
		this.prenom = prenom;
		this.numeroTel = numeroTel;
		this.numeroMobile = numeroMobile;
		this.email = email;
		
		//ajout de l'image
		this.photo = new ImageIcon(photo.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
		
		
		this.photoDescription = this.photo.getDescription();
		this.photo.setDescription(photoDescription);
		
		this.nomFichier = nom+prenom;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return  getNom() + " " + getPrenom() + " " + getNumeroMobile(); //cela s'affichera dans la jlist
	}
	
	/**
	 * enregistrer
	 * cette methode permet d'enregistrer/modifier un contact dans un fichier
	 * @author loanb
	 */
	public void enregistrer() 
	{
		System.out.println("Votre contact " + nom+ " "+prenom + " :  modifier/cr�e");
		String nomFichier = nom+prenom;
		
		StringTokenizer st = new StringTokenizer(photoDescription,".");
		if(st.countTokens() >=3)
			st.nextToken(); //tout ce qui est avant le premier . => poubelle 
		String cheminduficherRelatif = "." + st.nextToken() + "." + st.nextToken();
		//fin de la prise du chemin relatif
		
		System.out.println(cheminduficherRelatif);
		serializeObject(nomFichier,nom,prenom,numeroTel,numeroMobile,email,cheminduficherRelatif,photo); //�crire
	}
	/**
	 * serializeObject
	 * cette methode permet la cr�ation du fichier avec les param�re demander du contact
	 * @param nomFichier
	 * @param nom
	 * @param prenom
	 * @param numeroTel
	 * @param numeroMobile
	 * @param email
	 * @param photoDescription
	 * @param photo
	 * @author loanb
	 */
	private void serializeObject(String nomFichier, String nom, String prenom, String numeroTel, String numeroMobile, String email,String photoDescription, ImageIcon photo)
	{
		Contact cs = new Contact(nom,prenom,numeroTel,numeroMobile,email,photoDescription,photo);
		File f = new File(chemin+nomFichier+".txt");
		if(f.exists()) 
		{
			f.delete();
		}
		try 
		{
			FileOutputStream fichier = new FileOutputStream(chemin+nomFichier+".txt");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(cs);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * changerNomFichier
	 * cette methode est utiliser lorsque l'on modifier un contact
	 * elle supprimer l'ancien nom du fichier pour en recr�e un nouveau avec les bonnes informations
	 * @author loanb
	 */
	private void changerNomFichier() 
	{
		File f = new File(chemin+nomFichier+".txt");
		File f2 = new File(chemin+nom+prenom+".txt");
		try {
			Files.move(f.toPath(), f2.toPath(),REPLACE_EXISTING);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		nomFichier = nom+prenom;
	}
	/**suppressioncontact
	 * permet la supression du fichier qui coresspant ou contact que nous avons voulu supprimer
	 * @author loanb
	 */
	public void suppressioncontact()
	{
		File f = new File(chemin+nomFichier+".txt");
		System.out.println("votre fichier " + chemin+nomFichier+".txt" + " as �t� supprimer");
		f.delete(); 
	}
	/* compareTo
	 * cette methode nous permet de comparer un objet de type ici contact
	 * afin de determienr si c'est ou non le m�me contact
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @uthor loanb
	 */
	@Override
	public int compareTo(Object arg0) 
	{
		Contact c = (Contact)arg0;
		if(this.getNom().compareTo(c.getNom())==0) 
		{
			return getPrenom().compareTo(c.getPrenom());
		}
		else return getNom().compareTo(c.getNom());
	}

	//Setter et Getter : 
	/**
	 * @return
	 */
	public String getNom() 
	{
		return nom;
	}

	public void setNom(String nom) 
	{
		this.nom = nom;
		changerNomFichier();
	}

	/**
	 * @return
	 */
	public String getPrenom() 
	{
		return prenom;
	}

	/**
	 * @param prenom
	 */
	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
		changerNomFichier();
	}

	/**
	 * @return
	 */
	public String getNumeroTel() 
	{
		return numeroTel;
	}

	/**
	 * @param numeroTel
	 */
	public void setNumeroTel(String numeroTel) 
	{
		this.numeroTel = numeroTel;
	}

	/**
	 * @return
	 */
	public String getNumeroMobile() 
	{
		return numeroMobile;
	}

	/**
	 * @param numeroMobile
	 */
	public void setNumeroMobile(String numeroMobile) 
	{
		this.numeroMobile = numeroMobile;
	}

	/**
	 * @return
	 */
	public String getEmail() 
	{
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) 
	{
		this.email = email;
	}

	/**
	 * @return
	 */
	public ImageIcon getPhoto() 
	{
		return photo;
	}

	/**
	 * @param photo
	 */
	public void setPhoto(ImageIcon photo) 
	{
		this.photo = photo;
	}
	
	/**
	 * @param path
	 */
	public static void setChemin(String path) 
	{
		chemin = path;
	}
	
	/**
	 * @return
	 */
	public static String setChemin() 
	{
		return chemin;
	}
	/**
	 * @return
	 */
	public static String getChemin() 
	{
		return chemin;
	}

	/**
	 * @return
	 */
	public String getNomFichier() 
	{
		return nomFichier;
	}
	/**
	 * @return
	 */
	public String getPhotoDescription() 
	{
		return photoDescription;
	}

	/**
	 * @param photoDescription
	 */
	public void setPhotoDescription(String photoDescription) 
	{
		this.photoDescription = photoDescription;
	}

}


