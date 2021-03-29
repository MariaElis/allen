package PaooGame.entity.powerup;

import PaooGame.Game;
import PaooGame.Handler;
import PaooGame.Id;
import PaooGame.entity.Entity;
import PaooGame.tile.Tile;

import java.awt.*;
import java.util.Random;

/*! \class public class Mushroom extends Entity
    \brief Implementeaza notiunea de ciuperca (caracterul controlat de joc).

 */
public class Mushroom extends Entity {

    ///Referinta la un obiect de tip Random pentru deplasarea la intamplare in stanga sau in dreapta
    private Random random=new Random();

    /*! \fn public Mushroom(int x, int y, int width, int height, Id id, Handler handler,int type)
    \brief Constructorul de initializare al clasei Mushroom.

 \param x Pozitia initiala pe axa X a ciupercii.
       \param y Pozitia initiala pe axa Y a ciupercii.
       \param width Latimea initiala a cipercii.
       \param height Inaltimea initiala a ciupercii.
       \param id Id-ul ciupercii.
       \param hendler Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
       \param type Tipul ciupercii.(verde sau rosie)
 */
    public Mushroom(int x, int y, int width, int height, Id id, Handler handler,int type) {
        ///Apel al constructorului clasei de baza
        super(x, y, width, height, id, handler);
        this.type=type;    /*!< Retine tipul ciupercii*/

        ///aici se seteaza directia deplasarii ciupercii
        int dir=random.nextInt(2);

        switch (dir){
            case 0:
                setVelX(-2);
                break;
            case 1:
                setVelX(2);
                break;
        }
    }

    /*! \fn public void Draw(Graphics g)
       \brief Randeaza/deseneaza ciuperca in noua pozitie.

       \brief g Contextul grafic in care trebuie efectuata desenarea ciupercii.
    */
    @Override
    public void Draw(Graphics g) {
        switch(getType()){
            case 0:
                ///ciuperca rosie
                g.drawImage(Game.mushroom.getBufferdImage(),x,y,width,height,null);
                break;
            case 1:
                ///ciuperca verde
                g.drawImage(Game.lifeMushroom.getBufferdImage(),x,y,width,height,null);
                break;
        }

    }

    /*! \fn public void Update()
     \brief Actualizeaza pozitia ciupercii fata de ceea ce intalneste(tile-wall)
  */
    @Override
    public void Update() {
        x+=velX;
        y+=velY;

        ///actiunea ciupercii la fiecare obiect de tip Tile pe care-l intalneste
        for(Tile t:handler.tile){
            if(!t.solid) break;
            if(t.getId()==Id.wall){
                if(getBoundsBottom().intersects(t.getBounds()))
                {
                    setVelY(0);
                    if(falling) falling=false;
                }else
                if(!falling){
                    gravity=0.8;
                    falling=true;
                }
                if(getBoundsLeft().intersects(t.getBounds()))
                {
                    setVelX(2);
                }
                if(getBoundsRight().intersects(t.getBounds()))
                {
                    setVelX(-2);
                }
            }
        }

        ///ciuperca are gravitate la cadere
        if(falling){
            gravity+=0.1;
            setVelY((int)gravity);
        }

    }
}
