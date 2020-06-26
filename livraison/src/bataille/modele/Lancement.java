package bataille.modele;

import java.util.Random;

import bataille.modele.players.*;
import bataille.selectionBat.Fenetre;
import bataille.vue.*;

/**
	* Lancement est une classe qui permet de créer le choix de bateau dans la partie graphique
*/

public class Lancement{
	public GamePlayer player = new Player();
	public RandomPlayer playerRandom = new RandomPlayer(new Random());
	
	/**
		* Methode qui permet d'initialiser le lancement 
	*/
	public void initialiser(){
		int nbBateau = 0;
		int[] taille = {5,4,3,2,2};
		Mer partie = new Mer(player,10);
		Mer partieRandom = new Mer(playerRandom,10);
		
		while(nbBateau !=5){
			
			new Fenetre(player,partie,taille[nbBateau],nbBateau).setVisible(false);
			playerRandom.afficheBateau(partieRandom,taille[nbBateau]);
			
			nbBateau++;
			if(nbBateau ==5){
				player.setMer(partieRandom);
				playerRandom.setMer(partie);
				MerGUI fen = new MerGUI(player, playerRandom);
			}
		}
	}
}
