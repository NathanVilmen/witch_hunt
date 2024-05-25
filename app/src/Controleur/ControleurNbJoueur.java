package Controleur;
import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Modele.*;
import Vue.*;

/**
 * Classe qui a pour but de faire le lien entre le bouton <code>btnValiderNbJoueurs</code> et le modele. Implemente la classe Observer.
 * Permet d'ajouter une action à executer lors du click sur le bouton, dans le constructeur.
 * @author Nathan Vilmen, Etienne Lanternier
 * @version 14/01/2022
 */
public class ControleurNbJoueur implements Observer{
	
	/**
	 * Type JButton, utilise pour recuperer le <code>btnValiderNbJoueurs</code> de la vue.
	 */
	private JButton btnValiderNbJoueurs;
	/**
	 * Type Systeme, utilise pour faire appel a une fonction de systeme.
	 */
	private Systeme systeme;
	/**
	 * Type WitchHunt, utilise pour recuperer la vue en cours.
	 */
	private WitchHunt vue;
	
	/**
	 * Constructeur du controleur, qui permet d'ajouter une action au bouton. Cette action est l'appel de <code>choisirNbJoueurs</code>.
	 * @param btnValiderNbJoueurs : le bouton en question
	 * @param fieldNbJoueurs : le champ contenant le nombre de joueurs
	 * @param fieldNbJoueursVirtuels : le champ contenant le nombre de joueurs virtuels
	 * @param vue : la vue en cours
	 * @param systeme : le systeme
	 */
	public ControleurNbJoueur(JButton btnValiderNbJoueurs, JTextField fieldNbJoueurs, JTextField fieldNbJoueursVirtuels, WitchHunt vue, Systeme systeme) {
		this.btnValiderNbJoueurs = btnValiderNbJoueurs;
		this.vue = vue;
		this.systeme = systeme;
		this.systeme.addObserver(this);
		this.systeme.fieldNbJoueurs = fieldNbJoueurs;
		this.systeme.fieldNbJoueursVirtuels = fieldNbJoueursVirtuels;
		
		this.btnValiderNbJoueurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				systeme.choisirNbJoueurs();
			}
		});
	}
	
	/**
	 * Methode qui permet de mettre a jour la vue.
	 * @param observable : l'objet observable
	 * @param o : un objet
	 */
	public void update(Observable observable, Object o) {
		if(this.vue.panelNbJoueurs.isVisible() == true) {
			this.vue.cacherTout();
			this.vue.afficherChoisirCamp();
			this.systeme.distribuerCartes(systeme.getTapis());
			this.vue.afficherCartes(this.systeme.listeJoueurs.get(0));
		}
		
	}
}
