import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase de Enemigo 2.2
 * Segundo tipo de enemigo de la segunda rama de enemigos
 */
public class EnemyTwoTwo extends EnemyTwo
{
    // Indica el estado del enemigo (1: Movimiento | 2: Disparo)
    private int state;
    
    // Disparon hechos por el enemigo
    private int shots_made;
    
    // Tiempo desde que el enemigo avanzo
    private int move_time;
    
    // Imagen del disparo enemigo
    private final GreenfootImage proy = new GreenfootImage("Proy5.png");
    
    // Sonido disparo enemigo
    private final GreenfootSound shot = new GreenfootSound("Enemy_Shot.mp3");
    
    // Tiempo maximo antes de que el enemigo avance
    private final int MOVE_LIMIT = 60;
    
    // Numero maximo de disparos que tiene por hacer el enemigo
    private final int MADE_SHOT_LIMIT = 3;
    
    // Tiempo maximo antes de volver a disparar
    private final int SHOT_LIMIT = 80;
    
    // Metodo principal de la clase enemigo1
    public void act(){
        if (!this.getDeath())
        {
            // Avanza
            if (this.getState() == 1)
            {
                verifyMoveTime();
                movement();
            }
            // Dispara
            else if (this.getState() == 2)
            {
                verifyShotTime(SHOT_LIMIT);
                shoot();
            }
            collisionPlayer();
        }
        else
            enemyExplodes();
            increaseDeathTime();
    }
    
    // Constructor basico, inicializa tiempo de muerte y estado
    public EnemyTwoTwo(){
        this.setDeathTime(0);
        this.shot_time = 0;
        this.setDeath(false);
    }
    
    // Constructor principal se inicializan los atributos que diferencian al enemigo
    public EnemyTwoTwo(int newX, int newY, GreenfootImage... keyFrames)
    {        
        //Posiciones iniciales de enemigo2.2
        this.setPositionX(newX);
        this.setPositionY(newY);
        
        //Atributos propios de enemigo2.2
        this.setDeath(false);
        this.state = 1;
        
        //Se inicializan valores propios de enemigo2.2
        this.startPath();
        
        //Fotogramas del enemigo2.2
        this.buildPhotos(keyFrames);
        this.setNumPhotos(keyFrames.length);
        this.updatePhoto(0);
        
        //Inicialización del tiempo
        this.setDeathTime(0);
        this.setShotTime(0);
        this.setMoveTime(0);
    }
    
    // Se definen atributos del enemigo
    public void startPath(){
        this.setLoot(50);
        this.setHP(30);
        this.setVelocityX(0);
        this.setVelocityY(30);
        this.setCreated(true);
    }
    
    // Se definen los fotogramas del enemigo
    @Override
    public void buildPhotos(GreenfootImage... keyFrames){
        super.buildPhotos(keyFrames);
    }
    
    // Se obtiene la imagen actial del enemigo
    @Override
    public int getPhotoActual(){
        return super.getPhotoActual();
    }
    
    // Se obtiene el numero total de fotogramas para el enemigo
    @Override
    public int getNumPhotos(){
        return super.getNumPhotos();
    }
    
    // Se obtiene la imagen del proyectil
    public GreenfootImage getProyPhoto(){
        return this.proy;
    }
    
    // Se obtiene la posicion en X del enemigo
    @Override
    public int getX(){
        return super.getX();
    }
   
    // Se obtiene la posicion en Y del enemigo
    @Override
    public int getY(){
        return super.getY();
    }
    
    // Se obtiene la posicion inicial en X del enemigo
    @Override
    public int getPositionX(){
        return super.getPositionX();
    }
   
    // Se obtiene la posicion inicial de Y del enemigo
    @Override
    public int getPositionY(){
        return super.getPositionY();
    }
    
    // Se obtiene la cantidad de score que el enemigo da al morir
    @Override
    public int getLoot(){
        return super.getLoot();
    }
    
