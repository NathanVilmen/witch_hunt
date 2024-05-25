package Controleur;

import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Modele.*;
import Vue.*;

public class ControleurChooseNP {
	private Systeme systeme;
	private WitchHunt vue;
	private JButton btnValider;
	private JTextField fieldJoueurChoisi;
	
	
	public ControleurChooseNP(Systeme systeme, JTextField fieldJoueurChoisi, JButton btnValider, WitchHunt vue) {
		this.systeme = systeme;
		this.vue = vue;
		this.btnValider = btnValider;
		this.fieldJoueurChoisi = fieldJoueurChoisi;
		
		
		this.btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Broomstick broomstick = new Broomstick();
				broomstick.fieldChooseNextPlayer = fieldJoueurChoisi;
				broomstick.lireChooseNextPlayer();
				
			}
		});
	}
}

