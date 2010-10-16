/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Visual;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Danich
 */


public class CGame
{
    
    static Color BackGroundColor = new Color(176,176,176);
    static int MaxSpeed = 25;
    static String fs=System.getProperty("file.separator");

    private float ElementSize = 50.0f;
    private float t=-1;
    private int CurrenTurnToDisplay=1;
    private float Speed;
    private int GameDirection = -1;
    private boolean Transition = false;
    private boolean Play = false;
    private boolean ShowNames;
    private boolean ShowHealth = true;


    private BufferedImage MapImage;

    private String Status;
    //String GameFileName;
    //private Properties GameProperties;
    File GameFile;

    InputFileDisplay Display;
    static CVisualizer Visualizer;

    private Drawer frame;
    private String ver;
    private int id;
    private String text_if;
    private String comment;
    private String date;
    private String time;

    private int map_id;
    private int Height;
    private int Width;
    private ArrayList<CMapObject> Map = new ArrayList<CMapObject>();

    ArrayList<CGameObject> GameElements = new ArrayList<CGameObject>();

    private int RobotCount;
    private CRobot[] TransitionRobots;

    private int TurnCount;
    private CTurn[] Turns;

    void SetMap(ArrayList<CMapObject> _Map)
    {
        Map = (ArrayList<CMapObject>) _Map.clone();
        DrawMapToImage(MapImage, ElementSize, Map);
    }
    void ResizeGame()
    {
        ResizeGame(frame.Canvas.getWidth(),frame.Canvas.getHeight());
    }
    void ResizeGame(int CanvasWidth, int CanvasHeight)
    {
        float dx = CanvasWidth/(float)Width;
        float dy = CanvasHeight/(float)Height;
        ElementSize = Math.min(dx, dy);
        MapImage = new BufferedImage(CanvasWidth, CanvasHeight, BufferedImage.TYPE_INT_ARGB);
        DrawMapToImage(MapImage,ElementSize,Map);
    }
    public void PaintingEnabled(boolean isPaintingEnabled)
    {
        if (isPaintingEnabled) frame.Canvas.start(); else frame.Canvas.stop();
    }
    public CGame(){}
    public CGame(CVisualizer _Visualizer,File _GameFile) throws FileNotFoundException
    {
        Visualizer = _Visualizer;
        GameFile = _GameFile;
        Properties GameProperties = new Properties();
        try
        {
            GameProperties.load(new FileInputStream(GameFile.getAbsolutePath()));
            id = Integer.valueOf(GameProperties.getProperty("id"));
            ver = GameProperties.getProperty("ver");
            text_if = GameProperties.getProperty("text_if");
            comment = GameProperties.getProperty("comment");
            date = GameProperties.getProperty("date");
            time = GameProperties.getProperty("time");

            Height = Integer.valueOf(GameProperties.getProperty("height"));
            Width = Integer.valueOf(GameProperties.getProperty("width"));
            Scanner src = new Scanner(GameProperties.getProperty("map"));
            System.out.println(GameProperties.getProperty("map"));

            int i = 0;
            while (src.hasNext())
            {
                Map.add(new CMapObject(src.nextInt(),this,i%(getWidth()),i/(getWidth()),Map.size()));
                ++i;
            }
            src.close();

            Collections.sort(Map,new CMapObject.ColorComparator());

            RobotCount = Integer.valueOf(GameProperties.getProperty("rb_count"));
            TurnCount = Integer.valueOf(GameProperties.getProperty("turn_count"));
            Turns = new CTurn[TurnCount];
            TransitionRobots = new CRobot[RobotCount];
            for (i = 0; i<TurnCount; ++i)
            {
                Turns[i] = new CTurn(GameProperties.getProperty("t"+String.valueOf(i)),this);
            }
            for (i = 0; i<RobotCount; ++i)
            {
                TransitionRobots[i] = Turns[0].InTurnRobots[i].CloneRobot();
            }
            URL url = this.getClass().getResource("/TurningSound2.wav");
            try {
                SoundEffect.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SoundEffect.Game = this;
            SoundEffect.Mute = false;

            ////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////
            GameElements.addAll(Arrays.asList(TransitionRobots));
            GameElements.addAll(Map);


            frame = new Drawer(this);
/*
         int numItems = 25;
         String listofitems;
         String[] myarray = new String[numItems];
         listofitems = game_prop.getProperty("map");
         StringTokenizer tokens = new StringTokenizer(listofitems," ");
         int i = 0;
         while(tokens.hasMoreTokens())
         {
             myarray[i] = tokens.nextToken();
             System.out.println(myarray[i]);
             System.out.println(i);
             i++;

         }
*
*/
             }
             catch(IOException e)
             {
                e.printStackTrace();
             }


    }
    
    void DrawMapToImage(BufferedImage _MapImage,float d,ArrayList<CMapObject> _Map)
    {        
        Graphics2D g = _MapImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0 ; i < _Map.size(); ++i)
        {
            _Map.get(i).Draw(g,d);
        }
    }

