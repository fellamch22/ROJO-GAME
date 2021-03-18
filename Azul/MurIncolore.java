public class MurIncolore extends Mur {


    public MurIncolore(){

        for (int i = 0 ; i < 5 ; i++ ) {
            for (int j = 0; j < 5; j++) {

                cases[i][j]= new CaseMur('x'); // x veut dire qu'il n'ya pas de couleur precise " Mur Incolore "
            }

        }
    }


    public int deposerTuile(int i, int j, Tuile t) { // ne l'utiliser qu'apres verifier si tuile existe deja pour le tuiles colore

        int points = 0 ;

        if( cases[i][j].estVide()  ){

                    cases[i][j].remplirCase(t); // on remplit directement car on a vrifie avant avec la methode existe deja
                    points += nbTuilesHorAdja(i,j) + nbTuilesVerAdja(i,j) ;
                    if ( points == 0 )  // si aucune tuile adjascente elle remporte un point
                        points = 1 ;
        }

        return points ;
    }

    public boolean existeDeja( int i , int j , TuileColore t ){ // retourne vrai si une tuile de la meme couleur existe sur la meme ligne/colone

        for ( int p = 0 ; p < 5 ; p ++ ){
            if (! cases[i][p].estVide() ) {// verification horizontale
                if( cases[i][p].getTuile() instanceof TuileColore ){
                    if ( ((TuileColore) cases[i][p].getTuile()).getCouleur() == t.getCouleur() ) return true ;
                }
            }
            if (! cases[p][j].estVide() ) {// verification verticale
                if( cases[p][j].getTuile() instanceof TuileColore ){
                    if ( ((TuileColore) cases[p][j].getTuile()).getCouleur() == t.getCouleur() ) return true ;
                }
            }
        }

        return  false ;

    }
}
