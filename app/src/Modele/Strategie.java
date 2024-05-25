package Modele;

/**
 * Interface qui definie les methode liees a la strategie. Elles seront implementees dans les classes <code>ToujoursReveler</code> et <code>AccuserEtReveler</code>.
 * La methode executerStrategie va definir le comportement du joueur virtuel. Les methodes strategieCarteWitch et strategieCarteHunt vont permettre de choisir une carte et realiser son effet Witch? (resp. Hunt!).
 * @author Nathan Vilmen, Etienne Lanternier
 * @version 14/01/2022
 */
public interface Strategie {
	
	/**
	 * Definit le comportement du joueur virtuel (accuser ou reveler). Ce comportement va dépendre du type de strategie : soit ToujoursReveler, soit AccuserEtReveler.
	 * @param systeme : le systeme (Systeme)
	 * @param tapis : le tapis (Tapis)
	 * @param joueurAccusant : le joueurAccusant (Joueur)
	 * @param joueurVirtuel : le joueur virtuel (JoueurVirtuel)
	 */
	public void executerStrategie(Systeme systeme, Tapis tapis, Joueur joueurAccusant, JoueurVirtuel joueurVirtuel);
	
	/**
	 * Definit le comportement du joueur virtuel en cas d'invocation de la carte EvilEye.
	 * @param systeme : le systeme (Systeme)
	 * @param indiceJoueurDesigne : l'indice du joueur designe (int)
	 * @param tapis : le tapis (Tapis)
	 * @param joueurVirtuel : le joueur virtuel (Joueur)
	 */
	public void strategieEvilEye(Systeme systeme, int indiceJoueurDesigne, Tapis tapis, JoueurVirtuel joueurVirtuel);
	
	/**
	 * Definit la strategie de choix de carte Rumeur a choisir pour reveler son effet Hunt!.
	 * @param systeme : le systeme (Systeme)
	 * @param tapis : le tapis (Tapis)
	 * @param joueurAccusant : le joueurAccusant (Joueur)
	 * @param joueurVirtuel : le joueur virtuel (JoueurVirtuel)
	 */
	public void strategieCarteHunt(Systeme systeme, Tapis tapis, Joueur joueurAccusant, JoueurVirtuel joueurVirtuel);
	
	/**
	 * Definit la strategie de choix de carte Rumeur a choisir pour reveler son effet Witch?.
	 * @param systeme : le systeme (Systeme)
	 * @param tapis : le tapis (Tapis)
	 * @param joueurAccusant : le joueurAccusant (Joueur)
	 * @param joueurVirtuel : le joueur virtuel (JoueurVirtuel)
	 */
	public void strategieCarteWitch(Systeme systeme, Tapis tapis, Joueur joueurAccusant, JoueurVirtuel joueurVirtuel);
	
}
