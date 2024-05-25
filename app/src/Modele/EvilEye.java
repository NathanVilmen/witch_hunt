package Modele;

import java.util.Scanner;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class EvilEye extends CarteRumeur{
	
	Scanner scanner = new Scanner(System.in);

	public EvilEye() {
		super();
	}
	
	public String toString()
	{
		return "Carte Evil Eye: \nEffet Witch: Choisissez le joueur suivant, et au tour suivant, ils doivent accuser un autre joueur que vous si c'est possible. \nEffet Hunt: Choisissez le joueur suivant, et au tour suivant, ils doivent accuser un autre joueur que vous si c'est possible.\n\n";
	}
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		//D�but Choose next player
		int choix = 0;
		while(choix<1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1)
		{
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
			choix = scanner.nextInt();
		}
		System.out.println("Vous avez choisi le joueur "+ choix);
		joueurActuel.cartesEnMain.remove(this);
		joueurActuel.choisirQuoiFaireEvilEye(systeme, choix-1, tapis);


		//Fin choose next player
	}
	
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		
		//D�but Choose next player
		int choix = 0;
		while(choix<1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1)
		{
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
			choix = scanner.nextInt();
		}
		System.out.println("Vous avez choisi le joueur "+ choix);
		joueurActuel.cartesEnMain.remove(this);
		joueurActuel.choisirQuoiFaireEvilEye(systeme, choix-1, tapis);


		//Fin choose next player
	}
	
	
	public void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		//D�but Choose next player
		int choix = 0;
		while(choix<1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1)
		{
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
			choix = (int)(Math.random()*systeme.listeJoueurs.size()+1);
		}
		System.out.println("Vous avez choisi le joueur "+ choix);
		joueurActuel.cartesEnMain.remove(this);
		joueurActuel.choisirQuoiFaireEvilEye(systeme, choix-1, tapis);


		//Fin choose next player
	}
	
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		this.realiserEffetWitchBot(systeme, joueurActuel, joueurAccusant, tapis);
	}
	
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
	
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
}
