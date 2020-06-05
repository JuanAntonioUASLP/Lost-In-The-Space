import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Record here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Record extends World
{
    PlayerRecords player_records;
    
    // Detecta el Cursor
    private MouseInfo mouse;
    
    // Boton de Salir
    private Button exit;
    
    // Constructor
    /**
     * Constructor for objects of class Record.
     * 
     */
    public Record()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
         super(600, 400, 1);
        
        // Se crea el boton de exit
        exit = new Button(74, 21);
        player_records = new PlayerRecords();
        
        // Se agrega el boton
        this.addObject(exit, 554, 378);
        this.addObject(player_records, 300, 220);
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
