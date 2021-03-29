package PaooGame.tile;

import PaooGame.Handler;
import PaooGame.Id;

import java.awt.*;

/*! \class public class Pipe extends Tile
    \brief Implementeaza notiunea de pipe.
 */
public class Pipe extends Tile {

    /*! \fn public Pipe(int x, int y, int width, int height, boolean solid, Id id, Handler handler,int facing)
       \brief Constructorul de initializare al clasei
   */
    public Pipe(int x, int y, int width, int height, boolean solid, Id id, Handler handler,int facing) {
        ///Apel constructor la clasei de baza
        super(x, y, width, height, solid, id, handler);
        this.facing=facing;     /*!< Tipul pipe-ului, Up sau Down.*/
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza pipe-urile in noua pozitie .

        \brief g Contextul grafic in care trebuie efectuata desenarea pipe-ului.
    */
    @Override
    public void Draw(Graphics g) {
        g.setColor(new Color(128,128,128));
        g.fillRect(x,y,width,height);

    }

    @Override
    public void Update() {

    }
}
