import java.util.ArrayList;

public abstract class Mur {

    protected CaseMur[][] cases  = new CaseMur[5][5] ;


    public ArrayList<ArrayList<Tuile>> getMurArraylist(){
    	ArrayList<ArrayList<Tuile>> ret = new ArrayList<ArrayList<Tuile>>();
    	ArrayList<Tuile> murTemp = new ArrayList<Tuile>();   	
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				murTemp.add(cases[i][j].getTuile());
			}
			ret.add(murTemp);
			murTemp= new ArrayList<Tuile>();
		}

		return ret;
    }
       
    public String afficherMur(){
    	String s="";
        for ( int i = 0 ; i < 5 ; i++){
            s+="\n| ";
            for(int j = 0 ; j < 5 ; j++){           	
                if ( cases[i][j].estVide() ) {
                	s+=cases[i][j].getColor() + "  | ";

                }else {
                    if ( cases[i][j].getTuile() instanceof TuileColore ){
                    	s+=cases[i][j].getColor() +Character.toString(((TuileColore) cases[i][j].getTuile()).getCouleur())+" | ";

                    }else { // tuile joker
                    	s+=cases[i][j].getColor() +"k"+" | ";
                    }
                }
            }
        }
        return s;
    }

    // savoir si la case de la ligne i et la colonne j contient une tuile ou non
    public  boolean caseVide ( int i , int j ){

          return  cases[i][j].estVide() ;

    }
    public int nbTuilesHorAdja ( int i ,int j  ){ // retourne le nombre de tuiles horizentalement adjascentes a la tuile de la case i,j y compris
        int cpt = 0 ;                                                                    // cette case elle meme
        for ( int p = 0 ; p < 5 ; p ++){
            if( !cases[i][p].estVide() && p != j ) cpt++ ;
        }
        if ( cpt > 0 ) cpt ++ ; // pour iclure la case dans le calcul si elle a des cases hor adja
        return cpt ;
    }
    public int nbTuilesVerAdja (int i , int j  ){ // retourne le nombre de tuiles verticalement adjascentes a la tuile de la case i,j y compris
        int cpt = 0 ;                                                                    // cette case elle meme
        for ( int p = 0 ; p < 5 ; p ++){
            if( !cases[p][j].estVide() && p != i ) cpt++ ;
        }
        if ( cpt > 0 ) cpt ++ ; // pour iclure la case dans le calcul si elle a des cases Ver adja

        return cpt ;
    }

    public boolean dejaValidee( char color , int ligne ){

        for ( int j = 0 ; j < 5 ; j++ ){
            if (! cases[ligne][j].estVide() ){
                if( cases[ligne][j].getTuile() instanceof  TuileColore){
                     if ( ((TuileColore) cases[ligne][j].getTuile()).getCouleur() == color )
                         return true ;
                }
            }
        }
        return false ;
    }

    // retourne true si une ligne du mur du joueur a ete complete
    public boolean ligneComplete ( int i ){
        boolean result = true ;
        for ( int j = 0 ; j < 5 ; j ++ ){
            result = result && !caseVide(i,j) ;
        }
        return result ;
    }

    // retourne true si une colonne du mur du joueur a ete complete
    public boolean colonneComplete ( int j ){
        boolean result = true ;
        for ( int i = 0 ; i < 5 ; i ++ ){
            result = result && !caseVide(i,j) ;
        }
        return result ;
    }

    // retourne true si le joueur a reussi a completer 5 fois la couleur dans son mur
    public boolean couleurComplete ( char color ){
        int cpt = 0 ;
        for ( int i = 0 ; i < 5 ; i ++ ){
            for ( int j = 0 ; j < 5 ; j ++ ){

                if ( ! caseVide(i,j) ){
                     if  ( cases[i][j].getTuile() instanceof TuileColore ){
                         if ( ((TuileColore) cases[i][j].getTuile()).getCouleur() == color ) cpt ++;
                     }
                }
            }
        }

        return cpt == 5 ;
    }

    // calcule le bonus obtenu selon plusieurs criteres
    public int calculBonus ( ){
        char[] colors = {'b','r','l','j','n'} ;
        int points = 0 ;
        for ( char color : colors ){
            if ( couleurComplete(color) ) points += 10 ;
        }

        for (int i = 0 ; i < 5 ; i ++ ){

            if (ligneComplete(i)) points += 2 ;
            if (colonneComplete(i)) points += 7 ;

        }

        return  points ;
    }
}
