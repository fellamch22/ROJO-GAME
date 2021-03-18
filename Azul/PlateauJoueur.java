import java.util.ArrayList;

public class PlateauJoueur {

    protected LignesMotif lm;
    protected Mur m ;
    protected Plancher p;

    public ArrayList<ArrayList<Tuile>> getLigneMotifTab(){
    	ArrayList<ArrayList<Tuile>> ret;
    	ret=lm.getLigneMotifTab();
    	ret.add(p.getPlancher());
    	for(int i=0;i<5;i++) {
    		ret.add(m.getMurArraylist().get(i));
    	}
    	return ret;
    }
    
    public PlateauJoueur( boolean mode) {
        lm = new LignesMotif ();
         p = new Plancher();
        if (mode) {
            m = new MurColore() ;
        }else {
            m = new MurIncolore() ;
        }


    }

    public int bonusFinPartie (){
        return m.calculBonus() ;
    }

    public LignesMotif getLignesMotif() {
    	return this.lm;
    }
    
    @Override
    public String toString() {
        
        return "\n" + m.afficherMur() + "\n\n" +lm.afficheLigneMotif() + "\n" +  p  + "\n";
    }

}