package Modele;

import java.util.Scanner;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class BlackCat extends CarteRumeur{
	
	Scanner scanner = new Scanner(System.in);

	public BlackCat() {
	 super();
	}
	
	public String toString()
	{
		return "Carte Black Cat: \nEffet Witch: Prenez le prochain tour. \nEffet Hunt: Ajouter a votre main une carte de la defausse, puis defaussez la carte Black Cat. Prenez le prochain tour.\n\n";
	}
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
			if(joueurActuel.cartesEnMain.get(i) instanceof BlackCat) {
				joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
			}
		}
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
	}
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		
		while(choix<1 || choix > tapis.defausse.size())
		{
			System.out.println("Joueur " + (systeme.listeJoueurs.indexOf(joueurActuel)+1) + "\nChoisissez l'une des cartes suivantes presentes dans la defausse afin de la recuperer (tapez 1 pour la premiere, etc. \n" + tapis.defausse);
			choix = scanner.nextInt();
		}
		
		joueurActuel.cartesEnMain.add(tapis.defausse.get(choix-1));
		tapis.defausse.remove(choix-1);
		
		tapis.defausse.add(this);
		joueurActuel.cartesRevelees.remove(this);
		
		System.out.println("Vous avez recupere la carte " + joueurActuel.cartesEnMain.get(joueurActuel.cartesEnMain.size()-1));
		
		//Take next turn
		for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
			if(joueurActuel.cartesEnMain.get(i) instanceof BlackCat) {
				joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
			}
		}
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);

	}
	
	public void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
	}
	
	
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = -1;
		
		while(choix<0 || choix > tapis.defausse.size()-1)
		{
			System.out.println("Joueur " + (systeme.listeJoueurs.indexOf(joueurActuel)+1) + "\nChoisissez l'une des cartes suivantes presentes dans la defausse afin de la recuperer (tapez 1 pour la premiere, etc. \n" + tapis.defausse);
			choix = (int)(Math.random()*tapis.defausse.size());
		}
		
		joueurActuel.cartesEnMain.add(tapis.defausse.get(choix));
		tapis.defausse.remove(choix);
		
		tapis.defausse.add(this);
		joueurActuel.cartesRevelees.remove(this);
		
		System.out.println("Vous avez recupere la carte : " + joueurActuel.cartesEnMain.get(joueurActuel.cartesEnMain.size()-1));
		
		//Take next turn
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);

	}
	
	
	
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
	
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		if(tapis.defausse.size() == 0) {
			return false;
		}
		else {
			return true;
		}
	}

}
