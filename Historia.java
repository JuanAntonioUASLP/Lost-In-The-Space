import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Historia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Historia extends Button
{
    /**
     * Act - do whatever the Historia wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkMouse();
        
        if (Greenfoot.mouseClicked(this)){   
             Greenfoot.setWorld(new Story());
            }
    }    
}
