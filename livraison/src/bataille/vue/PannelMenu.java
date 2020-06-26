package bataille.vue;

import javax.swing.*;
import java.awt.*;

/**
	* PannelMenu est une classe qui herite de la classe JPanel, elle affiche un logo 
*/
public class PannelMenu extends JPanel{
	ImageIcon logo = new ImageIcon("images/logo.png");
	
	/**
		* Redefinition de la methode paintComponent
		* @param g
		*		instance de Graphics
	*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image im = logo.getImage();
		g.drawImage(im, 250,5, this);
	}
}
