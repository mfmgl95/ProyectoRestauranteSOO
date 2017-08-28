/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import repechajemv.SeleccionarMesa;

/**
 *
 * @author TOSHIBA
 */
public class Pedido {
    
    private int idPedido;
    private static String fecha;
    private int estado;
    private int idCliente;
    private int idMesa;
    
    private Connection cnx;

    public Pedido(Connection cnx){
        this.cnx = cnx;
    }
    
    public Pedido(int idPedido, String fecha,int estado, int idMesa,Connection cnx) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.estado = estado;
        this.idMesa =  idMesa;
        this.cnx = cnx;
    }

    public int getIdPedido() {
        return idPedido;
    }
    
    public Date DeStringADate(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String strFecha = fecha;
        Date fechaDate = null;
        try {
            fechaDate = convertUtilToSql(formato.parse(strFecha));
                        System.out.println(fechaDate.toString());
            return fechaDate;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return fechaDate;
        }
    }
    
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    public void almacenarPedido() throws SQLException{
       Statement stmt=(Statement)cnx.createStatement();
       Date date = DeStringADate();
       String insert="INSERT INTO pedido (idPedido, fecha, estado, idMesa) VALUES ('"+idPedido+"','"+date+"','"+estado+"','"+idMesa+"');";
       stmt.executeUpdate(insert);
        
    }
    
    public void ObtenerDatos(String idMesaB){
        
        String sql = "SELECT * FROM Pedido WHERE idMesa = "+idMesaB+";";
        Statement st;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                idPedido = Integer.valueOf(rs.getString(1));
                fecha = rs.getString(2);
                estado = Integer.valueOf(rs.getString(3));
                idMesa = Integer.valueOf(rs.getString(5));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SeleccionarMesa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
