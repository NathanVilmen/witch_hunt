package Modele;

import java.util.Scanner;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class PointedHat extends CarteRumeur{
	
	Scanner scanner = new Scanner(System.in);

	
	public PointedHat() {
		super();
	}
	
	public String toString()
	{
		return " Carte Pointed Hat: \nEffet Witch: Prenez une de vos propres cartes rumeurs defaussees dans votre main puis prenez le prochain tour. \nEffet Hunt: Prenez une de vos propres cartes rumeurs defaussees dans votre main, puis choisissez le joueur suivant.\nIl faut que vous ayez au moins une carte revelee pour jouer cette carte.\n\n";
	}
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		joueurActuel.cartesRevelees.remove(this);
		
		while(choix<1 || choix>joueurActuel.cartesRevelees.size()) {
			System.out.println("Vos cartes revelees : \n" + joueurActuel.cartesRevelees + "\nQuelle carte souhaitez vous recuperer dans votre main?\n");
			choix = scanner.nextInt();
		}
		
		joueurActuel.cartesEnMain.add(joueurActuel.cartesRevelees.get(choix-1));
		System.out.println("Vous avez recupere la carte " + choix + ". Vous avez maintenant dans votre main\n" + joueurActuel.cartesEnMain);
		joueurActuel.cartesRevelees.remove(0);
		joueurActuel.cartesRevelees.add(this);

		
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
			if(joueurActuel.cartesEnMain.get(i) instanceof PointedHat) {
				joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
			}
		}
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
		
		
		
	}
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		joueurActuel.cartesRevelees.remove(this);
		
		while(choix<1 || choix>joueurActuel.cartesRevelees.size()) {
			System.out.println("Vos cartes revelees : \n" + joueurActuel.cartesRevelees + "\nQuelle carte souhaitez vous recuperer dans votre main?\n");
			choix = scanner.nextInt();
		}
		
		joueurActuel.cartesEnMain.add(joueurActuel.cartesRevelees.get(choix-1));
		System.out.println("Vous avez recupere la carte " + choix + ". Vous avez maintenant dans votre main\n" + joueurActuel.cartesEnMain);
		joueurActuel.cartesRevelees.remove(0);
		joueurActuel.cartesRevelees.add(this);

		
		//Debut Choose next player
		int choixPlayer = 0;
		
		while(choixPlayer<1 || choixPlayer > systeme.listeJoueurs.size()) {
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
			choixPlayer = scanner.nextInt();

		}
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(choixPlayer-1, tapis, joueurAccusant);	
		//Fin choose next player
	}
	
	
	public void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		joueurActuel.cartesRevelees.remove(this);
		
		while(choix<1 || choix>joueurActuel.cartesRevelees.size()) {
			System.out.println("Vos cartes revelees : \n" + joueurActuel.cartesRevelees + "\nQuelle carte souhaitez vous recuperer dans votre main?\n");
			choix = (int)(Math.random()*joueurActuel.cartesRevelees.size()+1);
		}
				
		joueurActuel.cartesEnMain.add(joueurActuel.cartesRevelees.get(choix-1));
		System.out.println("Vous avez recupere la carte " + joueurActuel.cartesRevelees.get(choix-1) + ". Vous avez maintenant dans votre main\n" + joueurActuel.cartesEnMain);
		joueurActuel.cartesRevelees.remove(0);
		joueurActuel.cartesRevelees.add(this);

		
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
		
	}
	
	
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		joueurActuel.cartesRevelees.remove(this);
		
		while(choix<1 || choix>joueurActuel.cartesRevelees.size()) {
			System.out.println("Vos cartes revelees : \n" + joueurActuel.cartesRevelees + "\nQuelle carte souhaitez vous recuperer dans votre main?\n");
			choix = (int)(Math.random()*joueurActuel.cartesRevelees.size()+1);
		}
				
		joueurActuel.cartesEnMain.add(joueurActuel.cartesRevelees.get(choix-1));
		System.out.println("Vous avez recupere la carte " + joueurActuel.cartesRevelees.get(choix-1) + ". Vous avez maintenant dans votre main\n" + joueurActuel.cartesEnMain);
		joueurActuel.cartesRevelees.remove(0);
		joueurActuel.cartesRevelees.add(this);

		
		//Debut Choose next player
		int choixPlayer = 0;
		
		while(choixPlayer<1 || choixPlayer > systeme.listeJoueurs.size()) {
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
			choixPlayer = (int)(Math.random()*systeme.listeJoueurs.size()+1);

		}
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(choixPlayer-1, tapis, joueurAccusant);	
		//Fin choose next player
	}
	
	
	
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		if(joueurActuel.cartesRevelees.size()>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		if(joueurActuel.cartesRevelees.size()>0) {
			return true;
		}
		else {
			return false;
		}
	}
}
