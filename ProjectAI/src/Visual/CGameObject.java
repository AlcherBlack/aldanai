/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Visual;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Danich
 */
abstract public class CGameObject {
    static final int FLOOR = 0;
    static final int WALL = 1;
    static final int HEALTH = 2;
    static final int AMMO = 3;
    static final int ENERGY = 4;
    static final int TRAP = 5;
    static final int ROBOT = 8;

    static ImageIcon HealthImage = new ImageIcon(CGameObject.class.getResource("/Resources/Health.png"));
    static ImageIcon AmmoImage = new ImageIcon(CGameObject.class.getResource("/Resources/Ammo.png"));
    static ImageIcon EnergyImage = new ImageIcon(CGameObject.class.getResource("/Resources/Energy.png"));
    static ImageIcon TrapImage = new ImageIcon(CGameObject.class.getResource("/Resources/Trap.png"));

    CGame Game;
    int TypeId;
    String TypeName;
    Color Color;
    float X;
    float Y;
    int Number;
    boolean isVisible;
    abstract void Draw(Graphics2D g,float d);
    abstract void ReDraw(Graphics2D g,float d);
    abstract boolean isAim();


}
