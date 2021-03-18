import java.util.ArrayList;

public abstract class Zone {

        protected  ArrayList<Tuile> tuiles = new ArrayList<>();

        public abstract boolean addTuile(Tuile t) ;
        public  abstract  boolean addTuiles(ArrayList<Tuile> t ) ;
        public  ArrayList<Tuile> getTuiles(){
                return this.tuiles ;
        }
        public void viderZone(){
                this.tuiles.clear();
        }

        public boolean estVide(){
            System.out.println(getClass().getName()+" Nombre de Tuiles : "+tuiles.size() + " : " + tuiles );
                return tuiles.size() == 0 ;
        }
        public int nbTuiles(){
                return tuiles.size() ;
        }
        public int nbTuiles(char color ){
                int cpt = 0 , i = 0 ;
           while (i < tuiles.size()){
                   if ( tuiles.get(i) instanceof TuileColore ) {
                          if (((TuileColore) tuiles.get(i)).getCouleur() == color)
                           cpt++;
                   }

                   i ++ ;
           }

           return cpt ;
        }

        public int nbTuilesColore(){
            int i = 0 , cpt = 0 ;
            while ( i < tuiles.size() ){
                if ( tuiles.get(i++) instanceof TuileColore) { cpt ++ ; }

            }
            return cpt ;
        }
    public int nbTuilesJoker(){
        int i = 0 , cpt = 0 ;
        while ( i < tuiles.size() ){
            if ( tuiles.get(i++) instanceof TuileJoker) { cpt ++ ; }

        }
        return cpt ;
    }
    

}
