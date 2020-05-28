import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Clase del Jugador
 * Personaje del cual se tiene control
 */
public class Player extends Entity
{
    // Indica el nivel de poder del jugador
    private int power;

    // Indica el puntaje del jugador
    private int score;

    // Indica las vidas del jugador
    private int hearts;
    
    // Indica el estado del jugador
    private boolean death;
    
    // Proyectil 1
    private final GreenfootImage proy1 = new GreenfootImage("Proy1.png");
    
    // Proyectil 2
    private final GreenfootImage proy2 = new GreenfootImage("Proy2.png");
    
    // Proyectil 3
    private final GreenfootImage proy3 = new GreenfootImage("Proy3.png");
    
    // Sonido cuando el jugador es dañado
    private final GreenfootSound damage_player = new GreenfootSound("Damage_Player.mp3");
    
    // Tiempo entre disparos
    private int shot_time;
    
    // Tiempo desde que el jugador se movio a la izquierda
    private int left_time;
    
    // Tiempo desde que el jugador se movio a la derecha
    private int right_time;
    
    // Tiempo desde que el jugador dejo de moverse
    private int static_time;
    
    // Numero maximo de disparos a contar
    private final int SHOT_LIMIT = 6;
    
    // Numero maximo entre cambio de fotogramas
    private final int PHOTO_LIMIT = 3;
    
    // Numero maximo en que la umagen es la mismo
    private final int STATIC_LIMIT = 15;
    
    // Tiempo maximo antes de explotar
    private final int DEATH_TIME = 60;
    
    // Metodo principal de jugador, se llaman los metodos que hacen que funcione jugador
    public void act() 
    {
        // El jugador esta vivo
        if (!getDeath())
        {
            interact();
            actualicePosition();
            animation();
            increasePower();
        }
        // El jugador esta muerto
        else
            explode();
            verifyTimes();
    }
    
    // Constructor con solo los iniciadores de tiempo
    public Player(){
        this.shot_time = SHOT_LIMIT;
        this.static_time = STATIC_LIMIT;
        this.left_time = 0;
        this.right_time = 0;
        this.death = false;
    }
    
    // Constructor principal y completo de jugador
    public Player(int newX, int newY, int h, int sc, int pw, GreenfootImage... keyFrames)
    {
        //Posiciones iniciales de jugador
        this.setPositionX(newX);
        this.setPositionY(newY);
        
        //Atributos propios de jugador
        this.hearts = h;
        this.score = sc;
        this.power = pw;
        this.death = false;
        
        //Velocidad de entidad jugador
        this.setVelocityX(0);
        this.setVelocityY(0);
        
        //Fotogramas de entidad jugador
        this.buildPhotos(keyFrames);
        this.setNumPhotos(keyFrames.length);
        this.actualicePhoto(0);
        
        //Inicialización de los tiempos
        this.shot_time = SHOT_LIMIT;
        this.static_time = STATIC_LIMIT;
        this.left_time = 0;
        this.right_time = 0;
    }
    
    // Se definen las imagenes del jugador
    @Override
    public void buildPhotos(GreenfootImage... keyFrames){
        super.buildPhotos(keyFrames);
    }

    // Se obtiene la imagen actual del jugador
    @Override
    public int getPhotoActual(){
        return super.getPhotoActual();
    }
   
    // Se obtiene el numero total de fotogramas del jugador
    @Override
    public int getNumPhotos(){
        return super.getNumPhotos();
    }
    
    // Se obtiene la posicion en X del jugador
    @Override
    public int getX(){
        return super.getX();
    }

    // Se obtiene la posicion en Y del jugador
    @Override
    public int getY(){
        return super.getY();
    }
    
    // Se obtiene la posicion inicial en X del jugador
    @Override
    public int getPositionX(){
        return super.getPositionX();
    }
   
    // Se obtiene la posicion inicial en Y del jugador
    @Override
    public int getPositionY(){
        return super.getPositionY();
    }

    // Se obtiene el nivel de poder actual del jugador
    public int getPower(){
        return this.power;
    }

    // Se obtiene el puntaje actual del jugador
    public int getScore(){
        return this.score;
    }
    
    // Se obtiene el numero de vidas restantes del jugador
    public int getHearts(){
        return this.hearts;
    }
    
    // Se obtiene el estado actual del jugador
    public boolean getDeath(){
        return this.death;
    }
    
    // Se obtiene la velocidad en X del jugador
    @Override
    public int getVelocityX(){
        return super.getVelocityX();
    }
    
    // Se obtiene la velocidad en Y del jugador
    @Override
    public int getVelocityY(){
        return super.getVelocityY();
    }
    
    // Se obtiene el tiempo transcurrido desde el ultimo disparo
    public int getShotTime(){
        return this.shot_time;
    }
    
