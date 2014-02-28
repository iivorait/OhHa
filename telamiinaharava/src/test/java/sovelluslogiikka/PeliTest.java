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
    
    @Test
    public void panoksiaAlussaToimii() {
        Peli peli = new Peli(8, 10, new Random(1234567));
        peli.setPanoksiaAlussa(20);
        assertEquals(20, peli.getPanoksiaAlussa());
    }
    
    @Test
    public void sivunPituusToimii() {
        Peli peli = new Peli(20, 10, new Random(1234567));
        assertEquals(20, peli.getSivunPituus());
    }
    
    @Test
    public void rajahdysaineToimii() {
        Peli peli = new Peli(8, 10, new Random(1234567));
        peli.setRajahdysainetta(20);
        assertEquals(20, peli.getRajahdysainetta());
    }
    
    @Test
    public void miinojaToimii() {
        Peli peli = new Peli(8, 10, new Random(1234567));
        peli.setMiinoja(20);
        assertEquals(20, peli.getMiinoja());
    }
    
    @Test
    public void panoksetEiMeneNegatiiviseksi() {
        Peli peli = new Peli(8, 2, new Random(1234567));
        peli.rajayta(1, 1);
        peli.rajayta(2, 1);
        peli.rajayta(3, 1);
        peli.rajayta(4, 1);
        assertEquals(0, peli.getPanoksia());
    }
    
    @Test
    public void itseisarvoToimii() {
        Peli peli = new Peli(8, 2, new Random(1234567));
        assertEquals(0, peli.itseisarvo(0));
        assertEquals(1, peli.itseisarvo(1));
        assertEquals(1, peli.itseisarvo(-1));
    }
    
    @Test
    public void rajaytysToimii() {
        Peli peli = new Peli(8, 2, new Random(1234567));
        peli.setRajahdysainetta(1); //avaa vain yhden ruudun
        //peli.nollaaPeli();
        
        peli.rajayta(0, 0);
        assertEquals(Tyyppi.RAJAHTANYT, peli.getKentta().getRuutu(0, 0).getTyyppi());
        assertEquals(Tyyppi.MIINA, peli.getKentta().getRuutu(0, 1).getTyyppi());
        assertFalse(peli.getKentta().getRuutu(0, 1).onkoAvattu());
        
        peli = new Peli(8, 2, new Random(1234567));
        peli.setRajahdysainetta(100); //avaa monta ruutua
        //peli.nollaaPeli();
        
        peli.rajayta(0, 0);
        assertEquals(Tyyppi.RAJAHTANYT, peli.getKentta().getRuutu(0, 0).getTyyppi());
        assertEquals(Tyyppi.RAJAHTANYT, peli.getKentta().getRuutu(0, 1).getTyyppi());
        assertEquals(Tyyppi.TYHJA, peli.getKentta().getRuutu(7, 7).getTyyppi());
        assertEquals(Tyyppi.RAJAHTANYT, peli.getKentta().getRuutu(2, 0).getTyyppi()); //randomilla räjähti
        assertEquals(Tyyppi.MIINA, peli.getKentta().getRuutu(0, 2).getTyyppi()); //randomilla ei räjähtänyt
        assertTrue(peli.getKentta().getRuutu(0, 1).onkoAvattu());
        assertFalse(peli.getKentta().getRuutu(0, 5).onkoAvattu());
    }
    
    @Test
    public void lapiollaKokeiluToimii() {
        Peli peli = new Peli(8, 2, new Random(1234567));
        peli.kokeileLapiolla(0, 0);
        assertEquals(2, peli.getPanoksia());
        peli.kokeileLapiolla(0, 1);
        assertEquals(-1, peli.getPanoksia());
    }
    
    @Test
    public void kaynnissaToimii() {
        Peli peli = new Peli(8, 2, new Random(1234567));
        assertTrue(peli.kaynnissa());
        peli.kokeileLapiolla(0, 1);
        assertFalse(peli.kaynnissa());
    }
    
    @Test
    public void kaynnissaToimiiKunKaikkiRuudutAvattu() {
        Peli peli = new Peli(8, 10, new Random(1234567));
        peli.rajayta(2,4);
        peli.rajayta(5,4);
        peli.rajayta(5,1);
        peli.rajayta(3,1);
        assertTrue(peli.kaynnissa());
        peli.rajayta(7,0);
        peli.rajayta(0,0);
        assertFalse(peli.kaynnissa());
    }
    
    @Test
    public void viimeisellaMiinallaVoittoonToimii() {
        Peli peli = new Peli(8, 10, new Random(1234567));
        peli.rajayta(5,5);
        peli.rajayta(0,4);
        peli.rajayta(0,4);
        peli.rajayta(3,1);
        peli.rajayta(6,1);
        peli.kokeileLapiolla(0, 1);
        assertFalse(peli.kaynnissa());
        assertTrue(peli.isVoitto());
    }
    
    @Test
    public void vaaratilanneToimii() {
        Peli peli = new Peli(8, 2, new Random(1234567));
        assertEquals(245,peli.vaaratilanne(7, 7));
        assertEquals(245-3*10,peli.vaaratilanne(0, 0));
    }
    
    @Test
    public void vaaratilanneVarmassaKuolemassaToimii() {
        Peli peli = new Peli(8, 2, new Random(1234567));
        peli.setMiinoja(50);
        peli.nollaaPeli();
        assertEquals(0,peli.vaaratilanne(4, 4));
    }
}
