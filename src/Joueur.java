import java.awt.event.KeyEvent;

public class Joueur extends Entite{

    public boolean a = false;
    private Cases c;

    public Joueur(Cases b, int a){
        super(a);
        this.c = b;
    }

    public Cases getCases(){
        return this.c;
    }


    public void action(Cases courante, Cases cible) {
        if(cible.getContenu() instanceof Monstre){
        }else if(cible.getContenu() instanceof Personnage){
        }else if(cible instanceof CaseLibre) {// déplacement
            this.c = cible;
            cible.entre(courante.getContenu());
            courante.vide();
        }else if(cible instanceof CaseIntraversable){
        }else if(cible instanceof Sortie){
            courante.vide();
            this.a = true;
        }
    }

    @Override
    public String toString(String background) {
        return background.charAt(0) + "H" + background.charAt(2);
    }
}
