/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sovelluslogiikka.Peli;

/**
 *
 * @author iivo
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
