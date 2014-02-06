/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.ArrayList;
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
public class PeliTest {
    
    public PeliTest() {
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
    public void konstruktoriRandomillaToimii() {
        Peli peli = new Peli(8, 10, new Random(1234567));
        ArrayList<Ruutu> ruudut = peli.getRuudut();
        assertTrue(ruudut.get(0).getTyyppi()==Tyyppi.TYHJA);
    }
    
    @Test
    public void konstruktoriIlmanRandomiaToimii() {
        Peli peli = new Peli(8, 10);
        ArrayList<Ruutu> ruudut = peli.getRuudut();
        assertTrue(ruudut.size()==(8*8));
    }
    
    @Test
    public void panoksienHakuToimii() {
        Peli peli = new Peli(8, 10);
        assertEquals(10, peli.getPanoksia());
        peli.rajayta(1, 1);
        assertEquals(9, peli.getPanoksia());
    }
    
    @Test
    public void voittoToimii() {
        Peli peli = new Peli(8, 10, new Random(1234567));
        peli.rajayta(1, 1);
        peli.rajayta(4, 1);
        peli.rajayta(6, 1);
        peli.rajayta(1, 4);
        peli.rajayta(4, 4);
        peli.rajayta(6, 4);
        peli.rajayta(1, 6);
        peli.rajayta(4, 6);
        peli.rajayta(6, 6);
        peli.kaynnissa();
        assertTrue(peli.isVoitto());
    }

}
