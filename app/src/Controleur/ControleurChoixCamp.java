package Controleur;

import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Modele.*;
import Vue.*;

public class ControleurChoixCamp {

	private JButton btnSorciere;
	private JButton btnVillageois;
	private Systeme systeme;
	private WitchHunt vue;
	
	public ControleurChoixCamp(Systeme systeme, JButton btnSorciere, JButton btnVillageois, WitchHunt vue) {
		
		this.btnSorciere = btnSorciere;
		this.btnVillageois = btnVillageois;
		this.systeme = systeme;
		this.vue = vue;
		
		
		this.btnVillageois.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				Systeme.getInstance().listeJoueurs.get(Systeme.getInstance().getIndiceJoueurActuel()).setCamp(campsPossibles.VILLAGEOIS);
				vue.majNumJoueur();
				if(Systeme.getInstance().getIndiceJoueurActuel() == Systeme.getInstance().GetNbJoueurs()-1)
				{
					vue.resetCartes();
					vue.afficherCartes(Systeme.getInstance().listeJoueurs.get(0));
					Systeme.getInstance().passerAuSuivant(0, systeme.getTapis(), null);
				}
				else
				{
					vue.resetCartes();
					vue.afficherCartes(Systeme.getInstance().listeJoueurs.get(Systeme.getInstance().getIndiceJoueurActuel()+1));
					systeme.passerAuSuivant(systeme.getIndiceJoueurActuel()+1, systeme.getTapis(), null);
				}
				
			}
		});
		
		this.btnSorciere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				Systeme.getInstance().listeJoueurs.get(Systeme.getInstance().getIndiceJoueurActuel()).setCamp(campsPossibles.SORCIERE);
				vue.majNumJoueur();
				if(Systeme.getInstance().getIndiceJoueurActuel() == Systeme.getInstance().GetNbJoueurs()-1)
				{
					vue.resetCartes();
					vue.afficherCartes(Systeme.getInstance().listeJoueurs.get(0));
					Systeme.getInstance().passerAuSuivant(0, systeme.getTapis(), null);
				}
				else
				{
					vue.resetCartes();
					vue.afficherCartes(Systeme.getInstance().listeJoueurs.get(Systeme.getInstance().getIndiceJoueurActuel()+1));
					systeme.passerAuSuivant(systeme.getIndiceJoueurActuel()+1, systeme.getTapis(), null);
				}
				
			}
		});
	}
	
}
