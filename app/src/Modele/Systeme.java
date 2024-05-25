package Modele;
import java.util.*;
import Vue.*;
import javax.swing.*;
import java.util.Observable;


/**
 * Une classe qui represente la gestion de la partie. Contient notamment une reference sur les joueurs, les cartes et le tapis.
 * Systeme ne peut avoir qu'une instance unique, c'est un <b>Singleton</b>.
 * Permet notamment de <code>choisirNbJoueurs</code>, de <code>choisirNbJoueursVirtuels</code>, de <code>genererListeJoueurs</code>, de <code>creerJoueurPhysique</code>, de <code>creerJoueurVirtuel</code>, de <code>distribuerCartes</code>, de <code>passerAuSuivant</code>, de verifier si un tour est fini avec <code>checkFinDeRound</code>, de <code>recommencerRound</code> et de verifier que le jeu est fini avec <code>checkFinDeJeu</code>.
 * 
 * @author Nathan Vilmen, Etienne Lanternier
 * @version 14/01/2022
 *
 */
public class Systeme extends Observable {
	
	/**
	 * Type boolean, utilise pour savoir si on est dans un tour (true) ou non (false).
	 */
	private boolean inRound;
	/**
	 * Type integer, utilise pour stocker le nombre de joueurs total de la partie. Sa valeur varie entre 1 et 6 inclus, et est initialisee a 0.
	 */
	private int nbJoueurs=0;
	/**
	 * Type Joueur, utilise pour connaitre le joueur actuel d'un tour.
	 */
	private Joueur joueurActuel;
	/**
	 * Type List, utilise pour contenir la liste des joueurs de la partie. Peut contenir au maximum 6 joueurs, et est initalise vide.
	 */
	public static List<Joueur> listeJoueurs = new ArrayList<Joueur>();
	/**
	 * Type integer, utilise pour stocker le nombre de joueurs virtuels de la partie. Sa valeur varie entre 0 et 6.
	 */
	private int nbJoueursVirtuels;
	/**
	 * Type List, utilise pour contenir la liste des cartes de la partie. Peut contenir au maximum 12 cartes rumeur, et est initalise vide.
	 */
	public static List<CarteRumeur> tasDeCartes = new ArrayList<CarteRumeur>();
	/**
	 * Type integer, utilise pour stocker l'indice du joueur actuel. Compris entre 0 et 5, et initialise a 0.
	 */
	private int indiceJoueurActuel = 0;
	/**
	 * Type integer, utilise pour compter le nombre de joueurs non reveles, pour savoir quand le tour est fini. Compris entre 0 et 5.
	 */
	private int compteurJoueursNonReveles;
	/**
	 * Type Joueur, utilise pour connaitre le joueur qui en accuse un autre, dans le cas d'une accusation.
	 */
	public Joueur joueurAccusant;
	/**
	 * Type Joueur, utilise pour evoquer le dernier joueur du tour dont la carte identite n'est pas revelee.
	 */
	private Joueur joueurRestant;
	/**
	 * Type boolean, utilise pour savoir si la partie est finie (true) ou non (false).
	 */
	private boolean finDuJeu = false;
	/**
	 * Type integer, utilise pour connaitre le plus haut score parmi tous les scores. Compris entre 0 et 10.
	 */
	private int maxScore;
	/**
	 * Type Tapis, utilise comme reference a tapis pour pouvoir deposer des cartes rumeurs.
	 */
	private Tapis tapis;
	/**
	 * Type WitchHunt, utilise comme reference sur la vue graphique.
	 */
	public WitchHunt vue;
	/**
	 * Type JTextField, i.e. un champ de texte, utilise pour recuperer la valeur du nombre de joueurs physiques entre.
	 */
	public JTextField fieldNbJoueurs;
	/**
	 * Type JTextField, i.e. un champ de texte, utilise pour recuperer la valeur du nombre de joueurs virtuels entre.
	 */
	public JTextField fieldNbJoueursVirtuels;
	/**
	 * Type JTextField, i.e. un champ de texte, utilise pour recuperer la valeur du numero du joueur a accuser entre.
	 */
	public JTextField fieldJoueurAccuse;
	/**
	 * Type Scanner, utilise pour permettre de lire une valeur de la console. Initialisation par défaut.
	 */
	Scanner scanner = new Scanner(System.in);
	/**
	 * Type Systeme, utilise pour n'avoir qu'une instance de Systeme. Initialise a null.
	 */
	private static Systeme instance = null;
	
	
	/**
	 * A la fois getteur et setteur de instance. Utilise pour creer une instance unique de Systeme quand elle n'existe pas et la retourner, ou simplement la retourner quand elle existe.
	 * @return instance
	 */
	public static Systeme getInstance() {
		
		if (instance == null) {
			instance = new Systeme(false);
		}
		
		return instance;
	}
	
