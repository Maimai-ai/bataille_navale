package bataille.vue;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;

import bataille.modele.*;

/**
	* MerVueRand est une classe qui étend un JPanel implementant un Observer: elle Observe une Mer
*/
public class MerVueRand extends JPanel implements Observer {
	
	public Mer mer;
	
	/**
		* Constructeur de la methode, et dans lequel on initialise le JPanel
		* @param mer une instance de Mer
	*/
	public MerVueRand(Mer mer) {
		super();
		this.mer = mer;
		this.setLayout(new GridLayout(11, 11));
		this.setBackground(Color.BLACK);
		
		initialiser();
    } 
    
	/**
		* Methode qui permet d'initialiser et d'ajouter 10x10 PanelCase ainsi qu'une ligne de JPanel et une colonne de JPanel
	*/
	public void initialiser(){
		String colonnes[] = { "   ", " A "," B "," C "," D "," E "," F "," G "," H "," I "," J "," K "," L "," M "," N "," O "," P "," Q "," R "," S "," T "," X "," Y "};
		for(int k = 0; k<11; k++){
            JPanel colonne = new JPanel();
			colonne.setBackground(new Color(202,197,250,80));
			String string = colonnes[k];
			JLabel label = new JLabel(string);
			label.setFont(new Font("Verdana",1,20));
			colonne.setBorder(new javax.swing.border.LineBorder(Color.BLACK));
			colonne.add(label);
			this.add(colonne);
		}
		for(int i = 0; i < 10; i++){
			JPanel ligne = new JPanel();
			ligne.setBackground(new Color(202,197,250,80));
			String str = " " + (i+1) + " ";
			JLabel jlabel = new JLabel(str);
			jlabel.setFont(new Font("Verdana",1,20));
			ligne.setBorder(new javax.swing.border.LineBorder(Color.BLACK));
			ligne.add(jlabel);
			this.add(ligne);
			for(int j = 0; j < 10; j++){
				PanelCase panel = new PanelCase(this.mer.currentGrid[i][j], true);
				panel.setBorder(new javax.swing.border.LineBorder(Color.BLACK));
				this.add(panel);
				Case c = panel.getCase();
				c.addObserver(this);
			}
		}
	}
	
	/**
		* Redefinition de la methode
		* @param g une instance de Graphics
	*/
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
    }
	
	/**
		* Redefinition de la methode update
		* @param o une instance de Observable
		* @param obj une instance de Object
	*/
	@Override
	public void update(Observable o, Object obj){
		this.repaint();
	}
}