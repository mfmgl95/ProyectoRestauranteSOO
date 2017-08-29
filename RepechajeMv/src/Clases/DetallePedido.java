/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOSHIBA
 */
public class DetallePedido {
    
    private int idItem;
    private int idPedido;
    private String cantidad;
    
    private Connection cnx;

    public DetallePedido(Connection cnx){
        this.cnx = cnx;
    }
    
    public DetallePedido(int idItem, int idPedido, String cantidad, Connection cnx) {
        this.idItem = idItem;
        this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.cnx = cnx;
    }
    
    public void almacenarDetalle(int cantidad)throws SQLException{
        
       Statement stmt=(Statement)cnx.createStatement();
       String insert="INSERT INTO detalle (Item_idItem, Pedido_idPedido, cantidad) VALUES ('"+idItem+"','"+idPedido+"','"+cantidad+"');";
       stmt.executeUpdate(insert);
       
       DetalleCarta dt = new DetalleCarta(cnx);
       dt.obtenerDatos(idItem);
       dt.actualizarCantidad(cantidad);
    }
    
    public ResultSet obtenerDetalle(int idPedidoB){
        
        String sql = "SELECT  idItem,tipo,nombre,cantidad "
                + "FROM Detalle  as d "
                + "INNER JOIN item as i "
                + "ON Item_idItem = i.idItem "
                + "WHERE Pedido_idPedido = "+idPedidoB+";";
        Statement st;
        ResultSet rs = null;
        
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DetallePedido.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         
        return rs;
    }
    
    
}
