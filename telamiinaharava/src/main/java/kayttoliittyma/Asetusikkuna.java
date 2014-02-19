
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import sovelluslogiikka.Peli;

/**
* Asetusikkuna-luokka sisältää toiminnallisuuden asetukset-ikkunan luontiin
*/

public class Asetusikkuna implements Runnable {
    private JFrame frame;
    private Peli peli;

    /**
    * Konstruktorille annetaan aikaisemmin luotu Peli-olio asetusten lukua
    * ja kirjoitusta varten
    *
    * @param    peli   käytetty Peli-olio
    */
    
    public Asetusikkuna(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void run() {
        frame = new JFrame("Asetukset");
        
        frame.setPreferredSize(new Dimension(300, 200));

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
        
        JLabel sivunPituusTeksti = new JLabel("<html>Kentän sivun pituus: <br>(oletus 13)</html>");
        JTextField sivunPituusKentta = new JTextField("" + this.peli.getSivunPituus());
        JLabel panosTeksti = new JLabel("<html>Panosten määrä: <br>(oletus 10 kpl)</html>");
        JTextField panosKentta = new JTextField("" + this.peli.getPanoksiaAlussa());
        JLabel rajahdysaineTeksti = new JLabel("<html>TNT-rouheen määrä: <br>(oletus 100g)</html>");
        JTextField rajahdysaineKentta = new JTextField("" + this.peli.getRajahdysainetta());
        JLabel miinaTeksti = new JLabel("<html>Miinoja: <br>(oletus 10%)</html>");
        JTextField miinaKentta = new JTextField("" + this.peli.getMiinoja());
        JButton tallennaNappi = new JButton("Tallenna");
        tallennaNappi.addActionListener(new AsetusTallennusKuuntelija(this.peli, this.frame,
                sivunPituusKentta, panosKentta, rajahdysaineKentta, miinaKentta));
        
        container.add(sivunPituusTeksti);
        container.add(sivunPituusKentta);
        container.add(panosTeksti);
        container.add(panosKentta);
        container.add(rajahdysaineTeksti);
        container.add(rajahdysaineKentta);
        container.add(miinaTeksti);
        container.add(miinaKentta);
        container.add(tallennaNappi);
    }


    public JFrame getFrame() {
        return frame;
    }
    
}
