
package kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PoistuKuuntelija -luokka sulkee kaikki ikkunat
 * 
 */
public class PoistuKuuntelija implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    
}
