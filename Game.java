import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

/** 
 * Clase encargada de la simulación de todo el juego.
 * <p>
 * En esta pantalla, se realiza la simulación de todo el juego. Eso incluye
 * crear al Jugador, agregar Enemigos al mundo, mostrar en pantalla la interfaz
 * del juego y mantener el juego en marcha hasta que el Jugador se quede sin vidas.
 * 
 * @author Moctezuma Luna Alejandro
 */
public class Game extends World
{   
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
    
    // Asteroide
    private final GreenfootImage asteroid = new GreenfootImage("Asteroid.png");
    
    // Explosion
    private final GreenfootImage explosion = new GreenfootImage("Explosion.png");
    
    // Musica de fondo
    private final GreenfootSound music = new GreenfootSound("Music.mp3");
    
    // Musica de perder
    private final GreenfootSound lose_music = new GreenfootSound("Game_Over.mp3");
    
    // Constructor princial, se inicializan valores del mundo y del juego
    public Game()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        // Se crea el jugador
        player = new Player(300, 300, 3, 0, 1, player_frente, player_izq, player_der, explosion);
        
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
        this.addObject(player, player.getPositionX(), player.getPositionY());
        this.addObject(hud, 80, 30);
        music.playLoop();
    }
    
    // Metodo principal del juego, hace cambios en el mundo mientras esta corriendo el juego
    public void act(){
        int salir = 1, flag = 0;
        // Se realiza mientras el juego este en ejecución
        if(executeGame)
        {   
            // Se dibuja la interfaz del juego
            hud();
            
            // Si el jugador ya no tiene vidas termina el juego
            if(player.getHearts() == 0)
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
            music.stop();
            if(end_timer == 0){
                lose_music.play();
            }
            end_timer++;
            if(end_timer == END_TIMER_LIMIT){
                Greenfoot.setWorld(new TitleScreen());
            }
        }
    }
    
    // Se dibuja la interfaz en la pantalla
    public void hud(){
        hud.actualiceScore(player);
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
        List<Enemy> enemies = this.getObjects(Enemy.class);
        // Si ya no quedan enemigos se reinician los tiempos
        // y se genera una nueva oleada.
        if (enemies.size() == 0)
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
}