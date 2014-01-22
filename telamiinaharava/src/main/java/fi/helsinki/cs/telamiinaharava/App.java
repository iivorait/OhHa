package fi.helsinki.cs.telamiinaharava;

import java.util.Scanner;
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
        
        peli.tulosta();
        
        while(peli.kaynnissa()) {
            System.out.println("");
            System.out.print("Aseta räjähde x: ");
            int x = Integer.parseInt(scanner.nextLine());
            System.out.print("Aseta räjähde y: ");
            int y = Integer.parseInt(scanner.nextLine());
            System.out.println("");

            peli.rajayta(x, y);
            peli.tulosta();
        }
        
        System.out.println("\nPeli loppui");
        
    }
}
