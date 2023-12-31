/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package di.uniba.map.b.adventure.swing;

import di.uniba.map.b.adventure.Engine;
import di.uniba.map.b.adventure.games.EnchantedForest;
import di.uniba.map.b.adventure.rest.TimeService;
import java.awt.Color;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author acer
 */
public final class AdventureSwing extends javax.swing.JFrame {
    
    private Color[] cCOLORI={Color.black,Color.blue,Color.cyan,Color.gray,Color.green,Color.magenta, Color.orange, Color.pink ,Color.red,Color.white,Color.yellow}; 
    private Color sfondo;

    /**
     * Creates new form AdventureSwing
     * @throws java.sql.SQLException
     */
    public AdventureSwing() throws SQLException {
        initComponents();
        jTextArea1.setIgnoreRepaint(false);
        jTextArea1.setOpaque(true);
        jComboBox1.setEditable(false);
        sfondo=cCOLORI[9];
        jTextArea1.setBackground(sfondo);
        initConnectionEngine();
    }
    
    public void initConnectionEngine() throws SQLException {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(4321).build();
        ResourceConfig config = new ResourceConfig(TimeService.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        try {
            server.start();
            Engine engine = new Engine(new EnchantedForest());
            engine.execute(jTextField1, jTextArea1, jButton1);
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 600));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(214, 86));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(35);
        jTextArea1.setFont(new java.awt.Font("Dubai", 2, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextArea1.setMinimumSize(new java.awt.Dimension(113, 28));
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTextField1.setColumns(20);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField1);

        jButton1.setText("Ok");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jLabel1.setText("CAMBIA SFONDO:");
        jPanel2.add(jLabel1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nero", "Blu", "Azzrurro", "Grigio", "Verde", "Magenta", "Arancione", "Rosa", "Rosso", "Bianco", "Giallo" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1);

        jButton2.setText("Cambia");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton3.setText("Bianco");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int scelta=jComboBox1.getSelectedIndex();
          sfondo=cCOLORI[scelta];
          if(cCOLORI[scelta].equals(Color.black) || cCOLORI[scelta].equals(Color.blue) || cCOLORI[scelta].equals(Color.gray)){
              jTextArea1.setForeground(Color.white);
              jTextArea1.setBackground(sfondo);
          } else {
              jTextArea1.setForeground(Color.black);
              jTextArea1.setBackground(sfondo);
          }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         sfondo=Color.white;
       jTextArea1.setForeground(Color.black);
       jTextArea1.setBackground(sfondo);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

     /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
