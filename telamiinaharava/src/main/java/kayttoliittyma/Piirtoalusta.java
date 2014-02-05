/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import sovelluslogiikka.Peli;
import sovelluslogiikka.Ruutu;

/**
 *
 * @author iivo
 */
public class Piirtoalusta extends JPanel {

    private Peli peli;
    
    public Piirtoalusta(Peli peli) {
        super.setBackground(Color.WHITE); //poista?
        this.peli = peli;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.piirra(graphics);
    }
    
    public void piirra(Graphics graphics) {
        ArrayList<Ruutu> ruudut = this.peli.getRuudut();
        for (Ruutu ruutu : ruudut) {
            ruutu.piirra(graphics, 30);
        }
    }
}
