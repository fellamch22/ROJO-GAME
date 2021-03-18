public class MurColore extends Mur {


    public MurColore(){

        for (int i = 0 ; i < 5 ; i++ ){
            for( int j = 0 ; j < 5 ; j++ ){
                if( i == j ){
                    cases[i][j] = new CaseMur('b'); // cases bleu

                }else if( (i == 0 && j == 4 ) || (j == i-1) ){
                    cases[i][j] = new CaseMur('l'); // cases blanches

                }else if ( (i == 4 && j == 0 ) || (j == i+1) )  {
                    cases[i][j] = new CaseMur('j'); // cases jaunes

                }else if((j == i+2) || (j == i-3)){
                    cases[i][j] = new CaseMur('r'); // cases rouges

                }else if((j == i+3) || (j == i-2)){
                    cases[i][j] = new CaseMur('n'); // cases noir

                }
            }
        }
    }


    public int deposerTuile(int i ,   Tuile t ){
        int points = 0 ;

        for ( int j = 0 ; j < 5 ; j ++ ) {
            if (cases[i][j].estVide()) {

                if (t instanceof TuileColore) { // dans un mur colore on ne peut avoir que des tuiles colore
                    if (((TuileColore) t).getCouleur() == cases[i][j].getColor()) {
                        cases[i][j].remplirCase(t);
                        points += nbTuilesHorAdja(i, j) + nbTuilesVerAdja(i, j);
                        if (points == 0)  // si aucune tuile adjascente elle remporte un point
                            points = 1;

                    }
                }

            }

        }
        return points ;
    }
}

