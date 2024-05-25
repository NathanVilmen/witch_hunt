package Vue;

import Modele.*;
import Controleur.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Une classe qui represente la <b>vue utilisateur</b> lors de la lancee du programme. La vue est lancee par la methode <code>main</code>. 
 * Le constructeur de la vue appelle la methode <code>initialize</code>, dans laquelle la majorite du code se situe. La strategie utilisee est l'affichage et le cache de panel. Ainsi, pour cacher tous les panels, il y a la methode <code>cacherTout</code>. Il y a alors des methodes pour afficher chaque type de panel.
 * On affiche les cartes du joueur avec <code>afficherCartes</code>, en placant les cartes  graphiquement avec <code>placerCartes</code>. Le numero du joueurs est mis a jour avec <code>majNumJoueurs</code>.
 * Cette vue est composee d'elements graphiques comme des boutons, des fenetres, des champs de texte ou des images. 
 * Dependance a l'OS : tout fonctionne sur Windows comme sur Mac, sauf l'inclusion d'images comme boutons, qui ne fonctionne que sur Windows.
 * 
 * @author Nathan Vilmen, Etienne Lanternier
 * @version 14/01/2022
 *
 */
public class WitchHunt {

	/**
	 * Type JFrame, qui va servir de fenetre d'affichage general. Son initialisation est faite ulterieurement.
	 */
	private JFrame frame;
	
	
	/**
	 * Type JPanel, utilise pour afficher la page de demarrage du jeu. Elle va contenir le <code>labelWitchHunt</code> et le <code>btnStart</code>. Son initialisation est faite ulterieurement.
	 */
	public JPanel panelDebut;
	/**
	 * Type JLabel, i.e. un texte affiche a l'ecran, utilise pour afficher le titre du jeu "Witch Hunt!". Son initialisation est faite ulterieurement.
	 */
	private JLabel labelWitchHunt;
	/**
	 * Type JButton, i.e. un bouton. Il est utilise comme bouton "demarrer", pour lancer le jeu. Son initialisation est faite ulterieurement.
	 */
	private JButton btnStart;
	
	
	/**
	 * Type JPanel, utilise pour afficher la page de demande du nombre de joueurs. Elle va contenir le <code>labelNbJoueurs</code>, le <code>labelNbJoueursVirtuels</code>, le <code>fieldNbJoueurs</code>, le <code>fieldNbJoueursVirtuels</code> et le <code>btnValiderNbJoueurs</code>. Son initialisation est faite ulterieurement.
	 */
	public JPanel panelNbJoueurs;
	/**
	 * Type JLabel, i.e. un texte affiche a l'ecran, utilise pour afficher la demande du nombre de joueurs physiques. Son initialisation est faite ulterieurement.
	 */
	private JLabel labelNbJoueurs;
	/**
	 * Type JLabel, i.e. un texte affiche a l'ecran, utilise pour afficher la demande du nombre de joueurs virtuels. Son initialisation est faite ulterieurement.
	 */
	private JLabel labelNbJoueursVirtuels;
	/**
	 * Type JTextField, i.e. un champ à remplir au clavier, utilise pour recueillir le nombre de joueurs physiques. Son initialisation est faite ulterieurement.
	 */
	private JTextField fieldNbJoueurs;
	/**
	 * Type JTextField, i.e. un champ à remplir au clavier, utilise pour recueillir le nombre de joueurs virtuels. Son initialisation est faite ulterieurement.
	 */
	private JTextField fieldNbJoueursVirtuels;
	/**
	 * Type JButton, i.e. un bouton. Il est utilise comme bouton "Valider", pour valider le nombre de joueurs physiques et le nombre de joueurs virtuels. Son initialisation est faite ulterieurement.
	 */
	private JButton btnValiderNbJoueurs;
	
	
	/**
	 * Type JPanel, utilise pour afficher la page de choix de camp. Elle va contenir le <code>btnSorciere</code>, le <code>btnVillageois</code>, le <code>labelChoisirCamp</code> et le <code>labelNumJoueur</code>. Son initialisation est faite ulterieurement.
	 */
	public JPanel panelChoisirCamp;
	/**
	 * Type JButton, i.e. un bouton. Il est utilise comme bouton "Soricere", pour se declarer soricere. Son initialisation est faite ulterieurement.
	 */
	private JButton btnSorciere;
	/**
	 * Type JButton, i.e. un bouton. Il est utilise comme bouton "Villageois", pour se declarer villageois. Son initialisation est faite ulterieurement.
	 */
	private JButton btnVillageois;
	/*
	 * Type JLabel, i.e. un texte affiche a l'ecran, utilise pour afficher la demande du camp du joueur. Son initialisation est faite ulterieurement.
	 */
	private JLabel labelChoisirCamp;
	/**
	 * Type JLabel, i.e. un texte affiche a l'ecran, utilise pour afficher le numero du joueur auquel on demande le camp. Le numero varie entre 1 et 6. Son initialisation est faite ulterieurement.
	 */
	private JLabel labelNumJoueur;

	
	/**
	 * Type JPanel, utilise pour afficher la page de debut de tour du joueur. Elle va contenir le <code>btnAccuser</code>, le <code>btnRevelerCarte</code> et le <code>labelNumJoueur2</code> Son initialisation est faite ulterieurement.
	 */
	public JPanel panelJoueur;
	/**
	 * Type JButton, i.e. un bouton. Il est utilise comme bouton "Accuser un joueur", pour accuser un joueur. Son initialisation est faite ulterieurement.
	 */
	private JButton btnAccuser;
	/**
	 * Type JButton, i.e. un bouton. Il est utilise comme bouton "Reveler une carte Rumeur", pour se declarer villageois. Son initialisation est faite ulterieurement.
	 */
	private JButton btnRevelerCarte;
	/**
	 * Type JLabel, i.e. un texte affiche a l'ecran, utilise pour afficher le numero du joueur qui est en train de jouer. Le numero varie entre 1 et 6. Son initialisation est faite ulterieurement.
	 */
	private JLabel labelNumJoueur2;
	
	
	/**
	 * Type JPanel, utilise pour afficher la page d'accusation d'un joueur. Cette page va contenir le <code>labelAccusation</code>, le <code>fieldJoueurAccuse</code> et le <code>btnValiderAccuse</code>. Son initialisation est faite ulterieurement.
	 */
	public JPanel panelAccusation;
	/**
	 * Type Jlabel, i.e. un texte affiche a l'ecran, utilise pour afficher la demande du joueur a accuser. Son initialisation est faite ulterieurement.
	 */
	private JLabel labelAccusation;
	/**
	 * Type JTextField, i.e. un champ à remplir au clavier, utilise pour recueillir le numero du joueur a accuser. Les valeurs attendues sont donc entre 1 et 6. Son initialisation est faite ulterieurement.
	 */
	private JTextField fieldJoueurAccuse;
	/**
	 * Type JButton, i.e. un bouton. Il est utilise comme bouton "Valider", pour valider le joueur a accuser. Son initialisation est faite ulterieurement.
	 */
	private JButton btnValiderAccuse;

	
	/**
	 * Type JPanel, utilise pour afficher la page correspondant a un joueur accuse. Cette page va contenir le <code>labelNumJoueur3</code>, le <code>btnRevelerIdentite</code> et le <code>btnRevelerCarteWitch</code>. Son initialisation est faite ulterieurement.
	 */
	public JPanel panelJoueurAccuse;
	/**
	 * Type JLabel, i.e. un texte affiche a l'ecran, utilise pour afficher le numero du joueur qui est accuse. Le numero varie entre 1 et 6. Son initialisation est faite ulterieurement.
	 */
	private JLabel labelNumJoueur3;
	/**
	 * Type JButton, i.e. un bouton. Il est utilise comme bouton "Reveler son identite", pour reveler l'identite du joueur. Son initialisation est faite ulterieurement.
	 */
	private JButton btnRevelerIdentite;
	/**
	 * Type JButton, i.e. un bouton. Il est utilise comme bouton "Reveler carte Witch", pour reveler une carte rumeur et appliquer son effet Witch?. Son initialisation est faite ulterieurement.
	 */
	private JButton btnRevelerCarteWitch;
	
	
	/**
	 * Type JPanel, utilise pour afficher la page correspondant au choix du joueur suivant. Cette page va contenir le <code>labelChooseNextPlayer</code>, le <code>fieldChooseNextPlayer</code> et le <code>btnValiderChooseNP</code>. Son initialisation est faite ulterieurement.
	 */
	public JPanel panelChooseNextPlayer;
	/**
	 * Type JLabel, i.e. un texte affiche a l'ecran, utilise pour afficher l'instruction de choisir le joueur suivant. Son initialisation est faite ulterieurement.
	 */
	private JLabel labelChooseNextPlayer;
	/**
	 * Type JTextField, i.e. un champ à remplir au clavier, utilise pour recueillir le numero du joueur qu'on designe pour prendre le prochain tour. Les valeurs attendues sont donc entre 1 et 6. Son initialisation est faite ulterieurement.
	 */
	private JTextField fieldChooseNextPlayer;
	/**
	 * Type JButton, i.e. un bouton. Il est utilise comme bouton "Valider", pour valider le joueur designe pour le prochain tour. Son initialisation est faite ulterieurement.
	 */
	private JButton btnValiderChooseNP;
	
	
	/**
	 * Type boolean, utilise lors de l'affichage des cartes du joueur pour savoir si la zone 1 sur 4 est occupee (true), ou prete a accueillir une carte (false). Initialise a false.
	 */
	private	boolean case1_Occupee = false;
	/**
	 * Type boolean, utilise lors de l'affichage des cartes du joueur pour savoir si la zone 2 sur 4 est occupee (true), ou prete a accueillir une carte (false). Initialise a false.
	 */
	private boolean case2_Occupee = false;
	/**
	 * Type boolean, utilise lors de l'affichage des cartes du joueur pour savoir si la zone 3 sur 4 est occupee (true), ou prete a accueillir une carte (false). Initialise a false.
	 */
	private boolean case3_Occupee = false;
	/**
	 * Type boolean, utilise lors de l'affichage des cartes du joueur pour savoir si la zone 4 sur 4 est occupee (true), ou prete a accueillir une carte (false). Initialise a false.
	 */
	private boolean case4_Occupee = false;
	

	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>AngryMob</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnAngryMob;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>Broomstick</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnBroomstick;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>Cauldron</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnCauldron;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>Wart</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnWart;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>BlackCat</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnBlackCat;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>DuckingStool</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnDuckingStool;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>EvileEye</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnEvilEye;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>HookedNose</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnHookedNose;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>PetNewt</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnPetNewt;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>PointedHat</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnPointedHat;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>TheInquisition</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnTheInquisition;
	/**
	 * Type JButton, i.e. un bouton, utilise comme image-bouton, representant la carte <code>Toad</code> comme image cliquable. Initialisation utlterieure.
	 */
	private JButton btnToad;
	
	
	
	

	

