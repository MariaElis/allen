package PaooGame.tile;

import PaooGame.Game;
import PaooGame.Handler;
import PaooGame.Id;

import java.awt.*;

/*! \class public class Flag extends Tile
    \brief Implementeaza notiunea de Finish Flag.
 */
public class Flag extends Tile{

    /*! \fn public Flag(int x, int y, int width, int height, boolean solid, Id id, Handler handler)
        \brief Constructorul de initializare al clasei
    */
    public Flag(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        ///Apel constructor la clasei de baza
        super(x, y, width, height, solid, id, handler);
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza Steagul de finish pe bucati .

        \brief g Contextul grafic in care trebuie efectuata desenarea steagului.
    */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Game.flag[1].getBufferdImage(),getX(),getY(),width,64,null);

        g.drawImage(Game.flag[2].getBufferdImage(),getX(),getY()+64,width,64,null);
        g.drawImage(Game.flag[2].getBufferdImage(),getX(),getY()+128,width,64,null);
        g.drawImage(Game.flag[2].getBufferdImage(),getX(),getY()+192,width,64,null);

        g.drawImage(Game.flag[0].getBufferdImage(),getX(),getY()+256,width,64,null);
    }

    @Override
    public void Update() {

    }
}
