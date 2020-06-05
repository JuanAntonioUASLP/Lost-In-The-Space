import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase del jefe final
 * Jefe final del juego
 */
public class Boss extends Enemy
{
    // Sonido cuando el boss es eliminado
    private final GreenfootSound kill_boss = new GreenfootSound("Damage_Player.mp3");
    
    // Imagen del disparo del Boss
    private final GreenfootImage proy = new GreenfootImage("Proy4.png");
    
    // Sonido disparo del Boss
    private final GreenfootSound shot = new GreenfootSound("Enemy_Shot.mp3");
    
    // Tiempo maximo antes de volver a disparar el Boss
    private final int SHOT_LIMIT = 150;
    
    // Tiempo entre cada disparo del Boss
    int shot_time;
    
    // Tiempo limite antes de que desaparezca el Boss
    private final int LIMIT_DEATH = 20;
    
    // Metodo principal de la clase Boss
    public void act(){
        if (!this.getDeath())
        {
            verifyShotTime(SHOT_LIMIT);
            collisionPlayer();
            shoot();
        }
        else
            bossExplodes();
            increaseDeathTime();
    }
    
    // Constructor basico, inicializa tiempo de muerte y estado
    public Boss(){
        this.setDeathTime(0);
        this.shot_time = 0;
        this.setDeath(false);
    }
    
    // Constructor principal se inicializan los atributos del Boss
    public Boss(int newX, int newY, GreenfootImage... keyFrames)
    {        
        //Posiciones iniciales del Boss
        this.setPositionX(newX);
        this.setPositionY(newY);
        
        //Atributos propios del Boss
        this.setDeath(false);
        
        //Se inicializan valores propios del Boss
        this.initBoss();
        
        //Fotogramas del Boss
        this.buildPhotos(keyFrames);
        this.setNumPhotos(keyFrames.length);
        this.updatePhoto(0);
        
        //Inicialización del tiempo
        this.setDeathTime(0);
        this.shot_time = 0;
    }
    
    // Se definen los atributos del boss
    public void initBoss(){
        this.setHP(200);
        this.setCreated(false);
    }
    
    // Se definen los fotogramas del Boss
    @Override
    public void buildPhotos(GreenfootImage... keyFrames){
        super.buildPhotos(keyFrames);
    }
    
    // Se obtiene la imagen actial del Boss
    @Override
    public int getPhotoActual(){
        return super.getPhotoActual();
    }
    
    // Se obtiene el numero total de fotogramas para el Boss
    @Override
    public int getNumPhotos(){
        return super.getNumPhotos();
    }
    
    // Se obtiene la imagen del proyectil
    public GreenfootImage getProyPhoto(){
        return this.proy;
    }
    
    // Se obtiene la posicion en X del Boss
    @Override
    public int getX(){
        return super.getX();
    }
   
    // Se obtiene la posicion en Y del Boss
    @Override
    public int getY(){
        return super.getY();
    }
    
    // Se obtiene la posicion inicial en X del Boss
    @Override
    public int getPositionX(){
        return super.getPositionX();
    }
   
    // Se obtiene la posicion inicial de Y del Boss
    @Override
    public int getPositionY(){
        return super.getPositionY();
    }
    
    // Se obtiene la cantidad de vida que tiene el Boss
    @Override
    public int getHP(){
        return super.getHP();
    }
    
    // Se obtiene si el Boss ha sido creado
    @Override
    public boolean getCreated(){
        return super.getCreated();
    }
    
    // Se obtiene si el Boss sigue con vida
    @Override
    public boolean getDeath(){
        return super.getDeath();
    }
    
    // Se obtiene el tiempo transcurrido desde que murio el Boss
    public int getDeathTime(){
        return super.getDeathTime();
    }
    
    // Se obtiene el tiempo transcurrido luego de un disparo del Boss
    public int getShotTime(){
        return this.shot_time;
    }
    
    // Se obtiene el tiempo limite despes de morir el Boss
    public int getLimitDeathTime(){
        return super.getLimitDeathTime();
    }
    
    // Se define la posicion del Boss
    @Override
    public void setLocation(int newX, int newY){
        super.setLocation(newX, newY);
    }
    
