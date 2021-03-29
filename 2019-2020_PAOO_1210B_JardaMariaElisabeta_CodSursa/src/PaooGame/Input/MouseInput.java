package PaooGame.Input;

import PaooGame.Game;

import PaooGame.Graphics.gui.Button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*! \class public class MouseInput implements MouseListener, MouseMotionListener
    \brief Gestioneaza intrarea (input-ul) de la mouse.

    Clasa citeste daca a fost apasat un click si va genera evenimentul in cauza.
 */
public class MouseInput implements MouseListener, MouseMotionListener {

    public int x,y;  /*! Coordonatele pe axele X si respectiv Y ale locului pe tabela in care s-a apasat pe click.*/

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x=e.getX();
        y=e.getY();
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int i=0;i< Game.launcher.buttons.length;i++){
                Button button =Game.launcher.buttons[i];
                //daca apasam pe suprafata butonului se apeleaza button.triggerEvent()
                if(x>=button.getX()&&y>=button.getY()&&x<=button.getX()+button.getWidth()&&y<=button.getY()+button.getHeight()) button.triggerEvent();
        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }
}
