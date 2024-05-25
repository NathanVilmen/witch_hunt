package Modele;

import java.util.Scanner;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class Cauldron extends CarteRumeur {
	Scanner scanner = new Scanner(System.in);

	
	
	public Cauldron() {
		super();
	}
	
	public String toString()
	{
		return "Carte Cauldron: \nEffet Witch: Le joueur qui vous a accuse defausse une carte au hasard de sa main, puis vous prenez le prochain tour. \nEffet Hunt: Revelez votre identite. Si vous etes une sorciere, le joueur a votre gauche prend le prochain tour. Si vous etes un villageois, vous choisissez le prochain joueur.\n\n";
	}
	
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
			
		int choix = (int) Math.floor(4*Math.random()+1);
		while(joueurAccusant.cartesEnMain.size() < choix){
			choix = (int) Math.floor(4*Math.random()+1);
		}
		
		tapis.defausse.add(joueurAccusant.cartesEnMain.get(choix-1));
		System.out.println("Le joueur qui vous a accuse a defausse la carte " + joueurAccusant.cartesEnMain.get(choix-1));
		joueurAccusant.cartesEnMain.remove(choix-1);
						
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
			if(joueurActuel.cartesEnMain.get(i) instanceof Cauldron) {
				joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
			}
		}
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
	}
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		System.out.println("Votre identite est " + joueurActuel.getCamp());
		joueurActuel.setEstRevele(true);
		systeme.checkFinDeRound(tapis);
		
		for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
			if(joueurActuel.cartesEnMain.get(i) instanceof Cauldron) {
				joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
			}
		}
		
		if(joueurActuel.getCamp() == campsPossibles.SORCIERE) {
			if(systeme.listeJoueurs.indexOf(joueurActuel) == 0) {
				systeme.passerAuSuivant(systeme.listeJoueurs.size()-1, tapis, joueurAccusant);
				
			}
			else {
				systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel)-1, tapis, joueurAccusant);
			}
		}
		else {
			//Debut Choose next player
			int choix = 0;
			while(choix<1 || choix > systeme.listeJoueurs.size()) {
				System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
				choix = scanner.nextInt();
			}
			systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
			//Fin choose next player
		}
	}
	
	
	public void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		this.realiserEffetWitch(systeme, joueurActuel, joueurAccusant, tapis);
	}
	
	
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		System.out.println("Votre identite est " + joueurActuel.getCamp());
		joueurActuel.setEstRevele(true);
		systeme.checkFinDeRound(tapis);
		joueurActuel.cartesEnMain.remove(this);
		
		if(joueurActuel.getCamp() == campsPossibles.SORCIERE) {
			if(systeme.listeJoueurs.indexOf(joueurActuel) == 0) {
				systeme.passerAuSuivant(systeme.listeJoueurs.size()-1, tapis, joueurAccusant);
			}
			else {
				systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel)-1, tapis, joueurAccusant);

			}
		}
		else {
			int choix = 0;
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
			while(choix<1 || choix > systeme.listeJoueurs.size()) {
				choix = (int)(Math.random()*systeme.listeJoueurs.size());
			}
			systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
			//Fin choose next player

		}
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
