
package RushHour;

/**
 * Een auto
 * @author bloodsplatter
 * @see RushHour.Voertuig
 * @version 2009.03.03
 */
public class Auto extends Voertuig {
    private int grootte;

    /**
     * Constructor
     * @param orientatie de oriëntatie van het voertuig
     */
    public Auto(Orientatie orientatie) {
        super(orientatie);
        grootte = 2;
    }

    /**
     * Maak een auto op de opgegeven locatie aan
     * @param X de X-coördinaat
     * @param Y de Y-coördinaat
     * @param Kleur de kleur van de wagen
     */
    public Auto(int X, int Y,char Kleur) {
        super(X, Y,Kleur);
        grootte = 2;
    }

    /**
     * Constructor
     */
    public Auto() {
        super(0,0,' ');
        grootte = 2;
    }

    /**
     * Constructor
     * @param x X-coördinaat
     * @param y Y-coördinaat
     */
    public Auto(int x, int y)
    {
        super(x,y,' ');
        grootte = 2;
    }

    /**
     * Geeft de grote van het voertuig
     * @return de grote van het voertuig
     */
    public int getGrootte() {
        return grootte;
    }

    
    public Voertuig geefKopie() {
        Auto ret = new Auto(this.getX(), this.getY(), this.getKleur());
        ret.setOrientatie(this.getOrientatie());
        return ret;
    }

}