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
public class Receta {
    
    private int idReceta;
    private String descripcion;
    private String ingredientes;
    private String preparacion;
    private int idItem;
    
    Connection cnx;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }
    
    public Receta(Connection cnx){
        this.cnx = cnx;
    }
    
    public void obtenerDatos(int idIBuscar){
        
        String sql = "SELECT * FROM Receta WHERE idItem = "+idIBuscar+";";
        Statement st;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                idReceta = Integer.valueOf(rs.getString(1));
                descripcion = rs.getString(2);
                ingredientes = rs.getString(3);
                preparacion = rs.getString(4);
                idItem = Integer.valueOf(rs.getString(5));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SeleccionarMesa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