    // Se define la posicion en X del Boss
    @Override
    public void setX(int value){
        super.setX(value);
    }

    // Se define la posicion en Y del Boss
    @Override
    public void setY(int value){
        super.setY(value);
    }
    
    // Se define la posicion inicial en X del Boss
    @Override
    public void setPositionX(int value){
        super.setPositionX(value);
    }
   
    // Se define la posicion inicial en Y del Boss
    @Override
    public void setPositionY(int value){
        super.setPositionY(value);
    }
    
    // Se define la vida que tiene el Boss
    public void setHP(int value){
        super.setHP(value);
    }
    
    // Se define si el Boss ha sido creado
    public void setCreated(boolean value){
        super.setCreated(value);
    }
    
    // Se define si el Boss sigue con vida
    public void setDeath(boolean value){
        super.setDeath(value);
    }
    
    // Se define el tiempo transcurrido desde que el Boss murio
    public void setDeathTime(int value){
        super.setDeathTime(value);
    }
    
    // Se define el tiempo transcurrido luego de un disparo del Boss
    public void setShotTime(int value){
        this.shot_time = value;
    }
    
    // Se define la imagen actual del Boss
    @Override
    public void setPhotoActual(int value){
        super.setPhotoActual(value);
    }
    
    // Se define el numero total de fotogramas del Boss
    @Override
    public void setNumPhotos(int value){
        super.setNumPhotos(value);
    }
    
    // Se define la imagen actual del Boss
    @Override
    public void updatePhoto(int value){
        super.updatePhoto(value);
    }
    
    // Se confirma si el Boss ha muerto
    public boolean confirmDeath(){
        return super.confirmDeath();
    }
    
    // Se verifica y se aumenta el tiempo entre disparo
    public void verifyShotTime(int max_time)
    {
        if (shot_time < max_time)
            shot_time++;
    }
    
    // Se aumenta el conteo de muerte si el Boss es eliminado
    public void increaseDeathTime(){
        super.increaseDeathTime();
    }
    
    // Se hace la animacion de que explota el Boss cuando muere
    public void bossExplodes(){
        
        if(this.getDeathTime() == LIMIT_DEATH)
        {
            kill_boss.play();
            this.updatePhoto(1);
        }
        if(this.getDeathTime() == LIMIT_DEATH * 4){
            World world = this.getWorld();
            world.removeObject(this);
            this.setCreated(false);
        }
    }
    
    // Si el Boss hace contacto con el jugador lo daña
    @Override
    public void collisionPlayer(){
        super.collisionPlayer();
    }
    
    // El Boss dipara despues de cierto tiempo
    public void shoot()
    {
        if (!this.getDeath())
        {
            // Si pasa cierto tiempo dispara
            if (this.getShotTime() == SHOT_LIMIT)
            {
                    World world = this.getWorld();
                    Proyectile proyectile1 = new Proyectile(this.getX() - 140, this.getY() + 70, 6, 0, 2, this.getProyPhoto());
                    Proyectile proyectile2 = new Proyectile(this.getX() - 70, this.getY() + 75, 6, 0, 2, this.getProyPhoto());
                    Proyectile proyectile3 = new Proyectile(this.getX(), this.getY() + 80, 6, 0, 3, this.getProyPhoto());
                    Proyectile proyectile4 = new Proyectile(this.getX() + 70, this.getY() + 75, 6, 0, 2, this.getProyPhoto());
                    Proyectile proyectile5 = new Proyectile(this.getX() + 140, this.getY() + 80, 6, 0, 2, this.getProyPhoto());
                    world.addObject(proyectile1, proyectile1.getPositionX(), proyectile1.getPositionY());
                    world.addObject(proyectile2, proyectile2.getPositionX(), proyectile2.getPositionY());
                    world.addObject(proyectile3, proyectile3.getPositionX(), proyectile3.getPositionY());
                    world.addObject(proyectile4, proyectile4.getPositionX(), proyectile4.getPositionY());
                    world.addObject(proyectile5, proyectile5.getPositionX(), proyectile5.getPositionY());
                    this.setShotTime(0);
                    shot.setVolume(30);
                    shot.play();
            }
        }
    }
}