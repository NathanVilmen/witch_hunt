package Modele;

import java.util.*;

public class JoueurVirtuel extends Joueur{
	
	private Strategie strat = null;
	private int campAleatoire;
	
	
	public JoueurVirtuel() 
	{
		super();
		
		//on doit choisir son camp aleatoirement
		
		this.campAleatoire = (int) (Math.random()*2); //sort un nombre = à 0 ou 1
		/*Random random = new Random();
		this.campAleatoire = (int)Math.floor(random.nextInt(2));*/
		if (campAleatoire == 0) {
			this.setCamp(campsPossibles.SORCIERE);
			//on set la strategie a ToujoursReveler, i.e. tjr reveler une carte rumeur, mais jamais une carte identité
			this.strat = new ToujoursReveler();
			
		}
		else if (campAleatoire == 1){
			this.setCamp(campsPossibles.VILLAGEOIS);
			//on set la strategie a AccuserEtReveler, i.e. accuser quand c'est son tour, et reveler la carte ID quand on est accuse
			this.strat = new AccuserEtReveler();
			
		}
		else {
			System.out.println("Erreur lors de l'attibution d'une strategie. (constructeur)");
		}
	}
	
	
	public void choisirQuoiFaire(Systeme systeme, Tapis tapis, Joueur joueurAccusant) { 
	        //délègue le comportement à un objet Strategy
		System.out.println("Choix en cours...\n");
		strat.executerStrategie(systeme, tapis, joueurAccusant, this); 
	}
	
	public void choisirQuoiFaireEvilEye(Systeme systeme, int indiceJoueurDesigne, Tapis tapis) {
		strat.strategieEvilEye(systeme, indiceJoueurDesigne, tapis, this);
	}
	
	public void choisirCarteHunt(Systeme systeme, Tapis tapis, Joueur joueurAccusant) {
		strat.strategieCarteHunt(systeme, tapis, joueurAccusant, this);
	}
	
	
	public void choisirCarteWitch(Systeme systeme, Tapis tapis, Joueur joueurAccusant) {
		strat.strategieCarteWitch(systeme, tapis,joueurAccusant, this);
	}
	
	
	public void choisirSonCamp() {
		this.campAleatoire = (int) (Math.random()*2); //sort un nombre = � 0 ou 1
		if (campAleatoire == 0) {
			this.setCamp(campsPossibles.SORCIERE);
			
			//on set la strategie a ToujoursReveler, i.e. tjr reveler une carte rumeur, mais jamais une carte identité
			this.strat = new AccuserEtReveler();
		}
		else if (campAleatoire == 1){
			this.setCamp(campsPossibles.VILLAGEOIS);
			
			//on set la strategie a AccuserEtReveler, i.e. accuser quand c'est son tour, et reveler la carte ID quand on est accuse
			this.strat = new AccuserEtReveler();
		}
		else {
			System.out.println("Erreur lors de l'attibution d'une strategie. (choisirSonCamp)");
		}

	}
	
	
	public void setStrategie(Strategie strategie) {
	    this.strat = strategie;
	}
	public Strategie getStrategie() { 
	    return this.strat; 
	} 
	
	
	
	
	
	
}
