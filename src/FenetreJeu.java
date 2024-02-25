import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class FenetreJeu extends JPanel implements KeyListener {
    private Terrain terrain;
    private int tailleCase = 24;
    private int hauteur, largeur;
    private JFrame frame;
    private Joueur joueur;


    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;
        this.joueur = t.getJoueur();

        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(largeur * tailleCase, hauteur * tailleCase));

        JFrame frame = new JFrame("Donjon");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);

        //JLabel label = new JLabel();
        //label.setIcon(new ImageIcon("Capture.png"));

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tempX =0;
        int tempY =0;

        for (Cases[] cases : this.terrain.getCarte()) {
            for (Cases c : cases) {
                tempX = 0; tempY = 0;
                if(c instanceof CaseIntraversable){
                    g.setColor(Color.BLACK);
                    g.fillRect(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);

                    //JLabel label = new JLabel();
                    //label.setIcon(new ImageIcon("Capture.png"));
                    //label.setBounds(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                    //ImageIcon icone = new ImageIcon("capture.png");
                    //JLabel image = new JLabel(icone);
                    //frame.add(image);
                    //frame.setVisible(true);
                }
                if(c instanceof Sortie){
                    g.setColor(Color.blue);
                    g.fillRect(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                    g.setColor(Color.white);
                    g.fillOval(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                }

                if(c instanceof CaseTraversable){
                    if(c.getContenu() instanceof Obstacle){
                        g.setColor(Color.cyan);
                        g.fillOval(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                    }
                    if(c.getContenu() instanceof Personnage){
                        g.setColor(Color.blue);
                        g.fillOval(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                        //direction :
                        switch(c.getContenu().getD()){
                            case nord:
                                tempX = tailleCase/3;
                                break;
                            case sud:
                                tempX = tailleCase/3;
                                tempY = 2*tailleCase/3; // valeurs trouvees par experience
                                break;
                            case est:
                                tempY = tailleCase/3;
                                tempX = 2*tailleCase/3;
                                break;
                            case ouest:
                                tempY = tailleCase/3;
                                break;
                        }
                        g.setColor(Color.BLACK);
                        g.fillOval(c.col*tailleCase + tempX, c.lig*tailleCase+tempY, tailleCase/4, tailleCase/4);
                    }
                    if(c.getContenu() instanceof Monstre){
                        g.setColor(Color.red);
                        g.fillOval(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                        //direction :
                        switch(c.getContenu().getD()){
                            case nord:
                                tempX = tailleCase/3;
                                break;
                            case sud:
                                tempX = tailleCase/3;
                                tempY = 2*tailleCase/3;
                                break;
                            case est:
                                tempY = tailleCase/3;
                                tempX = 2*tailleCase/3;
                                break;
                            case ouest:
                                tempY = tailleCase/3;
                                break;
                        }
                        g.setColor(Color.BLACK);
                        g.fillOval(c.col*tailleCase + tempX, c.lig*tailleCase+tempY, tailleCase/4, tailleCase/4);

                    }
                    if(c.getContenu() instanceof Joueur){
                        g.setColor(Color.GREEN);
                        g.fillOval(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                        /*
                        //direction :
                        switch(c.getContenu().getD()){
                            case nord:
                                tempX = tailleCase/3;
                                break;
                            case sud:
                                tempX = tailleCase/3;
                                tempY = 2*tailleCase/3;
                                break;
                            case est:
                                tempY = tailleCase/3;
                                tempX = 2*tailleCase/3;
                                break;
                            case ouest:
                                tempY = tailleCase/3;
                                break;
                        }
                        g.setColor(Color.BLACK);
                        g.fillOval(c.lig*tailleCase + tempX, c.col*tailleCase+tempY, tailleCase/4, tailleCase/4);
                        */
                    }
                }
            }
        }

    }

    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }

/*
    public static boolean spacePress(KeyEvent ke){
        if(ke.getKeyCode()==KeyEvent.VK_SPACE){
            return true;
        }
        return false;
    }
*/

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyCode()){
            case VK_Z: this.joueur.action(this.joueur.getCases(), this.terrain.caseEnFace(this.joueur.getCases(), Direction.nord)); break;
            case VK_Q: this.joueur.action(this.joueur.getCases(), this.terrain.caseEnFace(this.joueur.getCases(), Direction.ouest)); break;
            case VK_S: this.joueur.action(this.joueur.getCases(), this.terrain.caseEnFace(this.joueur.getCases(), Direction.sud)); break;
            case VK_D: this.joueur.action(this.joueur.getCases(), this.terrain.caseEnFace(this.joueur.getCases(), Direction.est)); break;
        }
        if (e.getKeyCode() == VK_SPACE) {
            if (this.joueur.getCases() instanceof Sortie) {
                this.joueur.getCases().vide();
                    //nb moutons,...
            }
        }
        // else dire qu'on est pas sur une sortie
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case VK_Z : this.joueur.action(this.joueur.getCases(), this.terrain.caseEnFace(this.joueur.getCases(), Direction.nord)); break;
            case VK_Q : this.joueur.action(this.joueur.getCases(), this.terrain.caseEnFace(this.joueur.getCases(), Direction.ouest)); break;
            case VK_S : this.joueur.action(this.joueur.getCases(), this.terrain.caseEnFace(this.joueur.getCases(), Direction.sud)); break;
            case VK_D : this.joueur.action(this.joueur.getCases(), this.terrain.caseEnFace(this.joueur.getCases(), Direction.est)); break;
        }
        if(e.getKeyCode() == VK_SPACE){
            if(this.joueur.getCases() instanceof Sortie){
                this.joueur.getCases().vide();
                //nb moutons,...
            }
            // else dire qu'on est pas sur une sortie
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
