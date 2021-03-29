package PaooGame.entity;

import PaooGame.Game;
import PaooGame.Handler;
import PaooGame.Id;
import PaooGame.states.PlayerState;
import PaooGame.tile.Tile;

import java.awt.*;
import java.util.Random;

/*! \class public class Player extends Entity
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

 */
public class Player extends Entity{

    private PlayerState state;

    private int pixelsTravelled=0;

    //private int frame=0;
    //private int frameDelay=0;

    //nu face nimic,doar de test
    private Random random;

   //private boolean animate=false;

    /*! \fn public Player(int x, int y, int width, int height, Id id, Handler handler)
       \brief Constructorul de initializare al clasei Player.

       \param x Pozitia initiala pe axa X a eroului.
       \param y Pozitia initiala pe axa Y a eroului.
       \param width Latimea initiala a eroului.
       \param height Inaltimea initiala a eroului.
       \param id Id-ul eroului.
       \param hendler Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
    */
    public Player(int x, int y, int width, int height, Id id, Handler handler)
    {
        ///Apel al constructorului clasei de baza
        super(x,y,width,height,id,handler);

        ///seteaza marimea de start a eroului
        state=PlayerState.SMALL;

        random=new Random();
    }

    /*! \fn public void Draw(Graphics g)
       \brief Randeaza/deseneaza eroul in noua pozitie.

       \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
    */
    @Override
    public void Draw(Graphics g) {
        if(facing==0){
            g.drawImage(Game.player[frame+5].getBufferdImage(),x,y,width,height,null);
        }else if(facing==1){
            g.drawImage(Game.player[frame].getBufferdImage(),x,y,width,height,null);
        }

    }

    /*! \fn public void Update()
       \brief Actualizeaza pozitia eroului fata de ceea ce intalneste(tile, mushroom, goomba etc.)
    */
    @Override
    public void Update() {
        x+=velX;
        y+=velY;

        /// actiunea eroului la fiecare obiect de tip Tile pe care il intalneste
        for(int i=0;i<handler.tile.size();i++) {
            Tile t = handler.tile.get(i);
            if (t.isSolid()&&!goingDownPipe) {

                if(getBounds().intersects(t.getBounds())){
                    if(t.getId()==Id.flag){
                        Game.switchLevel();
                    }
                }
                if (getBoundsTop().intersects(t.getBounds())&&t.getId()!=Id.coin) {
                    setVelY(0);
                    if (jumping&&!goingDownPipe) {
                        jumping = false;
                        gravity = 0.8;
                        falling = true;
                    }
                    if (t.getId() == Id.powerUp) {
                        if (getBoundsTop().intersects(t.getBounds())) t.activated = true;
                    }
                }
                if (getBoundsBottom().intersects(t.getBounds())&&t.getId()!=Id.coin) {
                    setVelY(0);
                    if (falling) falling = false;
                } else if (!falling && !jumping) {
                    falling = true;
                    gravity = 0.8;
                }
                if (getBoundsLeft().intersects(t.getBounds())&&t.getId()!=Id.coin) {
                    setVelX(0);
                    x = t.getX() + width;
                }
                if (getBoundsRight().intersects(t.getBounds())&&t.getId()!=Id.coin) {
                    setVelX(0);
                    x = t.getX() - width;
                }
                if(getBounds().intersects(t.getBounds())&&t.getId()==Id.coin){
                    Game.coins++;
                    t.die();
                }

            }
        }

        ///actiunea eroului la fiecare obiect de tip Entity pe care il intalneste
        for(int i=0;i< handler.entity.size();i++){
            Entity e=handler.entity.get(i);

            if(e.getId()==Id.mushroom){
                if(getBounds().intersects(e.getBounds())) {
                switch (e.getType()){
                    case 0: //ciuperca rosie care te face mai mare
                        int tpX = getX();
                        int tpY = getY();
                        width += (width / 4);
                        height += (height / 4);
                        setX(tpX - width);
                        setY(tpY - height);
                        if (state == PlayerState.SMALL) state = PlayerState.BIG;
                        e.die();
                        break;
                    case 1: //ciuperca verde
                        Game.lives++;
                        e.die();
                        break;
                    }
                }
            }else if(e.getId()==Id.goomba){
                if(getBoundsBottom().intersects(e.getBoundsTop()))
                {
                    e.die();
                }
                else if(getBounds().intersects(e.getBounds())){
                    if(state==PlayerState.SMALL)
                    {
                        die();
                    }
                    else if(state==PlayerState.BIG) {
                        state=PlayerState.SMALL;
                        width-=(width/4);
                        height-=(height/4);
                        setX(getX()+(width/4));
                        setY(getY()+(height/4));

                        //width/=3;
                        //height/=3;
                        //x+=width;
                        //y+=height;
                    }
                }
            }
        }
        if(jumping&&!goingDownPipe){
            gravity-=0.12;
            setVelY((int)-gravity);
            if(gravity<=0.6){
                jumping=false;
                falling=true;
            }
        }

        if(falling&&!goingDownPipe){
            gravity+=0.12;
            setVelY((int)gravity);
        }
        if(velX!=0){
            frameDelay++;
            if(frameDelay>=10){
                frame++;
                if(frame>=3){
                    frame=0;
                }
                frameDelay=0;
            }
        }

        if(goingDownPipe){
            for(int i=0;i<Game.handler.tile.size();i++){
                Tile t=Game.handler.tile.get(i);
                if(t.getId()==Id.pipe){
                    if(getBounds().intersects(t.getBounds())){
                        switch (t.facing){
                            case 0: //facing==0 (up)
                                setVelY(-10);
                                setVelX(0);
                                pixelsTravelled+=-velY;
                                break;

                            case 2://down in primul pipe(tunel)
                                setVelY(10);
                                setVelX(0);
                                pixelsTravelled+=velY;
                                break;
                        }
                        if(pixelsTravelled>t.height) {//*2+height
                            goingDownPipe=false;
                            pixelsTravelled=0;
                        }
                    }
                }
            }
        }
    }
}
