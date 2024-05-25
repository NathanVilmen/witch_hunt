package Controleur;

import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Modele.*;
import Vue.*;

public class ControleurJoueurAccuse {
	private JButton btnRevelerCarte;
	private JButton btnRevelerIdentite;
	private Systeme systeme;
	private WitchHunt vue;
	private boolean peutJouer;
	
	public ControleurJoueurAccuse(Systeme systeme, JButton btnRevelerCarte, JButton btnRevelerIdentite, WitchHunt vue) {
		
		this.btnRevelerCarte = btnRevelerCarte;
		this.btnRevelerIdentite = btnRevelerIdentite;
		this.systeme = systeme;
		this.vue = vue;
		
		this.btnRevelerIdentite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				systeme.getJoueurActuel().revelerIdentite(systeme.joueurAccusant, systeme, systeme.getTapis());
			}
		});
		
		this.btnRevelerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				peutJouer = false;
				Systeme.getInstance().listeJoueurs.get(Systeme.getInstance().getIndiceJoueurActuel()).cartesEnMain.forEach((elt) -> {
					if(elt.estJouableWitch(systeme, systeme.listeJoueurs.get(systeme.getIndiceJoueurActuel()), Systeme.getInstance().joueurAccusant, systeme.getTapis())) {
						peutJouer = true;
					}
				});
				
				if(peutJouer) {
					vue.cacherTout();
					vue.revelerCarteWitch(Systeme.getInstance().joueurAccusant);
				}
				else {
					System.out.println("Vous ne pouvez pas jouer de carte.");
				}
				
			}
		});
	}
}
