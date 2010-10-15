/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Visual;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;
import javax.swing.ImageIcon;

/**
 * @author Danich
 */
public class CMapObject extends CGameObject
{

    ImageIcon Image;
    float ImageSizeCoeff;
    public void ChangeType(int _NewType)
    {
        CMapObject NewMapElement = new CMapObject(_NewType, Game, X, Y, Number);
        ImageSizeCoeff = NewMapElement.ImageSizeCoeff;
        TypeName = NewMapElement.TypeName;
        Color = NewMapElement.Color;
        Image = NewMapElement.Image;
        TypeId = _NewType;
    }
    public CMapObject(){}
    public CMapObject(int _Type,CGame _Game,float _x,float _y,int _Num)
    {
        isVisible = true;
        Image = new ImageIcon();
        Game = _Game;
        TypeId = _Type;
        X = _x;
        Y = _y;
        Number = _Num;
        Color = Color.WHITE;// every where white backgrounds but only for wall is black look below
        switch (TypeId)
        {
            case FLOOR:
                ImageSizeCoeff = 1;
                TypeName = "Floor";
                break;
            case WALL:
                Color = Color.BLACK;
                ImageSizeCoeff = 1;
                TypeName = "Wall";
                break;
            case HEALTH:
                Image = HealthImage;
                ImageSizeCoeff = 3.0f/4.0f;
                TypeName = "Health";
                break;
            case AMMO:
                Image = AmmoImage;
                ImageSizeCoeff = 3.0f/4.0f;
                TypeName = "Ammo";
                break;
            case ENERGY:
                Image = EnergyImage;
                ImageSizeCoeff = 3.0f/4.0f;
                TypeName = "Energy";
                break;
            case TRAP:
                Image = TrapImage;
                ImageSizeCoeff = 3.0f/4.0f;
                TypeName = "Trap";
                break;
        }
    }
    
    void Draw(Graphics2D g,float d)
    {
        
        long timeDrawMapinside = System.currentTimeMillis();

        float x = X * d, y = Y * d;
        g.setColor(Color);
        g.fill(new Rectangle2D.Float(x, y, d, d));
        g.setStroke(new BasicStroke(1));
        g.draw(new Rectangle2D.Float(x+1, y+1, d-1, d-1));
        
        if ((TypeId != WALL) && (TypeId != FLOOR)) 
        {
            int ImageSize = (int)(d*ImageSizeCoeff);
            g.drawImage(Image.getImage(), (int)(x+(d-ImageSize)/2), (int)(y+(d-ImageSize)/2), ImageSize, ImageSize, null);
        }

        timeDrawMapinside = System.currentTimeMillis() - timeDrawMapinside;
        //System.out.println("time for TimeDraw = "+timeDrawMapinside);

    }

    boolean isAim()
    {
        if (TypeId == WALL) return true; else return false;

    }

    @Override
    void ReDraw(Graphics2D g,float d)
    {
        Draw(g,d);

    }
    static  class ColorComparator implements Comparator
    {
        public int compare(Object o1, Object o2)
        {
          if (!(o1 instanceof CMapObject) || !(o2 instanceof CMapObject))
            throw new ClassCastException();

          CMapObject e1 = (CMapObject) o1;
          CMapObject e2 = (CMapObject) o2;

          return (e1.Color == e2.Color) ? 0 : (e1.Color == java.awt.Color.BLACK) ? 1 : -1;
        }//                                                                        1 : -1 - white first
    }
    static class IndexComparator implements Comparator {
        public int compare(Object o1, Object o2)
        {
          if (!(o1 instanceof CMapObject) || !(o2 instanceof CMapObject))
            throw new ClassCastException();

          CMapObject e1 = (CMapObject) o1;
          CMapObject e2 = (CMapObject) o2;

          return (int) ( (e1.X - e2.X != 0) ? (e1.Y - e2.Y != 0) ? e1.Y - e2.Y : e1.X - e2.X : (e1.Y - e2.Y != 0) ? e1.Y - e2.Y : 0  );
        }
    }




}
