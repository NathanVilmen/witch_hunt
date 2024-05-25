package Modele;

import java.util.Scanner;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class PetNewt extends CarteRumeur{
	
	Scanner scanner = new Scanner(System.in);
	
	private boolean aDesCartesRevelees = false;

	public PetNewt() {
		super();
	}
	
	public String toString()
	{
		return "Carte Pet Newt: \nEffet Witch: Prenez le prochain tour. \nEffet Hunt: Prenez une carte rumeur revelee de n'importe quel autre joueur et mettez la dans votre main, puis choisissez le joueur suivant.\n\n";
	}
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
			if(joueurActuel.cartesEnMain.get(i) instanceof PetNewt) {
				joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
			}
		}
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
	}
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		int choix = 0;
		
		while(choix<1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1)
		{
			System.out.println("De quel joueur souhaitez vous prendre une carte revelee?\n");
			choix = scanner.nextInt();
		}
		
		System.out.println("Vous avez choisi le joueur :"+systeme.listeJoueurs.get(choix-1));
		
		int choixCarte = 0;
		 
		while(choixCarte <1 || choixCarte > systeme.listeJoueurs.get(choix-1).cartesRevelees.size())
		{
			System.out.println("Quel carte desirez vous recuperer dans votre main?\n" + systeme.listeJoueurs.get(choix-1).cartesRevelees);
			choixCarte = scanner.nextInt();
		}
		
		System.out.println("Vous avez choisi la carte :"+systeme.listeJoueurs.get(choix-1).cartesRevelees.get(choixCarte-1));
		 
		joueurActuel.cartesEnMain.add(systeme.listeJoueurs.get(choix-1).cartesRevelees.get(choixCarte-1));
		systeme.listeJoueurs.get(choix-1).cartesRevelees.remove(choixCarte-1);
		 
		//Debut Choose next player
		int choixPlayer = 0;
		
		while(choixPlayer<1 || choixPlayer > systeme.listeJoueurs.size()) {
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
			choixPlayer = scanner.nextInt();
		}
		
		System.out.println("Joueur suivant : "+systeme.listeJoueurs.get(choixPlayer-1));
		
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(choixPlayer-1, tapis, joueurAccusant);	
		//Fin choose next player
	}
	
	public void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
	}
	
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		int choix = 0;
		
		while(choix<1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1)
		{
			System.out.println("De quel joueur souhaitez vous prendre une carte revelee?\n");
			choix = (int)(Math.random()*systeme.listeJoueurs.size()+1);
		}
		
		System.out.println("Vous avez choisi le joueur :"+systeme.listeJoueurs.get(choix-1));
		
		int choixCarte = 0;
		 
		while(choixCarte <1 || choixCarte > systeme.listeJoueurs.get(choix-1).cartesRevelees.size())
		{
			System.out.println("Quel carte desirez vous recuperer dans votre main?\nVoici la liste :\n" + systeme.listeJoueurs.get(choix-1).cartesRevelees);
			choixCarte = (int)(Math.random()*systeme.listeJoueurs.get(choix-1).cartesRevelees.size()+1);

		}
		
		System.out.println("Vous avez choisi la carte :"+systeme.listeJoueurs.get(choix-1).cartesRevelees.get(choixCarte-1));
		
		joueurActuel.cartesEnMain.add(systeme.listeJoueurs.get(choix-1).cartesRevelees.get(choixCarte-1));
		systeme.listeJoueurs.get(choix-1).cartesRevelees.remove(choixCarte-1);
		 
		//Debut Choose next player
		int choixPlayer = 0;
		
		while(choixPlayer<1 || choixPlayer > systeme.listeJoueurs.size()) {
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
			choixPlayer = (int)(Math.random()*systeme.listeJoueurs.size()+1);

		}
		
		System.out.println("Joueur suivant : "+systeme.listeJoueurs.get(choixPlayer-1));
		
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(choixPlayer-1, tapis, joueurAccusant);	
		//Fin choose next player
	}
	
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
	
	
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		systeme.listeJoueurs.forEach((elt) -> {
			if(elt.cartesRevelees.size()!=0 && elt != joueurActuel) {
				this.aDesCartesRevelees = true;
			}
		});
		if(this.aDesCartesRevelees) {
			this.aDesCartesRevelees = true;
			return true;
		}
		else {
			return false;
		}
	}
}
