package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import sovelluslogiikka.Peli;
import sovelluslogiikka.Ruutu;
import sovelluslogiikka.Tyyppi;

/**
* RuutujenKuuntelija sisältää yksittäisiin graafisen käyttöliittymän ruutuihin
* kuuluvan toiminnallisuuden. Luokka vaihtaa ruudun kuvaketta ja käsittelee
* hiirenpainallukset.
*/

public class RuutujenKuuntelija implements MouseListener {
    
    private Ruutu ruutu;
    private JButton nappi;
    private Gui gui;
    private Peli peli;

    /**
    * Konstruktorille annetaan tuttuun tapaan tieto ruudusta ja napista, mutta
    * myös Gui- ja Peli -oliot, joihin kohdistetaan huomiota ruutuja päivitettäessä
    * klikkauksen jälkeen sekä kyselyt pelin tilasta
    *
    * @param    ruutu   Ruutu-olio, jota nappi vastaa graafisessa käyttöliittymässä
    * @param    nappi   Nappi, jota tämä RuutujenKuuntelija kuuntelee
    * @param    gui     käynnissä oleva Gui-olio
    * @param    peli    käynnissä oleva Peli-olio
    */
    
    public RuutujenKuuntelija(Ruutu ruutu, JButton nappi, Gui gui, Peli peli) {
        this.ruutu = ruutu;
        this.nappi = nappi;
        this.gui = gui;
        this.peli = peli;
    }
    
    /**
    * paivita-metodi muuttaa napin kuvaketta vastaamaan nykyistä tilannetta
    */
    
    public void paivita() {
        
        if(!ruutu.onkoAvattu()) {
            //Jos ruutu on avaamaton, ei näytetä kuvaketta
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
    
    /**
    * sulje-metodi asettaa napin pois käytöstä pelin päätyttyä
    */
    
    public void sulje() {
        nappi.setEnabled(false);
    }        

    
    /**
    * mouseClicked -metodi kokeilee ruutua lapiolla (vasen hiirenpainike) tai
    * räjäyttää ruudun (oikea hiirenpainike). Toiminnon jälkeen Gui päivitetään
    */
    
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
