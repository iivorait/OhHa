/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import sovelluslogiikka.Peli;
import sovelluslogiikka.Ruutu;
import sovelluslogiikka.Tyyppi;

/**
 *
 * @author iivo
 */
public class RuutujenKuuntelija implements MouseListener {
    
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
    
    public void paivita() {
        
        if(!ruutu.onkoAvattu()) {
            
        } else if(this.ruutu.getTyyppi()==Tyyppi.FEIKKI && this.peli.kaynnissa() ||
                this.ruutu.getTyyppi()==Tyyppi.MIINA && this.peli.kaynnissa()) {
            this.nappi.setIcon(new javax.swing.ImageIcon(getClass().getResource("kumpare.png")));
        } else if(this.ruutu.getTyyppi()==Tyyppi.MIINA) { //peli päättynyt          
            this.nappi.setIcon(new javax.swing.ImageIcon(getClass().getResource("miina.png")));
        } else if(this.ruutu.getTyyppi()==Tyyppi.TODETTUFEIKKI || this.ruutu.getTyyppi()==Tyyppi.FEIKKI) {          
            this.nappi.setIcon(new javax.swing.ImageIcon(getClass().getResource("kanto.png")));
        } else if(this.ruutu.getTyyppi()==Tyyppi.RAJAHTANYT) {
            this.nappi.setIcon(new javax.swing.ImageIcon(getClass().getResource("rajahtanyt.png")));
        } else {
            this.nappi.setIcon(new javax.swing.ImageIcon(getClass().getResource("tyhja.png")));
        }
        
    }
    
    public void sulje() {
        nappi.setEnabled(false);
    }        

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) { //oikea hiirenpainike
            peli.rajayta(ruutu.getX(), ruutu.getY());
        } else {
            peli.kokeileLapiolla(ruutu.getX(), ruutu.getY());
        }
        
        this.gui.uudelleenpiirra();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
