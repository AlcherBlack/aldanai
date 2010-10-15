/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on May 5, 2010, 12:54:29 AM
 */

package projectai;

import Visual.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;



/**
 *
 * @author alcher
 */

class EvolutionConfig {
    static int gerneration_size=10;
    static int Damage_per_shot=10;
    static int Game_length=150;

}

class MapgenConfig {
    static boolean single_map=true;
    static String map_path="default.txt";
    static String map=""; //if we use a single map
    static int map_width=20;
    static int map_height=20;
    static String spawns="1 1 3 1 5 1 7 1 1 3 4 3 5 3 1 5 7 5 4 7 10 10 11 11 12 12 13 13 14 14 15 15 16 16 17 17 18 18";
}

public class MainFrame extends javax.swing.JFrame {
    public boolean StopEvolution=false;
    static final String fs = System.getProperty("file.separator"); //this allows for cross-platform compatability

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEvolutionConfigPath = new javax.swing.JTextField();
        btnEvolConfigOpen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMapgenConfigPath = new javax.swing.JTextField();
        btnMapgenConfigOpen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainTextArea = new javax.swing.JTextArea();
        btnEvolutionConfigLoad = new javax.swing.JButton();
        btnMapgenConfigLoad = new javax.swing.JButton();
        btnEvolutionBegin = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ROBOT 9000");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtEvolutionConfigPath.setText("/home/alcher/NetBeansProjects/ProjectAI/Data/test1/EvolutionConfig.txt");

