import java.util.ArrayList;

public class Fabrique extends Zone {

      /*    placer une tuile dans la fabrique */

    @Override
    public boolean addTuile(Tuile t) {

        if (  tuiles.size() + 1 <= 4 ){
                tuiles.add(t);
                return true ;
    }else{
            return false ;
        }
    }

    /*    placer un ensemble de tuiles dans la fabrique */

    @Override
    public boolean addTuiles(ArrayList<Tuile> t) {
        int i = 0 ;
        if( tuiles.size() + t.size() <= 4){

            while ( i < t.size() ){
                 tuiles.add( t.get(i));
                 i ++ ;
            }
            return true;
        }else{
        return false;
        }
    }

    public ArrayList<Tuile> getTuilesColore(char color) {
        Tuile e ;
        ArrayList<Tuile> t = new ArrayList<>();
        int i = 0 ;
        while (i < tuiles.size() ){
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
                i++;
            }
        }
        return t ;
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
