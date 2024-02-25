public class Obstacle extends Entite{

    private int resistance;
    public Obstacle() {
        this.resistance = 3;
    }

    public Obstacle(int resistance) {
        this.resistance = resistance;
    }

    public String toString(String background){
        if(this.resistance >= 3){ return "@@@"; }
        else if(this.resistance == 1){ return background.charAt(0) + "@" + background.charAt(2); }
        else if(this.resistance == 2){ return "@@" + background.charAt(2); }
        else{
            return "pas possible";
        }
    }

    public void setR(int a){ this.resistance = a;}
    public int getR(){ return this.resistance;}

}
