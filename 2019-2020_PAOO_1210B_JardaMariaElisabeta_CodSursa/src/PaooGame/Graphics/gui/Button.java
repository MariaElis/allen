package PaooGame.Graphics.gui;

import PaooGame.Game;

import java.awt.*;

/*! \class public class Button
    \brief Implementeaza notiunea de buton in joc.

 */
public class Button {

    public int x,y;         /*!< Pozitia butonului pe axele X si Y*/
    public int width,height;/*!< Latimea si respectiv inaltimea butonului*/

    public String label;    /*!< Textul scris pe buton.*/

    /*! \fn public Button(int x,int y,int width,int height,String label)
        \brief Constructor de initializare al clasei Button

        \param  x   Pozitia pe axa X a "tablei" de joc a butonului.
        \param  y   Pozitia pe axa Y a "tablei" de joc a butonului.
        \param  width   Latimea butonului.
        \param  height  Inaltimea butonului
        \param label
    */
    public Button(int x,int y,int width,int height,String label){
        this.x=x;           /*!< Retine coordonata pe axa X.*/
        this.y=y;           /*!< Retine coordonata pe axa Y.*/
        this.width=width;   /*!< Retine latimea butonului.*/
        this.height=height; /*!< Retine inaltimea butonului.*/
        this.label=label;   /*!< Retine string-ul de pe buton.*/
    }

    /*! \fn public void Draw(Graphics g)
       \brief Randeaza/deseneaza butonul in noua pozitie.

       \brief g Contextul grafic in care trebuie efectuata desenarea butonului.
    */
    public void Draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Century Gothic",Font.BOLD,35));
        g.drawRect(getX(),getY(),getWidth(),getHeight());

        FontMetrics fm=g.getFontMetrics();
        int stringX=(getWidth()-fm.stringWidth(getLabel()))/2;
        int stringY=(fm.getAscent()+(getHeight()-(fm.getAscent()+fm.getDescent()))/2);
        g.drawString(getLabel(),getX()+stringX,getY()+stringY);
    }
    //de cate ori se va apasa un buton,se va apela aceasta metoda
    public void triggerEvent(){
        //in functie de ce String e label-ul
        if(getLabel().toLowerCase().contains("start")) Game.playing=true;
        else if(getLabel().toLowerCase().contains("exit")) System.exit(0);
    }


    /*! \fn public int getX()
      \brief Returneaza coordonata pe axa X.
   */
    public int getX()
    {
        return x;
    }

    /*! \fn public void setX(int x)
        \brief Seteaza coordonata pe axa X.
    */
    public void setX(int x)
    {
        this.x=x;
    }

    /*! \fn public int getY()
      \brief Returneaza coordonata pe axa Y.
   */
    public int getY()
    {
        return y;
    }

    /*! \fn public void setY(int y)
       \brief Seteaza coordonata pe axa Y.
    */
    public void setY(int y)
    {
        this.y=y;
    }

    /*! \fn public float getWidth()
        \brief Returneaza latimea butonului.
   */
    public int getWidth() {
        return width;
    }

    /*! \fn public void setWidth(int width)
        \brief Seteaza latimea butonului.
 */
    public void setWidth(int width) {
        this.width = width;
    }

    /*! \fn public float getHeight()
        \brief Returneaza inaltimea butonului.
    */
    public int getHeight() {
        return height;
    }

    /*! \fn public void setHeight(int height)
        \brief Seteaza inaltimea butonului.
   */
    public void setHeight(int height) {
        this.height = height;
    }

    /*! \fn public String getLabel()
        \brief Returneaza String-ul cu numele de pe buton.
    */
    public String getLabel() {
        return label;
    }

    /*! \fn public void setLabel(String label)
        \brief Seteaza String-ul cu numele de pe buton.
    */
    public void setLabel(String label) {
        this.label = label;
    }
}