	/**
	 * Methode principale, utilisee pour lancer l'application
	 * @param args : un tableau de <code>String</code>
	 */
	public static void main(String[] args) {
		Systeme systeme = Systeme.getInstance();
		WitchHunt window = new WitchHunt();
		Systeme.getInstance().vue = window;
		window.frame.setVisible(true);
		
		// On desactive les panels sauf celui du depart
		window.cacherTout();
		window.afficherPanelDebut();
		
		

		ControleurNbJoueur controleurBouton = new ControleurNbJoueur(window.btnValiderNbJoueurs, window.fieldNbJoueurs, window.fieldNbJoueursVirtuels, window, systeme);
		ControleurChoixCamp controleurChoixCamp = new ControleurChoixCamp(systeme,  window.btnSorciere, window.btnVillageois, window);
		ControleurChoisirQuoiFaire controleurChoisirQuoiFaire = new ControleurChoisirQuoiFaire(systeme, window.btnAccuser, window.btnRevelerCarte, window);
		ControleurAccuser controleurAccuser = new ControleurAccuser(systeme, window.fieldJoueurAccuse, window.btnValiderAccuse, window);
		ControleurJoueurAccuse controleurJoueurAccuse = new ControleurJoueurAccuse(systeme, window.btnRevelerCarteWitch, window.btnRevelerIdentite, window);
		ControleurChooseNP controleurChooseNP = new ControleurChooseNP(systeme, window.fieldChooseNextPlayer, window.btnValiderChooseNP, window);
	}

