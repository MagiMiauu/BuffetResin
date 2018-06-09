package gestionContact;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import apparence.MaFenetre;
import apparence.MonBouton;
import apparence.MonLabel;
import apparence.PnlCentre;
import gestionContact .*;

public class PnlContact  extends PnlCentre {

	MonLabel lblTitre;
	MonBouton btnCreer;
	JList<Contact> lstContact;
	Contact[] tabContact;
	MaFenetre maman;
	DefaultListModel<Contact> lm;
	ArrayList<Contact> lContact;
	public PnlContact(MaFenetre maman) 
	{
		super("Contact");
		this.maman = maman;
		btnCreer = new MonBouton("Cr�er un nouveau contact");
		lblTitre = new MonLabel();
		lblTitre.setText("Contact");
		lContact = new ArrayList<Contact>();
		System.out.println("Voici la liste des contacts au d�but du programme : ");
		lireToutLesContact();
		add(lblTitre);
		btnCreer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				maman.afficherContact(null); //pour que l'image par defaut apparait 
				maman.changeCouche("Formulaire");
			}
		});
		//Affichage de la Jlist avec les images
		lm = new DefaultListModel<Contact>();
		for (Contact contact : lContact) {
			lm.addElement(contact);
		}
		
		//les �l�ments de la liste
		lstContact = new JList<>(lm);
		lstContact.setCellRenderer(new ContactCellRenderer());
		lstContact.setModel(lm);
		lstContact.setSelectedIndex(0); //pour ne pas avoir d'erreur de ne pas avoir selectionner
		JScrollPane scrollPane= new JScrollPane(lstContact); 
		scrollPane.setPreferredSize(new Dimension(460,580));
		add(scrollPane);
		lstContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				maman.afficherContact(lstContact.getSelectedValue());
			}
		});
		add(btnCreer);
	}
	private void lireToutLesContact() {
		File chemin = new File(".\\src\\fichierContact\\"); 
		listerRepertoire(chemin);
	}
	/*
	 * La methode listerRepertoire 
	 * 
	 * but : cette methode sert a lister tout les fichiers et les d�s�rialize
	 * 
	 * Entr�e : le chemin du repertoire ou contien les fichier
	 * Sortie : aucune
	 */
	public void listerRepertoire(File repertoire){
		String [] listefichiers;
		int i;
		listefichiers=repertoire.list();
		for(i=0;i<listefichiers.length;i++)
			{
				if(listefichiers[i].endsWith(".txt")) // == true
				{
					System.out.println("fichier : " + listefichiers[i].toString());
					deSerializeObject(listefichiers[i].toString());
				}
			}
		}
	/*
	 * Les methodes suivante servent a la cr�ation et la lecture des fichiers
	 * 
	 */
	public  void deSerializeObject(String nomFichier) 
	{ 
		try 
		{
			FileInputStream fichier = new FileInputStream(".\\src\\fichierContact\\"+nomFichier);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			Contact cs = (Contact) ois.readObject();
			lContact.add(cs);
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void ajouterContact(Contact contact) 
	{
		if(!lm.contains(contact)) 
		{
			//pur l'ajout dans l'ordre alphabatique de mani�re automatique (lors de l'ajout)
			//je pense que c'est ici pour les trucs de la size de l'image
			int i=0;
			while(i<lm.size() && lm.elementAt(i).compareTo(contact) < 0) 
			{
				i++;
			}
			lm.add(i, contact);
			//qqch avec celler //loan help
			lstContact.setSelectedIndex(-1);
		}
	}
	public void supprimerContact(Contact contact) 
	{
		if(lm.contains(contact)) 
		{
			int i = 0 ;

		while(i<lm.size() && !lm.elementAt(i).equals(contact)) 
		{
			i++;
		}
		lm.remove(i);
		lstContact.setSelectedIndex(1);
		}
	}
}
