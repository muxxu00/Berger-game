public class Monstre extends EntiteMobile{
    private Direction d;
    private int resistance;

    public Monstre(Direction a) {
        super(a);
        resistance = 3;
    }

    public String toString(String background){
        char b = ' ';
        char a = background.charAt(0);
        char c = background.charAt(2);
        switch(super.d){
            case nord :  b = 'm'; break;
            case sud :  b = 'w'; break;
            case est :  b = '»'; break;
            case ouest :  b = '«'; break;
            default : System.out.println("pas possible"); break;
        }
        //return String.valueOf(a + b + c);
        String s = new StringBuilder().append(a).append(b).append(c).toString();
        return s;

    }

    public int getR(){
        return resistance;
    }
    public void setR(int a){
        resistance = a;
    }


    public void action(Cases courante, Cases cible) {
        if (!(cible instanceof Sortie)) {
            if (cible.getContenu() instanceof Personnage) {
                Personnage a = (Personnage) cible.getContenu();
                a.setR(a.getR() - 1);
                if (a.getR() == 0) {
                    cible.vide();
                } else {
                    cible.vide();
                    cible.entre(a);
                }
            }else if(cible.getContenu() instanceof  Monstre){
                courante.getContenu().setD(Direction.random());
            }else if (cible.getContenu() instanceof Obstacle) {
                Obstacle a = (Obstacle) cible.getContenu();
                a.setR(a.getR() - 1);
                if (a.getR() == 0) {
                    cible.vide();
                } else {
                    cible.vide();
                    cible.entre(a);
                }
            }
            else if(cible instanceof CaseLibre){    //si vide, avance
                cible.entre(courante.getContenu());
                courante.vide();
            }else if (cible.getContenu() instanceof Joueur) {
                Joueur a = (Joueur) cible.getContenu();
                a.setR(a.getR() - 1);
                if (a.getR() == 0) {
                    cible.vide();
                } else {
                    cible.vide();
                    cible.entre(a);
                }
            }else{  //changement de direction car bloqué
                courante.getContenu().setD(Direction.random());
            }
        }else{
            courante.getContenu().setD(Direction.random());
        }
    }
}