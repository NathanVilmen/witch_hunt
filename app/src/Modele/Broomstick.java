package Modele;

import java.util.Scanner;
import javax.swing.*;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class Broomstick extends CarteRumeur{
	Scanner scanner = new Scanner(System.in);
	
	public JTextField fieldChooseNextPlayer;

	
	public Broomstick() {
		super();
	}
	
	public String toString()
	{
		return "Carte Broomstick: \nEffet Witch: Prenez le prochain tour. \nEffet Hunt: Choisissez le joueur suivant.\n\n";
	}
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
			if(joueurActuel.cartesEnMain.get(i) instanceof Broomstick) {
				joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
			}
		}
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
	}
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		systeme.vue.cacherTout();
		systeme.vue.afficherPanelChooseNP();
	}
	
	public void lireChooseNextPlayer() {
		try {
			int joueurSuivant = Integer.parseInt(fieldChooseNextPlayer.getText());
			if(joueurSuivant >= 1 && joueurSuivant <= Systeme.getInstance().listeJoueurs.size())
			{
				Systeme.getInstance().passerAuSuivant((joueurSuivant-1), Systeme.getInstance().getTapis(), Systeme.getInstance().getJoueurAccusant());
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Veuillez saisir un entier");
		}
	}
	
	public void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//D�but take next turn
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		joueurActuel.cartesEnMain.remove(this);
		systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
		//Fin take next turn
	}
	
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//D�but Choose next player
		int choix;
		System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
		choix = (int)(Math.random()*systeme.listeJoueurs.size());
		System.out.println("Vous avez choisi le joueur : "+(choix+1));
		
		joueurActuel.cartesEnMain.remove(this);
		if(choix < 0 || choix > systeme.listeJoueurs.size()-1)
		{
			this.realiserEffetHuntBot(systeme, joueurActuel, joueurAccusant, tapis);
		}
		else {
			systeme.passerAuSuivant(choix, tapis, joueurAccusant);
		}

		//Fin choose next player
	}
	
	
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
	
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
}
