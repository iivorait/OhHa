
package sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

public class Kentta {
    private ArrayList<Ruutu> ruudut;
    private Random random;

    public ArrayList<Ruutu> getRuudut() {
        return ruudut;
    }
    
    public Kentta(int sivunPituus, Random random) {
        this.ruudut = new ArrayList<>();
        this.random = random;
        
        for (int i = 0; i < sivunPituus; i++) { //rivit
            for (int j = 0; j < sivunPituus; j++) { //sarakkeet
                ruudut.add(new Ruutu(j,i,this.arvoTyyppi()));
            }
        }
    }
    
    public Tyyppi arvoTyyppi() {
        int arpa = this.random.nextInt(100);
        
        if(arpa<10) { //0-9 = 10% mahdollisuus
            return Tyyppi.MIINA;
        } else if(arpa<20) { //10-19 = 10% mahdollisuus
            return Tyyppi.FEIKKI;
        }
        
        return Tyyppi.TYHJA;
    }
    
    public void tulosta() {
        int y = 0;
        for (Ruutu ruutu : ruudut) {
            if(y != ruutu.getY()) {
                System.out.println("");
                y = ruutu.getY();
            }
            ruutu.tulosta();
        }
    }
}
