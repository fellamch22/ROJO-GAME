import java.util.ArrayList;
import java.util.Scanner;

public class LireDonnes {

	private static String repertoire = "/Users/sulifang/Desktop/POOV26/image/";
	
	//Default Scanner
	private static Scanner s = new Scanner(System.in);

	public static void printG(String s, VueProjet v) {
		System.out.println(s);
		if (v != null)
			v.getTextArea().append(s + "\n\n");
	}

	public static boolean nouvellePartie() {
		s = new Scanner(System.in);
		int choix = 0 ;
		while (choix != 1 && choix != 2 ) {
		    System.out.println("Voulez vous lancer une nouvelle Partie : \n1- Oui \n2- Non");
		    choix = s.nextInt() ;
		}
		if ( choix == 1 ) return true ;
		return false ;
		
		}
		
	
	public static boolean modeJeu() {
		s = new Scanner(System.in);
		int choix=0;
		while (choix != 1 && choix != 2) {
			System.out.println("Veuillez Choisir le mode de Jeu " + "\n1 - Mur Colore " + "\n2 - Mur Incolore ");
			choix = s.nextInt();
			if (choix == 1)
				return true;
			if (choix == 2)
				break;
		}
		return false;
	}

	public static boolean modeGraphique() {
		s = new Scanner(System.in);
		int choix=0;
		while (choix != 1 && choix != 2) {
			System.out.println("Veuillez Choisir le mode de Jeu " + "\n1 - Mode Texte " + "\n2 - Mode Graphique ");
			choix = s.nextInt();
			if (choix == 1)
				break;
			if (choix == 2)
				return true;
		}			
		return false;
	}

	public static int getnextchoice(VueProjet v) {
		int choix;
		while (true) {
			if (v != null) {
				s = new Scanner(v.getInputArea().getText());
			}
			if (s.hasNextInt()) {
				choix = s.nextInt();
				if (v != null) {
					v.getInputArea().setText("");
				}
				break;
			}
		}
		System.out.println(" choix   : "+choix);
		return choix;
	}

	public static ArrayList<Joueur> lireJoueurs(boolean modeJeu) {
		String nom;
		int choix = 0;

		ArrayList<Joueur> l = new ArrayList<>();
		while (choix < 2 || choix > 4) {
			System.out.println(" Veuillez introduire Le nombre de joueurs dans la partie (2 minimum , maximum 4");
			choix = s.nextInt();
		}
		s.nextLine();
		for (int i = 0; i < choix; i++) {

			System.out.println(" Veuillez Introduire le nom  du joueur " + (i + 1));

			nom = s.nextLine();
			if (nom.equals(""))
				nom = "Anonyme" + i;
			l.add(new Joueur(nom, 0, modeJeu));

		}
		return l;
	}

	public static boolean zoneSelectionne(ZonesCommunes z, VueProjet v) {// retourne true si il choisit une fabrique ,
																			// false si le
		// centre de table
		int choix = -1;

		while (choix < 0) {
			printG(" Voulez vous   :" + "\n1-9 - prendre des tuiles a partir d'une fabrique"
					+ "\n0 - prendre des tuiles a partir du centre de la table "
					+ "\n Veuillez introduire le numero de votre choix : ", v);
			choix = getnextchoice(v); // s.nextInt();

			if (choix == 0 && z.getCentre().estVide()) {
				choix = -1;
				printG(" Le centre de table est vide Veuillez selectionner une Fabrique ", v);
			}
			if (choix >= 1 && z.toutesFabriquesVides()) {
				choix = -1;
				printG("Toutes les fabriques sont vides  Veuillez choisir le centre de la table ", v);
			}

		}

		if (choix > 0)
			return true;
		
		return false;
	}

	public static int numFabrique(int nbFabriques, ZonesCommunes zone, VueProjet v) {
		int choix = 0;
		while (choix <= 0 || choix > nbFabriques) {
			printG(" Veuillez introduire le numero de Fabrique choisie \n" + "( choisissez un numero entre 1 et "
					+ nbFabriques + ")", v);
			choix = getnextchoice(v); // s.nextInt();
			if (choix > 0 && choix <= nbFabriques) {
				if (zone.getFabrique(choix - 1).estVide()) {
					choix = 0;
					printG("  Veuillez choisir une fabrique non vide ", v);
				}
			} else {
				printG(" Veuillez introduire un nombre valide ", v);
				choix = 0;
			}
		}

		return choix;
	}

