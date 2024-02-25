public abstract class EntiteMobile extends Entite{
    protected Direction d;
    public EntiteMobile(Direction a){
        this.d = a;
    }

    public void action(Cases courante, Cases cible) {
    }

    public void setD(Direction a) {this.d = a;}
    public Direction getD() { return this.d; }
}

