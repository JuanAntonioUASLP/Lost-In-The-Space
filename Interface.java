import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase de la interfaz
 * HUD del juego
 */
public class Interface extends Actor
{   
    // Texto mostrado en el marcador
    private String text;
    
    // Metodo principal de la interfaz
    public void act(){
    }
    
    // Se inicializa el texto del puntaje
    public Interface(){
        text = "Puntuacion: " + 0 + " Poder: " + 0;
    }
    
    // Se actualiza la informacion del texto
    public void actualiceScore(Player player)
    {
        int power = player.getPower();
        text = "Poder: " + power;
    }
    
    // Se actualiza la informacion del puntaje en el HUD
    public void showScore()
    {
        GreenfootImage area_text = new GreenfootImage(text, 35, new Color(255, 255, 255), new Color(0, 0, 0));
        this.setImage(area_text);
    }
}
