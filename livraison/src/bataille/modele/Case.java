package bataille.modele;

import java.util.Observer;
import java.util.Observable;

/**
	* Case est une classe qui est observable
*/
public class Case extends Observable{
	
	public int x;
	public int y;
	public Bateau etat = null;
	public boolean estTouche = false;
	public Mer mer;
	
	/**
		* Constructeur de Case, prenant en parametre la position d'une Case
		* @param x un entier
		* @param y un entier
	*/
	public Case(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
		* Second constructeur de Case qui prend en parametre le Mer dans laquelle il y est et ses positions
		* @param mer une instance de Mer
		* @param x un entier
		* @param y un entier
	*/
	public Case(Mer mer, int x, int y){
		super();
		this.mer = mer;
		this.x = x;
		this.y = y;
	}
	
	/**
		* Accesseur de la Mer 
		* @return une Mer
	*/
	public Mer getMer(){
		return this.mer;
	}
	
	/**
		* Accesseur getX, la position x de la case
		* @return un entier
	*/
	public int getX(){
		return this.x;
	}
	
	/**
		* Accesseur getY, la position y de la case
		* @return un entier 
	*/
	public int getY(){
		return this.y;
	}
	
	/**
		* Accesseur getEtat declare l'etat d'une Case
		* @return un Bateau 
	*/
	public Bateau getEtat(){
		return this.etat;
	}
	
	/**
		* Mutateur setEtat, change l'etat d'une case
		* @param a une instance de Bateau
	*/
	public void setEtat(Bateau a){
		this.setChanged();
		this.notifyObservers();
		this.etat = a;
	}
	
	/**
		* Accesseur si le bateau est touche ou pas
		* @return un booleen
	*/
	public boolean getTouche(){
		return this.estTouche;
	}
	
	/**
		* Mutateur qui modifie estTouche si la case est touche ou non
		* @param a un booleen
	*/
	public void setTouche(boolean a){
		this.setChanged();
		this.notifyObservers();
		this.estTouche = a;
		if(getEtat() != null){
			this.etat.setToucher(a);
		}
	}
	
	/**
		* Mutateur d'un Bateau est savoir s'il est coule ou pas
		* @return un booleen
	*/
	public boolean getCouler(){
		if(getEtat() != null){
			if(getEtat().getCoule()){
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
		* methode toString, l'emplacement sur x,y en une phrase
		* @return une String
	*/
	@Override
	public String toString(){
		return "( " + this.x + " ; " + this.y + " )" ;
	}
}
