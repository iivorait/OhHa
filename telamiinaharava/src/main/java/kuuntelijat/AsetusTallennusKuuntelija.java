package kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import kayttoliittyma.Gui;
import sovelluslogiikka.Peli;

/**
 * AsetusTallennusKuuntelija -luokka asettaa talletetut asetukset voimaan
 * 
 */
public class AsetusTallennusKuuntelija implements ActionListener {

    private JFrame frame;
    private Gui gui;
    private JTextField sivunPituusKentta;
    private JTextField panosKentta;
    private JTextField rajahdysaineKentta;
    private JTextField miinaKentta;
    
    public AsetusTallennusKuuntelija(JFrame frame, Gui gui,
            JTextField sivunPituusKentta, JTextField panosKentta, JTextField rajahdysaineKentta, JTextField miinaKentta) {
        this.frame = frame;
        this.gui = gui;
        this.sivunPituusKentta = sivunPituusKentta;
        this.panosKentta = panosKentta;
        this.rajahdysaineKentta = rajahdysaineKentta;
        this.miinaKentta = miinaKentta;
    }

    /**
    * Kun tallenna-painiketta painetaan, tehdään uusi peli talletetuilla
    * asetuksilla ja suljetaan vanha peli
    */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            Peli uusiPeli = new Peli(Integer.parseInt(this.sivunPituusKentta.getText()), 10);
            uusiPeli.setMiinoja(Integer.parseInt(this.miinaKentta.getText()));
            uusiPeli.setPanoksiaAlussa(Integer.parseInt(this.panosKentta.getText()));
            uusiPeli.setRajahdysainetta(Integer.parseInt(this.rajahdysaineKentta.getText()));
            uusiPeli.nollaaPeli();
            Gui kayttoliittyma = new Gui(uusiPeli);
            SwingUtilities.invokeLater(kayttoliittyma);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Asetuskentissä on virheellisiä arvoja!");
            return;
        }

        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); //sulje asetusikkuna
        this.gui.getFrame().setVisible(false); //sulje edellinen peli-ikkuna
        
    }
    
}
