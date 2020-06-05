import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase Boton
 * Los Botones de las Pantallas
 */
public class Button extends Actor
{
    // Ancho del Boton
    private int width;
    
    // Alto del Boton
    private int height;
    
       private boolean mouseOver = false;
    private static int MAX_TRANS = 255;
    
    // Constructor por Defecto
    public Button()
    {
    }
    
    // Construcor para crear un Boton con ancho y altura determinados
    public Button(int ancho, int altura)
    {
        GreenfootImage area = new GreenfootImage(ancho, altura);
        this.setImage(area);
    }
    
    // Metodo principal de la clase boton
    public void act()
    {
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
      public void checkMouse(){
        
        if(Greenfoot.mouseMoved(null)){
            mouseOver = Greenfoot.mouseMoved(this);
            
        }
        
        if(mouseOver){
          adjustTransparency(MAX_TRANS/2);
  
        }
        else{
            adjustTransparency(255);
        }
    }
    
    public void adjustTransparency(int adjust){
        GreenfootImage tempImage = getImage();
        tempImage.setTransparency(adjust);
        setImage(tempImage);
    }
  
}
