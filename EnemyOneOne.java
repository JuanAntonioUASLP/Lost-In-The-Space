import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase de Enemigo 1,1
 * Primer tipo de enemigo de la primera rama de enemigos
 */
public class EnemyOneOne extends EnemyOne
{
    // Indica la direccion en que se movera el enemigo
    private int direction;
    
    // Metodo principal de la clase enemigo1.1, llama a su movimiento y detecta si muere
    public void act(){
        if (!this.getDeath())
        {
            movement();
            if (!this.getDeath())
                collisionPlayer();
        }
        else
            enemyExplodes();
            increaseDeathTime();
    }
    
    // Constructor basico, inicializa tiempo de muerte y estado
    public EnemyOneOne(){
        this.setDeathTime(0);
        this.setDeath(false);
    }
    
    // Constructor principal se inicializan los atributos que diferencian al enemigo
    public EnemyOneOne(int newX, int newY, int type, int dir, GreenfootImage... keyFrames)
    {        
        //Posiciones iniciales de enemigo1.1
        this.setPositionX(newX);
        this.setPositionY(newY);
        
        //Atributos propios de enemigo1.1
        this.setDeath(false);
        
        //Se inicializan valores propios de enemigo1.1
        this.startPath(type, dir);
        
        //Fotogramas del enemigo1.1
        this.buildPhotos(keyFrames);
        this.setNumPhotos(keyFrames.length);
        this.updatePhoto(0);
        
        //Inicialización del tiempo
        this.setDeathTime(0);
    }
    
    // Se define la direccion y el patron del enemigo y otros atributos
    public void startPath(int type, int dir){
        this.setLoot(20);
        this.setHP(2);
                    
        // Se define la rotación y la velocidad del enemigo
        // El tipo define el ángulo en que se mueve el enemigo
        // El valor direction representa la dirección a la que ve el enemigo (1 = IZQUIERDA, 2 = DERECHA)
        switch(type)
        {
            case 1: this.setVelocityX(10);
                    this.setVelocityY(0);
                    // Izquierda
                    if (dir == 1)
                    {
                        this.setRotation(160);
                        this.setDirection(1);
                    }
                    // Derecha
                    else
                    {
                        this.setRotation(20);
                        this.setDirection(2);
                    }
                    break;
                                
            case 2: this.setVelocityX(8);
                    this.setVelocityY(0);
                    // Izquierda
                    if (dir == 1)
                    {
                        this.setRotation(150);
                        this.setDirection(1);
                    }
                    // Derecha
                    else
                    {
                        this.setRotation(30);
                        this.setDirection(2);
                    }
                    break;
                                
            case 3: this.setVelocityX(6);
                    this.setVelocityY(0);
                    // Izquierda
                    if (dir == 1)
                    {
                        this.setRotation(135);
                        this.setDirection(1);
                    }
                    // Derecha
                    else
                    {
                        this.setRotation(45);
                        this.setDirection(2);
                    }
                    break;
        }
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
    
    // Se obtiene la direccion del enemigo
    public int getDirection(){
        return this.direction;
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
    @Override
    public int getDeathTime(){
        return super.getDeathTime();
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
    
    // Se define la direccion en que se mueve el enemigo
    public void setDirection(int value){
        this.direction = value;
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
    @Override
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
    public void updatePhoto(int value){
        super.updatePhoto(value);
    }
    
    // Se confirma si un enemigo ha muerto
    @Override
    public boolean confirmDeath(){
        return super.confirmDeath();
    }
    
    // Se aumenta el conteo de muerte si un enemigo es eliminado
    @Override
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
    
    // Se define el movimiento del enemigo
    public void movement(){
        if (!this.isAtLowerEdge())
        {
            // Si el enemigo toca un borde lateral, cambia de dirección
            if (this.isAtEdge())
            {
                // Izquierda
                if (this.getDirection() == 1)
                {
                    this.setDirection(2);
                    this.setRotation(180 - this.getRotation());
                }
                // Derecha
                else if (this.getDirection() == 2)
                {
                    this.setDirection(1);
                    this.setRotation(180 - this.getRotation());
                }
            }
            // El enemigo se mueve a la dirección que apunta
            this.move(this.getVelocityX());
        }
        // Si toca el borde inferior explota
        else
            this.setDeath(true);
    }
}