/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repechajemv;

import Clases.AgregarFuente;
import Clases.Conexion;
import Clases.ImagePanel;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.theme.DarkStar;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.SWT;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import sun.misc.GC;
import utilitarios.ImagenFondo;

/**
 *
 * @author TOSHIBA
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    
    private Conexion cn;
    private Connection cnx;
    private Dimension dim;
    public Principal() throws SQLException, ClassNotFoundException {
        initComponents();
        inicializaIconos();
        this.setTitle("Bienvenido");
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        /*ImageIcon icon = new ImageIcon("src/Imagenes/");
        ImagePanel panel = new ImagePanel(icon.getImage());
        this.getContentPane().add(panel);*/        
        realizarConexion();        
        //dim=super.getToolkit().getScreenSize();        
        //this.setSize(dim);          
        ponerImagenPanel("/Imagenes/repechajeFondo.png", panPrincipal);
        this.setVisible(true);
        centrarPantalla();
    }
    
    public void inicializaIconos(){
        ImageIcon i = redimensionaYRetorna("src/Imagenes/note.png", 20);
        miRegistrarPedido.setIcon(i);
        
        i = redimensionaYRetorna("src/Imagenes/salir.png", 20);
        mSalir.setIcon(i);
        
        i = redimensionaYRetorna("src/Imagenes/update.png", 20);
        miActualizarPedido.setIcon(i);
        
        i = redimensionaYRetorna("src/Imagenes/receipt.png", 20);
        miRegistrarComprobante.setIcon(i);
        
        i = redimensionaYRetorna("src/Imagenes/iconoFrame.png", 20);
        this.setIconImage(i.getImage());
        
        ImageIcon logo = new ImageIcon("src/Imagenes/repechajeFondo.png");
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(panPrincipal.getWidth(), panPrincipal.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        logo = new ImageIcon(newimg);
    }
    
    public final void ponerImagenPanel(String direccion,javax.swing.JPanel pFondo) {
        ImagenFondo Imagen = new ImagenFondo(pFondo.getWidth(),pFondo.getHeight(),direccion);
        pFondo.add(Imagen);
        pFondo.repaint();
    }
    
    public ImageIcon redimensionaYRetorna(String ruta, int tamanio){
        ImageIcon i = new ImageIcon(ruta);
        Image image = i.getImage(); // transform it
        Image newimg = image.getScaledInstance(tamanio, tamanio, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        i = new ImageIcon(newimg);  // transform it back
        return i;
    }
    
    public ImageIcon redimensiona(String ruta, JPanel panel){
        ImageIcon i = new ImageIcon(ruta);
        Image image = i.getImage(); // transform it
        Image newimg = image.getScaledInstance(panel.getWidth(), panel.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        i = new ImageIcon(newimg);  // transform it back
        return i;
    }        
    
    public final void centrarPantalla() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //para obtener las dimensiones de la pantalla
        Dimension dimen = this.getSize();
        //igual pero para la ventana
        this.setLocation(
            (pantalla.width - dimen.width) / 2,
            (pantalla.height - dimen.height) / 2);
    }
    
    public void realizarConexion() throws SQLException, ClassNotFoundException{
    
        cn = new Conexion();
        if(cn.obtener()!=null){
            cnx = cn.obtener();
            System.out.println("Conexion exitosa");
        }
        else{
            System.out.println("Conexion erronea");
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

        panPrincipal = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mMenu = new javax.swing.JMenu();
        miRegistrarPedido = new javax.swing.JMenuItem();
        miActualizarPedido = new javax.swing.JMenuItem();
        miRegistrarComprobante = new javax.swing.JMenuItem();
        mSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout panPrincipalLayout = new javax.swing.GroupLayout(panPrincipal);
        panPrincipal.setLayout(panPrincipalLayout);
        panPrincipalLayout.setHorizontalGroup(
            panPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 838, Short.MAX_VALUE)
        );
        panPrincipalLayout.setVerticalGroup(
            panPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 533, Short.MAX_VALUE)
        );

        mMenu.setText("Menú");

        miRegistrarPedido.setText("Registrar Pedido");
        miRegistrarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRegistrarPedidoActionPerformed(evt);
            }
        });
        mMenu.add(miRegistrarPedido);

        miActualizarPedido.setText("Actualizar Pedido");
        miActualizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miActualizarPedidoActionPerformed(evt);
            }
        });
        mMenu.add(miActualizarPedido);

        miRegistrarComprobante.setText("Registrar Comprobante");
        miRegistrarComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRegistrarComprobanteActionPerformed(evt);
            }
        });
        mMenu.add(miRegistrarComprobante);

        jMenuBar1.add(mMenu);

        mSalir.setText("Salir");
        mSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mSalirMouseClicked(evt);
            }
        });
        jMenuBar1.add(mSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miRegistrarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRegistrarPedidoActionPerformed
        RegistrarPedido rP = new RegistrarPedido(cnx);
        rP.setVisible(true);
        
    }//GEN-LAST:event_miRegistrarPedidoActionPerformed

    private void miActualizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miActualizarPedidoActionPerformed
        ActualizarPedido aP = new ActualizarPedido(cnx);
        aP.setVisible(true);
        
    }//GEN-LAST:event_miActualizarPedidoActionPerformed

    private void miRegistrarComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRegistrarComprobanteActionPerformed
        RegistrarComprobante rC = new RegistrarComprobante(cn);
        try {
            cn.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        rC.setVisible(true);
    }//GEN-LAST:event_miRegistrarComprobanteActionPerformed

    private void mSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mSalirMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_mSalirMouseClicked
    public static void setUIFont(javax.swing.plaf.FontUIResource f)
    {   
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof javax.swing.plaf.FontUIResource) UIManager.put(key, f);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        AgregarFuente af = new AgregarFuente();
        
        //InputStream is = Principal.class.getResourceAsStream("Fuentes/Boogaloo-Regular.ttf");
        Font font = af.createFont();//Font.createFont(Font.TRUETYPE_FONT, is);
        
        Font sizedFont = font.deriveFont(18f);
       
        try {
            Plastic3DLookAndFeel.setPlasticTheme(new DarkStar());
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
            
            setUIFont(new javax.swing.plaf.FontUIResource(sizedFont));
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu mMenu;
    private javax.swing.JMenu mSalir;
    private javax.swing.JMenuItem miActualizarPedido;
    private javax.swing.JMenuItem miRegistrarComprobante;
    private javax.swing.JMenuItem miRegistrarPedido;
    private javax.swing.JPanel panPrincipal;
    // End of variables declaration//GEN-END:variables
}
