package RushHour;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *  <p style="margin-top: 0">
 *        Een level
 *      </p>
 * @author bloodsplatter
 * @version 2009.04.27
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.A1990BC5-05D5-C36D-C599-846C8E6F96CB]
// </editor-fold> 
public class Level implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5234BB41-70F2-FEE4-E9D6-C975C6805708]
    // </editor-fold> 
    private Speelveld veld = new Speelveld(6,6);

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F0552C0C-0429-8133-D01A-6AFD160DF257]
    // </editor-fold> 
    private ArrayList<Voertuig> voertuigen;
    private ArrayList<Voertuig> original;
    private String naam = "";
    private int stappen = 0;

    /**
     * Geeft de naam van de level
     * @return de naam van de level
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Stelt de naam van de level in
     * @param naam de gewenste naam van de level
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }


    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D3AA990C-E595-4085-FF55-7F09B5CE95F2]
    // </editor-fold> 
    /**
     * Constructor
     */
    public Level() {
        this.voertuigen = new ArrayList<Voertuig>();
        this.original = new ArrayList<Voertuig>();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A9F52AD7-8366-ACC3-67CC-93A0E2FB2BCE]
    // </editor-fold> 
    /**
     * Geeft het speelveld
     * @return het speelveld
     */
    public Speelveld getVeld() {
        return veld;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F40D4C23-C90B-4328-0972-643BFEE3D1B1]
    // </editor-fold> 
    /**
     * Stelt het speelveld in
     * @param val het gewenste speelveld
     */
    public void setVeld(Speelveld val) {
        this.veld = val;
    }

    /**
     *  <p style="margin-top: 0">
     *        Geeft een voertuig uit de lijst
     *      </p>
     * @param plaats de plaats van het voertuig in de lijst
     * @return het gevraagde voertuig
     * @throws ArrayIndexOutOfBoundsException als de plaats niet bestaat
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4AA52795-976D-7625-5514-67D107760A82]
    // </editor-fold> 
    public Voertuig voertuigOpPlaats(int plaats) throws ArrayIndexOutOfBoundsException {
        if (plaats >= 0 && plaats < voertuigen.size()) {
            return voertuigen.get(plaats);
        } else {
            throw new ArrayIndexOutOfBoundsException("Zo veel voertuigen zijn er niet.");
        }
    }

    /**
     * Geeft het aantal voertuigen in de level
     * @return het aantal voertuigen in de level
     */
    public int aantalVoertuigen()
    {
        return this.voertuigen.size();
    }

    /**
     * Zoekt een voertuig op een bepaalde positie
     * @param X de X-coördinaat van het voertuig
     * @param Y de Y-coördinaat van het voertuig
     * @return null als het voertuig niet gevonden is, anders het voertuig zelf
     * @see RushHour.Voertuig
     */
    public Voertuig voertuigOpPositie(int X, int Y) {
        Voertuig voertuig = null;
        Voertuig tmvoertuig = null;
        Iterator<Voertuig> iterator = voertuigen.iterator();
        while (iterator.hasNext()) {
            tmvoertuig = iterator.next();
            if ((tmvoertuig.getX() == X && tmvoertuig.getY() == Y) || (tmvoertuig.getOrientatie() == Orientatie.Horizontaal && tmvoertuig.getX() + 1 == X && tmvoertuig.getY() == Y) || (tmvoertuig.getOrientatie() == Orientatie.Verticaal && tmvoertuig.getX() == X && tmvoertuig.getY() + 1 == Y) || (tmvoertuig.getGrootte() == 3 && tmvoertuig.getOrientatie() == Orientatie.Horizontaal && tmvoertuig.getX() + 2 == X && tmvoertuig.getY() == Y) || (tmvoertuig.getGrootte() == 3 && tmvoertuig.getOrientatie() == Orientatie.Verticaal && tmvoertuig.getX() == X && tmvoertuig.getY() + 2 == Y)) {
                voertuig = tmvoertuig;
                break;
            }
        }

        return voertuig;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D2B3617D-ADA4-A3D4-308C-3734863ECB11]
    // </editor-fold>
    /**
     * Voegt een voertuig aan de level toe
     * @param voertuig het voertuig dat toegevoegd moet worden
     */
    public void voegVoertuigToe(Voertuig voertuig) {
        if (!voertuigen.contains(voertuig)) {
            voertuig.setLevel(this);
        }
        voertuigen.add(voertuig);
        original.add(voertuig.geefKopie());
    }

    @Override
    public String toString() {
        StringBuilder strout = new StringBuilder();
        int hoogtem = this.veld.getHoogte() + 2;
        int breetem = this.veld.getBreedte() + 2;
        char dispmatrx[][] = new char[hoogtem][breetem * 2];

        for (int i = 0; i < hoogtem; i++) {
            for (int j = 0; j < breetem * 2; j += 2) {
                if (i == 0 || i == hoogtem - 1) {
                    dispmatrx[i][j] = '-';
                    dispmatrx[i][j + 1] = '-';
                } else if (i == hoogtem / 2 && j == 0) {
                    dispmatrx[i][j] = ' ';
                    dispmatrx[i][j + 1] = ' ';
                } else if (j == 0) {
                    dispmatrx[i][j] = '|';
                    dispmatrx[i][j + 1] = ' ';
                } else if (j == breetem * 2 - 2) {
                    dispmatrx[i][j] = ' ';
                    dispmatrx[i][j + 1] = '|';
                } else {
                    dispmatrx[i][j] = ' ';
                    dispmatrx[i][j + 1] = ' ';
                }
            }
        }
        dispmatrx[0][0] = '+';
        dispmatrx[0][breetem * 2 - 1] = '+';
        dispmatrx[hoogtem - 1][0] = '+';
        dispmatrx[hoogtem - 1][breetem * 2 - 1] = '+';

        Iterator<Voertuig> itr = voertuigen.iterator();
        Voertuig tmpvrt;
        while (itr.hasNext()) {
            tmpvrt = itr.next();
            if (tmpvrt.getOrientatie() == Orientatie.Horizontaal) {

                dispmatrx[(1 + tmpvrt.getY())][(2 + tmpvrt.getX() * 2)] = tmpvrt.getKleurChar();
                dispmatrx[(1 + tmpvrt.getY())][(2 + tmpvrt.getX() * 2 + 1)] = tmpvrt.getKleurChar();
                dispmatrx[(1 + tmpvrt.getY())][(2 + tmpvrt.getX() * 2) + 2] = tmpvrt.getKleurChar();
                dispmatrx[(1 + tmpvrt.getY())][(2 + tmpvrt.getX() * 2 + 3)] = tmpvrt.getKleurChar();
                if (tmpvrt.getGrootte() == 3) {
                    dispmatrx[(1 + tmpvrt.getY())][(2 + tmpvrt.getX() * 2 + 4)] = tmpvrt.getKleurChar();
                    dispmatrx[(1 + tmpvrt.getY())][(2 + tmpvrt.getX() * 2 + 5)] = tmpvrt.getKleurChar();
                }
            } else if (tmpvrt.getOrientatie() == Orientatie.Verticaal) {

                dispmatrx[(1 + tmpvrt.getY())][(2 + tmpvrt.getX() * 2)] = tmpvrt.getKleurChar();
                dispmatrx[(1 + tmpvrt.getY())][(2 + tmpvrt.getX() * 2 + 1)] = tmpvrt.getKleurChar();
                dispmatrx[(1 + tmpvrt.getY() + 1)][(2 + tmpvrt.getX() * 2)] = tmpvrt.getKleurChar();
                dispmatrx[(1 + tmpvrt.getY() + 1)][(2 + tmpvrt.getX() * 2 + 1)] = tmpvrt.getKleurChar();
                if (tmpvrt.getGrootte() == 3) {
                    dispmatrx[(1 + tmpvrt.getY() + 2)][(2 + tmpvrt.getX() * 2)] = tmpvrt.getKleurChar();
                    dispmatrx[(1 + tmpvrt.getY() + 2)][(2 + tmpvrt.getX() * 2 + 1)] = tmpvrt.getKleurChar();
                }
            }

        }

        strout.append("\n\n");
        for (int i = 0; i < hoogtem; i++) {
            for (int j = 0; j < breetem * 2; j++) {
                strout.append(dispmatrx[i][j]);
            }
            strout.append('\n');
        }

        return strout.toString();
    }

    /**
     * Zoek het eerste voertuig met de opgegeven kleur
     * @param kleur de kleur die het voertuig moet hebben
     * @return null als er geen voertuig gevonden is, anders het Voertuig object.
     * @see RushHour.Voertuig
     */
    public Voertuig voertuigMetKleur(char kleur)
    {
        for (Voertuig voertuig : voertuigen) {
            if (String.valueOf(voertuig.getKleurChar()).equalsIgnoreCase(String.valueOf(kleur)))
                return voertuig;
        }

        return null;
    }

    /**
     * Voegt een extra stap toe
     */
    public void voegStapToe()
    {
        stappen++;
    }

    /**
     * Vraagt het aantal stappen op dat al gezet is
     * @return het aantal stappen
     */
    public int getAantalStappen()
    {
        return this.stappen;
    }

    /**
     * Zet het aantal stappen op 0
     */
    public void zetStappenOpNul()
    {
        stappen = 0;
    }

    /**
     * Controleert of de level uitgespeeld is
     * @return true als de level uitgespeeld is, anders false
     */
    public boolean isLevelUit()
    {
        // TODO add checks here
        return (voertuigOpPlaats(0).getX()+1 >= 5 && voertuigOpPlaats(0).getY()+1 == (getVeld().getHoogte() / 2));
    }

    /**
     * Geeft een array met alle voertuigen van de level
     * @return alle voertuigen van de level
     */
    public Voertuig[] getVoertuigen()
    {
        Voertuig[] voertuigen = new Voertuig[this.voertuigen.size()];
        this.voertuigen.toArray(voertuigen);
        return voertuigen;
    }

    /**
     * Reset de level
     */
    public void reset()
    {
        stappen = 0;
        voertuigen = (ArrayList<Voertuig>)original.clone();
        original.clear();

        for(Voertuig voertuig : voertuigen)
        {
            original.add(voertuig.geefKopie());
        }
    }
}
