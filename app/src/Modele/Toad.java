package Modele;

import java.util.Scanner;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class Toad extends CarteRumeur {
	
	Scanner scanner = new Scanner(System.in);

	public Toad() {
		super();
	}
	
	public String toString()
	{
		return "Carte Toad: \nEffet Witch: Prenez le prochain tour. \nEffet Hunt: Revelez votre identite. Si vous etes une sorciere le joueur a votre gauche prend le prochain tour. Si vous etes un villageois, choisissez le joueur suivant.\n\n";
	}
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
			if(joueurActuel.cartesEnMain.get(i) instanceof Toad) {
				joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
			}
		}
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
	}
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) 
	{
		joueurActuel.revelerIdentite(joueurAccusant, systeme, tapis);
		
		if(joueurActuel.getCamp() == campsPossibles.SORCIERE) {
			
			for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
				if(joueurActuel.cartesEnMain.get(i) instanceof Toad) {
					joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
				}
			}
			if(systeme.listeJoueurs.indexOf(joueurActuel) == 0) {
				systeme.passerAuSuivant(systeme.listeJoueurs.size()-1, tapis, joueurAccusant);
			}
			else {
				systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel)-1, tapis, joueurAccusant);

			}
		}
		else {
			//Debut Choose next player
			int choix;
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
			choix = scanner.nextInt();
			for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
				if(joueurActuel.cartesEnMain.get(i) instanceof Toad) {
					joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
				}
			}
			if(choix < 1 || choix > systeme.listeJoueurs.size())
			{
				this.realiserEffetHunt(systeme, joueurActuel, joueurAccusant, tapis);
			}
			else {
				systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
			}
			//Fin choose next player
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
	
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) 
	{
		joueurActuel.revelerIdentite(joueurAccusant, systeme, tapis);
		
		if(joueurActuel.getCamp() == campsPossibles.SORCIERE) {
			joueurActuel.cartesEnMain.remove(this);
			if(systeme.listeJoueurs.indexOf(joueurActuel) == 0) {
				systeme.passerAuSuivant(systeme.listeJoueurs.size()-1, tapis, joueurAccusant);
			}
			else {
				systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel)-1, tapis, joueurAccusant);

			}
		}
		else {
			//Debut Choose next player
			int choix;
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
			choix = (int)(Math.random()*systeme.listeJoueurs.size()+1);
			joueurActuel.cartesEnMain.remove(this);
			if(choix < 1 || choix > systeme.listeJoueurs.size())
			{
				this.realiserEffetHuntBot(systeme, joueurActuel, joueurAccusant, tapis);
			}
			else {
				systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
			}
			//Fin choose next player
		}
	}
	
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
	
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
}
