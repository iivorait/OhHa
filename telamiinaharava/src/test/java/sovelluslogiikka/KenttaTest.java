/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iivo
 */
public class KenttaTest {
    
    public KenttaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // new Kentta(8, new Random(1234567))
    // tulos:
//    -------F
//    M-------
//    MF-M--F-
//    ----FF--
//    --------
//    FM--F---
//    --------
//    --------
    
    @Test
    public void ruutujaOikeaMaara() {
        Kentta kentta = new Kentta(8, new Random());
        ArrayList ruudut = kentta.getRuudut();
        assertTrue(ruudut.size()==(8*8));
    }
    
    @Test
    public void satunnaislukugeneraattoriToimii() {
        HashMap<Tyyppi, Integer> esinkerrat = new HashMap<Tyyppi, Integer>();
        esinkerrat.put(Tyyppi.FEIKKI, 0);
        esinkerrat.put(Tyyppi.MIINA, 0);
        esinkerrat.put(Tyyppi.TYHJA, 0);
        
        Kentta kentta = new Kentta(8, new Random(1234567));
        ArrayList<Ruutu> ruudut = kentta.getRuudut();
        for (Ruutu ruutu : ruudut) {
            esinkerrat.put(ruutu.getTyyppi(), esinkerrat.get(ruutu.getTyyppi()) + 1);
        }
        assertTrue(esinkerrat.get(Tyyppi.FEIKKI)==7);
        assertTrue(esinkerrat.get(Tyyppi.MIINA)==4);
        assertTrue(esinkerrat.get(Tyyppi.TYHJA)==53);
    }
    
    @Test
    public void getRuutuToimii() {
        Kentta kentta = new Kentta(8, new Random(1234567));
        Ruutu ruutu = kentta.getRuutu(0, 1);
        assertTrue(ruutu.getTyyppi()==Tyyppi.MIINA);
        ruutu = kentta.getRuutu(7, 0);
        assertTrue(ruutu.getTyyppi()==Tyyppi.FEIKKI);
        ruutu = kentta.getRuutu(0, 0);
        assertTrue(ruutu.getTyyppi()==Tyyppi.TYHJA);
    }
    
 
}
