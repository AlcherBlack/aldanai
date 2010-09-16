//had to make this class public, because otherwise fail is achieved when attempting to return a Map variable in MApLoad.java

package projectai;

/*
 * @author alcher
 */
public class Map {
    int width=20;//hurray to shit code )
    int height=20;
    int[][] map; //dynamic, map = new int[map_width][map_height];
    String map_string;
    String spawns;
}