    public void DrawMap(Graphics2D g,BufferedImage _MapImage)
    {
        g.drawImage(_MapImage, 0, 0, _MapImage.getWidth(), _MapImage.getHeight(), null);
    }


    void Draw(Graphics2D g)
    {
        
        DrawMap(g,MapImage);
        FindAimsForRobots();
        if (isTransition())
        {
            if (Math.abs(getT() + getSpeed() * getGameDirection()) < 1)
            {
                setT(getT() + getGameDirection() * getSpeed());
            } else
            {
                setT(1 * getGameDirection());
                setTransition(false);
            }
        }
        else
        {
            if (isPlay())
            {
                if (getCurrenTurnToDisplay() + 1 < getTurnCount())
                {
                    setCurrenTurnToDisplay(getCurrenTurnToDisplay() + 1);
                    setTransition(true);
                    setT(0);
                }  else
                {
                    setPlay(false);
                }

            } else
            {
                setTransition(false);
            }
        }


        if (isTransition())
        {
                ChangeStatus("transition");

        } else
        {
                ChangeStatus("NO transition");
        }
        Turns[getCurrenTurnToDisplay()].IterateInturnTransition(g);
        if (frame != null) frame.DisplayInfo();
    }

    public void ChangeStatus(String str)
    {
        if (Status == null ? str != null : !Status.equals(str))
        {
            Status = str;
            System.out.println(Status);
        }
    }
    InputFileDisplay CreateDisplay()
    {
        Display = new InputFileDisplay(this);
        return Display;
    }
    String DisplayCodeOfAllRobots(InputFileDisplay _Display)
    {
        String s = new String();
        for (int r = 0; r < getRobotCount(); ++r)
        {
            s += DisplayRobotsCode(r,_Display);
        }
        return s;


    }
    String DisplayRobotsCode(int Number,InputFileDisplay _Display)
    {
        String s = new String();
        s += "\nRobot Number "+Number+"\n\n";
        s += Turns[0].InTurnRobots[Number].Code;
        if (_Display != null) _Display.AppendToDisplay(s);
        return s;
    }
    String DisplayByRobots(InputFileDisplay _Display)
    {
        String s = new String();
        for (int r = 0; r < getRobotCount(); ++r)
        {
            s += DisplayForThisRobotTurnsAroundCurrent(r,0,_Display);
        }
        return s;
    }
    String DisplayByTurns(InputFileDisplay _Display)
    {
        String s = new String();
        for (int turn = 0; turn < getTurnCount(); ++turn)
        {
            s += DisplayForThisTurnAllRobots(turn,_Display);
        }
        return s;
    }
    String DisplayForThisTurnAllRobots(int turn,InputFileDisplay _Display)
    {
        String s = new String();
        s += "\nTurn Number "+turn+"\n\n";
        for (int r = 0; r < getRobotCount(); ++r)
        {
            s += ("Robot "+r+": x = "+Turns[turn].InTurnRobots[r].X+", y = "+Turns[turn].InTurnRobots[r].Y+", Direction = "+Turns[turn].InTurnRobots[r].Angle+", action = "+Turns[turn].InTurnRobots[r].Action+", result = "+Turns[turn].InTurnRobots[r].Result+", hp = "+Turns[turn].InTurnRobots[r].hp+"\n");
        }
        if (_Display != null) _Display.AppendToDisplay(s);
        return s;
    }
    String DisplayForThisRobotTurnsAroundGiven(int  RobotNumber,int TurnNumber,int Depth,InputFileDisplay _Display)
    {
        String s = new String();
        s += "\nRobot Number "+RobotNumber+"\n\n";
        int start = 0;
        int end = TurnCount;
        if (Depth != 0)
        {
            if (TurnNumber - Depth > 0) start = TurnNumber-Depth;
            if (TurnNumber + Depth +1 < TurnCount) end = TurnNumber + Depth+1;
        }
        for (int turn = start; turn < end; ++turn)
        {
            s += ("Turn "+turn+": x = "+Turns[turn].InTurnRobots[RobotNumber].X+", y = "+Turns[turn].InTurnRobots[RobotNumber].Y+", Direction = "+Turns[turn].InTurnRobots[RobotNumber].Angle+", action = "+Turns[turn].InTurnRobots[RobotNumber].Action+", result = "+Turns[turn].InTurnRobots[RobotNumber].Result+", hp = "+Turns[turn].InTurnRobots[RobotNumber].hp+"\n");
        }
        if (_Display != null) _Display.AppendToDisplay(s);
        return s;
    }
    String DisplayForThisRobotTurnsAroundCurrent(int  RobotNumber,int Depth,InputFileDisplay _Display)
    {
        return DisplayForThisRobotTurnsAroundGiven(RobotNumber, CurrenTurnToDisplay, Depth, _Display);
    }
    void ChangeDirection(int dir) { setGameDirection(dir); }
    void ChangeDirection() { setGameDirection(getGameDirection() * -1); }

