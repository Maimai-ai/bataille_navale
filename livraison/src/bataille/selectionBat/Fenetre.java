package bataille.selectionBat;

import javax.swing.*;
import java.awt.*;

import bataille.modele.players.*;
import bataille.modele.*;

/**
	* Fenetre est une classe qui herite de JFrame et permet de creer une Fenetre
*/
public class Fenetre extends JFrame {
    private JButton bouton = new JButton("Bateau");
	public GamePlayer player;
	public Mer mer;
	public int taille, nb;
	
	/**
		* Constructeur qui permet de creer une Fenetre et qui fait appel a SelectionBateau
		* Afin d'afficher une Fenetre avec un bouton
		* @param player une instance de GamePlayer
		* @param mer une Mer
		* @param taille un entier
		* @param nb un entier
    */
	
	public Fenetre(GamePlayer player,Mer mer,int taille,int nb){
		this.setTitle("Bateau");
		this.setSize(300, 100);
		this.setLocationRelativeTo(null);
		this.nb = nb +1;
		this.mer = mer;
		
		SelectionBateau zd = new SelectionBateau(this, "Choix de votre "+ this.nb + " bateau de taille " + taille, true,player,this.mer,taille,this.nb);
		InformationBateau zInfo = zd.showZDialog();
		if(zInfo.toString()!="non"){
			JLabel lab = new JLabel(zInfo.toString());
			lab.setFont(new Font("Consolas", Font.PLAIN,16));
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, lab, "Votre Bateau", JOptionPane.INFORMATION_MESSAGE);
		}
		this.setVisible(false);      
    }
}
