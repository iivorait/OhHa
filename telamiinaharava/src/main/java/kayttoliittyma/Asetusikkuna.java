
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import kuuntelijat.AsetusTallennusKuuntelija;
import sovelluslogiikka.Peli;

/**
* Asetusikkuna-luokka sisältää toiminnallisuuden asetukset-ikkunan luontiin
*/

public class Asetusikkuna implements Runnable {
    private JFrame frame;
    private Peli peli;
    private Gui gui;

    /**
    * Konstruktorille annetaan aikaisemmin luotu Peli-olio asetusten lukua
    * ja kirjoitusta varten
    *
    * @param    peli   käytetty Peli-olio
    */
    
    public Asetusikkuna(Peli peli, Gui gui) {
        this.peli = peli;
        this.gui = gui;
    }

    @Override
    public void run() {
        frame = new JFrame("Asetukset");
        
        frame.setPreferredSize(new Dimension(450, 250));

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
    * luoKomponentit-metodi luo ikkunan graafisen ulkoasun
    */
    
    private void luoKomponentit(Container container) {
        
        GridLayout layout = new GridLayout(5,2);
        container.setLayout(layout);
        
        JLabel sivunPituusTeksti = new JLabel("<html>Kentän sivun pituus: <br>(oletus 13 ruutua)</html>");
        JTextField sivunPituusKentta = new JTextField("" + this.peli.getSivunPituus());
        JLabel panosTeksti = new JLabel("<html>Panosten määrä: <br>(oletus 10 kpl)</html>");
        JTextField panosKentta = new JTextField("" + this.peli.getPanoksiaAlussa());
        JLabel rajahdysaineTeksti = new JLabel("<html>TNT-rouheen määrä: <br>(oletus 100g)</html>");
        JTextField rajahdysaineKentta = new JTextField("" + this.peli.getRajahdysainetta());
        JLabel miinaTeksti = new JLabel("<html>Miinoja: <br>(oletus 10%)</html>");
        JTextField miinaKentta = new JTextField("" + this.peli.getMiinoja());
        JButton tallennaNappi = new JButton("Tallenna ja aloita uusi peli");
        tallennaNappi.addActionListener(new AsetusTallennusKuuntelija(this.frame, this.gui,
                sivunPituusKentta, panosKentta, rajahdysaineKentta, miinaKentta));
        JLabel ohjeTeksti = new JLabel("Älä kirjoita yksikköä");
        
        container.add(sivunPituusTeksti);
        container.add(sivunPituusKentta);
        container.add(panosTeksti);
        container.add(panosKentta);
        container.add(rajahdysaineTeksti);
        container.add(rajahdysaineKentta);
        container.add(miinaTeksti);
        container.add(miinaKentta);
        container.add(tallennaNappi);
        container.add(ohjeTeksti);
    }


    public JFrame getFrame() {
        return frame;
    }
    
}
