import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de Proyectil
 * Son los disparos tanto de enemigos como el jugador
 */
public class Proyectile extends Entity
{
    // Indica el tipo de proyectil
    private int type;
    
    // Indica si el proyectil ha desaparecido
    private boolean eliminated;

    // Metodo principal de los proyectiles, necesario para su movimiento y su estado
    public void act() 
    {
        updatePosition();
        if (this.getType() >= 1 && this.getType() <= 3)
            collisionEnemy();
        else
            collisionPlayer();
    }  
    
    // Constructor por defecto de Proyectil
    public Proyectile(){
    }

    // Constructor principal de proyectil, se definen todos sus atributos
    public Proyectile(int newX, int newY, int type, int velX, int velY, GreenfootImage... keyFrames)
    {
        //Posiciones iniciales del proyectil
        this.setPositionX(newX);
        this.setPositionY(newY);
        
        //Atributos propios de proyectil
        this.type = type;
        this.eliminated = false;
        
        //Velocidad del proyectil
        this.setVelocityX(velX);
        this.setVelocityY(velY);
        
        //Fotogramas del proyectil
        this.buildPhotos(keyFrames);
        this.setNumPhotos(keyFrames.length);
        this.updatePhoto(0);
    }
    
    // Se definen las imagenes del proyectil
    @Override
    public void buildPhotos(GreenfootImage... keyFrames){
        super.buildPhotos(keyFrames);
    }

    // Se obtiene la imagen actual del proyectil
    @Override
    public int getPhotoActual(){
        return super.getPhotoActual();
    }
   
    // Se obtiene el numero total de fotogramas del proyectil
    @Override
    public int getNumPhotos(){
        return super.getNumPhotos();
    }
    
    // Se obtiene la posicion en X del proyectil
    @Override
    public int getX(){
        return super.getX();
    }

    // Se obtiene la posicion en Y del proyectil
    @Override
    public int getY(){
        return super.getY();
    }

    // Se obtiene la posicion inicial en X del proyectil
    @Override
    public int getPositionX(){
        return super.getPositionX();
    }
   
    // Se obtiene la posicion inicial en Y del proyectil
    @Override
    public int getPositionY(){
        return super.getPositionY();
    }
    
    // Se obtiene el tipo de proyectil
    public int getType(){
        return this.type;
    }
    
    // Se obtiene si el proyectil va a ser o no eliminado
    public boolean getEliminated(){
        return this.eliminated;
    }
    
    // Se obtiene la velocidad en X del proyectil
    @Override
    public int getVelocityX(){
        return super.getVelocityX();
    }
    
    // Se obtiene la velocidad en Y del proyectil
    @Override
    public int getVelocityY(){
        return super.getVelocityY();
    }
    
    // Se define la posicion del proyectil
    @Override
    public void setLocation(int newX, int newY){
        super.setLocation(newX, newY);
    }

    // Se define la posicion en X del proyectil
    @Override
    public void setX(int value){
        super.setX(value);
    }

    // Se define la posicion en Y del proyectil
    @Override
    public void setY(int value){
        super.setY(value);
    }
    
    // Se define la posicion inicial en X del proyectil
    @Override
    public void setPositionX(int value){
        super.setPositionX(value);
    }
   
    // Se define la posicion inicial en Y del proyectil
    public void setPositionY(int value){
        super.setPositionY(value);
    }

    // Se define el tipo de proyectil
    public void setType(int value){
        this.type = value;
    }
    
    // Se define si el proyectil va a ser eliminado o no
    public void setEliminated(boolean value){
        this.eliminated = value;
    }
    
    // Se define la velocidad en X del proyectil
    @Override
    public void setVelocityX(int value){
        super.setVelocityX(value);
    }
    
    // Se define la velocidad en Y del proyectil
    @Override
    public void setVelocityY(int value){
        super.setVelocityY(value);
    }
    
    // Se define la imagen actual del proyectil
    @Override
    public void setPhotoActual(int value){
        super.setPhotoActual(value);
    }
   
    // Se define el numero total de fotogramas del proyectil
    @Override
    public void setNumPhotos(int value){
        super.setNumPhotos(value);
    }
    
    // Se actualiza la imagen que va a ser utilizada
    @Override
    public void updatePhoto(int value){
        super.updatePhoto(value);
    }
    
    // Se actualiza la posicion del proyectil por su velocidad
    public void updatePosition(){
        this.setX(this.getX() + this.getVelocityX());
        this.setY(this.getY() + this.getVelocityY());
        
        // Se elimina el proyectil en caso que toque el borde de la pantalla
        if (this.isAtEdge())
        {
            this.setEliminated(true);
            this.getWorld().removeObject(this);
        }
    }
    
    // Se obtiene el daño que causan los proyectiles del jugador
    public int getPower(){
        int damage = 0;
        switch(this.getType())
        {
            //Tipo 1
            case 1: damage = 2; 
                    break;
            // Tipo 2
            case 2: damage = 2;
                    break;
            // Tipo 3
            case 3: damage = 3;
                    break;
            // Tipo 4
            case 4: damage = 3;
                    break;
            // Tipo 5
            case 5: damage = 3;
                    break;
        }
        return damage;
    }
    
    // Se verifica si el impacto solo daña al enemigo o si lo mata y cambia parametros de jugador y enemigo
    public void collisionEnemy(){
        if (!this.getEliminated())
        {
            // Se obtiene el mundo en el que se juega
            World world = this.getWorld();
            
            Enemy hit = (Enemy)(this.getOneIntersectingObject(Enemy.class));
            // Si el enemigo existe y no esta muerto
            if (hit != null && !hit.getDeath())
            {
                // Se daña al enemigo con la fuerza del proyectil
                hit.setHP(hit.getHP() - this.getPower());
                world.removeObject(this);
                
                // Se confirma que la vida del enemigo sea 0
                if (hit.confirmDeath())
                {
                    // Se obtiene la recompensa del enemigo y se le da al jugador
                    List<Player> player = world.getObjects(Player.class);
                    player.get(0).setScore(player.get(0).getScore() + hit.getLoot());
                                                 
                    // Se elimina al enemigo
                    hit.setDeath(true);
                    
                    // Se destruye el proyectil
                    world.removeObject(this);
                }
            }
        }
    }
    
    // Se verifica si un proyectil enemigo mata al jugador o lo daña
    public void collisionPlayer(){
        if (!this.getEliminated())
        {
            Actor hit = this.getOneIntersectingObject(Player.class);
            if (hit != null && !((Player)hit).getDeath())
                    ((Player)hit).setDeath(true);
        }
    }
}
