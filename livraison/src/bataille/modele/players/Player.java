package bataille.modele.players;

import java.util.Scanner;
import java.util.InputMismatchException;

import bataille.modele.*;

/**
	* Player est une classe qui implemente l'interface GamePlayer
	* Elle nous permet de differencier un joueur humain d'un joueur aleatoire
*/
public class Player implements GamePlayer {
	public Mer mer;
	
	/**
		* Constructeur de Player qui ne prend rien en parametre
	*/
	public Player(){
		this.mer = mer;
	}
	
	/**
		* Second sonctructeur de Player qui prend en parametre une Mer
		* @param mer une instance de Mer
	*/
	public Player(Mer mer){
		this.mer = mer;
	}
	
	/**
		* Methode qui coverti un caractère en entier, que cela soit une minuscule ou une majuscule
		* @param car
		*		un String
		* @return un entier
	*/
    public int stringToInt(String car){
		if(car != null) {
			char caractere = car.charAt(0); 
			int entier = (int)caractere;
			// Si le caractère insere est une minuscule
			if(97 <= entier && entier < 123){
				return entier - 97;
			}
			else{
				return entier - 65;
			}
		}
		return -1;
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
		* Mutateur setMer, qui modifie la mer du Player
		* @param mer instance de Mer
	*/
	@Override
	public void setMer(Mer mer){
		this.mer = mer;
	}
	
	/**
		* Methode qui nous permet de vereifier si, ce que entre l'utilisateur, est bien un entier ou non
		* @return un entier
	*/
	public int detectionInt(){
		Scanner sc = new Scanner(System.in);
		boolean test = false;
		int entier =  0;
		while(!test){
			try{
				entier = sc.nextInt();
				test = true;
			}
			catch (InputMismatchException e){
				System.out.print("Ce n'est pas un entier. Essayez encore: \n");
				test = false;
				sc.next();
			}
			if(entier > 10){
				test = false;
			}
		}
		return entier;
	}
	
	/**
		* Methode qui nous permet de vereifier si, ce que entre l'utilisateur, est bien une String
		* @return une String
	*/
	public String detectionString(){
		Scanner sc = new Scanner(System.in);
		boolean test = false;
		String string = "";
		while(!test){
			try{
				string = sc.nextLine();
				test = true;
			}
			catch (InputMismatchException e){
				System.out.print("Ce n'est pas une String. Essayez encore: \n");
				test = false;
				sc.next();
			}
			if(string == ""){
				test = false;
			}
		}
		return string;
	}
	
	/**
		* methode afficheBateau, qui affiche dans le terminal le choix de l'emplacement du Bateau
		* @param mer
		* 		une instance de Mer
		* @param taille
		*		une instance de taille
	*/
	@Override
	public void afficheBateau(Mer mer, int taille){
		Scanner scan = new Scanner(System.in);
		boolean test = false;
		while (!test){
			try{
				System.out.println("Entrez la colonne : ");
				String str = detectionString();
				int colonne = stringToInt(str);
				
				System.out.println("Entrez la ligne ou vous voulez le mettre : ");
				int ligne = detectionInt();
				System.out.println("entrer la direction de votre bateau (0 ou pour horizontale, 1 pour verticale.):");
				int dir = detectionInt();
				
				if(dir != 0 ){
					if(!mer.navirePeutEtrePosee((ligne-1),colonne,taille, dir)|| ligne == 0){
						System.out.println("impossible de placer ce bateau. Reessayez \n ");
					}
					else{
						ligne = ligne-1;
						mer.chooseNavire(taille,ligne,colonne,dir);
						test = true;
					}
				}else{
					if(!mer.navirePeutEtrePosee((ligne-1),colonne,taille, dir) || ligne == 0){
						System.out.println("impossible de placer ce bateau. Reessayez  \n");
					}else{
						ligne = ligne-1;
						mer.chooseNavire(taille,ligne,colonne,dir);
						test = true;
					}
				}
			}
			catch(NumberFormatException marchePas){
				System.out.print("votre bateau : ");
			}
		}
	}
	
	
	/**
		* Redefinition de la methode chooseMove()
		* @param mer une instance de Mer
		* @return une case
		* 		qui corresponnd a la Case que le Player veut jouer
	*/
	@Override
	public Case chooseMove(Mer mer){
		Scanner scan = new Scanner(System.in);

		boolean bool=false;
		int colonne = 0;
		int ligne = 0;
		while (bool==false){
			try{
				System.out.println("Entrez la colonne: ");
				String str = detectionString();
				colonne = stringToInt(str);
				
				System.out.println("Entrez la ligne: ");
				ligne = detectionInt();
				
				if(ligne < 0 || ligne > mer.longueur ||  colonne < 0 || colonne > mer.longueur || ligne == 0 || mer.getGrid()[ligne-1][colonne].getTouche()){
					System.out.println("Le coup n'est pas valide, inserez de nouveau \n");
				}
				else{
					ligne = ligne - 1;
					mer.movePlay(ligne,colonne);
					bool = true;
				}
			}
			catch(NumberFormatException marchePas){
				System.out.print("Votre coup : ");
			}
		}
		Case c = new Case(ligne, colonne);
		return c;
	}
}
