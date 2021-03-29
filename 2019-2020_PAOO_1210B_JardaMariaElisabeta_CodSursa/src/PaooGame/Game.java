package PaooGame;

import PaooGame.Graphics.Sprite;
import PaooGame.Graphics.SpriteSheet;
import PaooGame.Graphics.gui.Launcher;
import PaooGame.Input.KeyInput;
import PaooGame.Input.MouseInput;
import PaooGame.entity.Entity;
import PaooGame.entity.Player;
import PaooGame.tile.Wall;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie).
    */

public class Game extends Canvas implements Runnable{
    /*!< latimea ferestrei in pixeli*/
    public static final int WIDTH=320;
    /*!< inaltimea ferestrei in pixeli*/
    public static final int HEIGHT=180;
    /*!< modul de scalare*/
    public static final int SCALE=4;
    /*!< titlul ferestrei*/
    public static final String TITLE="BomberWoman";

    private Thread  thread;                 /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private boolean running=false;          /*!< Flag ce arata starea firului de executie.*/

    private static BufferedImage[] levels;

    public static BufferedImage background;

    private static int playerX,playerY;
    private static int level=0;

    public static int coins=0;
    public static int lives=5;
    //variabila folosita pentru a arata ecranul cu vietile ramase
    public static int deathScreenTime=0;

    public static boolean showDeathScreen=true;
    public static boolean gameOver=false;
    public static boolean playing=false;

    public static Handler handler;
    public static SpriteSheet sheet,sheet2,sheet3,sheet4,sheet5,sheet6,sheet7;
    public static Camera cam;
    public static Launcher launcher;
    public static MouseInput mouse;

    public static Sprite grass;
    public static Sprite powerUp;
    public static Sprite usedPowerUp;

    public static Sprite mushroom;
    public static Sprite lifeMushroom;
    public static Sprite coin;

    public static Sprite[] player;
    public static Sprite[] goomba;
    public static Sprite[] flag;

    /*! \fn public Game()
        \brief Constructor de initializare al clasei Game.
     */
    public Game()
    {
        Dimension size=new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
        ///Seteaza dimensiunea "preferata"/implicita a obiectului de tip canvas.
        /// Functia primeste ca parametru un obiect de tip Dimension ca incapsuleaza
        /// doua proprietati: latime si inaltime. Cum acest obiect nu exista
        /// a fost creat unul si dat ca parametru.
        setPreferredSize(size);
        /// Avand in vedere ca elementele unei ferestre pot fi scalate atunci cand
        /// fereastra este redimensionata
        setMaximumSize(size);
        setMinimumSize(size);
    }


