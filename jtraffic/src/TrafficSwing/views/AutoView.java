package TrafficSwing.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import RushHour.*;
import TrafficSwing.resources.ResourceManager;
import TrafficSwing.Application;
import javax.swing.border.EmptyBorder;

/**
 * De view klasse voor Auto
 * @author bloodsplatter
 * @version 2009.05.13
 */
public class AutoView extends VoertuigView {
    /**
     * Breedte van de afbeelding
     */
    public static final int IMAGE_WIDTH = 144;
    /**
     * Hoogte van de afbeelding
     */
    public static final int IMAGE_HEIGHT = 72;

    /**
     * Constructor
     * @param auto
     */
    public AutoView(Auto auto)
    {
        super();

        voertuig = auto;
        img = ResourceManager.getInstance().getAfbeeldingVoorVoertuig(voertuig);
        super.add(new JLabel(img), BorderLayout.CENTER);

        if (voertuig.getOrientatie() == Orientatie.Horizontaal)
        {
            // knop naar links
            dirButton = new JButton(ResourceManager.getInstance().getPijl(ResourceManager.PIJL_LINKS));
            dirButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (voertuig.NaarLinks())
                    {
                        setBounds(voertuig.getX(), voertuig.getY(), getSize().width, getSize().height);
                        Application.getInstance().getContentPane().repaint();
                    }
                }
            });
            dirButton.setVisible(false);
            dirButton.setBorder(new EmptyBorder(2, 2, 2, 2));
            super.add(dirButton,BorderLayout.WEST);

            // knop naar rechts
            odirButton = new JButton(ResourceManager.getInstance().getPijl(ResourceManager.PIJL_RECHTS));
            odirButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (voertuig.NaarRechts())
                    {
                        setBounds(voertuig.getX(), voertuig.getY(), getSize().width, getSize().height);
                        Application.getInstance().getContentPane().repaint();
                    }
                }
            });
            odirButton.setVisible(false);
            odirButton.setBorder(new EmptyBorder(2, 2, 2, 2));
            super.add(odirButton,BorderLayout.EAST);

        } else
        {
            // knop naar boven
            dirButton = new JButton(ResourceManager.getInstance().getPijl(ResourceManager.PIJL_BOVEN));
            dirButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (voertuig.NaarBoven())
                        setBounds(voertuig.getX(), voertuig.getY(), getSize().width, getSize().height);
                        Application.getInstance().getContentPane().repaint();
                }
            });
            dirButton.setVisible(false);
            super.add(dirButton,BorderLayout.NORTH);

            // knop naar onder
            odirButton = new JButton(ResourceManager.getInstance().getPijl(ResourceManager.PIJL_BENEDEN));
            odirButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (voertuig.NaarBeneden())
                        setBounds(voertuig.getX(), voertuig.getY(), getSize().width, getSize().height);
                        Application.getInstance().getContentPane().repaint();
                }
            });
            odirButton.setVisible(false);
            super.add(odirButton,BorderLayout.SOUTH);
        }

        super.addMouseListener(new MouseListener() {

             public void mouseClicked(MouseEvent e) {

                if (!knoppenZichtbaar())
                {
                    odirButton.setVisible(true);
                    dirButton.setVisible(true);
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        this.setBounds(voertuig.getX(), voertuig.getY(), (voertuig.getOrientatie()==Orientatie.Horizontaal)?IMAGE_WIDTH:IMAGE_HEIGHT, (voertuig.getOrientatie()==Orientatie.Verticaal)?IMAGE_WIDTH:IMAGE_HEIGHT);
    }

    /**
     * Geeft de onderliggende auto
     * @return de onderliggende Auto
     */
    public Auto getAuto()
    {
        return (Auto)super.voertuig;
    }

    /**
     * Steld de auto in om te weergeven
     * @param auto
     */
    public void setAuto(Auto auto)
    {
        voertuig = auto;
    }

    /**
     * Creëert een view voor de opgegeven auto
     * @param auto de auto waarvan je een view wilt
     * @return de view voor de opgegeven auto
     */
    public static AutoView createAutoView(Auto auto)
    {
        AutoView av = new AutoView(auto);
        av.setPositie(new Point(auto.getX(),auto.getY()));
        return av;
    }
}