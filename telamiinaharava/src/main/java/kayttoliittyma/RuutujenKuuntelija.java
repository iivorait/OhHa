/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import sovelluslogiikka.Peli;
import sovelluslogiikka.Ruutu;
import sovelluslogiikka.Tyyppi;

/**
 *
 * @author iivo
 */
public class RuutujenKuuntelija implements ActionListener {
    
    private Ruutu ruutu;
    private JButton nappi;
    private Gui gui;
    private Peli peli;

    public RuutujenKuuntelija(Ruutu ruutu, JButton nappi, Gui gui, Peli peli) {
        this.ruutu = ruutu;
        this.nappi = nappi;
        this.gui = gui;
        this.peli = peli;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        peli.rajayta(ruutu.getX(), ruutu.getY());
        this.gui.uudelleenpiirra();
    }
    
    public void paivita() {
        
        if(!ruutu.onkoAvattu()) {
            nappi.setText("");
        } else if(this.ruutu.getTyyppi()==Tyyppi.FEIKKI) {
            nappi.setText("F");
        } else if(this.ruutu.getTyyppi()==Tyyppi.MIINA) {
            nappi.setText("M");
        } else if(this.ruutu.getTyyppi()==Tyyppi.RAJAHTANYT) {
            nappi.setText("*");
        } else {
            nappi.setText("-");
        }
        
    }
}