    public CMapObject FindElementHereXY(int x,int y,boolean CanBeNotTargetable,ArrayList<CMapObject> Elements,float d)
    {
        return (CMapObject) FindElementHereXY(x, y, CanBeNotTargetable,new ArrayList<CGameObject>(Elements), d);
    }
    public CGameObject FindElementHereXY(int x,int y,boolean CanBeNotTargetable,ArrayList<CGameObject> Elements,float d)
    {
        for (int i = 0; i < Elements.size(); ++i)
        {
            CGameObject Element = Elements.get(i);
            long Ex = Math.round(Element.X*d);
            long Ey = Math.round(Element.Y*d);
            if( (Ey <= y) && (y< Math.round(Ey+d)) && (Ex <= x) && (x < Math.round(Ex+d)) && (Element.isAim() || CanBeNotTargetable) && Element.isVisible)
            {
                    return Element;
            }
        }
        return null;
    }
    public CGameObject FindElementHereXY(int x,int y)
    {
        return FindElementHereXY(x, y,true,GameElements,ElementSize);
    }

    private void FindAimsForRobots()
    {
        float d = getElementSize();
        CRobot[] r = Turns[getCurrenTurnToDisplay()].InTurnRobots;//may be transition robots?
        for (int j = 0; j < r.length; ++j)
        {
            if (r[j].Action != CRobot.SHOOT)    continue;
            int dir = (int) r[j].Angle;
            CGameObject Aim=null;
            for (int i = 1; i < Math.max(Width,Height); i++)
            {
                
                Aim = FindElementHereXY((int) Math.round((r[j].X - (dir-2)*(dir%2)*i) * d+d/2), (int) Math.round((r[j].Y + (dir-1)*(-dir%2+1)*i) * d+d/2),false,GameElements,ElementSize);

                if (Aim != null)
                {
                    TransitionRobots[j].Aim = Aim;
                    break;
                }
            }
            if (Aim == null)
            {
                TransitionRobots[j].Aim = null;
            }
        }

    }
    void OpenMapEditor()
    {
        MapEditor MapEditor = new MapEditor(this,ElementSize,Width,Height,Map);
    }


    public Drawer getFrame() {
        return frame;
    }
    public void setFrame(Drawer frame) {
        this.frame = frame;
    }
    public float  getElementSize() {
        return ElementSize;
    }
    public void setElementSize(float ElementSize) {
        this.ElementSize = ElementSize;
    }

