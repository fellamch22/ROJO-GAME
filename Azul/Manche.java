import java.util.ArrayList;

public class Manche {

    private static Partie partie ;
    private Joueur premierJoueur ; // marquer le premier joueur de cette manche
    private VueProjet v;
    private boolean multiClick=true;
    
 // a intialiser des recuperation du centre de la table
    public Manche ( Partie partie , VueProjet vu){
        Manche.partie = partie ;
        this.v = vu;
    }

    public Joueur getPremierJoueur() {
        return premierJoueur;
    }

    public  void lancerManche ( boolean mode ){
    		this.phasePreparation();
    		if (! partie.finPartie()) {
    		this.phaseOffre(mode);
    		this.phaseDecoration(mode);
    		}
    }

    public void printG(String s) {
    	System.out.println(s);
    	if( v != null ) v.getTextArea().append(s+"\n\n");
    }
    
    public void phasePreparation(){

        if ( !partie.alimenterZoneCommune()) // si le sac et la defausse sont vides on arrete la partie
             partie.arreterPartie();
    }

    public void phaseOffre( boolean mode ){

        ArrayList <Joueur> joueurs = new ArrayList<>() ;
        ArrayList<Tuile> tuiles = new ArrayList<>() ;
        ArrayList<Tuile> tuilesJ = new ArrayList<>() ;
  
        Tuile t = null ;
        int choix , typeTuiles ;
        Joueur firstPlayer = partie.getLastManchePremJoueur() ;
        Joueur currentPlayer ;
        int i = -1 ;


        joueurs.addAll(partie.getJoueurs())  ;
        joueurs.remove(firstPlayer) ;

        while ( !partie.zone.estVide() ){
        	if(v != null ) v.refresh(partie);
            if ( i >=  joueurs.size())  i = -1 ;

            if(  i == -1 ){ // si au debut de la manche on prend le premier joueur de la manche precedente
                currentPlayer = firstPlayer  ;
                i ++ ;
            }else { // sinon on prend le joueur suivant de la liste
                currentPlayer = joueurs.get(i) ;
                i ++ ;
            }
            
            tuiles.clear();
            tuilesJ.clear();
            
            printG("-------------------------------------- \n C'est le tour de  "+currentPlayer.getNom()+ " ! Score = " +currentPlayer.getScore());
            System.out.println( currentPlayer.getP() );
            if(v != null ) v.updateCurrentPlayer(currentPlayer.getNom());
           // System.out.println("\n-------------------------------------- \n C'est le tour de  "+currentPlayer.getNom()+ " Score = " +currentPlayer.getScore());
           //  System.out.println( currentPlayer.getP() ) ;

            partie.zone.toutesFabriquesVides() ; // Display Fabriques
            partie.zone.getCentre().estVide();  // Display Centre
            /**
             *              Partie choix de zone de recuperation ( Fabrique ou centre Table )
             */

            if ( LireDonnes.zoneSelectionne(partie.zone,v) ){// si la zone selectionnee est une fabrique
                choix =  LireDonnes.numFabrique(partie.getJoueurs().size() *2 +1 , partie.zone,v ) ;

                while (  partie.zone.getFabrique( choix -1).estVide()){// si la fabrique choisie est vide il doit prendre une autre
                    choix =  LireDonnes.numFabrique(partie.getJoueurs().size() *2 +1 , partie.zone,v ) ;
                }

                if ( mode == true) { // mode colore

                    tuiles = partie.zone.getTuilesColoreFabrique(choix - 1, LireDonnes.choisirCouleur(partie.zone.getFabrique(choix - 1),v));


                    // on place les tuiles restantes de la fabrique vers le centre de table
                    partie.zone.getCentre().addTuiles(partie.zone.getFabrique( choix - 1).getTuiles());
                    partie.zone.getFabrique(choix -1 ).viderZone();


                }else { // mode incolore
                    typeTuiles = LireDonnes.choixTypeTuiles(partie.zone.getFabrique( choix - 1),v) ;

                    if ( typeTuiles == 1 ){ // l'utilisateur prend que des tuiles colore
                        tuiles = partie.zone.getTuilesColoreFabrique(choix - 1, LireDonnes.choisirCouleur(partie.zone.getFabrique(choix - 1),v));

                    }else if ( typeTuiles == 2 ){// l'utilisateur choisi des tuiles joker seulement
                        tuilesJ = partie.zone.getTuilesJokerFabrique( choix - 1 ) ;

                    }else {// des tuiles colores et des tuiles joker
                        tuilesJ = partie.zone.getTuilesJokerFabrique( choix - 1 ) ;
                        tuiles = partie.zone.getTuilesColoreFabrique(choix - 1, LireDonnes.choisirCouleur(partie.zone.getFabrique(choix - 1),v));

                    }

                    // on place les tuiles restantes de la fabrique vers le centre de table
                    partie.zone.getCentre().addTuiles(partie.zone.getFabrique( choix - 1).getTuiles());
                    partie.zone.getFabrique(choix -1 ).viderZone();
                }


            }else { // si il choisit le centre de table on demande la couleur 

                t = partie.zone.getCentre().getTuilePremJoueur() ;// remove the TuilePremJoueur and return it

                if ( t != null ) this.premierJoueur = currentPlayer ;// si la tuile premier joueur est dans le centre
                // le joueur va etre celui qui commence la premiere manche
             if ( partie.zone.getCentre().nbTuiles() != 0 ) { // si il y'avait pas que la tuile premier joueur seulemnt dans le centre
            	 
                 if (mode == true) { // mode colore
                	
                     tuiles = partie.zone.getTuilesColoreCentre(LireDonnes.choisirCouleur(partie.zone.getCentre(),v));

                 } else {
                     typeTuiles = LireDonnes.choixTypeTuiles(partie.zone.getCentre(),v);
                     if (typeTuiles == 1) { // l'utilisateur prend que des tuiles colore
                         tuiles = partie.zone.getTuilesColoreCentre(LireDonnes.choisirCouleur(partie.zone.getCentre(),v));

                     } else if (typeTuiles == 2) {// l'utilisateur choisi des tuiles joker seulement
                         tuilesJ = partie.zone.getTuilesJokerCentre();

                     } else {// des tuiles colores et des tuiles joker
                         tuilesJ = partie.zone.getTuilesJokerCentre();
                         tuiles = partie.zone.getTuilesColoreCentre(LireDonnes.choisirCouleur(partie.zone.getCentre(),v));

                     }
                 }

              }
             

            }


            // partie depos

            boolean bonDepos = false ;
            boolean choixDepos ;
            multiClick=true;
            while ( !bonDepos ) {
                choixDepos = LireDonnes.choixDepos(currentPlayer,v,multiClick) ;
                bonDepos = choixDepos( choixDepos , tuiles, tuilesJ, t, currentPlayer);
                t=null;
            }

            System.out.println( currentPlayer.getP() ) ;// Display plateau apres avoir joue
        }


    }


