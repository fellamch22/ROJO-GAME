import java.util.ArrayList;


public class CentreTable extends Zone {
    @Override
    public boolean addTuile(Tuile t) {

        this.tuiles.add(t);
        return  true ;
     }

    @Override
    public boolean addTuiles(ArrayList<Tuile> t) {
        int i = 0 ;
        while ( i < t.size() ){
            tuiles.add(t.get(i)) ;
            i ++ ;
        }
        return true ;
    }


    public ArrayList<Tuile> getTuilesColore(char color) {
        Tuile e ;
        ArrayList<Tuile> t = new ArrayList<>();
       int i = 0 ;
        while ( i < tuiles.size() ){
            e = tuiles.get(i) ;
            if(e instanceof TuileColore ){
                if (((TuileColore) e).getCouleur() == color ) {
                    t.add(e);
                    tuiles.remove(e);
                    if ( i != 0)  i = i - 1 ;

                }else {
                     i ++ ;
                }
            }else {
                i ++ ;
            }
        }

        return t ;
    }

    public Tuile getTuilePremJoueur(){
        int i = 0 ;
        Tuile e ;

        while ( i < tuiles.size() ) {
            e = tuiles.get(i) ;
            if ( e instanceof TuilePremJoueur){
                tuiles.remove(e) ;
                System.out.println("Une tuilePJoueur est presente dans le centre");
                return e ;
            }
                i++ ;
        }

        return null ;
    }
    public ArrayList<Tuile> getTuilesJoker() {
        Tuile e ;
        ArrayList<Tuile> t = new ArrayList<>();
        int i = 0 ;
        while ( i < tuiles.size() ){
            e = tuiles.get(i) ;
            if(e instanceof TuileJoker ){
                t.add(e);
                tuiles.remove(e);
                if ( i != 0)  i = i - 1 ;
            }else {
                i++;
            }
        }

        return t ;
    }
}
