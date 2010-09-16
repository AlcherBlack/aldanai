/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectai;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author alcher
 */
public class cRobot {
    int action;
    int health;
    int damage;
    int x,y;
    int heading; //(0 - north, 1 - east, 2 - south, 3 - west)
    String name;
    String ParentName;
    String code;
    boolean moved;
    boolean dead=false;
    boolean result;

    cRobot(String filename, int ox, int oy) {
        Load(filename, ox, oy);
    }
    

    void Load(String filename, int ox, int oy) {
        Properties RobotFile = new Properties();
        try {
            RobotFile.load(new FileInputStream(filename));
            //System.out.println("Robot "+filename+" loaded. Name is "+RobotFile.getProperty("name", "default"));
        } catch (IOException ex) {
            System.out.println("Failed to load robot "+filename);
        }
        name=RobotFile.getProperty("name", "default");
        code=RobotFile.getProperty("code", "1 11 55 0");
        health=100;
        dead=false;
        moved=false;
        x=ox;
        y=oy;
        Random r=new Random();
        heading=r.nextInt(4);
        action=80;
        result=true;
        }

    

    void Save(String filename) {
        Properties RobotFile = new Properties();
        RobotFile.setProperty("name", name);
        RobotFile.setProperty("code", code);
        try {
            RobotFile.store(new FileOutputStream(filename),null);
        } catch (IOException ex) {
            System.out.println("Failed to save robot "+filename);
        }
    }

    void Evolve() {
    }
   
}
