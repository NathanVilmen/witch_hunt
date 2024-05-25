package Modele;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

/**
 * Une classe qui represente un joueur de WitchHunt. Ce joueur peut <code>choisirQuoiFaire</code>, <code>choisirCarteHunt</code>, <code>choisirCarteWitch</code>, <code>revelerIdentite</code> et <code>accuserJoueur</code>.
 * On verifie l'entree du numero du joueur accuse avec <code>lireJoueurAccuse</code>. On lui ajoute des points avec <code>incrementerScore</code>.
 * @author Nathan Vilmen, Etienne Lanternier
 * @version 14/01/2022
 */
public class Joueur {
	/**
	 * Type entier, qui va representer le nombre de points du joueur, et qui sera initialise ulterieurement.
	 */
	private int score;
	
	/**
	 * Type etatsPossibles (enum defini dans le projet), qui va representer si le joueur est accuse ou pas. Il sera initialise ulterieurement.
	 */
	private etatsPossibles etat;
	
	/**
	 * Type booleen qui verifie si le joueur a ete revele ou pas. C'est important car un joueur revele, et sorciere ne peut plus joueur ni Ítre designe. Il sera initialise plus tard et peut prendre les valeurs true et false.
	 */
	private boolean estRevele;
	
	/**
	 * Type campsPossibles (enum defini dans le projet), qui va representer si le joueur est villageois ou sorciere. Il sera initialise ulterieurement.
	 */
	private campsPossibles camp;
	
	/**
	 * Type ArrayList contenant des objets de type CarteRumeur, qui correspond aux cartes que le joueur a dans sa main au cours de la partie. Elle sera initialisee ulterieurement.
	 */
	public ArrayList<CarteRumeur> cartesEnMain = new ArrayList<CarteRumeur>();
	
	/**
	 * Type ArrayList contenant des objets de type CarteRumeur, qui correspond aux cartes que le joueur a revele au cours de la partie. Elle sera initialisee ulterieurement.
	 */
	public ArrayList<CarteRumeur> cartesRevelees = new ArrayList<CarteRumeur>();
	
	/**
	 * Type booleen qui verifie si le joueur est toujours en jeu ou pas. Il sera initialise plus tard et peut prendre les valeurs true et false.
	 */
	private boolean enJeu = true;
	
	/**
	 * Type booleen qui verifie si le joueur peut accuser un autre joueur que celui qui vient de l'accuser dans le cadre de la carte EvilEye. Il sera initialise plus tard et peut prendre les valeurs true et false.
	 */
	private boolean peutAccuserAutre = false;
	
	/**
	 * Type booleen qui verifie si le joueur peut joueur une carte de sa main ou pas. Il sera initialise plus tard et peut prendre les valeurs true et false.
	 */
	private boolean peutJouer = false;
	
	/**
	 * Type Systeme (defini dans le projet) qui fait reference au systeme principal du jeu. Il sera initialise plus tard.
	 */
	private Systeme systeme;
	
	Scanner scanner = new Scanner(System.in);

	/**
	 * Il s'agit du constructeur de la classe Joueur. Ce constructeur va initialiser les attributs score, etat, camp, estRevele, ainsi que systeme pour que le jeu soit dans de bonnes conditions pour commencer une partie.
	 * Au debut de la partie, le score vaut , le joueur n'est ni accuse, ni sorciere ni villageois, et ni revele.
	 * On cree la reference a systeme grace a la methode statique Systeme.getInstance().
	 */
	public Joueur()
	{
		this.score = 0;
		this.etat = etatsPossibles.NONDEFINI;
		this.camp = campsPossibles.NONDEFINI;
		this.estRevele = false;
		this.systeme = Systeme.getInstance();
	}
	
	
	/**
	 * Il s'agit d'une methode qui va permettre au joueur de decider ce qu'il va faire dans la partie.
	 * Si celui ci est accuse, on affiche le PanelJoueurAccuse, et s'il ne l'est pas, on affiche le PanelJoueur. Pour cela, on cache tous les panels a l'aide de la methode cacherTout(), puis on affiche le panel.
	 * Il faut ensuite afficher a nouveau les cartes pour mettre a jour les cartes du joueur actuel.
	 * @param systeme
	 * @param tapis
	 * @param joueurAccusant
	 */
	public void choisirQuoiFaire(Systeme systeme, Tapis tapis, Joueur joueurAccusant) {		
		if(this.etat == etatsPossibles.NONDEFINI)
		{
			systeme.vue.cacherTout();
			systeme.vue.afficherPanelJoueur();
			systeme.vue.resetCartes();
			systeme.vue.afficherCartes(this);
		}
		else if (this.etat == etatsPossibles.ESTACCUSE) {
			systeme.vue.cacherTout();
			systeme.vue.resetCartes();
			systeme.vue.afficherCartes(this);
			systeme.vue.majNumJoueur();
			systeme.vue.afficherPanelJoueurAccuse();			 
		}
		
	}
	
