import java.util.Random;

/* Directions cardinales */
public enum Direction {
    nord,
    sud,
    est,
    ouest;

    /* Lie le symbole représentant une créature et sa direction */
    public static Direction ofChar(Character d) {
        switch (d) {
            case '^': case 'm': return Direction.nord;
            case 'v': case 'w': return Direction.sud;
            case '>': case '»': return Direction.est;
            case '<': case '«': return Direction.ouest;
        }
        return null;
    }

    /* Tirage d'une direction aléatoire */
    public static Direction random() {
        Random rnd = new Random();
        int r = rnd.nextInt(4);
        switch (r) {
            case 0:  return nord;
            case 1:  return sud;
            case 2:  return est;
            default: return ouest;
        }
    }

}
