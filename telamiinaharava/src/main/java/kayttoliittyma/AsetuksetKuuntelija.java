
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import sovelluslogiikka.Peli;

/**
 * AsetuksetKuuntelija -luokka avaa asetukset-ikkunan valikkonappia painettaessa
 */

public class AsetuksetKuuntelija implements ActionListener {
    
    private Peli peli;

    public AsetuksetKuuntelija(Peli peli) {
        this.peli = peli;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Asetusikkuna asetusikkuna = new Asetusikkuna(this.peli);
        SwingUtilities.invokeLater(asetusikkuna);
    }
}
