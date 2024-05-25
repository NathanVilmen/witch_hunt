package Modele;

import java.util.Scanner;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class HookedNose extends CarteRumeur {
	Scanner scanner = new Scanner(System.in);

	public HookedNose() {
		super();
	}
	
	public String toString()
	{
		return "Carte Hooked Nose \nEffet Witch: Prenez une carte de la main du joueur qui vous a accuse, puis prenez le prochain tour. \nEffet Hunt: Choisissez le joueur suivant, et juste avant son tour prenez une carte au hasard dans sa main et ajoutez la a la votre.\n\n";
	}
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		
		while(choix<1 || choix > joueurAccusant.cartesEnMain.size()) {
			System.out.println("Vous pouvez prendre une carte du joueur qui vous a accuse. Celui-ci a les cartes suivantes\n" + joueurAccusant.cartesEnMain + "\nLaquelle souhaitez vous prendre? (tapez 1 pour la premiere etc.)\n");
			choix = scanner.nextInt();
		}
		joueurActuel.cartesEnMain.add(joueurAccusant.cartesEnMain.get(choix-1));
		joueurAccusant.cartesEnMain.remove(choix-1);
		joueurActuel.cartesEnMain.remove(this);

		System.out.println("Vous avez recupere la carte " + joueurAccusant.cartesEnMain.get(choix-1) +". Vous avez maintenant dans votre main\n" + joueurActuel.cartesEnMain);
		
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
		
	}
	
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut Choose next player
		int choix;
		System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
		choix = scanner.nextInt();
		while(choix < 1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1 || systeme.listeJoueurs.get(choix-1).cartesEnMain.size() == 0)
		{
			System.out.println("Mauvaise valeur. Veuillez reessayer.\n");
			this.realiserEffetHunt(systeme, joueurActuel, joueurAccusant, tapis);
		}
		
		joueurActuel.cartesEnMain.remove(this);

		int choixRandom = 0;

		while(choixRandom < 1 || choixRandom > systeme.listeJoueurs.get(choix-1).cartesEnMain.size())
		{
			choixRandom = (int) Math.floor(3*Math.random())+1;				
		}
			
		joueurActuel.cartesEnMain.add(systeme.listeJoueurs.get(choix-1).cartesEnMain.get(choixRandom-1));
		System.out.println("Vous avez ajoute a votre main la carte suivante :\n" + systeme.listeJoueurs.get(choix-1).cartesEnMain.get(choixRandom-1));
		systeme.listeJoueurs.get(choix-1).cartesEnMain.remove(choixRandom-1);
			
		systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
		

		//Fin choose next player
	}
	
	
	public void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		
		while(choix<1 || choix > joueurAccusant.cartesEnMain.size()) {
			System.out.println("Vous pouvez prendre une carte du joueur qui vous a accuse. Celui-ci a les cartes suivantes\n" + joueurAccusant.cartesEnMain + "\nLaquelle souhaitez vous prendre? (tapez 1 pour la premiere etc.)\n");
			choix = (int)(Math.random()*joueurAccusant.cartesEnMain.size()+1);
		}
		joueurActuel.cartesEnMain.add(joueurAccusant.cartesEnMain.get(choix-1));
		joueurAccusant.cartesEnMain.remove(choix-1);
		joueurActuel.cartesEnMain.remove(this);

		System.out.println("Vous avez recupere la carte " + joueurAccusant.cartesEnMain.get(choix-1) +". Vous avez maintenant dans votre main\n" + joueurActuel.cartesEnMain);
		
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
		
	}
		
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut Choose next player
		int choix;
		System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
		choix = (int)(Math.random()*systeme.listeJoueurs.size()+1);
		while(choix < 1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1 || systeme.listeJoueurs.get(choix-1).cartesEnMain.size() == 0)
		{
			System.out.println("Mauvaise valeur. Veuillez reessayer.\n");
			this.realiserEffetHuntBot(systeme, joueurActuel, joueurAccusant, tapis);
		}
		System.out.println("Vous avez choisi le joueur "+ choix);
		joueurActuel.cartesEnMain.remove(this);

		int choixRandom = 0;

		while(choixRandom < 1 || choixRandom > systeme.listeJoueurs.get(choix-1).cartesEnMain.size())
		{
			choixRandom = (int) Math.floor(3*Math.random())+1;				
		}
			
		joueurActuel.cartesEnMain.add(systeme.listeJoueurs.get(choix-1).cartesEnMain.get(choixRandom-1));
		System.out.println("Vous avez ajoute a votre main la carte suivante :\n" + systeme.listeJoueurs.get(choix-1).cartesEnMain.get(choixRandom-1));
		systeme.listeJoueurs.get(choix-1).cartesEnMain.remove(choixRandom-1);
			
		systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
		

		//Fin choose next player
	}
	
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		if(joueurAccusant.cartesEnMain.size()==0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
}
