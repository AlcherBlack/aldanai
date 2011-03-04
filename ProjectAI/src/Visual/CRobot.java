package Visual;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**

 * @author Danich
 */
    public class CRobot extends CGameObject implements Cloneable{
    
    static final int SHOOT = 60;
    static final  int TURN_LEFT = 56;
    static final int TURN_RIGHT = 55;
    static final int MOVE_FORWARD = 51;
    static final int NO_ACTION = 80;
    
    private String Name;
    int hp;
    float RobotHealthSize;
    float Angle;
    int Action;
    int Result;
    String Code;
    Color RobotColor;
    Color LaserColor;
    CGameObject Aim=null;

    boolean isDying;
    float DyingCount=0;
    private float RobotBorderWidth;
    private float HealthBarWidth;
    private float RobotSize;
    private float HealthBarSize;
    private float EyeSize;
    private int FontSize;

    

    CRobot CloneRobot() {
            try {
                    return (CRobot) super.clone();
            } catch (CloneNotSupportedException e) {
                    System.out.println("Cloning not allowed.");
                    return this;
            }
    }

    public CRobot(){}

    public CRobot(int _hp,float  _x, float  _y,float  _direction,int _action, int _result,int _Num,String _Name,String _Code,CGame _Game)
    {
        
        Game = _Game;
        TypeId = ROBOT;
        TypeName = "Robot";
        RobotColor = new Color((float)Math.random(),(float)Math.random() ,(float)Math.random(),1);
        LaserColor = RobotColor;
        Number = _Num;
        Name = _Name;
        Code = _Code;
        hp = _hp;
        X = _x;
        Y = _y;
        Angle = _direction;
        Action = _action;
        Result = _result;
        System.out.println("x = "+X+", y = "+Y+", Direction = "+Angle+", action = "+Action+", result = "+Result+", hp = "+hp+", Num "+Number);
    }
    boolean isAim()
    {
        if (hp > 0) return true; else return false;
    }
    void DrawLaser(Graphics2D g,float d) throws FileNotFoundException, IOException
    {
        if (hp <=0 ) return;
        
        if ((Game.isTransition()) && (Action == SHOOT))
        {
            g.setStroke(new BasicStroke((float)EyeSize/2));
            g.setColor(new Color(LaserColor.getRed(), LaserColor.getGreen(), LaserColor.getBlue(), (int) (255*(1+(int)Game.getGameDirection())/2 - 255 * Math.abs(Game.getT())*(int)Game.getGameDirection())   ));
            g.draw(new Line2D.Float(X*d + d/2 - (RobotSize/2 - EyeSize/2)*(float)Math.cos((Angle + 1)*Math.PI/2) + (1-Angle)*(-(int)Angle%2+1)*EyeSize/4,
                                     Y*d + d/2 - (RobotSize/2 - EyeSize/2)*(float)Math.sin((Angle + 1)*Math.PI/2) + (2-Angle)*((int)Angle%2)*EyeSize/4,
                                     Aim.X*d + d/2 + (1-Angle)*(-(int)Angle%2+1)*EyeSize/4,
                                     Aim.Y*d + d/2 + (2-Angle)*((int)Angle%2)*EyeSize/4));
            Aim.ReDraw(g,d);
            if (Math.abs(Game.getT())==Game.getSpeed())
            {
                SoundEffect.LASERSHOOT.play();
            }

        }

    }
    void DrawRobotBody(Graphics2D g,float d)
    {

        java.awt.Stroke  DefaultStroke= g.getStroke();
        BasicStroke RobotBorderStroke = new BasicStroke((float)RobotBorderWidth);
        BasicStroke HealthBarStroke = new BasicStroke((float)HealthBarWidth);

        g.setColor(Color.WHITE);
        g.fill(new Ellipse2D.Float(X*d + RobotBorderWidth/2+(d-RobotSize)/2,
                                    Y*d + RobotBorderWidth/2+(d-RobotSize)/2,
                                    RobotSize-RobotBorderWidth,
                                    RobotSize-RobotBorderWidth));
        // robot health
        if (Game.isShowHealth())
        {
              g.setColor(RobotColor);
              RobotHealthSize = RobotSize*hp/100;
              g.fill(new Ellipse2D.Float(X*d + RobotBorderWidth/2+(d-RobotHealthSize)/2,
                                          Y*d + RobotBorderWidth/2+(d-RobotHealthSize)/2,
                                          RobotHealthSize-RobotBorderWidth,
                                          RobotHealthSize-RobotBorderWidth));
//            another way to draw health
//            g.setStroke(HealthBarStroke);
//            g.drawArc(Math.round(x*d + HealthBarWidth/2+(d-HealthBarSize)/2),Math.round(y*d + HealthBarWidth/2+(d-HealthBarSize)/2), HealthBarSize-HealthBarWidth, HealthBarSize-HealthBarWidth,(int) Math.round(-90*Direction), -180*hp/100);
        }
        // robot border
        g.setColor(Color.BLACK);
        g.setStroke(RobotBorderStroke);
        g.draw(new Ellipse2D.Float(X*d + RobotBorderWidth/2+(d-RobotSize)/2,Y*d + RobotBorderWidth/2+(d-RobotSize)/2, RobotSize-RobotBorderWidth, RobotSize-RobotBorderWidth));

        // robot eye
        g.setColor(Color.BLACK);
        g.fill(new Ellipse2D.Float(X*d+d/2-EyeSize/2  -(RobotSize/2-EyeSize/2)*(float)Math.cos((Angle+1)*Math.PI/2),
                                   Y*d+d/2-EyeSize/2  -(RobotSize/2-EyeSize/2)*(float)Math.sin((Angle+1)*Math.PI/2),
                                   EyeSize,
                                   EyeSize));

        if (Game.isShowNames())
        {
            g.setStroke(DefaultStroke);
            g.setColor(Color.GRAY);
            g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(),FontSize));
            g.drawString(Name, X*d,Y*d+d/2);
        }
        

    }
    void InitSizes(float  d)
    {
        EyeSize = d/4.0f;//size of direction pointer
        FontSize = (int)(d/4.0f);
        RobotBorderWidth = d/14.0f;
        HealthBarWidth = d/16.0f;
        RobotSize = d*7.0f/10.0f;
        HealthBarSize = (RobotSize*9.0f/13.0f-1.0f);
        //SoundEffect.SetDeltaSampleRate(Game.getSpeed()/0.029227138f);
    }
    void Draw(Graphics2D g,float  d)
    {
//        HealthBarSize += (HealthBarSize)%2*(int)Math.pow(-1.0,(Float)d%2)+d%2;
        InitSizes(d);
        if (isDying && isVisible) { DrawDying(g,d); return;}
        if (hp > 0)
        {
            try {
                DrawLaser(g, d);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CRobot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CRobot.class.getName()).log(Level.SEVERE, null, ex);
            }
            DrawRobotBody(g,d);
            isVisible = true;
        } else
        {
            if(isVisible)
            {
                isDying = true;
            }
        }

    }

    @Override
    void ReDraw(Graphics2D g,float d)
    {
        DrawRobotBody(g,d);
    }

    private void DrawDying(Graphics2D g,float d)
    {
            if (DyingCount == 0) SoundEffect.EXPLOSION.play();
            GifDecoder Decoder = new GifDecoder();
            Decoder.read(getClass().getResource("/Resources/Explode.gif"));
            int n = Decoder.getFrameCount();
            BufferedImage frame = Decoder.getFrame((int)Math.round(n*DyingCount));
            g.drawImage(frame,(int)(X*d),(int)(Y*d), (int)d, (int)d,null);
            DyingCount += Game.getSpeed();
            if (DyingCount>=1)
            {
                isDying = false;
                isVisible = false;
                DyingCount = 0;
            }
    }
    public int GetRotateDirection()
    {
        switch (Action)
        {
            case TURN_LEFT:
            {
                return -1;
            }
            case TURN_RIGHT:
            {
                return 1;
            }
            default: return 0;
        }
    }

    
}

