import java.util.ArrayList;

public class ZonesCommunes {

   private ArrayList<Fabrique> fabriques;
   private CentreTable centre;

    public ZonesCommunes(int nbFabriques ){/****** MisssssinnnG   **********/
          fabriques = new ArrayList<>();
          for ( int i =0 ; i < nbFabriques ; i++ ){
              fabriques.add(new Fabrique());
              
          }
          centre = new CentreTable() ;
    }

    /** Retourne toutes les tuiles colore d'une couleur donnée de la fabrique designée par numFabrique  */
    public ArrayList<Tuile> getTuilesColoreFabrique(int numFabrique, char color) {

        return (fabriques.get(numFabrique ).getTuilesColore(color));
    }

    /** Retourne toutes les tuiles colore d'une couleur donnée du centre de table  */
    public ArrayList<Tuile> getTuilesColoreCentre(char color) {

        return (centre.getTuilesColore(color));
    }

    /** Retourne toutes les tuiles Joker de la fabrique designée par numFabrique  */
    public ArrayList<Tuile> getTuilesJokerFabrique(int numFabrique) {

        return (fabriques.get(numFabrique ).getTuilesJoker());

    }

    /** Retourne toutes les tuiles Joker du centre de table  */
    public ArrayList<Tuile> getTuilesJokerCentre() {

        return centre.getTuilesJoker() ;

    }

    public void alimenterFabriques (Sac sac){
        int i = 0 ;
        while (i < fabriques.size()){
           if (fabriques.get(i).nbTuiles() < 4 )
               fabriques.get(i).addTuiles(sac.piocher(4-fabriques.get(i).nbTuiles()));
           	   
           i++ ;
        }

    }

    public void alimenterCentre(Sac sac){
        Tuile t ;
        t = sac.getTuilePremJoueur() ;
        if ( t != null )
        this.centre.addTuile(t);
    }


    public boolean bienAlimente(){
        boolean result = true ;
        int i = 0 ;
        while (i < fabriques.size() ){

            result = result &&  (fabriques.get(i).nbTuiles() == 4 ) ;
            i ++ ;
        }

        return result ;
    }

    public  boolean estVide(){ // retourne true si toutes les fabriques  et le centre de table sont vides , false sinon
        boolean result = true ;
        int i = 0 ;
        while ( i < fabriques.size() ){

            result = result && fabriques.get(i).estVide() ;
            i ++ ;
        }

        return result && centre.estVide() ;
    }

    public boolean toutesFabriquesVides(){
        int i = 0 ;
        boolean result = true ;
        while ( i < fabriques.size() ){
            result = result & fabriques.get(i).estVide() ;
            i ++ ;
        }
        return result ;
    }
    public Fabrique getFabrique(int i){
        return fabriques.get(i);
    }

	public ArrayList<Fabrique> getFabrique() {
		return fabriques;
	}

    public CentreTable getCentre() {
        return centre;
    }
}
