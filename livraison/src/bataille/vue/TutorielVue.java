package bataille.vue;

import javax.swing.*;
import java.awt.*;


/** 
	* TutorielVue est une classe qui herite de JFrame, une fenetre d'un tutoriel
*/
public class TutorielVue extends JFrame{
	
	/**
		* Constructeur de TutorielVue qui permet la construction de la fenetre
	*/
	public TutorielVue(){
		super();
		this.setTitle("Tutoriel");
		this.setLocation(200,0);
		this.setSize(600,600);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		//Pannel contenant les regles du jeu en un texte
		PannelTuto contenantTuto = new PannelTuto();
		contenantTuto.setPreferredSize(new Dimension(800,700));
		
		//Panel arriereplan conenant tous les élements
		JPanel Contour = new JPanel(){
			String chemin = "images/regles.jpg";
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				ImageIcon m = new ImageIcon(chemin);
				Image im = m.getImage();
				g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
			} 
		};
		
		Contour.setLayout(new BorderLayout());
		contenantTuto.setOpaque(false);
		
		//AJOUT DES REGLES A CE JPANEL
		Contour.add(contenantTuto, BorderLayout.CENTER);
		this.setContentPane(Contour);
		
		this.pack();
		this.setVisible(true);
	}
}


  

