package bataille.controleur;

import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;

import bataille.modele.*;
import bataille.modele.players.*;

/**
	* Controleur est une classe qui herite de la classe MouseAdapter
*/
public class Controleur extends MouseAdapter{
	
	public GamePlayer player;
	public RandomPlayer playerRand;
	public Mer mer;
	public Mer merRand;
	public Case cas;
	
	/**
		* Constructeur de la classe Controleur
		* @param cas une instance de Case
		* @param playerRand une instance de RandomPlayer
	*/
	public Controleur(Case cas, RandomPlayer playerRand){
		this.cas = cas;
		this.mer = cas.getMer();
		this.player = this.mer.getPlayer();
		this.playerRand = playerRand;
		this.merRand = this.playerRand.getMer();
		
	}
	
	/**
		* une methode qui permet de modifier une case lors d'un clique, il detecte lorsqu'un utilisateur clique avec sa souris
		* @param e 
		*		une instance de MouseEvent
	*/
	@Override 
	public void mouseClicked(MouseEvent e){
		if(!this.cas.getTouche()){
			this.mer.movePlay(this.cas.getX(), this.cas.getY());
			Case c = this.playerRand.chooseMove(this.merRand);
			this.merRand.movePlay(c.getX(), c.getY());
		}
	}
}
