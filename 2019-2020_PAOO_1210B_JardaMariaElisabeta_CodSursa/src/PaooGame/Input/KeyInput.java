package PaooGame.Input;

import PaooGame.Game;
import PaooGame.Id;
import PaooGame.entity.Entity;
import PaooGame.tile.Tile;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*! \class public class KeyInput implements KeyListener
    \brief Gestioneaza intrarea (input-ul) de tastatura.

    Clasa citeste daca au fost apasata o tasta, stabiliteste ce tasta a fost actionata si va genera evenimentul in cauza.
 */
public class KeyInput implements KeyListener {

    /*! \fn public void keyPressed(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand un eveniment de tasta apasata este generat.

        \param e obiectul eveniment de tastatura.
 */
    @Override
    public void keyPressed(KeyEvent e) {
        ///se retine int key ca o tasta a fost apasata.
        int key=e.getKeyCode();
        for(int i=0;i<Game.handler.entity.size();i++){
            Entity en=Game.handler.entity.get(i);
            if(en.getId()== Id.player){
                if(en.goingDownPipe) return;
                switch(key) {
                    case KeyEvent.VK_W:
                        for (int q = 0; q < Game.handler.tile.size(); q++) {
                            Tile t = Game.handler.tile.get(q);
                            if (t.getId() == Id.pipe) {
                                if (en.getBoundsTop().intersects(t.getBounds())) {
                                    if (!en.goingDownPipe) en.goingDownPipe = true;
                                }
                            }
                        }
                        if (!en.jumping) {
                            en.jumping = true;
                            en.gravity = 8.0;
                        }
                        break;
                    case KeyEvent.VK_S:
                        for (int q = 0; q < Game.handler.tile.size(); q++) {
                            Tile t = Game.handler.tile.get(q);
                            if (t.getId() == Id.pipe) {
                                if (en.getBoundsBottom().intersects(t.getBounds())) {
                                    if (!en.goingDownPipe) en.goingDownPipe = true;
                                }
                            }
                        }
                        break;
                    case KeyEvent.VK_A:
                        en.setVelX(-5);
                        en.facing = 0;//left
                        break;
                    case KeyEvent.VK_D:
                        en.setVelX(5);
                        en.facing = 1;//right
                        break;
                    case KeyEvent.VK_Q:
                        en.die();
                        break;
                }
            }
        }
    }

    /*! \fn public void keyReleased(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de tasta eliberata este generat.

        \param e obiectul eveniment de tastatura.
    */
    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();
        for(int i=0;i<Game.handler.entity.size();i++){
            Entity en=Game.handler.entity.get(i);
            if(en.getId()== Id.player){
                switch(key){
                    case KeyEvent.VK_W:
                        en.setVelY(0);
                        break;
                    case KeyEvent.VK_S:
                        en.setVelY(0);
                        break;
                    case KeyEvent.VK_A:
                        en.setVelX(0);
                        break;
                    case KeyEvent.VK_D:
                        en.setVelX(0);
                        break;
                }
            }

        }
    }

    /*! \fn public void keyTyped(KeyEvent e)
     \brief Functie ce va fi apelata atunci cand o tasta a fost apasata si eliberata.
     Momentan aceasta functie nu este utila in program.
  */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //not using
    }


}
