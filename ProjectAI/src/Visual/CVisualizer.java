/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Visual;

import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Danich
 */
public class CVisualizer
{
    static  ArrayList<CGame> mainGame = new ArrayList<CGame>();
    static  Rectangle WindowBounds;

    public void NewGame(String DefaultPath)
    {
    }

    public CVisualizer() throws FileNotFoundException, IOException
    {
        mainGame.add(new CGame(this));
    }

    static void SaveWindowBounds(int x, int y, int width, int height)
    {
        WindowBounds = new Rectangle(x,y,width,height);
    }



}
