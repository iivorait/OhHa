
package sovelluslogiikka;

/**
* Ruutu-luokka kuvaa yksittäistä kentän ruutua. Ruutu tietää sijaintinsa, 
* koskemattomuutensa ja tyyppinsä.
*/

public class Ruutu {
    
    private boolean avattu;
    private Tyyppi tyyppi;
    private int x;
    private int y;

    /**
    * Konstruktorille annetaan ruudun koordinaatit ja tyyppi
    *
    * @param    x       ruudun x-koordinaatti
    * @param    y       ruudun y-koordinaatti
    * @param    tyyppi  käynnissä oleva Gui-olio
    */
    
    public Ruutu(int x, int y, Tyyppi tyyppi) {
        this.avattu = false;
        this.tyyppi = tyyppi;
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Tyyppi getTyyppi() {
        return tyyppi;
    }
    
    public boolean onkoAvattu() {
        return avattu;
    }
    
    /**
    * avaa-metodi asettaa ruudun avatuksi, eli sen sisältö tulee näkyviin
    */
    
    public void avaa() {
        this.avattu = true;
    }
    
    /**
    * rajayta-metodi räjäyttää ruudun sisältöön katsomatta
    */
    
    public void rajayta() {
        this.avaa();
        this.tyyppi = Tyyppi.RAJAHTANYT;
    }
    
    /**
    * kokeileLapiolla -metodi avaa ruudun ja näyttää sen todellisen sisällön.
    * Jos ruudussa oli miina, peli päättyy
    * 
    * @return true jos miina, false jos ei miinaa
    */
    
    public boolean kokeileLapiolla() {
        this.avaa();
        if(this.tyyppi==Tyyppi.MIINA) {
            return true;
        } else if(this.tyyppi==Tyyppi.FEIKKI) {
            this.tyyppi = Tyyppi.TODETTUFEIKKI;
        }
        return false;
    }
    
    
    
}
