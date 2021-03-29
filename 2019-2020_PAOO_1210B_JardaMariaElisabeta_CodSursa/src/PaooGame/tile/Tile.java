package PaooGame.tile;

import PaooGame.Handler;
import PaooGame.Id;

import java.awt.*;

/*! \class public abstract class Tile
    \brief Implementeaza notiunea abstracta de dala in joc,de obiecte fara viata.(Wall,Coin,Flag,Pipe,PowerUpBlock)
 */
public abstract class Tile {

    public int x;               /*!< Pozitia pe axa X a dalei.*/
    public int y;               /*!< Pozitia pe axa Y a dalei. */
    public int width;           /*!< Latimea unei dale.*/
    public int height;          /*!< Inaltimea unei dale.*/
    public boolean solid=false; /*!< Flag pentru dala solida.*/
    public boolean activated=false;

    public int velX,velY;        /*!< Retine noua pozitie a dalei axa X.*/
    public int facing;           /*!< Retine noua pozitie a dalei axa Y.*/

    public Id id;                /*!< Id-ul dalei din clasa de tip enum Id.*/

    public Handler handler;      /*!< Referinta catre un obiect de tip hendler.*/

    /*! \fn  public Tile(int x,int y,int width,int height,boolean solid,Id id,Handler handler)
        \brief Constructor de initializare al clasei
        \param  x   Pozitia pe axa X a "tablei" de joc a dalei.
        \param  y   Pozitia pe axa Y a "tablei" de joc a dalei.
        \param  width   Latimea dalei.
        \param  height  Inaltimea dalei.
        \param id  Id-ul dalei.
        \param  handler Referinte "shortcut" catre alte referinte
    */
    public Tile(int x,int y,int width,int height,boolean solid,Id id,Handler handler)
    {
        this.x=x;               /*!< Retine coordonata pe axa X.*/
        this.y=y;               /*!< Retine coordonata pe axa Y.*/
        this.width=width;       /*!< Retine latimea dalei.*/
        this.height=height;     /*!< Retine inaltimea dalei.*/
        this.solid=solid;       /*!< Retine flagul dalei.*/
        this.id=id;             /*!< Retine Id-ul dalei.*/
        this.handler=handler;   /*!< Retine the "shortcut".*/
    }

    ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g);

    ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();

    /*! \fn public void die()
       \brief Sterge dala .
    */
    public void die()
    {
        handler.removeTile(this);
    }

    /*! \fn public int getX()
       \brief Returneaza coordonata pe axa X.
    */
    public int getX()
    {
        return x;
    }

    /*! \fn public void setX(int x)
        \brief Seteaza coordonata pe axa X.
    */
    public void setX(int x)
    {
        this.x=x;
    }

    /*! \fn public int getY()
      \brief Returneaza coordonata pe axa Y.
   */
    public int getY()
    {
        return y;
    }

    /*! \fn public void setY(int y)
       \brief Seteaza coordonata pe axa Y.
    */
    public void setY(int y)
    {
        this.y=y;
    }

    /*! \fn public Id getId()
        \brief Returneaza Id-ul dalei.
   */
    public Id getId(){
        return id;
    }

    /*! \fn public boolean isSolid()
     \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
  */
    public boolean isSolid()
    {
        return solid;
    }

    /*! \fn public void setSolid(boolean solid)
    \brief Seteaza proprietatea de dala solida sau nu.
 */
    public void setSolid(boolean solid)
    {
        this.solid=solid;
    }

    /*! \fn public void setVelX(int velX)
      \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
   */
    public void setVelX(int velX)
    {
        this.velX=velX;
    }

    /*! \fn public void setVelY(int velY)
       \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
    */
    public void setVelY(int velY)
    {
        this.velY=velY;
    }

    /*! \fn Rectangle getBounds()
       \brief Returneaza dreptunghiul de coliziune.
   */
    public Rectangle getBounds()
    {
        return new Rectangle(getX(),getY(),width,height);
    }
}


