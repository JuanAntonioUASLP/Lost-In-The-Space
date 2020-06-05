import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.Instant;
import java.time.Duration;

/**
 * Clase de la interfaz
 * HUD del juego
 */
public class Interface extends Actor
{   
    Instant start = null;
    Instant finish = null;
    
    // Texto mostrado en el marcador
    private String text;
    
    // Metodo principal de la interfaz
    public void act(){
    }
    
    // Se inicializa el texto del puntaje
    public Interface(){
        text = "Poder: " + 0;
    }
    
    // Se actualiza la informacion del texto
    public void updateScore(Player player)
    {
        int power = player.getPower();
        int score = player.getScore();
        int hearts = player.getHearts();
        text = "Puntuacion: " + score + "\nPoder: " + power + "\nVidas: " + hearts;
    }
    
    // Se actualiza la informacion del puntaje en el HUD
    public void showScore()
    {
        GreenfootImage area_text = new GreenfootImage(text, 20, new Color(255, 255, 255), null);
        this.setImage(area_text);
    }
}
