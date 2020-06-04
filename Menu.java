import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase del Menu
 * La Pantalla del Menu
 */ 
public class Menu extends World
{
    // Detecta el Cursor
    private MouseInfo mouse;
    
    // Boton de Inicio
    private Button start;
    
    // Boton de los Controles
    private Button control;
    
    // Boton de la Historia
    private Button story;
    
    // Boton de Salir
    private Button exit;

    // Constructor
    public Menu()
    {    
        // Se crea Menu
        super(600, 400, 1);
        
        // Se Crean los Botones
        start = new Button(167, 33);
        control = new Button(299, 35);
        story = new Button(216, 34);
        exit = new Button(142, 34);
        
        // Se Agregan los Botones
        this.addObject(start, 300, 52);
        this.addObject(control, 300, 144);
        this.addObject(story, 300, 236);
        this.addObject(exit, 300, 327);
    }
    
    /* Se aplica el efecto de boton a los botones
    public void buttonEffect(){
        mouse = Greenfoot.getMouseInfo();
        if (mouse != null){
            if(Greenfoot.mouseMoved(control)){
                GreenfootImage area = new GreenfootImage(start.getWidth(), start.getHeight());
                area.setColor(Color.GREEN);
                area.fillRect(300, 52, start.getWidth(), start.getHeight());
                area.setTransparency(100);
            }
            if(Greenfoot.mouseMoved(control)){
                GreenfootImage area = new GreenfootImage(control.getWidth(), control.getHeight());
                area.setColor(Color.GREEN);
                area.fillRect(300, 144, control.getWidth(), control.getHeight());
                area.setTransparency(100);
            }
            if(Greenfoot.mouseMoved(story)){
                GreenfootImage area = new GreenfootImage(story.getWidth(), story.getHeight());
                area.setColor(Color.GREEN);
                area.fillRect(300, 236, story.getWidth(), story.getHeight());
                area.setTransparency(100);
            }
            if(Greenfoot.mouseMoved(exit)){
                GreenfootImage area = new GreenfootImage(exit.getWidth(), exit.getHeight());
                area.setColor(Color.GREEN);
                area.fillRect(300, 327, exit.getWidth(), exit.getHeight());
                area.setTransparency(100);
            }
        }
    }
    */
    
    // En este metodo se detecta que botón se presiona
    // para cambiar de pantalla
    public void act()
    {
        //buttonEffect();
        mouse = Greenfoot.getMouseInfo();
        if (mouse != null && mouse.getButton() == 1)
        {
            // Iniciar Juego
            if (mouse.getActor() == start)
            {
                Game game = new Game();
                Greenfoot.setWorld(game);
            }
            // Controles
            if (mouse.getActor() == control)
            {
                Control control = new Control();
                Greenfoot.setWorld(control);
            }
            // Historia
            if (mouse.getActor() == story)
            {
                Story historia = new Story();
                Greenfoot.setWorld(historia);
            }
            // Salir
            if (mouse.getActor() == exit)
                Greenfoot.stop();
        }
    }
}