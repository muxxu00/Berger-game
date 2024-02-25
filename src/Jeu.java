import java.util.ArrayList;
import java.util.Random;

public class Jeu {

    Terrain terrain;
    public static int sortis;

    /* Initialisation d'un jeu avec le terrain initial décrit dans
       le fichier [f] donnÃ© en paramÃ¨tre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
        this.sortis = 0;
    }

    public void tour(){

        ArrayList a = new ArrayList<EntiteMobile>();
        Cases[][] b = terrain.getCarte();
        CaseTraversable z;
        boolean blackList = false;
        for(int i= 0; i < b.length; i++){
            for(int j= 0; j< b[i].length; j++){
                if(b[i][j] instanceof CaseTraversable) { //pour etre sur

                    z = (CaseTraversable) b[i][j];
                    blackList = false;

                    if ((z.getContenu() instanceof Personnage || z.getContenu() instanceof Monstre)) { // EntiteMobile
                        for(int k = 0; k < a.size(); k++){
                            if (z.getContenu().equals(a.get(k))) {
                                blackList = true; break; //sortir des que blackList vrai
                            }
                        }
                        if(!blackList) {
                            a.add(z.getContenu());
                            z.getContenu().action(z, this.terrain.caseEnFace(z, z.getContenu().getD()));

                            blackList = false;
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        Jeu j = new Jeu("laby2.txt");
        for(int i= 0; i < 3; i++){
            j.tour();
            j.terrain.print();
        }
        j.terrain.print();
    }

    public static void setS(int a){
        sortis = sortis + a;
    }

    public boolean partieFinie() {
        return terrain.getJoueur().a; // if joueur sur sortie et espace
    }
}