    // Se obtiene la cantidad de vide que tiene el enemigo
    @Override
    public int getHP(){
        return super.getHP();
    }
    
    // Se obtiene si el enemigo ha sido creado
    @Override
    public boolean getCreated(){
        return super.getCreated();
    }
    
    // Se obtiene si el enemigo sigue con vida
    @Override
    public boolean getDeath(){
        return super.getDeath();
    }
    
    // Se obtiene el estado del enemigo
    public int getState(){
        return this.state;
    }
    
    // Se obtiene la velocidad en X del enemigo
    @Override
    public int getVelocityX(){
        return super.getVelocityX();
    }
    
    // Se obtiene la velovidad en Y del enemigo
    @Override
    public int getVelocityY(){
        return super.getVelocityY();
    }
    
    // Se obtiene la cantidad de disparon hechos por el enemigo
    public int getShotsMade(){
        return this.shots_made;
    }
    
    // Se obtiene el tiempo transcurrido desde que murio un enemigo
    @Override
    public int getDeathTime(){
        return super.getDeathTime();
    }
    
    // Se obtiene el tiempo transcurrido luego de un disparo enemigo
    @Override
    public int getShotTime(){
        return super.getShotTime();
    }
    
    // Se obtiene el tiempo transcurrido antes de que avance el enemigo
    public int getMoveTime(){
        return this.move_time;
    }
    
    // Se obtiene el tiempo limite despes de morir un enemigo
    @Override
    public int getLimitDeathTime(){
        return super.getLimitDeathTime();
    }
    
    // Se define la posicion del enemigo
    @Override
    public void setLocation(int newX, int newY){
        super.setLocation(newX, newY);
    }
    
    // Se define la posicion en X del enemigo
    @Override
    public void setX(int value){
        super.setX(value);
    }

    // Se define la posicion en Y del enemigo
    @Override
    public void setY(int value){
        super.setY(value);
    }
    
    // Se define la posicion inicial en X del enemigo
    @Override
    public void setPositionX(int value){
        super.setPositionX(value);
    }
   
    // Se define la posicion inicial en Y del enemigo
    @Override
    public void setPositionY(int value){
        super.setPositionY(value);
    }
    
    // Se define la puntuacion que dara el enemigo al morir
    @Override
    public void setLoot(int value){
        super.setLoot(value);
    }
    
    // Se define la vida que tiene el enemigo
    @Override
    public void setHP(int value){
        super.setHP(value);
    }
    
    // Se define si el enemigo ha sido creado
    @Override
    public void setCreated(boolean value){
        super.setCreated(value);
    }
    
    // Se define si el enemigo sigue con vida
    @Override
    public void setDeath(boolean value){
        super.setDeath(value);
    }
    
    // Se define el estado del enemigo
    public void setState(int value){
        this.state = value;
    }
    
    // Se define la velocidad en X del enemigo
    @Override
    public void setVelocityX(int value){
        super.setVelocityX(value);
    }
    
    // Se define la velocidad en Y del enemigo
    @Override
    public void setVelocityY(int value){
        super.setVelocityY(value);
    }
    
    // Se define la cantidad de disparon hechos por el enemigo
    public void setShotsMade(int value){
        this.shots_made = value;
    }
    
    // Se define el tiempo transcurrido desde que el enemigo murio
    @Override
    public void setDeathTime(int value){
        super.setDeathTime(value);
    }
    
    // Se define el tiempo transcurrido luego de un disparo enemigo
    @Override
    public void setShotTime(int value){
        super.setShotTime(value);
    }
    
    // Se define el tiempo transcurrido antes de que avance el enemigo
    public void setMoveTime(int value){
        this.move_time = value;
    }
    
    // Se define la imagen actual del enemigo
    @Override
    public void setPhotoActual(int value){
        super.setPhotoActual(value);
    }
    
