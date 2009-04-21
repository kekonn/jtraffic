
package jtraffic;

import RushHour.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * The main class
 * @author bloodsplatter
 * @version Exodus
 */
public class Main {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // initialisatie
            LevelManager.Initialize();
            HighScores.Initialize();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            System.err.println("Kritieke fout.");
            System.exit(-2);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            System.err.println("Kritieke fout.");
            System.exit(-1);
        }

        MainMenu mainMenu = new MainMenu();
            while (true) {
                mainMenu.toon();
            }
    }

    /**
     * MainMenu klasse
     */
    private static class MainMenu extends Menu
    {
        public MainMenu()
        {
            super("Hoofdmenu");

            // start
            this.voegItemToe(new MenuItem(this,"Start spel", false) {

                @Override
                public void doAction() {
                    LevelSelect ls = new LevelSelect();
                    ls.toon();
                }
            });

            //
            this.voegItemToe(new MenuItem(this, "Toon highscores", false) {

                @Override
                public void doAction() {
                    System.out.println("Highscores\n");
                    HighScoreRecord[] hsrs = HighScores.toArray();
                    for (HighScoreRecord hsr : hsrs) {
                        System.out.println(hsr.toString());
                    }
                }
            });

            // Afsluiten
            this.voegItemToe(new MenuItem(this,"Afsluiten", true) {

                @Override
                public void doAction() {
                    System.exit(0);
                }
            });
        }
    }

    /**
     * Het level selectie menu
     */
    private static class LevelSelect extends Menu
    {

        public LevelSelect() {
            super("Kies level");

            Level[] levels = LevelManager.toArray();

            for (final Level level : levels) {
                this.voegItemToe(new MenuItem(this, level.getNaam(), false) {

                    @Override
                    public void doAction() {
                        LevelController lc = new LevelController(level);
                        lc.gebruikersInteractie();
                    }
                });
            }

            this.voegItemToe(new MenuItem(this, "Terug", false) {

                @Override
                public void doAction() {
                    return;
                }
            });
        }

    }
}
