/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectai;
import java.util.*;
import java.io.*;

/*
 * @author alcher
 */
public class cGame {
    static final String fs = System.getProperty("file.separator");
    Properties GameFile = new Properties();
    Map map;
    cRobot[] robots;
    int[][] robots_pos;//these are static for the turn, used to check if a robot sees them
    String s;


    cGame(Map omap, cRobot[] orobots) {
        map=omap;
        robots=orobots;
    }

    int[] Game(int generation){ //TODO: add round number
       int[] place;
       String turn;
       place = new int[3];
       robots_pos = new int[map.width][map.height];
       
       //TurnSave(0);
       for (int t=0;t<EvolutionConfig.Game_length;++t) { //TODO:add number of turns before end of generation
           
           
           for (int i=0;i<map.width;++i) {
           for (int j=0;j<map.height;++j) {
               
                   robots_pos[i][j]=0;
                   
               }
           }

           //System.out.println("Generation "+generation+" turn number "+t+" begins...");
           for (int i=0;i<EvolutionConfig.gerneration_size;++i) {
               //robots[i].health=robots[i].health-robots[i].damage;
               //robots[i].damage=0;

               //kk, let's check out if our robot is even alive lol
               if (robots[i].health<=0) {
                   //let's find out if he's freshly or long dead lol
                   if (robots[i].dead) {
                       //kk, the robot does nothing
                       robots[i].action=80;
                       continue;
                   } else {
                       //we've got a fresh one, gotta make a nice explosion!
                       robots[i].action=79;
                       robots[i].dead=true;
                   }
               }

               for (int z=0;z<EvolutionConfig.gerneration_size;++z) {
                       if (!robots[z].dead) {robots_pos[robots[z].x][robots[z].y]=1;}
                   }


               //kk, let's look at our AWESOME genetic code lol
               //parse returns TRUE if action successfull
               //80 - idle

               //checked in March 2011, should work fine. Alcher
               if ((!robots[i].dead)&&(Parse(robots[i].code,i)==false)) {
                   if (robots[i].action!=80) {
                       robots[i].result=false;
                   } else {robots[i].result=true;}
               } else {robots[i].result=true;}
               

               
           }
           TurnSave(t); //why don't we save the turn
       }


       //final saving
       CreateSavegame(generation);
       
       
       return place;  
     }
    
    void TurnSave(int turn){
        GameFile.setProperty("turn_count", String.valueOf(turn));
        String p;
        p=turn+" ";
         for(int i=0;i<EvolutionConfig.gerneration_size;++i){ //TODO: robot count must be variable
             /*
              turn number
              robot number
              hp - health
              x, y coordinates
              heading (0 - north, 1 - east, 2 - south, 3 - west)
              action type (the same id as in internal language)
              action result (1 if success, 0 if PHAIL)
              */
             //if (!robots[i].dead) {
                p=p+i+" "+robots[i].health+" "+robots[i].x+" "+robots[i].y+" "+robots[i].heading+" "+robots[i].action+" "+BolToInt(robots[i].result)+" ";
             //}
         }
             GameFile.setProperty("t"+turn, p);
    }

    int BolToInt(boolean bol) {
        if (bol) {return 1;} else {return 0;}
    }

    //this funciton actually checkes if something is true
    boolean Parse(String s, int i) {
         int k;
         Scanner src = new Scanner(s);
         //System.out.println("Parsing code string: "+s);
         if (s.equalsIgnoreCase("")) return false;
         while (src.hasNext()){
                if (src.nextInt()==1) {
                        //if clause begins, let's check out the next condition
                        if (Check(src.nextInt(),i)){
                            //success, let's check the next one out
                            k=src.nextInt();
                            //System.out.println("Next integer is "+k); //debug
                            if (k==1) {
                                //kk let's parse the next one
                                return Parse(Substring(s,2),i);
                            }
                            else {
                                return Execute(k, i);
                            }

                        }
                        else {

                            //kk this is srsly fucked up
                            return Parse(s.substring(s.indexOf(" 0 ")+3,s.length()),i);
                        }
                    }
               }

    //nothing happened
    //this only happens if none of the checks returned true (or if there is an error lol)
    robots[i].action=80;
    return false;
    }

