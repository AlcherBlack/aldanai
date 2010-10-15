/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Visual;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
/**
 *
 * @author Danich
 */
public class AICanvas extends javax.swing.JPanel implements Runnable
{
    ArrayList<Long> SpeedAnalisisList;
    CGame Game;
    Thread runner;
    
    float PaintTimeAverage=0;
    static long PaintTimeMax=0;

    public AICanvas(CGame _Game)
    {
        Game = _Game;
        float d = Game.getElementSize();
        SpeedAnalisisList = new ArrayList<Long>();
        start();
    }

    public void start() {
        if ( runner == null ) {
            runner = new Thread(this);
            runner.start();
        }
    }


    public void stop() {
        if ( runner != null && runner.isAlive() ) runner.stop();
        runner = null;
    }


    public void ReanalyseSpeed()
    {

    }
    public void run() {

        while (runner != null) {
            repaint();
            try {
                Thread.sleep(PaintTimeMax);
            } catch (InterruptedException ex) {
                Logger.getLogger(AICanvas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    
    @Override
  public void paint(Graphics _G)
  {
        Graphics2D G = (Graphics2D) _G;
        G.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        long time = System.currentTimeMillis();
        G.setColor(CGame.BackGroundColor);
        G.clearRect(0, 0, getWidth(), getHeight());

        Game.Draw(G);
        time = System.currentTimeMillis() - time;
        System.out.print("timeGameDraw = "+time);

        long time2 = System.currentTimeMillis();

        int LengthAverage=100;
        SpeedAnalisisList.add(time);
        if (SpeedAnalisisList.size() == LengthAverage+1)
        {

            long last = SpeedAnalisisList.get(0);
            SpeedAnalisisList.remove(0);
            PaintTimeAverage = PaintTimeAverage - (float)last/(float)(LengthAverage) + (float)SpeedAnalisisList.get(SpeedAnalisisList.size()-1)/(float)LengthAverage;
            
            if (PaintTimeAverage>PaintTimeMax)
            {
                PaintTimeMax =(long) PaintTimeAverage;
                //System.out.println("max = "+PaintTimeMax);
            }

        } else
        {
            PaintTimeAverage = PaintTimeAverage*(float)(SpeedAnalisisList.size()-1)/(float)SpeedAnalisisList.size()+(float)SpeedAnalisisList.get(SpeedAnalisisList.size()-1)/(float)SpeedAnalisisList.size();
            //System.out.println(PaintTimeAverage);
            PaintTimeMax = 0;
        }
        System.out.println(" Average = "+PaintTimeAverage+" Max = "+PaintTimeMax);
        
        if ((time2 = (System.currentTimeMillis() - time2)) > 1)
        {
            System.out.println("Warning calculating average time for paint is begger than 1 it is "+time2);
        }
  }

}