import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase de Pantalla Historia
 * La Pantalla de la Historia
 */
public class Story extends World
{
    // Detecta el Cursor
    private MouseInfo mouse;
    
    // Boton de Salir
    private Button exit;
    
    // Constructor
    public Story()
    {    
        // Se crea Historia
        super(600, 400, 1);
        
        // Se crea el boton de exit
        exit = new Button(74, 21);
        
        // Se agrega el boton
        this.addObject(exit, 554, 378);
    }
    
    // Se regresa al menu al clickear el boton de exit
    public void act(){
        mouse = Greenfoot.getMouseInfo();
        if (mouse != null && mouse.getButton() == 1){
            // Regresar al Menu
            if (mouse.getActor() == exit)
            {
                Menu menu = new Menu();
                Greenfoot.setWorld(menu);
            }
        }
    }
}
