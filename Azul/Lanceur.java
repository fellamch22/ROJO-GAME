

public class Lanceur {

	public static void main(String[] args) {
		
		Partie p;
		
		// charger le repertoire image
		
		if(args.length != 0) {
			
			LireDonnes.setRepertoire(args[0]);
			System.out.println("Repertoire image : " + LireDonnes.getRepertoire());
			
			}
	
		// lancer la nouvelle partie 
		
		while (LireDonnes.nouvellePartie()) {
			
			p = new Partie();
			p.lancerPartie();
		}
	}

}
