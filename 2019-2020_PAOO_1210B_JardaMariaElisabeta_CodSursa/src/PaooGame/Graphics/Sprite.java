package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Sprite
    \brief Clasa retine o referinta catre o imagine formata din dale (sprite sheet)


 */
public class Sprite {

    public SpriteSheet sheet;

    public BufferedImage image;

    /*! \class public Sprite(SpriteSheet sheet,int x,int y)
        \brief Constructor,initializeaza imaginea.

        \param sheet Obiectul ce a retinut SpriteSheet-ul
        \param x Numarul dalei din sprite sheet pe axa x.
        \param y Numarul dalei din sprite sheet pe axa y.
    */
    public Sprite(SpriteSheet sheet,int x,int y){
        image= sheet.getSprite(x,y);
    }

    /*! \class public BufferedImage getBufferdImage()
        \brief Metoda returneaza imaginea

     */
    public BufferedImage getBufferdImage(){
        return image;
    }
}
