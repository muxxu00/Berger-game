import java.util.ArrayList;

public class CaseLibre extends CaseTraversable{

    protected CaseLibre(int l, int c, Entite e) {
        super(l, c, e);
    }

    protected CaseLibre(int l, int c) {
        super(l, c, null);
    }

    public Entite getContenu(){
        return super.e;
    }

    public void vide(){
        super.e = null;
    }

    public void entre(Entite e){
        super.e = e;
    }

    public String toString(){
        if(this.estLibre()){
            return ("   ");
        }else{
            return super.e.toString("   ");
        }
    }

    @Override
    public boolean estLibre() {
        if (super.e instanceof Personnage || super.e instanceof Obstacle || super.e instanceof Monstre){ //super pour acceder a l'entite de CaseTraversable et non a celle de CaseLibre qui est tt le temsp null
            return false;
        }else{
            return true;
        }
    }

}
