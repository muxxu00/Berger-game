import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Terrain {

    private int hauteur, largeur;
    private Cases[][] carte;

    private Joueur joueur;
    public Joueur getJoueur(){ return this.joueur; }

    /* Initialisation d'un terrain à partir de la description donnée par
       un fichier texte. Format du fichier de description :
       - hauteur et largeur sur la première ligne
       - puis dessin du terrain (un caractère == une case) avec le codage
         suivant
         '#' pour un mur
         ' ' (espace) pour une case libre
         'o' pour une sortie
         '@' pour une case libre contenant un obstacle
         '^', 'v', '>', '<' pour une case libre contenant un personnage
         'm', 'w', '»', '«' pour une case libre contenant un monstre
    */
    public Terrain(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();
            this.carte = new Cases[hauteur][largeur];
            for (int l = 0; l < hauteur; l++) {
                String line = sc.nextLine();
                for (int c = 0; c < largeur; c++) {
                    Cases cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case 'H':
                            cc = new CaseLibre(l, c);   // créer la case
                            this.joueur = new Joueur(cc, 3);
                            cc.entre(this.joueur);    // installe le joueur dessus
                            break;
                        case '#':
                            cc = new CaseIntraversable(l, c);
                            break;
                        case ' ':
                            cc = new CaseLibre(l, c);
                            break;
                        case 'o':
                            cc = new Sortie(l, c);
                            break;
                        case '@':
                            cc = new CaseLibre(l, c, new Obstacle());
                            break;
                        case '^':
                        case '>':
                        case 'v':
                        case '<':
                            cc = new CaseLibre(l, c, new Personnage(Direction.ofChar(ch)));
                            break;
                        case 'm':
                        case '»':
                        case 'w':
                        case '«':
                            cc = new CaseLibre(l, c, new Monstre(Direction.ofChar(ch)));
                            break;
                        default:
                            cc = null;
                            break;
                    }
                    carte[l][c] = cc;
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                System.out.print(carte[i][j].toString());
            }
            System.out.println();
        }
    }

    public Cases caseEnFace(Cases courante, Direction d){
        switch (d) {
            case nord: return this.carte[courante.lig-1][courante.col];
            case sud: return this.carte[courante.lig+1][courante.col];
            case est: return this.carte[courante.lig][courante.col+1];
            case ouest: return this.carte[courante.lig][courante.col-1];
        }
        return courante;
    }

    public Cases[][] getCarte(){
        return this.carte;
    }

    public int getHauteur(){
        return this.hauteur;
    }
    public int getLargeur(){
        return this.largeur;
    }

}