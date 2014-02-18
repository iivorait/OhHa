
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;
import sovelluslogiikka.Peli;
import sovelluslogiikka.Ruutu;

/**
* Gui-luokka koostaa graafisen käyttöliittymän. Luokka sisältää toiminnallisuuden
* graafisen käyttöliittymän käynnistykseen ja päivitykseen
*/

public class Gui implements Runnable {
    private JFrame frame;
    private Peli peli;
    private ArrayList<RuutujenKuuntelija> kuuntelijat = new ArrayList<RuutujenKuuntelija>();
    private JLabel panoksia;

    /**
    * Konstruktorille annetaan aikaisemmin luotu Peli-olio
    *
    * @param    peli   käytetty Peli-olio
    */
    
    public Gui(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void run() {
        frame = new JFrame("Telamiinaharava");
        frame.setPreferredSize(new Dimension(400, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
    * luoKomponentit-metodi luo graafisen käyttöliittymän layoutin ja 
    * Peli-olion ruuduille kuuntelijat
    */
    
    private void luoKomponentit(Container container) {
        
        // Creates a menubar for a JFrame
        JMenuBar menuBar = new JMenuBar();
        
        // Add the menubar to the frame
        //setJMenuBar(menuBar);
        //container.add(menuBar);
        
        JPanel ylapaneeli = new JPanel(new GridLayout(2,1));
        this.panoksia = new JLabel("" + this.peli.getPanoksia());
        ylapaneeli.add(menuBar);
        ylapaneeli.add(panoksia);
        container.add(ylapaneeli,BorderLayout.NORTH);
        
        JPanel ruudukko = luoKentta(8);
        
        container.add(ruudukko);
        
        
                
        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("Valikko");
        menuBar.add(fileMenu);
        
        // Create and add simple menu item to one of the drop down menu
        JMenuItem newAction = new JMenuItem("Uusi peli");
        JMenuItem openAction = new JMenuItem("Asetukset");
        JMenuItem exitAction = new JMenuItem("Poistu");
        

        // Create a ButtonGroup and add both radio Button to it. Only one radio
        // button in a ButtonGroup can be selected at a time.
        ButtonGroup bg = new ButtonGroup();
 
        fileMenu.add(newAction);
        fileMenu.add(openAction);

        fileMenu.add(exitAction);

        // Add a listener to the New menu item. actionPerformed() method will
        // invoked, if user triggred this menu item
        newAction.addActionListener(new UusiPeliKuuntelija(peli, this));
        exitAction.addActionListener(new PoistuKuuntelija());

        
        
    }
    
    private JPanel luoKentta(int sivunPituus) {
        JPanel ruudukko = new JPanel(new GridLayout(sivunPituus, sivunPituus));

        ArrayList<Ruutu> ruutuoliot = this.peli.getRuudut();
        for (Ruutu ruutu : ruutuoliot) {
            JButton ruutunappi = new JButton();
            RuutujenKuuntelija kuuntelija = new RuutujenKuuntelija(ruutu, ruutunappi, this, peli);
            ruutunappi.addMouseListener(kuuntelija);
            ruudukko.add(ruutunappi);
            this.kuuntelijat.add(kuuntelija);
        }
        
        return ruudukko;
    }
    
    public void paivitaKentta() { //EI TOIMI
        //frame.getContentPane().add(luoKentta(8));
        //frame.setContentPane(null);
        frame.removeAll();
      frame.revalidate(); // try adding this
      //frame.repaint();
      luoKomponentit(frame.getContentPane());
      frame.pack();
    }

    public JFrame getFrame() {
        return frame;
    }
    
    /**
    * uudelleenpiirra-metodi päivittää graafisen käyttöliittymän klikkausten
    * jälkeen. Metodi tarkistaa Peliltä, onko se käynnissä ja tarvittaessa
    * lopettaa pelin.
    */
    
    public void uudelleenpiirra() {
        
        for (RuutujenKuuntelija kuuntelija : kuuntelijat) {
            kuuntelija.paivita();
        }
        
        if(!this.peli.kaynnissa()) {
            this.panoksia.setText("loppu");
            for (RuutujenKuuntelija kuuntelija : kuuntelijat) {
                kuuntelija.sulje();
            }
            return;
        }

        this.panoksia.setText("" + this.peli.getPanoksia());
    }
}