    String Substring(String s, int c) {
        Scanner src = new Scanner(s);
        ArrayList<Integer>list=new ArrayList<Integer>();
        while (src.hasNext()) {
            list.add(src.nextInt());
        }
        //System.out.print("String is "+list+" ");
        for (int i=0;i<c;i++) {
            list.remove(0);
        }
        //System.out.println("Substring is "+list);
        s="";
        while (!list.isEmpty()) {
            s=s+list.get(0)+" ";
            list.remove(0);
        }
        return s;
    }

    //executes an action
    boolean Execute(int k, int i) {
         //System.out.println("Executing command "+k);

        switch(k) {
            case 51: //attempting to drive forward
                     if ((Check(11,i)==false)&&(Check(12, i)==false)) {
                         robots[i].action=51;
                         switch (robots[i].heading) {
                            case 0: robots[i].y=robots[i].y-1; //north
                                    return true;
                            case 1: robots[i].x=robots[i].x+1; //east
                                    return true;
                            case 2: robots[i].y=robots[i].y+1; //south
                                    return true;
                            case 3: robots[i].x=robots[i].x-1; //west
                                    return true;
                            default: System.out.println("Robot "+i+" has an illegal heading."); break;
                            }
                     }
                     break;
            case 55: //kk let's turn RIGHT
                     robots[i].action=55;
                     robots[i].heading=robots[i].heading+1;
                     if (robots[i].heading==4) {robots[i].heading=0;}
                     return true;
            case 56: //kk let's turn LEFT
                     robots[i].action=56;
                     robots[i].heading=robots[i].heading-1;
                     if (robots[i].heading==-1) {robots[i].heading=3;}
                     return true;
            case 60: //shoot
                     robots[i].action=60;
                     if (CheckBulletPath(robots[i].x,robots[i].y,robots[i].heading,true,false)) {
                         //the damage is dealt in the pathcheck, so fuck it
                         return true;
                     }
            default: robots[i].action=80;
                     break;
        }

        return false;
    }

    //checks for a condition
    boolean Check(int c, int i) {

        switch (c) {
            case 11: //if wall in front
                return CheckMap(robots[i].x,robots[i].y,robots[i].heading,1);
            case 12: //if enemy in front
                return CheckMap(robots[i].x,robots[i].y,robots[i].heading,9);
            case 13: //wall to the left
                return CheckMap(robots[i].x,robots[i].y,robots[i].heading-1,1);
            case 14: //enemy to the left
                return CheckMap(robots[i].x,robots[i].y,robots[i].heading-1,9);
            case 15: //wall to the left
                return CheckMap(robots[i].x,robots[i].y,robots[i].heading+1,1);
            case 16: //enemy to the left
                return CheckMap(robots[i].x,robots[i].y,robots[i].heading+1,9);
            case 17: //wall behind
                return CheckMap(robots[i].x,robots[i].y,robots[i].heading+2,1);
            case 18: //enemy behind
                return CheckMap(robots[i].x,robots[i].y,robots[i].heading+2,9);
            case 31: //if no wall in front
                return (!Check(11,i));
            case 32:
                return (!Check(12,i));
            case 33:
                return (!Check(13,i));
            case 34:
                return (!Check(14,i));
            case 35:
                return (!Check(15,i));
            case 36:
                return (!Check(16,i));
            case 37:
                return (!Check(17,i));
            case 38:
                return (!Check(18,i));

            case 42: //if enemy far in front
                return CheckBulletPath(robots[i].x,robots[i].y,robots[i].heading,false,false);
            case 50: //random
                     Random r=new Random();
                     return (1!=r.nextInt(2));

            default:
                System.out.println(c+" is not a valid condition id.");
                return false;
        }

        //System.out.println("Checking condition "+c+" returned FALSE.");
        //if we're here, then fail has been achieved
        //return false;
    }