    public boolean choixDepos(boolean choixDepos , ArrayList<Tuile> tuiles , ArrayList<Tuile> tuilesJ , Tuile t  , Joueur currentPlayer ){
   
    	int ligne ;
        ArrayList<Tuile> resteP  = new ArrayList<>(), resteL = new ArrayList<>()  ;
        boolean bonChoixLigne = false , checkLignes = true ;


        /**
         Partie depot ( deposer les tuiles sur une ligne motif ou un plancher )
         */
        if ( t != null ) {
            currentPlayer.getP().p.add(t); // deposer la tuile premier joueur dans le plancher
            t = null;
        }

        if ( choixDepos ){ // si le joueur choisi de mettre les tuiles sur une lign motif

           while ( !bonChoixLigne) {   // invoquer la methode du joueur courant deposer ligne motif ( numero de la ligne , tuiles recuperees ) ;

               ligne = LireDonnes.numLigne(v); // lire le numero de la ligne motif// si t != null in voquer la methode deposer plancher du joueur

            if ( currentPlayer.getP().lm.toutesLignePleines()){// si toutes les ligne motif sont pleines --> on depose dans le plancher
               if( tuiles.size() > 0 ){
                   resteP = currentPlayer.getP().p.add(tuiles) ;
                   if ( resteP.size() > 0 ){ //si il reste des tuiles --> on les met dans la defausse
                       partie.alimenterDefausse(resteP);
                       resteP.clear();
                   }
               }
               if (tuilesJ.size() > 0 ){
                   resteP = currentPlayer.getP().p.add(tuilesJ);
                   if ( resteP.size() > 0 ) {
                       partie.alimenterDefausse(resteP);
                       resteP.clear();
                   }
               }
               bonChoixLigne = true ;
               return true ;

            }else if ( currentPlayer.getP().lm.lignePleine( ligne -1 )){
            	printG("La ligne choisie est pleine , Veuillez choisir la bonne \n");
                        bonChoixLigne = false ;

            }else if ( tuiles.size() > 0 ) {// si le joueur a pris des tuiles colores

                     if( tuiles.get(0) instanceof TuileColore && currentPlayer.getP().lm.ligneColor(ligne -1 ) != ' ') {//toujours verifie
                          if ( ((TuileColore) tuiles.get(0)).getCouleur() != currentPlayer.getP().lm.ligneColor(ligne -1 ) ) {
                              bonChoixLigne = false ;
                              for (int i = 0 ; i <5 ; i ++ )  checkLignes = checkLignes && (currentPlayer.getP().lm.lignePleine( i )
                                                                       || currentPlayer.getP().m.dejaValidee( ((TuileColore) tuiles.get(0)).getCouleur() , i)
                                                                       || ( currentPlayer.getP().lm.ligneColor(i) != ' '
                                                                                    &&   ((TuileColore) tuiles.get(0)).getCouleur() != currentPlayer.getP().lm.ligneColor(ligne -1 )) ) ;
                              if ( checkLignes ){// acune ligne acceptante , il doit mettre ses tuiles au plancher
                            	  printG(" Veuillez deposer vos tuiles sur le plancher aucune ligne acceptante ");
                                  		multiClick=false;
                                        return false;
                              }else{ // il y'a au moins une ligne acceptante il dois choisir la bonne ligne
                            	  printG(" Veuillez choisir la bonne ligne \n");
                                  bonChoixLigne = false ;
                              }


                          }else {// la ligne contient la meme couleur

                              /**** added ****************/

                              if (tuilesJ.size() > 0) {// le joueur a pris des tuiles joker

                                  resteL = currentPlayer.getP().lm.DeposerTuiles(tuilesJ, ligne - 1);
                                  if (resteL.size() > 0) {
                                      resteP = currentPlayer.getP().p.add(resteL);
                                      if (resteP.size() > 0) {
                                          partie.alimenterDefausse(resteP);
                                          partie.alimenterDefausse(tuiles);
                                      } else {
                                          resteP = currentPlayer.getP().p.add(tuiles);
                                          if (resteP.size() > 0) partie.alimenterDefausse(resteP);
                                      }
                                  } else {// resteL.size == 0
                                      resteL = currentPlayer.getP().lm.DeposerTuiles(tuiles, ligne - 1);
                                      if (resteL.size() > 0) {
                                          resteP = currentPlayer.getP().p.add(resteL);
                                          if (resteP.size() > 0) {
                                              partie.alimenterDefausse(resteP);
                                          }
                                      }
                                  }

                                  bonChoixLigne = true;
                                  return true;

                              } else { // le joueur a pris que des tuiles colores

                                  if (tuiles.get(0) instanceof TuileColore) {//toujours valide
                                      if (currentPlayer.getP().m.dejaValidee(((TuileColore) tuiles.get(0)).getCouleur(), ligne - 1)) {
                                    	  printG("Cette couleur est deja Validee sur le Mur Veuillez chosir une autre ligne ");
                                    	  bonChoixLigne = false;

                                      } else {
                                          resteL = currentPlayer.getP().lm.DeposerTuiles(tuiles, ligne - 1);

                                          if (resteL.size() > 0) {
                                              resteP = currentPlayer.getP().p.add(resteL);
                                              if (resteP.size() > 0) {
                                                  partie.alimenterDefausse(resteP);
                                              }
                                          }
                                          resteP.clear();
                                          resteL.clear();
                                          bonChoixLigne = true;
                                          return true;
                                      }
                                  }


                              }
                          }


                       /** fin added *****/
                     }else {// la ligne ne contient pas une couleur precise

                         if ( tuilesJ.size() > 0 ) {// le joueur a pris des tuiles joker

                             resteL = currentPlayer.getP().lm.DeposerTuiles(tuilesJ, ligne - 1);
                              if (resteL.size() > 0) {
                                 resteP = currentPlayer.getP().p.add(resteL);
                                 if (resteP.size() > 0) {
                                     partie.alimenterDefausse(resteP);
                                     partie.alimenterDefausse(tuiles);
                                 } else {
                                     resteP = currentPlayer.getP().p.add(tuiles);
                                     if (resteP.size() > 0) partie.alimenterDefausse(resteP);
                                 }
                              } else {// resteL.size == 0
                                 resteL = currentPlayer.getP().lm.DeposerTuiles(tuiles, ligne - 1);
                                 if (resteL.size() > 0) {
                                     resteP = currentPlayer.getP().p.add(resteL);
                                     if (resteP.size() > 0) {
                                         partie.alimenterDefausse(resteP);
                                     }
                                 }
                              }

                             bonChoixLigne = true ;
                             return true ;

                         }else { // le joueur a pris que des tuiles colores

                             if ( tuiles.get(0) instanceof TuileColore ){//toujours valide
                                 if( currentPlayer.getP().m.dejaValidee( ((TuileColore) tuiles.get(0)).getCouleur() , ligne -1 ) ) {
                                	 printG("Cette couleur est deja Validee sur le Mur Veuillez chosir une autre ligne ");
                                	 bonChoixLigne = false ;

                                 }else {
                                     resteL = currentPlayer.getP().lm.DeposerTuiles(tuiles, ligne -1 ) ;

                                     if ( resteL.size() > 0 ){
                                         resteP = currentPlayer.getP().p.add(resteL) ;
                                         if ( resteP.size() > 0 ){
                                             partie.alimenterDefausse(resteP);
                                         }
                                     }
                                    resteP.clear();
                                    resteL.clear();
                                    bonChoixLigne = true ;
                                    return true ;
                                 }
                             }
                         }


                     }



           }else{ // si le joueur a pris peut etre des tuiles joker seulement

                 if ( tuilesJ.size() > 0 ) {
                      resteL = currentPlayer.getP().lm.DeposerTuiles(tuilesJ , ligne -1 ) ;
                      if( resteL.size() > 0){
                          resteP = currentPlayer.getP().p.add(resteL);
                          if (resteP.size() > 0 ) partie.alimenterDefausse(resteP);
                      }
                 }

                 bonChoixLigne = true ;
                 return true ;
           }



           } // while

        }else {// si le joueur choisi de mettre les tuiles dans le plancher
            // si t != null in voquer la methode deposer plancher du joueur
            // pour mettre la tuilePremJoueur en premier
            if ( tuiles.size() > 0){
                resteP = currentPlayer.getP().p.add(tuiles);
                if (resteP.size() > 0 ) partie.alimenterDefausse(resteP);
                resteL.clear();
                resteP.clear();
            }
            if (tuilesJ.size() > 0 ){
                resteP = currentPlayer.getP().p.add(tuilesJ);
                if (resteP.size() > 0 ) partie.alimenterDefausse(resteP);
                resteL.clear();
                resteP.clear();
            }
            return true ;
        }

        return false ;

    }


