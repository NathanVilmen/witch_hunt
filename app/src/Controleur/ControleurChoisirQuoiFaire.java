package Controleur;

import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Modele.*;
import Vue.*;

public class ControleurChoisirQuoiFaire {

	private JButton btnAccuser;
	private JButton btnRevelerCarte;
	private Systeme systeme;
	private WitchHunt vue;
	private boolean peutJouer;
	
public ControleurChoisirQuoiFaire(Systeme systeme, JButton btnAccuser, JButton btnRevelerCarte, WitchHunt vue) {
		
		this.btnAccuser = btnAccuser;
		this.btnRevelerCarte = btnRevelerCarte;
		this.systeme = systeme;
		this.vue = vue;
		
		this.btnAccuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				vue.cacherTout();
				vue.afficherPanelAccusation();
				systeme.joueurAccusant = Systeme.getInstance().getJoueurActuel();
			}
		});
		
		this.btnRevelerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				peutJouer = false;
				Systeme.getInstance().listeJoueurs.get(Systeme.getInstance().getIndiceJoueurActuel()).cartesEnMain.forEach((elt) -> {
					if(elt.estJouableHunt(systeme, systeme.listeJoueurs.get(systeme.getIndiceJoueurActuel()), null, systeme.getTapis())) {
						peutJouer = true;
					}
				});
				
				if(peutJouer) {
					vue.cacherTout();
					vue.revelerCarteHunt();
				}
				else {
					System.out.println("Vous ne pouvez pas jouer de carte.");
				}
				
			}
		});
	}
}
