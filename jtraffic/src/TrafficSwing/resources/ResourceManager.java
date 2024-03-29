package TrafficSwing.resources;

import RushHour.*;
import java.awt.Image;
import java.io.IOException;
import javax.swing.*;
import java.net.*;
import javax.imageio.ImageIO;

/**
 * Resouce manager
 * @author bloodsplatter
 * @version 2009.05.08
 */
public class ResourceManager {

    /**
     * De instance
     */
    protected static ResourceManager _instance;
    /**
     * De constante voor de pijl naar links
     */
    public static final String PIJL_LINKS = "Links";
    /**
     * De constante voor de pijl naar rechts
     */
    public static final String PIJL_RECHTS = "Rechts";
    /**
     * De constante voor de pijl naar beneden
     */
    public static final String PIJL_BENEDEN = "Beneden";
    /**
     * De constante voor de pijl naar boven
     */
    public static final String PIJL_BOVEN = "Boven";

    /**
     * Constructor
     */
    protected ResourceManager() {
    }

    /**
     * Maakt een instance aan als deze nog niet bestaat
     */
    protected static void createInstance() {
        if (_instance == null) {
            _instance = new ResourceManager();
        }
    }

    /**
     * Geeft de ResourceManagerInstance
     * @return de ResourceManager instance
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
        resname += (voertuig.getOrientatie() == Orientatie.Horizontaal)?"EW":"NS";
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

    /**
     * Geeft het icoon voor het programma
     * @return het image object voor het icoon
     */
    public Image getIcoon()
    {
        try {
            Image img = ImageIO.read(this.getClass().getResource("icon.png"));
            return img;
        } catch (IOException ex) { }
        return null;
    }
}
