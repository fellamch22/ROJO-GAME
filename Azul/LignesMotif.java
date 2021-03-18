import java.util.ArrayList;
import java.util.Iterator;


public class LignesMotif {

    //Variable
    private CaseLigneMotif[][] ligneMotif;


    //Constructeur , cree un tableau triangulaire de caseslignemotif
    public LignesMotif () {
        ligneMotif= new CaseLigneMotif[][] {
                //constructeur par defaut de caselignemotif
                { new CaseLigneMotif() },
                { new CaseLigneMotif(), new CaseLigneMotif()},
                { new CaseLigneMotif(), new CaseLigneMotif(), new CaseLigneMotif() },
                { new CaseLigneMotif(), new CaseLigneMotif(), new CaseLigneMotif(), new CaseLigneMotif()},
                { new CaseLigneMotif(), new CaseLigneMotif(), new CaseLigneMotif(), new CaseLigneMotif(), new CaseLigneMotif() }
        };
    }

    //affiche le contenu du tableau triangulaire
    public String afficheLigneMotif() {

        String s = "";
        for (int i = 0; i < ligneMotif.length; i++) {
            s += i + 1 + " | ";
            for (int j = 0; j < ligneMotif[i].length; j++) {
               if( ligneMotif[i][j].estVide() ) {
                   s +=  "  | ";
               }else {
                  if ( ligneMotif[i][j].getTuile() instanceof  TuileColore ){
                      s += Character.toString( ( ((TuileColore) ligneMotif[i][j].getTuile()).getCouleur()) )+" | " ;

;                  }else {//Tuile Joker
                      s += "k | " ;
                  }
               }
            }
            s += "\n";
        }
        return s;
    }

    //test si la ligne mise en argument est pleine sur le tableau triangulaire//tofix
    public boolean lignePleine(int ligne) {
        if (ligneMotif[ligne][ligneMotif[ligne].length-1].getTuile() == null) { return false;}
        else { return true; }
    }

    // test si toutes les lignes sont completes
    public boolean toutesLignePleines() {
        return lignePleine(0) && lignePleine(1) && lignePleine(2) && lignePleine(3) && lignePleine(4) ;
    }

    // teste si  toutes les lignes pleines sauf clledonne en parametre
    public boolean toutesLignePleines(int ligne) {
        boolean result = true ;
        for ( int i = 0 ; i < 5 ; i ++ ){
            if (i != ligne) result = result && lignePleine(i) ;
        }

        return result ;
    }
    // test si la couleur en argument exist sur la ligne en question dans le tableau
    public boolean Existe(int ligne, char color) {

        for (int col=0 ; col < ligneMotif[ligne].length-1 ; col++ )
        {
            if(ligneMotif[ligne][col].getTuile() instanceof  TuileColore ) {
                if (((TuileColore) ligneMotif[ligne][col].getTuile()).getCouleur() == color) return true;
            }
        }
        return false;
    }

    // ligne colore
    public char ligneColor (int ligne ){
        for ( int i = 0 ; i < ligneMotif[ligne].length ; i++ ){
            if (!ligneMotif[ligne][i].estVide()){
                if (ligneMotif[ligne][i].getTuile() instanceof  TuileColore ){
                    return ((TuileColore) ligneMotif[ligne][i].getTuile()).getCouleur() ;
                }
            }
        }
        return ' ';
    }

    //test si la ligne du tableau triangulaire contient un joker
    public boolean containJoker(int ligne) {
        for (int col=0; col < ligneMotif[ligne].length ; col++ )
        {
            if(ligneMotif[ligne][col].getTuile() instanceof TuileJoker ) return true;
        }
        return false;
    }

    // try to add each tuile of the arraylist into the triangular tab with the DeposerTuile(Tuile) func.

    public ArrayList<Tuile> DeposerTuiles (ArrayList<Tuile> t,int ligne ) {
        ArrayList<Tuile> reste = new ArrayList<>() ;
        Iterator<Tuile>it = t.iterator();
        int i = 0 ;

        while ( i < ligneMotif[ligne].length && it.hasNext() ){
            if( ligneMotif[ligne][i].estVide()) ligneMotif[ligne][i].remplirCase(it.next());
            i++ ;
        }
        while (it.hasNext()) reste.add(it.next()) ;

        return reste ;
    }

    // add tuile to a specific line. return true if ok , false if KO

    public void DeposerTuile(Tuile t,int ligne ) {
        int i = 0 ;
        while ( i < ligneMotif[ligne].length && !ligneMotif[ligne][i].estVide() ) i ++ ;
        ligneMotif[ligne][i].remplirCase(t);
    }

    // retourne la tuile colore de la meme couleur contenu dans la ligne
    public Tuile getTuileColore( int ligne ){
        Tuile t ;
        for ( int j = 0 ; j < ligneMotif[ligne].length ; j ++ ){

            if ( ligneMotif[ligne][j].getTuile() instanceof  TuileColore ){
                t = ligneMotif[ligne][j].getTuile() ;
                ligneMotif[ligne][j].viderCase();
                return t ;
            }

        }
        return null ;
    }

    // retourne une tuile joker parmi les tuiles de la ligne
    public Tuile getTuileJoker ( int ligne ){
        Tuile t ;
        for ( int j = 0 ; j < ligneMotif[ligne].length ; j ++ ){

            if ( ligneMotif[ligne][j].getTuile() instanceof  TuileJoker ){
                t = ligneMotif[ligne][j].getTuile() ;
                ligneMotif[ligne][j].viderCase();
                return t ;
            }

        }
        return null ;

    }

    // vider la ligne en retournant toutes les tuiles qui etaient dedans
    public ArrayList<Tuile> viderLigne( int ligne ){
        ArrayList<Tuile> t = new ArrayList<>() ;

        for (int j = 0 ; j < ligneMotif[ligne].length ; j ++ ){

            if ( !ligneMotif[ligne][j].estVide()){
                t.add(ligneMotif[ligne][j].getTuile() );
                ligneMotif[ligne][j].viderCase();
            }
        }

        return t ;
    }
    
    public ArrayList<ArrayList<Tuile>> getLigneMotifTab(){
    	ArrayList<ArrayList<Tuile>> ret = new ArrayList<ArrayList<Tuile>>();
    	ArrayList<Tuile> lm = new ArrayList<Tuile>();   	
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j <= i; j++) {
				lm.add(ligneMotif[i][j].getTuile());
			}
			ret.add(lm);
			lm= new ArrayList<Tuile>();
		}

		return ret;
    }
    
}