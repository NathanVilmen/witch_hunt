package Modele;

import java.util.Scanner;

//import fr.UTT.LO02.WitchHunt.Joueur.etatsPossibles;

public class DuckingStool extends CarteRumeur{
	Scanner scanner = new Scanner(System.in);

	private boolean estJouable = false;
	private boolean joueurAWart = false;
	
	public DuckingStool() {
		super();
	}
	
	public String toString()
	{
		return "Carte Ducking Stool: \nEffet Witch: Choisissez le prochain joueur. \nEffet Hunt: Choisissez un joueur. Il doit reveler son identite, ou defausser une carte de sa main. Si c'est un villageois, vous perdez un point. Si c'est une sorciere, vous gagnez 1 point. S'il defausse, il prend le prochain tour.\nVous ne pouvez pas choisir un joueur qui a revele la carte Wart avec cette carte.\n\n";
	}
	
	public void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut Choose next player
		int choix;
		System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		choix = scanner.nextInt();
		joueurActuel.cartesEnMain.remove(this);
		if(choix < 1 || choix > systeme.listeJoueurs.size())
		{
			this.realiserEffetWitch(systeme, joueurActuel, joueurAccusant, tapis);
		}
		else {
			System.out.println("Vous avez choisi le joueur "+ choix);
			systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
		}

