package Modele;

import java.util.Scanner;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class TheInquisition extends CarteRumeur{
	
	Scanner scanner = new Scanner(System.in);

	public TheInquisition()
	{
		super();
	}
	
	public String toString()
	{
		return "Carte The Inquisition: \nEffet Witch: Defaussez une carte de votre main, puis prenez le prochain tour. \nEffet Hunt: Choisir le prochain joueur, puis avant son tour regardez secretement son identite.\nVous ne pouvez utiliser son effet Hunt que si vous avez �t� r�v�l� en tant que villageois.\n\n";
	}
	
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix;
		
		joueurActuel.cartesEnMain.remove(this);

		
		System.out.println("Vous devez defausser une carte : \n" + joueurActuel.cartesEnMain + "Laquelle souhaitez vous defausser? ( tapez 1 pour la premiere etc.)");
		choix = scanner.nextInt();
	
		while (choix > 5 || choix < 1 || joueurActuel.cartesEnMain.size() < choix){
			System.out.println("Vous avez entre un mauvais chiffre. Veuillez choisir une carte a defausser (1 = 1ere carte)");
		}
		tapis.defausse.add(joueurActuel.cartesEnMain.get(choix-1));
		joueurActuel.cartesEnMain.remove(choix-1);
		
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
			if(joueurActuel.cartesEnMain.get(i) instanceof TheInquisition) {
				joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
			}
		}
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
		
	}
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		
		
		//Debut Choose next player
		int choix = 0;
		while(choix < 1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1) {
			System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)");
			choix = scanner.nextInt();
		}
		joueurActuel.cartesEnMain.remove(this);
		System.out.println("Ceci est secret: l'identite de ce joueur est " + systeme.listeJoueurs.get(choix-1).getCamp() + "\n");
		systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
		//Fin choose next player
	}
	
	public void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix;
		
		joueurActuel.cartesEnMain.remove(this);

		
		System.out.println("Vous devez defausser une carte : \n" + joueurActuel.cartesEnMain + "Laquelle souhaitez vous defausser? ( tapez 1 pour la premiere etc.)");
		choix = (int)(Math.random()*joueurActuel.cartesEnMain.size()+1);

		while (choix > 5 || choix < 1 || joueurActuel.cartesEnMain.size() < choix){
			choix = (int)(Math.random()*joueurActuel.cartesEnMain.size()+1);
		}
		tapis.defausse.add(joueurActuel.cartesEnMain.get(choix-1));
		joueurActuel.cartesEnMain.remove(choix-1);
		
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
		
	}
	
	
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		
		
		//Debut Choose next player
		int choix=0;
		System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)");
		while(choix < 1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1) {
			choix = (int)(Math.random()*systeme.listeJoueurs.size()+1);
		}
		joueurActuel.cartesEnMain.remove(this);
		System.out.println("Ceci est secret: l'identite de ce joueur est " + systeme.listeJoueurs.get(choix-1).getCamp() + "\n");
		systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
		//Fin choose next player
	}
	
	
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		if(joueurActuel.cartesEnMain.size()<2) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		if(joueurActuel.getEstRevele() && joueurActuel.getCamp() == campsPossibles.VILLAGEOIS) {
			return true;
		}
		else {
			return false;
		}
	}
}
