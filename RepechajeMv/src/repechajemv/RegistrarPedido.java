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
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
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
import javax.swing.ImageIcon;
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
        inicializaIconos();
        
        this.setTitle("Registrar Pedido");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.cnx = cnx;
        fecha();
    }
    
    public ImageIcon redimensionaYRetorna(String ruta, int tamanio){
        ImageIcon i = new ImageIcon(ruta);
        Image image = i.getImage(); // transform it
        Image newimg = image.getScaledInstance(tamanio, tamanio, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        i = new ImageIcon(newimg);  // transform it back
        return i;
    }
    
    public void inicializaIconos(){
        ImageIcon i = redimensionaYRetorna("src/Imagenes/table.png", 25);
        mesa.setBorderPainted(false);
        mesa.setSelected(false);
        mesa.setFocusable(false);
        mesa.setIcon(i);
        
        i = redimensionaYRetorna("src/Imagenes/dish.png", 25);
        item.setBorderPainted(false);
        item.setSelected(false);
        item.setFocusable(false);
        item.setIcon(i);
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
        
        /*Item i = new Item(cnx);
        i.ObtenerDatos(nombre);
        Receta r = new Receta(cnx);
        r.obtenerDatos(i.getIdItem());
        
        RecetaGUI rG = new RecetaGUI(this, true, r.getDescripcion(), 
                r.getIngredientes(), r.getPreparacion());
        rG.setVisible(true);
 */
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
        
        for(int i = 0;i< codigos.size();i++){
            ImageIcon icon = redimensionaYRetorna("src/Imagenes/binoculars.png", 20);
            datos[i][0] = codigos.get(i);
            datos[i][1] = tipos.get(i);
            datos[i][2] = descripciones.get(i);
            datos[i][3] = new JButton(icon);
            ((JButton)(datos[i][3])).setBorderPainted(false);
            ((JButton)(datos[i][3])).setBackground(Color.WHITE);
            datos[i][4] = cantidad.get(i);
        }
        
        tbDetalle.setRowHeight(23);
        
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
        mesa = new javax.swing.JButton();
        item = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addContainerGap())
        );
        pDetalleLayout.setVerticalGroup(
            pDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDetalleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
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

        lbNroMesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNroMesa.setText("0");

        mesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesaActionPerformed(evt);
            }
        });

        item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pRegistrarPedidoLayout = new javax.swing.GroupLayout(pRegistrarPedido);
        pRegistrarPedido.setLayout(pRegistrarPedidoLayout);
        pRegistrarPedidoLayout.setHorizontalGroup(
            pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(pRegistrarPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mesa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(item)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addGroup(pRegistrarPedidoLayout.createSequentialGroup()
                .addGroup(pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pRegistrarPedidoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(bGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pRegistrarPedidoLayout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addComponent(pDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 24, Short.MAX_VALUE))
            .addGroup(pRegistrarPedidoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lbNroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        pRegistrarPedidoLayout.setVerticalGroup(
            pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pRegistrarPedidoLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mesa, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(item, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pRegistrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pRegistrarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pRegistrarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarActionPerformed
        if(tbDetalle.getRowCount()==0 || lbNroMesa.getText().equals("0")){
            if(lbNroMesa.getText().equals("0")){
                JOptionPane.showMessageDialog(this, "No ha seleccionado mesa");
            }
            else{
                JOptionPane.showMessageDialog(this, "No ha seleccionado items");
            }
        }
        else{
            try {
                // TODO add your handling code here
                Pedido p = new Pedido(lbFecha.getText(), 0,Integer.valueOf(lbNroMesa.getText()), cnx);
                p.almacenarPedido();

                for(int i=0;i<tbDetalle.getRowCount();i++){
                    DetallePedido dP = new DetallePedido(Integer.valueOf(String.valueOf(tbDetalle.getValueAt(i, 0))), 
                            p.getIdPedido(), String.valueOf(tbDetalle.getValueAt(i, 4)), cnx);
                    dP.almacenarDetalle();
                }

            } catch (SQLException ex) {
                Logger.getLogger(RegistrarPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
        }
        
        
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void mesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesaActionPerformed
        // TODO add your handling code here:
        SeleccionarMesa sM = new SeleccionarMesa(cnx,this);
        sM.setVisible(true);
    }//GEN-LAST:event_mesaActionPerformed

    private void itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemActionPerformed
        // TODO add your handling code here:
        SeleccionarItem sI = new SeleccionarItem(cnx,this);
        sI.setVisible(true);
    }//GEN-LAST:event_itemActionPerformed

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
    private javax.swing.JButton item;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbNroMesa;
    private javax.swing.JButton mesa;
    private javax.swing.JPanel pDetalle;
    private javax.swing.JPanel pRegistrarPedido;
    private javax.swing.JTable tbDetalle;
    // End of variables declaration//GEN-END:variables
}
