
package sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

/**
* Peli-luokka koostaa pelin sovelluslogiikan. Peli tietää, paljonko panoksia on 
* jäljellä ja jatkuuko peli. Peli luo kentän.
*/

public class Peli {
    
    private Kentta kentta;
    private Random random;
    private int panoksia;
    private boolean voitto;
    
    
    /**
    * Konstruktorille annetaan kentän koko ja panosten määrä, joita muuttamalla
    * pelin vaikeustasoa voidaan säädellä. Testausta varten voidaan antaa
    * Random-olio.
    *
    * @param    sivunPituus   Pelikentän yhden sivun pituus ruuduissa
    * @param    panoksia    Panosten lukumäärä
    * @param    random  Random-olio (valinnainen)
    */
    
    public Peli (int sivunPituus, int panoksia, Random random) {
        this.random = random;
        this.kentta = new Kentta(sivunPituus,this.random);
        this.panoksia = panoksia;
    }
    
    public Peli (int sivunPituus, int panoksia) {
        this(sivunPituus, panoksia, new Random());
    }

    public int getPanoksia() {
        return panoksia;
    }
    
    public ArrayList<Ruutu> getRuudut() {
        return this.kentta.getRuudut();
    }

    public boolean isVoitto() {
        return voitto;
    }
    
    /**
    * Metodi räjäyttää panoksen annetuissa koordinaateissa. Aluksi panoksia
    * vähennetään. Metodi avaa koordinaattien läheisyydessä olevat ruudut ja
    * räjäyttää niistä sisimmät. Aina ei räjähdä samaa määrää ruutuja.
    *
    * @param    x   Leveyskoordinaatti
    * @param    y   Korkeuskoordinaatti
    */
    
    public void rajayta(int x, int y) {
        this.panoksia--;
        
        for (Ruutu ruutu : this.kentta.getRuudut()) {
            if(itseisarvo(ruutu.getX()-x) <= 3 && itseisarvo(ruutu.getY()-y) <= 3) {
                ruutu.avaa();
            }
            if(itseisarvo(ruutu.getX()-x) <= 1 && itseisarvo(ruutu.getY()-y) <= 1) {
                ruutu.rajayta();
            } else if(itseisarvo(ruutu.getX()-x) <= 2 && itseisarvo(ruutu.getY()-y) <= 2) {
                if(this.random.nextInt(100)<25) { //25% mahdollisuus läheisen ruudun räjähtämiseen
                    ruutu.rajayta();
                }
            }
        }
    }
    
    /**
    * Metodi laskee luvun itseisarvon. Käytetään löytämään räjähteen
    * koordinaattien läheiset ruudut positiivisessa ja negatiivisessa suunnassa
    *
    * @param    luku    Alkuperäinen luku
    * 
    * @return   luku:n itseisarvo
    */
    public int itseisarvo(int luku) {
        if(luku > 0) {
            return luku;
        }
        return -luku;
    }
    
    /**
    * Metodi avaa ruudun annetuissa koordinaateissa. Jos ruudussa on miina,
    * peli loppuu häviöön
    *
    * @param    x   Leveyskoordinaatti
    * @param    y   Korkeuskoordinaatti
    */
    
    public void kokeileLapiolla(int x, int y) {
        if(this.kentta.getRuutu(x, y).kokeileLapiolla()){ //jos lapio osuu miinaan...
            this.panoksia = 0; //... panokset (ja pelaaja) räjähtää miinan mukana
        }
    }
    
    /**
    * Metodi kertoo, onko peli käynnissä (avaamattomia ruutuja, räjähtämättömiä
    * miinoja tai panoksia jäljellä). Asettaa voitto-muuttujaan tuloksen
    * 
    * @return true jos peli on käynnissä ja false jos ei ole
    */
    
    public boolean kaynnissa() {    
        if(this.panoksia==0) { //panokset loppu
            this.voitto = false;
            return false;
        }
        
        for (Ruutu ruutu : this.kentta.getRuudut()) { 
            if(!ruutu.onkoAvattu()) { //avaamattomia ruutuja löytyy vielä
                return true;
            }
            if(ruutu.getTyyppi()==Tyyppi.MIINA) { //miinoja löytyy vielä
                return true;
            }
        }
        this.voitto = true;
        return false;
    }
    
    public void nollaaPeli () {
        this.kentta = new Kentta(8,this.random);
        this.panoksia = 10;
    }
}
