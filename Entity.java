import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Clase de las Entidades
 * Engloba a todos los objetos que van a estar interactuando
 * a lo largo que se desarrolla el juego.
 */
public class Entity extends Actor
{
    // Se inicializa una lista de imagenes que representan a una entidad
    private ArrayList<GreenfootImage> photos;
    
    // Imagen actial de la entidad
    private int photo_actual;
    
    // Numero de imagenes de la entidad
    private int num_photos;
    
    //Inicializa la posición en X de la entidad
    private int x;
    
    //Inicializa la posición en Y de la entidad
    private int y;
    
    //Inicializa la velocidad en que se mueve sobre el eje X
    private int vel_x;
    
    //Inicializa la velocidad en que se mueve sobre el eje Y
    private int vel_y;
    
    // Metodo principal de la clase
    public void act() 
    {
    }
    
    // Constructor por Defecto
    public Entity()
    {
    }
    
    // Constructor que recibe los parametros ya inicializados
    // keyFrames contiene las imagenes que representa la entidad, son del tipo de la imagen  
    public Entity(int newX, int newY, int vel, GreenfootImage...keyFrames)
   {
       this.setPositionX(newX);
       this.setPositionY(newY);
       this.setVelocityX(vel);
       this.setVelocityY(vel);
       this.buildPhotos(keyFrames);
   }
   
   // Se construye la lista de imagenes de la entidad
   public void buildPhotos(GreenfootImage... keyFrames)
   {
       int i;
       // Se genera una lista donde se guardan las imagenes de la entidad
       photos = new ArrayList<>();
       
       // Se guardan los imagenes en la lista
       if (keyFrames.length != 0) {
           for (i = 0; i < keyFrames.length; i++)
                photos.add(new GreenfootImage(keyFrames[i]));
       }
   }
   
   // Se retorna la imagen actual
   public int getPhotoActual()
   {
       return this.photo_actual;
   }
   
   // Obtiene el numero total de fotogramas de la entidad
   public int getNumPhotos()
   {
       return this.num_photos;
   }
   
   // Obtiene la posicion en X de la entidad
   @Override
   public int getX()
   {
       if (this.getWorld() != null)
           return super.getX();
       return 0;
   }
   
   // Obtiene la posicion en Y de la entidad
   @Override
   public int getY()
   {
       if (this.getWorld() != null)
           return super.getY();
       return 0;
   }
   
   // Retorna la posicion inicial en X de la entidad
   public int getPositionX(){
       return this.x;
   }
   
   // Retorna la posicion inicial en Y de la entidad
   public int getPositionY(){
       return this.y;
   }
   
   // Retorna la velocidad en X de la entidad
   public int getVelocityX(){
       return this.vel_x;
   }
   
   // Retorna la velocidad en Y de la entidad
   public int getVelocityY(){
       return this.vel_y;
   }
   
   // Le asigna una posicion a la entidad en el mundo
   @Override
   public void setLocation(int newX, int newY){
       super.setLocation(newX,newY);
   }
   
   // Le asigna una posicion sobre X a la entidad
   public void setX(int value){
       if (this.getWorld() != null)
            super.setLocation(value,this.getY());
   }
   
   // Le asigna una posicion sobre Y a la entidad
   public void setY(int value){
       if (this.getWorld() != null)
            super.setLocation(this.getX(),value);
   }
   
   // Le asigna una posicion inicial sobre X a la entidad
   public void setPositionX(int value){
       this.x = value;
   }
   
   // Le asigna una posicion inicial sobre X a la entidad
   public void setPositionY(int value){
       this.y = value;
   }
   
   // Define la velocidad sobre X de la entidad
   public void setVelocityX(int value){
       this.vel_x = value;
   }
   
   // Define la velocidad sobre Y de la entidad
   public void setVelocityY(int value){
       this.vel_y = value;
   }
   
   // Define el fotograma actual de la entidad
   public void setPhotoActual(int value){
       this.photo_actual = value;
   }
   
   // Define el numero de fotogramas de la entidad
   public void setNumPhotos(int value){
       this.num_photos = value;
   }
   
   // Actualiza cual es el fotograma que se usara de la entidad
   public void updatePhoto(int value){
       if (value < this.num_photos)
           this.setImage(photos.get(value));
   }
}
