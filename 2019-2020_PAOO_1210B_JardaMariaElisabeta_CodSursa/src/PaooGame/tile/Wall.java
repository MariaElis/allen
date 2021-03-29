package PaooGame.tile;

import PaooGame.Game;
import PaooGame.Handler;
import PaooGame.Id;

import java.awt.*;

/*! \class public class Wall extends Tile
    \brief Implementeaza notiunea de dala de tip perete(sol,iarba).
 */
public class Wall extends Tile {

    /*! \fn public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler)
        \brief Constructorul de initializare al clasei
    */
    public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler){
        ///Apel constructor la clasei de baza
        super(x,y,width,height,solid,id,handler);
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza dala in noua pozitie.

        \brief g Contextul grafic in care trebuie efectuata desenarea dalei.
    */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Game.grass.getBufferdImage(),x,y,width,height,null);
    }

    @Override
    public void Update() {

    }

}
