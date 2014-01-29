
package sovelluslogiikka;

import java.util.Random;


public class Peli {
    
    private Kentta kentta;
    private Random random;
    private int panoksia;
    private boolean voitto;
    
    public Peli (int sivunPituus) {
        this.random = new Random();
        this.kentta = new Kentta(sivunPituus,this.random);
        this.panoksia = 10;
    }

    public int getPanoksia() {
        return panoksia;
    }

    public boolean isVoitto() {
        return voitto;
    }
    
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
    
    public int itseisarvo(int luku) {
        if(luku > 0) {
            return luku;
        }
        return -luku;
    }
    
    public void kokeileLapiolla(int x, int y) {
        if(this.kentta.getRuutu(x, y).kokeileLapiolla()){ //jos lapio osuu miinaan...
            this.panoksia = 0; //... panokset (ja pelaaja) räjähtää miinan mukana
        }
    }
    
    public void tulosta() {
        this.kentta.tulosta();
    }
    
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
}
