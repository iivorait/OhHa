
package kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittyma.Gui;
import sovelluslogiikka.Peli;

/**
 * UusiPeliKuuntelija -luokka luo uuden pelin vanhoilla asetuksilla
 * 
 */
public class UusiPeliKuuntelija implements ActionListener {

    private Peli peli;
    private Gui gui;

    
    public UusiPeliKuuntelija (Peli peli, Gui gui) {
        this.peli = peli;
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.peli.nollaaPeli();
        this.gui.paivitaKentta();
        this.gui.uudelleenpiirra();
    }
    
}
