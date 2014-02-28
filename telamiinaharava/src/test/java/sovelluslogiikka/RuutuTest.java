/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

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
public class RuutuTest {
    
    public RuutuTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testataanGetterit() {
        Ruutu ruutu = new Ruutu(1,4,Tyyppi.MIINA);
        assertEquals(1,ruutu.getX());
        assertEquals(4,ruutu.getY());
        assertEquals(Tyyppi.MIINA,ruutu.getTyyppi());
    }
    
    @Test
    public void alussaAvaamaton() {
        Ruutu ruutu = new Ruutu(0,0,Tyyppi.MIINA);
        assertFalse(ruutu.onkoAvattu());
    }
    
    @Test
    public void avaaminenToimii() {
        Ruutu ruutu = new Ruutu(0,0,Tyyppi.MIINA);
        ruutu.avaa();
        assertTrue(ruutu.onkoAvattu());
    }
    
    @Test
    public void lapiollaKokeileminenToimiiIlmanMiinaa() {
        Ruutu ruutu = new Ruutu(0,0,Tyyppi.TYHJA);
        assertFalse(ruutu.kokeileLapiolla());
        assertTrue(ruutu.onkoAvattu());
    }
    
    @Test
    public void lapiollaKokeileminenToimiiMiinalla() {
        Ruutu ruutu = new Ruutu(0,0,Tyyppi.MIINA);
        assertTrue(ruutu.kokeileLapiolla());
        assertTrue(ruutu.onkoAvattu());
    }
    
    @Test
    public void lapiollaKokeileminenToimiiFeikilla() {
        Ruutu ruutu = new Ruutu(0,0,Tyyppi.FEIKKI);
        assertFalse(ruutu.kokeileLapiolla());
        assertTrue(ruutu.onkoAvattu());
        assertEquals(Tyyppi.TODETTUFEIKKI, ruutu.getTyyppi());
    }
    
    @Test
    public void rajayttaminenToimii() {
        Ruutu ruutu = new Ruutu(0,0,Tyyppi.MIINA);
        ruutu.rajayta();
        assertEquals(Tyyppi.RAJAHTANYT,ruutu.getTyyppi());
        assertTrue(ruutu.onkoAvattu());
    }
}
