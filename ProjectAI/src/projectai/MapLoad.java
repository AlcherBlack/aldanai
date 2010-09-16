/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectai;
import java.util.Scanner;

/*
 * @author alcher
 */
public class MapLoad {
    public static Map Load() {


        Map map= new Map();
        map.height=MapgenConfig.map_height;
        map.width=MapgenConfig.map_width;
        map.map_string = MapgenConfig.map;
        map.map = new int[map.width][map.height];
        map.spawns=MapgenConfig.spawns;

        Scanner src = new Scanner(MapgenConfig.map);
        for (int j=0;j<map.width;++j) {
            for (int i=0;i<map.height;++i) {
                map.map[i][j]=src.nextInt();
            }
        }


        return map;
    }
}
