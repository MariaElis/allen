package PaooGame.tile;

import PaooGame.Game;
import PaooGame.Handler;
import PaooGame.Id;

import java.awt.*;

/*! \class public class Coin extends Tile
    \brief Implementeaza notiunea de scor(diamante in joc).
 */
public class Coin extends Tile {

    /*! \fn public Coin(int x, int y, int width, int height, boolean solid, Id id, Handler handler)
        \brief Constructorul de initializare al clasei
    */
    public Coin(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        ///Apel constructor la clasei de baza
        super(x, y, width, height, solid, id, handler);
    }

    /*! \fn public void Draw(Graphics g)
      \brief Randeaza/deseneaza diamantele .

      \brief g Contextul grafic in care trebuie efectuata desenarea diamantului.
  */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Game.coin.getBufferdImage(),x,y,width,height,null);
    }

    @Override
    public void Update() {

    }
}
