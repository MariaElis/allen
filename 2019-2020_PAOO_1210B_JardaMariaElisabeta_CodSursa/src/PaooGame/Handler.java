package PaooGame;

import PaooGame.entity.Entity;
import PaooGame.entity.Player;
import PaooGame.entity.mob.Goomba;
import PaooGame.entity.powerup.Mushroom;
import PaooGame.tile.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/*! \class public class Handler
    \brief Clasa retine referinta catre liste de entitati sau tile-uri.
 */
public class Handler {
    public LinkedList<Entity> entity=new LinkedList<Entity>();
    public LinkedList<Tile> tile=new LinkedList<Tile>();

    public Handler(){
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza toate entitatile si obiectele de tip Tile.

        \brief g Contextul grafic in care trebuie efectuata desenarea elementelor jocului.
    */
    public void Draw(Graphics g)
    {
        for(int i=0;i<entity.size();i++)
        {
            Entity en=entity.get(i);
            if(Game.getVisibleArea()!=null&& en.getBounds().intersects(Game.getVisibleArea())) en.Draw(g);
        }

        for(int i=0;i<tile.size();i++)
        {
            Tile ti=tile.get(i);
            if(Game.getVisibleArea()!=null&& ti.getBounds().intersects(Game.getVisibleArea()))ti.Draw(g);
        }

        //afisam scorul si cu Game.getVisibleArea().x reusim sa il afisam deasupra a orice altceva
        g.drawImage(Game.coin.getBufferdImage(),Game.getVisibleArea().x+20,Game.getVisibleArea().y+20,90,90,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("COURIER",Font.BOLD,20));
        g.drawString("x"+Game.coins,Game.getVisibleArea().x+95,Game.getVisibleArea().y+85);
    }

    /*! \fn public void Update()
       \brief Actualizeaza pozitia elementelor jocului.
    */
    public void Update()
    {
        for(int i=0;i<entity.size();i++)
        {
            Entity en=entity.get(i);
            en.Update();
        }

        for(int i=0;i<tile.size();i++)
        {
            Tile ti=tile.get(i);
            if(Game.getVisibleArea()!=null&& ti.getBounds().intersects(Game.getVisibleArea())) ti.Update();
        }
    }

    /*! \fn public void addEntity(Entity en)
        \brief Adauga obiecte de tip Entity in lista.
    */
    public void addEntity(Entity en)
    {
        entity.add(en);
    }

    /*! \fn public void removeEntity(Entity en)
        \brief Sterge obiecte de tip Entity din lista.
    */
    public void removeEntity(Entity en)
    {
        entity.remove(en);
    }

    /*! \fn  public void addTile(Tile ti)
        \brief Adauga obiecte de tip Tile in lista.
   */
    public void addTile(Tile ti)
    {
        tile.add(ti);
    }

    /*! \fn  public void removeTile(Tile ti)
        \brief Sterge obiecte de tip Tile din lista.
    */
    public void removeTile(Tile ti) {
        tile.remove(ti);
    }


    /*! \fn  public void createLevel(BufferedImage level)
        \brief Creaza nivel
    */
    public void createLevel(BufferedImage level){
       /* for(int i=0;i<Game.WIDTH*Game.SCALE/64+1;i++) {
            addTile(new Wall(i * 64, Game.HEIGHT * Game.SCALE - 64, 64, 64, true, Id.wall, this));
            if (i != 0 && i != 1 && i!=15 && i!=16 && i != 17)
                addTile(new Wall(i * 64, 300, 64, 64, true, Id.wall, this));
        }*/

       int width=level.getWidth();
       int height=level.getHeight();
       for(int y=0;y<height;y++)
       {
            for(int x=0;x<width;x++){
                int pixel=level.getRGB(x,y);

                int red=(pixel >> 16) & 0xff;
                int green=(pixel >> 8) & 0xff;
                int blue=(pixel) & 0xff;
                //https://j.hn/lab/colorpicker/
                if(red==0&&green==0&&blue==0) addTile(new Wall(x*64,y*64,64,64,true,Id.wall,this));
                if(red==0&&green==0&&blue==255) addEntity(new Player(x*64,y*64,64,64,Id.player,this));
                if(red==0&&green==255&&blue==255) addEntity(new Goomba(x*64,y*64,64,64,Id.goomba,this));
                if(red==84&&green==0&&blue==102) addEntity(new Mushroom(x*64,y*64,64,64,Id.mushroom,this,0));//540066 rosie
                if(red==57&&green==112&&blue==0) addEntity(new Mushroom(x*64,y*64,64,64,Id.mushroom,this,1));//397000 verde
                if(red==142&&green==142&&blue==0) addTile(new PowerUpBlock(x*64,y*64,64,64,true,Id.powerUp,this,Game.mushroom,0));
                if(red==255&&green==255&&blue==0) addTile(new PowerUpBlock(x*64,y*64,64,64,true,Id.powerUp,this,Game.lifeMushroom,1));
                if(red==0&&green==255&&blue==0) addTile(new Pipe(x*64,y*64,64,64*15,true,Id.pipe,this,2));
                if(red==255&&green==204&&blue==0) addTile(new Pipe(x*64,y*64,64,64*15,true,Id.pipe,this,0));//pentru GIMP: FFCC00
                if(red==255&&green==0&&blue==255) addTile(new Coin(x*64,y*64,64,64,true,Id.coin,this)) ;
                if(red==196&&green==0&&blue==42) addTile(new Flag(x*64,y*64,64,64*5,true,Id.flag,this));
            }
       }
    }

    //stergem toate dalele
    public void clearLevel(){
        entity.clear();
        tile.clear();
    }

}
