public class Joueur {

    private String nom ;
    private int score ;
    private PlateauJoueur plateau ;

    public Joueur(String nom, int score , boolean mode  ) {

        this.nom = nom ;
        this.score  = score ;
        this.plateau = new PlateauJoueur(mode) ;
    }

    //Getter and setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int sco) {
        this.score += sco;
    }

    public PlateauJoueur getP() {
        return plateau ;
    }


    //toString
    @Override
    public String toString() {
        return "\n----------------------------------------\n" + nom + " - Score = " + score + plateau ;
    }


}
