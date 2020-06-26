package bataille.selectionBat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.*;

import bataille.modele.players.*;
import bataille.modele.*;
import bataille.vue.*;

/**
    * SelectionBateau une classe qui herite de JDialog et implement l'interface ActionListener
*/
public class SelectionBateau extends JDialog implements ActionListener, Runnable{
    
	private InformationBateau zInfo = new InformationBateau();
	private boolean sendData;
	private JLabel posXLabel,posYLabel,dirLabel;
	private JTextField posX, posY;
	private JComboBox dir;
	public JButton okBouton, quitter;
	public GamePlayer player;
	public Mer mer;
	public int taille, nb;
	public JOptionPane  jop3;
	public JFrame parent;
	public SelectionBateau(){
	}
	
	/**
		* constructeur de la classe SelectionBateau qui permet de choisir le placement d'un Bateau
		* @param parent une instance de JFrame
		* @param title une String
		* @param modal une booleen
		* @param player une instance de GamePlayer
		* @param mer une instance de Mer
		* @param taille un entier
		* @param nb un entier
    */
	public SelectionBateau(JFrame parent, String title, boolean modal,GamePlayer player, Mer mer, int taille,int nb){
		super(parent, title, modal);
		this.setSize(750, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.parent = parent;
		this.player = player;
		this.mer = mer;
		this.taille = taille;
		this.nb = nb;
		
		initComponent();
	}
	
	/**
		* Methode qui permet d'afficher les Information d'un Bateau
		* @return une InformationBateau
	*/
	
	public InformationBateau showZDialog(){
		this.sendData = false;
		this.setVisible(true);
		return this.zInfo;
	}
	
	/**
		* Methode qui initialise ce que contient le JDialog
	*/
	private void initComponent(){
		// Le X
		JPanel panPosX = new JPanel();
		panPosX.setBackground(Color.white);
		panPosX.setPreferredSize(new Dimension(300, 100));
		posX = new JTextField();
		posX.setPreferredSize(new Dimension(100, 25));
		panPosX.setBorder(BorderFactory.createTitledBorder("Emplacement en ligne du bateau"));
		posXLabel = new JLabel("Saisir une Ligne entre 1 et 10 :");
		panPosX.add(posXLabel);
		panPosX.add(posX);
		
		// Le Y 
        JPanel panPosY = new JPanel();
		panPosY.setBackground(Color.white);
		panPosY.setPreferredSize(new Dimension(300, 100));
		posY = new JTextField();
		posY.setPreferredSize(new Dimension(100, 25));
		panPosY.setBorder(BorderFactory.createTitledBorder("Emplacement en colonne du bateau"));
		posYLabel = new JLabel("Saisir une colonne entre A et J :");
		panPosY.add(posYLabel);
		panPosY.add(posY);
		
        // La Direction
		JPanel panDir = new JPanel();
		panDir.setBackground(Color.white);
		panDir.setPreferredSize(new Dimension(300, 100));
		dir = new JComboBox();
		panDir.setBorder(BorderFactory.createTitledBorder("Direction du bateau"));
		dirLabel = new JLabel("Saisissez la direction du bateau");
		dir.addItem("Horizontale");
		dir.addItem("Verticale");
		
		panDir.add(dirLabel);
		panDir.add(dir);
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panPosY);
		content.add(panPosX);
		content.add(panDir);
		
		JPanel control = new JPanel();
		this.okBouton = new JButton("OK");
		
		this.okBouton.addActionListener(this);
		this.quitter = new JButton("quitter");
		this.quitter.addActionListener(this);
		
		control.add(this.okBouton);
		control.add(this.quitter);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
	
	/**
		* Accesseur de la taille
		* @return un entier 
	*/
	
	public int getTaille(){
		return this.taille;
	}
	
	/**
		* Accesseur de la position X d'un Bateau que nous voulons placer
		* @return un entier
	*/
    public int getPosX(){
        int x = Integer.parseInt(this.posX.getText());
        return x;
	}
	
	/**
		* Accesseur de la position Y d'un Bateau que nous voulons placer
		* @return un entier
	*/
	
	public int getPosY(){
        int entier = 0;
		
        if(this.posY != null) {
			char car = this.posY.getText().charAt(0); 
            int carAscii = (int)car;
			if(97 <= carAscii && carAscii < 123){
				return carAscii - 97;
			}
			else{
				return carAscii - 65;
			}
        }
        return entier;
    }
	
	/**
		* Accesseur de la direction d'un Bateau
		* @return un entier
	*/
    public int getDir(){
        String strDir = (String)dir.getSelectedItem();
		if(strDir.equals("Horizontale")){
			return 0;
		}
        else{
			return 1;
        }
    }
	
	/**
		* Redefinition de la methode
		* @param e une instance de ActionEvent
	*/
	@Override
    public void actionPerformed(ActionEvent e) {  
        Object appuiesur = e.getSource();
        
		boolean test = false;
        if(appuiesur == okBouton){
			while(test ==false){
				try{
					int length = getTaille();
					int X = getPosX();
					int Y = getPosY();
					int direction = getDir();
					
					if(direction !=0){
						if(!mer.navirePeutEtrePosee((X-1),Y,length, direction)|| X <= 0){
							this.jop3 = new JOptionPane();
							this.jop3.showMessageDialog(null, "impossible de placer ce bateau. Reesayez !", "Erreur", JOptionPane.ERROR_MESSAGE);
							this.dispose();
							new Fenetre(this.player,this.mer,getTaille(),nb);
							break;
						}
						else{
							mer.chooseNavire(length,X-1,Y,direction);
							zInfo = new InformationBateau(length,X-1,Y,direction, this.mer);
							this.dispose();
							test = true;
						}
					}
					else{
						if(!mer.navirePeutEtrePosee((X-1),Y,length, direction)|| X <= 0){
							this.jop3 = new JOptionPane();
							this.jop3.showMessageDialog(null, "impossible de placer ce bateau. Reesayez !", "Erreur", JOptionPane.ERROR_MESSAGE);
							this.dispose();
							new Fenetre(this.player,this.mer,getTaille(),nb);
							break;
						}
						else{
							mer.chooseNavire(length,X-1,Y,direction);
							zInfo = new InformationBateau(length,X,Y,direction, this.mer);
							this.dispose();
							test = true;
						}
					}
				}
				catch(NumberFormatException marchePas){
					JOptionPane.showMessageDialog(null, "Tu as mal ecrit !!! Colonne: une lettre, Ligne: un chiffre.");
					return;
				}
			}
		}
		if(appuiesur == quitter){
			Thread thread = new Thread(new SelectionBateau());
			thread.start();
			// System.exit(0);
			this.parent.dispose();
		}
    }
	
	/**
		* Redefinition de la methode run qui nous permet de fermer la fenetre Credits au bout de 10 sec
	*/
	@Override
	public void run(){
		int milli = 1000;
		int compt = 5; //5 sec d'affichage
		Credits cred = new Credits();
		for(int i = 0; i < compt; i++){
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
