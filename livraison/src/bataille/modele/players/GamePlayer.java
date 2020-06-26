package bataille.modele.players;

import bataille.modele.*;

/**
	* GamePlayer est une interface qui declare une methode chooseMove()
*/
public interface GamePlayer{
	/**
		* Declaraction de la methode chooseMove(), pour le choix d'un mouvement
		* @param mer
		* 		prend en argument une instance de type Mer
		* @return une Case
	*/
	public Case chooseMove(Mer mer);
    
	/**
		* Declaraction de la methode afficheBateau()
		* @param mer
		* 		prend en argument une instance de type Mer
		* @param taille un entier
	*/
    public void afficheBateau(Mer mer, int taille);
	
	/**
		* Declaraction de la methode getMer()
		* @return mer
		* 		prend en argument une instance de type Mer
	*/
	public Mer getMer();
	
	/**
		* Declaraction de la methode setMer()
		* @param mer
		* 		prend en argument une instance de type Mer
	*/
	public void setMer(Mer mer);
}

