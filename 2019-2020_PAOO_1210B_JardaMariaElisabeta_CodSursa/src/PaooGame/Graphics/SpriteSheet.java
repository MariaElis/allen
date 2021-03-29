package PaooGame.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \class public class SpriteSheet
    \brief Clasa retine o referinta catre o imagine formata din dale (sprite sheet)

    Metoda getSprite() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
 */
public class SpriteSheet {

    private BufferedImage sheet;    /*!< Referinta catre obiectul BufferedImage ce contine sprite sheet-ul.*/

    /*! \fn public SpriteSheet(String path)
        \brief Constructor, initializeaza spriteSheet incarcand o imagine intr-un obiect BufferedImage si returneaza o referinta catre acesta.

        \param path Calea relativa pentru localizarea fisierul imagine.
     */
    public SpriteSheet(String path){
             /// Avand in vedere exista situatii in care fisierul sursa sa nu poate fi accesat
             /// metoda read() arunca o excpetie ce trebuie tratata
        try {
            /// Clasa ImageIO contine o serie de metode statice pentru file IO.
            /// Metoda read() are ca argument un InputStream construit avand ca referinta
            /// directorul res, director declarat ca director de resurse in care se gasesc resursele
            /// proiectului sub forma de fisiere sursa.
            sheet= ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            /// Afiseaza informatiile necesare depanarii.
            e.printStackTrace();
        }

    }

    /*! \fn public BufferedImage getSprite(int x,int y)
      \brief Returneaza un obiect BufferedImage ce contine o subimagine (dala).

      \param x numarul dalei din sprite sheet pe axa x.
      \param y numarul dalei din sprite sheet pe axa y.
   */
    public BufferedImage getSprite(int x,int y){
        return sheet.getSubimage(x*32-32,y*32-32,32,32);
    }
}
