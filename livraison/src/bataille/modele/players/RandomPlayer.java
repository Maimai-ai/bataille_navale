package bataille.modele.players;


import java.util.Random;

import bataille.modele.*;

/**
	* RandomPlayer est une classe implementant l'interface GamePlayer
*/

public class RandomPlayer implements GamePlayer{
	private Random randomGenerator;
	public Mer mer;
	
	/**
		* Constructeur de la methode RandomPlayer
		* @param mer une instance de Mer
	*/
	public RandomPlayer(Mer mer){
		this.mer = mer;
	}
	
	/**
		* Second constructeur de RandonPlayer
		* @param randomGenerator une instance de Random
	*/
	public RandomPlayer(Random randomGenerator){
		this.randomGenerator=randomGenerator;
	}
	
	/**
		* Accesseur getMer
		* @return la mer
	*/
	@Override
	public Mer getMer(){
		return this.mer;
	}
	
	/**
		* Mutateur setMer, modifie la Mer du joueur
		* @param mer definition de la mer
	*/
	@Override
	public void setMer(Mer mer){
		this.mer = mer;
	}
	
	/**
		* methode afficheBateau
		* @param mer
		* 		une instance de Mer
		* @param taille un entier
	*/
	@Override
	public void afficheBateau(Mer mer, int taille){
		int posX = this.randomGenerator.nextInt(10);
		int posY = this.randomGenerator.nextInt(10);
		int dir = this.randomGenerator.nextInt(2);
		boolean test = false;
		while(!test){
			posX = this.randomGenerator.nextInt(10);
			posY = this.randomGenerator.nextInt(10);
			dir = this.randomGenerator.nextInt(2);
			test = mer.navirePeutEtrePosee(posX,posY, taille, dir);
		}
		mer.chooseNavire(taille,posX,posY,dir);
	}
	
	
	/**
		* Redefinition de la methode chooseMove()
		* @param mer une instance de Mer
		* @return une case
		* 		qui correspond a la Case dont le joueur veut jouer
	*/
	@Override
	public Case chooseMove(Mer mer){		
		int position = this.randomGenerator.nextInt(mer.validMoves().size());
		int x = mer.validMoves().get(position).getX();
		int y = mer.validMoves().get(position).getY();
		Case c = new Case(x, y);
		return c;
	}
	
	/**
		* Redefinition de la methode toString()
		* @return une chaine de caractere qui affiche "Joueur aleatoire" et un "hash code" qui permet de distinguer plusieurs eventuels joueur aleatoires
	*/
	
	@Override
	public String toString(){
		return "Joueur aleatoire #"+this.hashCode();
	}
}
