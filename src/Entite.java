public abstract class Entite {
    protected int resistance;

    public Entite(){ this.resistance = 3;}
    public Entite(int r){ this.resistance = r;}

    public abstract String toString(String background);


    public int getR(){
        return this.resistance;
    }
    public void setR(int a){
        this.resistance = a;
    }
    public void action(Cases courante, Cases cible) {
    }


    // setD et getD doivent etre gardés ici, pk?...
    public void setD(Direction a) {}
    public Direction getD() {return null;}

}
