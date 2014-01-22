
package sovelluslogiikka;

import java.util.Random;


public class Peli {
    
    private Kentta kentta;
    private Random random;
    
    public Peli (int sivunPituus) {
        this.random = new Random();
        this.kentta = new Kentta(sivunPituus,this.random);
    }
    
    public void rajayta(int x, int y) {
        for (Ruutu ruutu : this.kentta.getRuudut()) {
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
    
    public void tulosta() {
        this.kentta.tulosta();
    }
    
    public boolean kaynnissa() {
        for (Ruutu ruutu : this.kentta.getRuudut()) {
            if(!ruutu.onkoAvattu()) {
                return true;
            }
        }
        return false;
    }
}
