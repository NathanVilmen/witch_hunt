package Modele;


/**
 * Classe qui implemente l'interface Strategie. Elle redefinit les methodes de Strategie, selon le modele suivant : le joueur virtuel revele toujours une carte rumeur, qu'il soit accuse ou que ce soit sno tour.
 * @author Nathan Vilmen, Etienne Lanternier
 * @version 14/01/2022
 *
 */
public class ToujoursReveler implements Strategie {

	
	/**
	 * Methode qui definit la strategie a adopter pour le joueut virtuel, selon sa position d'accuse ou de debut de tour. Quel que soit son etat, le joueur va reveler une carte Rumeur.
	 * @param systeme : le systeme (Systeme)
	 * @param tapis : le tapis (Tapis)
	 * @param joueurAccusant : le joueur accusant (Joueur)
	 * @param joueurVirtuel : le joueur virtuel (JoueurVirtuel)
	 */
	public void executerStrategie(Systeme systeme, Tapis tapis, Joueur joueurAccusant, JoueurVirtuel joueurVirtuel) {
		//on detaille la marche a suivre pour cette strategie
		// si c'est son tour, il revele une carte Hunt!
		// s'il est accuse, il revele une carte Witch?
		System.out.println("Vous etes : "+joueurVirtuel.getCamp());
		if (joueurVirtuel.getEtat() == etatsPossibles.NONDEFINI) {
			joueurVirtuel.cartesEnMain.forEach((elt) -> {
				if(elt.estJouableHunt(systeme, joueurVirtuel, joueurAccusant, tapis)) {
					joueurVirtuel.setPeutJouer(true);
				}
			});
			
			if(joueurVirtuel.getPeutJouer()) {
				joueurVirtuel.setPeutJouer(false);
				System.out.println("Desirez vous accuser un joueur (tapez 0) ou reveler une carte rumeur, et appliquer son effet Hunt! (tapez 1)?\n");
				System.out.println("Vous avez choisi de reveler une carte rumeur.\n");
				joueurVirtuel.choisirCarteHunt(systeme, tapis, joueurAccusant);				
			}
			else {
				//s'il n'a plus de cartes rumeur a jouer 
				System.out.println("Vous n'avez plus de carte jouable dans votre situation, vous devez donc reveler votre identite.\n");
				joueurVirtuel.revelerIdentite(joueurAccusant, systeme, tapis);
			}
		}
		else if (joueurVirtuel.getEtat() == etatsPossibles.ESTACCUSE)
		{
			System.out.println("Desirez vous reveler votre identite (tapez 0) ou reveler une carte rumeur et appliquer son effet Witch? (tapez 1)\n");
			System.out.println("Vous avez choisi de reveler une carte rumeur.\n");
				//Ici, on vÈrifie qu'on peut jouer une carte rumeur, mais on ne verifie pas qu'on peut jouer la carte choisie.
			joueurVirtuel.cartesEnMain.forEach((elt) -> {
					if(elt.estJouableWitch(systeme, joueurVirtuel, joueurAccusant, tapis)) {
						//on doit utiliser un setteur vu que l'attribut peutJouer est en prive
						joueurVirtuel.setPeutJouer(true);
					}
				});
				
			//on doit utiliser un getteur vue que l'attribut peutJouer est en privé
				if(joueurVirtuel.getPeutJouer()) {
					//on doit utiliser un setteur vue que l'attribut peutJouer est en privé
					joueurVirtuel.setPeutJouer(false);
					joueurVirtuel.choisirCarteWitch(systeme, tapis, joueurAccusant);				
				}
				else {
					joueurVirtuel.choisirQuoiFaire(systeme, tapis, joueurAccusant);
				}
			}
			else
			{
				joueurVirtuel.choisirQuoiFaire(systeme, tapis, joueurAccusant);
			}
		}
		
	
	
	
	public void strategieEvilEye(Systeme systeme, int indiceJoueurDesigne, Tapis tapis, JoueurVirtuel joueurVirtuel)
	{
		int choix = 0;
		
		while(choix<1 || choix > systeme.listeJoueurs.size() || choix == indiceJoueurDesigne+1) {
			System.out.println("\nJoueur " + (indiceJoueurDesigne+1) + "\nVous avez ete designe par un joueur ayant joue la carte Evil Eye. Vous ne pouvez donc qu'accuser un joueur, et si possible pas le joueur " + (systeme.listeJoueurs.indexOf(this)+1) + ". \nQuel joueur souhaitez vous accuser?\n");
			System.out.println("\nChoix en cours...\n");
			choix = (int)(Math.random()*systeme.listeJoueurs.size());
		}
		
		if(choix != systeme.listeJoueurs.indexOf(this)+1)
		{
			joueurVirtuel.accuserJoueur(systeme, choix-1, tapis);
		}
		else {
			systeme.listeJoueurs.forEach((elt) -> {
				if(elt.getEnJeu() && elt != joueurVirtuel) {
					joueurVirtuel.setPeutAccuserAutre(true);
				}
			});
			if(joueurVirtuel.getPeutAccuserAutre()) {
				joueurVirtuel.setPeutAccuserAutre(false);
				joueurVirtuel.choisirQuoiFaireEvilEye(systeme, indiceJoueurDesigne, tapis);
			}
			else {
				joueurVirtuel.accuserJoueur(systeme, choix-1, tapis);
			}
		}
	}
	
