package TrafficSwing.resources;

import RushHour.*;
import javax.swing.*;
import java.net.*;

/**
 * Resouce manager
 * @author bloodsplatter
 * @version 2009.05.08
 */
public class ResourceManager {

    protected static ResourceManager _instance;
    public static final String PIJL_LINKS = "Links";
    public static final String PIJL_RECHTS = "Rechts";
    public static final String PIJL_BENEDEN = "Beneden";
    public static final String PIJL_BOVEN = "Boven";

    /**
     * Constructor
     */
    protected ResourceManager() {
    }

    /**
     * 
     */
    protected static void createInstance() {
        if (_instance == null) {
            _instance = new ResourceManager();
        }
    }

    /**
     * Gets the ResourceManager instance
     * @return the ResourceManager instance
     */
    public static ResourceManager getInstance() {
        if (_instance == null) {
            createInstance();
        }
        return _instance;
    }

    /**
     * Geeft de afbeelding die bij het voertuig hoort
     * @param voertuig het voertuig waar je de afbeelding voor wilt
     * @return de afbeelding van het voertuig
     */
    public ImageIcon getAfbeeldingVoorVoertuig(Voertuig voertuig) {
        Kleur kleur = voertuig.getKleur();
        String resname = "";
        if (voertuig instanceof Auto) {
            Auto auto = (Auto) voertuig;
            if (kleur == Kleur.Rood && auto.getOrientatie() == Orientatie.Horizontaal)
                return new ImageIcon(this.getClass().getResource(resname+"redcarEW.gif"));

            if (kleur == Kleur.LichtGroen)
                resname += "A";
            else if (kleur == Kleur.DonkerGroen)
                resname += "F";
            else if (kleur == Kleur.Blauw)
                resname += "C";
            else if (kleur == Kleur.Roos)
                resname += "D";
            else if (kleur == Kleur.Paars)
                resname += "E";
            else
                resname += "G";

            resname +="car";
        } else if (voertuig instanceof Vrachtwagen)
        {
            if (kleur == Kleur.Geel)
                resname += "O";
            else if (kleur == Kleur.Blauw)
                resname += "Q";
            else
                resname += "R";

            resname += "lorry";
        }
        resname += (voertuig.getOrientatie() == Orientatie.Horizontaal)?"EW":"NW";
        resname += ".gif";

        return new ImageIcon(this.getClass().getResource(resname));
    }

    /**
     * Geeft de afbeelding van het speelbord
     * @return
     */
    public URL getBord()
    {
        return this.getClass().getResource("board.png");
    }

    /**
     * Geeft de afbeelding van de pijl
     * @param optie de richting van de pijl
     * @return de bijhorende afbeelding of een lege afbeelding als de pijl niet is gevonden
     */
    public ImageIcon getPijl(String optie)
    {
        if (optie.equals(PIJL_LINKS))
            return new ImageIcon(this.getClass().getResource("arrowW.png"));
        if (optie.equals(PIJL_RECHTS))
            return new ImageIcon(this.getClass().getResource("arrowE.png"));
        if (optie.equals(PIJL_BOVEN))
            return new ImageIcon(this.getClass().getResource("arrowN.png"));
        if (optie.equals(PIJL_BENEDEN))
            return new ImageIcon(this.getClass().getResource("arrowS.png"));

        return new ImageIcon();
    }
}