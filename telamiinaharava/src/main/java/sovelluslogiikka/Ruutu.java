
package sovelluslogiikka;

public class Ruutu {
    
    private boolean avattu;
    private Tyyppi tyyppi;
    private int x;
    private int y;

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
    
    public void avaa() {
        this.avattu = true;
    }
    
    public void rajayta() {
        this.avaa();
        this.tyyppi = Tyyppi.RAJAHTANYT;
    }
    
    public boolean kokeileLapiolla() {
        this.avaa();
        if(this.tyyppi==Tyyppi.MIINA) {
            return true;
        }
        return false;
    }
    
    public void tulosta() {
        if(!this.avattu) {
            System.out.print(" ");
        } else if(this.tyyppi==Tyyppi.FEIKKI) {
            System.out.print("F");
        } else if(this.tyyppi==Tyyppi.MIINA) {
            System.out.print("M");
        } else if(this.tyyppi==Tyyppi.RAJAHTANYT) {
            System.out.print("*");
        } else {
            System.out.print("-");
        }
    }
    
    
    
}
