import java.util.ArrayList;
import java.util.Random;

public class Partie {

    private ArrayList<Joueur> joueurs ;
    private ArrayList<Manche> manches = new ArrayList<>();
    private  boolean arreterPartie = false ;
    private Sac sac ;
    private Defausse defausse ;
    public ZonesCommunes zone ;
    private VueProjet v;

    public void lancerPartie(){


    		boolean mode = LireDonnes.modeJeu() ;
    		joueurs =  LireDonnes.lireJoueurs(mode)  ;

  
       // initialiser le sac selon le mode de jeu xxxx//ajout des tuiles joker
        if (mode ) {
            sac = new Sac();
        }else {
            sac = new Sac(joueurs.size()) ;
        }
       // la defausse a vide
        defausse = new Defausse() ;
        zone = new ZonesCommunes(joueurs.size()*2 +1 );
        
        //Partie Graphique   
    	if ( LireDonnes.modeGraphique() ) { 
    		v = new VueProjet(this,mode); 
    		}
   
      //lancer les manches
        while (!arreterPartie) {
        	
        	printG("Debut de la manche "+(manches.size()+1)  );
            Manche m = new Manche(this,v);
            m.lancerManche(mode);
            manches.add(m);
        }
        // fin de partie
        bonusFinPartie(); // calculer le bonus de fin de partie pour tout les joueurs
        AnnoncerGagnant();
        v.refresh(this);
    }

    public void arreterPartie(){
        this.arreterPartie = true ;
    }
    
    public boolean finPartie() {
    	return this.arreterPartie ;
    }

    public Manche getPreviousManche(){
        return  this.manches.get(this.manches.size()-1);
    }

    public  boolean alimenterSac(){
     if( !defausse.estVide() ) {

         this.sac.addTuiles(defausse.getTuiles());
         defausse.viderZone();
         return  true ;
     }else {
         return  false ;
     }
    }

    public boolean alimenterZoneCommune(){
    if( !sac.estVide() || !defausse.estVide() ) {   	
        if (!sac.estVide()) {
        	defausse.estVide();
            zone.alimenterCentre(sac);         
            zone.alimenterFabriques(sac);

        }

        if ( ! zone.bienAlimente()) { // si il reste encore des fariques non remplies
           if (alimenterSac())
            zone.alimenterFabriques(sac);
        }

        return true ;

    }else {
        return false;
    }

    }


    public Joueur getLastManchePremJoueur(){// retourne le premier joueur de la derniere manche

        if ( manches.size() > 0 ){
          return   manches.get(manches.size() -1).getPremierJoueur() ;

        }else{ // si la premiere manche on choisit un joueur au hasard

            Random r = new Random();
           return joueurs.get(r.nextInt(joueurs.size())) ;

        }
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
    public  Sac getSac() {   return  this.sac ; }

    public void alimenterDefausse( ArrayList<Tuile> t ){
        this.defausse.addTuiles(t) ;
    }
    public void AnnoncerGagnant(){
        Joueur j = joueurs.get(0) ;
        for (int i = 1 ; i < joueurs.size() ; i ++ ){
            if (joueurs.get(i).getScore() > j.getScore() )
                j = joueurs.get(i) ;

        }
        printG(" Le gagnant de cette partie est : " + j.getNom() + " avec " + j.getScore()+" points !\n  Felicitations  !! ");
        v.runnableViewProjet.run();
    }

    public void printG(String s) {
    	System.out.println(s);
    	if( v != null ) v.getTextArea().append(s+"\n\n");
    }    
    
    public void bonusFinPartie (){

        for (Joueur j : joueurs ){
            j.addScore(j.getP().bonusFinPartie());
        }
        
    }
}
