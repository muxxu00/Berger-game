import java.util.ArrayList;

public class CaseTraversable extends Cases {
    protected Entite e;

    protected CaseTraversable(int l, int c, Entite e) {
        super(l, c);
        this.e = e;
    }

    public Entite getContenu(){
        return this.e;
    }

    public void vide(){
        this.e = null;
    }

    public void entre(Entite e){
        this.e = e;
    }

    public String toString(){
        if(this.estLibre()){
            return ("   ");
        }else{
            return e.toString("   ");
        }
    }

    @Override
    public boolean estLibre() {
        if (this.e == null){
            return true;
        }else{
            return false;
        }
    }



}

