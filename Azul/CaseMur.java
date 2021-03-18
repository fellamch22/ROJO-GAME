public class CaseMur extends Case {

    private char color ; /** precise la couleur de la tuile que doit contenir la case du mur */

    /** Constructeur de la classe */
    public CaseMur(char color){
        this.color = color ;
    }

    /** retourne la couleur que doit contenir la case du mur **/
    public char getColor(){
        return this.color ;
    }
}
