public class Personnage extends EntiteMobile{

    public Personnage(Direction a) {
        super(a);
    }

    public String toString(String background){
        char b = ' ';
        char a = background.charAt(0);
        char c = background.charAt(2);
        switch(super.d){
            case nord :  b = '^'; break;
            case sud :  b = 'v'; break;
            case est :  b = '>'; break;
            case ouest :  b = '<'; break;
            default : System.out.println("pas possible"); break;
        }
        String s = new StringBuilder().append(a).append(b).append(c).toString();
        return s;
        // return "" + a+ b+c; //marche aussi

    }

    public void setD(Direction a){
        super.d = a;
    }

    /*
    public int getR(){
        return super.resistance;
    }
    public void setR(int a){
        super.resistance = a;
    }
*/
    @Override
    public void action(Cases courante, Cases cible) {
        if(cible.getContenu() instanceof Monstre) {
            courante.getContenu().setD(Direction.random());
        }else if(cible.getContenu() instanceof Personnage){
            courante.getContenu().setD(Direction.random());
        }else if(cible.getContenu() instanceof Joueur){
            courante.getContenu().setD(Direction.random());
        }else if(cible instanceof Sortie){
            courante.vide();   //personnage sauvé
            Jeu.setS(1);
        }
        else if(cible instanceof CaseLibre && cible.getContenu() == null){    //si vide ET aucune entite, avance
            // if toggledByTRESHOLD ...

            cible.entre(courante.getContenu());
            courante.vide();
        }
        else if(cible.getContenu() instanceof Obstacle){
            Obstacle a = (Obstacle) cible.getContenu();
            a.setR(a.getR()-1);
            if(a.getR()==0){
                cible.vide();   // casse l'obstacle
            }
        }
        else if(cible instanceof CaseIntraversable){   // cible non vide, changement de dir
            courante.getContenu().setD(Direction.random());
        }
    }

/*
    //Chantier :

    //si on a fait trop de cases les memes, a chaque nv mouvement, on va scan 360 et si on peut, on prend une issue
    //qui n'est pas cible




        ArrayList hist = new ArrayList<Cases>(); // size 10, remplacer a chaque fois
        int THRESHOLD = 3;
        int compteur = 0;
        int compteurTemp = 0;
        // DANS AVANCE : hist.add(courante);
        for(int i = 0; i < 10; ++){
            for(int j = 0; j < 10; j++){
                if(hist.get(i).equals(hist.get(j))){    compteurTemp++; } //si 2 fois la meme case NE PAS OUBLIER QU IL COMPTE AUSSI LA MM CASE
                if (compteurTemp > compteur){ compteur = compteurTemp; }    // on garde ke plus grand compteur
                compteurTemp = 0;
            }
        }
        if(compteur > THRESHOLD){  trouve occasion de changer de direction a chaque pas  }
*/





}