		//Fin choose next player
	}
	
	public void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		
		
		
		while(choix < 1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1 || (systeme.listeJoueurs.get(choix-1).getEstRevele() && systeme.listeJoueurs.get(choix-1).cartesEnMain.size() == 0))
		{
			System.out.println("Quel joueur souhaitez vous designer? Il devra soit reveler son identite, soit defausser une carte de sa main (1 pour joueur 1 etc.)\n");
			choix = scanner.nextInt();
		}
		System.out.println("Vous avez choisi le joueur "+ choix);
		systeme.listeJoueurs.get(choix-1).cartesRevelees.forEach((elt) -> {
			if(elt instanceof Wart) {
				this.joueurAWart = true;
			}
		});
		
		if(this.joueurAWart) {
			this.joueurAWart = false;
			this.realiserEffetHunt(systeme, joueurActuel, joueurAccusant, tapis);
		}
		
		
		int choixDeux = -1;
		
		while(choixDeux < 0 || choixDeux > 1 || (choix == 0 && systeme.listeJoueurs.get(choix-1).getEstRevele()) || (choix == 2 && systeme.listeJoueurs.get(choix-1).cartesEnMain.size() == 0))
		{
			System.out.println("Joueur "+ choix + "\nDesirez vous reveler votre identite (tapez 0), ou defausser une carte (tapez 1)? Attention vous ne pouvez pas reveler votre identite si celle ci est dej‡ revelee, et vous ne pouvez pas defausser de cartes si vous n'en avez pas.\n");
			choixDeux = scanner.nextInt();
		}
		
		if(choixDeux == 0)
		{
			System.out.println("Vous avez choisi de reveler votre identite.\n");
			systeme.listeJoueurs.get(choix-1).revelerIdentite(joueurAccusant, systeme, tapis);
			
			if(systeme.listeJoueurs.get(choix-1).getCamp() == campsPossibles.SORCIERE)
			{
				joueurActuel.incrementerScore(1);
				
				//Debut take next turn
				for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
					if(joueurActuel.cartesEnMain.get(i) instanceof DuckingStool) {
						joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
					}
				}
				systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
				//Fin take next turn
				
			}
			else {
				joueurActuel.incrementerScore(-1);
				//Debut take next turn
				for(int i=0; i<joueurActuel.cartesEnMain.size(); i++) {
					if(joueurActuel.cartesEnMain.get(i) instanceof Wart) {
						joueurActuel.cartesEnMain.remove(joueurActuel.cartesEnMain.get(i));
					}
				}
				systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
				//Fin take next turn
				
				
			}
		}
		else {
			int choixCartes = 0;
			System.out.println("Vous avez choisi de defausser une carte.\n");
			 while(choixCartes < 1 || choixCartes > systeme.listeJoueurs.get(choix-1).cartesEnMain.size()) {
				 System.out.println("Joueur " + choix + "\n"+systeme.listeJoueurs.get(choix-1).cartesEnMain+"\nQuelle carte souhaitez-vous defausser?\n");
				 choixCartes = scanner.nextInt();
			 }
			 
			 System.out.println("Vous avez choisi de defausser la carte : "+systeme.listeJoueurs.get(choix-1).cartesEnMain.get(choixCartes-1));
			 tapis.defausse.add(systeme.listeJoueurs.get(choix-1).cartesEnMain.get(choixCartes-1));
			 systeme.listeJoueurs.get(choix-1).cartesEnMain.remove(choixCartes-1);
			 
			//Debut take next turn
			joueurActuel.cartesEnMain.remove(this);
			systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
			//Fin take next turn
		}
	}
	
	public void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		//Debut Choose next player
		int choix;
		System.out.println("Veuillez choisir le joueur suivant (tapez 1 pour joueur1 etc.)\n");
		joueurActuel.setEtat(etatsPossibles.NONDEFINI);
		choix = (int)(Math.random()*systeme.listeJoueurs.size());
		joueurActuel.cartesEnMain.remove(this);
		if(choix < 0 || choix > systeme.listeJoueurs.size()-1)
		{
			this.realiserEffetWitchBot(systeme, joueurActuel, joueurAccusant, tapis);
		}
		else {
			System.out.println("Vous avez choisi le joueur "+ choix);
			systeme.passerAuSuivant(choix, tapis, joueurAccusant);
		}

		//Fin choose next player
	}
	
	
	public void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis)
	{
		int choix = 0;
		
		
		
		while(choix < 1 || choix > systeme.listeJoueurs.size() || choix == systeme.listeJoueurs.indexOf(joueurActuel)+1 || (systeme.listeJoueurs.get(choix-1).getEstRevele() && systeme.listeJoueurs.get(choix-1).cartesEnMain.size() == 0))
		{
			System.out.println("Quel joueur souhaitez vous designer? Il devra soit reveler son identite, soit defausser une carte de sa main (1 pour joueur 1 etc.)\n");
			choix = (int)(Math.random()*systeme.listeJoueurs.size()+1);
		}
		
		System.out.println("Vous avez choisi de designer le joueur : "+choix);
		systeme.listeJoueurs.get(choix-1).cartesRevelees.forEach((elt) -> {
			if(elt instanceof Wart) {
				this.joueurAWart = true;
			}
		});
		
		if(this.joueurAWart) {
			this.joueurAWart = false;
			this.realiserEffetHuntBot(systeme, joueurActuel, joueurAccusant, tapis);
		}
		
		
		int choixDeux = -1;
		
		while(choixDeux < 0 || choixDeux > 1 || (choix == 0 && systeme.listeJoueurs.get(choix-1).getEstRevele()) || (choix == 2 && systeme.listeJoueurs.get(choix-1).cartesEnMain.size() == 0))
		{
			System.out.println("Joueur "+ choix + "\nDesirez vous reveler votre identite (tapez 0), ou defausser une carte (tapez 1)? Attention : vous ne pouvez pas reveler votre identite si celle ci est dej‡ revelee, et vous ne pouvez pas defausser de cartes si vous n'en avez pas.\n");
			choixDeux = (int)(Math.random()*2);
		}
		
		if(choixDeux == 0)
		{
			System.out.println("Vous avez choisi de reveler votre identite.\n");
			systeme.listeJoueurs.get(choix-1).revelerIdentite(joueurAccusant, systeme, tapis);
			
			if(systeme.listeJoueurs.get(choix-1).getCamp() == campsPossibles.SORCIERE)
			{
				joueurActuel.incrementerScore(1);
				
				//Debut take next turn
				joueurActuel.cartesEnMain.remove(this);
				systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurActuel), tapis, joueurAccusant);
				//Fin take next turn
				
			}
			else {
				joueurActuel.incrementerScore(-1);
				
				//Debut take next turn
				joueurActuel.cartesEnMain.remove(this);
				systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
				//Fin take next turn
			}
		}
		else {
			System.out.println("Vous avez choisi de defausser une carte.\n");
			int choixCartes = 0;
			 while(choixCartes < 1 || choixCartes > systeme.listeJoueurs.get(choix-1).cartesEnMain.size()) {
				 System.out.println("Joueur " + choix + "\n"+systeme.listeJoueurs.get(choix-1).cartesEnMain+"\nQuelle carte souhaitez-vous defausser?\n");
				 choixCartes = (int)(Math.random()*(systeme.listeJoueurs.get(choix-1).cartesEnMain.size()+1));
			 }
			 
			 System.out.println("Vous avez choisi de defausser la carte : "+systeme.listeJoueurs.get(choix-1).cartesEnMain.get(choixCartes-1));
			 tapis.defausse.add(systeme.listeJoueurs.get(choix-1).cartesEnMain.get(choixCartes-1));
			 systeme.listeJoueurs.get(choix-1).cartesEnMain.remove(choixCartes-1);
			 
			//Debut take next turn
			joueurActuel.cartesEnMain.remove(this);
			systeme.passerAuSuivant(choix-1, tapis, joueurAccusant);
			//Fin take next turn
		}
	}
	
	
	
	
	
	
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
	
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		
		systeme.listeJoueurs.forEach((elt)->{
			if(!elt.getEstRevele() || elt.cartesEnMain.size()>0) {
				elt.cartesRevelees.forEach((eltCarte) -> {
					if(eltCarte instanceof Wart) {
						this.joueurAWart = true;
					}
				});
				if(!this.joueurAWart) {
					this.estJouable = true;
				}
				this.joueurAWart = false;
			}
		});
		if(this.estJouable) {
			this.estJouable = false;
			return true;
		}
		else {
			return false;
		}
	}
}