	public static char choisirCouleur(Zone z, VueProjet v) {
		int choix = 0;
		while (choix <= 0 || choix > 5) {

			printG(" veuillez introduire le numero de la couleur que vous allez prendre "
					+ "\n1 - Bleu     ( Nombre de Tuiles " + z.nbTuiles('b') + " )"
					+ "\n2 - Blanc    ( Nombre de Tuiles " + z.nbTuiles('l') + " )"
					+ "\n3 - Jaune    ( Nombre de Tuiles " + z.nbTuiles('j') + " )"
					+ "\n4 - Rouge    ( Nombre de Tuiles " + z.nbTuiles('r') + " )"
					+ "\n5 - Noir     ( Nombre de Tuiles " + z.nbTuiles('n') + " )", v);
			choix = getnextchoice(v); // s.nextInt();

			switch (choix) {
			case 1:
				if (z.nbTuiles('b') != 0)
					return 'b';
				choix = 0;
				break;
			case 2:
				if (z.nbTuiles('l') != 0)
					return 'l';
				choix = 0;

				break;

			case 3:
				if (z.nbTuiles('j') != 0)
					return 'j';
				choix = 0;

				break;

			case 4:
				if (z.nbTuiles('r') != 0)
					return 'r';
				choix = 0;

				break;
			case 5:
				if (z.nbTuiles('n') != 0)
					return 'n';
				choix = 0;

				break;

			default:
				break;
			}

		}
		return 'x';
	}

	public static int choixTypeTuiles(Zone z, VueProjet v) {
		int choix = 0;
		while (choix <= 0 || choix > 3) {
			printG(" Voulez vous recuperer " + "\n1- Des tuiles colores seulement " + "\n2- Des tuiles Joker seulement "
					+ "\n3- Des tuiles colores et Joker " + "\n Veuillez introduire le numero de votre choix ", v);
			choix = getnextchoice(v); // s.nextInt();
			if ((choix == 1 || choix == 3 ) && z.nbTuilesColore() == 0) {
				choix = 0;
				printG(" pas de tuiles colore dans cette zone veuillez choisir les tuiles joker ", v);
			} else if (choix == 2 && z.nbTuilesJoker() == 0) {
				choix = 0;
				printG(" pas de tuiles Joker dans cette zone veuillez choisir les tuiles joker ", v);
			}
		}

		return choix;
	}

	public static boolean choixDepos(Joueur J, VueProjet v,boolean multiClick) {
		// retourne true si le joueur choisie une ligne motif , false si il
		// choisie le plancher
		if (v != null) v.setmultiClick(multiClick);
		int choix = -1;
		while (choix < 0 || choix > 5) {
			printG(" Voulez vous deposer vos tuiles " + "\n1-5 - Sur Une Ligne Motif " + "\n0- Sur le plancher     ",
					v);
			choix = getnextchoice(v); // s.nextInt();
		/*	if ( J.getP().aucuneLigneMotifDisponible() ) {
				choix = -1;
				printG(" Aucune ligne motif n est disponible.\nVeuillez choisir le Plancher ", v);
			}*/
		}

		if (choix == 0)
			return false;
		return true;
	}

	public static int numLigne(VueProjet v) {// lire le numero de la ligne motif
		int choix = 0;

		while (choix <= 0 || choix > 5) {
			printG(" Veuillez introduire le numero de la ligne " + "\n( Introduisez un numero entre 1 et 5 )", v);
			choix = getnextchoice(v); // s.nextInt();
		}

		return choix;
	}

	public static int colonneMur(String nom, VueProjet v) {
		int choix = 0;
		while (choix <= 0 || choix > 5) {
			printG(nom + " ,  Veuillez introduire le numero de la colonne du mur ou vous souhaitez deposer votre tuile "
					+ "\n Veuillez introduire un numero entre (  1 et 5 ) ", v);
			choix = getnextchoice(v); // s.nextInt();
		}
		return choix;
	}

	public static String getRepertoire() {
		return repertoire;
	}
	
	public static void setRepertoire(String cheminAbsolu) {
		 repertoire = cheminAbsolu ;
	}
	
	
}
