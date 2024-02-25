public class CaseIntraversable extends Cases{

    protected CaseIntraversable(int l, int c) {
        super(l, c);
    }


    public String toString() {
        return "###";
    }

    @Override
    public boolean estLibre() {
        return false;
    }

    @Override
    public void vide() {}

    @Override
    public void entre(Entite e) {}


}
