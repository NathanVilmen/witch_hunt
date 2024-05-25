package Modele;

import java.util.*;

/**
 * Classe qui implemente l'interface Strategie. Elle redefinit les methodes de Strategie, selon le modele suivant : le joueur virtuel accuse un autre joueur quand c'est son tour, et revele son identite quand il est accuse.
 * @author Nathan Vilmen, Etienne Lanternier
 * @version 14/01/2022
 */
public class AccuserEtReveler implements Strategie{
	
	public void executerStrategie(Systeme systeme, Tapis tapis, Joueur joueurAccusant, JoueurVirtuel joueurVirtuel) {
		//On détaille la marche à suivre pour cette stratégie
		
		
		//Si c'est son tour : accuser un joueur aléatoire
		//S'il est accusé : reveler sa carte identite
		//System.out.println("TEST2");
				
		int indiceJoueurAccuse;

		System.out.println("Vous etes : "+joueurVirtuel.getCamp());
		if(joueurVirtuel.getEtat() == etatsPossibles.NONDEFINI)
		{
			System.out.println("Désirez vous accuser un joueur (tapez 0) ou révéler une carte rumeur, et appliquer son effet Hunt! (tapez 1)?\n");
			System.out.println("Vous avez choisi d'accuser un joueur.\n");
			System.out.println("Choix du joueur a accuser...");
			indiceJoueurAccuse = (int)(Math.random()*systeme.listeJoueurs.size());
			while(indiceJoueurAccuse == systeme.listeJoueurs.indexOf(joueurVirtuel) || indiceJoueurAccuse >= systeme.listeJoueurs.size() || indiceJoueurAccuse<0 || systeme.listeJoueurs.get(indiceJoueurAccuse).getEstRevele())
			{
				//Soit vous vous etes accuse vous, soit vous avez saisi un nombre qui ne correspond a aucun joueur, soit vous avez accuse un joueur qui est deja revele
				indiceJoueurAccuse = (int)(Math.random()*systeme.listeJoueurs.size());
			}
			
			System.out.println("Le joueur accuse est : Joueur "+ (indiceJoueurAccuse+1));
			
			joueurVirtuel.accuserJoueur(systeme, indiceJoueurAccuse, tapis);
				
			
			
		}
		else if (joueurVirtuel.getEtat() == etatsPossibles.ESTACCUSE)
		{
			System.out.println("Le joueur est accuse, et choisi de reveler son identite\n");
			joueurVirtuel.revelerIdentite(joueurAccusant, systeme, tapis);
			
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
