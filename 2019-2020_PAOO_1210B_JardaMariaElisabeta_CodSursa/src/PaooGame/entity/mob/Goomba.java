package PaooGame.entity.mob;

import java.awt.Graphics;

import PaooGame.Game;
import PaooGame.Handler;
import PaooGame.Id;
import PaooGame.entity.Entity;
import PaooGame.tile.Tile;

import java.awt.*;
import java.util.Random;

/*! \class public class Goomba extends Entity
    \brief Implementeaza notiunea de inamic (caracterul controlat de joc).Numele inamicului este Goomba.

 */
public class Goomba extends Entity {

    ///Referinta la un obiect de tip Random pentru deplasarea la intamplare in stanga sau in dreapta
    private Random random=new Random();

    /*! \fn public Goomba(int x, int y, int width, int height, Id id, Handler handler)
        \brief Constructorul de initializare al clasei Goomba.

    \param x Pozitia initiala pe axa X a inamicului Goomba.
    \param y Pozitia initiala pe axa Y a inamicului Goomba.
    \param width Latimea initiala a inamicului Goomba.
    \param height Inaltimea initiala a inamicului Goomba.
    \param id Id-ul inamicului Goomba.
    \param hendler Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
*/
    public Goomba(int x, int y, int width, int height, Id id, Handler handler) {
        ///Apel al constructorului clasei de baza
        super(x, y, width, height, id, handler);

        ///aici se seteaza directia deplasarii inamicului
        int dir=random.nextInt(2);

        switch (dir){
            case 0:
                setVelX(-2);
                facing=0;
                break;
            case 1:
                setVelX(2);
                facing=1;
                break;
        }
    }

    /*! \fn public void Draw(Graphics g)
     \brief Randeaza/deseneaza inamicul in noua pozitie.

     \brief g Contextul grafic in care trebuie efectuata desenarea inamicului.
  */
    @Override
    public void Draw(Graphics g) {
        if(facing==0){
            g.drawImage(Game.goomba[frame+5].getBufferdImage(),x,y,width,height,null);
        }else if(facing==1){
            g.drawImage(Game.goomba[frame].getBufferdImage(),x,y,width,height,null);
        }
    }

    /*! \fn public void Update()
    \brief Actualizeaza pozitia inamicului fata de ceea ce intalneste(tile-wall)
 */
    @Override
    public void Update() {
        x+=velX;
        y+=velY;

        ///actiunea ianmicului la fiecare obiect de tip Tile pe care-l intalneste
        for(Tile t:handler.tile){
            if(!t.solid) break;
            if(t.getId()==Id.wall){
                if(getBoundsBottom().intersects(t.getBounds()))
                {
                    setVelY(0);
                    if(falling) falling=false;
                }else if(!falling){
                    gravity=0.8;
                    falling=true;
                }
                if(getBoundsLeft().intersects(t.getBounds()))
                {
                    setVelX(2);
                    facing=1;
                }
                if(getBoundsRight().intersects(t.getBounds()))
                {
                    setVelX(-2);
                    facing=0;
                }
            }
        }

        ///ianmicul are gravitate la cadere
        if(falling){
            gravity+=0.1;
            setVelY((int)gravity);
        }

        if(velX!=0){
            frameDelay++;
            if(frameDelay>=3){
                frame++;
                if(frame>=5){
                    frame=0;
                }
                frameDelay=0;
            }
        }
    }
}