    // Se obtiene el tiempo transcurrido desde que el jugador se movio a la izquierda
    public int getLeftTime(){
        return this.left_time;
    }
    
    // Se obtiene el tiempo transcurrido desde que el jugador se movio a la derecha
    public int getRightTime(){
        return this.right_time;
    }
    
    // Se obtiene el tiempo transcurrido desde que el jugador se dejo de mover
    public int getStaticTime(){
        return this.static_time;
    }
    
    // Se obtiene el tiempo limite antes de la explosion del jugador
    public int getDeathTime(){
        return this.DEATH_TIME;
    }

    // Se define la posicion del jugador
    @Override
    public void setLocation(int newX, int newY){
        super.setLocation(newX, newY);
    }
    
    // Se define la posicion en X del jugador
    @Override
    public void setX(int value){
        super.setX(value);
    }

    // Se define la posicion en Y del jugador
    @Override
    public void setY(int value){
        super.setY(value);
    }
    
    // Se define la posicion inicial en X del jugador
    @Override
    public void setPositionX(int value){
        super.setPositionX(value);
    }
   
    // Se define la posicion inicial en Y del jugador
    @Override
    public void setPositionY(int value){
        super.setPositionY(value);
    }

    // Se degine el nivel de poder actual del jugador
    public void setPower(int value){
        this.power= value;
    }

    // Se define el puntaje actual del jugador
    public void setScore(int value){
        this.score = value;
    }

    // Se define el numero de vidas restantes del jugador
    public void setHearts(int value){
        this.hearts = value;
    }
    
    // Se define el estado actual del jugador
    public void setDeath(boolean value){
        if (value)
        {
            this.death = value;
            this.setStaticTime(0);
        }
        else
            this.death = value;
    }
    
    // Se define la velocidad en X del jugador
    @Override
    public void setVelocityX(int value){
        super.setVelocityX(value);
    }
    
    // Se define la velocidad en Y del jugador
    @Override
    public void setVelocityY(int value){
        super.setVelocityY(value);
    }
        
    // Se define el tiempo transcurrido desde el ultimo disparo
    public void setShotTime(int value){
        this.shot_time = value;
    }
    
    // Se define el tiempo transcurrido desde que el jugador se movio a la izquierda
    public void setLeftTime(int value){
        this.left_time = value;
    }
    
    // Se define el tiempo transcurrido desde que el jugador se movio a la derecha
    public void setRightTime(int value){
        this.right_time = value;
    }
    
    // Se define el tiempo transcurrido desde que el jugador dejo de moverse
    public void setStaticTime(int value){
        this.static_time = value;
    }
    
    // Se define la imagen actual del jugador
    @Override
    public void setPhotoActual(int value){
        super.setPhotoActual(value);
    }
   
    // Se define el numero total de fotogramas del jugador
    @Override
    public void setNumPhotos(int value){
        super.setNumPhotos(value);
    }
    
    // Se actualiza la imagen a la que se va a usar
    @Override
    public void actualicePhoto(int value){
        super.actualicePhoto(value);
    }
    
    // Metodo que controla el movimiento del presonaje
    public void interact(){
        // Movimiento en X
        if (Greenfoot.isKeyDown("A") || Greenfoot.isKeyDown("a"))
        {
            this.setVelocityX(-3);
            // Al presionar la tecla "A", se suma a un contador el cual
            // se encarga de actualizar el fotograma del jugador
            if (this.getLeftTime() != PHOTO_LIMIT)
            {
                this.setLeftTime(this.getLeftTime() + 1);
                this.setStaticTime(0);
                if (this.getRightTime() != (-1) * PHOTO_LIMIT)
                    this.setRightTime(this.getRightTime() - 1);
            }
        }
        else if (Greenfoot.isKeyDown("D") || Greenfoot.isKeyDown("d"))
        {
            this.setVelocityX(3);
            // Al presionar la tecla "D", se suma a un contador el cual
            // se encarga de actualizar el fotograma del jugador
            if (this.getRightTime() != PHOTO_LIMIT)
            {
                this.setRightTime(this.getRightTime() + 1);
                this.setStaticTime(0);
                if (this.getLeftTime() != (-1) * PHOTO_LIMIT)
                    this.setLeftTime(this.getLeftTime() - 1);
            }
        }
        else
        {
            if(this.getStaticTime() != STATIC_LIMIT)
                this.setStaticTime(this.getStaticTime() + 1);
        }
        
        // Movimiento en Y
        if (Greenfoot.isKeyDown("W") || Greenfoot.isKeyDown("w"))
            this.setVelocityY(-3);
        else if (Greenfoot.isKeyDown("S") || Greenfoot.isKeyDown("s"))
            this.setVelocityY(3);
           
        // Disparar
        if (Greenfoot.isKeyDown("SPACE"))
        {
            // Si ha pasado el suficiente tiempo dispara
            if (this.getShotTime() == SHOT_LIMIT)
            {
                // Se agregan las balas al MUNDO según el nivel
                // de poder del jugador
                World world = this.getWorld();
                switch(this.getPower())
                {
                    // Balas Simples: Una bala simple.
                    case 0: Proyectile p11 = new Proyectile(this.getX(), this.getY() - 12, this.getPower(), 0, -8, proy1); 
                            world.addObject(p11, p11.getPositionX(), p11.getPositionY());
                            this.setShotTime(0);
                            break;
                    
                    // Balas Simples Mas Rapidas: Una bala simple pero mas rapida.
                    case 1: Proyectile p12 = new Proyectile(this.getX(), this.getY() - 12, this.getPower(), 0, -12, proy1); 
                            world.addObject(p12, p12.getPositionX(), p12.getPositionY());
                            this.setShotTime(0);
                            break;
                            
                    // Balas Grandes: Balas de mayor tamaño.
                    case 2: Proyectile p21 = new Proyectile (this.getX(), this.getY() - 12, this.getPower(), 0, -9, proy2);
                            world.addObject(p21, p21.getPositionX(), p21.getPositionY());
                            this.setShotTime(0);
                            break;
                            
                    // Balas Amplias: Tienen una hitbox mas amplia.
                    case 3: Proyectile p31 = new Proyectile (this.getX(), this.getY() - 12, this.getPower(), 0, -8, proy3);
                            world.addObject(p31, p31.getPositionX(), p31.getPositionY());
                            this.setShotTime(-5);
                            break;
                }
            }
        }
    }
    
