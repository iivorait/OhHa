package fi.helsinki.cs.telamiinaharava;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import kayttoliittyma.Gui;
import sovelluslogiikka.Peli;

/**
 * Testikoodia
 *
 */
public class App 
{
    public static void main( String[] args )
    {        
        Scanner scanner = new Scanner(System.in);
        
        Peli peli = new Peli(8);
        
        Gui kayttoliittyma = new Gui(peli);
        SwingUtilities.invokeLater(kayttoliittyma);
        
        peli.tulosta();
        
        while(peli.kaynnissa()) {
            System.out.println("");
            System.out.println("Panoksia jäljellä " + peli.getPanoksia() + " kpl");
            System.out.print("1: Aseta räjähde / 2: Kokeile lapiolla ");
            int valinta = Integer.parseInt(scanner.nextLine());
            System.out.print("Anna x-koordinaatti: ");
            int x = Integer.parseInt(scanner.nextLine());
            System.out.print("Anna y-koordinaatti: ");
            int y = Integer.parseInt(scanner.nextLine());
            System.out.println("");
            
            if(valinta==1) {
                peli.rajayta(x, y);
            } else {
                peli.kokeileLapiolla(x, y);
            }

            peli.tulosta();
            kayttoliittyma.uudelleenpiirra();
        }
        
        System.out.print("\nPeli loppui ");
        
        if(peli.isVoitto()) {
            System.out.println("voittoon");
        } else {
            System.out.println("häviöön");
        }        
    }
}
