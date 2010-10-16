/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Visual;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Danich
 */
public class CVisualizer
{
    static  ArrayList<CGame> mainGame = new ArrayList<CGame>();
    static  Rectangle WindowBounds;
    static  String fs=System.getProperty("file.separator");

    public void NewGame() throws FileNotFoundException {
        JFileChooser FileChooser = new JFileChooser();

        FileChooser.setFileFilter(new FileNameExtensionFilter("GameFile", "txt"));
        FileChooser.setDialogTitle("Chose the Game");
        FileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")+fs+"Data"+fs+"test1"+fs+"Games"));
        int Replay = FileChooser.showOpenDialog(null);
        if ((Replay == JFileChooser.CANCEL_OPTION)||(Replay == JFileChooser.ERROR_OPTION)) return;
        File GameFile = FileChooser.getSelectedFile();

        if (GameFile == null) return;
        NewGame(GameFile);
    }
    //private final File GameFile;

    public void NewGame(File GameFile) throws FileNotFoundException
    {
        mainGame.add(new CGame(this, GameFile));
    }
    public void NewGame(String FileName) throws FileNotFoundException
    {
        File GameFile = new File(System.getProperty("user.dir")+fs+"Data"+fs+"test1"+fs+"Games"+fs+FileName+".txt");
        if (GameFile == null){
            System.out.println("can't open generation file");
            return;
        }
        NewGame(GameFile);
    }

    public CVisualizer() throws FileNotFoundException, IOException
    {
        NewGame();
    }
    public CVisualizer(String FileName) throws FileNotFoundException, IOException
    {
        NewGame(FileName);
    }
    public CVisualizer(File GameFile) throws FileNotFoundException, IOException
    {
        NewGame(GameFile);
    }

    static void SaveWindowBounds(int x, int y, int width, int height)
    {
        WindowBounds = new Rectangle(x,y,width,height);
    }



}
