package bataille.vue;

import javax.swing.*;
import java.awt.*;

/**
	* Credits est une classe qui herite de la classe JFrame
	* Elle affiche la fenêtre de Credit
*/
public class Credits extends JFrame{
	
	/**
		* Construction de la JFrame
	*/
	public Credits(){
		//titre de la fenetre
		this.setTitle("CREDITS");
		
		//taille de la fenetre
		this.setSize(500,500);
		
		//l'utilisateur ne peut pas agrandir la fenetre.
		this.setResizable(false);
		
		//Positionement de la fenetre au centre.
		this.setLocationRelativeTo(null);
		
		//Termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon fond = new ImageIcon("images/fin.jpg");
		
		//affichage du texte et de l'image
		JPanel contenant = new JPanel(){
			
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				Image im = fond.getImage();
				g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
				g.setFont(new Font("Arial", Font.BOLD, 15));

				g.setColor(Color.white);
				g.drawString("Copyright 2019.",350,450);
				
				g.setFont(new Font("Courier-Italic", Font.ITALIC, 15));
				g.setColor(Color.black);
				g.drawString("AZOUI Zineb",20,40);
				g.drawString("BOUDET Ma\u00eblys",20,60);
				g.drawString("LEPETIT Lucie",20,80);
				g.drawString("YANG Tatiana",20,100);

			}
		};
		
		//tous les éléments sont disposés sur ce panel
		JPanel fondLabel = new JPanel();
		
        fondLabel.add(contenant);
		
		contenant.setOpaque(false);
		contenant.setPreferredSize(new Dimension(500,500));
		
		this.setContentPane(fondLabel);
		
		this.setVisible(true);
	}       
}