	/**
	 * Constructeur de Systeme.
	 * @param inRound
	 */
	public Systeme(boolean inRound)
	{
		this.inRound = inRound;
		this.tapis = new Tapis();
	}
	
	/**
	 * Permet de recuperer le nombre de joueurs total entre dans l'interface.
	 * Tant que le nombre de joueurs n'est pas compris entre 1 et 6, on demande a nouveau de saisir le nombre de joueurs.
	 * @throws NumberFormatException Si l'utilisateur entre un element qui n'est pas un entier.
	 */
	public void choisirNbJoueurs()
	{		
		//System.out.println("Combien desirez vous de joueurs dans la partie? (Le maximum est de 6)");
		try {
			this.nbJoueurs = Integer.parseInt(this.fieldNbJoueurs.getText());
			
			if(this.nbJoueurs<3 || this.nbJoueurs>6)
			{
				System.out.println("Vous avez saisi un chiffre inferieur a 3 ou superieur a 6. Veuillez saisir un chiffre compris entre 3 et 6 inclus.");
			}
			else {
				choisirNbJoueursVirtuels();
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Veuillez saisir un entier compris entre 3 et 6 inclus.");
		}
	}
	
	
	/**
	 * Permet de recuperer le nombre de joueurs virtuels entre dans l'interface.
	 * Tant que le nombre de joueurs n'est pas compris entre 0 et le nmbre de joueurs total, on demande a nouveau de saisir le nombre de joueurs.
	 * @throws NumberFormatException Si l'utilisateur entre un element qui n'est pas un entier.
	 */
	public void choisirNbJoueursVirtuels()
	{		
		try {
			//System.out.println("Combien desirez vous de joueurs virtuels dans la partie? (Le maximum est de " + this.nbJoueurs + ")");
			this.nbJoueursVirtuels = Integer.parseInt(this.fieldNbJoueursVirtuels.getText());

			if(this.nbJoueursVirtuels<0 || this.nbJoueursVirtuels>this.nbJoueurs)
			{
				System.out.println("Vous avez saisi un chiffre inferieur a 0 ou superieur a " + this.nbJoueurs + ". Veuillez saisir un chiffre compris entre 0 et " + this.nbJoueurs + " inclus.");
			}
			else {
				this.genererListeJoueurs();
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Veuillez saisir un entier");
		}
		
	}
	
	/**
	 * Permet de generer la liste des joueurs, avec des joueurs physiques et virtuels.
	 */
	public void genererListeJoueurs() {		
		//On remplit ensuite la liste des joueurs avec le bon nombre de joueurs physiques et virtuels.
		for(int i=1; i<=nbJoueurs-nbJoueursVirtuels; i++)
		{
			this.creerJoueurPhysique();
		}
		for(int i=1; i<=nbJoueursVirtuels; i++)
		{
			this.creerJoueurVirtuel();
		}
		//Ici, on utilise la methode shuffle de la classe Collections afin de melanger la liste pour eviter que les joueurs physiques jouent tout le temps en premier par exemple.
		Collections.shuffle(this.listeJoueurs);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	
	//Il s'agit d'une methode qui va distribuer les cartes aux differents joueurs.
	/**
	 * Distribue aleatoirement les cartes aux joueurs en les mettant dans leur main.
	 * @param tapis
	 */
	public void distribuerCartes(Tapis tapis)
	{
		//Pour commencer, on cree une collection ou il y a toutes les cartes du jeu. 
		this.tasDeCartes.add(new AngryMob());
		this.tasDeCartes.add(new TheInquisition());
		this.tasDeCartes.add(new PointedHat());
		this.tasDeCartes.add(new HookedNose());
		this.tasDeCartes.add(new Broomstick());
		this.tasDeCartes.add(new Wart());
		this.tasDeCartes.add(new DuckingStool());
		this.tasDeCartes.add(new Cauldron());
		this.tasDeCartes.add(new EvilEye());
		this.tasDeCartes.add(new Toad());
		this.tasDeCartes.add(new BlackCat());
		this.tasDeCartes.add(new PetNewt());
		
		Collections.shuffle(this.tasDeCartes);
		
		//En fonction du nombre de joueurs dans la partie, on distribue plus ou moins de cartes a chacun. On les enlËve ensuite de la liste afin de ne pas avoir de doublon.
		switch(this.nbJoueurs) {
			case 3 : {
				for (int i=0; i<=2; i++)
				{
					for (int j=0; j<=3; j++)
					{
						this.listeJoueurs.get(i).cartesEnMain.add(this.tasDeCartes.get(j));
					
						if(j == 3)
						{
							this.tasDeCartes.remove(0);
							this.tasDeCartes.remove(0);
							this.tasDeCartes.remove(0);
							this.tasDeCartes.remove(0);
						}
					}
				}
			} ; break;
			case 4 : {
				for (int i=0; i<=3; i++)
				{
					for (int j=0; j<=2; j++)
					{
						this.listeJoueurs.get(i).cartesEnMain.add(this.tasDeCartes.get(j));
						
						if(j == 2)
						{
							this.tasDeCartes.remove(0);
							this.tasDeCartes.remove(0);
							this.tasDeCartes.remove(0);
						}
					}
				}
			};break;
			case 5 : {
				for (int i=0; i<=4; i++)
				{
					for (int j=0; j<=1; j++)
					{
						this.listeJoueurs.get(i).cartesEnMain.add(this.tasDeCartes.get(j));
						
						if(j == 1)
						{
							this.tasDeCartes.remove(0);
							this.tasDeCartes.remove(0);
						}
					}
				}
				//Ici, c'est different car il reste 2 cartes dans la liste. On les ajoute alors a la defausse dans le tapis.
				tapis.defausse.add(this.tasDeCartes.get(0));
				this.tasDeCartes.remove(0);
				tapis.defausse.add(this.tasDeCartes.get(0));
				this.tasDeCartes.remove(0);
			};break;
			case 6 : {
				for (int i=0; i<=5; i++)
				{
					for (int j=0; j<=1; j++)
					{
						this.listeJoueurs.get(i).cartesEnMain.add(this.tasDeCartes.get(j));
						
						if(j == 1)
						{
							this.tasDeCartes.remove(0);
							this.tasDeCartes.remove(0);
						}
					}
				}
			};break;
			default : System.out.println("Erreur dans le nombre de joueur.");break;
		}
	
		//On changera forcement de joueur accusant avant de reveler une carte ou c'est utile.
		passerAuSuivant(indiceJoueurActuel, tapis, joueurActuel);
	}
	
	
	
	/**
	 * Permet de jouer le tour du joueur, i.e. soit choisir son camp, soit choisir quoi faire.
	 * @param indiceJoueurActuel : indice du joueur actuel (int)
	 * @param tapis : le tapis (Tapis)
	 * @param joueurAccusant (Joueur)
	 */
	public void passerAuSuivant(int indiceJoueurActuel, Tapis tapis, Joueur joueurAccusant)
	{
		this.indiceJoueurActuel = indiceJoueurActuel;
		this.joueurActuel = this.listeJoueurs.get(indiceJoueurActuel);
		if(this.joueurActuel.getCamp() == campsPossibles.NONDEFINI)
		{
			this.vue.afficherChoixCamp();
		}
		else 
		{
			this.joueurActuel.choisirQuoiFaire(this, tapis, joueurAccusant);
		}
	}
	
	
	/**
	 * Permet de verifier s'il reste plus qu'un joueur dont la carte identite est revelee, et le cas echeant afficher les scores.
	 * @param tapis
	 */
	public void checkFinDeRound(Tapis tapis) 
	{
		;
		this.compteurJoueursNonReveles = 0;
		this.listeJoueurs.forEach((elt) -> {
			if(!elt.getEstRevele())
			{
				this.compteurJoueursNonReveles++;
				this.joueurRestant = elt;
			}
			
		});
		
		if(this.compteurJoueursNonReveles == 1) {
			System.out.println("\nFin de round, car il ne reste qu'un joueur dont l'identite n'a pas encore revelee.");
			System.out.println("L'identite de ce joueur est " + this.joueurRestant.getCamp());
			if(joueurRestant.getCamp() == campsPossibles.SORCIERE) {
				joueurRestant.incrementerScore(2);
			}
			else {
				joueurRestant.incrementerScore(1);
			}
			
			//Affichage des scores
			Iterator<Joueur> it = listeJoueurs.iterator();
			System.out.println("--------------------------- SCORES ---------------------------\n");
			int indice = 0;
			while(it.hasNext()) {
				indice++;
				System.out.println("Joueur "+indice+" : "+it.next().getScore());
			}
			//Fin d'affichage des scores
			
			this.checkFinDeJeu();
			this.recommencerRound(tapis);
		}
	}
	
	/**
	 * Permet de recommencer un tour, en reinitialisant les elements caracteristiques du tour.
	 * @param tapis
	 */
	public void recommencerRound(Tapis tapis) {
		//On reinitialise chaque joueur ainsi que le tapis
		this.listeJoueurs.forEach((elt) -> {
			elt.setEtat(etatsPossibles.NONDEFINI);
			elt.setCamp(campsPossibles.NONDEFINI);
			elt.setEstRevele(false);
			elt.cartesEnMain.clear();
			elt.cartesRevelees.clear();
			elt.setEnJeu(true);
		});
		tapis.defausse.clear();
		this.distribuerCartes(tapis);
	}
	
	/**
	 * Permet de verifier si la partie est finie, en affichant le joueur qui a le plus haut score et son score.
	 */
	public void checkFinDeJeu() {
		this.maxScore = this.listeJoueurs.get(0).getScore();
		this.listeJoueurs.forEach((elt) -> {
			if(elt.getScore() >= 5)
			{
				this.finDuJeu = true;
			}
			if(elt.getScore()> this.maxScore) {
				maxScore = elt.getScore();
			}
		});
		
		if(this.finDuJeu)
		{
			this.listeJoueurs.forEach((elt) -> {
				if(elt.getScore() == this.maxScore) {
					System.out.println("\nLe joueur " + (this.listeJoueurs.indexOf(elt)+1) + " a gagne la partie.");
				}
			});
			System.exit(0);
		}
	}
	
	
	/**
	 * Permet de creer un joueur virtuel, en en aoutant un nouveau dans la liste des joueurs.
	 */
	public void creerJoueurVirtuel()
	{
		this.listeJoueurs.add(new JoueurVirtuel());
	}
	
	
	/**
	 * Permet de creer un joueur physique, en en ajoutant un nouveau dans la liste des joueurs.
	 */
	public void creerJoueurPhysique()
	{
		this.listeJoueurs.add(new Joueur());
	}
	
	/**
	 * Permet de retourner le nombre de joueurs de la partie.
	 * @return nbJoueurs : le nombre de joueurs (int)
	 */
	public int GetNbJoueurs()
	{
		return this.nbJoueurs;
	}

	/**
	 * Permet de fixer le nombre de joueurs de la partie.
	 * @param nbJoueurs : le nombre de joueurs de la partie (int)
	 */
	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	/**
	 * Permet de retourner le nombre de joueursvirtuels de la partie.
	 * @return nbJoueursVirtuels : le nombre de joueurs virtuels (int)
	 */
	public int getNbJoueursVirtuels() {
		return nbJoueursVirtuels;
	}
	
	/**
	 * Permet de fixer le nombre de joueurs virtuels de la partie.
	 * @param nbJoueursVirtuels : le nombre de joueurs virtuels (int)
	 */
	public void setNbJoueursVirtuels(int nbJoueursVirtuels) {
		this.nbJoueursVirtuels = nbJoueursVirtuels;
	}
	
	/**
	 * Permet de retourner le tapis du jeu.
	 * @return tapis : le tapis du jeu (Tapis)
	 */
	public Tapis getTapis() {
		return tapis;
	}

	/**
	 * Permet de fixer le tapis du jeu.
	 * @param tapis : le tapis à fixer (Tapis)
	 */
	public void setTapis(Tapis tapis) {
		this.tapis = tapis;
	}

	
	/**
	 * Permet de retourner l'indice du joueur actuel.
	 * @return indiceJoueurActuel : indice du joueur actuel (int)
	 */
	public int getIndiceJoueurActuel() {
		return indiceJoueurActuel;
	}

	/**
	 * Permet de fixer l'indice du joueur actuel.
	 * @param indiceJoueurActuel : indice du joueur actuel (int)
	 */
	public void setIndiceJoueurActuel(int indiceJoueurActuel) {
		this.indiceJoueurActuel = indiceJoueurActuel;
	}

	/**
	 * Permet de retourner le joueur actuel du tour.
	 * @return joueurActuel : le joueur actuel (Joueur)
	 */
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}

	/**
	 * Permet de fixer le joueur actuel du tour.
	 * @param joueurActuel : le joueur actuel (Joueur)
	 */
	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

	/**
	 * Permet de retourner le joueur accusant du tour.
	 * @return joueurAccusant : le joueur accusant (Joueur)
	 */
	public Joueur getJoueurAccusant() {
		return joueurAccusant;
	}

	/**
	 * Permet de fixer le joueur accusant du tour.
	 * @param joueurAccusant : le joueur accusant (Joueur)
	 */
	public void setJoueurAccusant(Joueur joueurAccusant) {
		this.joueurAccusant = joueurAccusant;
	}
	
	
}

