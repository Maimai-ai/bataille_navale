package bataille.vue;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
/**
	* PannelTuto est une classe qui herite de la classe JPanel
*/
public class PannelTuto extends JPanel{
	ImageIcon logo = new ImageIcon("images/logo.png");
	ImageIcon apercu = new ImageIcon("images/apercu.png");
	
	/**
		* Redefinition de la methode paintComponent, qui affiche les regles du jeu
		* @param g
		*		instance de Graphics
	*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image im = logo.getImage();
		g.drawImage(im, 0,0, this);
		
		g.setColor(Color.WHITE);
		g.drawRect(45,115,710,560);
		g.setColor(Color.BLACK);
		g.fillRect(50,120,700,550);
		
		Image imapercu = apercu.getImage();
		g.drawImage(imapercu, 540,160, this);
		
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("Principe du jeu.", 70, 140);
		
		g.drawLine(45, 156, 750, 156);
		
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Tu poss\u00e8de une flotte de 5 navires.", 70, 180);
		
		g.drawString("Ton adversaire aussi.", 70, 196);

		g.drawString("Choisis 5 navires, en donnant pour chacun:", 70, 220);
		g.drawString("- leurs longueurs,", 80, 238);
		g.drawString("- o\u00f9 les placer sur la grille en donnant la ligne et la colonne,", 80, 254);
		g.drawString("- et dans quelle direction tu les veux !", 80, 270);

		g.drawString("Pour les colonnes, entre une lettre majuscule comprise entre A et J.", 70, 300);
		g.drawString("Pour les lignes, entre un chiffre compris entre 1 et 10 !", 70, 316);

		g.drawString("Pour la direction, tu as deux choix: ", 70, 340);
		g.drawString("- horizontalement : tu entres le chiffre 0.", 70, 360);
		g.drawString("- verticalement: tu entres le chiffre 1.", 70, 376);

		g.drawLine(45, 395, 750, 395);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("Ton But:", 70, 415);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Etre le premier \u00e0 couler tous les bateaux de l'adversaire !", 70, 441);
		g.drawString("Tu es \u00e0 gauche, ton adversaire \u00e0 droite", 70, 461);
		g.drawString("tu cliques sur la case o\u00f9 tu penses que le bateau de l'adversaire se cache !", 70, 477);
		g.setColor(Color.GREEN);
		g.drawString("Si c'est VERT: tu ne l'as pas touch\u00e9 ! Mince ! R\u00e9\u00e9ssais !!", 70, 505);
		g.setColor(Color.RED);
		g.drawString("Si c'est ROUGE: bravo ! Tu l'a eu !", 70, 525);
		g.setColor(Color.YELLOW);
		g.drawString("Si tes cibles deviennent jaunes: BRAVOO ENCORE UNE FOIS!", 70, 545);
		g.setColor(Color.WHITE);
		g.drawString("Tu as coul\u00e9 un des bateaux de ces c\u00e9l\u00e9rats!", 70, 567);
		g.setFont(new Font("Arial", Font.BOLD, 17));
		g.drawString("Si tu r\u00e9ussi \u00e0 couler tous les bateaux de ton adversaire : TU GAGNERAS !!!", 70, 600);
	}
}




