package bataille.vue;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;

import bataille.modele.*;

/**
	* PanelCase est une classe qui étend un JPanel implementant un Observer: observe une case du plateau
*/
public class PanelCase extends JPanel implements Observer {
	public Case unecase;
	public Mer mer;
	
	Color bleu = new Color(27, 0, 101);
	Color rose = new Color(254, 204, 224);
	Color saumon = new Color(254, 119, 120);
	Color violet = new Color(156, 2, 146);
	Color rouge = new Color(241,3,55);
	Color vert = new Color(5,223,121);
	
	public boolean panCaseRand;
	
	
	/**
		* un constructeur de PanelCase
		* @param unecase une instance de Case
		* @param panCaseRand un booleen
	*/
	public PanelCase(Case unecase, boolean panCaseRand){
		this.unecase = unecase;
		this.mer = unecase.getMer();
		this.unecase.addObserver(this);
		this.setBackground(new Color(132,118,243,50));
		this.panCaseRand=panCaseRand;
	}
	
	/**
		* Accesseur de la Case
		* @return une Case
	*/
	public Case getCase(){
		return this.unecase;
	}
	
	/**
		* Redefinition de la methode paintComponent, et affiche les bateaux en un cercle de couleur rouge quand il est touche, sinon il affiche un cercle vert
		* et si un bateau est coule, il s'affiche en orange
		* @param g
		*		instance de Graphics
	*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = this.getWidth();
		int h = this.getHeight();
		if(this.panCaseRand){
			Bateau bat = this.unecase.getEtat();
			if(bat != null){
				if(Math.abs(bat.getList().get(0).getX() - bat.getList().get(1).getX()) == 0){
					if(bat.getTaille()==2){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0,w*2,h);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0-w,0,w*2,h);
						}
					}
					if(bat.getTaille()==3){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0,w*3,h);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0-w,0,w*3,h);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0-(w*2),0,w*3,h);
						}
					}
					if(bat.getTaille()==4){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0,w*4,h);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0-w,0,w*4,h);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0-(w*2),0,w*4,h);
						}
						if(this.unecase.getX() == bat.getList().get(3).getX() && this.unecase.getY() == bat.getList().get(3).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0-(w*3),0,w*4,h);
						}
					}
					if(bat.getTaille()==5){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0,w*5,h);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0-w,0,w*5,h);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0-(w*2),0,w*5,h);
						}
						if(this.unecase.getX() == bat.getList().get(3).getX() && this.unecase.getY() == bat.getList().get(3).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0-(w*3),0,w*5,h);
						}
						if(this.unecase.getX() == bat.getList().get(4).getX() && this.unecase.getY() == bat.getList().get(4).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0-(w*4),0,w*5,h);
						}
					}
				}
				else{
					if(bat.getTaille()==2){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0,w,h*2);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0-h,w,h*2);
						}
					}
					if(bat.getTaille()==3){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0,w,h*3);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0-h,w,h*3);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0-(h*2),w,h*3);
						}
					}
					if(bat.getTaille()==4){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0,w,h*4);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0-h,w,h*4);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0-(h*2),w,h*4);
						}
						if(this.unecase.getX() == bat.getList().get(3).getX() && this.unecase.getY() == bat.getList().get(3).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0-(h*3),w,h*4);
						}
					}
					if(bat.getTaille()==5){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0,w,h*5);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0-h,w,h*5);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0-(h*2),w,h*5);
						}
						if(this.unecase.getX() == bat.getList().get(3).getX() && this.unecase.getY() == bat.getList().get(3).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0-(h*3),w,h*5);
						}
						if(this.unecase.getX() == bat.getList().get(4).getX() && this.unecase.getY() == bat.getList().get(4).getY()){
							g.setColor(Color.ORANGE);
							g.fillOval(0,0-(h*4),w,h*5);
						}
					}
				}
			}
			if(this.unecase.getTouche()){
				if(this.unecase.getEtat() == null){
					g.setColor(vert);
					g.fillOval(20,20,w-45,h-45);
				}
				else{
					g.setColor(rouge);
					g.fillOval(20,20,w-45,h-45);
				}
			}
		}
		else{
			if(this.unecase.getCouler()){
				Bateau bat = this.unecase.getEtat();
				if(Math.abs(bat.getList().get(0).getX() - bat.getList().get(1).getX()) == 0){
					//Bateau taille 2
					if(bat.getTaille()==2){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(bleu);
							g.fillOval(0,0,w*2,h);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(bleu);
							g.fillOval(0-w,0,w*2,h);
						}
					}
					//Bateau taille 3
					if(bat.getTaille()==3){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(rose);
							g.fillOval(0,0,w*3,h);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(rose);
							g.fillOval(0-w,0,w*3,h);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(rose);
							g.fillOval(0-(w*2),0,w*3,h);
						}
					}
					// bateau taille 4
					if(bat.getTaille()==4){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(saumon);
							g.fillOval(0,0,w*4,h);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(saumon);
							g.fillOval(0-w,0,w*4,h);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(saumon);
							g.fillOval(0-(w*2),0,w*4,h);
						}
						if(this.unecase.getX() == bat.getList().get(3).getX() && this.unecase.getY() == bat.getList().get(3).getY()){
							g.setColor(saumon);
							g.fillOval(0-(w*3),0,w*4,h);
						}
					}
					//bateau taille 5
					if(bat.getTaille()==5){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(violet);
							g.fillOval(0,0,w*5,h);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(violet);
							g.fillOval(0-w,0,w*5,h);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(violet);
							g.fillOval(0-(w*2),0,w*5,h);
						}
						if(this.unecase.getX() == bat.getList().get(3).getX() && this.unecase.getY() == bat.getList().get(3).getY()){
							g.setColor(violet);
							g.fillOval(0-(w*3),0,w*5,h);
						}
						if(this.unecase.getX() == bat.getList().get(4).getX() && this.unecase.getY() == bat.getList().get(4).getY()){
							g.setColor(violet);
							g.fillOval(0-(w*4),0,w*5,h);
						}
					}
				}
				else{
					//encore bateau taille 2
					if(bat.getTaille()==2){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(bleu);
							g.fillOval(0,0,w,h*2);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(bleu);
							g.fillOval(0,0-h,w,h*2);
						}
					}
					//bateau taille 3
					if(bat.getTaille()==3){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(rose);
							g.fillOval(0,0,w,h*3);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(rose);
							g.fillOval(0,0-h,w,h*3);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(rose);
							g.fillOval(0,0-(h*2),w,h*3);
						}
					}
					
					//bateau taille 4
					if(bat.getTaille()==4){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(saumon);
							g.fillOval(0,0,w,h*4);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(saumon);
							g.fillOval(0,0-h,w,h*4);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(saumon);
							g.fillOval(0,0-(h*2),w,h*4);
						}
						if(this.unecase.getX() == bat.getList().get(3).getX() && this.unecase.getY() == bat.getList().get(3).getY()){
							g.setColor(saumon);
							g.fillOval(0,0-(h*3),w,h*4);
						}
					}
					//bateau taille 5
					if(bat.getTaille()==5){
						if(this.unecase.getX() == bat.getList().get(0).getX() && this.unecase.getY() == bat.getList().get(0).getY()){
							g.setColor(violet);
							g.fillOval(0,0,w,h*5);
						}
						if(this.unecase.getX() == bat.getList().get(1).getX() && this.unecase.getY() == bat.getList().get(1).getY()){
							g.setColor(violet);
							g.fillOval(0,0-h,w,h*5);
						}
						if(this.unecase.getX() == bat.getList().get(2).getX() && this.unecase.getY() == bat.getList().get(2).getY()){
							g.setColor(violet);
							g.fillOval(0,0-(h*2),w,h*5);
						}
						if(this.unecase.getX() == bat.getList().get(3).getX() && this.unecase.getY() == bat.getList().get(3).getY()){
							g.setColor(violet);
							g.fillOval(0,0-(h*3),w,h*5);
						}
						if(this.unecase.getX() == bat.getList().get(4).getX() && this.unecase.getY() == bat.getList().get(4).getY()){
							g.setColor(violet);
							g.fillOval(0,0-(h*4),w,h*5);
						}
					}
				}
			}
			if(this.unecase.getTouche() && !this.unecase.getCouler()){
				if(this.unecase.getEtat() == null){
					g.setColor(vert);
					g.fillOval(20,20,w-45,h-45);
				}
				else{
					g.setColor(rouge);
					g.fillOval(5,5,w-15,h-15);
				}
			}
		}
    }
    
    /**
		* Redefinition de la methode update qui repaint le PanelCase
		* @param o
		*		une instance de Observable
		* @param obj
		*		une instance de Object
	*/
    @Override
	public void update(Observable o, Object obj){
		this.repaint();
	}
}