    // Se define el numero total de fotogramas del enemigo
    @Override
    public void setNumPhotos(int value){
        super.setNumPhotos(value);
    }
    
    // Se define la imagen actual del enemigo
    @Override
    public void updatePhoto(int value){
        super.updatePhoto(value);
    }
    
    // Se confirma si un enemigo ha muerto
    @Override
    public boolean confirmDeath(){
        return super.confirmDeath();
    }
    
    // Se verifica y se aumenta el tiempo entre disparo
    @Override
    public void verifyShotTime(int max_time){
        super.verifyShotTime(SHOT_LIMIT);
    }
    
    // Se aumenta el conteo de muerte si un enemigo es eliminado
    @Override
    public void increaseDeathTime(){
        super.increaseDeathTime();
    }
    
    // Se verifica el tiempo antes de que avance el enemigo
    public void verifyMoveTime()
    {
        if (this.move_time < MOVE_LIMIT)
            this.move_time++;
    }
    
    // Se hace la animacion de que explota un enemigo cuando muere
    @Override
    public void enemyExplodes(){
        super.enemyExplodes();
    }
    
    // Si el enemigo hace contacto con el jugador lo daña
    @Override
    public void collisionPlayer(){
        super.collisionPlayer();
    }
    
    // Si el enemigo toca la parte inferior de la pantalla desaparece
    @Override
    public boolean isAtLowerEdge(){
        return super.isAtLowerEdge();
    }
    
    // Se determina el movimiento del enemigo segun su estado y si toca el fondo
    public void movement()
    {
        if (!this.getDeath())
        {
            // Si el enemigo no esta tocando el borde inferior avanza
            if (!this.isAtLowerEdge())
            {
                if (this.getMoveTime() == MOVE_LIMIT)
                {
                    this.setY(this.getY() + this.getVelocityY());
                    this.setMoveTime(0);
                    this.setState(2);
                }
            }
            // Si toca el borde inferior explota
            else
                this.setDeath(true);
        }
    }
    
    // El enemigo dipara despues de cierto tiempo en varias direcciones
    public void shoot()
    {
        if (!this.getDeath())
        {
            // Si aun no se ha hecho el numero maximo de disparos
            // y ha pasado suficiente tiempo, dispara
            if (this.getShotsMade() != MADE_SHOT_LIMIT && this.getShotTime() == SHOT_LIMIT)
            {
                    // Se obtiene el mundo
                    World world = this.getWorld();
                    
                    // Se crean los proyectiles al mundo
                    Proyectile up = new Proyectile(this.getX(), this.getY() - 5, 4, 0, -4, this.getProyPhoto());
                    Proyectile down = new Proyectile(this.getX(), this.getY() + 5, 4, 0, 4, this.getProyPhoto());
                    Proyectile left = new Proyectile(this.getX() - 5, this.getY(), 4, -4, 0, this.getProyPhoto());
                    Proyectile right = new Proyectile(this.getX() + 5, this.getY(), 4, 4, 0, this.getProyPhoto());
                    
                    // Se agregan los proyectiles al mundo
                    world.addObject(up, up.getPositionX(), up.getPositionY());
                    world.addObject(down, down.getPositionX(), down.getPositionY());
                    world.addObject(left, left.getPositionX(), left.getPositionY());
                    world.addObject(right, right.getPositionX(), right.getPositionY());
                    
                    // Se reproduce el efecto de sonido
                    // if (this.getShotsMade() == 0)
                    shot.setVolume(30);
                    shot.play();
                    
                    // Se modifican los atributos
                    this.setShotsMade(this.getShotsMade() + 1);
                    this.setShotTime(0);
            }
            // Si se han realizado los suficientes disparos, se vuelve a mover
            else
            if (this.getShotsMade() == MADE_SHOT_LIMIT)
            {
                this.setShotsMade(0);
                this.setShotTime(-30);
                this.setState(1);
            }
        }
    }
}