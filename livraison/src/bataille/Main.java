package bataille;

import java.util.*;
import java.lang.*;

import bataille.modele.players.*;
import bataille.vue.*;

/**
	* Main est la classe executable de la bataille, elle ouvre le jeu
*/

public class Main implements Runnable{
	
	/**
		* Methode qui permet de fermer notre fenêtre de Menu qu bout de 2 min
	*/
	public void run(){
		int milli = 1000;
		int compt = 60*2; // min affichage du menu
		MenuVue menu = new MenuVue();
		for(int i = 0; i < compt; i++){
			try{
				Thread.sleep(milli);
			}
			catch(InterruptedException e){
				System.out.println("Interruption !");
			}
		}
		menu.dispose();
	}
	
	public static void main (String[] args){
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Entrez 0 pour jouer dans le terminale et 1 pour jouer graphiquement");
		
		int choix = detectionInt();
		
		GamePlayer player = new Player();
		RandomPlayer playerRandom = new RandomPlayer(new Random());
		
		if(choix == 0){
			Orchestrateur orchestrateur = new Orchestrateur(player, playerRandom);
			orchestrateur.playGame();
		}
		else{
			Thread thread = new Thread(new Main());
			thread.start();
		}
		
		scanner.close();
	}
	
	/**
		* Methode qui nous permet de detecter si le scanner inserer est bien un entier
		* @return un entier
	*/
	public static int detectionInt(){
		Scanner sc = new Scanner(System.in);
		boolean test = false;
		int entier =  0;
		while(!test){
			try{
				entier = sc.nextInt();
				test = true;
			}
			catch (InputMismatchException e){
				System.out.print("Ce n'est pas un entier. Essayez encore: \n");
				test = false;
				sc.next();
			}
		}
		return entier;
	}

}
