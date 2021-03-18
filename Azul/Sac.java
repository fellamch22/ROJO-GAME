import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Sac extends Zone {

    public Sac(){ // constructeur pour le mode colore
        for (int i = 0 ; i < 20 ; i ++ ){
            tuiles.add(new TuileColore('r')) ;
            tuiles.add(new TuileColore('b')) ;
            tuiles.add(new TuileColore('l')) ;
            tuiles.add(new TuileColore('n')) ;
            tuiles.add(new TuileColore('j')) ;

        }
        tuiles.add(new TuilePremJoueur());
        //mis a jour : random color in fabrique
        Collections.shuffle(tuiles);
    }

    public Sac (int nbJoueurs ){// constructeur pour le mode incolore

        if ( nbJoueurs == 2 ){

            for ( int i = 0 ; i < 19 ; i ++ ){
                if(  i % 4 == 0 || i == 18 ) tuiles.add( new TuileJoker() );
                tuiles.add(new TuileColore('r')) ;
                tuiles.add(new TuileColore('b')) ;
                tuiles.add(new TuileColore('l')) ;
                tuiles.add(new TuileColore('n')) ;
                tuiles.add(new TuileColore('j')) ;
            }

        }else { // nombre des joueurs egale a  3 ou 4

            for ( int i = 0 ; i < 18 ; i ++ ){
                if(  i % 2 == 0 || i == 17 ) tuiles.add( new TuileJoker() );
                tuiles.add(new TuileColore('r')) ;
                tuiles.add(new TuileColore('b')) ;
                tuiles.add(new TuileColore('l')) ;
                tuiles.add(new TuileColore('n')) ;
                tuiles.add(new TuileColore('j')) ;
            }
        }

        tuiles.add(new TuilePremJoueur());
       
        //mis a jour : random color in fabrique
        Collections.shuffle(tuiles);
    }

    @Override
    public boolean addTuile(Tuile t) {

        if (tuiles.size() <= 101){
            tuiles.add(t);
            return true;

        }else{

        return false;
        }
    }

    /**   placer un ensemble de tuiles dans le sac */

    @Override
    public boolean addTuiles(ArrayList<Tuile> t) {

        if( tuiles.size() + t.size() <= 101 ){
            Iterator<Tuile> it = t.iterator();
            while ( it.hasNext()){
                tuiles.add(it.next());
            }
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Tuile> piocher( int nbTuiles){
        ArrayList<Tuile> l = new ArrayList<>();
        int i = 0 , k = 0 ;
        while ( i < tuiles.size() && k < nbTuiles ){
            if( !(tuiles.get(i) instanceof TuilePremJoueur) ) {

                l.add(tuiles.remove(i)); // recuperer les tuiles du sac
                k ++ ;
                if ( i != 0)  i = i - 1 ;
            }else {
                i++;
            }
        }

        return l ;
    }

    public Tuile getTuilePremJoueur(){
        int i = 0 ;
        while ( i < tuiles.size() ){

            if ( tuiles.get(i) instanceof TuilePremJoueur) {
                return tuiles.remove(i);

            }
            i++ ;
        }
         return  null ;
    }
}