    // Se actualiza la posicion del jugador segun sus atributos de velocidad
    public void actualicePosition(){
        // Se actualiza la posicion
        this.setX(this.getX() + this.getVelocityX());
        this.setY(this.getY() + this.getVelocityY());
        
        // Y se reinicia la velocidad del JUGADOR
        this.setVelocityX(0);
        this.setVelocityY(0);
    }
    
    // Se modifica la imagen que muestra el jugador segun sus movimientos
    public void animation(){
        // El jugador no se ha movido por un tiempo
        if (getStaticTime() == STATIC_LIMIT)
        {
            this.actualicePhoto(0);
            this.setLeftTime(0);
            this.setRightTime(0);
        }
        else
        if (getLeftTime() == PHOTO_LIMIT)
        {
            this.actualicePhoto(1);
        }
        // El jugador se mueve a la derecha
        else
        if (getRightTime() == PHOTO_LIMIT)
        {
            this.actualicePhoto(2);
        }
    }
    
    // Se incrementa el nivel del jugador segun su experiencia
    public void increasePower(){
        // Se obtiene el nivel actual del jugador y, en base a esto, 
        // se define la experiencia necesaria para subir de nivel
        switch(this.getPower())
        {
            // Nivel 1 -> Nivel 2
            case 1: if(this.getScore() >= 1200)
                    {
                        this.setPower(this.getPower() + 1);
                        this.setScore(0);
                        this.setHearts(this.getHearts() + 1);
                    }
                    break;
            // Nivel 2 -> Nivel 3
            case 2: if(this.getScore() >= 2400)
                    {
                        this.setPower(this.getPower() + 1);
                        this.setScore(0);
                        this.setHearts(this.getHearts() + 1);
                    }
                    break;
            // Nivel 3 -> Nivel 4
            case 3: if(this.getScore() >= 3600)
                    {
                        this.setPower(this.getPower() + 1);
                        this.setScore(0);
                        this.setHearts(this.getHearts() + 1);
                    }
                    break;
        }
    }
    
    // Se verifican y se modifican los tiempos
    public void verifyTimes(){
        // Si el JUGADOR esta vivo, se actualizan los ciclos
        // de disparos
        if (!getDeath())
        {
            if (this.getShotTime() != SHOT_LIMIT)
                this.setShotTime(this.getShotTime() + 1);
        }
        // Si no, se actualizan los ciclos para la muerte del JUGADOR
        else
            this.setStaticTime(this.getStaticTime() + 1);
    }
    
    // Se actualizan las imagenes y los atributos del jugador por permanecer mucho tiempo estatico
    public void explode(){
        // Cuando se pasa este limite, el jugador explota
        if (this.getStaticTime() == DEATH_TIME)
        {
            damage_player.play();
            this.actualicePhoto(3);
        }
        // Cuando se pasa este limite, se reduce la cantidad de vidas,
        // se reinicia la experiencia y se coloca al JUGADOR en una
        // posición nueva.
        else if (this.getStaticTime() == DEATH_TIME * 4)
        {
            this.setHearts(this.getHearts() - 1);
            if (this.getHearts() != 0)
            {
                this.actualicePhoto(0);
                this.setScore(0);
                this.setLocation(300,300);
                this.setDeath(false);
            }
        }
    }
}

