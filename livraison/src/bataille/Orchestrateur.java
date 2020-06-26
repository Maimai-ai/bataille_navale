package bataille;

//import java.util.Scanner;
//import java.util.Random;

import bataille.modele.*;
import bataille.modele.players.*;

/**
	* Orchestrateur est une classe qui nous permet de faire marcher le jeu dans le terminale
*/
public class Orchestrateur{
	public GamePlayer player;
	public GamePlayer playerRandom;
	public Mer partie;
	public Mer partieRandom;
	
	/**
		* Constructeur de Orchestrateur
		* @param player une instance de GamePlayer, qui represente le premier joueur
		* @param playerRandom une instance de GemePlayer, qui represente le second joueur
	*/
	public Orchestrateur(GamePlayer player, GamePlayer playerRandom){
		this.player = player;
		this.playerRandom = playerRandom;
		this.partie = new Mer(player,10);
		this.partieRandom = new Mer(playerRandom, 10);
	}
	
	/**
		* Methode qui permet de jouer en ligne de commande
	*/
	public void playGame(){
		int nbBateau = 0;
		int[] taille = {5,4,3,2,2};
		int compteur = 0;
		while(nbBateau != 5){
			if(compteur == 0){
				System.out.println("Posez votre " + (compteur+1) + "er bateau de taille " + taille[compteur] + " : ");
			}
			else{
				System.out.println("Posez votre  " + (compteur+1) + "eme bateau de taille " + taille[compteur] + " : ");
			}
			player.afficheBateau(this.partie,taille[compteur]);
			System.out.println("Votre plateau actuellement : \n" + this.partie.situationToString(false));
			playerRandom.afficheBateau(this.partieRandom,taille[compteur]);
			nbBateau++;
			compteur++;
		}
		System.out.println("------------ COMMENCEZ ------------");
		// Tant que les deux parties ne sont pas finis
		while(!this.partie.isTerminal() && !this.partieRandom.isTerminal()){
			System.out.println("\n A TOI DE JOUER  : \n");
			System.out.println(this.partieRandom.situationToString(true));
			
			Case c = player.chooseMove(this.partieRandom);
			this.partieRandom.movePlay(c.getX(), c.getY());
			System.lineSeparator();
			
			System.out.println(" \n C'EST AU TOUR DE TON ADVERSAIRE : \n");
			System.out.println(this.partie.situationToString(true));
			
			Case caseRand = playerRandom.chooseMove(this.partie);
			this.partie.movePlay(caseRand.getX(),caseRand.getY());
		}

		System.out.println("Ta partie : \n");
		System.out.println(this.partieRandom.situationToString(true));

		System.out.println("Celle de ton adversaire : \n");
		System.out.println(this.partie.situationToString(true));

		System.out.println("------------ THE END ------------");
		
		if(this.partie.isTerminal()){
			System.out.println("Le gagnant est :" + playerRandom);
		}
		else{
			System.out.println("Vous avez gagner !");
		}
	}
}