    /*! \fn private void init()
      \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

      Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

   */
    private void init()
    {
        handler=new Handler();
        sheet=new SpriteSheet("/player/sheet_ea.png");
        sheet2=new SpriteSheet("/tile.png");
        sheet3=new SpriteSheet("/mushroom.png");
        sheet4=new SpriteSheet("/Goomba.png");
        sheet5=new SpriteSheet("/powerUp.png");
        sheet6=new SpriteSheet("/coin.png");
        sheet7=new SpriteSheet("/finishFlag.png");
        cam=new Camera();
        launcher=new Launcher();
        mouse=new MouseInput();

        addKeyListener(new KeyInput());
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        ///Se incarca toate elementele grafice (dale)
        grass=new Sprite(sheet2,1,1);
        powerUp=new Sprite(sheet5,2,1);
        usedPowerUp=new Sprite(sheet5,5,1);

        mushroom=new Sprite(sheet3,1,1);
        lifeMushroom=new Sprite(sheet3,2,1);
        coin=new Sprite(sheet6,1,1);

        player= new Sprite[10];
        goomba=new Sprite[10];
        flag=new Sprite[3];

        levels=new BufferedImage[2];

        for(int i=0;i<player.length;i++){
            player[i]=new Sprite(sheet,i+1,1);
        }

        for(int i=0;i<goomba.length;i++){
            goomba[i]=new Sprite(sheet4,i+1,1);
        }

        for(int i=0;i<flag.length;i++){
            flag[i]=new Sprite(sheet7,i+1,1);
        }

        try {
            levels[0]=ImageIO.read(getClass().getResource("/level.png"));
            levels[1]=ImageIO.read(getClass().getResource("/level2.png"));
            background=ImageIO.read(getClass().getResource("/bg1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*! \fn public synchronized void start()
       \brief Creaza si starteaza firul separat de executie (thread).

       Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
    */
    private synchronized void start(){
        /// Thread-ul este creat si pornit deja
        if(running) return;
        /// Se actualizeaza flagul de stare a threadului
        running =true;
        /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
        /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
        thread=new Thread(this,"Thread");
        /// Threadul creat este lansat in executie (va executa metoda run())
        thread.start();

    }

    /*! \fn public synchronized void stop()
       \brief Opreste executie thread-ului.

       Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
    */
    private synchronized void stop()
    {
        /// Thread-ul este oprit deja.
        if(!running) return;
        /// Actualizare stare thread
        running =false;
        /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
        try {
            /// Metoda join() pune un thread in asteptare pana cand un altul isi termina executie.
            /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
            thread.join();
        }catch(InterruptedException e){
            /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
            e.printStackTrace();
        }
    }

    /*! \fn public void run()
           \brief Functia ce va rula in thread-ul creat.

           Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
        */
    @Override
    public void run() {
        /// Initializeaza obiectul game
        init();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (running)
        {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;
            }
        }
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Draw(){
        BufferStrategy bs=getBufferStrategy(); /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
        /// Verific daca buffer strategy a fost construit sau nu
        if(bs==null)
        {
            /// Se construieste triplu buffer
            createBufferStrategy(3);
            return;
        }
        /// Se obtine contextul grafic curent in care se poate desena.
        Graphics g=bs.getDrawGraphics();

        if(!showDeathScreen) {
            //setez imaginea din background
            g.drawImage(background,0,0,getWidth(),getHeight(),null);

        }else{
            g.setColor(Color.BLACK);
            g.fillRect(0,0,getWidth(),getHeight());

            g.setColor(Color.WHITE);
            g.setFont(new Font("COURIER",Font.BOLD,35));
            if(!gameOver){
                g.drawImage(Game.player[0].getBufferdImage(),500,300,110,110,null);
                g.drawString("x"+lives,580,405);
            }else{
                g.drawString("Game over. :(",500,370);
            }
        }

        if(playing) g.translate(cam.getX(),cam.getY());
        if(!showDeathScreen&&playing) handler.Draw(g);
        else if(!playing) launcher.Draw(g);

        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();

        /// Se afiseaza pe ecran
        bs.show();
    }
    /*! \fn private void Update()
          \brief Actualizeaza starea elementelor din joc.

          Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
       */
    private void Update(){
        ///Actualizez starea curenta a jocului
        if(playing) handler.Update();

        ///In acest for se actualizeaza camera jocului in functie de player
        for(int i=0;i<handler.entity.size();i++){
            Entity e=handler.entity.get(i);
            if(e.getId()==Id.player){
                if(!e.goingDownPipe) cam.Update(e);
            }
        }

        ///actualizam numarul aparitiilor ecranului in care sunt afisate vietile ramase
        if(showDeathScreen&&!gameOver&&playing) deathScreenTime++;
        if(deathScreenTime>=180){
            showDeathScreen=false;
            deathScreenTime=0;
            handler.clearLevel();
            handler.createLevel(levels[level]);
           /* if(!gameOver){
                showDeathScreen=false;
                deathScreenTime=0;
                handler.clearLevel();
                handler.createLevel(image);
            }else if(gameOver){
                showDeathScreen=false;
                deathScreenTime=0;
                playing=false;
                gameOver=false;
            }*/

        }
    }

    public static Rectangle getVisibleArea(){
        for(int i=0;i<handler.entity.size();i++){
            Entity e=handler.entity.get(i);
            if(e.getId()==Id.player) {
                if(!e.goingDownPipe){
                    playerX=e.getX();
                    playerY=e.getY();

                    return new Rectangle(playerX-(getFrameWidth()/2-5),playerY-(getFrameHeight()/2-5),getFrameWidth()+10,getFrameHeight()+10);
                }
            }
        }
        return new Rectangle(playerX-(getFrameWidth()/2-5),playerY-(getFrameHeight()/2-5),getFrameWidth()+10,getFrameHeight()+10);
    }

    /*! \fn public static void switchLevel()
       \brief Sterge level-ul curent si il creeaza pe urmatorul.
    */
    public static void switchLevel(){
        Game.level++;

        handler.clearLevel();;
        handler.createLevel(levels[level]);
    }

    /*! \fn public static int getFrameWidth()
       \brief Returneaza latimea ferestrei.
    */
    public static int getFrameWidth(){
        return WIDTH*SCALE;
    }

    /*! \fn public static int getFrameHeight()
      \brief Returneaza inaltimea ferestrei.
   */
    public static int getFrameHeight(){
        return HEIGHT*SCALE;
    }


    public static void main(String[] args)
    {
        Game game=new Game();
        JFrame frame=new JFrame(TITLE);
        /// Avand in vedere ca obiectul de tip canvas, proaspat creat, nu este automat
        /// adaugat in fereastra trebuie apelata metoda add
        frame.add(game);
        /// Urmatorul apel de functie are ca scop eventuala redimensionare a ferestrei
        /// ca tot ce contine sa poate fi afisat complet
        frame.pack();
        ///constrangem deocamdata jucatorul sa se joace in fereastra stabilitata
        frame.setResizable(false);
        ///Apel pentru ca fereastra sa apara in centrul ecranului.
        frame.setLocationRelativeTo(null);
        /// Operatia de inchidere (fereastra sa poata fi inchisa atunci cand
        /// este apasat butonul x din dreapta sus al ferestrei). Totodata acest
        /// lucru garanteaza ca nu doar fereastra va fi inchisa ci intregul
        /// program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /// Implicit o fereastra cand este creata nu este vizibila motiv pentru
        /// care trebuie setata aceasta proprietate
        frame.setVisible(true);
        game.start();

    }
}
