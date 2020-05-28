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
    
    // Metodo principal de las clases
    public void act()
    {
    }    
}
