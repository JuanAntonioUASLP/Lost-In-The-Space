import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Clase del enemigo
 * Enemigos que aparecen en la parte superior de la pantalla
 */
public class Enemy extends Entity
{
    // Cantidad de puntos que da el enemigo al ser eliminado
    private int loot;
    
    // Salud del enemigo
    private int hp;
    
    // Bandera para verificar si esta eliminado un enemigo
    private boolean death;
    
    // Bandera para verificar si un enemigo fue creado
    private boolean created;
    
    // Tiempo antes de que desaparezca el enemigo abatido
    private int death_time = 0;
    
    // Tiempo limite antes de que desaparezca el enemigo abatido
    private final int LIMIT_DEATH = 15;
    
    // Metodo principal de la clase
    public void act() 
    {
    }
    
    // Constructor simple, solo inicializa el tiempo de muerte
    // y el estado del enemigo
    public Enemy()
    {
        this.death_time = 0;
        this.death = false;
    }
    
    // Constructor mas completo, especifica posicion inicial, velocidad,
    // atributos y sus respectivas imagenes
    public Enemy(int newX, int newY, int score, int hitPoints, int vel, GreenfootImage... keyFrames)
    {
        //Posicion inicial del enemigo
        this.setPositionX(newX);
        this.setPositionY(newY);
        
        //Atributos del enemigo
        this.loot = score;
        this.hp = hitPoints;
        this.death = false;
        this.created = true;
        
        //Velocidad del enemigo
        this.setVelocityX(vel);
        this.setVelocityY(vel);
        
        //Imagenes que representan al enemigo
        this.buildPhotos(keyFrames);
        this.setNumPhotos(keyFrames.length);
        this.updatePhoto(0);
        
        //Inicializacion del tiempo de muerte
        this.death_time = 0;
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
    public int getLoot(){
        return this.loot;
    }
    
    // Se obtiene la cantidad de vide que tiene el enemigo
    public int getHP(){
        if (!this.getDeath())
            return this.hp;
        return 0;
    }
    
    // Se obtiene si el enemigo ha sido creado
    public boolean getCreated(){
        return this.created;
    }
    
    // Se obtiene si el enemigo sigue con vida
    public boolean getDeath(){
        return this.death;
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
    
    // Se obtiene el tiempo transcurrido desde que murio un enemigo
    public int getDeathTime(){
        return this.death_time;
    }
    
    // Se obtiene el tiempo limite despes de morir un enemigo
    public int getLimitDeathTime(){
        return this.LIMIT_DEATH;
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
    public void setLoot(int value){
        this.loot = value;
    }
    
    // Se define la vida que tiene el enemigo
    public void setHP(int value){
        this.hp = value;
    }
    
    // Se define si el enemigo ha sido creado
    public void setCreated(boolean value){
        this.created = value;
    }
    
    // Se define si el enemigo sigue con vida
    public void setDeath(boolean value){
        this.death = value;
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
    
    // Se define el tiempo transcurrido desde que el enemigo murio
    public void setDeathTime(int value){
        this.death_time = value;
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
    public boolean confirmDeath(){
        if (getHP() <= 0)
            return true;
        return false;
    }
    
    // Se aumenta el conteo de muerte si un enemigo es eliminado
    public void increaseDeathTime(){
        if (getDeath())
            this.setDeathTime(this.getDeathTime() + 1);
    }
    
    // Se hace la animacion de que explota un enemigo cuando muere
    public void enemyExplodes(){
        this.updatePhoto(1);
        if(this.getDeathTime() == LIMIT_DEATH)
        {
            World world = this.getWorld();
            world.removeObject(this);
        }
    }
    
    // Si el enemigo hace contacto con el jugador lo daña
    public void collisionPlayer(){
        Actor hit = this.getOneIntersectingObject(Player.class);
        if (hit != null && !((Player)hit).getDeath())
            ((Player)hit).setDeath(true);
    }
    
    // Si el enemigo toca la parte inferior de la pantalla desaparece
    public boolean isAtLowerEdge(){
        if (this.getY() >= 395)
            return true;
        return false;
    }
}