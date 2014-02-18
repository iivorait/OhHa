
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
    private int panoksiaAlussa;
    private boolean voitto;
    private int sivunPituus;
    private int rajahdysainetta;
    private int miinoja;
    
    
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
        this.sivunPituus = sivunPituus;
        this.miinoja = 10;
        this.kentta = new Kentta(sivunPituus,this.random, this.miinoja);
        this.panoksia = panoksia;
        this.panoksiaAlussa = panoksia;
        this.rajahdysainetta = 100;
        
    }
    
    public Peli (int sivunPituus, int panoksia) {
        this(sivunPituus, panoksia, new Random());
    }

    public int getPanoksia() {
        return panoksia;
    }

    public void setPanoksiaAlussa(int panoksiaAlussa) {
        this.panoksiaAlussa = panoksiaAlussa;
    }

    public int getPanoksiaAlussa() {
        return panoksiaAlussa;
    }

    public int getSivunPituus() {
        return sivunPituus;
    }

    public void setSivunPituus(int sivunPituus) {
        this.sivunPituus = sivunPituus;
    }

    public int getRajahdysainetta() {
        return rajahdysainetta;
    }

    public void setRajahdysainetta(int rajahdysainetta) {
        this.rajahdysainetta = rajahdysainetta;
    }

    public int getMiinoja() {
        return miinoja;
    }

    public void setMiinoja(int miinoja) {
        this.miinoja = miinoja;
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
        if(this.panoksia > 0) {
            this.panoksia--;
        } else {
            return;
        }
        
        int vaikutusalue = this.rajahdysainetta / 33; 
        int rajahdysalue = this.rajahdysainetta / 100; 
        
        for (Ruutu ruutu : this.kentta.getRuudut()) {
            if(itseisarvo(ruutu.getX()-x) <= vaikutusalue && itseisarvo(ruutu.getY()-y) <= vaikutusalue) {
                ruutu.avaa();
            }
            if(itseisarvo(ruutu.getX()-x) <= rajahdysalue && itseisarvo(ruutu.getY()-y) <= rajahdysalue) {
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
            this.panoksia = -1; //... panokset (ja pelaaja) räjähtää miinan mukana
        }
    }
    
    /**
    * Metodi kertoo, onko peli käynnissä (avaamattomia ruutuja, räjähtämättömiä
    * miinoja tai panoksia jäljellä). Asettaa voitto-muuttujaan tuloksen
    * 
    * @return true jos peli on käynnissä ja false jos ei ole
    */
    
    public boolean kaynnissa() {    
        if(this.panoksia==-1) { //panokset loppu
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
    
    public int vaaratilanne(int x, int y) {
        
        int vaaratilanne = 255; //255 = valkoinen, ei vaaraa
        
        for (Ruutu ruutu : this.kentta.getRuudut()) {
            if(itseisarvo(ruutu.getX()-x) <= 3 && itseisarvo(ruutu.getY()-y) <= 3) {
                if(ruutu.getTyyppi()==Tyyppi.MIINA || ruutu.getTyyppi()==Tyyppi.FEIKKI) {
                    vaaratilanne -= 10;
                }
            }
        }
        
        if(vaaratilanne<0) {
            return 0;
        }
        
        return vaaratilanne;
    }
    
    public void nollaaPeli () {
        this.kentta = new Kentta(this.sivunPituus,this.random, this.miinoja);
        this.panoksia = this.panoksiaAlussa;
    }
}
