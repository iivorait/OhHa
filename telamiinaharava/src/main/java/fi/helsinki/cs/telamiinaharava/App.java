package fi.helsinki.cs.telamiinaharava;

import javax.swing.SwingUtilities;
import kayttoliittyma.Gui;
import sovelluslogiikka.Peli;

/**
* Pelin käynnistys 
*/
public class App 
{
    public static void main( String[] args )
    {        
        Peli peli = new Peli(8, 10);
        
        Gui kayttoliittyma = new Gui(peli);
        SwingUtilities.invokeLater(kayttoliittyma);  
    }
}
