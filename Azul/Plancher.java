import java.util.ArrayList;

public class Plancher {
	// Variable
	private CasePlancher[] plancher;

	// Constructeur
	// on initialise le tableau de CasePlancher, avec chaque case ayant une valeur
	// differente
	// Constructeur de casePlancher : public CasePlancher(int points) // to
	// ameliorate
	public Plancher() {
		this.plancher = new CasePlancher[7];
		this.plancher[0] = new CasePlancher(-1);
		this.plancher[1] = new CasePlancher(-1);
		this.plancher[2] = new CasePlancher(-2);
		this.plancher[3] = new CasePlancher(-2);
		this.plancher[4] = new CasePlancher(-2);
		this.plancher[5] = new CasePlancher(-3);
		this.plancher[6] = new CasePlancher(-3);
	}

	// Affiche le plancher
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < plancher.length; i++) {
			if (i == plancher.length - 1) {
				if (plancher[i].estVide()) {
					s += "  ";
				} else {
					if (plancher[i].getTuile() instanceof TuileColore) {
						s += ((TuileColore) plancher[i].getTuile()).getCouleur() ;
					} else if (plancher[i].getTuile() instanceof TuilePremJoueur) {
						s += plancher[i].getTuile().toString();

					} else {// Tuile Joker
						s += plancher[i].getTuile().toString() ;
					}
				}
			} else {
				if (plancher[i].estVide()) {
					s += "  | ";
				} else {
					if (plancher[i].getTuile() instanceof TuileColore) {
						s += ((TuileColore) plancher[i].getTuile()).getCouleur() + " | ";
					} else if (plancher[i].getTuile() instanceof TuilePremJoueur) {
						s += plancher[i].getTuile().toString() + "  | ";

					} else {// Tuile Joker
						s += plancher[i].getTuile().toString() + "  | ";
					}
				}
			}
		}
		return "[ " + s + " ]";
	}

	// Calcul les points. si la case 0 du plancher n'est pas vide, on ajoute ses
	// points au malus
	// de meme pour les autres cases
	public int calculPoints() {
		int points = 0;
		for (int i = 0; i < plancher.length; i++) {
			if (!plancher[i].estVide()) {
				points += plancher[i].points;
			}
		}
		return points;
	}

	// on ajoute une tuile au plancher
	public Tuile add(Tuile t) {
		// on cherche la place de la premiere case vide sur le plancher ( correspond a
		// la colone 'taille'//toFix

		int taille = 0;
		for (int i = 0; i < plancher.length; i++) {
			if (plancher[i].getTuile() == null) {
				break;
			}
			taille++;
		}
		// si il y a une case vide, on la remplius avec la tuile et return null
		// si tout est plein on return la tuile
		if (taille < plancher.length) {
			plancher[taille].remplirCase(t);
			return null;
		} else {
			return t;
		}
	}

	// on ajoute une arraylist de tuiles. chaque tuile de l'arraylistr est ajoutee
	// avec la fonction precedente//tofix
	public ArrayList<Tuile> add(ArrayList<Tuile> t) {
		ArrayList<Tuile> retour = new ArrayList<Tuile>();
		Tuile e;
		for (int i = 0; i < t.size(); i++) { // For all Tuiles of the arraylist
			e = add(t.get(i));
			if (e != null)
				retour.add(e);

		}
		return retour;
	}

	public Tuile getTuilePremJoueur() {
		Tuile t;

		for (int i = 0; i < plancher.length; i++) {
			if (!plancher[i].estVide()) {
				if (plancher[i].getTuile() instanceof TuilePremJoueur) {

					t = plancher[i].getTuile();
					plancher[i].viderCase();
					return t;

				}
			}
		}
		return null;
	}

	// on vide le plancher, on fait appel a la fonction vidercase sur chaque case
	// composant le plancher
	public ArrayList<Tuile> viderPlancher() {
		ArrayList<Tuile> temp = new ArrayList<>();

		for (int i = 0; i < plancher.length; i++) {
			if (!plancher[i].estVide()) {

				temp.add(plancher[i].getTuile());
				plancher[i].viderCase();// vider la case
			}
		}
		return temp;
	}

	public ArrayList<Tuile> getPlancher(){
		 ArrayList<Tuile> ret= new ArrayList<Tuile>() ;
		 for(int i=0;i<7;i++) {
			 ret.add(plancher[i].getTuile());
		 }
		 return ret;
	}
}