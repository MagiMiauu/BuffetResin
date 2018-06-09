package gestionContact;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.StringTokenizer;

import static java.nio.file.StandardCopyOption.*;
import javax.swing.ImageIcon;

import gestionGalerie.MonImage;

public class Contact implements Serializable, Comparable{

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
	public Contact(String nom, String prenom, String numeroTel, String numeroMobile, String email,String photoDescription, ImageIcon photo) {
		this.nom = nom;
		this.prenom = prenom;
		this.numeroTel = numeroTel;
		this.numeroMobile = numeroMobile;
		this.email = email;
		//ajout de l'image
		this.photo = new ImageIcon(photo.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
		System.out.println(new ImageIcon (getPhotoDescription()).getImageLoadStatus() +"");
//		if ( new ImageIcon (getPhotoDescription()).getImageLoadStatus() != 8)
//		{
			
//			setPhoto(MonImage.transformationImage(".\\src\\photoDefault\\default.png", 120, 120));
//			setPhotoDescription(".\\src\\photoDefault\\default.png");
//		}
//		else
//		{
			this.photo.setDescription(photoDescription);
			this.photoDescription = photoDescription;
//		}
		
		this.nomFichier = nom+prenom;
	}
	
	public Contact(String nom, String prenom, String numeroTel, String numeroMobile, String email,ImageIcon photo) {
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

	@Override
	public String toString() 
	{
		return  getNom() + " " + getPrenom() + " " + getNumeroMobile(); //cela s'affichera dans la jlist
	}
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
	private void serializeObject(String nomFichier, String nom, String prenom, String numeroTel, String numeroMobile, String email,String photoDescription, ImageIcon photo) {
		Contact cs = new Contact(nom,prenom,numeroTel,numeroMobile,email,photoDescription,photo);
		File f = new File(chemin+nomFichier+".txt");
		if(f.exists()) 
		{
			f.delete();
		}
		try {
			FileOutputStream fichier = new FileOutputStream(chemin+nomFichier+".txt");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(cs);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	private void changerNomFichier() {
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
	public void suppressioncontact()
	{
		File f = new File(chemin+nomFichier+".txt");
		System.out.println("votre fichier " + chemin+nomFichier+".txt" + " as �t� supprimer");
		f.delete(); 
	}
	@Override
	public int compareTo(Object arg0) {
		Contact c = (Contact)arg0;
		if(this.getNom().compareTo(c.getNom())==0) 
		{
			return getPrenom().compareTo(c.getPrenom());
		}
		else return getNom().compareTo(c.getNom());
	}

	//Setter et Getter : 
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
		changerNomFichier();
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
		changerNomFichier();
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		numeroTel = numeroTel;
	}

	public String getNumeroMobile() {
		return numeroMobile;
	}

	public void setNumeroMobile(String numeroMobile) {
		numeroMobile = numeroMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ImageIcon getPhoto() {
		return photo;
	}

	public void setPhoto(ImageIcon photo) {
		this.photo = photo;
	}
	
	public static void setChemin(String path) {
		chemin = path;
	}
	
	public static String setChemin() {
		return chemin;
	}
	public static String getChemin() {
		return chemin;
	}

	public String getNomFichier() {
		return nomFichier;
	}
	public String getPhotoDescription() {
		return photoDescription;
	}

	public void setPhotoDescription(String photoDescription) {
		this.photoDescription = photoDescription;
	}

}


