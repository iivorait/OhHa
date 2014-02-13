
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        
        JPanel ylapaneeli = new JPanel(new GridLayout(1,1));
        this.panoksia = new JLabel("" + this.peli.getPanoksia());
        ylapaneeli.add(panoksia);
        container.add(ylapaneeli,BorderLayout.NORTH);
        
        JPanel ruudukko = new JPanel(new GridLayout(8,8));

        ArrayList<Ruutu> ruutuoliot = this.peli.getRuudut();
        for (Ruutu ruutu : ruutuoliot) {
            JButton ruutunappi = new JButton();
            RuutujenKuuntelija kuuntelija = new RuutujenKuuntelija(ruutu, ruutunappi, this, peli);
            ruutunappi.addMouseListener(kuuntelija);
            ruudukko.add(ruutunappi);
            this.kuuntelijat.add(kuuntelija);
        }
        container.add(ruudukko);
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
