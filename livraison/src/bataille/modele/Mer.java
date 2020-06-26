package bataille.modele;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

import bataille.modele.players.*;

/**
	* Mer est classe qui nous permet de creer une mer: une grille de Case
	* Dans laquelle on pourra touche un Bateau ou non 
*/
public class Mer extends Observable{
	public Case currentGrid[][];
	public GamePlayer player;
	public ArrayList<Bateau> listBateau;
	public int longueur;
	public int nbCouler;
	
	/**
		* Constructeur de Mer qui creer une Mer en fonction de sa longueur et d'un joueur
		* @param player une instance de GamePlayer
		* @param longueur un entier, qui correspond a la taille de notre grille de Case
	*/
	public Mer(GamePlayer player, int longueur){
		this.player = player;
		this.longueur = longueur;
		this.currentGrid = new Case[this.longueur][this.longueur];
		this.listBateau = new ArrayList<Bateau>();
		initialiser();
	}
	
	/**
		* Accesseur de la longueur
		* @return un entier
	*/
	public int getLigne(){
		return this.longueur;
	}
	
	/**
		* Accesseur du joueur
		* @return un joueur
	*/
	public GamePlayer getPlayer(){
		return this.player;
	}
	
	/**
		* Accesseur de la liste de Bateau dans la Mer
		* @return une liste de Bateau
	*/
	public ArrayList<Bateau> getListe(){
		return this.listBateau;
	}
	
	public Case[][] getGrid(){
		return this.currentGrid;
	}
	
	/**
		* methode initialiser qui cree une grille avec des Cases
		* @return un tableau de Case
	*/
	public Case[][] initialiser(){
		for(int i = 0; i< this.currentGrid.length; i++){
			for(int j = 0; j< this.currentGrid[i].length;j++){
				Case unecase = new Case(this, i,j);
				unecase.setEtat(null);
				this.currentGrid[i][j] = unecase;
			}
		} 
		return this.currentGrid;
	}
	
	/**
		* Methode qui permet de savoir si un navire(ou Bateau) peut etre deposer dans les Case souhaitees ou non 
		* @param x un entier qui correspond a la position X
		* @param y un entier qui correspond a la position Y
		* @param taille un entier qui correspond a taille du Bateau
		* @param dir un entier qui correspond a la direction du Bateau
		* @return un booleen
	*/
	public boolean navirePeutEtrePosee(int x, int y, int taille, int dir){
        if( x<0 || this.longueur < x || y < 0 || this.longueur <= y){
            return false;
        }
		if(dir == 0){
			if((y+taille) > this.longueur){
				return false;
			}
			for(int i = 0; i <taille; i++){
				if( this.currentGrid[x][y+i].getEtat() != null ){
					return false;
				}
			}
		}
		else{
			if((x+taille) > this.longueur){
				return false;
			}
			for(int i = 0; i <taille; i++){
				if( this.currentGrid[x+i][y].getEtat() != null ){
					return false;
				}
			}
		}
        return true;
    }
	
	/**
		* methode chooseNavire qui depose dans la mer un bateau, qui ajoute un bateau a la liste des bateaux en jeu
		* @param taille un entier
		* @param x un entier qui correspond a la ligne
		* @param y un entier qui correspond a la colonne
		* @param dir un entier qui correspond a la direction dans laquelle nous allons poser notre navire
	*/
	public void chooseNavire(int taille, int x, int y, int dir){ 
		/* dir= 0 vers la droite(horizontal) sinon vers le bas (verticale)*/
		Bateau bat = new Bateau(taille,x,y);
		int taille2 = taille;
		while(taille !=0){
			if(0 <= x && x<this.currentGrid.length){
				if(0 <= y && y< this.currentGrid[x].length){
					if(this.currentGrid[x][y].getEtat() == null ){
						this.currentGrid[x][y].setEtat(bat);
						Bateau partBateau = new Bateau(taille,x,y);
						bat.ajoutList(partBateau);
					}
				}
			}
			if(dir == 0){
				y++;
				taille--;
			}
			else{
				x++;
				taille--;
			}
		}
		this.listBateau.add(bat);
	}
	
	/**
		* methode qui permet de connaitre toutes les cases qui ne sont pas Toucher
		* @return liste de Case
	*/
	public ArrayList<Case> validMoves(){
		ArrayList<Case> list = new ArrayList<Case>();
		for(int i=0; i < this.currentGrid.length; i++){
			for(int j = 0; j <this.currentGrid[i].length; j++){
				if(!this.currentGrid[i][j].getTouche()){
					Case cas = new Case(this,i,j);
					list.add(cas);
				}
			}
		}
		return list;
	}
	
