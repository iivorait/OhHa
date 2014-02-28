
package kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import kayttoliittyma.Asetusikkuna;
import kayttoliittyma.Gui;
import sovelluslogiikka.Peli;

/**
 * AsetuksetKuuntelija -luokka avaa asetukset-ikkunan valikkonappia painettaessa
 */

public class AsetuksetKuuntelija implements ActionListener {
    
    private Peli peli;
    private Gui gui;

    public AsetuksetKuuntelija(Peli peli, Gui gui) {
        this.peli = peli;
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Asetusikkuna asetusikkuna = new Asetusikkuna(this.peli, this.gui);
        SwingUtilities.invokeLater(asetusikkuna);
    }
}
