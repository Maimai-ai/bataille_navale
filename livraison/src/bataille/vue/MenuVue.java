package bataille.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import bataille.modele.*;

/** 
	* MenueVue est une classe qui herite de JFrame et implemente la classe ActionListener
	* elle affiche une fenetre de menu du jeu
*/
public class MenuVue extends JFrame implements ActionListener, Runnable{
	private JButton play;
	private JButton exit;
	private JButton tuto;
	
	/**	
		* Constructeur de la fenetre de menu
	*/
	public MenuVue(){
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bataille Navale");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width =(int) screenSize.getWidth();
		int height =(int) screenSize.getHeight();
		
		setPreferredSize(new Dimension(width, height));
		
		this.setUndecorated(true);
		this.setResizable(false);
		
		PannelMenu contenantLogo = new PannelMenu();
		contenantLogo.setPreferredSize(new Dimension(width,600));
		
		this.play = new JButton("PLAY");
		this.play.setBackground(Color.WHITE);
		this.play.setForeground(Color.BLACK);
		this.play.setOpaque(true);
		this.play.addActionListener(this);
		this.play.setBounds(300,150,175,70);
		
		this.tuto = new JButton("Tutoriel");
		this.tuto.setBackground(Color.BLACK);
		this.tuto.setForeground(Color.WHITE);
		this.tuto.setOpaque(true);
		this.tuto.addActionListener(this);
		
		
		this.exit = new JButton("EXIT");
		this.exit.setBackground(Color.BLACK);
		this.exit.setForeground(Color.WHITE);
		this.exit.setOpaque(true);
		this.exit.addActionListener(this);
		
		
		JPanel PanBouton = new JPanel();
		PanBouton.setPreferredSize(new Dimension(width,100));
		PanBouton.add(this.exit);
		PanBouton.add(this.tuto);
		PanBouton.add(this.play);
		
		
		
		JPanel leLogo = new JPanel();
		leLogo.add(contenantLogo);
		
		JPanel ContourJeu = new JPanel(){
			String chemin = "images/background.jpg";
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				ImageIcon m = new ImageIcon(chemin);
				Image im = m.getImage();
				g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
			} 
		};
		
		ContourJeu.setLayout(new FlowLayout());
		
		PanBouton.setOpaque(false);
		contenantLogo.setOpaque(false);
		leLogo.setOpaque(false);
		
		ContourJeu.add(leLogo);
		ContourJeu.add(PanBouton);
		
		this.setContentPane(ContourJeu);
		
		this.pack();
		this.setVisible(true);
	}
	
	/**
		* Une methode qui lors du clique sur un bouton affiche la selection des navires
		* @param e
		*		Une instance de type ActionEvent
	*/
	public void actionPerformed(ActionEvent e){
		Object appuiesur = e.getSource();
		if(appuiesur == this.play){
			this.dispose();
			Lancement jeu = new Lancement();
			jeu.initialiser();
			
		}
		if(appuiesur == this.exit){
			this.dispose();
			Thread thread = new Thread(new MenuVue());
			thread.start();
		}
		if(appuiesur == this.tuto){
			new TutorielVue().setVisible(true);
		}
	}
	
	/**
		* Redefinition de la methode run qui nous permet de fermer la fenetre Credits au bout de 10 sec
	*/
	@Override
	public void run(){
		int milli = 1000;
		int decompt = 5; //5 sec d'affichage
		Credits cred = new Credits();
		for(int i = 0; i < decompt; i++){
			try{
				Thread.sleep(milli);
			}
			catch(InterruptedException e){
				System.out.println("Interruption !");
			}
		}
		cred.dispose();
		System.exit(0);
	}
}

  

