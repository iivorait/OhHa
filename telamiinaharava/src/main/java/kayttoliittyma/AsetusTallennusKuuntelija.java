/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import sovelluslogiikka.Peli;

/**
 *
 * @author iivo
 */
public class AsetusTallennusKuuntelija implements ActionListener {

    private Peli peli;
    private Gui gui;
    private JFrame frame;
    private JTextField sivunPituusKentta;
    private JTextField panosKentta;
    private JTextField rajahdysaineKentta;
    private JTextField miinaKentta;
    
    AsetusTallennusKuuntelija(Peli peli, Gui gui, JFrame frame,
            JTextField sivunPituusKentta, JTextField panosKentta, JTextField rajahdysaineKentta, JTextField miinaKentta) {
        this.peli = peli;
        this.gui = gui;
        this.frame = frame;
        this.sivunPituusKentta = sivunPituusKentta;
        this.panosKentta = panosKentta;
        this.rajahdysaineKentta = rajahdysaineKentta;
        this.miinaKentta = miinaKentta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(this.peli.getSivunPituus() != Integer.parseInt(this.sivunPituusKentta.getText())) {
//            this.peli.setSivunPituus(Integer.parseInt(this.sivunPituusKentta.getText()));
//            this.peli.nollaaPeli();
//            this.gui.paivitaKentta();
//            this.gui.uudelleenpiirra();
//        }
        this.peli.setMiinoja(Integer.parseInt(this.miinaKentta.getText()));
        this.peli.setPanoksiaAlussa(Integer.parseInt(this.panosKentta.getText()));
        this.peli.setRajahdysainetta(Integer.parseInt(this.rajahdysaineKentta.getText()));
        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    
}
