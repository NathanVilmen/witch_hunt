package Modele;

import java.util.Scanner;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class AngryMob extends CarteRumeur {
	
	Scanner scanner = new Scanner(System.in);
	private boolean estJouable = false;
	private boolean joueurABroomstick = false;

	public AngryMob() {
		super();
	}
	
	public String toString()
	{
		return "Carte Angry Mob: \nEffet Witch: Prenez le prochain tour. \nEffet Hunt: Reveler l'identite d'un autre joueur ( Si c'est une sorciere, vous gagnez 2 points, si c'est un villageois vous perdez 2 points.\nL'effet Hunt n'est jouable que si vous avez ete revele en tant que villageois, et vous ne pourrez pas designer un joueur ayant revele Broomstick.\n\n";
	}
	
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
			if(joueurActuel.cartesEnMain.get(i) instanceof AngryMob) {
				joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
			}
		}
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
	}
	
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		while(choix < 1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1)
		{
			System.out.println("De quel joueur souhaitez vous reveler l'identite? (1 pour joueur 1 etc.)\n");
			choix = scanner.nextInt();
		}
		
		systeme.listeJoueurs.get(choix-1).cartesRevelees.forEach((elt) -> {
			if(elt instanceof Broomstick) {
				this.joueurABroomstick = true;
			}
		});
		
		if(this.joueurABroomstick) {
			this.joueurABroomstick = false;
			this.realiserEffetHunt(systeme, joueurActuel, joueurAccusant, tapis);
		}
		
		
		systeme.listeJoueurs.get(choix-1).revelerIdentite(joueurAccusant, systeme, tapis);
		if(systeme.listeJoueurs.get(choix-1).getCamp() == campsPossibles.SORCIERE)
		{
			joueurActuel.incrementerScore(2);
			
			//Debut take next turn
			for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
				if(joueurActuel.cartesEnMain.get(i) instanceof AngryMob) {
					joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
				}
			}
			systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
			//Fin take next turn
		}
		else {
			joueurActuel.incrementerScore(-2);
			
			//Debut take next turn
			for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
				if(joueurActuel.cartesEnMain.get(i) instanceof AngryMob) {
					joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
				}
			}
			systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
			//Fin take next turn

		}	
	}
	
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) 
	{
		return true;
	}
	
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) 
	{
		//On verifie qu'au moins un joueur n'a pas revele broomstick, et n'est pas revele.
		systeme.listeJoueurs.forEach((elt) -> {
			elt.cartesRevelees.forEach((eltCarte) -> {
				if(eltCarte instanceof Broomstick) {
					this.joueurABroomstick = true;
				}
			});
			if(!this.joueurABroomstick && !elt.getEstRevele()) {
				this.estJouable = true;
			}
			this.joueurABroomstick = false;
		});
		
		if(joueurActuel.getEstRevele() && joueurActuel.getCamp() == campsPossibles.VILLAGEOIS && this.estJouable) {
			this.estJouable = false;
			return true;
		}
		else {
			this.estJouable = false;
			return false;
		}
	
	}
	
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		while(choix < 1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1)
		{
			System.out.println("De quel joueur souhaitez vous reveler l'identite? (1 pour joueur 1 etc.)\n");
			choix = (int)(Math.random()*systeme.listeJoueurs.size());
		}
		
		systeme.listeJoueurs.get(choix).cartesRevelees.forEach((elt) -> {
			if(elt instanceof Broomstick) {
				this.joueurABroomstick = true;
			}
		});
		
		if(this.joueurABroomstick) {
			this.joueurABroomstick = false;
			this.realiserEffetHuntBot(systeme, joueurActuel, joueurAccusant, tapis);
		}
		
		
		systeme.listeJoueurs.get(choix).revelerIdentite(joueurAccusant, systeme, tapis);
		if(systeme.listeJoueurs.get(choix).getCamp() == campsPossibles.SORCIERE)
		{
			joueurActuel.incrementerScore(2);
			
			//Debut take next turn
			joueurActuel.cartesEnMain.remove(this);
			systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
			//Fin take next turn
		}
		else {
			joueurActuel.incrementerScore(-2);
			
			joueurActuel.cartesEnMain.remove(this);
			systeme.passerAuSuivant(choix, tapis, joueurAccusant);
		}	
	}
	
	public void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		
		//Fin take next turn
	}
	
	
}
