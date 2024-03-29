/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MapEditor.java
 *
 * Created on Jun 11, 2010, 10:17:26 PM
 */

package Visual;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Danich
 */
public class MapEditor extends javax.swing.JFrame {
    float d;
    CGame Game;
    BufferedImage MapImage;
    Point MapSize;
    ArrayList<CMapObject> Map;
    /** Creates new form MapEditor */
    public MapEditor(CGame _Game,float _ElementSize,int width,int height,ArrayList<CMapObject> _Map) {
        Game = _Game;
        d = _ElementSize;
        MapSize = new Point(width, height);
        Map = new ArrayList<CMapObject>();
        Map.addAll(_Map);


        initComponents();
        
        setBackground(CGame.BackGroundColor);
        jPanel1.setBounds(0, 0, Game.getFrame().Canvas.getWidth(), Game.getFrame().Canvas.getHeight());
        setBounds(0, 0, jPanel1.getWidth(), jApplyToGameButton.getHeight()+jSaveButton.getHeight()+jPanel1.getHeight()+37);
        MapImage = new BufferedImage(Game.getFrame().Canvas.getWidth(), Game.getFrame().Canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        jPanel1.repaint();
        setVisible(true);
    }
    void ResizeMap(int CanvasWidth, int CanvasHeight)
    {
        float dx = CanvasWidth/(float)MapSize.x;
        float dy = CanvasHeight/(float)MapSize.y;
        d = Math.min(dx, dy);
        MapImage = new BufferedImage(CanvasWidth, CanvasHeight, BufferedImage.TYPE_INT_ARGB);
        repaint();
    }
    void SaveMapToFile(File SaveFile)
    {
        try {
            Writer output = new BufferedWriter(new FileWriter(SaveFile));
            output.write("map="+PrintMap()+"\n"+"width="+MapSize.x+"\n"+"height="+MapSize.y);
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(MapEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jApplyToGameButton = new javax.swing.JButton();
        jSaveButton = new javax.swing.JButton();
        jPanel1 = new MapEditorCanvas();
        jMenuBar1 = new javax.swing.JMenuBar();
        jSaveMenu = new javax.swing.JMenu();
        jSaveMenuItem = new javax.swing.JMenuItem();
        jSaveAsMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jResizeMenuItem = new javax.swing.JMenuItem();
        jClearMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(286, 293));

        jLabel1.setText("Element Type:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Floor", "Wall", "Health", "Ammo", "Energy", "Trap" }));

        jApplyToGameButton.setText("Apply to Game");
        jApplyToGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jApplyToGameButtonActionPerformed(evt);
            }
        });

        jSaveButton.setText("Save");
        jSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveButtonActionPerformed(evt);
            }
        });

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 303, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 198, Short.MAX_VALUE)
        );

        jSaveMenu.setText("File");

        jSaveMenuItem.setText("Save");
        jSaveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveMenuItemActionPerformed(evt);
            }
        });
        jSaveMenu.add(jSaveMenuItem);

        jSaveAsMenuItem.setText("Save As...");
        jSaveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveAsMenuItemActionPerformed(evt);
            }
        });
        jSaveMenu.add(jSaveAsMenuItem);

        jMenuBar1.add(jSaveMenu);

        jMenu2.setText("Map");

        jResizeMenuItem.setText("Resize");
        jResizeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResizeMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(jResizeMenuItem);

        jClearMenuItem.setText("Clear");
        jClearMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(jClearMenuItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jApplyToGameButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .add(jSaveButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jApplyToGameButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jSaveButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        jPanel1MouseClicked(evt);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jApplyToGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jApplyToGameButtonActionPerformed
        Collections.sort(Map,new CMapObject.ColorComparator());
        Game.SetMap(Map);
        Game.setWidth(MapSize.x);
        Game.setHeight(MapSize.y);
        Game.ResizeGame();

        Game.getFrame().requestFocus();
    }//GEN-LAST:event_jApplyToGameButtonActionPerformed

    private void jSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveButtonActionPerformed
        jSaveMenuItemActionPerformed(evt);
    }//GEN-LAST:event_jSaveButtonActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        CMapObject MapElement = Game.FindMapElementHereXY(evt.getX(), evt.getY(), true,Map,d);
        if (MapElement != null)
        {
            MapElement.ChangeType(jComboBox1.getSelectedIndex());
            jPanel1.repaint();
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jResizeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResizeMenuItemActionPerformed
        //JOptionPane.showMessageDialog(null,"Sorry this feature is developing");
        int width;
        do
        {
            String reply = JOptionPane.showInputDialog("Type in map width (minimun is 1)","1");
            if (reply == null) return;
            width = Integer.valueOf(reply);
        } while (width < 1);
        int height;
        do
        {
            String reply = JOptionPane.showInputDialog("Type in map height (minimun is 1)","1");
            if (reply == null) return;
            height = Integer.valueOf(reply);
        } while (height < 1);
        ArrayList ElementsToDelete = new ArrayList();
        long time = System.currentTimeMillis();
        if ((MapSize.x > width)||(MapSize.y > height))
        {
            for (int i=0; i < Map.size(); ++i)
            {
                if (Map.get(i).X > width-1) ElementsToDelete.add(Map.get(i));
                if (Map.get(i).Y > height-1) ElementsToDelete.add(Map.get(i));
            }
        }
        System.out.println("Adding to deletearray = "+(System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        Iterator<CMapObject> k = ElementsToDelete.iterator();
        while (k.hasNext())
        {
            Map.remove(k.next());
        }
        System.out.println("Deleting that array from map = "+(System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        Collections.sort(Map, new CMapObject.IndexComparator());
        System.out.println("Sorting = "+(System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        for (int i=0; i<width; ++i)
        {
            for (int j=0; j<height; ++j)
            {
                if ((i > MapSize.x-1)||(j > MapSize.y-1)) Map.add(new CMapObject(CGameObject.WALL, Game, i, j,i+j*width));
            }
        }
        System.out.println("adding missing = "+(System.currentTimeMillis() - time));
        MapSize.x = width;
        MapSize.y = height;
        time = System.currentTimeMillis();
        ResizeMap(jPanel1.getWidth(), jPanel1.getHeight());
        System.out.println("Resize = "+(System.currentTimeMillis() - time));
    }//GEN-LAST:event_jResizeMenuItemActionPerformed

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        ResizeMap(jPanel1.getWidth(), jPanel1.getHeight());
    }//GEN-LAST:event_jPanel1ComponentResized

    private void jClearMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearMenuItemActionPerformed
        Iterator <CMapObject> it = Map.iterator();
        while (it.hasNext()){ it.next().ChangeType(0);}
        jPanel1.repaint();
    }//GEN-LAST:event_jClearMenuItemActionPerformed

    private void jSaveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveMenuItemActionPerformed
        SaveMapToFile(new File(Game.GameFile.getName()+"_SavedMap.txt"));
    }//GEN-LAST:event_jSaveMenuItemActionPerformed

    private void jSaveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveAsMenuItemActionPerformed
        JFileChooser fs = new JFileChooser(Game.GameFile);
        fs.setFileFilter(new FileNameExtensionFilter("GameFile", "txt"));
        int replay = fs.showSaveDialog(jSaveAsMenuItem);
        if ((replay == JFileChooser.CANCEL_OPTION)||replay == JFileChooser.ERROR_OPTION) return;
        File SaveFile = fs.getSelectedFile();

        SaveMapToFile(SaveFile);        
    }//GEN-LAST:event_jSaveAsMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jApplyToGameButton;
    private javax.swing.JMenuItem jClearMenuItem;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem jResizeMenuItem;
    private javax.swing.JMenuItem jSaveAsMenuItem;
    private javax.swing.JButton jSaveButton;
    private javax.swing.JMenu jSaveMenu;
    private javax.swing.JMenuItem jSaveMenuItem;
    // End of variables declaration//GEN-END:variables

    private String PrintMap()
    {
        Collections.sort(Map, new CMapObject.IndexComparator());
        String s="";
        for(int i = 0; i<Map.size(); ++i)
        {
            s +=Map.get(i).TypeId+" ";
        }
        return s;
    }
    // End of variables declaration
    class MapEditorCanvas extends javax.swing.JPanel
    {
        @Override
      public void paint(Graphics _g)
      {
            Graphics2D g = (Graphics2D) _g;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(CGame.BackGroundColor);
            g.clearRect(0, 0, getWidth(), getHeight());
            
            Collections.sort(Map,new CMapObject.ColorComparator());
            Game.DrawMapToImage(MapImage, d, Map);
            Game.DrawMap(g,MapImage);
      }
    }

}