    boolean CheckBulletPath(int x,int y,int heading,boolean DealDamage,boolean CheckDistance) {
        int dist=0;
        int gainx=0;
        int gainy=0;

        //TODO: check if this shit is correct, it is kinda...
        switch (heading) {
            case 0: dist=y;
                    gainy=-1;
                    break;
            case 1: dist=map.width-x;
                    gainx=1;
                    break;
            case 2: dist=map.height-y;
                    gainy=1;
                    break;
            case 3: dist=x;
                    gainx=-1;
                    break;
            default:
                    System.out.println(heading+" is an illegal heading!");
                    return false;
        }

        for (int i=0;i<dist;++i) {
            if (CheckMap(x+(i*gainx),y+(i*gainy),heading,1)) { //checking for a wall
                //System.out.println("Bullet hits a wall! Nobody cares.");
                return false;
            }
            else {
                if (CheckForRobot(x+((i+1)*gainx),y+((i+1)*gainy),DealDamage)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean CheckMap(int x, int y, int heading, int type) {
        if (heading==4) heading=0;
        if (heading==5) heading=1;
        if (heading==-1) heading=3;
        if (heading==-2) heading=2;
        if (type==9) { //if we're dealing with a robot
            switch (heading) {
                    case 0: return (CheckForRobot(x,y-1,false)); //north
                    case 1: return (CheckForRobot(x+1,y,false)); //east
                    case 2: return (CheckForRobot(x,y+1,false)); //south
                    case 3: return (CheckForRobot(x-1,y,false)); //west
                    default: System.out.println(heading +" is an illegal heading."); break;
            }
        }
        switch (heading) {
                    case 0: return (map.map[x][y-1]==type); //north
                    case 1: return (map.map[x+1][y]==type); //east
                    case 2: return (map.map[x][y+1]==type); //south
                    case 3: return (map.map[x-1][y]==type); //west
                    default: System.out.println(heading +" is an illegal heading."); break;
                }
        return false;
    }

    boolean CheckForRobot(int x, int y,boolean DealDamage) {
        for (int z=0;z<EvolutionConfig.gerneration_size;++z) {
                       if ((robots[z].x==x)&&(robots[z].y==y)&&(!robots[z].dead)) {
                           //robots[z].damage=robots[z].damage+EvolutionConfig.Damage_per_shot;
                           if (DealDamage) {robots[z].health=robots[z].health-EvolutionConfig.Damage_per_shot;}
                           return true;
                       }
                   }
        //if (robots_pos[x][y]==1) {return true;}
        return false;
    }
    
    
    void CreateSavegame(int generation){
        GameFile.setProperty("ver", "0.1");


        //id is the same as generation for now, but it should be something like test1+generation+attempt
        GameFile.setProperty("id", String.valueOf(generation));
        GameFile.setProperty("generation", String.valueOf(generation));

        //TODO: date, time, map_id

        GameFile.setProperty("height", String.valueOf(map.height));
        GameFile.setProperty("width", String.valueOf(map.width));
        GameFile.setProperty("map", map.map_string);
        GameFile.setProperty("rb_count", "10");

        for(int i=0;i<EvolutionConfig.gerneration_size;++i){ //TODO: robot count must be variable
            GameFile.setProperty("rb"+i+"_name", robots[i].name);
            GameFile.setProperty("rb"+i+"_code", robots[i].code);
        }

        try {
            GameFile.store(new FileOutputStream(System.getProperty("user.dir")+fs+"Data"+fs+"test1"+fs+"Games"+fs+"Generation "+generation+".txt"),null); //TODO: change folders and format
        } catch (IOException ex) {
            System.out.println("Failed to save round "+generation);
        }
    }
}