	/**
	 * Il s'agit d'une methode qui permet de lire le numero du joueur que l'on souhaite accuser. On ecrit le numero dans le JTextField fieldJoueurAccuse qui est un attribut de Joueur, puis on le lit. 
	 * Si c'est un nombre satisfaisant (correspondant a un joueur en jeu), on appelle la methode <code>accuserJoueur</code>. Si le nombre ne nous convient pas on affiche un message d'avertissement.
	 * On utilise un bloc try catch pour prÈvenir les exceptions qui pourraient apparaÓtre si on entre par exemple une chaine de caracteres.
	 * @param tapis
	 */
	public void lireJoueurAccuse(Tapis tapis) {
		try {
			int choix = Integer.parseInt(systeme.fieldJoueurAccuse.getText());
			if(choix-1 != systeme.listeJoueurs.indexOf(this) && choix <= systeme.listeJoueurs.size() && choix>0 && !systeme.listeJoueurs.get(choix-1).estRevele) {
				this.accuserJoueur(systeme, choix-1, tapis);
			}
			else {
				System.out.println("Soit vous vous etes accuse vous, soit vous avez saisi un nombre qui ne correspond a aucun joueur, soit vous avez accuse un joueur qui est deja revele.");
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Veuillez saisir un entier.");
		}
	}
	
	

	/**
	 * Cette methode n'a pas encore ete adaptee a l'interface graphique. Elle fait exactement le meme chose que choisirQuoiFaire, sauf qu'elle empeche d'accuser celui qui nous a accuse si on a d'autres choix. Elle empeche aussi de reveler une carte.
	 * @param systeme
	 * @param indiceJoueurDesigne
	 * @param tapis
	 */
	public void choisirQuoiFaireEvilEye(Systeme systeme, int indiceJoueurDesigne, Tapis tapis)
	{
		int choix = 0;
		
		while(choix<1 || choix > systeme.listeJoueurs.size() || choix == indiceJoueurDesigne+1) {
			System.out.println("Joueur " + (indiceJoueurDesigne+1) + "\nVous avez ete designe par un joueur ayant joue la carte Evil Eye. Vous ne pouvez donc qu'accuser un joueur, et si possible pas le joueur " + (systeme.listeJoueurs.indexOf(this)+1) + ". \nQuel joueur souhaitez vous accuser?\n");
			choix = scanner.nextInt();
		}
		
		if(choix != systeme.listeJoueurs.indexOf(this)+1)
		{
			this.accuserJoueur(systeme, choix-1, tapis);
		}
		else {
			systeme.listeJoueurs.forEach((elt) -> {
				if(elt.enJeu && elt != this) {
					this.peutAccuserAutre = true;
				}
			});
			if(this.peutAccuserAutre) {
				this.peutAccuserAutre = false;
				this.choisirQuoiFaireEvilEye(systeme, indiceJoueurDesigne, tapis);
			}
			else {
				this.accuserJoueur(systeme, choix-1, tapis);
			}
		}
	}
	
	/**
	 * Il s'agit d'une methode de la classe Joueur qui permet de choisir quelle carte rumeur nous souhaitons joueur pour actionner son effet "Hunt!".
	 * Cette methode contient des securites. En effet, on appelle la fonction <code>estJouableHunt(systeme, this, joueurAccusant, tapis)</code> pour verifier qu'on peut joueur la carte, puis si on peut la joueur, cela appelle la methode <code>realiserEffetHunt</code> de la carte correspondante.
	 * @param systeme
	 * @param tapis
	 * @param joueurAccusant
	 */
	public void choisirCarteHunt(Systeme systeme, Tapis tapis, Joueur joueurAccusant)
	{
		int choix = 0;
		
		
		while(choix<1 || choix > this.cartesEnMain.size() || !this.cartesEnMain.get(choix-1).estJouableHunt(systeme, this, joueurAccusant, tapis))
		{
			System.out.println("Vos cartes:\n\n" +this.cartesEnMain + "\n\n Quelle carte souhaitez vous reveler? (tapez 1 pour la premiere etc.)\n");
			choix = scanner.nextInt();
		}
		
		this.cartesRevelees.add(this.cartesEnMain.get(choix-1));
		this.cartesEnMain.get(choix-1).realiserEffetHunt(systeme, this, joueurAccusant, tapis);
		
	}
	
	
	/**
	 * Il s'agit d'une methode de la classe Joueur qui permet de choisir quelle carte rumeur nous souhaitons joueur pour actionner son effet "Witch?".
	 * Cette methode contient des securites. En effet, on appelle la fonction <code>estJouableWitch(systeme, this, joueurAccusant, tapis)</code> pour verifier qu'on peut joueur la carte, puis si on peut la joueur, cela appelle la methode <code>realiserEffetWitch</code> de la carte correspondante.
	 * @param systeme
	 * @param tapis
	 * @param joueurAccusant
	 */
	public void choisirCarteWitch(Systeme systeme, Tapis tapis, Joueur joueurAccusant)
	{
		int choix = 0;

		while(choix<1 || choix > this.cartesEnMain.size() || !this.cartesEnMain.get(choix-1).estJouableWitch(systeme, this, joueurAccusant, tapis))
		{
			System.out.println("Vos cartes:\n\n" +this.cartesEnMain + "\n\n Quelle carte souhaitez vous reveler? (tapez 1 pour la premiere etc.)\n");
			choix = scanner.nextInt();
		}
		
		this.cartesRevelees.add(this.cartesEnMain.get(choix-1));
		this.cartesEnMain.get(choix-1).realiserEffetWitch(systeme, this, joueurAccusant, tapis);
	}
	
	/**
	 * Il s'agit d'une methode qui permet de reveler l'identite du joueur. On affiche alors un message contenant l'identite de celui-ci, on passe estRevele a true, puis on gere les points.
	 * En effet, si le joueur revele etait une sorciere, le joueur accusant prend un point, et prend le prochain tour. Sinon, c'est au joueur qui vient de reveler son identite de jouer. 
	 * On souhaite aussi verifier si le round est fini a l'aide de la methode <code>checkFinDeRound(tapis)</code>.
	 * @param joueurAccusant
	 * @param systeme
	 * @param tapis
	 */
	public void revelerIdentite(Joueur joueurAccusant, Systeme systeme, Tapis tapis) {
		System.out.println("L'identite de ce joueur est " + this.camp);
		this.estRevele = true;
		//Ici, on a le cas classique de l'accusation contre une sorciere.
		if(this.camp == campsPossibles.SORCIERE && joueurAccusant != null) {
			joueurAccusant.incrementerScore(1);
			this.enJeu = false;
			systeme.checkFinDeRound(tapis);
			systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(joueurAccusant), tapis, joueurAccusant);	
		}
		//pour le cas ou on revele son identite sans etre accuse (Cauldron et Toad), alors joueurAccusant=null et on passe au suivant.
		else if(this.camp == campsPossibles.SORCIERE && joueurAccusant == null) {
			this.enJeu = false;
			systeme.checkFinDeRound(tapis);
			//Si on est le dernier joueur, et que l'on est revele, on passe au premier joueur (indice 0). Sinon, on passe au joueur suivant dans la liste
			if(systeme.listeJoueurs.indexOf(this) == systeme.GetNbJoueurs()) {
				systeme.passerAuSuivant(0, tapis, joueurAccusant);	
			}
			else {
				systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(this)+1, tapis, joueurAccusant);	
			}
		}
		else if (this.camp == campsPossibles.VILLAGEOIS && joueurAccusant == null) {
			systeme.checkFinDeRound(tapis);
			systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(this), tapis, joueurAccusant);
		}
		else {
			this.etat = etatsPossibles.NONDEFINI;
			systeme.checkFinDeRound(tapis);
			systeme.passerAuSuivant(systeme.listeJoueurs.indexOf(this), tapis, joueurAccusant);
		}
	}
	
	
	/**
	 * Cette methode permet d'accuser un joueur c'est a dire changer son etat, puis passer a son tour.
	 * @param systeme
	 * @param indiceJoueurAccuse
	 * @param tapis
	 */
	public void accuserJoueur(Systeme systeme, int indiceJoueurAccuse, Tapis tapis)
	{
		systeme.listeJoueurs.get(indiceJoueurAccuse).etat = etat.ESTACCUSE;		
		systeme.passerAuSuivant(indiceJoueurAccuse, tapis, this);
	}
	
	
	public void incrementerScore(int ajout) {
		this.setScore(this.getScore()+ajout);
		System.out.println("Votre score : " + this.score + "\n");
	}
	
	
	//Getters et Setters
	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public boolean getEstRevele() {
		return estRevele;
	}


	public void setEstRevele(boolean estRevele) {
		this.estRevele = estRevele;
	}


	public ArrayList<CarteRumeur> getCartesEnMain() {
		return cartesEnMain;
	}


	public void setCartesEnMain(ArrayList<CarteRumeur> cartesEnMain) {
		this.cartesEnMain = cartesEnMain;
	}


	public ArrayList<CarteRumeur> getCartesRevelees() {
		return cartesRevelees;
	}


	public void setCartesRevelees(ArrayList<CarteRumeur> cartesRevelees) {
		this.cartesRevelees = cartesRevelees;
	}


	public boolean getEnJeu() {
		return enJeu;
	}


	public void setEnJeu(boolean enJeu) {
		this.enJeu = enJeu;
	}


	public boolean getPeutAccuserAutre() {
		return peutAccuserAutre;
	}


	public void setPeutAccuserAutre(boolean peutAccuserAutre) {
		this.peutAccuserAutre = peutAccuserAutre;
	}


	public campsPossibles getCamp(){
		return this.camp;
	}
	
	
	public etatsPossibles getEtat() {
		return this.etat;
	}

	public void setEtat(etatsPossibles etat) {
		this.etat = etat;
	}
	
	public void setCamp(campsPossibles camp) {
		this.camp = camp;
	}
	
	public boolean getPeutJouer() {
		return peutJouer;
	}


	public void setPeutJouer(boolean peutJouer) {
		this.peutJouer = peutJouer;
	}
	
	
}