/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectai;
import javax.swing.*;
import java.util.Scanner;

//testing the versioning system... AGAIN!

/**
 * @author alcher
 */
public class Evolution {
    static final String fs = System.getProperty("file.separator");
    int[] place;
    int generation;
    public cRobot[] robots;
    public MainFrame Frame;
    Map map= new Map();
    Runnable r=new Runnable() {
    public void run() {
        

       //evolution doesn't stop until we press Stop
       while (Frame.StopEvolution==false){
           
           //creating robots each turn TODO: FUCKING EVERYTHING
           Scanner src = new Scanner(map.spawns);
           for (int i=0;i<EvolutionConfig.gerneration_size;++i) {
           //TODO: Okay, the scanner thing here is fucked up beyond belief. We have to do something with this shit.
                int x,y;
                x=src.nextInt();
                y=src.nextInt();
                robots[i] = new cRobot(System.getProperty("user.dir")+fs+"Data"+fs+"test1"+fs+"robots"+fs+"default.txt",x,y);
           }

           generation++;
           Frame.AddMessage("Generation "+generation+"\n");
           cGame game = new cGame(map,robots);
           game.Game(generation);


           //Robot.Evolve;
       }

       //if we're here, then btnStop has benn pressed
       Frame.AddMessage("Stopped.\n");
       Frame.StopEvolution=false;
    }
    };

    

    Evolution(MainFrame _Frame) {
        Frame=_Frame;
    };

    public void Start() {
    //JOptionPane.showMessageDialog(null,MapgenConfig.map_path);
        Frame.AddMessage("Lolevolution progressing!\n");
        generation=0;
    
       //kk, let's init some shit
       //this should be moved into the actual evolution thread once mapgen code is implemented
       map=MapLoad.Load();

       //this one is just for debug
       /*for (int i=0;i<MapgenConfig.map_width;++i) {
            for (int j=0;j<MapgenConfig.map_height;++j) {
                Frame.AddMessage(""+map.map[i][j]);
            }
            Frame.AddMessage("\n");
        }*/

       //kk, let's init some robots YEAH

       robots = new cRobot[EvolutionConfig.gerneration_size];

       //this is to read the spawn points

       place = new int[3]; //this is required to determine the winners

       //looks like shit has been set up for shit to unfold
       Thread t=new Thread(r);
       t.start();
    }
     
     //the actual game code



}
