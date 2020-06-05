import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * Write a description of class PlayerRecords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerRecords extends Actor
{
    private String text;
    
    ArrayList records = new ArrayList<Player>();
    
    public PlayerRecords(){
        records = readRecords();
        showRecords(records);
    }
    
    public void act(){
    }
    
    public ArrayList<Player> readRecords(){
        File file = new File("Records.txt");
        ArrayList file_records = new ArrayList<Player>();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner coma = new Scanner(line);
                coma.useDelimiter("\\s*,\\s*");
                
                Player p = new Player();
                p.setName(coma.next());
                p.setTime(Integer.parseInt(coma.next()));
                file_records.add(p);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
        }
        return file_records;
    }
    
    public void showRecords(ArrayList<Player> records){
        for(Player p : records){
            text = "Jugador: " + p.name + "                                               " + "Tiempo: " + p.record_time + "\n";
            GreenfootImage area_text = new GreenfootImage(text, 20, new Color(255, 255, 255), null);
            this.setImage(area_text);
        }
    }
}
