package PaooGame.Graphics.gui;

import PaooGame.Game;
import PaooGame.Graphics.Sprite;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/*! \class public class Launcher
    \brief Implementeaza notiunea de butoane in joc.

 */
public class Launcher {

    public Button[] buttons; /*! Vector de butoane.*/

    /*! \fn public Launcher()
        \brief Constructor de initializare al clasei Launcher

    */
    public Launcher(){

        ///initializare butoane
        buttons=new Button[2];

        ///forma butoanelor
        buttons[0]=new Button(100, Game.getFrameHeight()/2-100,Game.getFrameWidth()-200,100,"Start Game");
        buttons[1]=new Button(100,Game.getFrameHeight()/2+100,Game.getFrameWidth()-200,100,"Exit Game");

    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza background-ul butoanelor si butoanele in noua pozitie si noua culoare.

        \brief g Contextul grafic in care trebuie efectuata desenarea .
    */
    public void Draw(Graphics g){
        g.setColor(Color.MAGENTA);
        g.fillRect(0,0, Game.getFrameWidth(),Game.getFrameHeight());


        for(int i=0;i<buttons.length;i++){
            buttons[i].Draw(g);
        }
    }


}