	/**
		* Methode isTerminal qui verifie si tous les bateaux de la liste des bateaux ont ete coule, et donc si le jeu est fini
		* @return un booleen
	*/
	public boolean isTerminal(){
		for(Bateau bat : this.listBateau){
			if(!bat.getCoule()){
				return false;
			}
		}
		return true;
	}
	
	/**
		* methode movePlay, permet de choisir un coup, ou cliquer pour jouer au touche-coule, notification aux observers
		* @param x l'entier qui correspond a la ligne voulu
		* @param y l'entier qui correspond a la colonne voulu
	*/
	public void movePlay(int x, int y){
		if(x<0 || x > this.longueur || y < 0 || y > this.longueur){
			throw new IllegalArgumentException("Coup invalide");
		}
		else{
			if(!this.currentGrid[x][y].getTouche()){
				if(this.currentGrid[x][y].getEtat() == null){
					this.currentGrid[x][y].setTouche(true);
				}
				else{
					this.currentGrid[x][y].setTouche(true);
					System.out.println("TOUCHER !");
					for(Bateau bat : this.listBateau){
						if(!bat.getDejaCouler()){
							if(bat.getCoule()){
								System.out.println("COULER !");
								bat.setDejaCouler(true);
							}
						}
					}
				}
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
		* methode situationToString qui renvoie la situation actuelle du jeu dans le terminale avec une grille de la mer pour chaques joueurs
		* @param affichage un booleen
		* @return une String
	*/
	public String situationToString(boolean affichage){
		String phrase="   " ;
		String colonnes[] = {"  A "," B "," C "," D "," E "," F "," G "," H "," I "," J "," K "," L "," M "," N "," O "," P "," Q "," R "," S "," T "," X "," Y "};
		for(int k = 0; k< this.longueur; k++){
			phrase += colonnes[k]; 
		}
		phrase = phrase + System.lineSeparator();
		for(int i = 0; i< this.longueur; i++){
			if(i == 9){
				phrase += " " + (i+1) + " "; 
			}
			else{
				phrase += " " + (i+1) + "  ";
			}
			if(affichage){
				for(int j = 0; j< this.longueur;j++){
					if(this.currentGrid[i][j].getTouche()){
						if(this.currentGrid[i][j].getEtat() == null){
							phrase = phrase +" ! ";
						}
						else{
							phrase = phrase +" X ";
						}
					}
					else{
						phrase = phrase +" - ";
					}
				}
			}
			else{
				for(int j = 0; j< this.longueur;j++){
					if(this.currentGrid[i][j].getEtat() == null){
						phrase = phrase +" - ";
					}
					else{
						phrase = phrase +" X ";
					}
				}
			}
			phrase = phrase + System.lineSeparator();
		}
		return phrase;
	}
	
	/**
		* methode qui permet d'afficher une grille de la Mer dans le terminal lorsque nous posons des Bateaux
		* les balises html permettent l'affichage dans un Jlabel
		* @return une String
	*/
	public String affichageChoixBateau(){
		String phrase="<html>   " ;
		// &nbsp; est la balise pour l'espace en html
        String colonnes[] = {"&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;A&nbsp;","&nbsp;B&nbsp;","&nbsp;C&nbsp;","&nbsp;D&nbsp;","&nbsp;E&nbsp;","&nbsp;F&nbsp;","&nbsp;G&nbsp;","&nbsp;H&nbsp;","&nbsp;I&nbsp;","&nbsp;J&nbsp;","&nbsp;K&nbsp;","&nbsp;L&nbsp;","&nbsp;M&nbsp;","&nbsp;N&nbsp;","&nbsp;O&nbsp;","&nbsp;P&nbsp;","&nbsp;Q&nbsp;","&nbsp;R&nbsp;","&nbsp;S&nbsp;","&nbsp;T&nbsp;"};
		for(int k = 0; k<=this.longueur; k++){
            phrase += colonnes[k]; 
        }
		phrase = phrase + System.lineSeparator() + "<br>";
        for(int i = 0; i< this.longueur; i++){
			if(i == 9){
				phrase += "&nbsp;" + (i+1) + "&nbsp;"; 
			}
			else{
				phrase += "&nbsp;" + (i+1) + "&nbsp;&nbsp;";
			}
			for(int j = 0; j< this.longueur;j++){
				if(this.currentGrid[i][j].getEtat() == null){
					phrase = phrase +"&nbsp;-&nbsp;";
				}
				else{
					phrase = phrase +"&nbsp;X&nbsp;";
				}
			}
			phrase = phrase + System.lineSeparator()+ "<br>";
		}
		phrase += "</html>";
		return phrase;
	}
	
	
}
