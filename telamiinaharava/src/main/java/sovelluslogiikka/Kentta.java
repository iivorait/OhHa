
package sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

/**
* Kentta-luokka luo ja sisältää yksittäiset ruudut. 
*/

public class Kentta {
    private ArrayList<Ruutu> ruudut;
    private Random random;
    
    /**
    * Konstruktorille annetaan kentän sivun pituus ja Random-olio
    *
    * @param    sivunPituus   kentän yhden sivun pituus ruutuina
    * @param    random        Random-olio
    */
    
    public Kentta(int sivunPituus, Random random) {
        this.ruudut = new ArrayList<>();
        this.random = random;
        
        for (int i = 0; i < sivunPituus; i++) { //rivit
            for (int j = 0; j < sivunPituus; j++) { //sarakkeet
                ruudut.add(new Ruutu(j,i,this.arvoTyyppi()));
            }
        }
    }

    public ArrayList<Ruutu> getRuudut() {
        return ruudut;
    }
    
    /**
    * getRuutu palauttaa ruudun tietyistä koordinaateista
    * 
    * @param    x   x-koordinaatti
    * @param    y   y-koordinaatti
    * 
    * @return ruutu-olio kyseisissä koordinaateissa
    */
    
    public Ruutu getRuutu(int x, int y) {
        Ruutu loydettyRuutu = null;
        
        for (Ruutu ruutu : ruudut) {
            if(ruutu.getX()==x && ruutu.getY()==y) {
                loydettyRuutu = ruutu;
            }
        }
        return loydettyRuutu;
    }
 
    /**
    * arvoTyyppi arpoo ruudun tyypin, käytetään miinakentän luonnissa
    * 
    * @return Tyyppi
    */
    
    public Tyyppi arvoTyyppi() {
        int arpa = this.random.nextInt(100);
        
        if(arpa<10) { //0-9 = 10% mahdollisuus
            return Tyyppi.MIINA;
        } else if(arpa<20) { //10-19 = 10% mahdollisuus
            return Tyyppi.FEIKKI;
        }
        
        return Tyyppi.TYHJA;
    }
}