        btnEvolConfigOpen.setText("Open...");
        btnEvolConfigOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvolConfigOpenActionPerformed(evt);
            }
        });

        jLabel1.setText("Evolution config:");

        jLabel2.setText("Mapgen config:");

        txtMapgenConfigPath.setText("/home/alcher/NetBeansProjects/ProjectAI/Data/test1/MapgenConfig.txt");

        btnMapgenConfigOpen.setText("Open...");
        btnMapgenConfigOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapgenConfigOpenActionPerformed(evt);
            }
        });

        MainTextArea.setColumns(20);
        MainTextArea.setRows(5);
        jScrollPane1.setViewportView(MainTextArea);

        btnEvolutionConfigLoad.setText("Load");
        btnEvolutionConfigLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvolutionConfigLoadActionPerformed(evt);
            }
        });

        btnMapgenConfigLoad.setText("Load");
        btnMapgenConfigLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapgenConfigLoadActionPerformed(evt);
            }
        });

        btnEvolutionBegin.setText("Lolevolution");
        btnEvolutionBegin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvolutionBeginActionPerformed(evt);
            }
        });

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        jButton1.setText("Code Viewer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Visualise");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMapgenConfigPath, 0, 0, Short.MAX_VALUE)
                                    .addComponent(txtEvolutionConfigPath, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEvolutionBegin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnStop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnMapgenConfigOpen, 0, 0, Short.MAX_VALUE)
                                    .addComponent(btnEvolConfigOpen))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnMapgenConfigLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEvolutionConfigLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 62, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtEvolutionConfigPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMapgenConfigPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnStop)
                            .addComponent(btnEvolutionBegin)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEvolConfigOpen)
                            .addComponent(btnEvolutionConfigLoad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMapgenConfigOpen)
                            .addComponent(btnMapgenConfigLoad))
                        .addGap(40, 40, 40)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEvolConfigOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvolConfigOpenActionPerformed

        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        txtEvolutionConfigPath.setText(file.getAbsolutePath());
        btnEvolutionConfigLoadActionPerformed(null);

}//GEN-LAST:event_btnEvolConfigOpenActionPerformed

    private void btnMapgenConfigOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapgenConfigOpenActionPerformed
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        txtMapgenConfigPath.setText(file.getAbsolutePath());
        Properties MapgenConfigFile = new Properties();
    }//GEN-LAST:event_btnMapgenConfigOpenActionPerformed

    //initializing
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       btnStop.setEnabled(false);
       txtEvolutionConfigPath.setText(System.getProperty("user.dir")+fs+"Data"+fs+"test1"+fs+"EvolutionConfig.txt");
       txtMapgenConfigPath.setText(System.getProperty("user.dir")+fs+"Data"+fs+"test1"+fs+"MapgenConfig.txt");
       MainTextArea.append("Initializing program...\n");
       MainTextArea.append("Done.\n");
       MainTextArea.append("Please load configuration files so the evolution may begin.\n");
    }//GEN-LAST:event_formWindowOpened

    Properties EvolutionConfigFile = new Properties();
    private void btnEvolutionConfigLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvolutionConfigLoadActionPerformed
        
        try {

             EvolutionConfigFile.load(new FileInputStream(txtEvolutionConfigPath.getText()));

             EvolutionConfig.gerneration_size = Integer.valueOf(EvolutionConfigFile.getProperty("generation_size", "10"));
             MainTextArea.append("Evolution configuration file loaded successfully.\n\tVersion is "+EvolutionConfigFile.getProperty("ver","default")+"\n");
             
              }

             //catch exception in case properties file does not exist
             catch(IOException e)
             {
             MainTextArea.append(txtEvolutionConfigPath.getText()+" loading failed.\n");
             }
    }//GEN-LAST:event_btnEvolutionConfigLoadActionPerformed

    Properties MapgenConfigFile = new Properties();
    Properties MapFile = new Properties();
    private void btnMapgenConfigLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapgenConfigLoadActionPerformed
        
        try {

             MapgenConfigFile.load(new FileInputStream(txtMapgenConfigPath.getText()));

             MapgenConfig.single_map=Boolean.valueOf(MapgenConfigFile.getProperty("single_map","TRUE"));
             if (MapgenConfig.single_map) {
                MapgenConfig.map_path=MapgenConfigFile.getProperty("map_path","default_map.txt");
                try {
                    MapFile.load(new FileInputStream(System.getProperty("user.dir")+fs+"Data"+fs+"test1"+fs+"maps"+fs+MapgenConfig.map_path));
                    MainTextArea.append(MapgenConfig.map_path+" map file loaded successfully.\n");
                }
                catch(IOException e) {
                    MainTextArea.append(MapgenConfig.map_path+" loading failed.\n");
                }
                MapgenConfig.map_width=Integer.valueOf(MapFile.getProperty("width","10"));
                MapgenConfig.map_height=Integer.valueOf(MapFile.getProperty("height","10"));
                MapgenConfig.map=MapFile.getProperty("map");
             } else {
                //implement interaction with Ilya
             }
             MainTextArea.append("Mapgen configuration file loaded successfully.\n\tVersion is "+MapgenConfigFile.getProperty("ver")+"\n");
              }

             //catch exception in case properties file does not exist
             catch(IOException e)
             {
             MainTextArea.append(txtMapgenConfigPath.getText()+" loading failed.\n");
             }
    }//GEN-LAST:event_btnMapgenConfigLoadActionPerformed

    private void btnEvolutionBeginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvolutionBeginActionPerformed
        if ((!EvolutionConfigFile.isEmpty())&&(!MapgenConfigFile.isEmpty())) {
            MainTextArea.append("Let the Lolevolution™ begin!\n");
            Evolution evolution = new Evolution(this);
            btnStop.setEnabled(true);
            evolution.Start();
            
        }
        else {MainTextArea.append("Please load all the configs.\n");}
}//GEN-LAST:event_btnEvolutionBeginActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        btnStop.setEnabled(false);
        MainTextArea.append("Stopping evolution. Please wait...\n");
        StopEvolution=true;
    }//GEN-LAST:event_btnStopActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EditorFrame Frame = new EditorFrame();
        Frame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new CVisualizer();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void AddMessage(String s) {
        MainTextArea.append(s);
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea MainTextArea;
    private javax.swing.JButton btnEvolConfigOpen;
    private javax.swing.JButton btnEvolutionBegin;
    private javax.swing.JButton btnEvolutionConfigLoad;
    private javax.swing.JButton btnMapgenConfigLoad;
    private javax.swing.JButton btnMapgenConfigOpen;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtEvolutionConfigPath;
    private javax.swing.JTextField txtMapgenConfigPath;
    // End of variables declaration//GEN-END:variables

}
