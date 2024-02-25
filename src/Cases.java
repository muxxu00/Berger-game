public abstract class Cases {
    public final int lig;
    public final int col;

    protected Cases(int l, int c){
        this.lig=l;
        this.col=c;
    }

    public abstract boolean estLibre();

    public abstract void vide();

    public abstract void entre(Entite e);

    public Entite getContenu() {
        return null;
    }

    public Entite getEntite() {
        return null;
    }

}