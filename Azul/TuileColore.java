public class TuileColore extends Tuile {

      private char couleur ;


      public TuileColore ( char couleur ){
            this.couleur = couleur ;
      }
      public char getCouleur(){
            return this.couleur ;
      }
      
      public String toString() {
    	  return ""+couleur;
      }
}
