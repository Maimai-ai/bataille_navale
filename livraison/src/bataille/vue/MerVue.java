package bataille.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Observer;
import java.util.Observable;

import bataille.modele.*;
import bataille.modele.players.*;
import bataille.controleur.*;

/**
	* MerVue est une classe qui herite de la classe JPanel et de l'interface Observer, c'est la vue de la Mer
*/

public class MerVue extends JPanel implements Observer {
	
	public Mer mer;
	public GamePlayer player;
	public RandomPlayer playerRand;
	
	/**
		* Constructeur de MerVue, elle initialise egalement le JPanel
		* @param player une instance de GamePlayer
		* @param playerRand une instance de RandomPlayer
	*/
	public MerVue(GamePlayer player, RandomPlayer playerRand){
		super();
		this.player = player;
		this.mer = player.getMer();
		this.playerRand = playerRand;
		
		this.setLayout(new GridLayout(11, 11));
		this.setBackground(Color.BLACK);
		
		initialiser();
	}
	
	/**
		* Methode qui d'initialiser la grille de la mer, avec 10x10 cases qui sont des PanelCase
		* et une ligne ainsi qu'un colonne de JPanel
	*/
	public void initialiser(){
		String colonnes[] = { "   ", " A "," B "," C "," D "," E "," F "," G "," H "," I "," J "," K "," L "," M "," N "," O "," P "," Q "," R "," S "," T "," X "," Y "};
		for(int k = 0; k<11; k++){
            JPanel colonne = new JPanel();
			colonne.setBackground(new Color(202,197,250,80));
			String string = colonnes[k];
			JLabel label = new JLabel(string);
			label.setFont(new Font("Verdana",1,20));
			colonne.add(label);
			colonne.setBorder(new javax.swing.border.LineBorder(Color.BLACK));
			this.add(colonne);
		}
		
		for(int i = 0; i < 10; i++){
			JPanel ligne = new JPanel();
			ligne.setBackground(new Color(202,197,250,80));
			String str = " " + (i+1) + " ";
			JLabel jlabel = new JLabel(str);
			jlabel.setFont(new Font("Verdana",1,20));
			ligne.add(jlabel);
			ligne.setBorder(new javax.swing.border.LineBorder(Color.BLACK));
			this.add(ligne);
			
			for(int j = 0; j < 10; j++){
				PanelCase panel = new PanelCase(this.mer.currentGrid[i][j],false);
				panel.setBorder(new javax.swing.border.LineBorder(Color.BLACK));
				this.add(panel);
				Case c = panel.getCase();
				c.addObserver(this);
				Controleur controle = new Controleur(c,this.playerRand);
				panel.addMouseListener(controle);
			}
		}
	}
	
	/**
		* Redefinition de la methode paintComponent,qui affiche les éléments.
		* @param g
		*		instance de Graphics
	*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
    }
    
    	/**
		* Redefinition de la methode update qui repaint le MerVue
		* @param o
		*		une instance de Observable
		* @param obj
		*		une instance de Object
	*/
    @Override
	public void update(Observable o, Object obj){
		this.repaint();
	}
}
