/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TOSHIBA
 */
public class Comprobante {
    
    private int idComprobante;
    private String monto;
    private String fecha;
    private int numero;
    private int idPedido;
    
    Pedido p;
    
    private Connection cnx;
    
    public Comprobante(Connection cnx){
        this.cnx = cnx;
    }
    
    public Comprobante(String monto, String fecha, int numero, int idPedido, Connection cnx) {
        this.monto = monto;
        this.fecha = fecha;
        this.numero = numero;
        this.idPedido = idPedido;
        this.cnx = cnx;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public java.sql.Date getFecha() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date utilDate = format.parse(this.fecha);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    public int obtenerPedido2(int codigo) throws ClassNotFoundException, SQLException{
      
       try {
            CallableStatement proc = cnx.prepareCall("CALL obtenerIdPedido(?)");
            proc.setInt("codigo", codigo);
            
            ResultSet rs = proc.executeQuery();
            System.out.println(rs.getInt(1));
           
            
            
       }
       catch (SQLException e) {                  
            System.out.println(e);
       }
        return 0;
   }
    
    public DefaultTableModel obtenerPedido(int codigo) throws ClassNotFoundException, SQLException{
       
       DefaultTableModel modelo = new DefaultTableModel();
       
       try {
            CallableStatement proc = cnx.prepareCall("CALL obtenPedido(?)");
            proc.setInt("codigo", codigo);
            //proc.registerOutParameter("id_pedido", java.sql.Types.INTEGER);
            
            
            ResultSet rs = proc.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            
            
            
            int cantidadColumnas = rsMd.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
             modelo.addColumn(rsMd.getColumnLabel(i));
            }
            
            while (rs.next()) {
             Object[] fila = new Object[cantidadColumnas];
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
             }
             modelo.addRow(fila);
            }
            
           
            
        } 
       catch (SQLException e) {                  
            System.out.println(e);
       }
       
       return modelo;
   }
   
   public int obtenerNumeroDeComprobantes() throws ClassNotFoundException{
       try {
            CallableStatement sp = cnx.prepareCall("SELECT COUNT(*) FROM comprobante");
            
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
            return rs.getInt(1) + 1;
            }
            
        } 
       catch (SQLException m) {                  
            System.out.println("Error" + m);
       }
       return 0;
   }
    public void guardarComprobante(Comprobante comprobante, Cliente cliente) throws ClassNotFoundException, ParseException{
        
       try {
            CallableStatement sp = cnx.prepareCall("CALL guardarComprobante(?,?,?,?,?,?,?)");
            sp.setDate("fecha", comprobante.getFecha());
            sp.setString("monto", comprobante.getMonto());
            sp.setInt("numero", comprobante.getNumero());
            sp.setInt("Pedido", comprobante.getIdPedido());
            
            
            sp.setString("nombreR", cliente.getNombreR());
           
            sp.setString("DNI_RUC", cliente.getDNI_RUC());
            
            sp.setString("direccion", cliente.getDireccion());
            
            sp.execute();
            System.out.println("Se guardó correctamente");
        } 
       catch (SQLException m) {                  
            System.out.println("Error" + m);
       }
    }
    
}
