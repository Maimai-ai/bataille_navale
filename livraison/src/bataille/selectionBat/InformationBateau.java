package bataille.selectionBat;

import bataille.modele.*;

/**
	* Cette classe permet d'afficher une String
*/
public class InformationBateau {
	public int taille ,posX, posY, dir;
	public Mer mer;
	
	public InformationBateau(){
	}
	
	/**
		* Constructeur de la classee
		* @param taille un entier
		* @param posX un entier
		* @param posY un entier
		* @param dir un entier
		* @param mer une Mer
	*/
	public InformationBateau(int taille , int posX, int posY, int dir, Mer mer){
		this.taille = taille;
		this.posX = posX;
		this.posY = posY;
		this.dir = dir;
		this.mer = mer;
	}
    
	/** 
		* Redefinition de la methode toString
		* @return une String
	*/
	@Override
	public String toString(){
		String str;
		if(this.taille != 0){
			// Utilisation du html pour un affichage avec des saut de ligne
			if(this.dir ==0 ){
				str = "<html> Description du bateau\n <br>";
				str += "Taille : " + this.taille + "\n <br>";
				str += "Ordonnee : " + (char)(this.posY+65) + " \n <br>";
				str += "Abscisse : " +  this.posX + "\n <br>";
				str += "Direction : Horizontale\n <br>";
				str += "\n <br>";
				str += mer.affichageChoixBateau() +"\n </html>";
			}
			else{
				str = "<html> Description du bateau\n";
				str += "Taille : " + this.taille + "\n <br>";
				str += "Ordonnee : " + (char)(this.posY+65) + "\n <br>";
				str += "Abscisse : " +  this.posX + "\n <br>";
				str += "Direction : Verticale\n <br>";
				str += "\n <br>";
				str +=  mer.affichageChoixBateau() +"\n </html>";
			}
		}
		else{
			str = "non";
		}
		return str;
	}
}