	public void strategieCarteHunt(Systeme systeme, Tapis tapis, Joueur joueurAccusant, JoueurVirtuel joueurVirtuel)
	{
		int choix = 0;
		
		
		while(choix<1 || choix > joueurVirtuel.cartesEnMain.size() || !joueurVirtuel.cartesEnMain.get(choix-1).estJouableHunt(systeme, joueurVirtuel, joueurAccusant, tapis))
		{
				if (choix >0 && !joueurVirtuel.cartesEnMain.get(choix-1).estJouableHunt(systeme, joueurVirtuel, joueurAccusant, tapis)) {
					System.out.println("\nVous avez choisi la carte : "+joueurVirtuel.cartesEnMain.get(choix-1)+", mais elle n'est pas jouable pour l'instant. Choissisez-en une autre.\n");
				}
			System.out.println("Vos cartes:\n\n" +joueurVirtuel.cartesEnMain + "\n\n Quelle carte souhaitez vous reveler? (tapez 1 pour la premiere etc.)\n");
			choix = (int)(Math.random()*(joueurVirtuel.cartesEnMain.size()+1));
		}
		
		System.out.println("Carte choisie : "+joueurVirtuel.cartesEnMain.get(choix-1));
		joueurVirtuel.cartesRevelees.add(joueurVirtuel.cartesEnMain.get(choix-1));
		joueurVirtuel.cartesEnMain.get(choix-1).realiserEffetHuntBot(systeme, joueurVirtuel, joueurAccusant, tapis);
		
	}
	
	
	public void strategieCarteWitch(Systeme systeme, Tapis tapis, Joueur joueurAccusant, JoueurVirtuel joueurVirtuel)
	{
		int choix = 0;

		while(choix<1 || choix > joueurVirtuel.cartesEnMain.size() || !joueurVirtuel.cartesEnMain.get(choix-1).estJouableWitch(systeme, joueurVirtuel, joueurAccusant, tapis))
		{
			System.out.println("Vos cartes:\n\n" +joueurVirtuel.cartesEnMain + "\n\n Quelle carte souhaitez vous reveler? (tapez 1 pour la premiere etc.)\n");
			choix = (int)(Math.random()*(joueurVirtuel.cartesEnMain.size()+1));
		}
		System.out.println("Carte choisie : "+joueurVirtuel.cartesEnMain.get(choix-1));
		joueurVirtuel.cartesRevelees.add(joueurVirtuel.cartesEnMain.get(choix-1));
		joueurVirtuel.cartesEnMain.get(choix-1).realiserEffetWitchBot(systeme, joueurVirtuel, joueurAccusant, tapis);
	}

		
		
}
