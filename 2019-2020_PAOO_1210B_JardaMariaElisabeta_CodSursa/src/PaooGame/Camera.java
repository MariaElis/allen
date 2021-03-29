package PaooGame;

import PaooGame.entity.Entity;


/*! \class public class Camera
    \brief Clasa ce actualizeaza camera ,ceea ce vedem in joc in functie de pozitia player-ului.
 */
public class Camera {
    public int x;       /*!< Pozitia pe axa X a camerei.*/
    public int y;       /*!< Pozitia pe axa Y a camerei.*/


    /*! \fn public void Update(Entity player)
       \brief Actualizeaza pozitia camerei in functie de pozitia player-ului.
    */
    public void Update(Entity player)
    {
        setX(-player.getX()+Game.getFrameWidth()/2);
        setY(-player.getY()+Game.getFrameHeight()/2);
    }

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
}
