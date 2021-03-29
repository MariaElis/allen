package PaooGame.entity;

import PaooGame.Game;
import PaooGame.Handler;
import PaooGame.Id;

import java.awt.*;

/*! \class Entity
    \brief. Implementeaza notiunea abstracta de caracter/individ/fiinta, entitate activa din joc, "element cu care se poate interactiona: monstru, turn etc.".
 */
public abstract class Entity {
    public int x;             /*!< Pozitia pe axa X a "tablei" de joc a imaginii entitatii.*/
    public int y;             /*!< Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.*/
    public int width;         /*!< Latimea imaginii entitatii.*/
    public int height;        /*!< Inaltimea imaginii entitatii.*/
    //0 - left,1 - right
    public int facing=0;      /*!< Imaginea pozitionata implicit pe stanga a entitatii.*/
    public int type;          /*!<Tipul entitatii..De exemplu: tipul ciupercii(rosie sau verde)*/

    public boolean jumping=false;       /*!< Flag pentru salt.*/
    public boolean falling=false;       /*!< Flag pentru cadere.*/
    public boolean goingDownPipe=false; /*!< Flag pentru cadere prin pipe.*/

    public int velX;           /*!< Retine noua pozitie a caracterului pe axa X.*/
    public int velY;           /*!< Retine noua pozitie a caracterului pe axa Y.*/

    public Id id;              /*!< Id-ul entitatii din clasa de tip enum Id.*/

    public double gravity=0.0; /*!< Initializare a gravitatii.*/

    public Handler handler;    /*!< O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/

    public int frame=0;
    public int frameDelay=0;
    public boolean animate=false; /*!< Flag pentru animatia entitatii.*/

    /*! \fn  public Entity(int x,int y,int width,int height,Id id,Handler handler)
    \brief Constructor de initializare al clasei
    \param  x   Pozitia pe axa X a "tablei" de joc a imaginii entitatii.
    \param  y   Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.
    \param  width   Latimea imaginii entitatii.
    \param  height  Inaltimea imaginii entitatii.
    \param id  Id-ul entitatii.
    \param  handler Referinte "shortcut" catre alte referinte
 */
    public Entity(int x,int y,int width,int height,Id id,Handler handler)
    {
        this.x=x;              /*!< Retine coordonata pe axa X.*/
        this.y=y;              /*!< Retine coordonata pe axa Y.*/
        this.width=width;      /*!< Retine latimea imaginii.*/
        this.height=height;    /*!< Retine inaltimea imaginii.*/
        this.id=id;            /*!< Retine Id-ul entitatii.*/
        this.handler=handler;  /*!< Retine the "shortcut".*/
    }

    ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g);

    ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();

    /*! \fn public void die()
        \brief Face sa "moara" entitatea ,iar in caz de entitatea este chiar player-ul ii si scade din vieti.
     */
    public void die()
    {
        handler.removeEntity(this);
        if(getId()==Id.player){
            Game.lives--;
            Game.showDeathScreen=true;

            if(Game.lives<=0) Game.gameOver=true;
        }

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
        \brief Returneaza Id-ul entitatii.
   */
    public Id getId(){
        return id;
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

    /*! \fn public int getType()
        \brief Returneaza tipul entitatii.
    */
    public int getType() {
        return type;
    }

    /*! \fn Rectangle getBounds()
        \brief Returneaza dreptunghiul de coliziune.
    */
    public Rectangle getBounds()
    {
        return new Rectangle(getX(),getY(),width,height);
    }
    /*! \fn Rectangle getBounds()
       \brief Returneaza dreptunghiul de coliziune de sus.
   */
    public Rectangle getBoundsTop()
    {
        return new Rectangle(getX()+10,getY(),width-20,5);
    }

    /*! \fn Rectangle getBounds()
      \brief Returneaza dreptunghiul de coliziune de jos.
  */
    public Rectangle getBoundsBottom(){
        return new Rectangle(getX()+10,getY()+height-5,width-20,5);
    }

    /*! \fn Rectangle getBounds()
      \brief Returneaza dreptunghiul de coliziune din stanga.
  */
    public Rectangle getBoundsLeft(){
        return new Rectangle(getX(),getY()+10,5,height-20);
    }

    /*! \fn Rectangle getBounds()
      \brief Returneaza dreptunghiul de coliziune din dreapta.
  */
    public Rectangle getBoundsRight(){
        return new Rectangle(getX()+width-5,getY()+10,5,height-20);
    }


}
