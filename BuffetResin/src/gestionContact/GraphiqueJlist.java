package gestionContact;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/*
 * cette classe permet la mise en place graphique de la Jlist.
 * donc on definit le comportement de la Jlist
 * @author loanb
 */
	//pour la mise en place de la Jlist , spécialement concue pour les Jlist avec images
		@SuppressWarnings("serial")
		class GraphiqueJlist extends DefaultListCellRenderer implements ListCellRenderer<Object>
		{
		  /* (non-Javadoc)
		 * @see javax.swing.DefaultListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
		 */
		@Override
		  public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
		{
		     Contact photo = (Contact) value;
		 
		     setText(value.toString()); // texte
		     setIcon(photo.getPhoto()); //la photo
		    
		     //pour le changement de fond lorsque nous selectionnons l'élément
		     if (isSelected) 
		     {
		        setBackground(Color.cyan);
		        setForeground(list.getSelectionForeground()); //prendre par defaut
		     }
		     else 
		     {
		        setBackground(Color.white);
		        setForeground(list.getForeground()); //prendre par defaut
		     }
		     return this;
		  }
		}


