import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.lang.Math;
import java.time.*;
import java.io.*;

/** 
 * Clase del Juego
 * Se llaman los metodos para que se ejecuten e interactuen entre si
 */
public class Game extends World
{   
    private long time_sec;
    
    Instant time_start = Instant.now();
    
    Instant time_finish;
    
    // Genera numeros aleatorios
    Random rand = new Random();
    
    // Mantiene el juego corriendo
    private boolean executeGame;
    
    // Se usa cuando se eliminan a todos los enemigos en pantalla
    private boolean killEnemies;
    
    // Indica una neva oleada de enemigos
    private boolean newWave;
    
    // Se generan nuevos enemiigos en el mapa
    private boolean generateNewEnemies;
    
    // Se agrega un enemigo al mapa
    private boolean generateEnemy;
    
    // Tipo de enemigo (Dispara o Colisiona)
    private int type;
    
    // Clase de enemigo
    private int klass;
    
    // Cantidad de enemigos que se generaran
    private int size;
    
    // Posicion en X donde se generaran los enemigos
    private int pos;
    
    // Bandera de creacion del Boss
    boolean boss_flag = true;
    
    // Direccion en que se moveran los enemigos
    private int dir;
    
    // Tiempo luego de que se eliminaron a todos los enemigos
    private int empty_time;
    
    // Tiempo luego de que se generara un enemigo
    private int gen_time;
    
    // Tiempo antes de cambiar de pantalla al morir
    private int end_timer = 0;
    
    // Tiempo limite antes de que cambie de pantalla al morir
    private final int END_TIMER_LIMIT = 350;
    
    // Tiempo limite luego de que se eliminaron los enemigos
    private final int EMPTY_TIME_LIMIT = 30;
    
    // Tiempo limite luego de que se genero un enemigo
    private final int GEN_TIME_LIMIT = 12;
    
    // Datos del jugador
    private Player player;
    
    // Datos del boss
    private Boss boss;
    
    // Datos del HUD
    private Interface hud;
    
    // Imagen jugador de frente
    private final GreenfootImage player_frente = new GreenfootImage("Player.png");
    
    // Imagen jugador izquierda
    private final GreenfootImage player_izq = new GreenfootImage("Player_Izq.png");
    
    // Imagen jugador derecha
    private final GreenfootImage player_der = new GreenfootImage("Player_Der.png");
    
    // Imagen enemigo OneOne
    private final GreenfootImage enemy_proy1 = new GreenfootImage("Enemy_Proy1.png");
    
    // Imagen enemigo OneTwo
    private final GreenfootImage enemy_proy2 = new GreenfootImage("Enemy_Proy2.png");
    
    // Star
    private final GreenfootImage star = new GreenfootImage("Star.png");
    
    // Boss
    private final GreenfootImage boss_image = new GreenfootImage("Boss.gif");
    
    // Asteroide
    private final GreenfootImage asteroid = new GreenfootImage("Asteroid.png");
    
    // Explosion
    private final GreenfootImage explosion = new GreenfootImage("Explosion.png");
    
    // Explosion boss
    private final GreenfootImage explosion_boss = new GreenfootImage("Explosion_Boss.png");
    
    // Musica de fondo
    private final GreenfootSound music = new GreenfootSound("Music.mp3");
    
    // Musica de perder
    private final GreenfootSound lose_music = new GreenfootSound("Game_Over.mp3");
    
    // Musica de ganar
    private final GreenfootSound victory_music = new GreenfootSound("Victory.mp3");
    
    // Constructor princial, se inicializan valores del mundo y del juego
    public Game()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        // Se crean el jugador y el Boss
        player = new Player(300, 300, 3, 0, 1, player_frente, player_izq, player_der, explosion);
        boss = new Boss(300, 30, boss_image, explosion_boss);
        
        // Se crea la interfaz
        hud = new Interface();
        
        // Se encarga de mantener el juego en ejecución
        executeGame = true;
        
        // Parametros de control de mundo
        killEnemies = false;
        newWave = false;
        generateNewEnemies = false;
        generateEnemy = false;
        
        // Conteo de tiempos
        empty_time = ( - (EMPTY_TIME_LIMIT)) * 3;
        gen_time = 0;
        
        // Características de los enemigos que seran creados
        type = 0;
        klass = 0;
        size = 0;
        pos = 0;
        dir = 0;
        
