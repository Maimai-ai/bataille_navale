package bataille.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.Observable;
import javax.swing.*;
import java.awt.*;

import bataille.modele.*;
import bataille.modele.players.*;
import bataille.controleur.*;

/**
	* MerGUI est une classe qui herite de la classe JFrame et de l'interface Observer et de ActionListener
	* Elle observe les deux Mers et affiche une fenetre qui affiche les deux mers et les boutons
*/
public class MerGUI extends JFrame implements Observer, ActionListener, Runnable{
	public Mer mer;
	public Mer merRand;
	public GamePlayer player;
	public RandomPlayer playerRand;
	public MerVue merVue;
	public MerVueRand merVueRand;
	
	private JButton exit;
	private JButton reset;
	private JButton menu;
	public int width;
	public int height;
	
	public MerGUI(){
	}
	/**
		* Constructeur de MerGUI, qui construit la fenetre de Jeu
		* @param player une instance de GamePlayer
		* @param playerRand une instance de GamePlayer
	*/
	public MerGUI(GamePlayer player, RandomPlayer playerRand){
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bataille Navale");
		this.setLocation(0,0);
		
		//Obtenir la taille de l'écran
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.width =(int) screenSize.getWidth();
		this.height =(int) screenSize.getHeight();
		
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		//plein ecran
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//pas de barre en haut
		this.setUndecorated(true);
		
		//Creation des boutons
		this.reset = new JButton("RESET");
		this.exit = new JButton("EXIT");
		this.menu = new JButton("MENU");
		
		//Couleur de fond des boutons
		this.reset.setBackground(Color.BLACK);
		this.exit.setBackground(Color.BLACK);
		this.menu.setBackground(Color.BLACK);
		
		//Couleur texte des boutons
		this.reset.setForeground(new Color(157,231,122));
		this.exit.setForeground(new Color(255,53,53));
		this.menu.setForeground(new Color(255,53,53));
		
		//Ajout des ActionListener aux boutons pour faire l'action correspondante
		this.reset.addActionListener(this);
		this.exit.addActionListener(this);
		this.menu.addActionListener(this);
		
		//Préparation des élements du jeu

		this.player = player;
		this.playerRand = playerRand;
		
		this.mer = player.getMer();
		this.merRand = playerRand.getMer();
		
		this.merVue = new MerVue(this.player, this.playerRand);
		this.merVueRand = new MerVueRand(merRand);
		
		
		
		
		//set Opaque utilisé pour la transparence: on voit le fond
		this.merVue.setOpaque(false);
		this.merVueRand.setOpaque(false);
		
		//MerGui observe le modèle
		mer.addObserver(this);
		merRand.addObserver(this);
		
		//Eléments pour disposer la fenetre
		// 1) Le fenetrefond 2)Le contour 3) les jeux et les bordures ainsi que les boutons dans le contour
		JPanel contour = new JPanel();
		contour.setSize(width,height);
		
		JPanel lesjeux = new JPanel();
		
		lesjeux.setLayout(new BorderLayout());
		lesjeux.setOpaque(false); 
		lesjeux.setSize(new Dimension(width-80,height-90));
		
		JPanel est = new JPanel();
		est.setPreferredSize(new Dimension(40,height-40));
		est.setOpaque(false);
		
		JPanel ouest = new JPanel();
		ouest.setPreferredSize(new Dimension(40,height-40));
		ouest.setOpaque(false);
		
		JPanel nord = new JPanel();
		nord.setPreferredSize(new Dimension(width-40,40));
		nord.setOpaque(false);
		
		JPanel sud = new JPanel();
		sud.setSize(width-40,40);
		
		sud.setOpaque(false);
		
		nord.add(this.exit);
		this.exit.setFocusable(false); //pour permettre au focus de se concentrer en priorité sur le plateau de jeu
		
		nord.add(this.reset);
		this.reset.setFocusable(false);
		
		nord.add(this.menu);
		this.menu.setFocusable(false);
		
		contour.setOpaque(false);
		
		//division du panel contour en un BorderLayout
		contour.setLayout(new BorderLayout());
		contour.add(lesjeux, BorderLayout.CENTER);
		contour.add(nord, BorderLayout.NORTH);
		contour.add(est, BorderLayout.EAST);
		contour.add(ouest, BorderLayout.WEST);
		contour.add(sud, BorderLayout.SOUTH);
		
		this.merVue.setPreferredSize(new Dimension((lesjeux.getWidth()/2)-10,lesjeux.getHeight()));
		this.merVueRand.setPreferredSize(new Dimension((lesjeux.getWidth()/2)-10,lesjeux.getHeight()));
		
		lesjeux.add(this.merVue, BorderLayout.WEST);
		lesjeux.add(this.merVueRand, BorderLayout.EAST);
		
		//pannel d'arriere plan, contenant tus les éléments sur lui
		JPanel fenetrefond = new JPanel(){
			String chemin = "images/background.jpg";
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				ImageIcon m = new ImageIcon(chemin);
				Image im = m.getImage();
				g.drawImage(im,0,0,width,height,this);
			} 
		};
		
		fenetrefond.setSize(width, height);
		fenetrefond.setLayout(new FlowLayout());
		fenetrefond.add(contour);
		
		this.setContentPane(fenetrefond);
		this.pack();
		//visibilité de la fenetre
		this.setVisible(true);
	}
	
	/**
		* Methode qui fait l'action lors du clic sur un des bouton exit, reset ou menu
		* @param e 
		* 		une instance de ActionEvent
	*/
	public void actionPerformed(ActionEvent e){
		Object appuiesur = e.getSource();
		if (appuiesur == this.exit){
			this.dispose();
			Thread test = new Thread(new MerGUI());
			test.start();
		}
		else if (appuiesur == this.reset){
			Lancement nouveaujeu = new Lancement();
			nouveaujeu.initialiser();
			this.dispose();
		}
		else if (appuiesur == this.menu){
			MenuVue retourmenu = new MenuVue();
			this.dispose();
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
	
	/**
		* Redefinition de la methode update qui repaint le MerGUI
		* @param o
		*		une instance de Observable
		* @param arg
		*		une instance de Object
	*/
	@Override
	public void update(Observable o, Object arg) {
		if(!this.mer.isTerminal() && !this.merRand.isTerminal()){
			this.merVue.repaint();
			this.merVueRand.repaint();
		}
		else if(this.mer.isTerminal()){
			//affichage d'un jdialogue si le joueur a gagné de même s'il a perdu en dessous.
			Win afficheGagnant = new Win(this, "BRAVO" , "Wouaaah tu as d\u00e9truit la flotte de ton adversaire! \n A nous le tresors ! ","images/tresor.png");
			afficheGagnant.setSize(600,600);
			
		}
		else{
			Win affichePerdant = new Win(this, "GAME OVER" , "Mince, les pirates vous ont fait couler ... !","images/pirate.png");
			affichePerdant.setSize(600,600);

		}
    }
}
