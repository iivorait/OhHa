
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
    private JLabel tilannerivi;
    private JPanel ruudukko;

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
        
        int leveys = this.peli.getSivunPituus() * 45;
        int korkeus = leveys + 20;
        
        frame.setPreferredSize(new Dimension(leveys, korkeus));

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
        
        JMenuBar valikkoBar = new JMenuBar();
   
        JPanel ylapaneeli = new JPanel(new GridLayout(2,1));
        this.tilannerivi = new JLabel("Peli alkaa! Panoksia käytettävissä " + this.peli.getPanoksia() + " kpl");
        ylapaneeli.add(valikkoBar);
        ylapaneeli.add(tilannerivi);
        container.add(ylapaneeli,BorderLayout.NORTH);
        
        JPanel ruudukko = luoKentta();
        this.ruudukko = ruudukko;
        container.add(ruudukko);
        
        JMenu valikkoSisalto = new JMenu("Valikko");
        valikkoBar.add(valikkoSisalto);
        
        JMenuItem uusiNappi = new JMenuItem("Uusi peli");
        JMenuItem asetuksetNappi = new JMenuItem("Asetukset");
        JMenuItem poistuNappi = new JMenuItem("Taakse poistu");

 
        valikkoSisalto.add(uusiNappi);
        valikkoSisalto.add(asetuksetNappi);
        valikkoSisalto.add(poistuNappi);

        uusiNappi.addActionListener(new UusiPeliKuuntelija(peli, this));
        asetuksetNappi.addActionListener(new AsetuksetKuuntelija(this.peli));
        poistuNappi.addActionListener(new PoistuKuuntelija());

    }
    
    /**
    * luoKentta -metodi luo kentän graafisen ilmentymän
    * 
    * @return JPanel sisältäen GridLayoutin ruutuineen ja kuuntelijoineen
    */
    
    private JPanel luoKentta() {
        JPanel ruudukko = new JPanel(new GridLayout(this.peli.getSivunPituus(), this.peli.getSivunPituus()));

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
    
    /**
    * paivitaKentta -metodi muuttaa ruutujen kuuntelijoille uudet ruutu-oliot,
    * kun kenttä on muuttunut uuden pelin aloituksen yhteydessä
    */
    
    public void paivitaKentta() {
        
        ArrayList<Ruutu> ruudut = peli.getRuudut();
        
        Iterator<Ruutu> iteraattori = ruudut.iterator();
        
        for (RuutujenKuuntelija kuuntelija : kuuntelijat) {
            kuuntelija.muutaRuutua(iteraattori.next());
        }
        
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
            if(this.peli.isVoitto()) {
                this.tilannerivi.setText("Voitit!");
            } else {
                this.tilannerivi.setText("Hävisit!");
            }
            
            for (RuutujenKuuntelija kuuntelija : kuuntelijat) {
                kuuntelija.sulje();
            }
            return;
        }

        this.tilannerivi.setText("Panoksia jäljellä " + this.peli.getPanoksia() + " kpl");
    }
}
