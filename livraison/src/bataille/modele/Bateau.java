package bataille.modele;

import java.util.ArrayList;


/**
	* Bateau est une classe qui defini un bateau, s'il est touche et coule, ainsi que sa position et sa taille
*/
public class Bateau{
	public ArrayList<Bateau> listPartBateau;
	public boolean estTouche = false;
	public int taille;
	public int x;
	public int y;
	public int nbtouche;
	public boolean dejaCouler = false;
	
	/**
		* Constructeur de Bateau qui prend une taille
		* @param taille un entier
	*/
	public Bateau(int taille){
		this.taille = taille;
		this.nbtouche = 0;
		this.listPartBateau = new ArrayList<Bateau>();
	}
	
	/**
		* Second constructeur de Bateau qui prend la position d'un Bateau
		* @param x un entier qui correspond a la colonne
		* @param y un entier qui correspond a la colonne
	*/
	public Bateau(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**	
		* Troisieme constructeur de Bateau qui prend une taille, et sa position en (x,y)
		* @param taille un entier
		* @param x un entier
		* @param y un entier
	*/
	public Bateau(int taille, int x, int y){
		this.taille = taille;
		this.x = x;
		this.y = y;
		this.nbtouche = 0;
		this.listPartBateau = new ArrayList<Bateau>();
	}
	
	/**	
		* Accesseur de la position x du Bateau
		* @return une entier
	*/
	public int getX(){
		return this.x;
	}
	
	/**
		* Accesseur de la position y du Bateau
		* @return un entier
	*/
	public int getY(){
		return this.y;
	}
	
	/**
		* Mutateur de la position x du Bateau
		* @param x un entier
	*/
	public void setX(int x){
		this.x = x;
	}
	
	/**
		* Mutateur de la position y du Bateau 
		* @param y un entier
	*/
	public void setY(int y){
		this.y = y;
	}
	
	/**
		* Accesseur getTaille, taille d'un bateau
		* @return un entier
	*/
	public int getTaille(){
		return this.taille;
	}
	/**
		* Mutateur setTaille, modifie la taille du Bateau
		* @param n un entier
	*/
	public void setTaille(int n){
		this.taille = n;
	}
	
	/**
		* methode ajoutList, qui ajoute un bateau a la liste d'un bateau
		* @param bat une instance de Bateau
	*/
	public void ajoutList(Bateau bat){
		this.listPartBateau.add(bat);
	}
	
	/**
		* Accessuer getList, la liste des parties de bateaux du Bateau
		* @return une liste de Bateau
	*/
	public ArrayList<Bateau> getList(){
		return listPartBateau;
	}
	
	/**
		* Accesseur getTouche, si le bateau est ou non touche
		* @return un booleen
	*/
	public boolean getTouche(){
		return this.estTouche;
	}
	
	/**
		* Mutateur setToucher, modifie estTouche
		* @param a un booleen
	*/
	public void setToucher(boolean a){
		if(a){
			this.nbtouche++;
		}
		this.estTouche = a;
	}
	
	/**
		* Accesseur getCoule
		* @return un booleen, si le bateau est coule
	*/
	public boolean getCoule(){
		if(this.nbtouche == this.taille){
			return true;
		}
		return false;
	}
	
	/**
		* Accesseur getDejaCouler, si le bateau a deja ete coule
		* @return un booleen
	*/
	public boolean getDejaCouler(){
		return this.dejaCouler;
	}
	
	/**
		* Mutateur setDejaCouler, modifie l'etat d'un bateau s'il a ete deja coule ou non
		* @param n un booleen
	*/
	public void setDejaCouler(boolean n){
		this.dejaCouler = n;
	}
}