	/**
	 * Constructeur de la vue, pour creer l'application. Fait appel a la methode <code>inittialize()</code>
	 */
	public WitchHunt() {
		initialize();
	}

	/**
	 * Initialise le contenu de la fenetre. C'est ici que sont initialises les differents panels.
	 */
	private void initialize() {
		//Ici, on cree la frame
		frame = new JFrame();
		frame.setBounds(50, 50, 1050, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		
		//Ici, on cree le panel affiche lorsque l'on lance l'application.
		this.panelDebut = new JPanel();
		panelDebut.setBounds(0, 0, 1050, 800);
		this.panelDebut.setLayout(null);
		
		this.labelWitchHunt = new JLabel("Witch Hunt!");
		labelWitchHunt.setFont(new Font("MS Gothic", Font.ITALIC, 99));
		//Nathan tu seras meilleur que moi en centrage amuses toi (je sais pas trop pk le truc automatique marche pas ptet r�essayer).
		labelWitchHunt.setBounds(265, 300 , 800, 200);
		this.panelDebut.add(this.labelWitchHunt);
		
		this.btnStart = new JButton("Jouer");
		btnStart.setFont(new Font("MS Gothic", Font.PLAIN, 46));
		this.btnStart.setBounds(425, 500, 200, 100);
		this.panelDebut.add(this.btnStart);
		frame.getContentPane().add(panelDebut);
		
		// Changement de couleur de fond
		this.panelDebut.setBackground(Color.GRAY);
		
		this.btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDebut.setEnabled(false);
				panelDebut.setVisible(false);
				
				panelNbJoueurs.setEnabled(true);
				panelNbJoueurs.setVisible(true);
			}
		});
		
		
		
		//Ici, on cree le panel affiche pour choisir le nombre de joueurs.
		this.panelNbJoueurs = new JPanel();
		panelNbJoueurs.setBounds(0, 0, 1050, 800);
		this.panelNbJoueurs.setLayout(null);
		
		//On utilise html pour faire des retours a la ligne sinon ca ne marche pas
		this.labelNbJoueurs = new JLabel("<html>Combien desirez vous de joueurs dans la partie? <br/>(Le minimum de 3, et le maximum est de 6.)</html>");
		labelNbJoueurs.setFont(new Font("MS Gothic", Font.PLAIN, 30));
		labelNbJoueurs.setBounds(50, 50 , 1500, 75);
		this.panelNbJoueurs.add(this.labelNbJoueurs);
		
		fieldNbJoueurs = new JTextField();
		fieldNbJoueurs.setBounds(425, 200, 200, 50);
		panelNbJoueurs.add(fieldNbJoueurs);
		fieldNbJoueurs.setColumns(10);		

		this.labelNbJoueursVirtuels = new JLabel("<html>Combien desirez vous de joueurs virtuels? <br/>(Le maximum est le nombre de joueurs totaux que vous avez saisi.)</html>");
		labelNbJoueursVirtuels.setFont(new Font("MS Gothic", Font.PLAIN, 30));
		labelNbJoueursVirtuels.setBounds(50, 300 , 1500, 75);
		this.panelNbJoueurs.add(this.labelNbJoueursVirtuels);
		
		fieldNbJoueursVirtuels = new JTextField();
		fieldNbJoueursVirtuels.setBounds(425, 450, 200, 50);
		panelNbJoueurs.add(fieldNbJoueursVirtuels);
		fieldNbJoueursVirtuels.setColumns(10);
		
		this.btnValiderNbJoueurs = new JButton("Valider");
		btnValiderNbJoueurs.setFont(new Font("MS Gothic", Font.PLAIN, 46));
		this.btnValiderNbJoueurs.setBounds(425, 600, 200, 100);
		this.panelNbJoueurs.add(this.btnValiderNbJoueurs);
		
		// Changement de couleur de fond
		this.panelNbJoueurs.setBackground(Color.GRAY);
		frame.getContentPane().add(panelNbJoueurs);
		
		
			
		//Ici, on cree le panel de choix de camp.
		this.panelChoisirCamp = new JPanel();
		this.panelChoisirCamp.setBounds(0, 0, 1050, 800);
		this.panelChoisirCamp.setLayout(null);
		
		// Je le desactive car sinon le bouton sorciere s'affiche dans le panel de choix du nb de joueurs
		
		
		// On cree les boutons et labels dont on a besoin, et on les ajoute au panel choisirCamp
		this.labelNumJoueur = new JLabel("Joueur "+(Systeme.getInstance().getIndiceJoueurActuel()+1)+" :");
		this.labelNumJoueur.setFont(new Font("MS Gothic", Font.PLAIN, 30));
		this.labelNumJoueur.setBounds(50, 350 , 1500, 75);
		this.panelChoisirCamp.add(this.labelNumJoueur);

		this.labelChoisirCamp = new JLabel("<html>Veuillez choisir votre camp :</html>");
		this.labelChoisirCamp.setFont(new Font("MS Gothic", Font.PLAIN, 30));
		this.labelChoisirCamp.setBounds(50, 400 , 1500, 75);
		this.panelChoisirCamp.add(this.labelChoisirCamp);
		
		this.btnSorciere = new JButton("Sorciere");
		this.btnSorciere.setBounds(150, 500, 300, 100);
		this.btnSorciere.setFont(new Font("MS Gothic", Font.PLAIN, 46));
		this.panelChoisirCamp.add(this.btnSorciere);

		this.btnVillageois = new JButton("Villageois");
		this.btnVillageois.setBounds(600, 500, 300, 100);
		this.btnVillageois.setFont(new Font("MS Gothic", Font.PLAIN, 46));
		this.panelChoisirCamp.add(this.btnVillageois);		
		
		// On ajoute le panel a la vue actuelle
		frame.getContentPane().add(this.panelChoisirCamp);
		
		
		
		/* Panel joueur */
		
		//On cree le panel global
		this.panelJoueur = new JPanel();
		this.panelJoueur.setBounds(0,0,1050,800);
		this.panelJoueur.setLayout(null);
		
		
		
		// Affichage du joueur en cours (avec une boucle)
		this.labelNumJoueur2 = new JLabel("Joueur "+(Systeme.getInstance().getIndiceJoueurActuel()+1)+" :");
		this.labelNumJoueur2.setFont(new Font("MS Gothic", Font.PLAIN, 30));
		this.labelNumJoueur2.setBounds(50, 350 , 1500, 75);
		this.panelJoueur.add(this.labelNumJoueur2);
		
		// Ajout du bouton pour accuser
		this.btnAccuser = new JButton("Accuser un joueur");
		this.btnAccuser.setBounds(100, 500, 400, 100);
		this.btnAccuser.setFont(new Font("MS Gothic", Font.PLAIN, 25));
		this.panelJoueur.add(this.btnAccuser);
		
		// Ajout du bouton pour reveler
		this.btnRevelerCarte = new JButton("Reveler une carte Rumeur");
		this.btnRevelerCarte.setBounds(600, 500, 400, 100);
		this.btnRevelerCarte.setFont(new Font("MS Gothic", Font.PLAIN, 25));
		this.panelJoueur.add(this.btnRevelerCarte);

		// Ajout des cartes à afficher
		// Pour cela il faut s'occuper de la distribution des cartes
		
	
		frame.getContentPane().add(panelJoueur);
		
		/* Panel accusation */
		
		//On cree le panel global
		this.panelAccusation = new JPanel();
		this.panelAccusation.setBounds(0,0,1050,800);
		this.panelAccusation.setLayout(null);
		
		
		
		// Affichage du joueur en cours (avec une boucle)
		this.labelAccusation = new JLabel("Quel joueur souhaitez vous accuser?");
		this.labelAccusation.setFont(new Font("MS Gothic", Font.PLAIN, 30));
		this.labelAccusation.setBounds(50, 350 , 1500, 75);
		this.panelAccusation.add(this.labelAccusation);
		
		// Ajout du TextField
		this.fieldJoueurAccuse = new JTextField();
		fieldJoueurAccuse.setBounds(425, 450, 200, 50);
		this.panelAccusation.add(this.fieldJoueurAccuse);
		this.fieldJoueurAccuse.setColumns(10);
		
		//Ajout du bouton valider
		this.btnValiderAccuse = new JButton("Valider");
		this.btnValiderAccuse.setFont(new Font("MS Gothic", Font.PLAIN, 46));
		this.btnValiderAccuse.setBounds(425, 600, 200, 100);
		this.panelAccusation.add(this.btnValiderAccuse);
		
	
		frame.getContentPane().add(panelAccusation);
		
		/* Panel JoueurAccuse */
		
		//On cree le panel global
		this.panelJoueurAccuse = new JPanel();
		this.panelJoueurAccuse.setBounds(0,0,1050,800);
		this.panelJoueurAccuse.setLayout(null);
		
		
		
		// Affichage du joueur en cours (avec une boucle)
		this.labelNumJoueur3 = new JLabel("Joueur "+(Systeme.getInstance().getIndiceJoueurActuel()+1)+" :");
		this.labelNumJoueur3.setFont(new Font("MS Gothic", Font.PLAIN, 30));
		this.labelNumJoueur3.setBounds(50, 350 , 1500, 75);
		this.panelJoueurAccuse.add(this.labelNumJoueur3);
		
		// Ajout du bouton pour accuser
		this.btnRevelerIdentite = new JButton("Reveler son identite");
		this.btnRevelerIdentite.setBounds(100, 500, 400, 100);
		this.btnRevelerIdentite.setFont(new Font("MS Gothic", Font.PLAIN, 25));
		this.panelJoueurAccuse.add(this.btnRevelerIdentite);
		
		// Ajout du bouton pour reveler
		this.btnRevelerCarteWitch = new JButton("Reveler une carte Rumeur");
		this.btnRevelerCarteWitch.setBounds(600, 500, 400, 100);
		this.btnRevelerCarteWitch.setFont(new Font("MS Gothic", Font.PLAIN, 25));
		this.panelJoueurAccuse.add(this.btnRevelerCarteWitch);
		
	
		frame.getContentPane().add(panelJoueurAccuse);
		
		
		
		/* Panel ChooseNextPlayer */
		
		//On cree le panel global
		this.panelChooseNextPlayer = new JPanel();
		this.panelChooseNextPlayer.setBounds(0,0,1050,800);
		this.panelChooseNextPlayer.setLayout(null);
		
		// Affichage du joueur en cours (avec une boucle)
		this.labelChooseNextPlayer = new JLabel("Veuillez choisir le joueur suivant");
		this.labelChooseNextPlayer.setFont(new Font("MS Gothic", Font.PLAIN, 30));
		this.labelChooseNextPlayer.setBounds(50, 350 , 1500, 75);
		this.panelChooseNextPlayer.add(this.labelChooseNextPlayer);
		
		// Ajout du TextField
		this.fieldChooseNextPlayer = new JTextField();
		fieldChooseNextPlayer.setBounds(425, 450, 200, 50);
		this.panelChooseNextPlayer.add(this.fieldChooseNextPlayer);
		this.fieldChooseNextPlayer.setColumns(10);
		
		//Ajout du bouton valider
		this.btnValiderChooseNP = new JButton("Valider");
		this.btnValiderChooseNP.setFont(new Font("MS Gothic", Font.PLAIN, 46));
		this.btnValiderChooseNP.setBounds(425, 600, 200, 100);
		this.panelChooseNextPlayer.add(this.btnValiderChooseNP);
		
		frame.getContentPane().add(panelChooseNextPlayer);

		
		
		// Declaration de tous les bouton-image
		
		Icon iconAngryMob = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/AngryMob.PNG");
		this.btnAngryMob = new JButton(iconAngryMob);
		this.btnAngryMob.setVisible(false);
		
		frame.getContentPane().add(this.btnAngryMob);
		
		Icon iconBroomstick = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/Broomstick.PNG");
		this.btnBroomstick = new JButton(iconBroomstick);
		this.btnBroomstick.setVisible(false);
		frame.getContentPane().add(this.btnBroomstick);
		
		Icon iconCauldron = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/Cauldron.PNG");
		this.btnCauldron = new JButton(iconCauldron);
		this.btnCauldron.setVisible(false);
		frame.getContentPane().add(this.btnCauldron);
		
		Icon iconWart = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/Wart.PNG");
		this.btnWart = new JButton(iconWart);
		this.btnWart.setVisible(false);
		frame.getContentPane().add(this.btnWart);
		
		Icon iconBlackCat = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/BlackCat.PNG");
		this.btnBlackCat = new JButton(iconBlackCat);
		this.btnBlackCat.setVisible(false);
		frame.getContentPane().add(this.btnBlackCat);
		
		Icon iconDuckingStool = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/DuckingStool.PNG");
		this.btnDuckingStool = new JButton(iconDuckingStool);
		this.btnDuckingStool.setVisible(false);
		frame.getContentPane().add(this.btnDuckingStool);
		
		Icon iconEvilEye = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/EvilEye.PNG");
		this.btnEvilEye = new JButton(iconEvilEye);
		this.btnEvilEye.setVisible(false);
		frame.getContentPane().add(this.btnEvilEye);
		
		Icon iconHookedNose = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/HookedNose.PNG");
		this.btnHookedNose = new JButton(iconHookedNose);
		this.btnHookedNose.setVisible(false);
		frame.getContentPane().add(this.btnHookedNose);
		
		Icon iconPetNewt = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/PetNewt.PNG");
		this.btnPetNewt = new JButton(iconPetNewt);
		this.btnPetNewt.setVisible(false);
		frame.getContentPane().add(this.btnPetNewt);
		
		Icon iconPointedHat = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/PointedHat.PNG");
		this.btnPointedHat = new JButton(iconPointedHat);
		this.btnPointedHat.setVisible(false);
		frame.getContentPane().add(this.btnPointedHat);
		
		Icon iconTheInquisition = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/TheInquisition.PNG");
		this.btnTheInquisition = new JButton(iconTheInquisition);
		this.btnTheInquisition.setVisible(false);
		frame.getContentPane().add(this.btnTheInquisition);

		Icon iconToad = new ImageIcon("D://eclipse/WitchHunt04-01-2022/src/Vue/Toad.PNG");
		this.btnToad = new JButton(iconToad);
		this.btnToad.setVisible(false);
		frame.getContentPane().add(this.btnToad);
	}
	
	
	/**
	 * Cache tout les panels, qu'ils soient visibles ou non. Fait appel aux methodes <code>setEnable</code> et <code>setVisible</code>.
	 */
	public void cacherTout() {
		this.panelDebut.setEnabled(false);
		this.panelDebut.setVisible(false);
		
		this.panelNbJoueurs.setVisible(false);
		this.panelNbJoueurs.setEnabled(false);
		
		this.panelChoisirCamp.setVisible(false);
		this.panelChoisirCamp.setEnabled(false);
		
		this.panelJoueur.setVisible(false);
		this.panelJoueur.setEnabled(false);
		
		this.panelAccusation.setEnabled(false);
		this.panelAccusation.setVisible(false);
		
		this.panelJoueurAccuse.setEnabled(false);
		this.panelJoueurAccuse.setVisible(false);
		
		this.panelChooseNextPlayer.setEnabled(false);
		this.panelChooseNextPlayer.setVisible(false);
	}
	
	/**
	 * Affiche le panel de debut <code>panelDebut</code>. Fait appel aux methodes <code>setEnable</code> et <code>setVisible</code>.
	 */
	public void afficherPanelDebut() {
		this.panelDebut.setVisible(true);
		this.panelDebut.setEnabled(true);
	}
	
	/**
	 * Affiche le panel de choix de camp <code>panelChoisirCamp</code>. Fait appel aux methodes <code>setEnable</code> et <code>setVisible</code>.
	 */
	public void afficherChoisirCamp() {
		this.panelChoisirCamp.setVisible(true);
		this.panelChoisirCamp.setEnabled(true);
	}
	
	/**
	 * Affiche le panel de choix de camp <code>panelChoisirCamp</code>. Fait appel aux methodes <code>setEnable</code> et <code>setVisible</code>.
	 */
	public void afficherChoixCamp() {
		this.panelChoisirCamp.setVisible(true);
		this.panelChoisirCamp.setEnabled(true);
	}
	
	/**
	 * Affiche le panel du tour du joueur <code>panelJoueur</code>. Fait appel aux methodes <code>setEnable</code> et <code>setVisible</code>.
	 */
	public void afficherPanelJoueur() {
		this.panelJoueur.setVisible(true);
		this.panelJoueur.setEnabled(true);
	}
	
	/**
	 * Affiche le panel d'accusation d'un joueur <code>panelAccusation</code>. Fait appel aux methodes <code>setEnable</code> et <code>setVisible</code>.
	 */
	public void afficherPanelAccusation() {
		this.panelAccusation.setVisible(true);
		this.panelAccusation.setEnabled(true);
	}
	
	/**
	 * Affiche le panel <code>panelJoueurAccuse</code> d'un joueur accuse. Fait appel aux methodes <code>setEnable</code> et <code>setVisible</code>.
	 */
	public void afficherPanelJoueurAccuse() {
		this.panelJoueurAccuse.setVisible(true);
		this.panelJoueurAccuse.setEnabled(true);
	}
	
	/**
	 * Affiche le panel de choix du joueur suivant <code>panelChooseNextPlayer</code>. Fait appel aux methodes <code>setEnable</code> et <code>setVisible</code>.
	 */
	public void afficherPanelChooseNP() {
		this.panelChooseNextPlayer.setVisible(true);
		this.panelChooseNextPlayer.setEnabled(true);
	}
	
	/**
	 * Affiche les cartes du joueur en cours sous forme d'image-bouton. Fait appel a la methode <code>placerCartes</code>.
	 * @param joueur le joueur pour qui on veut afficher les cartes
	 */
	public void afficherCartes(Joueur joueur) {
		
		for (int k=0 ; k < joueur.cartesEnMain.size() ; k++) {
			
			if (joueur.cartesEnMain.get(k) instanceof AngryMob) {
				this.placerCarte(joueur, btnAngryMob, this);
			} else if (joueur.cartesEnMain.get(k) instanceof Broomstick) {
				this.placerCarte(joueur, btnBroomstick, this);
			} else if (joueur.cartesEnMain.get(k) instanceof Cauldron) {
				this.placerCarte(joueur, btnCauldron, this);
			} else if (joueur.cartesEnMain.get(k) instanceof Wart) {
				this.placerCarte(joueur, btnWart, this);
			} else if (joueur.cartesEnMain.get(k) instanceof BlackCat) {
				this.placerCarte(joueur, btnBlackCat, this);
			} else if (joueur.cartesEnMain.get(k) instanceof DuckingStool) {
				this.placerCarte(joueur, btnDuckingStool, this);
			} else if (joueur.cartesEnMain.get(k) instanceof EvilEye) {
				this.placerCarte(joueur, btnEvilEye, this);
			} else if (joueur.cartesEnMain.get(k) instanceof HookedNose) {
				this.placerCarte(joueur, btnHookedNose, this);
			} else if (joueur.cartesEnMain.get(k) instanceof PetNewt) {
				this.placerCarte(joueur, btnPetNewt, this);
			} else if (joueur.cartesEnMain.get(k) instanceof PointedHat) {
				this.placerCarte(joueur, btnPointedHat, this);
			} else if (joueur.cartesEnMain.get(k) instanceof TheInquisition) {
				this.placerCarte(joueur, btnTheInquisition, this);
			} else if (joueur.cartesEnMain.get(k) instanceof Toad) {
				this.placerCarte(joueur, btnToad, this);
			} else {
				System.out.println("Erreur lors de la lecture des cartes du joueur.");
			}
		}
	}
	
	/**
	 * Utilise pour remettre a 0 l'affichage des cartes, i.e. les enlever du panel (avec l'enlevage des actions de click aussi).
	 */
	public void resetCartes() {
		//On reset tout pour pouvoir mettre � jour l'affichage des cartes.
		this.case1_Occupee = false;
		this.case2_Occupee = false;
		this.case3_Occupee = false;
		this.case4_Occupee = false;
		
		//On cache chaque carte
		this.btnAngryMob.setVisible(false);
		this.btnBroomstick.setVisible(false);
		this.btnCauldron.setVisible(false);
		this.btnWart.setVisible(false);
		this.btnBlackCat.setVisible(false);
		this.btnDuckingStool.setVisible(false);
		this.btnEvilEye.setVisible(false);
		this.btnHookedNose.setVisible(false);
		this.btnPetNewt.setVisible(false);
		this.btnPointedHat.setVisible(false);
		this.btnTheInquisition.setVisible(false);
		this.btnToad.setVisible(false);
		
		//On enl�ve chaque actionlistener de chaque carte.
		for( ActionListener al : this.btnAngryMob.getActionListeners() ) {
	        this.btnAngryMob.removeActionListener( al );
	    }
		for( ActionListener al : this.btnCauldron.getActionListeners() ) {
	        this.btnCauldron.removeActionListener( al );
	    }
		for( ActionListener al : this.btnBroomstick.getActionListeners() ) {
	        this.btnBroomstick.removeActionListener( al );
	    }
		for( ActionListener al : this.btnWart.getActionListeners() ) {
	        this.btnWart.removeActionListener( al );
	    }
		for( ActionListener al : this.btnBlackCat.getActionListeners() ) {
	        this.btnBlackCat.removeActionListener( al );
	    }
		for( ActionListener al : this.btnDuckingStool.getActionListeners() ) {
	        this.btnDuckingStool.removeActionListener( al );
	    }
		for( ActionListener al : this.btnEvilEye.getActionListeners() ) {
	        this.btnEvilEye.removeActionListener( al );
	    }
		for( ActionListener al : this.btnHookedNose.getActionListeners() ) {
	        this.btnHookedNose.removeActionListener( al );
	    }
		for( ActionListener al : this.btnPetNewt.getActionListeners() ) {
	        this.btnPetNewt.removeActionListener( al );
	    }
		for( ActionListener al : this.btnPointedHat.getActionListeners() ) {
	        this.btnPointedHat.removeActionListener( al );
	    }
		for( ActionListener al : this.btnTheInquisition.getActionListeners() ) {
	        this.btnTheInquisition.removeActionListener( al );
	    }
		for( ActionListener al : this.btnToad.getActionListeners() ) {
	        this.btnToad.removeActionListener( al );
	    }

	}

	/**
	 * Dispose les carte du joueur en fonction de l'espace disponible.
	 * @param joueur le joueur en cours
	 * @param btn le bouton-image a placer
	 * @param vue la vue en cours
	 */
	public void placerCarte(Joueur joueur, JButton btn, WitchHunt vue) {
		if (vue.case1_Occupee) {
			if (vue.case2_Occupee) {
				if (vue.case3_Occupee) {
					if (vue.case4_Occupee) {
						System.out.println("Erreur, le nombre de carte est deja atteint");
					} else {
						vue.case4_Occupee = true;
						//Affichage de la carte dans le quatrieme emplacement
						btn.setBounds(800, 50, 200,270);
					}
				} else {
					vue.case3_Occupee = true;
					//Affichage de la carte dans le troisieme emplacement
					btn.setBounds(550, 50, 200,270);
				}
			} else {
				vue.case2_Occupee = true;
				// Affichage de la carte dans le deuxieme emplacement
				btn.setBounds(300, 50, 200,270);
			}
			
		} else {
			vue.case1_Occupee = true;
			// Affichage de la carte dans le premier emplacement
			btn.setBounds(50, 50, 200,270);
		}
		btn.setVisible(true);
	}
	
	/**
	 * Utilise pour faire appel a la methode <code>realiserEffetHunt</code> des cartes, lors du click sur le bouton-image associe.
	 */
	public void revelerCarteHunt() {
		this.btnAngryMob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AngryMob angryMob = new AngryMob();
				angryMob.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnBroomstick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Broomstick broomstick = new Broomstick();
				broomstick.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnCauldron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cauldron cauldron = new Cauldron();
				cauldron.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnWart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wart wart = new Wart();
				wart.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnBlackCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BlackCat blackCat = new BlackCat();
				blackCat.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnDuckingStool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DuckingStool duckingStool = new DuckingStool();
				duckingStool.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnEvilEye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EvilEye evilEye = new EvilEye();
				evilEye.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnHookedNose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HookedNose hookedNose = new HookedNose();
				hookedNose.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnPetNewt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PetNewt petNewt = new PetNewt();
				petNewt.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnPointedHat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PointedHat pointedHat = new PointedHat();
				pointedHat.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnTheInquisition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TheInquisition theInquisition = new TheInquisition();
				theInquisition.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		this.btnToad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Toad toad = new Toad();
				toad.realiserEffetHunt(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), null, Systeme.getInstance().getTapis());
			}
		});
		
	}
	
	
	//Attention il faut aussi changer joueurAccusant.
	/**
	 * Utilise pour faire appel a la methode <code>realiserEffetWitch</code> des cartes, lors du click sur le bouton-image associe.
	 * @param joueurAccusant
	 */
	public void revelerCarteWitch(Joueur joueurAccusant) {
		this.btnAngryMob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AngryMob angryMob = new AngryMob();
				angryMob.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnBroomstick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Broomstick broomstick = new Broomstick();
				broomstick.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnCauldron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cauldron cauldron = new Cauldron();
				cauldron.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnWart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wart wart = new Wart();
				wart.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnBlackCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BlackCat blackCat = new BlackCat();
				blackCat.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnDuckingStool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DuckingStool duckingStool = new DuckingStool();
				duckingStool.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnEvilEye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EvilEye evilEye = new EvilEye();
				evilEye.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnHookedNose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HookedNose hookedNose = new HookedNose();
				hookedNose.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnPetNewt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PetNewt petNewt = new PetNewt();
				petNewt.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnPointedHat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PointedHat pointedHat = new PointedHat();
				pointedHat.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnTheInquisition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TheInquisition theInquisition = new TheInquisition();
				theInquisition.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		this.btnToad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Toad toad = new Toad();
				toad.realiserEffetWitch(Systeme.getInstance(), Systeme.getInstance().getJoueurActuel(), joueurAccusant, Systeme.getInstance().getTapis());
			}
		});
		
	}
	
	/**
	 * Met a jour le numero du joueur en cours dans les labels <code>labelNumJoueur</code>, <code>labelNumJoueur2</code> et <code>labelNumJoueur3</code>.
	 */
	public void majNumJoueur() {
		if(Systeme.getInstance().getIndiceJoueurActuel() == Systeme.getInstance().GetNbJoueurs()-1) {
			this.labelNumJoueur.setText("Joueur 1 :");
			this.labelNumJoueur2.setText("Joueur 1 :");
			this.labelNumJoueur3.setText("Joueur 1 :");


		}
		else {
			this.labelNumJoueur.setText("Joueur "+(Systeme.getInstance().getIndiceJoueurActuel()+2)+" :");
			this.labelNumJoueur2.setText("Joueur "+(Systeme.getInstance().getIndiceJoueurActuel()+2)+" :");
			this.labelNumJoueur3.setText("Joueur "+(Systeme.getInstance().getIndiceJoueurActuel()+1)+" :");
		}
	}
}