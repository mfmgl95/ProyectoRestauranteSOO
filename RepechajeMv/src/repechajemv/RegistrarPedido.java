/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repechajemv;

import Clases.DetallePedido;
import Clases.Item;
import Clases.Pedido;
import Clases.Receta;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author TOSHIBA
 */
public class RegistrarPedido extends javax.swing.JFrame {

    /**
     * Creates new form RegistrarPedido
     */
    
    Calendar c = new GregorianCalendar();
    DefaultTableModel modelo ; 
    Connection cnx;
    int nroMesaActual;
    public RegistrarPedido(Connection cnx) {
        initComponents();
        this.setTitle("Registrar Pedido");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.cnx = cnx;
        fecha();
    }
    
    
    public void cambiarValorMesa(String nro){
        lbNroMesa.setText(nro);
    }
    
    public void setMesaActual(int mesa){
        this.nroMesaActual = mesa;
    }
     public int getMesaActual(){
        return this.nroMesaActual;
    }
     
    public void consultarReceta(String nombre){
        
        Item i = new Item(cnx);
        i.ObtenerDatos(nombre);
        Receta r = new Receta(cnx);
        r.obtenerDatos(i.getIdItem());
        
        RecetaGUI rG = new RecetaGUI(this, true, r.getDescripcion(), 
                r.getIngredientes(), r.getPreparacion());
        rG.setVisible(true);
        
        //JOptionPane.showMessageDialog(null,r.getDescripcion(), nombre, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void llenarTabla(ArrayList<String> codigos,ArrayList<String> tipos,
            ArrayList<String> descripciones, ArrayList<String> cantidad){
        
        String[] columnas = new String[]{"Código","Tipo","Descripción","Selecciona",
            "Cantidad"};
        
        final Class[] tiposColumnas = new Class[]{
            java.lang.String.class,
            java.lang.String.class,
            java.lang.String.class,
            JButton.class,
            java.lang.Object.class 
        };
        /*       
        Object[][] datos = new Object[][]{
            {"code", "En", "Descripcion", new JButton("Consultar Receta"),"10"},
            {"code", "A", "Descripcion", new JButton("Consultar Receta"),"10"},
            {"code", "Ex", "Descripcion", new JButton("Consultar Receta"),"10"}
        };*/        
        Object[][] datos = new Object[codigos.size()][5];
       
        
        for(int i = 0;i< codigos.size();i++){
            
            datos[i][0] = codigos.get(i);
            datos[i][1] = tipos.get(i);
            datos[i][2] = descripciones.get(i);
            datos[i][3] = new JButton("Consultar Receta");
            datos[i][4] = cantidad.get(i);
        }
        

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(datos,columnas ){
            // Esta variable nos permite conocer de antemano los tipos de datos de cada columna, dentro del TableModel
            Class[] tipos = tiposColumnas;

            @Override
            public Class getColumnClass(int columnIndex) {
                // Este método es invocado por el CellRenderer para saber que dibujar en la celda,
                // observen que estamos retornando la clase que definimos de antemano.
                return tipos[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Sobrescribimos este método para evitar que la columna que contiene los botones sea editada.
                return !(this.getColumnClass(column).equals(JButton.class));
            }
        });
        
        tbDetalle.setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                /**
                 * Observen que todo lo que hacemos en éste método es retornar el objeto que se va a dibujar en la 
                 * celda. Esto significa que se dibujará en la celda el objeto que devuelva el TableModel. También 
                 * significa que este renderer nos permitiría dibujar cualquier objeto gráfico en la grilla, pues 
                 * retorna el objeto tal y como lo recibe.
                 */
                return (Component) objeto;
            }
        });
        
        tbDetalle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tbDetalle.rowAtPoint(e.getPoint());
                int columna = tbDetalle.columnAtPoint(e.getPoint());

                /**
                 * Preguntamos si hicimos clic sobre la celda que contiene el botón, si tuviéramos más de un botón 
                 * por fila tendríamos que además preguntar por el contenido del botón o el nombre de la columna
                 */
                if (tbDetalle.getModel().getColumnClass(columna).equals(JButton.class)) {
                    
                    consultarReceta(String.valueOf(tbDetalle.getValueAt(fila,2)));
                    //JOptionPane.showMessageDialog(null, "Seleccionada la fila " + fila );
                }
            }
        });
        
        
        /*JButton boton = new JButton("Consultar Receta");
        boton.setSize(100,45);
        boton.setVisible(true);
        ActionListener listener = new ActionListener(){ 
           public void actionPerformed(ActionEvent e) { 
             consultarReceta(descripcion); //lo declaras en otra parte del documento
           }         
        };
        boton.addActionListener(listener);*/
        
    }
    
    public void fecha(){
        String dia,mes,annio;
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        annio = Integer.toString(c.get(Calendar.YEAR));
        lbFecha.setText(dia+"/"+mes+"/"+annio);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        pRegistrarPedido = new javax.swing.JPanel();
        pDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bCancelar = new javax.swing.JButton();
        bGuardar = new javax.swing.JButton();
        lbNroMesa = new javax.swing.JLabel();
        lbFecha = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miSeleccionarMesa = new javax.swing.JMenuItem();
        miSeleccionarItem = new javax.swing.JMenuItem();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbDetalle);

        javax.swing.GroupLayout pDetalleLayout = new javax.swing.GroupLayout(pDetalle);
        pDetalle.setLayout(pDetalleLayout);
        pDetalleLayout.setHorizontalGroup(
            pDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDetalleLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pDetalleLayout.setVerticalGroup(
            pDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel1.setText("Número de Mesa: ");

        jLabel2.setText("Fecha: ");

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        bGuardar.setText("Guardar");
        bGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarActionPerformed(evt);
            }
        });

        lbNroMesa.setText("0");

        javax.swing.GroupLayout pRegistrarPedidoLayout = new javax.swing.GroupLayout(pRegistrarPedido);
        pRegistrarPedido.setLayout(pRegistrarPedidoLayout);
        pRegistrarPedidoLayout.setHorizontalGroup(
            pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pRegistrarPedidoLayout.createSequentialGroup()
                .addGroup(pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pRegistrarPedidoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(bGuardar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pRegistrarPedidoLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pRegistrarPedidoLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lbNroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pRegistrarPedidoLayout.setVerticalGroup(
            pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pRegistrarPedidoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(lbNroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(pDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bGuardar)
                    .addComponent(bCancelar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jMenu1.setText("Menú");

        miSeleccionarMesa.setText("Seleccionar Mesa");
        miSeleccionarMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSeleccionarMesaActionPerformed(evt);
            }
        });
        jMenu1.add(miSeleccionarMesa);

        miSeleccionarItem.setText("Seleccionar Item");
        miSeleccionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSeleccionarItemActionPerformed(evt);
            }
        });
        jMenu1.add(miSeleccionarItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pRegistrarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pRegistrarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miSeleccionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSeleccionarItemActionPerformed
        // TODO add your handling code here:
        SeleccionarItem sI = new SeleccionarItem(cnx,this);
        sI.setVisible(true);
    }//GEN-LAST:event_miSeleccionarItemActionPerformed

    private void miSeleccionarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSeleccionarMesaActionPerformed
        // TODO add your handling code here:
        SeleccionarMesa sM = new SeleccionarMesa(cnx,this);
        sM.setVisible(true);
    }//GEN-LAST:event_miSeleccionarMesaActionPerformed

    private void bGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarActionPerformed
        try {
            // TODO add your handling code here:
            int idP = (int) (Math.random()*10000+1);
            Pedido p = new Pedido(idP,lbFecha.getText(), 0,Integer.valueOf(lbNroMesa.getText()), cnx);
            p.almacenarPedido();
            
            for(int i=0;i<tbDetalle.getRowCount();i++){
                DetallePedido dP = new DetallePedido(Integer.valueOf(String.valueOf(tbDetalle.getValueAt(i, 0))), 
                        idP, String.valueOf(tbDetalle.getValueAt(i, 4)), cnx);
                dP.almacenarDetalle(Integer.valueOf(String.valueOf(tbDetalle.getValueAt(i, 4))));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_bGuardarActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            //UPDATE mesa SET estado=0 WHERE idMesa=1;
            PreparedStatement pstm = cnx.prepareStatement("UPDATE mesa " +
                    "SET estado = ? " +
                    "WHERE idMesa = ? ");
            pstm.setInt(2,nroMesaActual);
            pstm.setInt(1,1);           
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SeleccionarMesa.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistrarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form *//*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RegistrarPedido().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarPedido.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RegistrarPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bGuardar;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbNroMesa;
    private javax.swing.JMenuItem miSeleccionarItem;
    private javax.swing.JMenuItem miSeleccionarMesa;
    private javax.swing.JPanel pDetalle;
    private javax.swing.JPanel pRegistrarPedido;
    private javax.swing.JTable tbDetalle;
    // End of variables declaration//GEN-END:variables
}
