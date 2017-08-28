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
import repechajemv.SeleccionarMesa;

/**
 *
 * @author TOSHIBA
 */
public class Item {
    
    private int idItem;
    private String nombre;
    private String tipo;
    private String precio;
    
    Connection cnx;

    public int getIdItem() {
        return idItem;
    }
    
    public Item(Connection cnx){
        this.cnx = cnx;
    }
    
    public void ObtenerDatos(String nombre){
        
        String sql = "SELECT * FROM item WHERE nombre = '"+nombre+"';";
        Statement st;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                idItem = Integer.valueOf(rs.getString(1));
                nombre = rs.getString(2);
                tipo = rs.getString(3);
                precio = rs.getString(4);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SeleccionarMesa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
