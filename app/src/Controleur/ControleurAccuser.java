package Controleur;

import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Modele.*;
import Vue.*;

public class ControleurAccuser {
	private Systeme systeme;
	private WitchHunt vue;
	private JButton btnValider;
	
	public ControleurAccuser(Systeme systeme, JTextField fieldJoueurAccuse, JButton btnValider, WitchHunt vue) {
		this.systeme = systeme;
		this.vue = vue;
		this.btnValider = btnValider;
		this.systeme.fieldJoueurAccuse = fieldJoueurAccuse;
		
		this.btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				systeme.listeJoueurs.get(systeme.getIndiceJoueurActuel()).lireJoueurAccuse(systeme.getTapis());
				
			}
		});
	}
}