    public float getT() {
        return t;
    }
    public void setT(float t) {
        this.t = t;
    }
    public int getCurrenTurnToDisplay() {
        return CurrenTurnToDisplay;
    }
    public void setCurrenTurnToDisplay(int CurrenTurnToDisplay) {
        this.CurrenTurnToDisplay = CurrenTurnToDisplay;
    }
    public float getSpeed() {
        return Speed;
    }
    public void setSpeed(float Speed) {
        this.Speed = Speed;
    }
    public int getGameDirection() {
        return GameDirection;
    }
    public void setGameDirection(int GameDirection) {
        this.GameDirection = GameDirection;
    }
    public boolean isTransition() {
        return Transition;
    }
    public void setTransition(boolean Transition) {
        this.Transition = Transition;
    }
    public boolean isPlay() {
        return Play;
    }
    public void setPlay(boolean _Play) {
        Play = _Play;
        frame.setTextPlayButton(_Play);
    }
    public int getTurnCount() {
        return TurnCount;
    }
    public void setTurnCount(int TurnCount) {
        this.TurnCount = TurnCount;
    }
    public boolean isShowNames() {
        return ShowNames;
    }
    public void setShowNames(boolean _ShowNames)
    {
        ShowNames = _ShowNames;
    }
    public boolean isShowHealth() {
        return ShowHealth;
    }
    public void setShowHealth(boolean _ShowHealth)
    {
        ShowHealth = _ShowHealth;
    }
    public int getRobotCount() {
        return RobotCount;
    }
    public int getWidth() {
        return Width;
    }
    public void setWidth(int _Width)
    {
        Width = _Width;
    }
    public int getHeight() {
        return Height;
    }
    public void setHeight(int _Height)
    {
        Height = _Height;
    }
    class CTurn implements Cloneable{


        //CGame Game;
        //Runnable k;
        int Num;//номер хода
        CRobot[] InTurnRobots;

        CTurn CloneTurn() {
                try {
                        return (CTurn) super.clone();
                } catch (CloneNotSupportedException e) {
                        System.out.println("Cloning not allowed.");
                        return this;
                }
        }

        CTurn (){}
        CTurn (String Data,CGame Game) throws FileNotFoundException, IOException
        {
            Scanner src = new Scanner(Data).useDelimiter(" ");
            Num = src.nextInt();
            Properties GameProperties = new Properties();
            GameProperties.load(new FileInputStream(GameFile.getAbsolutePath()));
            System.out.println("");
            System.out.println("Turn "+Num);
            InTurnRobots = new CRobot[Integer.valueOf(GameProperties.getProperty("rb_count"))];
            while (src.hasNextInt())
            {
               int i = src.nextInt();
               int hp  = src.nextInt();
               float x = src.nextInt();
               float y = src.nextInt();
               float Direction = src.nextInt();
               int Action = src.nextInt();
               int Result = src.nextInt();
               String Name = GameProperties.getProperty("rb"+String.valueOf(i)+"_name");
               String Code = GameProperties.getProperty("rb"+String.valueOf(i)+"_code");

               InTurnRobots[i] = new CRobot(hp,x,y,Direction,Action,Result,i,Name,Code,Game);
            }
            src.close();
        }

        public void IterateInturnTransition(Graphics2D g)
        {
                float d = ElementSize;
                CRobot[] PrevTurnRobots = Turns[CurrenTurnToDisplay-1].InTurnRobots;// -1?
                for (int i=0 ; i < InTurnRobots.length; i++)
                {
                    TransitionRobots[i].Action = InTurnRobots[i].Action;  //do i realy need this?
                    TransitionRobots[i].X = InTurnRobots[i].X;
                    TransitionRobots[i].Y = InTurnRobots[i].Y;
                    if (InTurnRobots[i].Action == CRobot.MOVE_FORWARD)
                    {
                        int dir = (int)InTurnRobots[i].Angle;
                        TransitionRobots[i].X += (dir-2)*(dir%2) *(GameDirection+1)/2 - t * (dir-2)*(dir%2);  //??? what means that cooedinates? position in the end of turn or of the begining???
                        TransitionRobots[i].Y -= (dir-1)*(-dir%2+1) * (GameDirection+1)/2 - t * (dir-1)*(-dir%2+1);
                    }
                    if (InTurnRobots[i].GetRotateDirection() != 0)
                    {
                        if (Math.abs(t) == Speed) //play only once in a turn
                            SoundEffect.TURNING.play();
                        //if (Math.abs(t) == 1)
                          //  SoundEffect.TURNING.stop();
                    }
                    TransitionRobots[i].Angle = InTurnRobots[i].Angle - InTurnRobots[i].GetRotateDirection() * (GameDirection+1)/2 + t * InTurnRobots[i].GetRotateDirection();
                    TransitionRobots[i].hp = (int) (InTurnRobots[i].hp + (PrevTurnRobots[i].hp - InTurnRobots[i].hp)*(GameDirection+1)/2 - t *(PrevTurnRobots[i].hp - InTurnRobots[i].hp));

                    TransitionRobots[i].Draw(g,d);
                }
        }
    }

}