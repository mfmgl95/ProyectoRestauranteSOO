/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import repechajemv.SeleccionarMesa;

/**
 *
 * @author TOSHIBA
 */
public class DetalleCarta {
    
    private int idItem;
    private int idCarta;
    private int cantidad;
    
    Connection cnx;
    
    public DetalleCarta(Connection cnx){
        this.cnx = cnx;
    }
    
    public void obtenerDatos(int idItemB){
        String sql = "SELECT * FROM detalleCarta WHERE idItem = "+idItemB+";";
        Statement st;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                idItem = Integer.valueOf(rs.getString(1));
                idCarta = Integer.valueOf(rs.getString(2));
                cantidad = Integer.valueOf(rs.getString(3));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SeleccionarMesa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarCantidad(int cantidad){
        try {            
            PreparedStatement pstm = cnx.prepareStatement("UPDATE DetalleCarta " +
            "set cantidad = ? " +                   
            "where idItem = ? ");            
            
            int c = this.cantidad-cantidad;
            pstm.setInt(2,idItem);
            pstm.setInt(1,c);
            
            pstm.executeUpdate();
                        
         }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    
}
