
package TrafficSwing;

import java.awt.*;
import java.awt.event.*;
import RushHour.*;
import TrafficSwing.views.*;
import javax.swing.*;
import java.util.*;

/**
 * The main Swing Application
 * @author bloodsplatter
 * @version 2009.05.15
 */
public class Application extends JFrame {
    protected static Application _instance = null;
    protected JMenuBar mainMenuBar;
    protected HighScoreView hsv;

    /**
     * Constructor
     */
    protected Application()
    {
        super("TrafficSwing");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initMenuBar();
        super.getContentPane().add(Application.DEFAULT_PANEL());
        hsv = new HighScoreView();
        //debug();
        super.setPreferredSize(new Dimension(800, 600));
        pack();
    }

    protected void debug()
    {
        HighScoreRecord hsr = new HighScoreRecord(5, "Testlevel", "Testspeler");
        HighScores.getInstance().voegHighScoreToe(hsr);
    }

    protected void initMenuBar()
    {
        mainMenuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Bestand");
        mainMenuBar.add(fileMenu);

        JMenuItem startItem = new JMenuItem("Nieuw spel");
        fileMenu.add(startItem);
        startItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Simulatie van een simpele lvl voor debug redenen
                Level lvl = new Level();
                Auto auto1 = new Auto(2, 2,Kleur.Geel);
                auto1.setOrientatie(Orientatie.Horizontaal);
                lvl.voegVoertuigToe(auto1);
                lvl.setNaam("Testlevel");
                LevelView lvllw = new LevelView(lvl);
                setView(lvllw);
            }
        });
        fileMenu.add(new JPopupMenu.Separator());

        JMenuItem hoofdMenu = new JMenuItem("Hoofdmenu");
        fileMenu.add(hoofdMenu);
        hoofdMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setDefaultPanel();
            }
        });

        super.setJMenuBar(mainMenuBar);
    }

    protected static JPanel DEFAULT_PANEL()
    {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel menuPanel = new JPanel(new GridLayout(3, 0));

        JButton newGame = new JButton("Nieuw Spel");
        newGame.setFont(new Font("Arial", Font.BOLD, 36));
        newGame.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Application.getInstance(), "Dit is nog niet ingebouwd","Ontwikkeling",JOptionPane.WARNING_MESSAGE);
            }
        });
        menuPanel.add(newGame);

        JButton highScores = new JButton("High Scores");
        highScores.setFont(new Font("Arial", Font.BOLD, 36));
        highScores.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Application.getInstance().toonHighScores();
            }
        });
        menuPanel.add(highScores);

        panel.add(menuPanel,BorderLayout.CENTER);
        return panel;
    }

    protected static void createInstance()
    {
        if (_instance == null)
            _instance = new Application();
    }

    /**
     *
     * @return geeft de huidige level terug
     * @exception UnsupportedOperationException
     */
    public Level getHuidigeLevel() throws UnsupportedOperationException
    {
        if (super.getContentPane().getComponent(0) instanceof LevelView)
        {
            return ((LevelView)super.getContentPane().getComponent(0)).getLevel();
        }
        else
            throw new UnsupportedOperationException("Er draait nu geen level");
    }

    /**
     * De Application instance opvragen
     * @return de Application instance
     */
    public static Application getInstance()
    {
        if (_instance == null)
            createInstance();

        return _instance;
    }

    public void setView(View view)
    {
        Component cp = super.getContentPane();
        if (cp instanceof View)
        {

            View v = (View)cp;
            if (view.sluit())
            {
                super.setContentPane(view);
                view.updateUI();
                super.pack();
            } else
            {
                JOptionPane.showMessageDialog(this, "Deze optie is nu niet beschikbaar.","Fout!",JOptionPane.ERROR_MESSAGE);
            }
        } else if (cp instanceof JPanel)
        {
            super.setContentPane(view);
            view.updateUI();
            if (view instanceof LevelView)
                super.setPreferredSize(calculateSize(new Dimension(LevelView.BORD_BREEDTE, LevelView.BORD_HOOGTE)));
            
            pack();
        }
    }

    public void setDefaultPanel()
    {
        if (getContentPane() instanceof View)
        {
            View v = (View)getContentPane();
            if (v.sluit())
            {
                setContentPane(DEFAULT_PANEL());
            } else
                JOptionPane.showMessageDialog(this, "Kan hoofdmenu nu niet tonen.","Fout",JOptionPane.ERROR_MESSAGE);
        } else
            setContentPane(DEFAULT_PANEL());

        super.setPreferredSize(new Dimension(800, 600));
        pack();
    }

    /**
     * Toont het HighScoreView
     */
    public void toonHighScores()
    {
        if (!hsv.isVisible())
            hsv.setVisible(true);
    }

    private Dimension calculateSize(Dimension d)
    {
        super.setPreferredSize(d);
        int extrah = super.getContentPane().getSize().height - d.width;
        int extraw = super.getContentPane().getSize().width - d.width;
        d.height += extrah;
        d.width += extraw + 10;
        return d;
    }
}
