package Modele;

/**
 * Une classe abstraite qui declare les methodes de toutes les cartes rumeurs. Avec ces cartes, on peut <code>realiserEffetWitch</code>, <code>realiserEffetHunt</code>, <code>realiserEffetWitchBot</code>,
 * <code>realiserEffetHuntBot</code>, ainsi que verifier si la carte est jouable a l'aide des methodes <code>estJouableHunt</code>, et <code>estJouableWith</code>.
 * @author Nathan Vilmen, Etienne Lanternier
 * @version 14/01/2022
 */
public abstract class CarteRumeur {
	
	public CarteRumeur()
	{
	}
	
	/**
	 * Affichage de "Carte Rumeur".
	 * @return "Carte Rumeur"
	 */
	public String toString()
	{
		return "Carte Rumeur";
	}
	
	/**
	 * Cette methode redefinie dans chaque classe heritant de CarteRumeur, correspond a l'effet Witch de la carte.
	 * @param systeme
	 * @param joueurActuel
	 * @param joueurAccusant
	 * @param tapis
	 */
	public abstract void realiserEffetWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis);
	
	/**
	 * Cette methode redefinie dans chaque classe heritant de CarteRumeur, correspond a l'effet Hunt de la carte.
	 * @param systeme
	 * @param joueurActuel
	 * @param joueurAccusant
	 * @param tapis
	 */
	public abstract void realiserEffetHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis);
	
	/**
	 * Cette methode redefinie dans chaque classe heritant de CarteRumeur, correspond a l'effet Witch de la carte lorsqu'elle est jouee par un bot.
	 * @param systeme
	 * @param joueurActuel
	 * @param joueurAccusant
	 * @param tapis
	 */
	public abstract void realiserEffetWitchBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis);
	
	/**
	 * Cette methode redefinie dans chaque classe heritant de CarteRumeur, correspond a l'effet Hunt de la carte lorsqu'elle est jouee par un bot.
	 * @param systeme
	 * @param joueurActuel
	 * @param joueurAccusant
	 * @param tapis
	 */
	public abstract void realiserEffetHuntBot(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis);
	
	/**
	 * Cette methode est la securite de l'effet Witch de la carte. Elle renvoie true si la carte est jouable, et false sinon.
	 * @param systeme
	 * @param joueurActuel
	 * @param joueurAccusant
	 * @param tapis
	 * @return
	 */
	public boolean estJouableWitch(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
	
	/**
	 * Cette methode est la securite de l'effet Hunt de la carte. Elle renvoie true si la carte est jouable, et false sinon.
	 * @param systeme
	 * @param joueurActuel
	 * @param joueurAccusant
	 * @param tapis
	 * @return
	 */
	public boolean estJouableHunt(Systeme systeme, Joueur joueurActuel, Joueur joueurAccusant, Tapis tapis) {
		return true;
	}
}
