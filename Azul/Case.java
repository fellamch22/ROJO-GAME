public class Case {

    private Tuile t ;

/** Ajouter une tuile t a la case */
    public void remplirCase(Tuile t){

        this.t = t ;
    }

/** verifier si la case contient une tuile ou non */
    public boolean estVide(){

        return t == null ;
    }

/** vider la case */
    public void viderCase(){

        this.t = null ;
    }

/** recuperer la tuile contenue dans la case */
public  Tuile getTuile(){
    return t ;
}

}