        // Inicializando Juego
        setPaintOrder(Player.class, Proyectile.class, Enemy.class, Interface.class);
        setBackground("Fondo.png");
        player.name = Greenfoot.ask("Introduce Tu Nombre: ");
        this.addObject(player, player.getPositionX(), player.getPositionY());
        this.addObject(hud, 55, 28);
        music.setVolume(30);
        music.playLoop();
    }
    
    // Metodo principal del juego, hace cambios en el mundo mientras esta corriendo el juego
    public void act(){
        // Se realiza mientras el juego este en ejecución
        if(executeGame)
        {   
            // Se dibuja la interfaz del juego
            hud();
            
            // Si el jugador ya no tiene vidas termina el juego
            if(player.getHearts() == 0)
                executeGame = false;
                
            if(boss.getDeath() == true)
                executeGame = false;

            // Si el jugador muere, se eliminan a todos los enemigos en pantalla
            if(executeGame && player.getDeath())
                game_over();
                
            // Se encarga de verificar los ciclos contados en el juego
            if(executeGame && !player.getDeath())
                verifyTimes();
                
            // Si el jugador todavía tiene vidas se vuelve a agregar a los enemigos en pantalla
            if (executeGame && !player.getDeath() && killEnemies)
            {
                boss.setCreated(false);
                newWave = false;
                generateNewEnemies = false;
                generateEnemy = false;
                empty_time = 0;
                gen_time = GEN_TIME_LIMIT;
                killEnemies = false;
            }
            
            // Se definen las especificaciones de un nuevo enemigo
            if (executeGame && !player.getDeath())
                defineNewEnemies();
            
            // Se empiezan a crear y agregar nuevos enemigos en pantalla
            if (executeGame && !player.getDeath() && generateNewEnemies)
                createEnemy();
                
            // Se verifica que todos los enemigos generados esten muertos
            if (executeGame && generateNewEnemies && !player.getDeath() && size == 0)
                verifyEnemies();
        }
        // El juego termina
        else
        {
            if(boss.getDeath()){
                music.stop();
                if(end_timer == 100){
                    victory_music.play();
                }
                end_timer++;
                if(end_timer == END_TIMER_LIMIT){
                    Instant time_finish = Instant.now();
                    long time_record = Duration.between(time_start, time_finish).getSeconds();
                    saveRecords(player.name, time_record);
                    Greenfoot.setWorld(new TitleScreen());
                }
            }
            else
            {
                music.stop();
                if(end_timer == 0){
                    lose_music.play();
                }
                end_timer++;
                if(end_timer == END_TIMER_LIMIT){
                    Instant time_finish = Instant.now();
                    System.out.println(time_finish);
                    System.out.println(time_start);
                    Duration time_record = Duration.between(time_start, time_finish);
                    time_sec = time_record.getSeconds();
                    saveRecords(player.name, time_sec);
                    Greenfoot.setWorld(new TitleScreen());
                }
            }
            
        }
    }
    
    // Se dibuja la interfaz en la pantalla
    public void hud(){
        hud.updateScore(player);
        hud.showScore();
    }
    
    // Se generan enemigos
    public void createEnemy()
    {
        // Se crean enemigos mientras que las entidades en pantalla no excedan los limites definidos
        if (generateEnemy && size != 0)
        {
            addEnemies();
            generateEnemy = false;
            size--;
        }
    }
    
    // Método con el que se deciden los datos de los nuevos enemigos a generar. Este método es
    // utilizado el momento en que todos los Enemigos de una oleada hayan muerto. El método
    // define que tipo y clase de enemigo se utilizará, además de la cantidad de Enemigos por
    // generar, así como su posición y dirección (dependiendo del caso).
    public void defineNewEnemies()
    {
        // Solo se definen una vez, después de que el jugador mata
        // a todos los enemigos en pantalla
        if (!newWave)
        {
            // Define que tipo de enemigo aparecera
            this.type = rand.nextInt(2) + 1;
            // Elige entre Enemigo de colision o enemigo de disparo
            this.klass = rand.nextInt(10) + 1;
            switch(player.getPower())
            {
                // Power 1
                case 1: // Enemigo Colision
                        if (this.type == 1)                           //100%: Enemigo 01
                        {
                            this.klass = 1;
                            this.size = 2;
                            this.dir = rand.nextInt(2);
                            // Izquierda
                            if (this.dir == 0)
                                this.pos = rand.nextInt(100) + 100;
                            // Derecha
                            else
                                this.pos = rand.nextInt(100) + 400;
                        }
                        // Enemigo Disparo
                        else                                          //100%: Enemigo 01
                        {
                            this.klass = 1;
                            this.size = 15;
                            this.dir = rand.nextInt(2);
                            // Izquierda
                            if (this.dir == 0)
                                this.pos = rand.nextInt(100) + 100;
                            // Derecha
                            else
                                this.pos = rand.nextInt(100) + 400;
                        }
                        break;
                // Poder 2
                case 2: // Enemigo colision
                        if (this.type == 1)
                        {
                            if (this.klass <= 7)                       //70%: Enemigo 01
                            {
                                this.klass = 1;
                                this.size = 3;
                                this.dir = rand.nextInt(2);
                                // Izquierda
                                if (this.dir == 0)
                                    this.pos = rand.nextInt(100) + 100;
                                // Derecha
                                else
                                    this.pos = rand.nextInt(100) + 400;
                            }
                            else                                       //30%: Enemigo 02
                            {
                                this.klass = 2;
                                this.size = 8;
                                this.pos = rand.nextInt(200) + 200;
                            }
                        }
                        // Enemigo disparo
                        else
                        {
                            if (this.klass <= 7)                       //70%: Enemigo 01
                            {
                                this.klass = 1;
                                this.size = 15;
                                this.dir = rand.nextInt(2);
                                // Izquierda
                                if (this.dir == 0)
                                    this.pos = rand.nextInt(100) + 100;
                                // Derecha
                                else
                                    this.pos = rand.nextInt(100) + 400;
                            }
                            else                                       //30%: Enemigo 02
                            {
                                this.klass = 2;
                                this.size = 5;
                            }
                        }
                        break;
                // Poder 3
                case 3: // Enemigo Colision
                        if (this.type == 1)
                        {
                            if (this.klass <= 5)                        //50%: Enemigo 01
                            {
                                this.klass = 1;
                                this.size = 3;
                                this.dir = rand.nextInt(2);
                                // Izquierda
                                if (this.dir == 0)
                                    this.pos = rand.nextInt(100) + 100;
                                // Derecha
                                else
                                    this.pos = rand.nextInt(400) + 100;
                            }
                            else                                         //50%: Enemigo 02
                            {
                                this.klass = 2;
                                this.size = 8;
                                this.pos = rand.nextInt(200) + 200;
                            }
                        }
                        // Enemigo disparo
                        else
                        {
                            if (this.klass <= 5)                         //50%: Enemigo 01
                            {
                                this.klass = 1;
                                this.size = 15;
                                this.dir = rand.nextInt(2);
                                // Izquierda
                                if (this.dir == 0)
                                    this.pos = rand.nextInt(100) + 100;
                                // Derecha
                                else
                                    this.pos = rand.nextInt(400) + 100;
                            }
                            else                                         //50%: Enemigo 02
                            {
                                this.klass = 2;
                                this.size = 6;
                            }
                        }
                        break;
                // Poder 4
                case 4: // Enemigo Colision
                        if (this.type == 1)
                        {
                            if (this.klass <= 2)                         //20%: Enemigo 02
                            {
                                this.klass = 1;
                                this.size = 4;
                                this.dir = rand.nextInt(2);
                                // Izquierda
                                if (this.dir == 0)
                                    this.pos = rand.nextInt(100) + 100;
                                // Derecha
                                else
                                    this.pos = rand.nextInt(400) + 100;
                            }
                            else                                         //80%: Enemigo 03
                            {
                                this.klass = 2;
                                this.size = 8;
                                this.pos = rand.nextInt(200) + 200;
                            }
                        }
                        // Enemigo disparo
                        else
                        {
                            if (this.klass <= 2)                         //20%: Enemigo 01
                            {
                                this.klass = 1;
                                this.size = 15;
                                this.dir = rand.nextInt(2);
                                // Izquierda
                                if (this.dir == 0)
                                    this.pos = rand.nextInt(100) + 100;
                                // Derecha
                                else
                                    this.pos = rand.nextInt(100) + 400;
                            }
                            else                                         //80%: Enemigo 02
                            {
                                this.klass = 2;
                                this.size = 10;
                            }
                        }
                        break;
                // Poder 5
                case 5: // Boss
                        if (boss.getCreated() == false){
                            addBoss();
                            boss.setCreated(true);
                        }
                        
                        // Enemigo colision
                        if (this.type == 1)
                        {
                            if (this.klass <= 4)                         //40%: Enemigo 01
                            {
                                this.klass = 1;
                                this.size = 4;
                                this.dir = rand.nextInt(2);
                                // Izquierda
                                if (this.dir == 0)
                                    this.pos = rand.nextInt(100) + 100;
                                // Derecha
                                else
                                    this.pos = rand.nextInt(400) + 100;
                            }
                            else                                         //60%: Enemigo 02
                            {
                                this.klass = 2;
                                this.size = 8;
                                this.pos = rand.nextInt(200) + 200;
                            }
                        }
                        // Enemigo disparo
                        else
                        {
                            if (this.klass <= 4)                         //40%: Enemigo 01
                            {
                                this.klass = 1;
                                this.size = 15;
                                this.dir = rand.nextInt(2);
                                // Izquierda
                                if (this.dir == 0)
                                    this.pos = rand.nextInt(100) + 100;
                                // Derecha
                                else
                                    this.pos = rand.nextInt(100) + 400;
                            }
                            else                                         //60%: Enemigo 02
                            {
                                this.klass = 2;
                                this.size = 10;
                            }
                        }
                        break;
            }
            // La oleada fue definida exitosamente
            newWave = true;
        }
    }
    
    // Se agregan los nuevos enemigos al mundo
    public void addEnemies()
    {
        switch(type)
        {
            // Colision
            case 1: switch(klass)
                    {
                        case 1: addStar(pos);    // Star
                                break;
                        case 2: addAsteroid(pos);    // Asteroid
                                break;
                    }
                    break;
            // Disparo
            case 2: switch(klass)
                    {
                        case 1: addEnemyDisp1(pos);
                                break;
                        case 2: this.pos = rand.nextInt(400) + 100; 
                                addEnemyDisp2(pos);
                                break;
                    }
                    break;
        }
    }
    
    // Se agrega el enemigo estrella
    public void addStar(int pos)
    {
        EnemyOneOne colision1 = new EnemyOneOne(pos, 0, 1, dir, star, explosion);
        this.addObject(colision1, colision1.getPositionX(), colision1.getPositionY());
    }
    
    // Se agrega el enemigo asteroide
    public void addAsteroid(int pos)
    {
        EnemyOneTwo colision2 = new EnemyOneTwo(pos, 0, asteroid, explosion);
        this.addObject(colision2, colision2.getPositionX(), colision2.getPositionY());
    }
    
    // Se agrega el enemigo disp_01
    public void addEnemyDisp1(int pos)
    {
        EnemyTwoOne disp1 = new EnemyTwoOne(pos, 10, dir, enemy_proy1, explosion);
        this.addObject(disp1, disp1.getPositionX(), disp1.getPositionY());
    }
    
    // Se agrega el enemigo disp_02
    public void addEnemyDisp2(int pos)
    {
        EnemyTwoTwo disp2 = new EnemyTwoTwo(pos, 10, enemy_proy2, explosion);
        this.addObject(disp2, disp2.getPositionX(), disp2.getPositionY());
    }
    
    // Se agrega el boss
    public void addBoss()
    {
        this.addObject(boss, boss.getPositionX(), boss.getPositionY());
    }
    
    // Se verifican tiempos
    public void verifyTimes(){
        // Si el escenario esta libre de enemigos se contea el tiempo
        if (empty_time != EMPTY_TIME_LIMIT)
        {
            empty_time++;
            // Despues de cierto tiempo se generan nuevos enemigos
            if (empty_time == EMPTY_TIME_LIMIT)
                generateNewEnemies = true;
        }
        // Generación de enemigos
        else
        if (empty_time == EMPTY_TIME_LIMIT)
        {
            // Cuando pasa el suficiente tiempo se genera un nuevo enemigo
            if (generateNewEnemies && gen_time == GEN_TIME_LIMIT)
            {
                generateEnemy = true;
                gen_time = 0;
            }
            // Si no ha pasado el tiempo suficiente se sigue contando
            else
            if (generateNewEnemies)
                gen_time++;
        }
    }
    
    // Se verifica si aun quedan enemigos vivos
    public void verifyEnemies()
    {
        // Se obtiene la lista de todos los enemigos
        List<EnemyOne> enemiesOne = this.getObjects(EnemyOne.class);
        List<EnemyTwo> enemiesTwo = this.getObjects(EnemyTwo.class);
        // Si ya no quedan enemigos se reinician los tiempos
        // y se genera una nueva oleada.
        if (enemiesOne.size() == 0 && enemiesTwo.size() == 0)
        {
            newWave = false;
            generateNewEnemies = false;
            generateEnemy = false;
            empty_time = 0;
            gen_time = 0;
        }
    }
    
    // Se destruyen todas las entidades cuando muere el jugador
    public void game_over()
    {
        // Se activa el parametro de control de matar enemigos
        killEnemies = true;
        
        // Se obtienen todos los enemigos y proyectiles
        List<Enemy> enemies = this.getObjects(Enemy.class);
        List<Proyectile> proyectiles = this.getObjects(Proyectile.class);
        
        // Se eliminen los enemigos y proyectiles
        this.removeObjects(enemies);
        this.removeObjects(proyectiles);
    }
    
    public void saveRecords(String name, long time){
        int check_time;
        FileWriter filewriter = null;
        try{
            filewriter = new FileWriter("Records.txt", true);
            BufferedWriter bfwriter = new BufferedWriter(filewriter);
            bfwriter.write(name + "\t," + time + "\n");
            bfwriter.close();
        }
        catch(IOException e){
        }
        finally{
            if(filewriter != null){
                try{
                    filewriter.close();
                } catch(IOException e){
                }
            }
        }
    }
}