    public void phaseDecoration( boolean mode ){

        ArrayList<Joueur> joueurs = partie.getJoueurs() ;
        ArrayList<Tuile> reste = new ArrayList<>(); // utilsee pour recuperer le reste des tuiles des lignes motifs et le plancher
        Tuile tMur ; // la tuile a placer sur le mur
        Tuile tPrem  = null ; //la tuile Premier joueur
        int colonne , j = 0 ;
        boolean stop = false ;

      for  (int i = 0 ; i < joueurs.size() ; i ++ ) {// pour chaque joueur de la partie

          for ( int k = 0 ; k < 5 ; k ++ ) { // pour chaque ligne du mur du joueur

              if ( joueurs.get(i).getP().lm.lignePleine( k )) { // si la ligne est pleine


                  if (mode) {// le mode colore

                        tMur = joueurs.get(i).getP().lm.getTuileColore(k) ;
                        reste = joueurs.get(i).getP().lm.viderLigne(k) ;
                        partie.alimenterDefausse(reste);
                        if ( joueurs.get(i).getP().m instanceof MurColore ) { // toujours verifiee car mode colore
                            joueurs.get(i).addScore(((MurColore) joueurs.get(i).getP().m).deposerTuile(k, tMur));
                        }

                  } else { // mode incolore

                      if ( joueurs.get(i).getP().lm.containJoker( k ) ){// si la ligne contient au moins une tuile joker

                          tMur = joueurs.get(i).getP().lm.getTuileJoker( k ) ;
                          reste = joueurs.get(i).getP().lm.viderLigne( k ) ;
                          partie.alimenterDefausse( reste );
                          // demander au joueur de choisir la colonne pour deposer ses tuiles
                          colonne = LireDonnes.colonneMur(joueurs.get(i).getNom(),v) - 1  ;
                          while ( !joueurs.get(i).getP().m.caseVide( k , colonne )) {
                        	  printG(" Veuillez introduire une colonne vide ");
                              colonne = LireDonnes.colonneMur(joueurs.get(i).getNom(),v) - 1 ;
                          }
                          if ( joueurs.get(i).getP().m instanceof  MurIncolore ){// toujours verifie car mode incolore

                              joueurs.get(i).addScore ( ((MurIncolore) joueurs.get(i).getP().m).deposerTuile(k , colonne , tMur ) );
                          }


                      }else { // la ligne ne contient aucune tuile joker

                          tMur = joueurs.get(i).getP().lm.getTuileColore( k ) ;
                          reste = joueurs.get(i).getP().lm.viderLigne( k ) ;
                          partie.alimenterDefausse( reste );
                          // demander au joueur de choisir la colonne pour deposer ses tuiles
                          colonne = LireDonnes.colonneMur(joueurs.get(i).getNom(),v) - 1 ;
                          if ( joueurs.get(i).getP().m instanceof  MurIncolore ){// toujours verifie car mode incolore
                          while ( !joueurs.get(i).getP().m.caseVide( k , colonne ) || ((MurIncolore) joueurs.get(i).getP().m).existeDeja(k,colonne, (TuileColore) tMur) ) {
                              printG(" Veuillez introduire une colonne vide ou qui ne contient ps une tuile de la meme couleur ");
                              colonne = LireDonnes.colonneMur( joueurs.get(i).getNom() ,v) - 1 ;
                          }

                              joueurs.get(i).addScore ( ((MurIncolore) joueurs.get(i).getP().m).deposerTuile(k , colonne , tMur ) );
                          }

                      }



                  }

                  reste.clear();
              }

          }

          // FIXME Verifier si le joueur a complete au moins une ligne de son mur pour declancher la fin de la partie
          // a la fin de cette manche
          while (  j < 5 && !stop ){
              if ( joueurs.get(i).getP().m.ligneComplete(j)) {
                  partie.arreterPartie();
                  stop = true ;
              }
            j ++ ;
          }
          if ( !stop ) j = 0 ;// reinitialiser la variable  pour la verification du prochain joueur si stop est toujours a faux

          // Nettoyage du plancher

           joueurs.get(i).addScore(joueurs.get(i).getP().p.calculPoints()) ;
           tPrem = joueurs.get(i).getP().p.getTuilePremJoueur();
          
           if ( tPrem != null )   partie.getSac().addTuile(tPrem) ;
          reste = joueurs.get(i).getP().p.viderPlancher() ;
          partie.alimenterDefausse(reste);// mettre les tuiles du plancher dans la defausse
           // pass to the next player
      }
    }
}
