import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase de Enemigo 1
 * Primer tipo de enemigo
 */
public abstract class EnemyOne extends Enemy
{
    // Metodo principal de la clase enemigo1
    public void act(){
    }
    
    // Constructor basico, inicializa tiempo de muerte y estado
    public EnemyOne(){
        this.setDeathTime(0);
        this.setDeath(false);
    }
    
    // Constructor principal se inicializan los atributos que diferencian al enemigo
    public EnemyOne(int newX, int newY, GreenfootImage... keyFrames)
    {        
        //Posiciones iniciales de enemigo1
        this.setPositionX(newX);
        this.setPositionY(newY);
        
        //Atributos propios de enemigo1
        this.setDeath(false);
        
        //Se inicializan valores propios de enemigo1
        this.setLoot(100);
        this.setHP(2);
        this.setVelocityX(0);
        this.setVelocityY(0);
        this.setRotation(90);
        
        //Fotogramas del enemigo1
        this.buildPhotos(keyFrames);
        this.setNumPhotos(keyFrames.length);
        this.actualicePhoto(0);
        
        //Inicialización del tiempo
        this.setDeathTime(0);
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
        return super.getLoot();
    }
    
    // Se obtiene la cantidad de vide que tiene el enemigo
    public int getHP(){
        return super.getHP();
    }
    
    // Se obtiene si el enemigo ha sido creado
    public boolean getCreated(){
        return super.getCreated();
    }
    
    // Se obtiene si el enemigo sigue con vida
    public boolean getDeath(){
        return super.getDeath();
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
        return super.getDeathTime();
    }
    
    // Se obtiene el tiempo limite despes de morir un enemigo
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
    public void setLoot(int value){
        super.setLoot(value);
    }
    
    // Se define la vida que tiene el enemigo
    public void setHP(int value){
        super.setHP(value);
    }
    
    // Se define si el enemigo ha sido creado
    public void setCreated(boolean value){
        super.setCreated(value);
    }
    
    // Se define si el enemigo sigue con vida
    public void setDeath(boolean value){
        super.setDeath(value);
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
        super.setDeathTime(value);
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
    public void actualicePhoto(int value){
        super.actualicePhoto(value);
    }
    
    // Se confirma si un enemigo ha muerto
    public boolean confirmDeath(){
        return super.confirmDeath();
    }
    
    // Se aumenta el conteo de muerte si un enemigo es eliminado
    public void increaseDeathTime(){
        super.increaseDeathTime();
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
}