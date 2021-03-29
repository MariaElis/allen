package PaooGame.tile;

import PaooGame.Game;
import PaooGame.Graphics.Sprite;
import PaooGame.Handler;
import PaooGame.Id;
import PaooGame.entity.powerup.Mushroom;

import java.awt.*;

/*! \class public class PowerUpBlock extends Tile
    \brief Implementeaza notiunea de cutie ce contine un obiect.
 */
public class PowerUpBlock extends Tile{


    private Sprite powerUp;         /*!< Referinta catre imaginea cutiei.*/

    private boolean poppedUp=false; /*!< Flag pentru starea cutiei.*/

    private int spriteY=getY();     /*!< Pozitia pe axa Y a dalei.Pentru de determina locul aparitei obiectului din cutie.*/
    private int type;               /*!< Tipul obiectului din cutiei.*/

    /*! \fn public PowerUpBlock(int x, int y, int width, int height, boolean solid, Id id, Handler handler, Sprite powerUp,int type)
        \brief Constructorul de initializare al clasei
    */
    public PowerUpBlock(int x, int y, int width, int height, boolean solid, Id id, Handler handler, Sprite powerUp,int type) {
        ///Apel constructor la clasei de baza
        super(x, y, width, height, solid, id, handler);
        this.type=type;         /*!< Retine tipul obiectului din cutie.*/
        this.powerUp=powerUp;   /*!< Retine imaginea cutiei.*/
    }

    /*! \fn public void Draw(Graphics g)
       \brief Randeaza/deseneaza cutia in noua pozitie si obiectul care se afla in ea dupa ce player-ul atinge cutia cu capul.

       \brief g Contextul grafic in care trebuie efectuata desenarea cutiei.
   */
    @Override
    public void Draw(Graphics g) {
        if(!poppedUp) g.drawImage(powerUp.getBufferdImage(),x,spriteY,width,height,null);
        if(!activated) g.drawImage(Game.powerUp.getBufferdImage(),x,y,width,height,null);
        else g.drawImage(Game.usedPowerUp.getBufferdImage(),x,y,width,height,null);
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia continutului cutiei.
    */
    @Override
    public void Update() {
        if(activated&&!poppedUp){
            spriteY--;
            if(spriteY<=y-height){
                handler.addEntity(new Mushroom(x,spriteY,width,height,Id.mushroom,handler,type));
                poppedUp=true;
            }
        }
    }
}
