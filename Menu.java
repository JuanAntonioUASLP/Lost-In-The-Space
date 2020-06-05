import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase del Menu
 * La Pantalla del Menu
 */ 
public class Menu extends World
{
 
    // Constructor
    public Menu()
    {    
        // Se crea Menu
       super(600, 400, 1);
       
       addObject(new Play(),300,40);
       addObject(new Records(),300,120);
       addObject(new Controles(),300,200);
       addObject(new Historia(),300,280);
       addObject(new Exit(),300,360);
    }
}