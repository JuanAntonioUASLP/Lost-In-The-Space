import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase de la Pantalla de Titulo
 * La Pantalla de Titulo
 */
public class TitleScreen extends World
{
    // Inicializa temporizador
    private int timer = 0;
    
    // Tiempo para que cambie de pantalla al menu
    private final int GO_MENU = 350;

    // Constructor
    public TitleScreen()
    {
        // Crea la Portada
        super(600, 400, 1);
    }
    
    // Se reproduce el sonido del titulo
    private void playTitleSound()
    {
        Greenfoot.playSound("Intro.mp3");
    }

    // Se cambia de pantalla al menu
    public void act()
    {
        if(timer == 0){
            playTitleSound();
        }
        timer++;
        if(timer == GO_MENU)
        {
            Greenfoot.setWorld(new Menu());
        }
    }
}
